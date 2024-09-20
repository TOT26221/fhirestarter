package at.spengergasse.fhirstarter.repository;

import at.spengergasse.fhirstarter.model.Greeting;
import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, String> {
}
