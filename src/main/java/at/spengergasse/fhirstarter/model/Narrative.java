package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="na_narrative")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Narrative extends Element{
    public enum NarrativeStatusCode {
        generated,
        extensions,
        additional,
        empty
    }

    @Enumerated(EnumType.STRING)
    @Column(name="na_status", nullable = false)
    private NarrativeStatusCode status;

    @Lob
    @Column(name="na_div", nullable = false, columnDefinition = "TEXT")
    private String div;

}
