/**
 * 
 */
package com.pwc.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.pwc.service.APIManagerService;
import com.pwc.service.PlatformService;
import com.pwc.utility.ConversionUtility;

@Configuration
@EntityScan(basePackages = { "com.pwc.data.entity" })
@ComponentScan(basePackages = { "com.pwc.controller" })
public class ApplicationConfig {

	/*@Bean
	public RestTemplate getRestTemplate() {
		/*
		 * CloseableHttpClient httpClient =
		 * HttpClients.custom().setSSLHostnameVerifier(new
		 * NoopHostnameVerifier()) .build();
		 * HttpComponentsClientHttpRequestFactory requestFactory = new
		 * HttpComponentsClientHttpRequestFactory();
		 * requestFactory.setHttpClient(httpClient);
		 */
		/*return new RestTemplate();
	}*/

	@Bean
	public ConversionUtility getConversionUtility() {
		return new ConversionUtility();
	}

	@Bean
	public PlatformService getPlatformService() {
		return new PlatformService();
	}

	@Bean
	public APIManagerService getAPIManagerService() {
		return new APIManagerService();
	}

	@Bean
	public RestTemplate getestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}
}
