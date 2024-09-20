package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="c_codableconcept")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CodeableConcept extends Element{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="co_cc_id")
    private List<Coding> coding = new ArrayList<Coding>();

    @Column(name="cc_text")
    private String text;
}
