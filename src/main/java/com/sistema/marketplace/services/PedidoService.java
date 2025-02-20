package com.sistema.marketplace.services;

import java.util.List;
import java.util.Optional;

import com.sistema.marketplace.entities.Pedido;
import com.sistema.marketplace.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repositorio;

	public List<Pedido> buscarTodos() {
		return repositorio.findAll();
	}

	public Pedido buscarPorId(Long id) {
		Optional<Pedido> obj = repositorio.findById(id);
		return obj.get();
	}
}
