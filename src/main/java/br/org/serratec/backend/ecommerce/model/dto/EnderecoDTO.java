package br.org.serratec.backend.ecommerce.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EnderecoDTO {

	@NotNull
	@Size(min=4, max=60)
	private String rua;

	@NotNull
	@Size(min=1, max=20)
	private String numero;

	@Size(min=0, max=30)
	private String complemento;

	@Size(min=0, max=40)
	private String bairro;

	@NotNull
	@Size(min=3, max=40)
	private String cidade;

	@NotNull
	@Size(min=0, max=2)
	private String estado;

	@NotNull
	private String cep;
	
}