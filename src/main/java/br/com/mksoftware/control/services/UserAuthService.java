package br.com.mksoftware.control.services;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.mksoftware.control.dtos.resquest.MailRequest;
import br.com.mksoftware.control.dtos.resquest.PasswordUpdateRequest;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.entities.UserTokenPassword;
import br.com.mksoftware.control.exceptions.BusinessException;
import br.com.mksoftware.control.exceptions.UserNotFoundException;
import br.com.mksoftware.control.repository.UserRespository;
import br.com.mksoftware.control.repository.UserTokenPasswordRespository;
import br.com.mksoftware.control.utils.TokenGenarate;


@Service
public class UserAuthService {
	
	
	@Autowired
	private UserRespository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserTokenPasswordRespository userTokenPasswordRespository;
	
	
	
	@Transactional
	public User updatePasswordUser(PasswordUpdateRequest newPassword) {
		
		var user = userRepository.findByMail(newPassword.getMail()).orElseThrow(() -> new UserNotFoundException(newPassword.getMail().toString()));
		
		if(!newPassword.getNewPassword().equals(newPassword.getReNewPawword())) {
			throw new BusinessException(
					String.format("As senhas não coincidem."));
		}
		
		var userToken = userTokenPasswordRespository.findByMailUser(user.getMail());
		
		if(!ObjectUtils.isEmpty(userToken)) {
			if(!userToken.getToken().equals(newPassword.getToken())) {
				throw new BusinessException(
						String.format("O token informado não é válido."));
			}
			
			if(tokenExpirate(userToken)) {
				throw new BusinessException(
						String.format("O token expirado. favor tentar novamente."));
			}

		}
		
		user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
		
		return userRepository.save(user);
	}
	
	
	public void generateTokenUpdatePassword(MailRequest mailRequest) {
		
		var user = userRepository.findByMail(mailRequest.getMail()).orElseThrow(() -> new UserNotFoundException(mailRequest.getMail().toString()));
		UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        Long token = new  TokenGenarate().getToken();
		
		var userToken = new UserTokenPassword(null,OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).plusMinutes(5),token, user.getMail(), uuidAsString);
				
		userTokenPasswordRespository.save(userToken);

	}
	
	
	private boolean tokenExpirate(UserTokenPassword userToken) {
		return (OffsetDateTime.now().isAfter( userToken.getDateRegister()));
	}


}
