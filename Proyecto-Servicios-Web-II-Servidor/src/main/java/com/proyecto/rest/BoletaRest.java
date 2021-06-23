package com.proyecto.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entity.Boleta;
import com.proyecto.service.BoletaService;

@RestController
@RequestMapping("/boleta")
public class BoletaRest {

	@Autowired
	private BoletaService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<Boleta> lista() {
		return servico.listar();
	}

	@PostMapping("/registrar")
	public void registrar(@RequestBody Boleta bean) {
		servico.registrar(bean);
	}

	@PutMapping("/modificar")
	public void modificar(@RequestBody Boleta bean) {
		servico.modificar(bean);
	}

	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int cod) {
		servico.eliminar(cod);
	}

	@GetMapping("/consulta/{cliente}")
	@ResponseBody
	public List<Boleta> listarBoletasCliente(@PathVariable("cliente") int idCliente) {
		return servico.listarBoletasCliente(idCliente);
	}

}
