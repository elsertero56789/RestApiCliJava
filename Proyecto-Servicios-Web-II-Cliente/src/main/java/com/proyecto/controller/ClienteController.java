package com.proyecto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.proyecto.entity.Cargo;
import com.proyecto.entity.Cliente;
import com.proyecto.entity.Usuario;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private static final String URL_CLIENTE = "http://localhost:8091/cliente/";

	@RequestMapping("/mantenimiento")
	public String index(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(true);
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Cliente[]> response = rt.getForEntity(URL_CLIENTE + "lista", Cliente[].class);
			model.addAttribute("clientes", response.getBody());
			model.addAttribute("cargo", session.getAttribute("CARGO"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mantenimientoClientes";
	}

	@RequestMapping("/guardar")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
			@RequestParam("dni") String dni, @RequestParam("telefono") String telefono,
			@RequestParam("direccion") String direccion, @RequestParam("email") String email,
			@RequestParam("usuario.usuario") String nombreUsuario,
			@RequestParam("usuario.contrasenia") String contraseniaUsuario, RedirectAttributes redirect,
			HttpServletRequest reques) {
		try {
			HttpSession session = reques.getSession(true);

			Cliente bean = new Cliente();
			bean.setNombre(nombre);
			bean.setApellido(apellido);
			bean.setDni(dni);
			bean.setTelefono(telefono);
			bean.setDireccion(direccion);
			bean.setEmail(email);

			Usuario usuario = new Usuario();
			usuario.setUsuario(nombreUsuario);
			usuario.setContrasenia(contraseniaUsuario);

			Cargo cargo = new Cargo();
			cargo.setIdCargo(2);
			usuario.setCargo(cargo);

			bean.setUsuario(usuario);
			Gson gson = new Gson();
			String json = gson.toJson(bean);

			RestTemplate rs = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, header);
			rs.postForObject(URL_CLIENTE + "registrar", request, String.class);

			redirect.addFlashAttribute("MENSAJE", "Cliente registrado");

			if (session.getAttribute("CARGO") == null) {
				return "redirect:/producto/listado";
			} else if (session.getAttribute("CARGO").toString().equals("Adminsitrador")) {
				return "redirect:/cliente/mantenimiento";
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en el registro");
		}
		return "redirect:/cliente/mantenimiento";
	}

	@RequestMapping("/editar")
	public String editar(@RequestParam("idCliente") int idCliente, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido, @RequestParam("dni") String dni,
			@RequestParam("telefono") String telefono, @RequestParam("direccion") String direccion,
			@RequestParam("email") String email, RedirectAttributes redirect) {
		try {
			Cliente bean = new Cliente();
			bean.setCodigo(idCliente);
			bean.setNombre(nombre);
			bean.setApellido(apellido);
			bean.setDni(dni);
			bean.setTelefono(telefono);
			bean.setDireccion(direccion);
			bean.setEmail(email);

			RestTemplate rt = new RestTemplate();
			ResponseEntity<Cliente> response = rt.getForEntity(URL_CLIENTE + "consultaCliente/" + idCliente,
					Cliente.class);
			bean.setUsuario(response.getBody().getUsuario());

			Gson gson = new Gson();
			String json = gson.toJson(bean);

			RestTemplate rs = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, header);
			rs.put(URL_CLIENTE + "modificar", request, String.class);

			redirect.addFlashAttribute("MENSAJE", "Cliente modificado");

		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en la modificación");
		}
		return "redirect:/cliente/mantenimiento";
	}

	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") int cod, RedirectAttributes redirect) {
		try {
			RestTemplate rt = new RestTemplate();
			rt.delete(URL_CLIENTE + "eliminar/" + cod);
			redirect.addFlashAttribute("MENSAJE", "Cliente eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminación");
		}
		return "redirect:/cliente/mantenimiento";
	}

	@RequestMapping("/buscarCliente")
	@ResponseBody
	public Cliente buscarCliente(@RequestParam("idCliente") int idCliente) {
		Cliente data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Cliente> response = rt.getForEntity(URL_CLIENTE + "consultaCliente/" + idCliente,
					Cliente.class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
