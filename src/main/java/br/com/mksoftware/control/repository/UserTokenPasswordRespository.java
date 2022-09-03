package br.com.mksoftware.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mksoftware.control.entities.UserTokenPassword;

@Repository
public interface UserTokenPasswordRespository extends JpaRepository<UserTokenPassword, Long>{
	
	UserTokenPassword findByMailUser(String mail);

}
