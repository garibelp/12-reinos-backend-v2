package br.com.extratora.twelvekingdoms.model;

import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
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
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "INTELLIGENCE", nullable = false)
    private DiceEnum intelligence;

    @Column(name = "CUNNING", nullable = false)
    private DiceEnum cunning;

    @Column(name = "TENACITY", nullable = false)
    private DiceEnum tenacity;

    @Column(name = "CELERITY", nullable = false)
    private DiceEnum celerity;

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

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    private PlayerModel player;

    @ManyToOne
    @JoinColumn(name = "LINEAGE_ID", nullable = false)
    private LineageModel lineage;
}
