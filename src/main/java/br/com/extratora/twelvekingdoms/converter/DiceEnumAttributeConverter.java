package br.com.extratora.twelvekingdoms.converter;

import br.com.extratora.twelvekingdoms.enums.DiceEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DiceEnumAttributeConverter implements AttributeConverter<DiceEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DiceEnum diceEnum) {
        return diceEnum.getValue();
    }

    @Override
    public DiceEnum convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return Stream.of(DiceEnum.values())
                .filter(d -> value == d.getValue())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
