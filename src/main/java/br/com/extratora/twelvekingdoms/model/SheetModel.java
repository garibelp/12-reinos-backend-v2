package br.com.extratora.twelvekingdoms.model;

import br.com.extratora.twelvekingdoms.enums.DeathRollStatus;
import br.com.extratora.twelvekingdoms.enums.Dice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
@Table(name = "SHEETS")
public class SheetModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "INTELLIGENCE", nullable = false)
    private Dice intelligence;

    @Column(name = "CUNNING", nullable = false)
    private Dice cunning;

    @Column(name = "TENACITY", nullable = false)
    private Dice tenacity;

    @Column(name = "CELERITY", nullable = false)
    private Dice celerity;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "LEVEL", nullable = false)
    private int level = 1;

    @Column(name = "MENTAL_CURRENT", nullable = false)
    private int mentalCurrent = 1;

    @Column(name = "MENTAL_TOTAL", nullable = false)
    private int mentalTotal = 1;

    @Column(name = "PHYSICAL_CURRENT", nullable = false)
    private int physicalCurrent = 1;

    @Column(name = "PHYSICAL_TOTAL", nullable = false)
    private int physicalTotal = 1;

    @Column(name = "HEROISM_CURRENT", nullable = false)
    private int heroismCurrent = 1;

    @Column(name = "HEROISM_TOTAL", nullable = false)
    private int heroismTotal = 1;

    @Column(name = "BOND", length = 200)
    private String bond;

    @Column(name = "MOTIVATION", length = 200)
    private String motivation;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "NOTES")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEATH_ROLL_BODY")
    private DeathRollStatus deathRollBody = DeathRollStatus.UNCHECKED;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEATH_ROLL_MIND")
    private DeathRollStatus deathRollMind = DeathRollStatus.UNCHECKED;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEATH_ROLL_SPIRIT")
    private DeathRollStatus deathRollSpirit = DeathRollStatus.UNCHECKED;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive = true;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    private PlayerModel player;

    @ManyToOne
    @JoinColumn(name = "LINEAGE_ID", nullable = false)
    private LineageModel lineage;

    @ManyToOne
    @JoinColumn(name = "BACKGROUND_ID", nullable = false)
    private BackgroundModel background;

    @ManyToOne
    @JoinColumn(name = "JOB_ID", nullable = false)
    private JobModel job;

    @ManyToMany
    @JoinTable(
            name = "APTITUDE_SHEET",
            joinColumns = @JoinColumn(name = "SHEET_ID"),
            inverseJoinColumns = @JoinColumn(name = "APTITUDE_ID")
    )
    private Set<AptitudeModel> aptitudes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "CAMPAIGN_ID", insertable = false, updatable = false)
    private CampaignModel campaign;

    @ManyToOne
    @JoinColumn(name = "WOUND_ID", insertable = false)
    private WoundsModel wound;

    public void addAptitude(AptitudeModel aptitude) {
        aptitudes.add(aptitude);
    }
}
