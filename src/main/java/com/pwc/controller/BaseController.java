/**
 * 
 */
package com.pwc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pwc.utility.ConversionUtility;
import com.pwc.utility.ResponseMessage;

/**
 * @author Pradheep
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BaseController {
	
	@Autowired
	public ConversionUtility conversionUtility;
	
	public ResponseMessage getSucessMessage(){
		ResponseMessage rMsg = new ResponseMessage();
		rMsg.setResponseCode(ApplicationConstants.SUCCESS);
		rMsg.setResponseMessage(ApplicationConstants.SUCCESS_MSG);
		return rMsg;
	}
	
	public ResponseMessage getFailueMessage(){
		ResponseMessage rMsg = new ResponseMessage();
		rMsg.setResponseCode(ApplicationConstants.ERROR);
		rMsg.setResponseMessage(ApplicationConstants.FAILURE_MSG);
		return rMsg;
	}
	
	public ResponseMessage getFailureMessage(String errorMessage) {
		ResponseMessage rMsg = getFailueMessage();
		rMsg.setErrorMessage(errorMessage);
		return rMsg;
	}
	
	public ResponseMessage getSucessMessage(Object payload){
		ResponseMessage rMsg = new ResponseMessage();
		rMsg.setResponseCode(ApplicationConstants.SUCCESS);
		rMsg.setResponseMessage(ApplicationConstants.SUCCESS_MSG);
		rMsg.setBody(payload);
		return rMsg;
	}
}
