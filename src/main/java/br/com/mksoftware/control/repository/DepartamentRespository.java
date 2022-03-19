package br.com.mksoftware.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mksoftware.control.entities.Department;

@Repository
public interface DepartamentRespository extends JpaRepository<Department, Long> {

}
