package at.spengergasse.fhirstarter.repository;

import at.spengergasse.fhirstarter.entity.Patient;
import at.spengergasse.fhirstarter.entity.Practitioner;
import org.springframework.data.repository.CrudRepository;

public interface PractitionerRepository extends CrudRepository<Practitioner, String> {
}
