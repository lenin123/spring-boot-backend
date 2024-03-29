package com.leninmuller.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	
	private static final long serialVersionUID = 1L;	
	
	private List<FieldMessage> listErros = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return listErros;
	}

	public void addError(String fieldName, String message) {
		listErros.add(new FieldMessage(fieldName, message));
	}


	

}
