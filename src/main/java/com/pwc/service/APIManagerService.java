package com.pwc.service;
import java.io.File;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.pwc.data.entity.ApplicationEntity;
import com.pwc.security.OAuth2Token;
import com.pwc.utility.ConversionUtility;

import lombok.extern.slf4j.Slf4j;

/**
 * WSO2 API Manager helper class.
 * @author Pradheep
 *
 */
@Slf4j
public class APIManagerService extends UrlConstructor{
	
	@Autowired
	private RestTemplate restTemplate;
	
	Gson gson = new Gson();
	
	String token = "eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbiIsImF1dCI6IkFQUExJQ0FUSU9OX1VTRVIiLCJhdWQiOiJaZng1bUoxOHBQa0g4NWZpZWMwSzM0YnVETzhhIiwibmJmIjoxNjMxODc2NjU0LCJhenAiOiJaZng1bUoxOHBQa0g4NWZpZWMwSzM0YnVETzhhIiwic2NvcGUiOiJkZWZhdWx0IiwiaXNzIjoiaHR0cHM6XC9cL2xvY2FsaG9zdDo5NDQzXC9vYXV0aDJcL3Rva2VuIiwiZXhwIjoxNjMxODgwMjU0LCJpYXQiOjE2MzE4NzY2NTQsImp0aSI6IjQ5MWY4NjJjLWU2MTAtNDk0YS1iZDgwLWI1NGM4Y2M4YjBkNSJ9.N3vfrOiJ9kItL54IkdkrKvskVN7zlTV8eXPHwdY1hilFYmB9ionLHCv5f6C-PDOyTeD57POONRIFuwJGyXbRPV0Uhb1PBer5PDLCHnWks1vZGi80vApmKkQGiDxjhX87O58wDq8tKqqL4OXKQQc4rhjP7QIBhvNrddv1tS73UCqSFgE-w9XFn-_RtYLTB00XXOw0GM0hTEFk76MhCyhtqRrn_17RnLxG9bJtfNy9ax-pH3HdWuChCdcvJmR4hzkSjOVK4inmsoZDaeyvlA64xt4Dsr8hAfRzV88lpRgjtRAvgg_04ExOaHplotHCsTcac_j2CmCMVUkhSIWW1P6RBw";
	
	/**
	 * curl -X POST
	 * "https://gateway.api.cloud.wso2.com/api/am/publisher/apis/import-openapi"
	 * -H "accept: application/json" -H "Content-Type: multipart/form-data" -F
	 * "file=@car-rental.yaml"
	 */
	@Async
	public void publishTOAPIManager(ApplicationEntity application) {
		String URL = getAPIManagerImportUrl();
		log.info("Publishing the API to API Manager :" + URL);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("accept", "application/json");
		headers.add("Authorization", "Bearer "+token);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", getOpenAPIDefinition(application));
		body.add("additionalProperties", "{\"name\": \"CarRentalAPI\", \"context\" : \"/rental\", \"version\" : \"1.0.0\"}");
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(URL, requestEntity, String.class);
		log.info("Printing the response :" + response);
	}
	
	public FileSystemResource getOpenAPIDefinition(ApplicationEntity application) {
		ConversionUtility utility = new ConversionUtility();
		Long length;
		File fileObj = null;
		try {
			length = application.getOpenAPIDefinitionFile().length();
			fileObj = utility.convertBytesToFile(application.getOpenAPIDefinitionFile().getBytes(0, length.intValue()), application.getAppname(), ".yaml");
		} catch (IllegalArgumentException e) {
			log.error("Error while reading file",e);
		} catch (SQLException e) {
			log.error("Error while reading file",e);
		}
		return new FileSystemResource(fileObj);
	}	 
}
