/**
 * 
 */
package com.pwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pwc.security.OAuth2Token;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Pradheep
 *
 */
@Slf4j
public class OAuth2Manager {
	
	@Value("${apimanager.baseurl}")
	public String apiManagerBaseUrl;
	
	@Value("${oauth2.token.url}")
	public String oauthUrl;
	
	@Value("${oauth2.token.param}")
	public String oauth2Param;
	
	@Value("${admin.oath2.bearer.token}")
	public String oauth2BearerToken;
	
	public String getOauthTokenUrl(){
		return apiManagerBaseUrl + oauthUrl + "?" + oauth2Param;
	}
	
	public HttpHeaders getOauth2Headers(){
	HttpHeaders headers = new HttpHeaders();	
	headers.add("Content-Type", "application/json");
	headers.add("Authorization", "Bearer "+oauth2BearerToken);
	return headers;
	}
	
	@Autowired
	private RestTemplate restTemplate;
	/*public String accessTkn(String code) {
		try {
			SSLContext sslContext = null;
			try {
				sslContext = SSLContext.getInstance("SSL");
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// set up a TrustManager that trusts everything
			try {
				sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				            public X509Certificate[] getAcceptedIssuers() {
				                    System.out.println("getAcceptedIssuers =============");
				                    return null;
				            }

				            public void checkClientTrusted(X509Certificate[] certs,
				                            String authType) {
				                    System.out.println("checkClientTrusted =============");
				            }

				            public void checkServerTrusted(X509Certificate[] certs,
				                            String authType) {
				                    System.out.println("checkServerTrusted =============");
				            }
				} }, new SecureRandom());
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SSLSocketFactory sf = new SSLSocketFactory(sslContext);
			Scheme httpsScheme = new Scheme("https", 443, sf);
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(httpsScheme);

			// apache HttpClient version >4.2 should use BasicClientConnectionManager
			ClientConnectionManager cm = new SingleClientConnManager(schemeRegistry);
			HttpClient httpClient = new DefaultHttpClient(cm);
			HttpPost httppost = new HttpPost("https://localhost:9443/oauth2/token");

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("client_id", "Zfx5mJ18pPkH85fiec0K34buDO8a"));
			params.add(new BasicNameValuePair("client_secret", "fRtuzWZO0M1XClZlaKZgtiwZZ5wa"));
			params.add(new BasicNameValuePair("grant_type", "client_credentials"));
			httppost.addHeader("Authorization",
					"Basic WmZ4NW1KMThwUGtIODVmaWVjMEszNGJ1RE84YTpmUnR1eldaTzBNMVhDbFpsYUtaZ3Rpd1paNXdh");

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			// Execute and get the response.
			RestTemplate template = new RestTemplate();
			
			HttpResponse response = httpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			System.out.println("entity " + entity.getContent());

			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					String result = new BufferedReader(new InputStreamReader(instream)).lines()
							.collect(Collectors.joining("\n"));
					System.out.println(result);
					// do something useful
				} finally {
					instream.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "token";

	}*/
	
	public OAuth2Token getToken(){
		HttpHeaders headers = getOauth2Headers();
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(getOauthTokenUrl(), HttpMethod.POST, requestEntity, String.class);
		log.info(response.toString());
		return null;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		OAuth2Manager manager = new OAuth2Manager();
		OAuth2Token token = manager.getToken();
	}

}
