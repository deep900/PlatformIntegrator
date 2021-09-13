/**
 * 
 */
package com.pwc.cell;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Cell is the collection of micro applications that communicate with each other.
 * 
 * @author Pradheep
 *
 */
@ToString
@Getter
@Setter
public abstract class Cell {
	
	public int id;
	
	public abstract List<Application> getApplications();
	
	/**
	 * Port number in which the cell will run.
	 */
	public int port;
	
	/**
	 * Name of the cell
	 */
	public String cellname;	
	
	public String status;

}
