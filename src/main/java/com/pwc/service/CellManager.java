/**
 * 
 */
package com.pwc.service;

import org.springframework.beans.factory.InitializingBean;

import lombok.extern.java.Log;

/**
 * Manages the state of the cells. Runs / Halts and terminates the cells.
 * 
 * @author Pradheep
 *
 */
@Log
public class CellManager implements InitializingBean {

	/**
	 * Retore the all cells in case of server restart.
	 * 
	 * @return
	 */
	public boolean restoreAllCells() {
		log.info("Trying to restore all cells");
		// TODO
		return true;
	}

	public boolean haltCell(int cellId) {
		// TODO
		return false;
	}

	public boolean startCell(int cellId) {
		// TODO
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		restoreAllCells();
	}
}
