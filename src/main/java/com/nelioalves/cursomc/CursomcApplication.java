package com.nelioalves.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
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
	
	@Autowired
	private ClienteRepository cliRepository;

	@Autowired
	private EnderecoRepository endRepository;

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
		
		//cria o cliente e adiciona seus telefones
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().add("27363323");
		cli1.getTelefones().add("93838393");
		
		//cria os endereços e adiciona aos clientes e suas cidades
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardim", "38220834", cli1, cid1);
		Endereco end2 = new Endereco(null, "Evenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().add(end1);
		cli1.getEnderecos().add(end2);
		
		// salva cliente primeiro no banco de dados atraves de uma instancia da classe cliRepository pois sao independetes dos endereços
		cliRepository.save(cli1);
		
		// depois salva os endereços no banco de dados atraves de uma instancia da classe endRepository
		endRepository.save(end1);
		endRepository.save(end2);

		
	
		

	}
}
