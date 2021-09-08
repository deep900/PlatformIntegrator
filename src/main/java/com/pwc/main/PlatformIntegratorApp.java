/**
 * 
 */
package com.pwc.main;

import java.util.Collections;

import org.springframework.boot.SpringApplication;

import lombok.extern.java.Log;

/**
 * @author Pradheep
 *
 */
@Log
public class PlatformIntegratorApp {

	public static int port = 8090;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("Starting the Platform Integrator at port[" + port +"]");
		SpringApplication application = new SpringApplication(PlatformIntegratorApp.class);
		application.setDefaultProperties(Collections.singletonMap("server.port", (Object) port));
		application.run(args);
	}
}
