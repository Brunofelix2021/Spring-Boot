package br.org.com.generation.blogpessoal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.com.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{ // herdando da  dependencia JPA e referenciando a classe postagem
	

	List<Postagem>findAllByTituloContainingIgnoreCase(String titulo); // guardando somente os metodos armazenamento de varios metodos
}
     // select * from tb_postagem   %like 

//interfaces fornecem metodos que atraves desses metodos consegue criar situações de herançãs mutuas



// interface é uma interface de ações