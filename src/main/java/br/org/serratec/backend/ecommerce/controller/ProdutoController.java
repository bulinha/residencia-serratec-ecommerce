package br.org.serratec.backend.ecommerce.controller;

import static java.util.stream.Collectors.toList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.exception.NotAllowedUpdateException;
import br.org.serratec.backend.ecommerce.model.Foto;
import br.org.serratec.backend.ecommerce.model.dto.ProdutoDTO;
import br.org.serratec.backend.ecommerce.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController

@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	
	/**
	 * Adiciona um link para acessar a foto do produto
	 * @param produtoDTO
	 * @return
	 */
	private ProdutoDTO addFotoLink(ProdutoDTO produtoDTO) {
		URI uri = ServletUriComponentsBuilder
			    .fromCurrentRequest()
			    .path("/{id}/foto")
			    .buildAndExpand(produtoDTO.getId())
				.toUri();
		produtoDTO.setFotoLink(uri.toString());
		return produtoDTO;
		
	}
	

	@ApiOperation("Permite inserir um produto")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ProdutoDTO> inserir( @Valid @RequestBody ProdutoDTO produto) throws DataNotFoundException {
		ProdutoDTO produtoDTO = produtoService.inserir(produto);
		URI uri = ServletUriComponentsBuilder
		    .fromCurrentRequest()
		    .path("/{id}")
		    .buildAndExpand(produtoDTO.getId())
			.toUri();
		
		return ResponseEntity.created(uri).body(produtoDTO);
	}

	@ApiOperation("Permite inserir um produto com uma foto")
	@PostMapping(path="comfoto" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ProdutoDTO> inserirComFoto(@RequestParam("file") MultipartFile file, @Valid @RequestPart("produto") ProdutoDTO produto) throws DataNotFoundException {
		ProdutoDTO produtoDTO = produtoService.inserir(produto, file);
		URI uri = ServletUriComponentsBuilder
		    .fromCurrentRequest()
		    .path("/{id}")
		    .buildAndExpand(produtoDTO.getId())
			.toUri();
		
		return ResponseEntity.created(uri).body(produtoDTO);
	}

	@ApiOperation("Retorna uma lista de produtos")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<ProdutoDTO>> listar() {
		List<ProdutoDTO> lista = produtoService.listar();
		lista = lista.stream().map(this::addFotoLink).collect(toList());
		return ResponseEntity.ok(lista);
	}

	@ApiOperation("Retorna um único produto")
	@GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ProdutoDTO> listarProdutoPorId(@PathVariable Integer id) throws DataNotFoundException {
		return ResponseEntity.ok(addFotoLink(produtoService.listarProdutoPorId(id)));

	}
	
	@ApiOperation("Retorna a foto de um produto")
	@GetMapping(path="/{id}/foto")
	public ResponseEntity<byte[]> fotoPorId(@PathVariable Integer id) throws DataNotFoundException {
		Foto foto = produtoService.foto(id);
		HttpHeaders headers = new HttpHeaders();
		if (foto==null) return null;
		headers.add("content-type", foto.getMimetype());
		headers.add("content-length", String.valueOf(foto.getData().length));
		return new ResponseEntity<>(foto.getData(), headers, HttpStatus.OK);
	}


	@ApiOperation("Permite atualizar um pedido")
	@PutMapping(path="/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
		     produces = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Integer id,
			@RequestBody(required = true) ProdutoDTO produto) throws DataNotFoundException, NotAllowedUpdateException {
		return ResponseEntity.ok(produtoService.atualizar(id, produto));
	}

	@ApiOperation("Permite excluir um pedido")
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws DataNotFoundException {
		produtoService.deletar(id);
	}
	

}
