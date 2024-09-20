package at.spengergasse.fhirstarter.repository;

import at.spengergasse.fhirstarter.entity.Encounter;
import org.springframework.data.repository.CrudRepository;

public interface EncounterRepository extends CrudRepository<Encounter, String> {
}
