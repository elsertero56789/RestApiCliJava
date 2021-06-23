package com.proyecto.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalleboleta")
public class DetalleBoleta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetalleboleta")
	private int idDetalleBoleta;

	@ManyToOne
	@JoinColumn(name = "idproducto")
	private Producto producto;

	@Column(name = "cantidad")
	private int cantidad;

	@Column(name = "precio")
	private double precio;

	@ManyToOne
	@JoinColumn(name = "idboleta")
	private Boleta boleta;

	public int getIdDetalleBoleta() {
		return idDetalleBoleta;
	}

	public void setIdDetalleBoleta(int idDetalleBoleta) {
		this.idDetalleBoleta = idDetalleBoleta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

}
