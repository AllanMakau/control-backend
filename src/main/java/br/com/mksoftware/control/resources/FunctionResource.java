package br.com.mksoftware.control.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.TagPermission;
import br.com.mksoftware.control.services.FunctionService;

@RestController
@RequestMapping(value = "/function")
public class FunctionResource {
	

	@Autowired
	private FunctionService functionService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<Function> functions = functionService.getAll();
		return ResponseEntity.ok(functions);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = {"*/*"} )
	public ResponseEntity<?> saveFunction( @RequestBody Function function){
		Function newFunction = functionService.saveFunction(function);
		return ResponseEntity.ok(newFunction);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getFunctionById(@PathVariable Long id){
		Function function = functionService.getFunctionById(id);
		return ResponseEntity.ok(function);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		functionService.deleteFunctionById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody Function function){
		Function functionUpdated = functionService.updateFunction(function); 
		return ResponseEntity.ok(functionUpdated);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateFunction(@PathVariable Long id, @PathVariable Boolean ativo ){
		Function functionUpdated = functionService.activate(id, ativo); 
		return ResponseEntity.ok(functionUpdated);
	}
	
	@RequestMapping(value = "/{id}/add-tag/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addTagPermission(@PathVariable Long id, @RequestBody TagPermission tag ){
		Function functionUpdated = functionService.addTags(id, tag); 
		return ResponseEntity.ok(functionUpdated);
	}

	@RequestMapping(value = "/{id}/remove-tag/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> removeTagPermission(@PathVariable Long id, @RequestBody TagPermission tag ){
		Function functionUpdated = functionService.removeTags(id, tag); 
		return ResponseEntity.ok(functionUpdated);
	}


}
