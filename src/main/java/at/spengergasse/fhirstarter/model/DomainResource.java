package at.spengergasse.fhirstarter.model;

import at.spengergasse.fhirstarter.model.Resource;
import jakarta.persistence.CascadeType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@MappedSuperclass
public abstract class DomainResource extends Resource {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dr_text")
        private Narrative text;
}
