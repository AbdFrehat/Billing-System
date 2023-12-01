package com.selling.system.data.get.manager.sales.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
@ContextConfiguration(classes = {
        WebClientInit.class
})
class WebClientInitTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @DisplayName("test WebClientInit configuration class")
    @Test
    void testWebClientInitTestHasRequiredAnnotations() throws NoSuchMethodException {
        assert WebClientInit.class.isAnnotationPresent(Configuration.class);
        assert WebClientInit.class.isAnnotationPresent(ConditionalOnProperty.class);
        assert WebClientInit.class.getAnnotation(ConditionalOnProperty.class).value()[0].equals("spring.cloud.discovery.enabled");
        assert WebClientInit.class.getMethod("webClientBuilder").getAnnotation(LoadBalanced.class) != null;
        assert WebClientInit.class.getMethod("webClientBuilder").getAnnotation(Bean.class) != null;
        assert webClientBuilder != null;

    }
}