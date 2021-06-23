package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Opcion;
import com.proyecto.repository.OpcionDAO;

@Service
public class OpcionService {

	@Autowired
	private OpcionDAO dao;

	public List<Opcion> listarOpcionesCargo(int idCargo) {
		return dao.listarOpcionesCargo(idCargo);
	}

}
