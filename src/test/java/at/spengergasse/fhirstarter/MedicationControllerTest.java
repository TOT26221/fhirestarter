package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.entity.Medication;
import at.spengergasse.fhirstarter.repository.MedicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testGetAllMedications() throws Exception {
        mockMvc.perform(get("/api/medication/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetMedicationById() throws Exception {
        Medication medication = medicationRepository.save(returnOneMedicationJSON());

        mockMvc.perform(get("/api/medication/" + medication.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(medication.getId()));
    }

    @Test
    public void testCreateMedication() throws Exception {
        Medication medication = returnOneMedicationJSON();

        mockMvc.perform(post("/api/medication/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medication)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateMedication() throws Exception {
        Medication medication = medicationRepository.save(returnOneMedicationJSON());

        mockMvc.perform(put("/api/medication/" + medication.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medication)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testDeleteMedication() throws Exception {
        Medication medication = medicationRepository.save(returnOneMedicationJSON());

        mockMvc.perform(delete("/api/medication/" + medication.getId()))
                .andExpect(status().isNoContent());
    }

    private Medication returnOneMedicationJSON() {
        Medication medication = null;
        try {
            File dataFile = resourceLoader.getResource("classpath:Medication.json").getFile();
            medication = objectMapper.readValue(dataFile, Medication.class);
        } catch (Exception e) {
            System.err.println("Error reading JSON Object");
        }
        return medication;
    }
}