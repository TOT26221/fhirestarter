package at.spengergasse.fhirstarter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Table(name="at_attachment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Attachment extends Element{

    @Column(name = "at_contentType")
    private String contentType;

    @Column(name = "at_language")
    private String language;

    @Lob
    @Column(name = "at_data", columnDefinition = "BLOB")
    private byte[] data;

    public Attachment(byte[] data) {
        this.data = Base64.getEncoder().encode(data);
    }

    @Column(name = "at_title")
    private String title;

    @Column(name = "at_creation")
    private LocalDateTime creation;
}
