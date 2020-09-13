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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.model.dto.PedidoDTO;
import br.org.serratec.backend.ecommerce.model.dto.PedidoSimplesDTO;
import br.org.serratec.backend.ecommerce.service.PedidoService;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping ("/pedido")

public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@ApiOperation("Permite incluir um pedido")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<PedidoDTO> inserir(@Valid @RequestBody PedidoDTO pedido) {
		PedidoDTO pedidoDTO = pedidoService.inserir(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(pedidoDTO.getId())
          .toUri();
		return ResponseEntity.created(uri).body(pedidoDTO);
	}

	@ApiOperation("Retorna uma lista de pedidos")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<PedidoSimplesDTO>> listar() {
		return ResponseEntity.ok(pedidoService.listar());
	}

	@ApiOperation("Retorna um único pedido")
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<PedidoDTO> listarPorId(@PathVariable Integer id) throws DataNotFoundException {
		PedidoDTO pedido = pedidoService.listarPorId(id);
		return ResponseEntity.ok(pedido);
	}

	@ApiOperation("E")
	@ResponseStatus(code=HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws DataNotFoundException {
		pedidoService.deletar(id);
	}
	

}
