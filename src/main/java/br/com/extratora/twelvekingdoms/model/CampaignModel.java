package br.com.extratora.twelvekingdoms.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Table(name = "CAMPAIGN")
public class CampaignModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "NOTES")
    private String notes;

    @NotNull
    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    private PlayerModel player;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Set<SheetModel> sheets = new HashSet<>();

    public void addSheet(SheetModel sheet) {
        sheet.setCampaign(this);
        this.sheets.add(sheet);
    }

    public void removeSheet(SheetModel sheet) {
        sheet.setCampaign(null);
        this.sheets.remove(sheet);
    }
}
