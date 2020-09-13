package br.org.serratec.backend.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 5, max = 60)
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@NotNull
	@Size(min = 5, max = 200)
	@Column(name = "descricao", nullable = false, length = 200)
	private String descricao;

	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Produto> produto = new ArrayList<>();

}
