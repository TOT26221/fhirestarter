import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AttachmentPLF {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cid;

    private Long size;

    private String title;

    private LocalDateTime creation;
}
