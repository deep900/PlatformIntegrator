/**
 * 
 */
package com.pwc.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pwc.data.entity.CellAppMapperEntity;

/**
 * @author Pradheep
 *
 */
@Repository
public interface CellMapperRepository extends CrudRepository<CellAppMapperEntity, Integer>{

}
