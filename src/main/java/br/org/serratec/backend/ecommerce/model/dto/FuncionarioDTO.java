package br.org.serratec.backend.ecommerce.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class FuncionarioDTO {

	private Integer id;

	@NotNull
	@Size(min = 5, max = 60)
	private String nome;

	@NotNull
	@Size(min = 11, max = 11)
	@CPF
	private String cpf;


}
