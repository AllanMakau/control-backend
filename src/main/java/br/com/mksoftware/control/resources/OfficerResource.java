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

import br.com.mksoftware.control.dtos.resquest.OfficerRequest;
import br.com.mksoftware.control.entities.Officer;
import br.com.mksoftware.control.parse.OfficerParse;
import br.com.mksoftware.control.services.OfficerService;



@RestController
@RequestMapping(value = "/officer")
public class OfficerResource {
	
	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private OfficerParse officerParse;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<Officer> officers = officerService.getAll();
		return ResponseEntity.ok(officerParse.toCollectionModel(officers));
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveOfficer( @RequestBody @Valid OfficerRequest officerRequest){
		Officer officer = officerParse.toDomainObject(officerRequest);
		officer = officerService.saveOfficer(officer);
		return ResponseEntity.ok(officerParse.toModelResponse(officer));
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getOfficerById(@PathVariable Long id){
		Officer officer = officerService.getOfficerById(id);
		return ResponseEntity.ok(officerParse.toModelResponse(officer));
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		officerService.deleteOfficerById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody @Valid OfficerRequest officerRequest){
		Officer officer = officerParse.toDomainObject(officerRequest);
		officer = officerService.updateOfficer(officer);
		return ResponseEntity.ok(officerParse.toModelResponse(officer));
	}
	
	
	@RequestMapping(value = "/{id}/activate", method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@PathVariable Long id ){
		Officer officerUpdated = officerService.activate(id); 
		return ResponseEntity.ok(officerParse.toModelResponse(officerUpdated));
	}
	
	@RequestMapping(value = "/{id}/inactivate", method = RequestMethod.PUT)
	public ResponseEntity<?> inactivateUser(@PathVariable Long id ){
		Officer officerUpdated = officerService.inactivate(id); 
		return ResponseEntity.ok(officerParse.toModelResponse(officerUpdated));
	}

}
