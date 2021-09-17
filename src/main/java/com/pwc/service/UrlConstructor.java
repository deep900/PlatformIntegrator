package com.pwc.service;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Pradheep
 *
 */
public class UrlConstructor {
	
	@Value("${apimanager.baseurl}")
	private String apiManagerBaseUrl;
	
	@Value("${import.api.path}")
	private String apiManagerImportUrl;
	
	@Value("${oauth2.token.url}")
	private String oauth2TokenUrl;
	
	public String getAPIManagerImportUrl(){
		return apiManagerBaseUrl + apiManagerImportUrl;
	}	
	
	public String getOAuth2TokenUrl(){
		return apiManagerBaseUrl + oauth2TokenUrl;
	}
}
