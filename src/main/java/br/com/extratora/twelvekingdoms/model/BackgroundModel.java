package br.com.extratora.twelvekingdoms.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BACKGROUNDS")
public class BackgroundModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "PHYSICAL_POINTS", nullable = false)
    private int physicalPoints;

    @Column(name = "MENTAL_POINTS", nullable = false)
    private int mentalPoints;

    @ManyToOne
    @JoinColumn(name = "ADVANTAGE_ID", nullable = false)
    private AdvantageModel advantage;
}
