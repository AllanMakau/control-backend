package br.com.mksoftware.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.System;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long>{
	
	List<Function> findFunctionBySystem(System system);

}
