package at.spengergasse.fhirstarter.entity;

import at.spengergasse.fhirstarter.model.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="p_patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Patient extends DomainResource {

    public enum GenderCode{
        male, female, other, unknown
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_p_id", referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @Column(name = "p_active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="hn_p_id",nullable = true, referencedColumnName ="id")
    private List<HumanName> name = new ArrayList<HumanName>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cp_p_id", referencedColumnName = "id")
    private List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    @Enumerated(EnumType.STRING)
    @Column(name = "p_gender")
    private GenderCode gender;

    @Column(name = "p_birthdate")
    private LocalDate birthDate;

    @Column(name= "p_deceasedboolean")
    private Boolean deceasedBoolean;

    @Column(name="p_deceaseddatetime")
    private LocalDateTime deceasedDateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "a_p_id", referencedColumnName = "id")
    private List<Address> address = new ArrayList<Address>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "re_p_generalPractitioner", referencedColumnName = "id")
    private List<Reference> generalPractitioner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "at_p_photo", referencedColumnName = "id")
    private List<Attachment> photo;
}
