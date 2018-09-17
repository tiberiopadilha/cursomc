package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;

//classe REST de controle mapeada com a url categorias
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired//faz com que a dependencia (CategoriaService catService) seja instanciada pelo spring
	private CategoriaService catService; //usado para acessar o serviço
	
	//metodo de listar todas as categorias. Chama o serviço da classe CategoriaService para buscar no BD
	@RequestMapping(method=RequestMethod.GET)		
	public ResponseEntity<List <CategoriaDTO>> findAll() {
		List <Categoria> lista = catService.listarTodas();
		//cria uma listadto, percorre cada elemento da lista e converte
		List <CategoriaDTO> listaDto = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);//se deu tudo ok busca e retorna o objeto 
		}
	
	//metodo de busca que recebe um id via get e chama o serviço da classe CategoriaService para buscar a Categoria no BD
	@RequestMapping(value="/{id}", method=RequestMethod.GET)		
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = catService.buscar(id);//vai no serviço buscar o objeto pelo id		
		return ResponseEntity.ok().body(obj);//se deu tudo ok busca e retorna o objeto 
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
		return ResponseEntity.noContent().build();//se deu tudo ok atualiza e não retorna nada		
	}
	
	//metodo delete que recebe um id via delete e chama o serviço da classe CategoriaService e deleta a Categoria no BD
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)		
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
		catService.deletar(id);//vai no serviço para deletar o objeto pelo id		
		return ResponseEntity.noContent().build();//se deu tudo ok deleta e não retorna nada
		}
}
