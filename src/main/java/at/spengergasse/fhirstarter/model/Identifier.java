package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="id_identityfier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identityfier extends Element{
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public enum IdentityfierUseCode {usual, official, temp, secondary, old}
        @Enumerated(EnumType.STRING)
        @Column(name="id_use")
        private IdentityfierUseCode use;

}
