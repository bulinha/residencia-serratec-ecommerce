package br.org.serratec.backend.ecommerce.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.model.dto.CategoriaDTO;
import br.org.serratec.backend.ecommerce.service.CategoriaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@ApiOperation("Permite inserir uma categoria")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<CategoriaDTO> inserir(@Valid @RequestBody CategoriaDTO categoria) {
		CategoriaDTO categoriaDTO = categoriaService.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(categoriaDTO.getId())
          .toUri();
		return ResponseEntity.created(uri).body(categoriaDTO);
	}

	@ApiOperation("Retorna uma lista de categoria")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<CategoriaDTO>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}

	@ApiOperation("Retorma uma única categoria")
	@GetMapping(path="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<CategoriaDTO> listarPorId(@PathVariable Integer id) throws DataNotFoundException {
		CategoriaDTO categoriaDTO = categoriaService.listarPorId(id);
		return ResponseEntity.ok(categoriaDTO);
	}

	@ApiOperation("Permite alterar uma categoria")
	@PutMapping(path="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<CategoriaDTO> substituir(@PathVariable Integer id,@Valid @RequestBody(required = true) CategoriaDTO categoria)
			throws DataNotFoundException {
		CategoriaDTO categoriaDTO = categoriaService.substituir(id, categoria);
		return ResponseEntity.ok(categoriaDTO);
	}

	@ApiOperation("Permite excluir uma categoria")
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws DataNotFoundException {
		categoriaService.deletar(id);
	}

}