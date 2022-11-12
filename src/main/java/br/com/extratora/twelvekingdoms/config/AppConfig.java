package br.com.extratora.twelvekingdoms.config;

import br.com.extratora.twelvekingdoms.converter.CaseInsensitiveEnumConverter;
import br.com.extratora.twelvekingdoms.enums.DiceEnum;
import br.com.extratora.twelvekingdoms.enums.LineageEnum;
import br.com.extratora.twelvekingdoms.enums.PlayerSortEnum;
import br.com.extratora.twelvekingdoms.enums.SheetSortEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        List<Class<? extends Enum>> enums = List.of(
                PlayerSortEnum.class,
                Sort.Direction.class,
                DiceEnum.class,
                LineageEnum.class,
                SheetSortEnum.class
        );
        enums.forEach(enumClass -> registry.addConverter(
                String.class,
                enumClass,
                new CaseInsensitiveEnumConverter<>(enumClass))
        );
    }
}
