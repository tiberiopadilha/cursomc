package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Pedido;

//classe que realiza operações de acesso aos dados da classe Pedido(CRUD entre outras)

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
