/**
 * 
 */
package com.pwc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.pwc.utility.ConversionUtility;

@Configuration
@EnableJpaRepositories(basePackages = { "com.pwc.data.entity" })
public class ApplicationConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ConversionUtility getConversionUtility() {
		return new ConversionUtility();
	}
}
