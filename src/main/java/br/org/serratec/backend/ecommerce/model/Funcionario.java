package br.org.serratec.backend.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Entity
@Table(name = "funcionario")
@Data
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 5, max = 60)
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@NotNull
	@Size(min = 11, max = 11)
	@CPF
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;

	@OneToMany(targetEntity = Produto.class, mappedBy = "funcionario")
	private List<Produto> produto;

}
