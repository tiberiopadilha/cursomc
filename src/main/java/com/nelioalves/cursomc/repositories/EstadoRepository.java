package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Estado;

//classe que realiza operações de acesso aos dados da classe Estado(CRUD entre outras)

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
