package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.enums.TipoCliente;

@Entity
public class Cliente implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo; //armazena um inteiro para o tipo do cliente, mas vai expor um tipocliente no construtor pro sistema
	
	// 1 cliente tem vários endereços
	//a classe Cliente deve conhecer seus endereços, mas não ao contrario. Esse controle foi feito na classe Endereço
	@OneToMany(mappedBy="cliente") //já foi mapeado na classe Endereco pelo campo cliente
	private List<Endereco> enderecos = new ArrayList<>() ;	
	
	//cliente tem varios telefones que nao podem ser repetidos, por isso Set
	@ElementCollection //mapeado como entidade fraca
	@CollectionTable(name="TELEFONE")//cria uma tabela fraca com o nome TELEFONE
	private Set<String> telefones = new HashSet<>();
	
	//1 Cliente tem varios Pedidos. Cliente deve enxergar os pedidos
	@JsonIgnore /*Cliente nao deve conhecer seu Pedido(problema de referencia cíclica). Deve deixar a referencia ser gerenciada pelo json 
	para permitir somente que o Pedido conheca o Cliente e sejam serealizados, ignorando que Cliente o conheca, pois se permitir dos dois 
	lados, dara erro na busca, erro de referencia cíclica.*/
	@OneToMany(mappedBy="cliente")//ja foi mapeado na classe cliente pelo atributo cliente
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {		
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();//recebe o tipo, mas só pega so o codigo
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() { //para retornar o tipo equivalente recebendo um inteiro, usa o método toEnum da Classe Enum TipoCliente
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();//recebe o tipo, mas so armazena o codigo
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
