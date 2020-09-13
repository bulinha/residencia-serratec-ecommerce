package br.org.serratec.backend.ecommerce.service;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.exception.NotAllowedUpdateException;
import br.org.serratec.backend.ecommerce.model.Categoria;
import br.org.serratec.backend.ecommerce.model.Foto;
import br.org.serratec.backend.ecommerce.model.Funcionario;
import br.org.serratec.backend.ecommerce.model.Produto;
import br.org.serratec.backend.ecommerce.model.dto.ProdutoDTO;
import br.org.serratec.backend.ecommerce.repository.CategoriaRepository;
import br.org.serratec.backend.ecommerce.repository.FotoRepository;
import br.org.serratec.backend.ecommerce.repository.FuncionarioRepository;
import br.org.serratec.backend.ecommerce.repository.ProdutoRepository;
import br.org.serratec.backend.ecommerce.service.mapper.ProdutoMapper;

@Service

public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FotoRepository fotoRepository;

	@Autowired
	private ProdutoMapper produtoMapper;

	private Produto findBy(Integer id) throws DataNotFoundException {
		Optional<Produto> optionalProduto = produtoRepository.findById(id);

		if (optionalProduto.isPresent()) {
			return optionalProduto.get();
		}
		throw new DataNotFoundException(String.format("Produto com id %d não encontrado", id)) ;		
	}	

	@Transactional
	public ProdutoDTO inserir(ProdutoDTO produtoDTO) throws  DataNotFoundException {
		
		Produto produto = produtoMapper.toEntity(produtoDTO);
		verificaDados(produto);
		Produto produtoSalvoNoBd = produtoRepository.save(produto);
		return produtoMapper.toDTO(produtoSalvoNoBd);

	}
	@Transactional
	public ProdutoDTO inserir(ProdutoDTO produtoDTO, MultipartFile file) throws  DataNotFoundException {

		Produto produto = produtoMapper.toEntity(produtoDTO);
		verificaDados(produto);

		Foto foto = new Foto();
		foto.setMimetype(file.getContentType());
		foto.setNome(file.getName());
		try {
			foto.setData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Foto fotoBanco = fotoRepository.save(foto);
		
		
		produto.setFoto(fotoBanco);
		Produto produtoSalvoNoBd = produtoRepository.save(produto);
		return produtoMapper.toDTO(produtoSalvoNoBd);

	}

	private void verificaDados(Produto produto) throws DataNotFoundException{
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(produto.getCategoria().getId());
		if (!optionalCategoria.isPresent()) {
			throw new DataNotFoundException(String.format("Categoria com id %d não encontrada", produto.getCategoria().getId())) ;		
		} else {
			produto.setCategoria(optionalCategoria.get());
		}
		Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(produto.getFuncionario().getId());
		if (!optionalFuncionario.isPresent()) {
			throw new DataNotFoundException(String.format("Funcionario com id %d não encontrado", produto.getCategoria().getId())) ;		
		}

	}

	public List<ProdutoDTO> listar() {
		return produtoRepository.findAll().stream()
		.map(produtoMapper::toDTO)
		.collect(toList());
	}

	public ProdutoDTO listarProdutoPorId(Integer id) throws DataNotFoundException {
		return produtoMapper.toDTO(findBy(id));
	}

	public ProdutoDTO atualizar(Integer id, @Valid ProdutoDTO produtoDTO) 
			throws DataNotFoundException, NotAllowedUpdateException {
		Produto produtoNoBanco = findBy(id);
		
		if (produtoDTO.getNome() != null) {
			produtoNoBanco.setNome(produtoDTO.getNome());
		}

		if (produtoDTO.getDescricao() != null) {
			produtoNoBanco.setDescricao(produtoDTO.getDescricao());
		}

		if (produtoDTO.getQtdEstoque() != null) {
			produtoNoBanco.setQtdEstoque(produtoDTO.getQtdEstoque());
		}

		if (produtoDTO.getValor() != null) {
			produtoNoBanco.setValor(produtoDTO.getValor());
		}

		if (produtoDTO.getDataFabricacao() != null) {
			produtoNoBanco.setDataFabricacao(produtoDTO.getDataFabricacao());
		}

		if (!produtoDTO.getIdFuncionario().equals(produtoNoBanco.getFuncionario().getId())) {
           throw new NotAllowedUpdateException(
			   String.format(
				   "Somente o funcionario com o id %d pode atualizar o produto com o id %d", 
				   produtoNoBanco.getFuncionario().getId(), 
				   produtoNoBanco.getId()
				   )
		   );
		}

		return produtoMapper.toDTO(produtoRepository.save(produtoNoBanco));
	}

	public void deletar(Integer id) throws DataNotFoundException {
		Produto produtoNoBanco = findBy(id);
		produtoRepository.delete(produtoNoBanco);
	}
	
	public Foto foto(Integer id) throws DataNotFoundException {
		Produto produtoNoBanco = findBy(id);
		return produtoNoBanco.getFoto();
	}


}
