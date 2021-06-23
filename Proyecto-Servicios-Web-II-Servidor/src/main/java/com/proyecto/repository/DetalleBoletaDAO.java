package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.entity.DetalleBoleta;

public interface DetalleBoletaDAO extends JpaRepository<DetalleBoleta, Integer> {

	@Query("select db from DetalleBoleta db where db.boleta.idBoleta = :param_boleta")
	public List<DetalleBoleta> listarDetalleBoleta(@Param("param_boleta") int idBoleta);
}
