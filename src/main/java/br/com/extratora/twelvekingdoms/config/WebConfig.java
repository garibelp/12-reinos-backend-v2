package br.com.extratora.twelvekingdoms.config;

import br.com.extratora.twelvekingdoms.converter.CaseInsensitiveEnumConverter;
import br.com.extratora.twelvekingdoms.enums.Dice;
import br.com.extratora.twelvekingdoms.enums.Lineage;
import br.com.extratora.twelvekingdoms.enums.PlayerSort;
import br.com.extratora.twelvekingdoms.enums.SheetSort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.cors.origins}")
    private String corsAllowedOrigins;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        List<Class<? extends Enum>> enums = List.of(
                PlayerSort.class,
                Sort.Direction.class,
                Dice.class,
                Lineage.class,
                SheetSort.class
        );
        enums.forEach(enumClass -> registry.addConverter(
                String.class,
                enumClass,
                new CaseInsensitiveEnumConverter<>(enumClass))
        );
    }

    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
        converters.add(jacksonMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("Enabling CORS for domain [{}]", corsAllowedOrigins);
        registry.addMapping("/**")
                .allowedOrigins(corsAllowedOrigins)
                .allowedMethods("GET", "OPTIONS", "POST", "PATCH", "PUT", "DELETE");
    }
}
