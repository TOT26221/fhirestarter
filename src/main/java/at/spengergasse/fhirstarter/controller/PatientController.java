package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.repository.PatientRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/patient/")
@CrossOrigin
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
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> onConstraintValidationException(
            ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(),
                    violation.getMessage());
        }
        return errors;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField() , fieldError.getDefaultMessage());
        }
        return errors;
    }
}
