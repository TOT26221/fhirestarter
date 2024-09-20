package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api/patient")
@CrossOrigin("http://localhost:4200")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping
    public @ResponseBody
    Iterable<Patient> getAllPatients() {
// This returns a JSON or XML with the users
        return patientRepository.findAll();
    }
    @GetMapping("/{id}") //http://localhost:8080/api/patient/123
    public ResponseEntity<Patient> getPatient(@PathVariable String id) {
        return patientRepository
                .findById(id)
                .map(patient -> ResponseEntity.ok().body(patient))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody
                                                 Patient patient) {
        patient.setId(null); // ensure to create new names
        var saved = patientRepository.save(patient);
        return ResponseEntity
                .created(URI.create("/api/patient/" + saved.getId()))
                .body(saved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value =
            "id") String patientId, @RequestBody Patient patientDetails) {
        return patientRepository.findById(patientId)
                .map(patient -> {
                    patient.setActive(patientDetails.getActive());
                    patient.setGender(patientDetails.getGender());
                    patient.setIdentifier(patientDetails.getIdentifier());
                    patient.setName(patientDetails.getName());
                    patient.setAddress(patientDetails.getAddress());
                    patient.setBirthDate(patientDetails.getBirthDate());
                    patient.setText(patientDetails.getText());
                    patient.setTelecom(patientDetails.getTelecom());
                    patient.setDeceasedDateTime(patientDetails.getDeceasedDateTime());
                    patient.setDeceasedBoolean(patientDetails.getDeceasedBoolean());
                    Patient updatedPatient =
                            patientRepository.save(patient);
                    return ResponseEntity.ok(updatedPatient);
                }).orElseGet(() -> createPatient(patientDetails));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable(value =
            "id") String patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().<Patient>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
