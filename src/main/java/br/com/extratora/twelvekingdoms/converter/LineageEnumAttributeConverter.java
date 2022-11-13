package br.com.extratora.twelvekingdoms.converter;

import br.com.extratora.twelvekingdoms.enums.LineageEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LineageEnumAttributeConverter implements AttributeConverter<LineageEnum, String> {

    @Override
    public String convertToDatabaseColumn(LineageEnum lineageEnum) {
        return lineageEnum.getName();
    }

    @Override
    public LineageEnum convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(LineageEnum.values())
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
