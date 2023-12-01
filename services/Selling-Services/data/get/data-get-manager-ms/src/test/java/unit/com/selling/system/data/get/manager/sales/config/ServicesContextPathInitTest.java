package com.selling.system.data.get.manager.sales.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = {
        ServicesContextPathInit.class
})
@TestPropertySource(
        properties = {
                "config.services.context-path.data-get-ms=url1",
                "config.services.context-path.data-get-free-ms=url2",
                "config.services.context-path.data-get-opt-ms=url3",
        }
)
class ServicesContextPathInitTest {

    @Autowired
    private Map<String, String> servicesContextPath;

    @DisplayName("test if the map of urls of the services is created successfully")
    @Test
    void testServicesContextPath() {
        assert servicesContextPath.size() == 3;
        assert servicesContextPath.get("data-get-ms").equals("url1");
        assert servicesContextPath.get("data-get-free-ms").equals("url2");
        assert servicesContextPath.get("data-get-opt-ms").equals("url3");
    }
}