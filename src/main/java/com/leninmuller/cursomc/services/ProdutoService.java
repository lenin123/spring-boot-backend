package com.leninmuller.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.leninmuller.cursomc.domain.Categoria;
import com.leninmuller.cursomc.domain.Produto;
import com.leninmuller.cursomc.repositories.CategoriaRepository;
import com.leninmuller.cursomc.repositories.ProdutoRepository;
import com.leninmuller.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Produto find(Integer id) {  
		Optional<Produto> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())); 
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer line, String direction, String orderBy){
		PageRequest pageRequest = new PageRequest(page, line);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
	
}
