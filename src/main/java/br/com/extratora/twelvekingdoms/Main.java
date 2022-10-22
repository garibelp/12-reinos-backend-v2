package br.com.extratora.twelvekingdoms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class Main {
    @Value("${timezone}")
    private String timezone;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));   // It will set UTC timezone
        log.info("Spring boot application running in {} timezone : {}", timezone, new Date());   // It will print UTC timezone
    }

}
