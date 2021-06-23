package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Producto;
import com.proyecto.repository.ProductoDAO;

@Service
public class ProductoService {

	@Autowired
	private ProductoDAO dao;

	public List<Producto> listar() {
		return dao.findAll();
	}

	public List<Producto> listarProductosNombre(String descripcion) {
		return dao.findByDescripcionLike(descripcion);
	}

	public Producto listarProductosId(int idProducto) {
		return dao.findById(idProducto).get();
	}

	public List<Producto> listarProductosRangoPrecio(double precioDesde, double precioHasta) {
		return dao.findByPrecioBetween(precioDesde, precioHasta);
	}

	public void registrar(Producto bean) {
		dao.save(bean);
	}

	public void modificar(Producto bean) {
		dao.save(bean);
	}

	public void eliminar(int cod) {
		dao.deleteById(cod);
	}

}
