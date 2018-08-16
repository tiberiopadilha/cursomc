package com.nelioalves.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.services.CategoriaService;

//classe REST de controle mapeada com a url categorias
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired//faz com que a dependencia (CategoriaService catService) seja instanciada pelo spring
	private CategoriaService catService; //usado para acessar o serviço
	
	//metodo de busca que recebe um id via get e chama o serviço da classe CategoriaService para buscar a Categoria no BD
	@RequestMapping(value="/{id}", method=RequestMethod.GET)		
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = catService.buscar(id);//vai no serviço buscar o objeto pelo id		
		return ResponseEntity.ok().body(obj);
	}
	
	//metodo que recebe um objeto Categoria via POST no formato json e chama o serviço da classe CategoriaService para inserir uma Categoria no BD
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria cat){ //@RequestBody faz o json ser convertido para o objeto java
		cat = catService.inserir(cat);	
		//retorna a uri do recurso criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//metodo de atualização que recebe o id e o objeto da Categoria via PUT e chama o serviço da classe CategoriaService para atualizar a Categoria no BD
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria cat, @PathVariable Integer id){
		cat = catService.atualizar(cat);
		return ResponseEntity.noContent().build();		
	}
	
	
	
}
