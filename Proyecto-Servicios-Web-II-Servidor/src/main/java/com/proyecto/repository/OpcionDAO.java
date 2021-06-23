package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entity.Opcion;

public interface OpcionDAO extends JpaRepository<Opcion, Integer> {

	@Query("select o from Opcion o where o.cargo.idCargo = :param_cargo")
	public abstract List<Opcion> listarOpcionesCargo(@Param("param_cargo") int idCargo);
}
