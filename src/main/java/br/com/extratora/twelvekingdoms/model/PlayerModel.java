package br.com.extratora.twelvekingdoms.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "PLAYER")
public class PlayerModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "USERNAME", nullable = false, unique = true, length = 30)
    private String username;

    @NotBlank
    @Column(name = "FIRST_NAME", nullable = false, length = 20)
    private String firstName;

    @NotBlank
    @Column(name = "LAST_NAME", nullable = false, length = 30)
    private String lastName;

    @NotBlank
    @Column(name = "EMAIL", nullable = false, unique = true, length = 50)
    @Email
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PLAYER_ROLES",
            joinColumns = @JoinColumn(name = "PLAYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<RoleModel> roles = new HashSet<>();
}
