package at.spengergasse.fhirstarter.entity;

import at.spengergasse.fhirstarter.model.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="pr_practitioner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Practitioner extends DomainResource {

    public boolean isActive() {
        return active;
    }

    public enum GenderCode{
        male, female, other, unknown
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_pr_id", referencedColumnName = "id")
    private List<Identifier> identifier;

    @Column(name = "pr_active")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hn_pr_id", referencedColumnName = "id")
    private List<HumanName> name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_pr_id", referencedColumnName = "id")
    private List<ContactPoint> telecom;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_pr_id", referencedColumnName = "id")
    private List<Address> address;

    @Enumerated(EnumType.STRING)
    @Column(name = "pr_gender")
    private GenderCode gender;

    @Column(name = "pr_birthdate")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "at_pr_id", referencedColumnName = "id")
    private List<Attachment> photo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "q_pr_id", referencedColumnName = "id")
    private List<Qualification> qualification;
}
