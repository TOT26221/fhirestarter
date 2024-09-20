package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="di_diagnosis")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Diagnosis extends BackboneElement{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "di_re_display", nullable = false)
    @NotNull
    private Reference condition;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "di_cc_system")
    private CodeableConcept use;

    @Column(name = "di_rank")
    private int rank;
}
