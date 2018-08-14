package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//classe que implementa os serviços da classe Categoria, chamando operações da classe repository para acessar os dados
@Service
public class ClienteService {

	@Autowired // faz com que a dependencia (ClienteRepository repo) seja instanciada pelo spring				
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		// optional é um container que carrega o objeto cliente
		Optional<Cliente> obj = repo.findById(id);// busca o objeto pelo id se existir, senão retorna nulo e lança exceção criado em services.exceptions
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo: " +Cliente.class.getName()));// se o objeto não for encontrado retorna null
	}

}
