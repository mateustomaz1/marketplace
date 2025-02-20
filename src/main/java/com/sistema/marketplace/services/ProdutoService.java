package com.sistema.marketplace.services;

import java.util.List;
import java.util.Optional;

import com.sistema.marketplace.entities.Produto;
import com.sistema.marketplace.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositorio;

	public List<Produto> buscarTodos() {
		return repositorio.findAll();
	}

	public Produto buscarPorId(Long id) {
		Optional<Produto> obj = repositorio.findById(id);
		return obj.get();
	}
}
