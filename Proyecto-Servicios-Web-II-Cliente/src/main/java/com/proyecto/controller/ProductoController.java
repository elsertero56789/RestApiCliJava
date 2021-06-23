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
import com.proyecto.entity.Categoria;
import com.proyecto.entity.Producto;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	private static final String URL_PRODUCTO = "http://localhost:8091/producto/";
	private static final String URL_CATEGORIA = "http://localhost:8091/categoria/";

	@RequestMapping("/mantenimiento")
	public String index(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(true);
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Producto[]> response = rt.getForEntity(URL_PRODUCTO + "lista", Producto[].class);
			ResponseEntity<Categoria[]> response1 = rt.getForEntity(URL_CATEGORIA + "lista", Categoria[].class);
			model.addAttribute("productos", response.getBody());
			model.addAttribute("categorias", response1.getBody());
			model.addAttribute("cargo", session.getAttribute("CARGO"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mantenimientoProductos";
	}

	@RequestMapping("/guardar")
	public String guardar(@RequestParam("descripcion") String descripcion, @RequestParam("categoria") int idCategoria,
			@RequestParam("precio") double precio, @RequestParam("stockAct") int stockAct,
			@RequestParam("stockMin") int stockMin, RedirectAttributes redirect) {
		try {
			Producto bean = new Producto();
			bean.setDescripcion(descripcion);
			bean.setPrecio(precio);
			bean.setStockAct(stockAct);
			bean.setStockMin(stockMin);

			Categoria categoria = new Categoria();
			categoria.setIdCategoria(idCategoria);
			bean.setCategoria(categoria);

			Gson gson = new Gson();
			String json = gson.toJson(bean);

			RestTemplate rs = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, header);
			rs.postForObject(URL_PRODUCTO + "registrar", request, String.class);

			redirect.addFlashAttribute("MENSAJE", "Producto registrado");
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en el registro");
		}
		return "redirect:/producto/mantenimiento";
	}

	@RequestMapping("/editar")
	public String editar(@RequestParam("idProducto") int idProducto, @RequestParam("descripcion") String descripcion,
			@RequestParam("categoria") int idCategoria, @RequestParam("precio") double precio,
			@RequestParam("stockAct") int stockAct, @RequestParam("stockMin") int stockMin,
			RedirectAttributes redirect) {
		try {
			Producto bean = new Producto();
			bean.setIdProducto(idProducto);
			bean.setDescripcion(descripcion);
			bean.setPrecio(precio);
			bean.setStockAct(stockAct);
			bean.setStockMin(stockMin);

			Categoria categoria = new Categoria();
			categoria.setIdCategoria(idCategoria);
			bean.setCategoria(categoria);

			Gson gson = new Gson();
			String json = gson.toJson(bean);

			RestTemplate rs = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<String>(json, header);
			rs.put(URL_PRODUCTO + "modificar", request, String.class);

			redirect.addFlashAttribute("MENSAJE", "Producto modificado");
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en la modificación");
		}
		return "redirect:/producto/mantenimiento";
	}

	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") int cod, RedirectAttributes redirect) {
		try {
			RestTemplate rt = new RestTemplate();
			rt.delete(URL_PRODUCTO + "eliminar/" + cod);
			redirect.addFlashAttribute("MENSAJE", "Producto eliminado");
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("MENSAJE", "Error en la eliminación");
		}
		return "redirect:/producto/mantenimiento";
	}

	@RequestMapping("/listado")
	public String listado(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(true);
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Producto[]> response = rt.getForEntity(URL_PRODUCTO + "lista", Producto[].class);
			model.addAttribute("productos", response.getBody());
			model.addAttribute("cargo", session.getAttribute("CARGO"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listadoProductos";
	}

	@RequestMapping("/buscarProducto")
	@ResponseBody
	public Producto buscarProducto(@RequestParam("idProducto") int idProducto) {
		Producto data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Producto> response = rt.getForEntity(URL_PRODUCTO + "consultaProducto/" + idProducto,
					Producto.class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping("/consultaPrecio")
	@ResponseBody
	public Producto[] consultaPrecio(@RequestParam("precioDesde") double precioDesde,
			@RequestParam("precioHasta") double precioHasta) {
		Producto[] data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Producto[]> response = rt
					.getForEntity(URL_PRODUCTO + "consultaPrecio/" + precioDesde + "/" + precioHasta, Producto[].class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@RequestMapping("/consultaDescripcion")
	@ResponseBody
	public Producto[] consultaDescripcion(@RequestParam("descripcion") String descripcion) {
		Producto[] data = null;
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<Producto[]> response = rt.getForEntity(URL_PRODUCTO + "consultaDescripcion/" + descripcion,
					Producto[].class);
			data = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
