package com.sistema.marketplace.services;

import java.util.List;
import java.util.Optional;

import com.sistema.marketplace.entities.Categoria;
import com.sistema.marketplace.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	public List<Categoria> buscarTodos() {
		return repositorio.findAll();
	}

	public Categoria buscarPorId(Long id) {
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.get();
	}
}
