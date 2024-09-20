package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.model.*;
import at.spengergasse.fhirstarter.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.io.File;

@SpringBootTest
public class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    ObjectMapper om;

    @Test
    @Transactional
    @Order(1)
    public void testSaveAndLoadOnePatient(){
        Patient p = returnOnePatientJSON();
        //2. Instanz in die DB speichern
        Patient savedP = patientRepository.save(p);
        //3. Gespeicherte Daten aus der DB lesen
        Patient loadedPatient =
                patientRepository.findById(savedP.getId()).get();

        assertThat(loadedPatient)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFieldsMatchingRegexes(".*id")
                .ignoringFields("id")
                .isEqualTo(p);

        assertEquals(true, p.getActive());

    }

    public static Patient returnOnePatient(){
        List<Identifier> identifiers = new ArrayList<>();
        List<Coding> codings = new ArrayList<>();
        List<ContactPoint> contactPoints = new ArrayList<>();
        List<HumanName> names = new ArrayList<>();
        List<Address> address = new ArrayList<>();
        List<String> prefixes = null;
        List<String> suffixes = null;
        //Ein Coding Objekt wird mit wie bisher mit einem Konstruktor gebaut.
                codings.add(new Coding("System", "0.1.1", "Code", "<div>...<div>",
                        false));
        //Eine Period mit Konstruktor
        Period period = new Period(LocalDateTime.of(2000, 01,01,1,1),
                LocalDateTime.of(2001,01,01,1,1));
        //Eine Period mit dem Builder Pattern. Es ist offensichtlicher, welche Attribute gesetzt werden.
        Period period2 = Period.builder()
                .start(LocalDateTime.of(2000, 01,01,1,1))
                .end(LocalDateTime.of(2010, 02,02,2,2))
                .build();
        Period period3 = Period.builder()
                .start(LocalDateTime.of(2001, 01,01,1,1))
                .end(LocalDateTime.of(2011, 02,02,2,2))
                .build();
        CodeableConcept ccType = CodeableConcept.builder()
                .coding(codings)
                .text("<div></div>")
                .build();// new CodeableConcept(codings, "Text");
        identifiers.add(
                Identifier.builder()
                        .use(Identifier.IdentifierUseCode.official)
                        .period(period)
                        .system("System")
                        .type(ccType)
                        .value("value")
                        .build()
        );
        contactPoints.add(
                ContactPoint.builder()
                        .period(period2)
                        .rank(1)
                        .system(ContactPoint.SystemCode.email)
                        .use(ContactPoint.UseCode.home)
                        .value("pirker@spengergasse.at")
                        .build());
                //new ContactPoint(ContactPoint.SystemCode.phone, "123454321", ContactPoint.UseCode.home, 1, period2);
        List<String> givenNames = new ArrayList<>();
        givenNames.add("Simon");
        givenNames.add("2.Vorname");
        names.add(HumanName.builder()
                .family("Pirker")
                .given(givenNames)

                .period(Period.builder().start(LocalDateTime.now()).end(LocalDateTime.now()
                ).build())
                .use(HumanName.UseCode.anonymous)
                .build());
        address.add(
                Address.builder()
                        .city("Wien")
                        .country("Österreich")
                        .district("Wien")
                        .line(Collections.singletonList("Spengergasse 20"))
                        .postalCode("1050")
                        .period(period3)
                        .state("Wien")
                        .text("<div>.../</div>")
                        .type(Address.TypeCode.both)
                        .use(Address.UseCode.home)
                        .build()
        );
        return Patient.builder()
                .active(true)
                .birthDate(LocalDate.of(1999, 01, 01))
                .identifier(identifiers)
                .deceasedBoolean(false)
                .gender(Patient.GenderCode.male)
                .name(names)
                .telecom(contactPoints)
                .address(address)
                .build();
    }

    public Patient returnOnePatientJSON() {
        String json = "";
        Patient p = null;
        //OM Configure
        try {
            File dataFile =
                    resourceLoader.getResource("classpath:Patient.json").getFile();
            System.out.println("File exists"+dataFile.exists());
            p = om.readValue(dataFile, Patient.class);
        } catch (Exception e) {
            System.out.println("Error reading JSON Object");
        }
        return p;
    }

}
