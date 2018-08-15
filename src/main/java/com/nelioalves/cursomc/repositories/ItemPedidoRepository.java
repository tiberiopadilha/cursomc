package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.ItemPedido;

//classe que realiza operações de acesso aos dados da classe ItemPedido(CRUD entre outras)

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
