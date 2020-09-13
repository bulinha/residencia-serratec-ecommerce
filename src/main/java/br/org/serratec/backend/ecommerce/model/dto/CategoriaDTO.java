package br.org.serratec.backend.ecommerce.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoriaDTO {

	private Integer id;

	@NotNull
	@Size(min = 5, max = 60)
	private String nome;

	@Size(min = 5, max = 200)
	private String descricao;

}
