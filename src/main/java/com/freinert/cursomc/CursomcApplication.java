package com.freinert.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.freinert.cursomc.domain.Categoria;
import com.freinert.cursomc.domain.Cidade;
import com.freinert.cursomc.domain.Cliente;
import com.freinert.cursomc.domain.Endereco;
import com.freinert.cursomc.domain.Estado;
import com.freinert.cursomc.domain.Produto;
import com.freinert.cursomc.domain.enums.TipoCliente;
import com.freinert.cursomc.repositories.CategoriaRepository;
import com.freinert.cursomc.repositories.CidadeRepository;
import com.freinert.cursomc.repositories.ClienteRepository;
import com.freinert.cursomc.repositories.EnderecoRepository;
import com.freinert.cursomc.repositories.EstadoRepository;
import com.freinert.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
	
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); 
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	
		
		Estado est1 = new Estado(null,"Minas gerais");
		Estado est2 = new Estado(null,"Sum Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo",est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2)); 
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com","341234678",TipoCliente.PESSOAFISICA );
		
		cli1.getTelefones().addAll(Arrays.asList("27688823","123445668"));
		
		Endereco e1 = new Endereco(null,"Rua das Flores,","1111","apto. 47","Jardim das Flores","0234499",cli1, c1);
		Endereco e2 = new Endereco(null,"Av. Matos,","2776","","Centro","035000000",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1)); 
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

	
}
