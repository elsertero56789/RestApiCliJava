package com.proyecto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Cliente;
import com.proyecto.repository.ClienteDAO;
import com.proyecto.repository.UsuarioDAO;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO dao;

	@Autowired
	private UsuarioDAO daoUsuario;

	public List<Cliente> listar() {
		return dao.findAll();
	}

	public Cliente listarClientesId(int idCliente) {
		return dao.findById(idCliente).get();
	}

	public List<Cliente> listarClientesNombre(String nombre) {
		return dao.findByNombreLike(nombre);
	}

	public Cliente buscarClienteUsuario(int idUsuario) {
		return dao.buscarClienteUsuario(idUsuario);
	}

	@Transactional
	public void registrar(Cliente bean) {
		daoUsuario.save(bean.getUsuario());
		dao.save(bean);
	}

	public void modificar(Cliente bean) {
		dao.save(bean);
	}

	public void eliminar(int cod) {
		dao.deleteById(cod);
	}

}
