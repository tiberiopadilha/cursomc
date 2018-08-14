package com.nelioalves.cursomc.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	//construtor em enum é privado
	private EstadoPagamento(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;		
	}
	
	//enums so tem gets porque valores são fixos
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//possivel mesmo sem instanciar objeto
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}		
		//percorre todos os valores possíveis(pendente, quitado e cancelado) e verifica se o codigo passado é igual 
		for(EstadoPagamento x : EstadoPagamento.values()){
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		//excessao se o codigo passado for inválido 
		throw new IllegalArgumentException("Id inválido: " +cod);
	}

}
