package com.proyecto.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entity.Opcion;
import com.proyecto.service.OpcionService;

@RestController
@RequestMapping("/opcion")
public class OpcionRest {

	@Autowired
	private OpcionService servico;

	@GetMapping("/lista/{cargo}")
	@ResponseBody
	public List<Opcion> listarOpcionesCargo(@PathVariable("cargo") int idCargo) {
		return servico.listarOpcionesCargo(idCargo);
	}

}
