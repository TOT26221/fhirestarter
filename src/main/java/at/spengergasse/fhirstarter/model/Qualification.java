package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="q_qualification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Qualification extends BackboneElement{
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_q_id", referencedColumnName = "id")
    private List<Identifier> identifier;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_q_id", referencedColumnName = "id")
    private CodeableConcept code;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "q_p_id", referencedColumnName = "id")
    private Period period;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "r_q_id", referencedColumnName = "id")
    private Reference issuer;
}
