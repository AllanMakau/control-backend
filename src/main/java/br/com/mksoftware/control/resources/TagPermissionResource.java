package br.com.mksoftware.control.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.entities.TagPermission;
import br.com.mksoftware.control.services.TagPermissionService;


@RestController
@RequestMapping(value = "/tagpermission")
public class TagPermissionResource {
	

	@Autowired
	private TagPermissionService tagPermissionService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<TagPermission> tagPermissions = tagPermissionService.getAll();
		return ResponseEntity.ok(tagPermissions);
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveTagPermissio( @RequestBody TagPermission tagPermission){
		TagPermission newTagPermission = tagPermissionService.saveTagPermission(tagPermission);
		return ResponseEntity.ok(newTagPermission);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getTagPermissionById(@PathVariable Long id){
		TagPermission tagPermission = tagPermissionService.getTagPermissionById(id);
		return ResponseEntity.ok(tagPermission);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		tagPermissionService.deleteTagPermissionById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody TagPermission tagPermission){
		TagPermission tagPermissionUpdated = tagPermissionService.updateTagPermission(tagPermission); 
		return ResponseEntity.ok(tagPermissionUpdated);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateTagPermission(@PathVariable Long id, @PathVariable Boolean ativo ){
		TagPermission tagPermissionUpdated = tagPermissionService.activate(id, ativo); 
		return ResponseEntity.ok(tagPermissionUpdated);
	}

}
