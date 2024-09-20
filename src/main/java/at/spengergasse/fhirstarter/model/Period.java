package at.spengergasse.fhirstarter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pe_period")
@SuperBuilder
public class Period extends Element{

    @Column(name = "pe_start")
    private LocalDateTime start;

    @Column(name = "pe_end")
    private LocalDateTime end;
}
