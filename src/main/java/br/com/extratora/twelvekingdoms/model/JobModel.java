package br.com.extratora.twelvekingdoms.model;

import br.com.extratora.twelvekingdoms.enums.AttributesEnum;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "JOBS")
public class JobModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false, unique = true, length = 20)
    private String name;

    @NotBlank
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "MAIN_ATTRIBUTE", nullable = false, length = 20)
    private AttributesEnum mainAttribute;

    @Column(name = "PHYSICAL_POINTS", nullable = false)
    private int physicalPoints;

    @Column(name = "MENTAL_POINTS", nullable = false)
    private int mentalPoints;

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
    private Set<SkillModel> skills = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "APTITUDE_JOB",
            joinColumns = @JoinColumn(name = "JOB_ID"),
            inverseJoinColumns = @JoinColumn(name = "APTITUDE_ID")
    )
    private Set<AptitudeModel> aptitudes = new HashSet<>();
}
