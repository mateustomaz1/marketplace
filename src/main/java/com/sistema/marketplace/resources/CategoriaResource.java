package com.sistema.marketplace.resources;

import java.util.List;

import com.sistema.marketplace.entities.Categoria;
import com.sistema.marketplace.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servico;

	@GetMapping
	public ResponseEntity<List<Categoria>> buscarTodas() {
		List<Categoria> lista = servico.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
		Categoria obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
}
