package com.sistema.marketplace.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String urlImagem;

	@ManyToMany
	@JoinTable(name = "tb_produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();

	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();

	public Produto() {
	}

	public Produto(Long id, String nome, String descricao, Double preco, String urlImagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.urlImagem = urlImagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	@JsonIgnore
	public Set<Pedido> getPedidos() {
		Set<Pedido> set = new HashSet<>();
		for (ItemPedido x : itens) {
			set.add(x.getPedido());
		}
		return set;
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((id == null) ? 0 : id.hashCode());
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
		Produto outro = (Produto) obj;
		if (id == null) {
			if (outro.id != null)
				return false;
		} else if (!id.equals(outro.id))
			return false;
		return true;
	}
}
