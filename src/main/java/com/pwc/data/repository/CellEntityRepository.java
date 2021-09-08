/**
 * 
 */
package com.pwc.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pwc.data.entity.CellEntity;

/**
 * @author Pradheep
 *
 */
@Repository
public interface CellEntityRepository extends CrudRepository<CellEntity, Integer> {
	
}
