package br.org.serratec.backend.ecommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name="produto")
@Data
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=5, max=30)
	@Column(name="nome", nullable=false , length=30)
	private String nome;
	
	@NotNull
	@Size(min=5, max=200)
	@Column(name="descricao", nullable=false , length=200)
	private String descricao;
	
	@NotNull
	@Column(name="qtd_estoque", nullable=false , length=20)
	private Integer qtdEstoque;
	
	@NotNull
	@Column(name="valor", nullable=false , length=10)
	private Double valor;
	
	@Past
	@Column(name="data_fabricacao")
	private Date dataFabricacao;

	@ManyToOne()
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@ManyToOne()
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@OneToMany(mappedBy = "id.produto", fetch = FetchType.LAZY)
	private Set<PedidoItem> itens = new HashSet<>();
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_foto")
	private Foto foto;
	
}
