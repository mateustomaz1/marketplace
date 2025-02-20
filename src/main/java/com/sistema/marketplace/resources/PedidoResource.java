package com.sistema.marketplace.resources;

import java.util.List;

import com.sistema.marketplace.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.marketplace.entities.Pedido;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService servico;

	@GetMapping
	public ResponseEntity<List<Pedido>> buscarTodos() {
		List<Pedido> lista = servico.buscarTodos();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		Pedido obj = servico.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
}
