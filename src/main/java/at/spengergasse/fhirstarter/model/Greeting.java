package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="g_greeting")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private long l;
    private String format;
}
