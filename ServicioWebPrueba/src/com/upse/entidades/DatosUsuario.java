package com.upse.entidades;

public class DatosUsuario {
	
	private int idDatosUsuario;
	private int idUsuario;
	private String alias;
	private String password;
	private String estado;
	private Usuario usuario;
	
	public DatosUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosUsuario(int idDatosUsuario, int idUsuario, String alias,
			String password, String estado, Usuario usuario) {
		super();
		this.idDatosUsuario = idDatosUsuario;
		this.idUsuario = idUsuario;
		this.alias = alias;
		this.password = password;
		this.estado = estado;
		this.usuario = usuario;
	}

	public int getIdDatosUsuario() {
		return idDatosUsuario;
	}

	public void setIdDatosUsuario(int idDatosUsuario) {
		this.idDatosUsuario = idDatosUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
