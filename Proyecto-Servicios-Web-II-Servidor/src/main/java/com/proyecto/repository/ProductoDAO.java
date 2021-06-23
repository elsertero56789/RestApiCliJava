package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entity.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Integer> {

	public abstract List<Producto> findByDescripcionLike(String descripcion);

	public abstract List<Producto> findByPrecioBetween(double precioDesde, double precioHasta);
}
