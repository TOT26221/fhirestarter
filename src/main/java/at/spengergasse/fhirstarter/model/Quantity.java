package at.spengergasse.fhirstarter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="qua_quantity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Quantity extends Element {

    @Column(name = "qua_value")
    private double value;

    @Column(name = "qua_comparator")
    private String comparator;

    @Column(name = "qua_unit")
    private String unit;

    @Column(name = "qua_system")
    private String system;

    @Column(name = "qua_code")
    private String code;
}
