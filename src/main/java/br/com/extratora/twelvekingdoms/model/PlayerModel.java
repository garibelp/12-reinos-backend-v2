package br.com.extratora.twelvekingdoms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PLAYER")
public class PlayerModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive = true;

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

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private Set<SheetModel> sheets = new HashSet<>();
}
