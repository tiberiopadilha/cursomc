package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//classe que implementa os serviços da classe Categoria, chamando operações da classe repository para acessar os dados
@Service
public class CategoriaService {

	@Autowired // faz com que a dependencia (CategoriaRepository repo) seja instanciada pelo spring				
	private CategoriaRepository repo;
	
	//método que recebe um id do método find() da classe CategoriaResource para buscar a categoria correspondente no BD
	public Categoria buscar(Integer id) {
		// optional é um container que carrega o objeto categoria
		Optional<Categoria> obj = repo.findById(id);// busca o objeto pelo id se existir, senão retorna nulo e lança exceção criado em services.exceptions
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo: " +Categoria.class.getName()));// se o objeto não for encontrado retorna null
	}
	
	//método que recebe um objeto Categoria do método insert() da classe CategoriaResource para inseri-lo no BD
	public Categoria inserir(Categoria cat) {
		cat.setId(null); //o objeto tem que ter um id nulo, ou seja, ainda não existir, para então ser salvo
		return repo.save(cat);	
	}

}
