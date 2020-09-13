package br.org.serratec.backend.ecommerce.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import br.org.serratec.backend.ecommerce.model.dto.FuncionarioDTO;
import br.org.serratec.backend.ecommerce.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/funcionario")

public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@ApiOperation("Permite inserir um funcionário")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<FuncionarioDTO> inserir(@Valid @RequestBody FuncionarioDTO funcionario) {
		FuncionarioDTO funcionarioDTO = funcionarioService.inserir(funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(funcionarioDTO.getId())
          .toUri();
		return ResponseEntity.created(uri).body(funcionarioDTO);
	}

	@ApiOperation("Retorna todos os clientes")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<FuncionarioDTO>> listar() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(funcionarioService.listar(), headers, HttpStatus.OK);
	}

	@ApiOperation("Retorna um único cliente")
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<FuncionarioDTO> listarPorId(@PathVariable Integer id) throws DataNotFoundException {
		FuncionarioDTO funcionario = funcionarioService.listarPorId(id);
		return ResponseEntity.ok(funcionario);
	}

	@ApiOperation("Permite alterar um cliente e seu endereço")
	@PutMapping(path="/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<FuncionarioDTO> substituir(@PathVariable Integer id, @RequestBody(required = true) FuncionarioDTO funcionario)
			throws DataNotFoundException {
		FuncionarioDTO funcionarioDTO =  funcionarioService.substituir(id, funcionario);
		return ResponseEntity.ok(funcionarioDTO);
	}

	
	@ApiOperation("Permite excluir um cliente")
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws DataNotFoundException {
		funcionarioService.deletar(id);
	}

}