package com.sg.kata.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Jackson Configuration 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 02:24:07
 */
@Configuration
public class JacksonConfiguration implements WebMvcConfigurer {
	
	/**
	 * Method used to define object mapper provider
	 * @return	Object Mapper Provider
	 */
    @Bean
    public ObjectMapper objectMapper() {
    	
    	// Build and return provider
        return new ObjectMapper()
                .setDefaultPropertyInclusion(Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                // .registerModule(new JSR353Module())
                .registerModule(new JavaTimeModule());
    }
}
