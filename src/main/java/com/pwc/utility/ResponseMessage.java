/**
 * 
 */
package com.pwc.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pradheep
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage {
	
	public String responseMessage;
	
	public int responseCode;
	
	public String errorMessage;
	
	public Object body;
	
}
