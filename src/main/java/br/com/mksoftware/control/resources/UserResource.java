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
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.services.UserService;



@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<User> users = userService.getAll();
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveUser( @RequestBody User user){
		User newUser = userService.saveUser(user);
		return ResponseEntity.ok(newUser);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		userService.deleteUserById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody User user){
		User userUpdated = userService.updateUser(user); 
		return ResponseEntity.ok(userUpdated);
	}
	
	
	@RequestMapping(value = "/{id}/ativo/{ativo}", method = RequestMethod.PUT)
	public ResponseEntity<?> activateUser(@PathVariable Long id, @PathVariable Boolean ativo ){
		User userUpdated = userService.activate(id, ativo); 
		return ResponseEntity.ok(userUpdated);
	}
	
	@RequestMapping(value = "/{id}/add-system/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addSystem(@PathVariable Long id, @RequestBody System system ){
		User userUpdated = userService.addSystem(id, system); 
		return ResponseEntity.ok(userUpdated);
	}

	@RequestMapping(value = "/{id}/remove-system/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> removeSystem(@PathVariable Long id, @RequestBody System tag ){
		User userUpdated = userService.removeSystem(id, tag); 
		return ResponseEntity.ok(userUpdated);
	}
	
	@RequestMapping(value = "/{id}/add-function/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addFunction(@PathVariable Long id, @RequestBody Function function ){
		User userUpdated = userService.addFunction(id, function); 
		return ResponseEntity.ok(userUpdated);
	}

	@RequestMapping(value = "/{id}/remove-function/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> removeFunction(@PathVariable Long id, @RequestBody Function function ){
		User userUpdated = userService.removeFunction(id, function); 
		return ResponseEntity.ok(userUpdated);
	}

}
