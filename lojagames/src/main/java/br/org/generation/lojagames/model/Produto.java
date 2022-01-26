package br.org.generation.lojagames.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity // declarando tipo entidade
@Table(name = "tb_produtos") // criando tabela

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // passando chave primária da tabela automaticamente
	private Long id; // criando o ID

	@NotBlank(message = "O campo Nome é obrigatório!")
	private String nome;

	@Size(min = 50, max = 350, message = "A descrição precisa ter no minimo 50 caracteres e no máximo 350 ")
	private String descrição;

	@Column(name = "data_lancamento") // trabalhando com datas
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLancamento;

	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
