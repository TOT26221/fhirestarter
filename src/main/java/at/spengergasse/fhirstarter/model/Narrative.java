package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="na_narrative")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Narrative extends Element{

    public enum NarrativeStatusCode {
        generated,
        extensions,
        additional,
        empty
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="na_status", nullable = false)
    private NarrativeStatusCode status;

    @NotNull
    @Lob
    @Column(name="na_div", nullable = false, columnDefinition = "TEXT")
    private String div;

}
