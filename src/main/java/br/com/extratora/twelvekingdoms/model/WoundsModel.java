package br.com.extratora.twelvekingdoms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
@Table(name = "WOUNDS")
public class WoundsModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @NotBlank
    @Column(name = "DICE_RANGE", nullable = false, length = 5)
    private String diceRange;

    @NotBlank
    @Column(name = "DESCRIPTION", nullable = false, length = 300)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "WOUND_ID")
    @JsonIgnore
    private Set<SheetModel> sheets = new HashSet<>();

    public void addSheet(SheetModel sheet) {
        sheet.setWound(this);
        this.sheets.add(sheet);
    }

    public void removeSheet(SheetModel sheet) {
        sheet.setWound(null);
        this.sheets.remove(sheet);
    }
}
