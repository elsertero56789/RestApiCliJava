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

import com.proyecto.entity.Cliente;
import com.proyecto.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {

	@Autowired
	private ClienteService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<Cliente> lista() {
		return servico.listar();
	}

	@GetMapping("/consultaCliente/{idCliente}")
	@ResponseBody
	public Cliente listarClientesId(@PathVariable("idCliente") int idCliente) {
		return servico.listarClientesId(idCliente);
	}

	@GetMapping("/consulta/{nombre}")
	@ResponseBody
	public List<Cliente> listarClientesNombre(@PathVariable("nombre") String nombre) {
		return servico.listarClientesNombre(nombre);
	}

	@GetMapping("/consultaUsuario/{idUsuario}")
	@ResponseBody
	public Cliente listarClienteUsuario(@PathVariable("idUsuario") int idUsuario) {
		return servico.buscarClienteUsuario(idUsuario);
	}

	@PostMapping("/registrar")
	public void registrar(@RequestBody Cliente bean) {
		servico.registrar(bean);
	}

	@PutMapping("/modificar")
	public void modificar(@RequestBody Cliente bean) {
		servico.modificar(bean);
	}

	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int cod) {
		servico.eliminar(cod);
	}

}
