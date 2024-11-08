package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="in_ingredient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Ingredient extends BackboneElement{    //muss noch validator hinzugefügt werden, dass nur eines befüllt ist

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_icc_id", referencedColumnName = "id")
    private CodeableConcept itemCodeableConcept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_ire_id", referencedColumnName = "id")
    private Reference itemReference;

    @Column(name = "in_isActive")
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "in_ra_strength", referencedColumnName = "id")
    private Ratio strength;
}
