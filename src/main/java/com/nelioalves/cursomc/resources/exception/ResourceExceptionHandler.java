package com.nelioalves.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice //classe auxiliar que manipula erros
public class ResourceExceptionHandler {
	
	//metodo padrao que recebe uma excessao
	@ExceptionHandler(ObjectNotFoundException.class) //para indicar que é um tratador de exceções
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		//objeto que recebe o status da mensagem, a mensagem em si e o tempo ocorrido		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);//retorna o status e o objeto no corpo da requisição		
	}
}
