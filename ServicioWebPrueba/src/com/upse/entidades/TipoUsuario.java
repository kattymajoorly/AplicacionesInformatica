package com.upse.entidades;

public class TipoUsuario {
	
	private int idTipoUsuario;
	private String descripcion;
	private String estado;
	
	public TipoUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoUsuario(int idTipoUsuario, String descripcion, String estado) {
		super();
		this.idTipoUsuario = idTipoUsuario;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
