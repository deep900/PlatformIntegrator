/**
 * 
 */
package com.pwc.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Pradheep
 *
 */
@Entity
@Table(name = "cell")
@Getter
@Setter
@ToString
public class CellEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	public String cellname;
	
	@Column(name="port")
	public int port;
	
	/**
	 * Describes the running status of a cell.
	 */
	@Column(name="status")
	public String status;
	
}
