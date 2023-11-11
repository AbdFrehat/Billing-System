package com.selling.system.query.shared.module.config;

import com.selling.system.query.shared.module.convertors.StringToPurchaseMethodConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReactiveMongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new StringToPurchaseMethodConvertor());
        return new MongoCustomConversions(converters);
    }
}
