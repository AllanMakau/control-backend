package br.com.mksoftware.control.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.entities.Officer;
import br.com.mksoftware.control.services.OfficerService;



@RestController
@RequestMapping(value = "/officer")
public class OfficerResource {
	
	@Autowired
	private OfficerService officerService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<Officer> officers = officerService.getAll();
		return ResponseEntity.ok(officers);
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveOfficer( @RequestBody Officer officer){
		Officer newOfficer = officerService.saveOfficer(officer);
		return ResponseEntity.ok(newOfficer);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getOfficerById(@PathVariable Long id){
		Officer officer = officerService.getOfficerById(id);
		return ResponseEntity.ok(officer);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		officerService.deleteOfficerById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody Officer officer){
		Officer officerUpdated = officerService.updateOfficer(officer); 
		return ResponseEntity.ok(officerUpdated);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateOfficer(@PathVariable Long id, @PathVariable Boolean ativo ){
		Officer officerUpdated = officerService.activate(id, ativo); 
		return ResponseEntity.ok(officerUpdated);
	}

}
