package at.spengergasse.fhirstarter;

import at.spengergasse.fhirstarter.controller.PractitionerController;
import at.spengergasse.fhirstarter.entity.Practitioner;
import at.spengergasse.fhirstarter.model.HumanName;
import at.spengergasse.fhirstarter.model.Identifier;
import at.spengergasse.fhirstarter.repository.PractitionerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PractitionerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    ObjectMapper om;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void getAllPractitioners() {
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/practitioner"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                    .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAPractitioner() {
        Practitioner p = returnOnePractitionerJSON();
        Practitioner pWithId = practitionerRepository.save(p);

        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/practitioner/"+ pWithId.getId()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void postAPractitioner() {
        Practitioner practitioner = returnOnePractitionerJSON();
        String json = null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.post("/api/practitioner")
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
    public void putAPractitioner() {
        var practitionerSave = returnOnePractitionerJSON();
        assertNotNull(practitionerSave);
        practitionerSave.setId(null);
        Practitioner practitioner = practitionerRepository.save(practitionerSave);

        practitioner.setActive(!practitioner.isActive());
        practitioner.setGender(Practitioner.GenderCode.unknown);

        String json = null;
        try {
            json = om.writeValueAsString(practitioner);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders.put("/api/practitioner/" + practitioner.getId())
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
        public void deleteAPractitioner() {
            Practitioner p = returnOnePractitionerJSON();
            Practitioner pWithId = practitionerRepository.save(p);
            try {
                mockMvc
                        .perform(MockMvcRequestBuilders.delete("/api/practitioner/" + pWithId.getId()))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
