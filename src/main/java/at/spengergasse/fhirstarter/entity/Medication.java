package at.spengergasse.fhirstarter.entity;

import at.spengergasse.fhirstarter.model.*;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="me_medication")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Medication extends DomainResource {

    public enum MedicationStatus {
        active, inactive, entered_in_error
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_i_id", referencedColumnName = "id")
    private List<Identifier> identifier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_cc_code", referencedColumnName = "id")
    private CodeableConcept code;

    @Enumerated(EnumType.STRING)
    @Column(name = "me_status")
    private MedicationStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_re_id", referencedColumnName = "id")
    private Reference manufacturer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_cc_form", referencedColumnName = "id")
    private CodeableConcept form;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_ra_id", referencedColumnName = "id")
    private Ratio amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_in_id", referencedColumnName = "id")
    private List<Ingredient> ingredient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "me_ba_id", referencedColumnName = "id")
    private Batch batch;
}
