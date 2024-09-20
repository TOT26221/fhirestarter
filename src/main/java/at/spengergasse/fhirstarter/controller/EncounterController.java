package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Encounter;
import at.spengergasse.fhirstarter.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/encounter")
@CrossOrigin("http://localhost:4200")
public class EncounterController {

    @Autowired
    private EncounterRepository encounterRepository;

    @GetMapping
    public @ResponseBody
    Iterable<Encounter> getAllEncounters() {
        // This returns a JSON or XML with all encounters
        return encounterRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encounter> getEncounter(@PathVariable String id) {
        return encounterRepository
                .findById(id)
                .map(encounter -> ResponseEntity.ok().body(encounter))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Encounter> createEncounter(@Valid @RequestBody Encounter encounter) {
        encounter.setId(null); // Ensure to create new encounter
        var saved = encounterRepository.save(encounter);
        return ResponseEntity
                .created(URI.create("/api/encounter/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encounter> updateEncounter(@PathVariable(value = "id") String encounterId, @RequestBody Encounter encounterDetails) {
        return encounterRepository.findById(encounterId)
                .map(encounter -> {
                    // Update the encounter fields
                    encounter.setStatus(encounterDetails.getStatus());
                    encounter.setIdentifier(encounterDetails.getIdentifier());
                    encounter.setType(encounterDetails.getType());
                    encounter.setSubject(encounterDetails.getSubject());
                    encounter.setParticipant(encounterDetails.getParticipant());
                    encounter.setAppointment(encounterDetails.getAppointment());
                    encounter.setPeriod(encounterDetails.getPeriod());
                    encounter.setReasonReference(encounterDetails.getReasonReference());
                    encounter.setDiagnosis(encounterDetails.getDiagnosis());
                    encounter.setPartOf(encounterDetails.getPartOf());

                    var updated = encounterRepository.save(encounter);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncounter(@PathVariable(value = "id") String encounterId) {
        return encounterRepository.findById(encounterId)
                .map(encounter -> {
                    encounterRepository.delete(encounter);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
