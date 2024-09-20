package at.spengergasse.fhirstarter.entity;

import at.spengergasse.fhirstarter.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="en_encounter")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Encounter extends DomainResource {

    public enum StatusCode {
        planned, arrived, triaged, @JsonProperty("in-progress") in_progress, onleave, finished, cancelled
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_en_id")
    private List<Identifier> identifier = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "en_status")
    private StatusCode status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_cc_id")
    private List<CodeableConcept> type = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_re_id")
    private Reference subject;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_pa_id")
    private List<Participant> participant = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_app_re_id")
    private List<Reference> appointment = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_pe_id")
    private Period period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_re_id")
    private List<Reference> reasonReference = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_di_id")
    private List<Diagnosis> diagnosis = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "en_partOf_re_id")
    private List<Reference> partOf = new ArrayList<>();
}
