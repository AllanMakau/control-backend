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

import br.com.mksoftware.control.dtos.resquest.TagPermissionRequest;
import br.com.mksoftware.control.entities.TagPermission;
import br.com.mksoftware.control.parse.TagPermissionParse;
import br.com.mksoftware.control.services.TagPermissionService;


@RestController
@RequestMapping(value = "/tagpermission")
public class TagPermissionResource {
	

	@Autowired
	private TagPermissionService tagPermissionService;
	
	@Autowired
	private TagPermissionParse tagPermissionParse;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<TagPermission> tagPermissions = tagPermissionService.getAll();
		return ResponseEntity.ok(tagPermissionParse.toCollectionModel(tagPermissions));
	}
	
	//@PreAuthorize("hasAuthority('ATUAUSER 1')")
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveTagPermissio( @RequestBody @Valid TagPermissionRequest tagPermissionRequest){
		TagPermission tagPermission = tagPermissionParse.toDomainObject(tagPermissionRequest);
		tagPermission = tagPermissionService.saveTagPermission(tagPermission);
		return ResponseEntity.ok(tagPermissionParse.toModelResponse(tagPermission));
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getTagPermissionById(@PathVariable Long id){
		TagPermission tagPermission = tagPermissionService.getTagPermissionById(id);
		return ResponseEntity.ok(tagPermissionParse.toModelResponse(tagPermission));
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		tagPermissionService.deleteTagPermissionById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid TagPermissionRequest tagPermissionRequest){
		
		TagPermission tagPermission = tagPermissionService.getTagPermissionById(id);
		tagPermissionParse.toDomainInputTagPermission(tagPermissionRequest,tagPermission);
		tagPermission = tagPermissionService.updateTagPermission(tagPermission); 
		return ResponseEntity.ok(tagPermissionParse.toModelResponse(tagPermission));
	}
	
	
	@RequestMapping(value = "/{id}/activate", method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@PathVariable Long id ){
		TagPermission tagPermissionUpdated = tagPermissionService.activate(id); 
		return ResponseEntity.ok(tagPermissionParse.toModelResponse(tagPermissionUpdated));
	}
	
	@RequestMapping(value = "/{id}/inactivate", method = RequestMethod.PUT)
	public ResponseEntity<?> inactivateUser(@PathVariable Long id ){
		TagPermission tagPermissionUpdated = tagPermissionService.inactivate(id); 
		return ResponseEntity.ok(tagPermissionParse.toModelResponse(tagPermissionUpdated));
	}

}
