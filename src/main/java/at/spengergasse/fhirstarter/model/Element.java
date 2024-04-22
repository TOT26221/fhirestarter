package at.spengergasse.fhirstarter.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public abstract class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
