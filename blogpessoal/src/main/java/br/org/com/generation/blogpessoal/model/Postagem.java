package br.org.com.generation.blogpessoal.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity /*@Entity indica que a classe é uma entidade, ele será utilizada para gerar uma tabela no Banco de Dados. */
@Table(name = "tb_postagens")  /*A anotação @Table indica o nome da tabela no Banco de dados.*/
public class Postagem {


	@Id /* A anotação @ID inidica que o atributo é a chave primária da tabela*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)  /*indica que a chave primária será gerada 	automaticamente pelo Banco de Dados.*/
	private long id; /*atributo id da tabela*/


	@NotBlank(message = "O atributo título é Obrigatório e não pode utilizar espaços em branco!") 
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo; 

	@NotNull(message = "O atributo texto é Obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 500 caracteres")
	private String texto;


	@UpdateTimestamp
	private LocalDateTime data;

	@ManyToOne  // Relacionando do outro lado
	@JsonIgnoreProperties("postagem")
	private Tema tema; // Objeto criado
	
	 /*Os Métodos Get e Set obrigatoriamente devem ser criados para cada atributo.*/
	 
	 
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}