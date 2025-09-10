package ru.bogachev.node_service.data.entity.requets;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "requests")
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complain_text", nullable = false, length = 2000)
    private String complaintText;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false, nullable = false)
    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "requests_photos",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    private RequestPhoto photo;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Request request = (Request) object;
        return Objects.equals(id, request.id)
               && Objects.equals(complaintText, request.complaintText)
               && Objects.equals(createAt, request.createAt)
               && status == request.status
               && Objects.equals(photo, request.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, complaintText, createAt, status, photo);
    }
}
