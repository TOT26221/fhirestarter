package at.spengergasse.fhirstarter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="g_greeting")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private long l;
    private String format;
}
