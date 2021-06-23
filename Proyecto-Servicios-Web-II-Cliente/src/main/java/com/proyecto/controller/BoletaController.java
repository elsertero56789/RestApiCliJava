package com.proyecto.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.proyecto.entity.Boleta;
import com.proyecto.entity.Cliente;
import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.Producto;
import com.proyecto.entity.Seleccion;

@Controller
@RequestMapping("/boleta")
public class BoletaController {

	private static final String URL_BOLETA = "http://localhost:8091/boleta/";
	private static final String URL_DETALLEBOLETA = "http://localhost:8091/detalleboleta/";
	private List<Seleccion> seleccionados = new ArrayList<Seleccion>();

	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		double total = 0;
		model.addAttribute("seleccionados", seleccionados);
		model.addAttribute("cargo", session.getAttribute("CARGO"));
		model.addAttribute("fecha", LocalDateTime.now().toString().split("T")[0]);
		model.addAttribute("cliente", session.getAttribute("CLIENTE"));
		model.addAttribute("numero", "111111");
		for (Seleccion x : seleccionados) {
			total += x.getPrecio() * x.getCantidad();
		}
		model.addAttribute("monto", total);
		return "boleta";
	}

	@RequestMapping("/pedidos")
	public String pedidos(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("CARGO") == null && session.getAttribute("CLIENTE") == null) {
			return "redirect:/producto/listado";
		} else {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Boleta[]> response = rt.getForEntity(
					URL_BOLETA + "consulta/" + session.getAttribute("CLIENTE").toString(), Boleta[].class);
			model.addAttribute("cargo", session.getAttribute("CARGO"));
			model.addAttribute("pedidos", response.getBody());
			return "pedidos";
		}
	}

	@RequestMapping("/detallePedidos")
	@ResponseBody
	public DetalleBoleta[] detallePedidos(@RequestParam("idBoleta") int idBoleta) {
		DetalleBoleta[] data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<DetalleBoleta[]> response = rt.getForEntity(URL_DETALLEBOLETA + "consulta/" + idBoleta,
					DetalleBoleta[].class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping("/agregarSeleccion")
	public String agregarSeleccion(@RequestParam("idProducto") int idProducto,
			@RequestParam("descripcion") String descripcion, @RequestParam("precio") double precio,
			RedirectAttributes redirect) {
		for (Seleccion x : seleccionados) {
			if (x.getIdProducto() == idProducto) {
				redirect.addFlashAttribute("MENSAJE", "El Producto ya está agregado al carrito");
				return "redirect:/producto/listado";
			}
		}
		Seleccion seleccion = new Seleccion();
		seleccion.setIdProducto(idProducto);
		seleccion.setDescripcion(descripcion);
		seleccion.setCantidad(1);
		seleccion.setPrecio(precio);
		seleccionados.add(seleccion);
		redirect.addFlashAttribute("MENSAJE", "Producto agregado al carrito");
		return "redirect:/producto/listado";
	}

	@RequestMapping("/eliminaSeleccion")
	public String elimina(@RequestParam("idProducto") int idProducto, RedirectAttributes redirect) {
		for (Seleccion x : seleccionados) {
			if (x.getIdProducto() == idProducto) {
				seleccionados.remove(x);
				redirect.addFlashAttribute("MENSAJE", "Producto eliminado de la Boleta");
				break;
			}
		}
		return "redirect:/boleta/";
	}

	@RequestMapping("/agregarQuitarCantidad")
	@ResponseBody
	public String agregarQuitarCantidad(@RequestParam("idProducto") int idProducto,
			@RequestParam("cantidad") int cantidad) {
		for (Seleccion x : seleccionados) {
			if (x.getIdProducto() == idProducto) {
				x.setCantidad(cantidad);
				break;
			}
		}
		return "SI";
	}

	@RequestMapping("/guardar")
	public String guardar(@RequestParam("numero") String numero, @RequestParam("cliente") int idCliente,
			@RequestParam("fecha") String fecha, @RequestParam("monto") double monto, RedirectAttributes redirect) {
		try {
			if (idCliente != 0 && monto > 0) {
				List<DetalleBoleta> detalles = new ArrayList<DetalleBoleta>();
				for (Seleccion seleccion : seleccionados) {
					DetalleBoleta detalleBoleta = new DetalleBoleta();
					detalleBoleta.setCantidad(seleccion.getCantidad());
					detalleBoleta.setPrecio(seleccion.getPrecio());

					Producto producto = new Producto();
					producto.setIdProducto(seleccion.getIdProducto());

					detalleBoleta.setProducto(producto);
					detalles.add(detalleBoleta);
				}
				Boleta bean = new Boleta();
				bean.setNumero(numero);
				bean.setFecha(fecha);
				bean.setMonto(monto);
				bean.setDetallesBoleta(detalles);

				Cliente cliente = new Cliente();
				cliente.setCodigo(idCliente);
				bean.setCliente(cliente);

				Gson gson = new Gson();
				String json = gson.toJson(bean);

				RestTemplate rs = new RestTemplate();
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> requestBoleta = new HttpEntity<String>(json, header);
				rs.postForObject(URL_BOLETA + "registrar", requestBoleta, String.class);

				redirect.addFlashAttribute("MENSAJE", "Boleta registrada");
				seleccionados.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en el registro de la Boleta");
		}
		return "redirect:/boleta/";
	}

}
