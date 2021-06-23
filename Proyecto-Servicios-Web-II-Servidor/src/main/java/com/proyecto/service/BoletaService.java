package com.proyecto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Boleta;
import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.Producto;
import com.proyecto.repository.BoletaDAO;
import com.proyecto.repository.DetalleBoletaDAO;
import com.proyecto.repository.ProductoDAO;

@Service
public class BoletaService {

	@Autowired
	private BoletaDAO dao;

	@Autowired
	private DetalleBoletaDAO daoDetalle;

	@Autowired
	private ProductoDAO daoProducto;

	public List<Boleta> listar() {
		return dao.findAll();
	}

	public List<Boleta> listarBoletasCliente(int idCliente) {
		return dao.listarBoletasCliente(idCliente);
	}

	@Transactional
	public Boleta registrar(Boleta bean) {
		Boleta boleta = dao.save(bean);
		for (DetalleBoleta d : boleta.getDetallesBoleta()) {
			d.setBoleta(boleta);
			daoDetalle.save(d);
			Producto producto = daoProducto.findById(d.getProducto().getIdProducto()).get();
			producto.setStockAct(producto.getStockAct() - d.getCantidad());
			daoProducto.save(producto);
		}
		return boleta;
	}

	public void modificar(Boleta bean) {
		dao.save(bean);
	}

	public void eliminar(int cod) {
		dao.deleteById(cod);
	}
}
