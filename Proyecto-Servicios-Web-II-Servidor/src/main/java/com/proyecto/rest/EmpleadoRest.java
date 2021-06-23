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

import com.proyecto.entity.Empleado;
import com.proyecto.service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
public class EmpleadoRest {

	@Autowired
	private EmpleadoService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<Empleado> lista() {
		return servico.listar();
	}

	@GetMapping("/consultaEmpleado/{idEmpleado}")
	@ResponseBody
	public Empleado listarEmpleadosId(@PathVariable("idEmpleado") int idEmpleado) {
		return servico.listarEmpleadosId(idEmpleado);
	}

	@PostMapping("/registrar")
	public void registrar(@RequestBody Empleado bean) {
		servico.registrar(bean);
	}

	@PutMapping("/modificar")
	public void modificar(@RequestBody Empleado bean) {
		servico.modificar(bean);
	}

	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int cod) {
		servico.eliminar(cod);
	}

}
