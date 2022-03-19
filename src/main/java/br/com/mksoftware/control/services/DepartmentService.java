package br.com.mksoftware.control.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.repository.DepartamentRespository;

@Service
public class DepartmentService {
	
	
	@Autowired
	private DepartamentRespository departmentRepository;
	

	public List<Department> getAll() {
		var departments = departmentRepository.findAll();
		return departments;
	}

	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).get();
	}
	
	public Department saveDepartment(Department officer) {
		return departmentRepository.save(officer);
	}

	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);
	}

	public Department updateDepartment(Department Department) {
		return departmentRepository.save(Department);
	}

	public Department activate(Long id, Boolean ativo) {
		Department DepartmentOld = departmentRepository.findById(id).get();
		DepartmentOld.setIsActive(ativo);
		Department DepartmentUpdated = departmentRepository.save(DepartmentOld);
		return DepartmentUpdated;
	}

}
