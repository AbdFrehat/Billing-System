package com.selling.system.data.get.manager.sales.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {
        EurekaConfig.class
})
class EurekaConfigTest {

    @Autowired
    private EurekaConfig eurekaConfig;

    @DisplayName("test EurekaConfig configuration class")
    @Test
    void testEurekaConfigTestHasRequiredAnnotations() {
        assert eurekaConfig != null;
        Class<?> eurekaConfigClass = EurekaConfig.class;
        assert eurekaConfigClass.isAnnotationPresent(EnableDiscoveryClient.class);
        assert eurekaConfigClass.isAnnotationPresent(EnableEurekaServer.class);
        assert eurekaConfigClass.isAnnotationPresent(Configuration.class);
        assert eurekaConfigClass.isAnnotationPresent(ConditionalOnProperty.class);
        ConditionalOnProperty conditionalOnProperty = eurekaConfigClass.getAnnotation(ConditionalOnProperty.class);
        assert conditionalOnProperty.value() != null;
        assert conditionalOnProperty.value().length > 0;
        assert conditionalOnProperty.value()[0].equals("spring.cloud.discovery.enabled");
    }

}