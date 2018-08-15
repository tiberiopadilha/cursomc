package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")//para formatar a saida da hora de milisegundos para data, hora e minuto
	private Date instante;
	
	//1 Pedido tem 1 Pagamento, com relação bidirecional com ids relacionados, o id do pagamento é o mesmo do pedido
	@JsonManagedReference //como a relação pedido e pagamento é de mão dupla, deve deixa a referencia ser gerenciada pelo json para permitir...
	//...somente os pagamentos serem serealizados(pedido conhecer os pagamentos), pois se permitir dos dois lados, dara erro na busca...
	//... Essa referencia é gerenciada pelo json para vir os objetos(pagamentos) associados a cada pedido 
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")//cascate para salvar o pagamento relacionado com o pedido. MappedBy foi mapeado na classe pagamento pelo atributo pedido
	private Pagamento pagamento;
	
	//1 Pedido tem 1 Cliente
	@JsonManagedReference //como a relação pedido e cliente é de mão dupla, deve deixa a referencia ser gerenciada pelo json para permitir...
	//...somente os clientes serem serealizados(pedido conhecer os clientes), pois se permitir dos dois lados, dara erro na busca...
	//... Essa referencia é gerenciada pelo json para vir os objetos(clientes) associados a cada pedido 
	@ManyToOne
	@JoinColumn(name="cliente_id")//cliente_id é a chave estrangeira de Cliente na classe Pedido no BD
	private Cliente cliente;
	
	//1 Pedido tem 1 Endereco de entrega
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")//endereco_de_entrega_id é a chave estrangeira de Endereço na classe Pedido no BD
	private Endereco enderecoDeEntrega;
	
	//Pedido conhece os Itens do Pedido associados a ele
	@OneToMany(mappedBy="id.pedido") //ja foi mapeado do outro lado pelo id.pedido que tem a referencia para cada item do pedido
	private Set<ItemPedido> itens = new HashSet<>(); //set garante que não vai haver itens repetidos no pedido
	
	public Pedido(){		
	}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
