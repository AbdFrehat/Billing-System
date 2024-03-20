package com.selling.system.shared.module.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())
                .timeZone(TimeZone.getTimeZone("UTC"))
                .featuresToDisable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .featuresToDisable(WRITE_DATES_AS_TIMESTAMPS)
                .featuresToEnable(ORDER_MAP_ENTRIES_BY_KEYS)
                .serializerByType(LocalDateTime.class, customLocalDateTimeSerializer())
                .build();
    }

    @Bean
    public JsonSerializer<LocalDateTime> customLocalDateTimeSerializer() {
        return new JsonSerializer<LocalDateTime>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                OffsetDateTime offsetDateTime = value.atOffset(ZoneOffset.UTC);
                String formattedDateTime = offsetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
                gen.writeString(formattedDateTime);
            }
        };
    }
}
