package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Encounter;
import at.spengergasse.fhirstarter.repository.EncounterRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/encounter/")
@CrossOrigin
public class EncounterController {
    @Autowired
    EncounterRepository encounterRepository;

    @GetMapping()
    public Iterable<Encounter> getAllEncounter(){
        return encounterRepository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<Encounter> getEncounter(@PathVariable String id) {
        return encounterRepository
                .findById(id)
                .map(encounter -> ResponseEntity.ok().body(encounter))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Encounter> createEncounter(@Valid @RequestBody Encounter encounter) {
        encounter.setId(null); // ensure to create new names
        var saved = encounterRepository.save(encounter);
        return ResponseEntity
                .created(URI.create("/api/encounter/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<Encounter> updatedEncounter(@PathVariable(value = "id") String encounterId, @RequestBody Encounter encounterDetails) {
        return encounterRepository.findById(encounterId)
                .map(encounter -> {
                    encounter.setIdentifier(encounterDetails.getIdentifier());
                    encounter.setStatus(encounterDetails.getStatus());
                    encounter.setStatusHistory(encounterDetails.getStatusHistory());
                    encounter.setType(encounterDetails.getType());
                    encounter.setSubject(encounterDetails.getSubject());
                    encounter.setEpisodeOfCare(encounterDetails.getEpisodeOfCare());
                    encounter.setParticipant(encounterDetails.getParticipant());
                    encounter.setAppointment(encounterDetails.getAppointment());
                    encounter.setPeriod(encounterDetails.getPeriod());
                    encounter.setReasonReference(encounterDetails.getReasonReference());
                    encounter.setDiagnosis(encounterDetails.getDiagnosis());
                    encounter.setPartOf(encounterDetails.getPartOf());

                    Encounter updatedEncounter = encounterRepository.save(encounter);
                    return ResponseEntity.ok(updatedEncounter);
                }).orElseGet(() -> createEncounter(encounterDetails));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Encounter> deleteEncounter(@PathVariable(value = "id") String encounterId) {
        return encounterRepository.findById(encounterId).
                map(encounter -> {
                    encounterRepository.delete(encounter);
                    return ResponseEntity.ok().<Encounter>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
