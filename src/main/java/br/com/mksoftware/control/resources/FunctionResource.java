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

import br.com.mksoftware.control.dtos.resquest.FunctionRequest;
import br.com.mksoftware.control.dtos.resquest.TagPermissionRequest;
import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.parse.FunctionParse;
import br.com.mksoftware.control.parse.TagPermissionParse;
import br.com.mksoftware.control.services.FunctionService;

@RestController
@RequestMapping(value = "/function")
public class FunctionResource {
	

	@Autowired
	private FunctionService functionService;
	
	
	@Autowired
	private FunctionParse functionParse;
	
	@Autowired
	private TagPermissionParse tagPermissionParse;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<Function> functions = functionService.getAll();
		return ResponseEntity.ok(functionParse.toCollectionModel(functions));
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = {"*/*"} )
	public ResponseEntity<?> saveFunction( @RequestBody @Valid FunctionRequest functionRequest){
		Function function = functionParse.toDomainObject(functionRequest);
		function = functionService.saveFunction(function);
		return ResponseEntity.ok(functionParse.toModelResponse(function));
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getFunctionById(@PathVariable Long id){
		Function department = functionService.getFunctionById(id);
		return ResponseEntity.ok(functionParse.toModelResponse(department));
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		functionService.deleteFunctionById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid FunctionRequest functionRequest){
		
		Function function = functionService.getFunctionById(id);
		functionParse.toDomainInputOfficer(functionRequest, function);
		function = functionService.updateFunction(function);
		return ResponseEntity.ok(functionParse.toModelResponse(function));
	}
	
	
	@RequestMapping(value = "/{id}/activate", method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@PathVariable Long id ){
		Function function = functionService.activate(id); 
		return ResponseEntity.ok(functionParse.toModelResponse(function));
	}
	
	@RequestMapping(value = "/{id}/inactivate", method = RequestMethod.PUT)
	public ResponseEntity<?> inactivateUser(@PathVariable Long id ){
		Function function = functionService.inactivate(id); 
		return ResponseEntity.ok(functionParse.toModelResponse(function));
	}
	
	@RequestMapping(value = "/{id}/add-tag", method = RequestMethod.PUT)
	public ResponseEntity<?> addTagPermission(@PathVariable Long id, @RequestBody @Valid TagPermissionRequest tagPermissionRequest ){
		var tag = tagPermissionParse.toDomainObject(tagPermissionRequest);
		Function function = functionService.addTags(id, tag); 
		return ResponseEntity.ok(functionParse.toModelResponse(function));
	}

	@RequestMapping(value = "/{id}/remove-tag", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> removeTagPermission(@PathVariable Long id, @RequestBody @Valid TagPermissionRequest tagPermissionRequest){
		var tag = tagPermissionParse.toDomainObject(tagPermissionRequest);
		Function function = functionService.removeTags(id, tag); 
		return ResponseEntity.ok(functionParse.toModelResponse(function));
	}

 
}
