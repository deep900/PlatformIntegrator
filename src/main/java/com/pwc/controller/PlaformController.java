/**
 * 
 */
package com.pwc.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.cell.Cell;
import com.pwc.cell.CellAppMapper;
import com.pwc.cell.DefaultApplication;
import com.pwc.cell.DefaultCell;
import com.pwc.data.entity.ApplicationEntity;
import com.pwc.data.entity.CellEntity;
import com.pwc.service.APIManagerService;
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
	
	@Autowired
	private APIManagerService apiManagerService;

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

	@GetMapping("/getApplications")
	public @ResponseBody ResponseMessage getApplications() {
		Optional<Iterable<ApplicationEntity>> applicationInfo = platformService.getAllApplications();
		if (applicationInfo.isPresent()) {
			return getSucessMessage(applicationInfo.get());
		} else {
			log.info("No cell information available");
			return getSucessMessage();
		}
	}

	@PostMapping("/createCell")
	public @ResponseBody ResponseMessage createCell(@RequestBody DefaultCell cellDetails) {
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
			postCellCreationProcess(cellDetails);
			return getSucessMessage();
		} else {
			log.info("Unable to create the cell");
			return getFailueMessage();
		}
	}

	@PostMapping("/associateApp")
	public @ResponseBody ResponseMessage associateApplicationWithCell(@RequestBody CellAppMapper mapper) {
		log.info("Associating cell with applications" + mapper.toString());
		postCellAppAssociationProcess(mapper.getAppinfo(), mapper.getCellinfo());
		return getSucessMessage();
	}

	private void postCellCreationProcess(DefaultCell cellInfo) {
		// Step 1 Create a new micro gateway.
		platformService.createMicroGateway(cellInfo);
	}

	private void postCellAppAssociationProcess(DefaultApplication application, DefaultCell cellInfo) {
		// Add the application to the micro gateway.	
		Optional<ApplicationEntity> appObj = platformService.getApplication(application.getId());
		ApplicationEntity applicationObj1 = appObj.get();
		platformService.addApplicationAPISpecificationToGateway(applicationObj1, cellInfo.getCellname());
		try {
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			log.error("Interpt error",e);			
		}
		apiManagerService.publishTOAPIManager(applicationObj1);
	}
	
	@GetMapping("/buildCell")
	public @ResponseBody ResponseMessage buildCell(HttpServletRequest servletRequest){
		String param = servletRequest.getParameter("cellName");
		platformService.buildMicroGateway(param);
		return getSucessMessage();
	}

}
