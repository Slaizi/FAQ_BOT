package ru.bogachev.node_service.data.entity.requets;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "photos")
class RequestPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "telegram_file_id")
    private String fileId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RequestPhoto that = (RequestPhoto) object;
        return Objects.equals(id, that.id)
               && Objects.equals(originalFileName, that.originalFileName)
               && Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalFileName, fileId);
    }
}
