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
import com.proyecto.entity.Empleado;
import com.proyecto.entity.Usuario;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

	private static final String URL_EMPLEADO = "http://localhost:8091/empleado/";

	@RequestMapping("/mantenimiento")
	public String index(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(true);
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Empleado[]> response = rt.getForEntity(URL_EMPLEADO + "lista", Empleado[].class);
			model.addAttribute("empleados", response.getBody());
			model.addAttribute("cargo", session.getAttribute("CARGO"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mantenimientoEmpleados";
	}

	@RequestMapping("/guardar")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
			@RequestParam("dni") String dni, @RequestParam("telefono") String telefono,
			@RequestParam("direccion") String direccion, @RequestParam("email") String email,
			@RequestParam("usuario.usuario") String nombreUsuario,
			@RequestParam("usuario.contrasenia") String contraseniaUsuario, RedirectAttributes redirect) {
		try {
			Empleado bean = new Empleado();
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
			cargo.setIdCargo(3);
			usuario.setCargo(cargo);

			bean.setUsuario(usuario);
			Gson gson = new Gson();
			String json = gson.toJson(bean);

			RestTemplate rs = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, header);
			rs.postForObject(URL_EMPLEADO + "registrar", request, String.class);

			redirect.addFlashAttribute("MENSAJE", "Empleado registrado");

		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en el registro");
		}
		return "redirect:/empleado/mantenimiento";
	}

	@RequestMapping("/editar")
	public String editar(@RequestParam("idEmpleado") int idEmpleado, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido, @RequestParam("dni") String dni,
			@RequestParam("telefono") String telefono, @RequestParam("direccion") String direccion,
			@RequestParam("email") String email, RedirectAttributes redirect) {
		try {
			Empleado bean = new Empleado();
			bean.setCodigo(idEmpleado);
			bean.setNombre(nombre);
			bean.setApellido(apellido);
			bean.setDni(dni);
			bean.setTelefono(telefono);
			bean.setDireccion(direccion);
			bean.setEmail(email);

			RestTemplate rt = new RestTemplate();
			ResponseEntity<Empleado> response = rt.getForEntity(URL_EMPLEADO + "consultaEmpleado/" + idEmpleado,
					Empleado.class);
			bean.setUsuario(response.getBody().getUsuario());

			Gson gson = new Gson();
			String json = gson.toJson(bean);

			RestTemplate rs = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, header);
			rs.put(URL_EMPLEADO + "modificar", request, String.class);

			redirect.addFlashAttribute("MENSAJE", "Empleado modificado");

		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en la modificación");
		}
		return "redirect:/empleado/mantenimiento";
	}

	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") int cod, RedirectAttributes redirect) {
		try {
			RestTemplate rt = new RestTemplate();
			rt.delete(URL_EMPLEADO + "eliminar/" + cod);
			redirect.addFlashAttribute("MENSAJE", "Empleado eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminación");
		}
		return "redirect:/empleado/mantenimiento";
	}

	@RequestMapping("/buscarEmpleado")
	@ResponseBody
	public Empleado buscarEmpleado(@RequestParam("idEmpleado") int idEmpleado) {
		Empleado data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Empleado> response = rt.getForEntity(URL_EMPLEADO + "consultaEmpleado/" + idEmpleado,
					Empleado.class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
