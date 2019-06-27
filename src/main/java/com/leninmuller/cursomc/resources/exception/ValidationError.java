package com.leninmuller.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandedError {
	
	private static final long serialVersionUID = 1L;	
	
	private List<FieldMessage> listErros = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return listErros;
	}

	public void addError(String fieldName, String message) {
		listErros.add(new FieldMessage(fieldName, message));
	}


	

}
