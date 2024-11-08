package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="cr_codableReference")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CodableReference extends BackboneElement {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cr_cc_id", referencedColumnName = "id")
    private CodeableConcept concept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cr_re_id", referencedColumnName = "id")
    private Reference reference;
}
