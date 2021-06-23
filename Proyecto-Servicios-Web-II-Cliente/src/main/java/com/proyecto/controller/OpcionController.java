package com.proyecto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.proyecto.entity.Opcion;

@Controller
@RequestMapping("/opcion")
public class OpcionController {

	private static final String URL_OPCION = "http://localhost:8091/opcion/";

	@RequestMapping("/listado")
	@ResponseBody
	public Opcion[] listado(@RequestParam("cargo") int idCargo) {
		Opcion[] data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Opcion[]> response = rt.getForEntity(URL_OPCION + "lista/" + idCargo, Opcion[].class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
