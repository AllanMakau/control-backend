package br.com.mksoftware.control.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.services.SystemService;

@RestController
@RequestMapping(value = "/system")
public class SystemResource {
	
	
	@Autowired
	private SystemService systemService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<System> systems = systemService.getAll();
		return ResponseEntity.ok(systems);
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveSystem( @RequestBody System system){
		System newSystem = systemService.saveSystem(system);
		return ResponseEntity.ok(newSystem);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getSystemById(@PathVariable Long id){
		System system = systemService.getSystemById(id);
		return ResponseEntity.ok(system);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		systemService.deleteSystemById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody System system){
		System systemUpdated = systemService.updateSystem(system); 
		return ResponseEntity.ok(systemUpdated);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateSystem(@PathVariable Long id, @PathVariable Boolean ativo ){
		System systemUpdated = systemService.activate(id, ativo); 
		return ResponseEntity.ok(systemUpdated);
	}

}
