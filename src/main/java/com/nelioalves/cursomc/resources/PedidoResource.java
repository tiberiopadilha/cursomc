package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;

//classe REST de controle mapeada com a url pedidos
@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired//faz com que a dependencia (PedidoService pedService) seja instanciada pelo spring
	private PedidoService pedService; //usado para acessar o serviço
	
	//metodo de busca que recebe um id via get e chama o serviço da classe PedidoService para buscar o Pedido no BD
	@RequestMapping(value="/{id}", method=RequestMethod.GET)	
	public ResponseEntity<?> find(@PathVariable Integer id) { 
		Pedido obj = pedService.buscar(id);//vai no serviço buscar o objeto pelo id		
		return ResponseEntity.ok().body(obj);
	}
}
