package br.org.serratec.backend.ecommerce.model.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ClienteDTO {

	private Integer id;

	@NotNull
	@Size(min = 5, max = 60)
	private String nome;

	@NotNull
	@Size(min=6, max = 15)
	private String usuario;

	@NotNull
	@Size(min=11,max=11)
	@CPF
	private String cpf;

	@NotNull
	@Size(min=4,max=30)
	@Email
	private String email;

	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT" )
	private Date dataNascimento;

	@Valid
	private EnderecoDTO endereco;


}
