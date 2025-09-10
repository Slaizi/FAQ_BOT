package ru.bogachev.node_service.data.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.bogachev.node_service.data.entity.requets.Request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Setter
    @Id
    private Long id;

    @Setter
    @Size(max = 36)
    @Column(name = "first_name")
    private String firstName;

    @Setter
    @Size(max = 36)
    @Column(name = "last_name")
    private String lastName;

    @Setter
    @Size(max = 36)
    @Column(name = "username")
    private String username;

    public User(String username, String lastName, String firstName, Long userId) {
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = userId;
    }

    @CreationTimestamp
    @Column(name = "register_date", updatable = false, nullable = false)
    private LocalDateTime registerDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_requests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "request_id")
    )
    private List<Request> requests = new ArrayList<>();

    public List<Request> getRequests() {
        return List.copyOf(requests);
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void deleteRequest(Request request) {
        requests.remove(request);
    }

    @CollectionTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>(Set.of(Role.ROLE_USER));

    public void addUserRole(Role role) {
        roles.add(role);
    }

    public void removeUserRole(Role role) {
        roles.remove(role);
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName)
               && Objects.equals(lastName, user.lastName)
               && Objects.equals(username, user.username)
               && Objects.equals(registerDate, user.registerDate)
               && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, registerDate, roles);
    }
}
