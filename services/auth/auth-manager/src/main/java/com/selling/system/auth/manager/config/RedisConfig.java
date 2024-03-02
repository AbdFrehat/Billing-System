package com.selling.system.auth.manager.config;

import com.selling.system.auth.manager.model.entities.AuthDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisOperations<String, AuthDetails> redisOperations(ReactiveRedisConnectionFactory connectionFactory) {
        connectionFactory.getReactiveConnection().ping();
        Jackson2JsonRedisSerializer<AuthDetails> serializer = new Jackson2JsonRedisSerializer<>(AuthDetails.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, AuthDetails> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, AuthDetails> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }
}
