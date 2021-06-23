package com.proyecto.entity;

import java.util.List;

public class Boleta {

	private int idBoleta;
	private String numero;
	private Cliente cliente;
	private String fecha;
	private Double monto;
	private List<DetalleBoleta> detallesBoleta;

	public int getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public List<DetalleBoleta> getDetallesBoleta() {
		return detallesBoleta;
	}

	public void setDetallesBoleta(List<DetalleBoleta> detallesBoleta) {
		this.detallesBoleta = detallesBoleta;
	}

}
