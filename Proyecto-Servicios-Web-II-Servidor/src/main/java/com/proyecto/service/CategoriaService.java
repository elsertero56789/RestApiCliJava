package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Categoria;
import com.proyecto.repository.CategoriaDAO;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaDAO dao;
	
	public List<Categoria> listar(){
		return dao.findAll();
	}
}
