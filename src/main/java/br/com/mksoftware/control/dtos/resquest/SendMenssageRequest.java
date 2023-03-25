package br.com.mksoftware.control.dtos.resquest;

import java.util.Map;

import br.com.mksoftware.control.enums.TemplateType;
import br.com.mksoftware.control.enums.TypeMessage;
import lombok.Data;

@Data
public class SendMenssageRequest {
	
	
	public SendMenssageRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SendMenssageRequest(String to, String subject, TemplateType template, TypeMessage typeMessage,
			Map<String, String> values) {
		super();
		this.to = to;
		this.subject = subject;
		this.template = template;
		this.typeMessage = typeMessage;
		this.values = values;
	}
	
	private String to;
	private String subject;
	private TemplateType template;
	private TypeMessage typeMessage;
	private Map<String, String> values;
	
	
}
