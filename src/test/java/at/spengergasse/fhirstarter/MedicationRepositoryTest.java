package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.entity.Medication;
import at.spengergasse.fhirstarter.repository.MedicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    ObjectMapper om;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    @Transactional
    public void testSaveAndLoadOneMedication() {
        // 1. Create an instance of Medication with data
        Medication m = returnOneMedicationJSON();
        // 2. Save the instance to the database
        Medication savedM = medicationRepository.save(m);
        // 3. Load the saved data from the database
        Medication loadedMedication = medicationRepository.findById(savedM.getId()).get();

        assertThat(loadedMedication)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .ignoringFieldsMatchingRegexes(".*id")
                .ignoringFields("form", "ingredient")
                .isEqualTo(m);
    }

    public Medication returnOneMedicationJSON() {
        String json = "";
        Medication m = null;
        // Configure ObjectMapper
        try {
            File dataFile = resourceLoader.getResource("classpath:Medication.json").getFile();
            System.out.println("File exists " + dataFile.exists());
            m = om.readValue(dataFile, Medication.class);
        } catch (Exception e) {
            System.out.println("Error reading JSON Object");
        }
        return m;
    }
}