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

import com.proyecto.entity.Producto;
import com.proyecto.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoRest {

	@Autowired
	private ProductoService servico;

	@GetMapping("/lista")
	@ResponseBody
	public List<Producto> lista() {
		return servico.listar();
	}

	@PostMapping("/registrar")
	public void registrar(@RequestBody Producto bean) {
		servico.registrar(bean);
	}

	@PutMapping("/modificar")
	public void modificar(@RequestBody Producto bean) {
		servico.modificar(bean);
	}

	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int cod) {
		servico.eliminar(cod);
	}

	@GetMapping("/consultaProducto/{idProducto}")
	@ResponseBody
	public Producto listarProductosId(@PathVariable("idProducto") int idProducto) {
		return servico.listarProductosId(idProducto);
	}

	@GetMapping("/consultaPrecio/{precioDesde}/{precioHasta}")
	@ResponseBody
	public List<Producto> listarProductosRangoPrecio(@PathVariable("precioDesde") double precioDesde,
			@PathVariable("precioHasta") double precioHasta) {
		return servico.listarProductosRangoPrecio(precioDesde, precioHasta);
	}

	@GetMapping("/consultaDescripcion/{descripcion}")
	@ResponseBody
	public List<Producto> listarProductosNombre(@PathVariable("descripcion") String descripcion) {
		return servico.listarProductosNombre("%" + descripcion + "%");
	}

}
