package br.com.extratora.twelvekingdoms.converter;

import br.com.extratora.twelvekingdoms.enums.Lineage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LineageEnumAttributeConverter implements AttributeConverter<Lineage, String> {

    @Override
    public String convertToDatabaseColumn(Lineage lineage) {
        return lineage.getName();
    }

    @Override
    public Lineage convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(Lineage.values())
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
