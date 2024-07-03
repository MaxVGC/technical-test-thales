package com.thales.employee_app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

class ServletInitializerTest {
    @Test
    void testConfigure() {
        ServletInitializer servletInitializer = new ServletInitializer();
        SpringApplicationBuilder application = new SpringApplicationBuilder();
        SpringApplicationBuilder result = servletInitializer.configure(application);
        assertNotNull(result);
    }
}
