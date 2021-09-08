/**
 * 
 */
package com.pwc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pwc.cell.Application;
import com.pwc.cell.Cell;
import com.pwc.data.entity.CellEntity;
import com.pwc.data.repository.ApplicationRepository;
import com.pwc.data.repository.CellEntityRepository;

/**
 * @author Pradheep
 *
 */
public class PlatformService {
	
	@Autowired
	private CellEntityRepository cellEntityRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public boolean createCell(Cell cellObj){
		return true;
	}
	
	public boolean createApplication(Application applicationObj) {	
		return true;
	}
	
	public boolean associateApplicationToCell(Application applicationObj,Cell cellObj) {
		return false;
	}
	
	public Optional<Iterable<CellEntity>> getAllCells(){
		return Optional.of(cellEntityRepository.findAll());		
	}
	
	public boolean publishAPI(Application applicationObj) {		
		return false; 		
	}	
} 

