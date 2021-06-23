package com.proyecto.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entity.Categoria;
import com.proyecto.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaRest {

	@Autowired
	private CategoriaService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<Categoria> lista() {
		return servico.listar();
	}
}
