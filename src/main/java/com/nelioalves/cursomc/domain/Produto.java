package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private double preco;
	
	//como a relação entre Categoria e Produto é de muito pra muito, deve ser criada outra tabela que vamos chamar de PRODUTO_CATEGORIA
	//contendo as chaves estrangeiras da duas tabelas
	@JsonBackReference //diz que no outro lado da associação ja foram buscados os objetos e omite a busca para nao da busca cíclica
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", 
	           joinColumns = @JoinColumn(name= "produto_id"), 
	           inverseJoinColumns = @JoinColumn(name= "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();//cada produto possui uma ou varias categorias
	
	//Produto conhece os Itens do Pedido associados a ele
	@OneToMany(mappedBy="id.produto") //ja foi mapeado do outro lado pelo id.produto que tem a referencia para cada item do pedido
	private Set<ItemPedido> itens = new HashSet<>(); //set garante que não vai haver itens repetidos no pedido
	
	public Produto() {		
	}
	
	public Produto(Integer id, String nome, double preco) { //como categoria ja foi iniciada nao vai no construtor
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	//produto conhece os seus pedidos. Então precisa de um metodo para pecorrer os pedidos em itempedidos, adicionando os itens aos pedidos
	//e retornando uma lista dos pedidos
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getPedido());
		}
		return lista;		
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
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
