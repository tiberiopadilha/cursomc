package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) //estratégia para mapear herança no jpa, com as subclasses
public abstract class Pagamento implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id	
	private Integer id; //id do Pagamento sera o mesmo do Pedido, por isso não tem a anotação generatevalue
	private Integer estado;
	
	//1 pagamento tem 1 pedido
	@JsonIgnore /*Pagamento nao deve conhecer seu Pedido(problema de referencia cíclica). Deve deixar a referencia ser gerenciada pelo json 
	para permitir que somente que o Pedido conheca o Pagamento e sejam serealizados, ignorando que Pagamento o conheca, pois se 
	permitir dos dois lados, dara erro na busca, erro de referencia cíclica.*/
	@OneToOne
	@JoinColumn(name="pedido_id")//pedido_id é a chave estrangeira da classe Pedido na classe Pagamento no BD
	@MapsId//para garantir que o id do pagamento sera o mesmo id do pedido
	private Pedido pedido;
	
	public Pagamento() {		
	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
