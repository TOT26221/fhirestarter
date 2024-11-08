package at.spengergasse.fhirstarter.repository;

import at.spengergasse.fhirstarter.entity.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, String> {
}
