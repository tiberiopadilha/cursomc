package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;

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
	
	public Categoria atualizar(Categoria cat) {
		buscar(cat.getId());//chama o método buscar que ja tem uma implementacao para verificar se o objeto existe
		return repo.save(cat);		
	}
	
	//método que recebe um id do método delete() da classe CategoriaResource para deletar a categoria correspondente no BD
	public void deletar(Integer id) {
		buscar(id);//chama o metodo buscar para ver se o id existe	
		try {
		repo.deleteById(id);//deleta o objeto passado por id
		}catch(DataIntegrityViolationException e) { //exceção quando tenta deletar um objeto que viola integridade referencial no banco
			 throw new DataIntegrityException("Não é possível excluir uma categoria que possue produtos");//criada no pacote service.exceptions
			
		}
		
		}
}
