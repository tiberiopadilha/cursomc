package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Produto;

//classe que realiza operações de acesso aos dados da classe Categoria(CRUD entre outras)

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
