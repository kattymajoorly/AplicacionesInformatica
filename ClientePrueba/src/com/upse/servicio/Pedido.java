package com.upse.servicio;

import java.util.Date;

public class Pedido {
	private int idPedidos;
	private int idUsuario;
	private String fecha;
	private double subtotal;
	private double total_iva;
	private double total;
	
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(int idPedidos, int idUsuario, String fecha, double subtotal,
			double total_iva, double total) {
		super();
		this.idPedidos = idPedidos;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.total_iva = total_iva;
		this.total = total;
		
	}

	public int getIdPedidos() {
		return idPedidos;
	}

	public void setIdPedidos(int idPedidos) {
		this.idPedidos = idPedidos;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal_iva() {
		return total_iva;
	}

	public void setTotal_iva(double total_iva) {
		this.total_iva = total_iva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

		
}
