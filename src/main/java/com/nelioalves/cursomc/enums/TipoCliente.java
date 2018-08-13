package com.nelioalves.cursomc.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	//construtor em enum é privado
	private TipoCliente(int cod, String desc) {
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
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}		
		//percorre todos os valores possíveis(pessoa fisica e juridica) e verifica se o codigo passado é igual 
		for(TipoCliente x : TipoCliente.values()){
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		//excessao se o codigo passado for inválido 
		throw new IllegalArgumentException("Id inválido: " +cod);
	}

}
