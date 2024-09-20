package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "re_reference")
public class Reference extends Element {

    @Column(name="re_ref")
    private String reference;

    @Column(name = "re_type")
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_i_id", referencedColumnName = "id")
    private Identifier identifier;

    @Column(name = "re_display")
    private String display;
}
