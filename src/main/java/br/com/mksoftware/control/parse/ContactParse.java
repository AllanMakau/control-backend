package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.ContactResponse;
import br.com.mksoftware.control.dtos.resquest.ContactRequest;
import br.com.mksoftware.control.entities.Contact;

@Component
public class ContactParse {
	
	@Autowired
	private ModelMapper modelMapper;

	public Contact toDomainObject(ContactRequest contactRequest) {
		return modelMapper.map(contactRequest, Contact.class);
	}

	public ContactResponse toModelResponse(Contact contac) {
		return modelMapper.map(contac, ContactResponse.class);
	}
	
	public List<ContactResponse> toCollectionModel(List<Contact> contacts) {
		return contacts.stream()
				.map(contact -> toModelResponse(contact))
				.collect(Collectors.toList());  
	}

}
