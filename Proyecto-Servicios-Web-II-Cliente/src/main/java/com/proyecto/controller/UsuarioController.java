    package com.proyecto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.entity.Cliente;
import com.proyecto.entity.Usuario;

@Controller
@RequestMapping("/")
public class UsuarioController {

	private static final String URL_USUARIO = "http://localhost:8091/usuario/";
	private static final String URL_CLIENTE = "http://localhost:8091/cliente/";

	@RequestMapping("login")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("CARGO") == null) {
			return "login";
		} else if (session.getAttribute("CARGO").toString().equals("Administrador")) {
			return "redirect:/producto/mantenimiento";
		} else if (session.getAttribute("CARGO").toString().equals("Cliente")) {
			return "redirect:/producto/listado";
		} else if (session.getAttribute("CARGO").toString().equals("Empleado")) {
			return "redirect:/producto/listado";
		}
		return "login";
	}

	@RequestMapping("usuario/registro")
	public String registroUsuario(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("CARGO") == null) {
			return "registroCliente";
		} else if (session.getAttribute("CARGO").toString().equals("Administrador")) {
			return "redirect:/cliente/mantenimiento";
		} else if (session.getAttribute("CARGO").toString().equals("Cliente")) {
			return "redirect:/producto/listado";
		} else if (session.getAttribute("CARGO").toString().equals("Empleado")) {
			return "redirect:/producto/listado";
		}
		return "registroCliente";
	}

	@RequestMapping("usuario/consulta")
	public String consulta(@RequestParam("usuario") String usuario, @RequestParam("contrasenia") String contrasenia,
			RedirectAttributes redirect, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(true);
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Usuario> response = rt.getForEntity(URL_USUARIO + "consultaUsuario/" + usuario,
					Usuario.class);
			ResponseEntity<Cliente> response1 = rt
					.getForEntity(URL_CLIENTE + "consultaUsuario/" + response.getBody().getIdUsuario(), Cliente.class);
			if (response.getBody().getUsuario().equals(usuario)
					&& response.getBody().getContrasenia().equals(contrasenia)) {
				session.setAttribute("CARGO", response.getBody().getCargo().getNombre());
				session.setAttribute("CLIENTE", response1.getBody().getCodigo());
				if (response.getBody().getCargo().getNombre().equals("Administrador")) {
					return "redirect:/producto/mantenimiento";
				} else if (response.getBody().getCargo().getNombre().equals("Cliente")) {
					return "redirect:/producto/listado";
				} else if (response.getBody().getCargo().getNombre().equals("Empleado")) {
					return "redirect:/producto/listado";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en el registro de la Boleta");
		}
		return "redirect:/login/";
	}

	@RequestMapping("salir")
	public String salir(HttpServletRequest request, RedirectAttributes redirect) {
		try {
			HttpSession session = request.getSession(true);
			session.removeAttribute("CARGO");
			return "redirect:/producto/listado";
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en el registro de la Boleta");
		}
		return "redirect:/producto/listado";
	}
}
