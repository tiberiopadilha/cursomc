package com.nelioalves.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{ //excessao tratada no pacote resouces.exception

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);		
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);		
	}
}
