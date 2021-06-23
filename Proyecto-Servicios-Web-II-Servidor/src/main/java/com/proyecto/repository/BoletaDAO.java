package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entity.Boleta;

public interface BoletaDAO extends JpaRepository<Boleta, Integer> {

	@Query("select b from Boleta b where b.cliente.codigo = :param_cliente")
	public abstract List<Boleta> listarBoletasCliente(@Param("param_cliente") int idCliente);
}
