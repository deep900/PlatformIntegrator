package com.pwc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import com.pwc.cell.Application;
import com.pwc.cell.Cell;
import com.pwc.cell.DefaultApplication;
import com.pwc.cell.DefaultCell;
import com.pwc.data.entity.ApplicationEntity;
import com.pwc.data.entity.CellAppMapperEntity;
import com.pwc.data.entity.CellEntity;
import com.pwc.data.repository.ApplicationRepository;
import com.pwc.data.repository.CellEntityRepository;
import com.pwc.data.repository.CellMapperRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Pradheep
 *
 */
@Slf4j
public class PlatformService {

	@Autowired
	private CellEntityRepository cellEntityRepository;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private CellMapperRepository cellMapperRepository;

	@Value("${gateway.workspace}")
	private String workspace;

	@Value("${gateway.basepath}")
	private String gatewayBasepath;

	public boolean createCell(DefaultCell cellObj) {
		try {
			CellEntity entity = new CellEntity();
			BeanUtils.copyProperties(cellObj, entity);
			cellEntityRepository.save(entity);
		} catch (Exception err) {
			log.error("Error while creating cell", err);
			return false;
		}
		return true;
	}

	public boolean createApplication(DefaultApplication applicationObj) {
		try {
			ApplicationEntity entity = new ApplicationEntity();
			BeanUtils.copyProperties(applicationObj, entity);
			entity.setOpenAPIDefinitionFile(new SerialBlob(applicationObj.getOpenAPIDefinitionFile()));
			this.applicationRepository.save(entity);
		} catch (Exception err) {
			log.error("Error while creating an application", err);
			return false;
		}
		return true;
	}

	public boolean associateApplicationToCell(Application applicationObj, Cell cellObj) {
		try {
			CellAppMapperEntity cellAppMapperEntity = new CellAppMapperEntity();
			cellAppMapperEntity.setAppId(applicationObj.getId());
			cellAppMapperEntity.setCellId(cellObj.getId());
			this.cellMapperRepository.save(cellAppMapperEntity);
		} catch (Exception err) {
			log.error("Error in associating the application with cell", err);
		}
		return true;
	}

	public Optional<Iterable<CellEntity>> getAllCells() {
		return Optional.of(cellEntityRepository.findAll());
	}

	public Optional<Iterable<ApplicationEntity>> getAllApplications() {
		return Optional.of(applicationRepository.findAll());
	}

	@Async
	public void createMicroGateway(DefaultCell defaultCell) throws IllegalArgumentException {
		log.info("Printing the user directory :" + System.getProperty("user.dir"));
		log.info("Creating a micro gateway");
		if (defaultCell.getCellname() == null || defaultCell.getCellname().isEmpty()) {
			throw new IllegalArgumentException("Cell name should be valid");
		}
		String command = new String("cmd /c micro-gw init " + defaultCell.getCellname());
		executeSystemCommand(command);
	}

	public void executeSystemCommand(String command) {
		log.info("Executing command " + command);
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			log.error("Error while creating the gateway", e);
		}
		log.info(convertInputStreamToString(process.getInputStream(), process));
	}

	public String convertInputStreamToString(InputStream inputStream, Process process) {
		if (null == inputStream) {
			return "Inputstream was null, unknown response";
		}
		String convertedString = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
				.collect(Collectors.joining("\n"));
		// process.destroyForcibly();
		log.info("-------Exit status-----" + process.exitValue() + "---------------------");
		return convertedString;
	}

	@Async
	public void buildMicroGateway(String cellName) {
		if (cellName == null || cellName.isEmpty()) {
			throw new IllegalArgumentException("Cell name should be valid");
		}
		String command = "cmd /c micro-gw build " + cellName;
		executeSystemCommand(command);
	}

	@Async
	public void addApplicationToGateway(DefaultApplication application, String cellName)
			throws IllegalArgumentException {
		log.info("Trying to add application to gateway");
		if (cellName == null || cellName.isEmpty()) {
			throw new IllegalArgumentException("Cell name should be valid");
		}
		if (null == application.getOpenAPIDefinitionUrl()) {
			throw new IllegalArgumentException("Invalid API specification - Not a valid data");
		}
		String command = "cmd /c micro-gw import " + cellName + " -a " + application.getOpenAPIDefinitionUrl();
		log.info("Printing the command " + command);
		executeSystemCommand(command);
	}

	@Async
	public void addApplicationAPISpecificationToGateway(ApplicationEntity applicationObj1, String cellName)
			throws IllegalArgumentException {		
		log.info("Trying to add the Open API specification: ");
		Path path = FileSystems.getDefault().getPath(gatewayBasepath + "/" + cellName, "api_definitions",
				applicationObj1.getAppname() + ".yaml");
		log.info("Printing the size:" + applicationObj1.getOpenAPIDefinitionFile());
		try {
			log.info("printing file name:", path.getFileName());
			Long length;
			length = applicationObj1.getOpenAPIDefinitionFile().length();
			Files.write(path, applicationObj1.getOpenAPIDefinitionFile().getBytes(0l, length.intValue()),
					StandardOpenOption.CREATE_NEW);
		} catch (IOException err) {
			log.info("Error while copying the API specification file.", err);
		} catch (SQLException err) {
			log.error("SQL exception ", err);
		} finally {
			log.info("Completed writing file ");
		}
	}

	public Optional<ApplicationEntity> getApplication(int applicationId) {
		log.info("Printing the application id:" + applicationId);
		return this.applicationRepository.findById(applicationId);
	}
}
