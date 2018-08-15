package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ItemPedido implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	//chave composta que tem como id os ids(id_pedido e id_produto) da classe auxiliar ItemPedido_PK que faz o relacionamento entre 
	//pedido e produto. Usa a anotação EmbeddedId para marcar que é um id embutido em um tipo auxiliar 
	@EmbeddedId
	@JsonIgnore //pedir para o json ignorar e nao serializar tudo que vier de itempedidopk
	private ItemPedidoPK id = new ItemPedidoPK();	
	
	private Double desconto;
	private Integer quantidade;
	private double preco;
	
	public ItemPedido() {		
	}

	//construtor recebe além dos atributos do ItemPedido, o ids referentes aos pedidos e produtos dos itens do pedido
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	//get pedido e produto para ter acesso a pedido e produto fora das classes pedido e produto
	@JsonIgnore //pedir para o json ignorar e nao serializar os pedidos, pois oq é iniciado com get é serializado
	//automaticamente, ocorrendo referencia cíclica
	public Pedido getPedido() {
		return id.getPedido();		
	}
	//não ignorar o produto pq é necessario saber quais produtos estao relacionados com os itens
	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
