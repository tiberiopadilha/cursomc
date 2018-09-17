package com.nelioalves.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{//excessao tratada no pacote resouces.exception

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);		
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);		
	}
}
