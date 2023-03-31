package br.com.mksoftware.control.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;



public class ValidationError extends Problem{
	
	
List<FieldMessage> erros = new ArrayList<FieldMessage>();



	public ValidationError(Integer status, OffsetDateTime timestamp, String type, String title, String detail,
		String userMessage, List<FieldMessage> erros) {
	super(status, timestamp, type, title, detail, userMessage);
	this.erros = erros;
}


	ValidationError(Integer status, OffsetDateTime timestamp, String type, String title, String detail,
			String userMessage) {
		super(status, timestamp, type, title, detail, userMessage);
	}
	
	public void addErro(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

}
