package com.proyecto.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "boleta")
public class Boleta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idboleta")
	private int idBoleta;

	@Column(name = "numero")
	private String numero;

	@ManyToOne
	@JoinColumn(name = "idcliente")
	private Cliente cliente;

	@Column(name = "fecha")
	private String fecha;

	@Column(name = "monto")
	private Double monto;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "boleta")
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
