package br.com.extratora.twelvekingdoms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "APTITUDES")
public class AptitudeModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false, unique = true, length = 20)
    private String name;

    @NotBlank
    @Column(name = "DESCRIPTION", nullable = false, length = 1000)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "aptitudes")
    private Set<JobModel> jobs;

    @JsonIgnore
    @ManyToMany(mappedBy = "aptitudes")
    private Set<SheetModel> sheets;
}
