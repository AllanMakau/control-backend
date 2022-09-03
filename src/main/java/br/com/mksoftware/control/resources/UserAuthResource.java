package br.com.mksoftware.control.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mksoftware.control.config.security.JWTUtil;
import br.com.mksoftware.control.config.security.UserSS;
import br.com.mksoftware.control.dtos.resquest.MailRequest;
import br.com.mksoftware.control.dtos.resquest.PasswordUpdateRequest;
import br.com.mksoftware.control.parse.UserParse;
import br.com.mksoftware.control.services.UserAuthService;
import br.com.mksoftware.control.services.UserService;

@RestController
@RequestMapping(value = "/user-auth")
public class UserAuthResource {

	@Autowired
	private UserService userService;

	@Autowired
	private UserParse userParse;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserAuthService userAuthService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<?> forgot(@RequestBody MailRequest mailRequest) {
		userAuthService.generateTokenUpdatePassword(mailRequest);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/forgot/update-password", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePasswordForgot(@RequestBody @Valid PasswordUpdateRequest newPassowrd) {
		return ResponseEntity.ok(userParse.toModelResponse(userAuthService.updatePasswordUser(newPassowrd)));
	}

	@RequestMapping(value = "/update-password", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePassword(@RequestBody @Valid PasswordUpdateRequest newPassowrd) {
		return ResponseEntity.ok(userParse.toModelResponse(userService.updatePasswordUser(newPassowrd)));
	}

}
