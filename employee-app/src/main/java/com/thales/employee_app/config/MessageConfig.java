package com.thales.employee_app.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuration class for message sources and validators.
 */
@Configuration
public class MessageConfig {

    /**
     * Creates and configures the message source bean for internationalization.
     * The message source is responsible for resolving messages from resource bundles.
     * 
     * @return the configured message source bean
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Returns a LocalValidatorFactoryBean object configured with the validation message source.
     *
     * @return the LocalValidatorFactoryBean object
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
