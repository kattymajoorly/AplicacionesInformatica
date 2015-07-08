package com.upse.entidades;

public class Productos {
	
	private int idProductos;
	private int idCategoria;
	private String nombre_producto;
	private String descripcion;
	private int stock;
	private double precio;
	private long imagen;
	private String estado;
	private Categoria categoria;
	
	
	public Productos() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Productos(int idProductos, int idCategoria, String nombre_producto,
			String descripcion, int stock,
			double precio, long imagen, String estado, Categoria categoria) {
		super();
		this.idProductos = idProductos;
		this.idCategoria = idCategoria;
		this.nombre_producto = nombre_producto;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
		this.imagen = imagen;
		this.estado = estado;
		this.categoria = categoria;
	}


	public int getIdProductos() {
		return idProductos;
	}


	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}


	public int getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNombre_producto() {
		return nombre_producto;
	}


	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public long getImagen() {
		return imagen;
	}


	public void setImagen(long imagen) {
		this.imagen = imagen;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String toJsonCDProducto() {
		return "{'idProductos=':" + idProductos + ", 'categoria':" + categoria.toJsonCDProducto()
				+ ", 'nombre_producto':'" + nombre_producto 
			    + ", 'stock':" + stock + ", 'precio':" + precio + ", 'imagen':'" + imagen + "}";
	}
	
	
	


	
	
	
	
	

}
