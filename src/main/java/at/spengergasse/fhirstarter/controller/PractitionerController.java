package at.spengergasse.controller;

import at.spengergasse.fhirstarter.entity.*;
import at.spengergasse.fhirstarter.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/practitioner")
public class PractitionerController {

    @Autowired
    private PractitionerRepository practitionerRepository;

    @GetMapping
    public Iterable<Practitioner> getAllPractioners() {
        return practitionerRepository.findAll();
    }

    @PostMapping
    public Practitioner savePractitioner(@Valid @RequestBody Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }

    @PutMapping("/Update/{id}")
    public Practitioner updatePractitioner(@PathVariable String id, @Valid @RequestBody Practitioner updatedPractitioner) {
        // Prüfen, ob der Patient existiert
        if (!practitionerRepository.existsById(id)) {
            // Hier können Sie eine geeignete Fehlerbehandlung durchführen, z.B. eine NotFoundException werfen.
            // Hier wird einfach null zurückgegeben, was darauf hinweisen könnte, dass der Patient nicht gefunden wurde.
            return null;
        }

        // Setzen Sie die ID des aktualisierten Patienten auf die vorhandene ID
        updatedPractitioner.setId(id);
        return practitionerRepository.save(updatedPractitioner);
    }

    @DeleteMapping("/Delete/{id}")
    public void deletePractitioner(@PathVariable String id) {
        // Hier können Sie zusätzliche Überprüfungen hinzufügen, um sicherzustellen, dass der Patient existiert, bevor er gelöscht wird.

        practitionerRepository.deleteById(id);
        System.out.println("Erfolgreicht gelöscht");
    }
    @GetMapping("/OnePractitioner/{id}")
    public ResponseEntity<Practitioner> getPractitioner(@PathVariable String id) {
        return practitionerRepository
                .findById(id)
                .map(practitioner -> ResponseEntity.ok().body(practitioner))
                .orElse(ResponseEntity.notFound().build());
    }

}
