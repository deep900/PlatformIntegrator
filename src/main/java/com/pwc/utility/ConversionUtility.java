/**
 * 
 */
package com.pwc.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.pwc.cell.Cell;
import com.pwc.cell.DefaultCell;
import com.pwc.data.entity.CellEntity;

/**
 * @author Pradheep
 *
 */
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
}
