/**
 * 
 */
package com.pwc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.pwc.cell.DefaultApplication;
import com.pwc.service.PlatformService;
import com.pwc.utility.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Pradheep
 *
 */
@Controller
@RequestMapping("/file")
@Slf4j
public class FileUploadController extends BaseController {

	@Autowired
	private PlatformService platformService;	
	
	Gson gson = new Gson();

	@PostMapping(path = "/createApplication", consumes = { "multipart/form-data" })
	public @ResponseBody ResponseMessage createApplication(@RequestParam(name = "file") MultipartFile file,
			@RequestParam(name = "Application") String applicationObj) {
		log.info("Creating a new application " + applicationObj.toString());
		DefaultApplication application = gson.fromJson(applicationObj.toString(), DefaultApplication.class);
		try {
			log.info("Available bytes to read: " + file.getSize() + ", Original file name:"
					+ file.getOriginalFilename());			
			application.setOpenAPIDefinitionFile(file.getBytes());
		} catch (Exception err) {
			log.error("Error while reading the file", err);
		}
		boolean flag = false;
		try {
			flag = platformService.createApplication(application);
		} catch (Exception err) {
			log.error("Error while creating application", err);
			return getFailureMessage(err.getMessage());
		}
		if (flag) {
			log.info("Created the application successfully");
			return getSucessMessage();
		} else {
			log.info("Unable to create the application");
			return getFailueMessage();
		}
	}
}
