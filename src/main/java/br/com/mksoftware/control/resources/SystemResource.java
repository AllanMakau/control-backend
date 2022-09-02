package br.com.mksoftware.control.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.dtos.resquest.SystemRequest;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.parse.SystemParse;
import br.com.mksoftware.control.services.SystemService;

@RestController
@RequestMapping(value = "/system")
public class SystemResource {

	@Autowired
	private SystemService systemService;

	@Autowired
	private SystemParse systemParse;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList() {
		List<System> systems = systemService.getAll();
		return ResponseEntity.ok(systemParse.toCollectionModel(systems));
	}

	//@PreAuthorize("hasRole('ATUAUSER 1')") 
	//@PreAuthorize("hasAuthority('ATUAUSER 1')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveSystem(@RequestBody @Valid SystemRequest systemRequest) {
		System system = systemParse.toDomainObject(systemRequest);
		system = systemService.saveSystem(system);
		return ResponseEntity.ok(systemParse.toModelResponse(system));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getSystemById(@PathVariable Long id) {
		System system = systemService.getSystemById(id);
		return ResponseEntity.ok(systemParse.toModelResponse(system));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		systemService.deleteSystemById(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody @Valid SystemRequest systemRequest) {

		System systemUpdated = systemParse.toDomainObject(systemRequest);
		systemUpdated = systemService.updateSystem(systemUpdated);
		return ResponseEntity.ok(systemParse.toModelResponse(systemUpdated));
	}

	@RequestMapping(value = "/{id}/activate", method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@PathVariable Long id) {
		System systemUpdated = systemService.activate(id);
		return ResponseEntity.ok(systemParse.toModelResponse(systemUpdated));
	}

	@RequestMapping(value = "/{id}/inactivate", method = RequestMethod.PUT)
	public ResponseEntity<?> inactivateUser(@PathVariable Long id) {
		System systemUpdated = systemService.inactivate(id);
		return ResponseEntity.ok(systemParse.toModelResponse(systemUpdated));
	}

}
