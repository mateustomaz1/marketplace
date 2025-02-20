package com.sistema.marketplace.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.marketplace.entities.Produto;
import com.sistema.marketplace.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService servico;

	@GetMapping
	public ResponseEntity<List<Produto>> buscarTodos() {
		List<Produto> lista = servico.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		Produto obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
}
