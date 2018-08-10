package com.nelioalves.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // interface que faz com que a classe execute toda vez que rodar a aplicação

	//instancias das classes repository para inserir no banco de dados
	@Autowired
	private CategoriaRepository catRepository;

	@Autowired
	private ProdutoRepository prodRepository;

	@Autowired
	private EstadoRepository estRepository;

	@Autowired
	private CidadeRepository cidRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { // metodo da interface executado ao rodar a aplicação

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);

		// adicionar os produtos em cada categoria
		cat1.getProdutos().add(prod1);
		cat1.getProdutos().add(prod2);
		cat1.getProdutos().add(prod3);
		cat2.getProdutos().add(prod2);

		// adicionar as categorias a cada produto
		prod1.getCategorias().add(cat1);
		prod2.getCategorias().add(cat1);
		prod2.getCategorias().add(cat2);
		prod3.getCategorias().add(cat1);

		// salva as categorias no banco de dados atraves de uma instancia da classe
		// catRepository
		catRepository.save(cat1);
		catRepository.save(cat2);

		// salva os produtos no banco de dados atraves de uma instancia da classe
		// prodRepository
		prodRepository.save(prod1);
		prodRepository.save(prod2);
		prodRepository.save(prod3);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		// adiciona as cidades em cada estado
		est1.getCidades().add(cid1);
		est2.getCidades().add(cid2);
		est2.getCidades().add(cid3);

		// salva os estados no banco de dados atraves de uma instancia da classe estRepository
		estRepository.save(est1);
		estRepository.save(est2);

		// salva as cidades no banco de dados atraves de uma instancia da classe cidRepository
		cidRepository.save(cid1);
		cidRepository.save(cid2);
		cidRepository.save(cid3);

	}
}
