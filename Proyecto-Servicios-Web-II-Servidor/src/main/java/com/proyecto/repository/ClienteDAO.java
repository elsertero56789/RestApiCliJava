package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entity.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

	public abstract List<Cliente> findByNombreLike(String nombre);

	@Query("select c from Cliente c where c.usuario.idUsuario = :param_usuario")
	public abstract Cliente buscarClienteUsuario(@Param("param_usuario") int idUsuario);
}
