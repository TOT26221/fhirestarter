package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.entity.Encounter;
import at.spengergasse.fhirstarter.repository.EncounterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class EncounterControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Autowired
    EncounterRepository encounterRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void getAllEncounters() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/encounter/"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAnEncounter() {
        Encounter encounter = EncounterRepositoryTest.returnOneEncounterJSON(resourceLoader, om);
        Encounter eWithId = encounterRepository.save(encounter);
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/encounter/" + eWithId.getId()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postAnEncounter() {
        Encounter encounter = EncounterRepositoryTest.returnOneEncounterJSON(resourceLoader, om);
        String json = null;
        try {
            json = om.writeValueAsString(encounter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/encounter/")
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
    public void putAnEncounter() {
        var encounterSave = EncounterRepositoryTest.returnOneEncounterJSON(resourceLoader, om);
        assertNotNull(encounterSave);
        encounterSave.setId(null);
        Encounter encounter = encounterRepository.save(encounterSave);

        encounter.setStatus(Encounter.StatusCode.cancelled);

        String json = null;
        try {
            json = om.writeValueAsString(encounter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.put("/api/encounter/" + encounter.getId())
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void deleteAnEncounter() {
        Encounter encounter = EncounterRepositoryTest.returnOneEncounterJSON(resourceLoader, om);
        Encounter eWithId = encounterRepository.save(encounter);
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.delete("/api/encounter/" + eWithId.getId()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}