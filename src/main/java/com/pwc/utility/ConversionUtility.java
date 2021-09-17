/**
 * 
 */
package com.pwc.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pwc.cell.Cell;
import com.pwc.cell.DefaultCell;
import com.pwc.data.entity.CellEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Pradheep
 *
 */
@Slf4j
public class ConversionUtility {

	public List<Cell> convertCellEntity(Iterable<CellEntity> cellEntityIterable) {
		List<Cell> cellList = new ArrayList<Cell>();
		cellEntityIterable.forEach(cellEntity -> {
			Cell cellObj = new DefaultCell();
			BeanUtils.copyProperties(cellEntity, cellObj);
			cellList.add(cellObj);
		});
		return cellList;
	}
	
	public File convertBytesToFile(byte[] data, String fileName,String fileExtension) throws IllegalArgumentException{
		if(null == data || null == fileName || null == fileExtension) {
			throw new IllegalArgumentException("Invalid arguments passed to convert file.");
		}
		File file = null;
		try {
			file = File.createTempFile(fileName,fileExtension);
		} catch (IOException err) {
			log.error("Unable to create a temp file", err);
		}
		try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
			fileOutputStream.write(data);
			log.info("File written:" + file.getAbsolutePath());
		}catch(Exception err) {
			log.error("Error while creating file",err);			
		}
		return file;
	}
}
