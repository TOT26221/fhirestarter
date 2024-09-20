package at.spengergasse.fhirstarter.repository;

import at.spengergasse.fhirstarter.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, String> {
}
