package com.sg.kata.bank.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Swagger API documentation Configuration 
 * @author <a href="mailto:jean.jacques.x.etune.ngi.-nd@disney.com">Jean-Jacques ETUNE NGI (Java EE Technical Lead / Enterprise Architect)</a>
 * @since Mon, 2021-01-11 - 02:24:07
 */
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter(swaggerObjectMapper()));
        converters.add(new ByteArrayHttpMessageConverter());
    }
	
	/**
	 * Method used to define object mapper provider
	 * @return	Object Mapper Provider
	 */
    @Bean
    @Primary
    public ObjectMapper swaggerObjectMapper() {
    	
    	// Build and return provider
        return new ObjectMapper()
                .setDefaultPropertyInclusion(Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JSR353Module());
    }
    
	/**
	 * Method used to define forwarded strategy bean
	 * @return	forwarded strategy bean
	 */
	@Bean
	public ForwardedHeaderFilter forwardedHeaderFilter() {
		
		// Return the filter instance
		return new ForwardedHeaderFilter();
	}
	
	/**
	 * Method used to define OpenAPI Bean
	 * @return	OpenAPI Bean
	 */
	@Bean
	public OpenAPI digitalKeyOpenAPI() {
		
		// Build OpenAPI
		return new OpenAPI()
				.info(info())
				.openapi("3.0.0");
	}
	
	/**
	 * Method used to build OpenAPI Info
	 * @return	OpenAPI Info
	 */
	private Info info() {
		
		// Build and return info
		return new Info()
				.title("KATA SG BANK ACCOUNT API")
				.description("Bank Account API Simple Design and implementation")
				.license(new License()
						.url("")
						.name("SG all rigths reserved")
				)
				.contact(new Contact()
						.email("jeanjacques.etunengi@adservio.fr")
						.name(String.format("%s %s", "ETUNE NGI", "Jean-Jacques"))
						.url("mailto:jeanjacques.etunengi@adservio.fr")
				);
	}
}
