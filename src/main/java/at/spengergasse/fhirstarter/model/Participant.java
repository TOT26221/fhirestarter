package at.spengergasse.fhirstarter.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pa_participant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Participant extends BackboneElement{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pa_cc_id", referencedColumnName = "id")
    private List<CodeableConcept> type = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pa_pe_id", referencedColumnName = "id")
    private Period period;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pa_re_id", referencedColumnName = "id")
    private Reference individual;
}
