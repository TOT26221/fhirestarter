package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.entity.Practitioner;
import at.spengergasse.fhirstarter.repository.PractitionerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/practitioner")
@CrossOrigin("http://localhost:4200")
public class PractitionerController {

    @Autowired
    private PractitionerRepository practitionerRepository;
    @GetMapping
    public @ResponseBody
    Iterable<Practitioner> getAllPractitioners() {
        // This returns a JSON or XML with the users
        return practitionerRepository.findAll();
    }
    @GetMapping("/{id}") //http://localhost:8080/api/patient/123
    public ResponseEntity<Practitioner> getPractitioner(@PathVariable String id) {
        return practitionerRepository
                .findById(id)
                .map(practitioner -> ResponseEntity.ok().body(practitioner))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Practitioner> createPractitioner(@Valid @RequestBody Practitioner practitioner) {
        practitioner.setId(null); // ensure to create new names
        var saved = practitionerRepository.save(practitioner);
        return ResponseEntity
                .created(URI.create("/api/practitioner/" + saved.getId()))
                .body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Practitioner> updatePractitioner(@PathVariable(value = "id") String practitionerId, @RequestBody Practitioner practitionerDetails) {
        return practitionerRepository.findById(practitionerId)
                .map(practitioner -> {
                    practitioner.setActive(practitionerDetails.isActive());
                    practitioner.setGender(practitionerDetails.getGender());
                    practitioner.setIdentifier(practitionerDetails.getIdentifier());
                    practitioner.setName(practitionerDetails.getName());
                    practitioner.setAddress(practitionerDetails.getAddress());
                    practitioner.setBirthDate(practitionerDetails.getBirthDate());
                    practitioner.setText(practitionerDetails.getText());
                    practitioner.setTelecom(practitionerDetails.getTelecom());
                    Practitioner updatedPractitioner =
                            practitionerRepository.save(practitioner);
                    return ResponseEntity.ok(updatedPractitioner);
                }).orElseGet(() -> createPractitioner(practitionerDetails));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable(value =
            "id") String patientId) {
        return practitionerRepository.findById(patientId).map(patient -> {
            practitionerRepository.delete(patient);
            return ResponseEntity.ok().<Patient>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}