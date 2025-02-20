package com.sistema.marketplace.repositories;

import com.sistema.marketplace.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
