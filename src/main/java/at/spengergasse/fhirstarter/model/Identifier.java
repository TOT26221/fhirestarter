package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name="i_identifier")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Identifier extends Element{
    public enum IdentifierUseCode {usual, official, temp, secondary, old}

    @Enumerated(EnumType.STRING)
    @Column(name="id_use")
    private IdentifierUseCode use;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="i_cc_id")
    private CodeableConcept type;

    @Column(name="i_system")
    private String system;

    @Column(name="i_value")
    private String value;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_pe_id", referencedColumnName = "id")
    private Period period;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_re_id", referencedColumnName = "id")
    private Reference assigner;

}
