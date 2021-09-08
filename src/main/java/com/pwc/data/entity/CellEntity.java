/**
 * 
 */
package com.pwc.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pradheep
 *
 */
@Entity
@Table(name = "cell")
@Getter
@Setter
public class CellEntity {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name="port")
	public int port;
	
	/**
	 * Describes the running status of a cell.
	 */
	@Column(name="status")
	public String status;
	
}
