package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.model.Narrative;
import at.spengergasse.fhirstarter.repository.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    PatientRepository patientRepository;

    //Test Get all patients
    @Test
    public void testGetAllPatient() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/patient"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Test Get one patient by id
    @Test
    public void
    getAPatient() {
        try {
            mockMvc

                    .perform(MockMvcRequestBuilders.get("/api/patient/643221yu"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void
    postAPatient() {
        Patient patient = PatientRepositoryTest.returnOnePatient();
        String json = null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/patient")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void putAPatient(){
        Patient patient = PatientRepositoryTest.returnOnePatient();
        patient.setId("7439re");
        String json= null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.put("/api/patient/643221yu")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAPatient() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete("/api/patient/643221yu"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Validator wird getestet, wenn deceasedDateTime und deceasedBoolean beide gesetzt sind
    //soll ein 400 bad Request retourniert werden.
    @Test
    @Transactional
    public void
    postInvalidDeceasedPatient(){
        Patient patient = PatientRepositoryTest.returnOnePatient();
        //patient.setDeceasedDateTime(LocalDateTime.now());
        patient.setDeceasedDateTime(LocalDateTime.now());
        patient.setDeceasedBoolean(true);
        String json= null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/patient/")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Validator wird getestet, ob in Narrative (text) beide Attribute nicht null sind
//soll ein 400 bad Request retourniert werden.
    @Test
    public void
    putInvalidNarrativePatient(){
        Patient patient = PatientRepositoryTest.returnOnePatient();
        Narrative narrative = new Narrative();
        narrative.setStatus(null);
        narrative.setDiv(null);
        patient.setText(narrative);
//patient.setActive(null);
        String json= null;
        try {
            json = om.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/patient")
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
