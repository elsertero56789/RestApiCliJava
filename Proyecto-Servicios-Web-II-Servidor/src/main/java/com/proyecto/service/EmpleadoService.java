package com.proyecto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Empleado;
import com.proyecto.repository.EmpleadoDAO;
import com.proyecto.repository.UsuarioDAO;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoDAO dao;

	@Autowired
	private UsuarioDAO daoUsuario;

	public List<Empleado> listar() {
		return dao.findAll();
	}

	public Empleado listarEmpleadosId(int idEmpleado) {
		return dao.findById(idEmpleado).get();
	}

	@Transactional
	public void registrar(Empleado bean) {
		daoUsuario.save(bean.getUsuario());
		dao.save(bean);
	}

	public void modificar(Empleado bean) {
		dao.save(bean);
	}

	public void eliminar(int cod) {
		dao.deleteById(cod);
	}

}
