package src.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "archive")
public class Archive {
    @Id
    @Column(name = "id_archive")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idArchive;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Column(name = "archive_path")
    private String archivePath;

    @ManyToOne
    @JoinColumn(name = "user_a")
    private User userA;

    @Column(name = "approved")
    private Boolean approved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Archive)) return false;
        Archive archive = (Archive) o;
        return idArchive.equals(archive.idArchive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArchive);
    }

    public Long getIdArchive() {
        return idArchive;
    }

    public void setIdArchive(Long idArchive) {
        this.idArchive = idArchive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getArchivePath() {
        return archivePath;
    }

    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
