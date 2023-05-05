package br.com.mksoftware.control.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.dtos.resquest.DepartmentRequest;
import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.parse.DepartmentParse;
import br.com.mksoftware.control.services.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags =  "Departamento")
@RestController
@RequestMapping(value = "/departament")
public class DepartmentResource {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmentParse departmentParse;
	
	
	
	@ApiOperation("Listar departamentos")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<Department> departments = departmentService.getAll();
		return ResponseEntity.ok(departmentParse.toCollectionModel(departments));
	}
	
	@ApiOperation("Cadastrar um departamento")
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveDepartment( @RequestBody @Valid DepartmentRequest departmentRequest){
		
		Department newDepartment = departmentParse.toDomainObject(departmentRequest);
		newDepartment = departmentService.saveDepartment(newDepartment);
		return ResponseEntity.ok(departmentParse.toModelResponse(newDepartment));
	}
	
	@ApiOperation("Buscar um departamentos")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getDepartmentById(@PathVariable Long id){
		Department department = departmentService.getDepartmentById(id);
		return ResponseEntity.ok(departmentParse.toModelResponse(department));
	}
	
	@ApiOperation("Deletar departamentos")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		departmentService.deleteDepartmentById(id); 
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation("Atualizar departamentos")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid DepartmentRequest departmentRequest){
		
		Department department = departmentService.getDepartmentById(id);
		departmentParse.toDomainInputDepartment(departmentRequest, department);
		department = departmentService.updateDepartment(department);
		return ResponseEntity.ok(departmentParse.toModelResponse(department));
	}
	
	@ApiOperation("Ativar departamentos")
	@RequestMapping(value = "/{id}/activate", method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@PathVariable Long id ){
		Department departmentUpdated = departmentService.activate(id); 
		return ResponseEntity.ok(departmentParse.toModelResponse(departmentUpdated));
	}
	
	@ApiOperation("Inativar departamentos")
	@RequestMapping(value = "/{id}/inactivate", method = RequestMethod.PUT)
	public ResponseEntity<?> inactivateUser(@PathVariable Long id ){
		Department departmentUpdated = departmentService.inactivate(id); 
		return ResponseEntity.ok(departmentParse.toModelResponse(departmentUpdated));
	}

}
