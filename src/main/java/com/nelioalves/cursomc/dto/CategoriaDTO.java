package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.Categoria;

//classe para determinar quais informações de uma classe devem trafegar nas operações, ou seja, quando for listada uma categoria
//não mostrar os produtos relacionados a elas, somente id e o nome da categoria 
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {		
	}
	
	//converte o objeto Categoria em objeto CategoriaDTO
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
