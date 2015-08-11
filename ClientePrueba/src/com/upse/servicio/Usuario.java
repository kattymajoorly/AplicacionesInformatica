package com.upse.servicio;

public class Usuario {
	
	private int idUsuario;
	private int idTipoUsuario;
	private int idPersona;
	private String estado;
	private Persona persona;
	//private TipoUsuario tipousuario;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int idUsuario, int idTipoUsuario, int idPersona,
			String estado, Persona persona) {
		super();
		this.idUsuario = idUsuario;
		this.idTipoUsuario = idTipoUsuario;
		this.idPersona = idPersona;
		this.estado = estado;
		this.persona = persona;
		
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

		
	
	public String toJsonCDUsuario(){
		return "{'idUsuario':" + idUsuario + ", 'persona':" + persona.toJsonCDPersona() + "}"; 
	}
	
	
	
}
