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

import br.com.mksoftware.control.dtos.resquest.ContactRequest;
import br.com.mksoftware.control.dtos.resquest.FunctionRequest;
import br.com.mksoftware.control.dtos.resquest.PasswordUpdateRequest;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.parse.ContactParse;
import br.com.mksoftware.control.parse.FunctionParse;
import br.com.mksoftware.control.parse.UserParse;
import br.com.mksoftware.control.services.UserService;



@RestController
@RequestMapping(value = "/user")
public class UserResource {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserParse userParse;
	
	@Autowired
	private FunctionParse functionParse;
	
	@Autowired
	private ContactParse contactParse;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList(){
		List<User> users = userService.getAll();
		return ResponseEntity.ok(userParse.toCollectionModel(users));
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<?> saveUser( @RequestBody User user){
		User newUser = userService.saveUser(user);
		return ResponseEntity.ok(newUser);
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		User user = userService.getUserById(id);
		return ResponseEntity.ok(userParse.toModelResponse(user));
	}
	
	@RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> getUserDetailById(@PathVariable Long id){
		User user = userService.getUserById(id);
		return ResponseEntity.ok(userParse.toModelDetailResponse(user));
	}
	
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		userService.deleteUserById(id); 
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping( method = RequestMethod.PUT)
	public ResponseEntity<?> update( @RequestBody User user){
		User userUpdated = userService.updateUser(user); 
		return ResponseEntity.ok(userUpdated);
	}
	
	@RequestMapping(value = "/update-password", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePassword(@RequestBody @Valid PasswordUpdateRequest newPassowrd){
		return ResponseEntity.ok(userParse.toModelResponse(userService.updatePasswordUser(newPassowrd)));
	}
	
	@RequestMapping(value = "/{id}/activate", method = RequestMethod.DELETE)
	public ResponseEntity<?> activateUser(@PathVariable Long id ){
		User userUpdated = userService.activate(id); 
		return ResponseEntity.ok(userParse.toModelResponse(userUpdated));
	}
	
	@RequestMapping(value = "/{id}/inactivate", method = RequestMethod.DELETE)
	public ResponseEntity<?> inactivateUser(@PathVariable Long id ){
		User userUpdated = userService.inactivate(id); 
		return ResponseEntity.ok(userParse.toModelResponse(userUpdated));
	}
	
	@RequestMapping(value = "/{id}/add-function", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addFunction(@PathVariable Long id, @RequestBody FunctionRequest functionRequest ){
		var function = functionParse.toDomainObject(functionRequest);
		User userUpdated = userService.addFunction(id, function); 
		return ResponseEntity.ok(userParse.toModelResponse(userUpdated));
	}

	@RequestMapping(value = "/{id}/remove-function", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> removeFunction(@PathVariable Long id, @RequestBody FunctionRequest functionRequest ){
		var function = functionParse.toDomainObject(functionRequest);
		User userUpdated = userService.removeFunction(id, function); 
		return ResponseEntity.ok(userParse.toModelResponse(userUpdated));
	}
	
	@RequestMapping(value = "/{id}/add-contact", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addContact(@PathVariable Long id, @RequestBody ContactRequest contactRequest ){
		var contact = contactParse.toDomainObject(contactRequest);
		User userUpdated = userService.addContact(id, contact); 
		return ResponseEntity.ok(userParse.toModelResponse(userUpdated));
	}

	@RequestMapping(value = "/{id}/remove-contact", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> removeContact(@PathVariable Long id, @RequestBody ContactRequest contactRequest ){
		var contact = contactParse.toDomainObject(contactRequest);
		User userUpdated = userService.removeContact(id, contact); 
		return ResponseEntity.ok(userParse.toModelResponse(userUpdated));
	}
	
	@RequestMapping(value = "/{idUser}/image", method = RequestMethod.POST )
	public ResponseEntity<Void> addImage(@PathVariable Long idUser, @RequestBody String base64 ){
		userService.saveImage(idUser, base64);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/{idUser}/image", method = RequestMethod.DELETE )
	public ResponseEntity<Void> removeImage(@PathVariable Long idUser ){
		userService.removeImage(idUser);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/{idUser}/image", method = RequestMethod.GET )
	public ResponseEntity<?> getImage(@PathVariable Long idUser ){
		userService.getImage(idUser);
		return ResponseEntity.ok().build();
	}
	

}
