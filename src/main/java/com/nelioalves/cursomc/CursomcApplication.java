package com.nelioalves.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{ //interface que faz com que a classe execute toda vez que rodar a aplicação
	
	@Autowired 
	private CategoriaRepository catRepository;
	
	@Autowired 
	private ProdutoRepository prodRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { //metodo da interface executado ao rodar a aplicação
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		//adicionar os produtos em cada categoria
		cat1.getProdutos().add(prod1);
		cat1.getProdutos().add(prod2);
		cat1.getProdutos().add(prod3);
		cat2.getProdutos().add(prod2);
		
		//adicionar as categorias a cada produto
		prod1.getCategorias().add(cat1);
		prod2.getCategorias().add(cat1);
		prod2.getCategorias().add(cat2);
		prod3.getCategorias().add(cat1);		
		
		//salva as categorias no banco de dados atraves de uma instancia da classe catRepository
		catRepository.save(cat1);
		catRepository.save(cat2);
		
		//salva os produtos no banco de dados atraves de uma instancia da classe prodRepository
		prodRepository.save(prod1);
		prodRepository.save(prod2);		
		prodRepository.save(prod3);
		
	}
}
