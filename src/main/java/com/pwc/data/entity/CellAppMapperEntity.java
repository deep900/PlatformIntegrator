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

/**
 * @author Pradheep
 *
 */
@Entity
@Table(name = "cell_app_mapper")
@Getter
@Setter
public class CellAppMapperEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cell_id")
	private int cellId;
	
	@Column(name="app_id")
	private int appId;

}
