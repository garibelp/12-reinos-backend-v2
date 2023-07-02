package br.com.extratora.twelvekingdoms.model;

import br.com.extratora.twelvekingdoms.enums.EnergyTypeEnum;
import br.com.extratora.twelvekingdoms.enums.SkillTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SKILLS")
public class SkillModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false, length = 40)
    private String name;

    @NotBlank
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COST", nullable = false)
    private int cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENERGY_TYPE", nullable = false, length = 12)
    private EnergyTypeEnum energyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "SKILL_TYPE", nullable = false)
    private SkillTypeEnum skillType;

    @Column(name = "RANGE")
    private Integer range;

    @Column(name = "SKILL_LEVEL", nullable = false)
    private int skillLevel;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID", nullable = false)
    private JobModel job;
}
