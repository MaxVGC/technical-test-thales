package com.thales.employee_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration class for global CORS (Cross-Origin Resource Sharing) settings.
 * This class implements the WebMvcConfigurer interface to customize CORS configuration.
 */
@Configuration
public class CorsConfigGlobal implements WebMvcConfigurer {

    /**
     * Configures CORS (Cross-Origin Resource Sharing) mappings for the application.
     * This method allows specifying the allowed origins, methods, headers, and credentials for CORS requests.
     *
     * @param registry the CorsRegistry object used to configure the CORS mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}