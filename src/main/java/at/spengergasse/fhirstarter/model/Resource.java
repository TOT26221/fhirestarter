package at.spengergasse.fhirstarter.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
