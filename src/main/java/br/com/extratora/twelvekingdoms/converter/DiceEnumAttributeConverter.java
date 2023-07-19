package br.com.extratora.twelvekingdoms.converter;

import br.com.extratora.twelvekingdoms.enums.Dice;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DiceEnumAttributeConverter implements AttributeConverter<Dice, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Dice dice) {
        return dice.getValue();
    }

    @Override
    public Dice convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Dice.values())
                .filter(d -> value == d.getValue())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
