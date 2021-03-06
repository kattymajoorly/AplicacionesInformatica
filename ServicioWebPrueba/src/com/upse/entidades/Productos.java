package com.upse.entidades;

public class Productos {
	
	private int idProductos;
	private int idCategoria;
	private String nombre_producto;
	private String descripcion;
	private int stock_minimo;
	private int stock_actual;
	private int stockpedido;
	private double precio;
	private long imagen;
	private String pathservicio;
	private String pathimagen;
	private String estado;
	private Categoria categoria;
	                                   
	


	public Productos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Productos(int idProductos, int idCategoria, String nombre_producto,
			String descripcion, int stock_minimo, int stock_actual,
			int stockpedido, double precio, long imagen, String pathservicio,
			String pathimagen, String estado, Categoria categoria) {
		super();
		this.idProductos = idProductos;
		this.idCategoria = idCategoria;
		this.nombre_producto = nombre_producto;
		this.descripcion = descripcion;
		this.stock_minimo = stock_minimo;
		this.stock_actual = stock_actual;
		this.stockpedido = stockpedido;
		this.precio = precio;
		this.imagen = imagen;
		this.pathservicio = pathservicio;
		this.pathimagen = pathimagen;
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

	public int getStock_minimo() {
		return stock_minimo;
	}

	public void setStock_minimo(int stock_minimo) {
		this.stock_minimo = stock_minimo;
	}

	public int getStock_actual() {
		return stock_actual;
	}

	public void setStock_actual(int stock_actual) {
		this.stock_actual = stock_actual;
	}

	public int getStockpedido() {
		return stockpedido;
	}

	public void setStockpedido(int stockpedido) {
		this.stockpedido = stockpedido;
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

	public String getPathservicio() {
		return pathservicio;
	}

	public void setPathservicio(String pathservicio) {
		this.pathservicio = pathservicio;
	}

	public String getPathimagen() {
		return pathimagen;
	}

	public void setPathimagen(String pathimagen) {
		this.pathimagen = pathimagen;
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
		return "{'idProductos':" + idProductos + ", 'categoria':" + categoria.toJsonCDProducto()
				+ ", 'nombre_producto':'" + nombre_producto + "', 'descripcion':'" + descripcion + "', 'stock_minimo':" + stock_minimo + ", 'stock_actual':" 
				+ stock_actual + ",'precio':" + precio + ", 'pathservicio':'" + pathservicio + "','pathimagen':'" + pathimagen + "'}";
	}
	
	public String toJsonCDProductoDet() {
		return "{'idProductos':" + idProductos + ", 'nombre_producto':'" + nombre_producto + "','precio':" + precio + "}";
	}
	
}