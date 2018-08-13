package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Cliente;

//classe que realiza operações de acesso aos dados da classe Cidade(CRUD entre outras)

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
