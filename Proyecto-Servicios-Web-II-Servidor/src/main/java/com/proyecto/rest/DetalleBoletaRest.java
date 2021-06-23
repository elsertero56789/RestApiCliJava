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

import com.proyecto.entity.DetalleBoleta;
import com.proyecto.service.DetalleBoletaService;

@RestController
@RequestMapping("/detalleboleta")
public class DetalleBoletaRest {

	@Autowired
	private DetalleBoletaService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<DetalleBoleta> lista() {
		return servico.listar();
	}

	@PostMapping("/registrar")
	public void registrar(@RequestBody DetalleBoleta bean) {
		servico.registrar(bean);
	}

	@PutMapping("/modificar")
	public void modificar(@RequestBody DetalleBoleta bean) {
		servico.modificar(bean);
	}

	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int cod) {
		servico.eliminar(cod);
	}

	@GetMapping("/consulta/{boleta}")
	@ResponseBody
	public List<DetalleBoleta> listarDetalleBoleta(@PathVariable("boleta") int idBoleta) {
		return servico.listarDetalleBoleta(idBoleta);
	}

}
