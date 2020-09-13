package br.org.serratec.backend.ecommerce.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "CLIENTE")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 5, max = 60)
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;

	@NotNull
	@Size(min=6, max = 15)
	@Column(name="login", nullable = false, length=15)
	private String usuario;

	@NotNull
	@Size(min=11,max=11)
	@CPF
	@Column(name="cpf", nullable = false, length=11)
	private String cpf;

	@NotNull
	@Size(min=4,max=30)
	@Email
	@Column(name="email", nullable = false,length=30)
	private String email;

	@Past
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento", nullable = false)
	private Date dataNascimento;


	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Valid
	private Endereco endereco;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "cliente", fetch=FetchType.EAGER)
	private List<Pedido> pedido = new ArrayList<>();

}
