package br.com.extratora.twelvekingdoms.model;

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
@Table(name = "LINEAGES")
public class LineageModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false, unique = true, length = 20)
    private String name;

    @NotBlank
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotBlank
    @Column(name = "SIZE", nullable = false, length = 50)
    private String size;

    @NotBlank
    @Column(name = "LANGUAGE", nullable = false, length = 50)
    private String language;

    @NotBlank
    @Column(name = "MATURITY", nullable = false, length = 100)
    private String maturity;

    @NotBlank
    @Column(name = "POSITIVE_TRAIT_NAME", nullable = false, unique = true, length = 20)
    private String positiveTraitName;

    @NotBlank
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "POSITIVE_TRAIT_DESCRIPTION", nullable = false)
    private String positiveTraitDescription;

    @NotBlank
    @Column(name = "NEGATIVE_TRAIT_NAME", nullable = false, unique = true, length = 20)
    private String negativeTraitName;

    @NotBlank
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "NEGATIVE_TRAIT_DESCRIPTION", nullable = false)
    private String negativeTraitDescription;
}
