package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.controller.PractitionerController;
import at.spengergasse.fhirstarter.entity.Practitioner;
import at.spengergasse.fhirstarter.model.HumanName;
import at.spengergasse.fhirstarter.model.Identifier;
import at.spengergasse.fhirstarter.repository.PractitionerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PractitionerRepositoryTest {


    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    ObjectMapper om;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @Transactional
    public void testSaveAndLoadOnePractitioner(){
        //1. Erstellen einer mit Daten befüllten Patienteninstanz
        Practitioner p = returnOnePractitionerJSON();
        //2. Instanz in die DB speichern
        Practitioner savedP = practitionerRepository.save(p);
        //3. Gespeicherte Daten aus der DB lesen
        Practitioner loadedPractitioner =
                practitionerRepository.findById(savedP.getId()).get();

        assertThat(loadedPractitioner)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFieldsMatchingRegexes(".*id")
                .ignoringFields("id")
                .isEqualTo(p);

        assertEquals(true, p.isActive());
    }

    public Practitioner returnOnePractitionerJSON() {
        String json = "";
        Practitioner p = null;
        //OM Configure
        try {
            File dataFile =
                    resourceLoader.getResource("classpath:Practitioner.json").getFile();
            System.out.println("File exists "+dataFile.exists());
            p = om.readValue(dataFile, Practitioner.class);
        } catch (Exception e) {
            System.out.println("Error reading JSON Object");
        }
        return p;
    }

}