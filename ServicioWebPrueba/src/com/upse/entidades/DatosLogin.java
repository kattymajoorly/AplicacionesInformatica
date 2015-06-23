package com.upse.entidades;

public class DatosLogin {
	
	private String nombres;
	private String apellidos;
	private String cedula;
	private String telefono;
	private String alias;
	private int id_usuario;
	
	
	public DatosLogin(String nombres, String apellidos, String cedula,
			String telefono, String alias, int id_usuario) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.telefono = telefono;
		this.alias = alias;
		this.id_usuario = id_usuario;
	}


	public DatosLogin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public int getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


}
