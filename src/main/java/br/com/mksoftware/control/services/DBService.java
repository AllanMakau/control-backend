package br.com.mksoftware.control.services;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Contact;
import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.Officer;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.TagPermission;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.enums.ContactTypeEnum;
import br.com.mksoftware.control.repository.ContactRepository;
import br.com.mksoftware.control.repository.DepartamentRespository;
import br.com.mksoftware.control.repository.FunctionRepository;
import br.com.mksoftware.control.repository.OfficerRepository;
import br.com.mksoftware.control.repository.SystemRepository;
import br.com.mksoftware.control.repository.TagPermissionRespository;
import br.com.mksoftware.control.repository.UserRespository;

@Service
public class DBService {

	@Autowired
	OfficerRepository officeRepository;

	@Autowired
	DepartamentRespository departamentRepository;

	@Autowired
	FunctionRepository functionRepository;

	@Autowired
	TagPermissionRespository tagPermissionRepository;

	@Autowired
	SystemRepository systemRepository;

	@Autowired
	UserRespository userAdminRepository;
	
	@Autowired
	ContactRepository contactRepository;

	@Autowired
	private BCryptPasswordEncoder encode;

	public void dbInit() {

		TagPermission f1 = new TagPermission(null, "ROLES_INSERTUSER", "cadastrar usuarios", true);
		TagPermission f2 = new TagPermission(null, "ROLES_DELETEUSER", "deletar usuarios", true);
		TagPermission f3 = new TagPermission(null, "ROLES_LISTUSER", "listar usuarios", true);
		TagPermission f4 = new TagPermission(null, "ROLES_UPDATEUSER", "atualizar usuarios", true);
		TagPermission f5 = new TagPermission(null, "ROLES_INSERTPERFIL", "cadastrar perfil", true);
		TagPermission f6 = new TagPermission(null, "ROLES_DELETEPERFIL", "deletar perfil", true);
		TagPermission f7 = new TagPermission(null, "ROLES_UPDATEPERFIL", "atualizar Perfil", true);
		TagPermission f8 = new TagPermission(null, "ROLES_LISTPERFIL", "listar perfil", true);
		TagPermission f9 = new TagPermission(null, "ROLES_INSERTFUNC", "cadastrar funcionalidade", true);
		TagPermission f10 = new TagPermission(null, "ROLES_DELETEFUNC", "cadastrar deletar", true);
		TagPermission f11 = new TagPermission(null, "ROLES_UPDATEFUNC", "cadastrar atualizar", true);
		TagPermission f12 = new TagPermission(null, "ROLES_LISTFUNC", "cadastrar listar", true);
		TagPermission mestre = new TagPermission(null, "ROLES_ADMIN", "Administrador", true);
		tagPermissionRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, mestre));

		
		System sys1 = new System(null, "Control", "CONTROL", "controle de usuários", true);
		System sys2 = new System(null, "Diárias", "Diárias", "Sistema de controle de diáras", true);
		System sys3 = new System(null, "Viagem", "Viagem", "Sistema de controle de viagens",  true);
		System sys4 = new System(null, "HelpDesk", "HelpDesk", "Sistema de help desk",  true);
		systemRepository.saveAll(Arrays.asList(sys1,sys2,sys3,sys4));

		

		Function pAdmin = new Function(null, "Admin", "usuario administrador do sistema", true, new HashSet<TagPermission>());
		Function pBasic = new Function(null, "Basic", "usuario basico do sistema", true, new HashSet<TagPermission>());
		Function pInsert = new Function(null, "Insert", "usuario para operações de insert", true, new HashSet<TagPermission>());
		Function pUpdate = new Function(null, "Update", "usuario para operações de update", true, new HashSet<TagPermission>());
		Function pDelete = new Function(null, "Delete", "usuario para operações de delete", true, new HashSet<TagPermission>());
		
		Function teste = new Function(null, "Teste", "teste", true, new HashSet<TagPermission>());
		teste.getTaglist().addAll(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12));
		functionRepository.save(teste);

		
		pAdmin.getTaglist().addAll(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12));
		functionRepository.save(pAdmin);
		
		
		pBasic.getTaglist().addAll(Arrays.asList(f3,f8,f12));
		functionRepository.save(pBasic);
		
		
		pInsert.getTaglist().addAll(Arrays.asList(f3,f8,f12,f1,f5,f9));
		functionRepository.save(pInsert);
		
		
		pUpdate.getTaglist().addAll(Arrays.asList(f3,f8,f12,f1,f5,f9,f4,f7,f11));
		functionRepository.save(pUpdate);
		
		
		pDelete.getTaglist().addAll(Arrays.asList(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12));
		functionRepository.save(pDelete);
		
		Officer of1 = new Officer(null, "Diretor", "Diretor", true);
		Officer of2 = new Officer(null, "Analista", "Analista", true);
		Officer of3 = new Officer(null, "Cordenador", "Cordenador", true);
		
		officeRepository.saveAll(Arrays.asList(of1,of2,of3));
		
		Department dp1 = new Department(null, "Recursos Humanos", "", true);
		Department dp2 = new Department(null, "Diretoria", "Diretoria", true);
		Department dp3 = new Department(null, "Atendimento", "Atendimento", true);
		
		departamentRepository.saveAll(Arrays.asList(dp1,dp2,dp3));
		

		User userAdmin = new User();
		userAdmin.setFirstName("Admin");
		userAdmin.setLastName("Admin");
		userAdmin.setIdentityDocument("0976905973");
		userAdmin.setIsActive(true);
		userAdmin.setMail("admin@gmail.com");
		userAdmin.setNickname("Admin");
		userAdmin.setSocialNumber("01825809542");
		userAdmin.setBirthDate(OffsetDateTime.now());
		userAdmin.setUsername("admin");
		userAdmin.setPassword(encode.encode("123456"));
		userAdmin.setDepartment(dp1);
		userAdmin.setOfficer(of1);
		userAdmin.getContactList().add(new Contact(null, ContactTypeEnum.CEL, "71", "991637645"));
		userAdmin.getFunctionList().add(pAdmin);
		userAdmin.getSystemList().addAll(Arrays.asList(sys1,sys2,sys3,sys4));

		
		  User userBasic = new User(); userBasic.setFirstName("userBasic");
		  userBasic.setLastName("userBasic");
		  userBasic.setIdentityDocument("0976905973"); userBasic.setIsActive(true);
		  userBasic.setMail("userbasic@gmail.com"); userBasic.setNickname("userBasic");
		  userBasic.setSocialNumber("01825809543");
		  userBasic.setBirthDate(OffsetDateTime.now()); userBasic.setUsername("admin");
		  userBasic.setPassword(encode.encode("123456")); userBasic.setDepartment(dp2);
		  userBasic.setOfficer(of2); userBasic.getFunctionList().add(pBasic);
		  userBasic.getContactList().add(new Contact(null, ContactTypeEnum.CEL, "71",
		  "991637645")); userBasic.getSystemList().addAll(Arrays.asList(sys1));
		  
		  User userInsert = new User(); userInsert.setFirstName("userInsert");
		  userInsert.setLastName("userInsert");
		  userInsert.setIdentityDocument("0976905974"); userInsert.setIsActive(true);
		  userInsert.setMail("userinsert@gmail.com");
		  userInsert.setNickname("userInsert");
		  userInsert.setSocialNumber("01825809544");
		  userInsert.setBirthDate(OffsetDateTime.now());
		  userInsert.setUsername("admin");
		  userInsert.setPassword(encode.encode("123456"));
		  userInsert.setDepartment(dp3); userInsert.setOfficer(of3);
		  userInsert.getFunctionList().add(pInsert);
		  userInsert.getSystemList().addAll(Arrays.asList(sys1,sys3,sys4));
		  
		  User userUpdate = new User(); userUpdate.setFirstName("userUpdate");
		  userUpdate.setLastName("userUpdate");
		  userUpdate.setIdentityDocument("0976905973"); userUpdate.setIsActive(true);
		  userUpdate.setMail("userupdaten@gmail.com");
		  userUpdate.setNickname("userUpdate");
		  userUpdate.setSocialNumber("01825809545");
		  userUpdate.setBirthDate(OffsetDateTime.now());
		  userUpdate.setUsername("userUpdate");
		  userUpdate.setPassword(encode.encode("123456"));
		  userUpdate.setDepartment(dp1); userUpdate.setOfficer(of3);
		  userUpdate.getFunctionList().add(pUpdate);
		  userUpdate.getContactList().add(new Contact(null, ContactTypeEnum.CEL, "71",
		  "991637645"));
		  userUpdate.getSystemList().addAll(Arrays.asList(sys1,sys2,sys4));
		  
		  userAdminRepository.saveAll(Arrays.asList(userAdmin, userBasic,
		  userInsert,userUpdate));
		 
		

	}
}
