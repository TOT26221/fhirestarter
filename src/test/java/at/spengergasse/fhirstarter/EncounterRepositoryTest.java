package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.entity.Encounter;
import at.spengergasse.fhirstarter.repository.EncounterRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EncounterRepositoryTest {
    @Autowired
    EncounterRepository encounterRepository;

    @Autowired
    ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    private ResourceLoader resourceLoader;



    @Test
    public void testSaveAndLoadOneEncounter() {
        // 1. Erstellen einer mit Daten befüllten Encounterinstanz
        Encounter encounter = returnOneEncounterJSON(resourceLoader, om);

        // 2. Instanz in die DB speichern
        Encounter savedEncoun = encounterRepository.save(encounter);
        // 3. Gespeicherte Daten aus der DB lesen
        Encounter loadedEncounter = encounterRepository.findById(savedEncoun.getId()).get();
        // 4. Vergleich des gespeicherten Objekts mit dem geladenen
        assertThat(loadedEncounter)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFieldsMatchingRegexes(".*id")
                .ignoringFields("id", "diagnosis", "episodeOfCare", "participant", "reasonReference", "statusHistory")
                .isEqualTo(encounter);
    }

    @Test
    public void testUpdateOneEncounter() {
        Encounter encounter = returnOneEncounterJSON(resourceLoader, om);
        Encounter savedEncoun = encounterRepository.save(encounter);

        savedEncoun.setStatus(Encounter.StatusCode.arrived);

        Encounter updatedEncounter = encounterRepository.save(savedEncoun);
        assertThat(updatedEncounter.getStatus()).isEqualTo(Encounter.StatusCode.arrived);;
    }

    public static Encounter returnOneEncounterJSON(ResourceLoader resourceLoader, ObjectMapper om) {
        String json = "";
        Encounter encounter = null;
        //OM Configure
        try {
            File dataFile =
                    resourceLoader.getResource("classpath:Encounter.json").getFile();
            System.out.println("File exists"+dataFile.exists());
            encounter = om.readValue(dataFile, Encounter.class);
        } catch (Exception e) {
            System.out.println("Error reading JSON Object");
        }
        return encounter;
    }

}