package br.org.com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.com.generation.blogpessoal.model.Postagem;
import br.org.com.generation.blogpessoal.repository.PostagemRepository;


@RestController //Controlar essa classe tipo se prepara para controlar
@RequestMapping("/postagens") // criando o endereço depois do 8080
@CrossOrigin(origins = "*", allowedHeaders = "*") // endereço a ser passado normalmente passaod pelo front no caso o * é que esta aceitando tudo



public class PostagemController {
		

	@Autowired // intanciando um objeto da classe  
	private PostagemRepository postagemRepository; // PostagemRepository puxando os metodos da Interface PostagemRepository
	
	//DEFINIÇÃO DE ENDEREÇO
	@GetMapping 
	public ResponseEntity<List<Postagem>> getAll (){   // Pegando todos os itens da tabela postagem
		return ResponseEntity.ok(postagemRepository.findAll());  // retornando o /postagem e retorna findall todos os dados da tabela
	}
	
	
	// ResponseEntity retorno do  HTTP ex 404
	@GetMapping("/{id}")  // pesquisa por ID
	public ResponseEntity<Postagem> getById(@PathVariable long id) { // @PathVariable caminho da variavel onde ele vai fazer a definição do tipo da variavel
		return postagemRepository.findById(id) //retornando a pesquisa conforme o ID que eu coloquei
			.map(resposta -> ResponseEntity.ok(resposta)) // .map seria um IF caso eu informe um valor que exista ele retorna sua pesquisa pelo ID
			.orElse(ResponseEntity.notFound().build()); // caso não exista retorna erro 404  notFound
	}
	
	// GET PESQUISA- PUXO DADOS 
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); // pesquisando na coluna titulo o que foi digitado pra verificar se esta dentro de 1 ou mais  registro da tabela
	}

	
	//CRIA DADOS 
	@PostMapping
	public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem)); // salvar tudo o que foi digitado no front e salvar tudo no banco de dados
	}  //@Valid verifica as regras que colocou na model 
		// RequestBody pegar o conteúdo do corpo da requisição e transfora no objeto , ou seja o parametro
	
	
	//ALTERA DADOS
	@PutMapping
	public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem){ 
		return postagemRepository.findById(postagem.getId()) // buscando pelo ID para poder alterar
			.map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem))) // caso encontre eu consigo alterar
			.orElse(ResponseEntity.notFound().build());// caso eu não encontre retorna um erro 404 notFound
	}
			

	//DELETAR
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return postagemRepository.findById(id) //Fazendo busca pelo ID
				.map(resposta -> { postagemRepository.deleteById(id); // Caso encontre o ID ele deleta o ID
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();// forçando me dar um retorno 204 de sucesso para informar que foi deletado
				})
				.orElse(ResponseEntity.notFound().build()); // caso ID não existir  ira retornar notFound
	}

}