package at.spengergasse.fhirstarter.controller;

import at.spengergasse.fhirstarter.entity.Medication;
import at.spengergasse.fhirstarter.repository.MedicationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medication/")
public class MedicationController {

    @Autowired
    private MedicationRepository medicationRepository;

    @Operation(summary = "Get all medications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all medications",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class)) })
    })
    @GetMapping
    public List<Medication> getAllMedications() {
        return (List<Medication>) medicationRepository.findAll();
    }

    @Operation(summary = "Get a medication by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the medication",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found",
                    content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable String id) {
        Optional<Medication> medication = medicationRepository.findById(id);
        return medication.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Medication created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        medication.setId(null); // ensure to create new names
        var saved = medicationRepository.save(medication);
        return ResponseEntity
                .created(URI.create("/api/medications/" + saved.getId()))
                .body(saved);
    }

    @Operation(summary = "Update an existing medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable String id, @RequestBody Medication medicationDetails) {
        Optional<Medication> medication = medicationRepository.findById(id);
        if (medication.isPresent()) {
            Medication updatedMedication = medication.get();
            updatedMedication.setIdentifier(medicationDetails.getIdentifier());
            updatedMedication.setCode(medicationDetails.getCode());
            updatedMedication.setStatus(medicationDetails.getStatus());
            updatedMedication.setManufacturer(medicationDetails.getManufacturer());
            updatedMedication.setForm(medicationDetails.getForm());
            updatedMedication.setAmount(medicationDetails.getAmount());
            updatedMedication.setIngredient(medicationDetails.getIngredient());
            updatedMedication.setBatch(medicationDetails.getBatch());
            return ResponseEntity.ok(medicationRepository.save(updatedMedication));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a medication by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medication deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable String id) {
        if (medicationRepository.existsById(id)) {
            medicationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}