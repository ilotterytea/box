package kz.ilotterytea.bin.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * File entity.
 * @author ilotterytea
 * @since 1.0
 */
@Entity
@Table(name = "files")
public class File {
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    private String id;

    @Column(updatable = false)
    private String mime;

    @Column(updatable = false)
    private String extension;

    @JsonIgnore
    @Lob
    private byte[] data;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, nullable = false)
    private Date creationTimestamp;

    public File(String id, String mime, String extension, byte[] data) {
        this.id = id;
        this.mime = mime;
        this.extension = extension;
        this.data = data;
    }

    public File() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
