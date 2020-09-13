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
import br.org.serratec.backend.ecommerce.model.dto.ClienteDTO;
import br.org.serratec.backend.ecommerce.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController

@RequestMapping("/cliente")

public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@ApiOperation("Permite inserir um cliente e seu endereço")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteDTO cliente) {
		ClienteDTO clienteDTO = clienteService.inserir(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(clienteDTO.getId())
          .toUri();
		return ResponseEntity.created(uri).body(clienteDTO);
	}

	@ApiOperation("Retorna a lista de clientes")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@ApiOperation("Retorna um único cliente")
	@GetMapping(path="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ClienteDTO> listarPorId(@PathVariable Integer id) throws DataNotFoundException {
		ClienteDTO clienteDTO = clienteService.listarPorId(id);
		return ResponseEntity.ok(clienteDTO);
	}

	@ApiOperation("Permite alterar um cliente e seu endereço")
	@PutMapping(path="/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ClienteDTO> substituir(@PathVariable Integer id, @RequestBody(required = true) ClienteDTO cliente)
			throws DataNotFoundException {
		return ResponseEntity.ok(clienteService.substituir(id, cliente));
	}

	@ApiOperation("Permite excluir um cliente")
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws DataNotFoundException {
		clienteService.deletar(id);
	}
	
}
