package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Pagamento;

//classe que realiza operações de acesso aos dados da classe Pagamento(CRUD entre outras)

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
