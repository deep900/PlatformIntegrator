/**
 * 
 */
package com.pwc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.pwc.service.PlatformService;
import com.pwc.utility.ConversionUtility;

@Configuration
@EntityScan(basePackages={"com.pwc.data.entity"})
@ComponentScan(basePackages={"com.pwc.controller"})
public class ApplicationConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ConversionUtility getConversionUtility() {
		return new ConversionUtility();
	}
	
	@Bean
	public PlatformService getPlatformService(){
		return new PlatformService();
	}
}
