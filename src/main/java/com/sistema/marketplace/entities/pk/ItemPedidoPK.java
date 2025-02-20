package com.sistema.marketplace.entities.pk;

import java.io.Serializable;

import com.sistema.marketplace.entities.Pedido;
import com.sistema.marketplace.entities.Produto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((pedido == null) ? 0 : pedido.hashCode());
		resultado = primo * resultado + ((produto == null) ? 0 : produto.hashCode());
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK outro = (ItemPedidoPK) obj;
		if (pedido == null) {
			if (outro.pedido != null)
				return false;
		} else if (!pedido.equals(outro.pedido))
			return false;
		if (produto == null) {
			if (outro.produto != null)
				return false;
		} else if (!produto.equals(outro.produto))
			return false;
		return true;
	}
}
