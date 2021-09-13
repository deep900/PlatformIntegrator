/**
 * 
 */
package com.pwc.main;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.pwc.config.ApplicationConfig;

import lombok.extern.java.Log;

/**
 * @author Pradheep
 *
 */
@EnableAsync
@SpringBootApplication
@Import(ApplicationConfig.class)
@EnableJpaRepositories(basePackages = { "com.pwc.data.repository" })
@Log
public class PlatformIntegratorApp {

	public static int port = 8081;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Starting the Platform Integrator at port[" + port +"]");
		SpringApplication application = new SpringApplication(PlatformIntegratorApp.class);
		application.setDefaultProperties(Collections.singletonMap("server.port", (Object) port));
		application.run(args);
		//new SpringApplication(PlatformIntegratorApp.class).run(args);
	}
}
