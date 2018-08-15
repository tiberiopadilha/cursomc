package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

//classe que implementa os serviços da classe Pedido, chamando operações da classe repository para acessar os dados
@Service
public class PedidoService {

	@Autowired // faz com que a dependencia (PedidoRepository repo) seja instanciada pelo spring				
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		// optional é um container que carrega o objeto pedido
		Optional<Pedido> obj = repo.findById(id);// busca o objeto pelo id se existir, senão retorna nulo e lança exceção criado em services.exceptions
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo: " +Pedido.class.getName()));// se o objeto não for encontrado retorna null
	}
}
