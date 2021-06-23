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

import com.proyecto.entity.Usuario;
import com.proyecto.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

	@Autowired
	private UsuarioService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<Usuario> lista() {
		return servico.listar();
	}

	@PostMapping("/registrar")
	public void registrar(@RequestBody Usuario bean) {
		servico.registrar(bean);
	}

	@PutMapping("/modificar")
	public void modificar(@RequestBody Usuario bean) {
		servico.modificar(bean);
	}

	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int cod) {
		servico.eliminar(cod);
	}

	@GetMapping("/consultaUsuario/{usuario}")
	@ResponseBody
	public Usuario buscarUsuario(@PathVariable("usuario") String usuario) {
		return servico.buscarUsuario(usuario);
	}

}
