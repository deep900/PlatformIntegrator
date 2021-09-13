/**
 * 
 */
package com.pwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.pwc.cell.Application;

import lombok.extern.java.Log;

/**
 * WSO2 API Manager helper class.
 * @author Pradheep
 *
 */
@Log
public class APIManagerConnector {
	
	@Value("{apimanager.baseurl}")
	private String baseUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void registerWithAPIManager(Application application){
		log.info("Register the application with API Manager");
		log.info(application.toString());
	}
	
	
	 
}
