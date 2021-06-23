package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Usuario;
import com.proyecto.repository.UsuarioDAO;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO dao;

	public List<Usuario> listar() {
		return dao.findAll();
	}

	public Usuario buscarUsuario(String usuario) {
		return dao.findByUsuario(usuario);
	}

	public void registrar(Usuario bean) {
		dao.save(bean);
	}

	public void modificar(Usuario bean) {
		dao.save(bean);
	}

	public void eliminar(int cod) {
		dao.deleteById(cod);
	}
}
