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
@Table(name = "ADVANTAGES")
public class AdvantageModel implements Serializable {
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
}
