package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="ra_ratio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Ratio extends Element {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ra_nu_id", referencedColumnName = "id")
    private Quantity numerator;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ra_de_id", referencedColumnName = "id")
    private Quantity denominator;
}
