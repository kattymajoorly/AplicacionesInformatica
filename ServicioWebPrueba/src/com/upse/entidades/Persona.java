package com.upse.entidades;

public class Persona {
	
	private int idPersona;
	private String nombres;
	private String apellidos;
	private String cedula;
	private String email;
	private String direccion;
	private String telefono;
	private String estado;
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(int idPersona, String nombres, String apellidos,
			String cedula, String email, String direccion, String telefono,
			String estado) {
		super();
		this.idPersona = idPersona;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estado = estado;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	public String toJsonCDPersona() {
		return "{'idPersona':" + idPersona +",'nombres':'" + nombres + ", 'apellidos':'" + apellidos + ", 'cedula':'" + cedula + "'}";
	}
	
	

}
