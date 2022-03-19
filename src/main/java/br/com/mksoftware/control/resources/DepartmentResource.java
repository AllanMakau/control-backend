package br.com.mksoftware.control.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.services.DepartmentService;


@RestController
@RequestMapping(value = "/department")
public class DepartmentResource {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<Department> departments = departmentService.getAll();
		return ResponseEntity.ok(departments);
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveDepartment( @RequestBody Department department){
		Department newDepartment = departmentService.saveDepartment(department);
		return ResponseEntity.ok(newDepartment);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
		Department system = departmentService.getDepartmentById(id);
		return ResponseEntity.ok(system);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		departmentService.deleteDepartmentById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody Department department){
		Department departmentUpdated = departmentService.updateDepartment(department); 
		return ResponseEntity.ok(departmentUpdated);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateDepartment(@PathVariable Long id, @PathVariable Boolean ativo ){
		Department departmentUpdated = departmentService.activate(id, ativo); 
		return ResponseEntity.ok(departmentUpdated);
	}

}
