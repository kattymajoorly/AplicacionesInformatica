package com.upse.entidades;

public class datosConsultaPedido {
	private int idConsulta;
	private String fecha;
	private double total;
	public datosConsultaPedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public datosConsultaPedido(int idConsulta, String fecha, double total) {
		super();
		this.idConsulta = idConsulta;
		this.fecha = fecha;
		this.total = total;
	}
	public int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public String toJsonCDConsulta() {
		return "{'idConsulta':" + idConsulta + ", 'fecha':'" + fecha + "', 'total':" + total + "}";
	}
	

}
