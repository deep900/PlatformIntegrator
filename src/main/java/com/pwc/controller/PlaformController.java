/**
 * 
 */
package com.pwc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.cell.Application;
import com.pwc.cell.Cell;
import com.pwc.data.entity.CellEntity;
import com.pwc.service.PlatformService;
import com.pwc.utility.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Pradheep
 *
 */
@RestController
@RequestMapping("/platform")
@Slf4j
public class PlaformController extends BaseController {

	@Autowired
	private PlatformService platformService;

	@GetMapping("/getCells")
	public @ResponseBody ResponseMessage getCells() {
		Optional<Iterable<CellEntity>> cellInfo = platformService.getAllCells();
		if (cellInfo.isPresent()) {
			List<Cell> cellList = conversionUtility.convertCellEntity(cellInfo.get());
			log.info("Found " + cellList.size() + "Records");
			log.debug(cellList.toString());
			return getSucessMessage(cellList);
		} else {
			log.info("No cell information available");
			return getSucessMessage();
		}
	}

	@PostMapping("/createCell")
	public @ResponseBody ResponseMessage createCell(@RequestBody Cell cellDetails) {
		log.info("Creating a cell:" + cellDetails.toString());
		boolean flag = false;
		try {
			flag = platformService.createCell(cellDetails);
		} catch (Exception err) {
			log.error("Error while creating cell", err);
			return getFailureMessage(err.getMessage());
		}
		if (flag) {
			log.info("Created the cell successfully");
			return getSucessMessage();
		} else {
			log.info("Unable to create the cell");
			return getFailueMessage();
		}
	}

	@PostMapping("/createApplication")
	public @ResponseBody ResponseMessage createApplication(@RequestBody Application applicationObj) {
		log.info("Creating a new application " + applicationObj.toString());
		boolean flag = false;
		try {
			flag = platformService.createApplication(applicationObj);
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
