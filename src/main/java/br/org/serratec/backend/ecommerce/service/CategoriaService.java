package br.org.serratec.backend.ecommerce.service;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.model.Categoria;
import br.org.serratec.backend.ecommerce.model.dto.CategoriaDTO;
import br.org.serratec.backend.ecommerce.repository.CategoriaRepository;
import br.org.serratec.backend.ecommerce.service.mapper.CategoriaMapper;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaMapper categoriaMapper;

	private Categoria findBy(Integer id) throws DataNotFoundException {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

		if (optionalCategoria.isPresent()) {
			return optionalCategoria.get();
		}
		throw new DataNotFoundException(String.format("Categoria com id %d não encontrada", id)) ;
	}
	

	@Transactional
	public CategoriaDTO inserir(@Valid CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
		Categoria categoriaSalvoNoBd = categoriaRepository.save(categoria);
		return categoriaMapper.toDTO(categoriaSalvoNoBd);

	}

	public List<CategoriaDTO> listar() {
		return categoriaRepository.findAll()
			.stream()
			.map(categoriaMapper::toDTO)
			.collect(toList());
	}


	public CategoriaDTO listarPorId(Integer id) throws DataNotFoundException {
		return categoriaMapper.toDTO(findBy(id));
	}

	@Transactional
	public CategoriaDTO substituir(Integer id, @Valid CategoriaDTO categoriaDTO) throws DataNotFoundException {
		Categoria categoriaNoBanco = findBy(id);

		if (categoriaDTO.getNome() != null) {
			categoriaNoBanco.setNome(categoriaDTO.getNome());
		}

		if (categoriaDTO.getDescricao() != null) {
			categoriaNoBanco.setDescricao(categoriaDTO.getDescricao());
		}
		return categoriaMapper.toDTO(categoriaRepository.save(categoriaNoBanco));
	}

	@Transactional
	public void deletar(Integer id) throws DataNotFoundException {
		Categoria categoriaNoBanco = findBy(id);
		categoriaRepository.delete(categoriaNoBanco);
	}

}