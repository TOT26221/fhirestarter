package at.spengergasse.fhirstarter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="sh_status_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StatusHistory extends BackboneElement{

    public enum StatusCode {
        planned, arrived, triaged, @JsonProperty("in-progress") in_progress, onleave, finished, cancelled
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sh_status", nullable = false)
    @NotNull
    private StatusCode status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sh_period_id", nullable = false, referencedColumnName = "id")
    @NotNull
    private Period period;
}
