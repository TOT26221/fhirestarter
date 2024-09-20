package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="at_at1plf")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class At1PLF {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "at_size")
    private int size;

    @Column(name = "at_title")
    private String title;

    @Column(name = "at_creation")
    private LocalDateTime creation;
}
