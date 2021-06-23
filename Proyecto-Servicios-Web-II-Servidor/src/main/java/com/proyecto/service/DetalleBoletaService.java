package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.DetalleBoleta;
import com.proyecto.repository.DetalleBoletaDAO;

@Service
public class DetalleBoletaService {

	@Autowired
	private DetalleBoletaDAO dao;

	public List<DetalleBoleta> listar() {
		return dao.findAll();
	}

	public List<DetalleBoleta> listarDetalleBoleta(int idBoleta) {
		return dao.listarDetalleBoleta(idBoleta);
	}

	public void registrar(DetalleBoleta bean) {
		dao.save(bean);
	}

	public void modificar(DetalleBoleta bean) {
		dao.save(bean);
	}

	public void eliminar(int cod) {
		dao.deleteById(cod);
	}

}
