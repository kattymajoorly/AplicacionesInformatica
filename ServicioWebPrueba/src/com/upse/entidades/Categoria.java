package com.upse.entidades;

public class Categoria {
	
	private int idCategoria;
	private String nombre_categoria;
	private String estado;
	
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(int idCategoria, String nombre_categoria, String estado) {
		super();
		this.idCategoria = idCategoria;
		this.nombre_categoria = nombre_categoria;
		this.estado = estado;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre_categoria() {
		return nombre_categoria;
	}

	public void setNombre_categoria(String nombre_categoria) {
		this.nombre_categoria = nombre_categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	public String toJsonCDProducto() {
		return "{'idCategoria':" + idCategoria
				+ "', 'nombre_categoria':'" + nombre_categoria + "'}";
	}

}
