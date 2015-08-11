package com.upse.servicio;

public class Estados {
	
	private int idEstado;
	private String estados;
	
	
	public Estados() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Estados(int idEstado, String estados) {
		super();
		this.idEstado = idEstado;
		this.estados = estados;
	}


	public int getIdEstado() {
		return idEstado;
	}


	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}


	public String getEstados() {
		return estados;
	}


	public void setEstados(String estados) {
		this.estados = estados;
	}
	
	

}
