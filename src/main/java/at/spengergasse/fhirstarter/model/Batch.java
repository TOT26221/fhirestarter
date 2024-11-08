package at.spengergasse.fhirstarter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name="ba_batch")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Batch extends BackboneElement{

    @Column(name = "ba_expirationDate")
    private LocalDateTime expirationDate;

    @Column(name = "ba_lotNumber")
    private String lotNumber;
}
