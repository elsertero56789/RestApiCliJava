package com.proyecto.entity;

public class Seleccion {

	private int idProducto;
	private String descripcion;
	private double precio;
	private int cantidad;
	private double totalParcial;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotalParcial() {
		totalParcial = precio * cantidad;
		return totalParcial;
	}

	public void setTotalParcial(double totalParcial) {
		this.totalParcial = totalParcial;
	}

}
