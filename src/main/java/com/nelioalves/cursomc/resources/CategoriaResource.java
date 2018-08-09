package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource { //classe REST de controle
	
	@Autowired//faz com que a dependencia (CategoriaService catService) seja instanciada pelo spring
	private CategoriaService catService; //usado para acessar o serviço
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) { //metodo que recebe id e chama o serviço da classe CategoriaServide para buscar
		Categoria obj = catService.buscar(id);//vai no serviço buscar o objeto pelo id		
		return ResponseEntity.ok().body(obj);
	}
}
