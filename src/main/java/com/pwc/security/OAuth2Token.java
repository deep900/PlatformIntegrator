/**
 * 
 */
package com.pwc.security;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pradheep
 *
 */
@Setter
@Getter
public class OAuth2Token {
	public String access_token;
	public String refresh_token;
	public String scope;
	public String token_type;
	public Integer expires_in;
}
