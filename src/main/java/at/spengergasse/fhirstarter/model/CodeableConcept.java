package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name ="cc_codeableconcept")
public class CodeableConcept extends Element {
    @OneToMany
    @JoinColumn(name="cc_co_fk")
    private List<Coding> coding = new ArrayList<>();

    @Column(name="cc_text")
    private String text;
}
