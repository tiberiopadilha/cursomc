package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.ClienteService;

//classe REST de controle mapeada com a url clientes
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired//faz com que a dependencia (ClienteService cliService) seja instanciada pelo spring
	private ClienteService cliService; //usado para acessar o serviço
	
	//metodo de busca que recebe um id via get e chama o serviço da classe ClienteService para buscar o Cliente no BD
	@RequestMapping(value="/{id}", method=RequestMethod.GET)	
	public ResponseEntity<?> find(@PathVariable Integer id) { 
		Cliente obj = cliService.buscar(id);//vai no serviço buscar o objeto pelo id		
		return ResponseEntity.ok().body(obj);
	}
}
