package com.upse.servicio;

public class DetallePedido {
	private int idDetallePedido;
	private int idProductos;
	private int idPedidos;
	private int cantidad;
	private double subtotal;
	private double precio;
	private String nombre_producto;

	public DetallePedido(int idDetallePedido, int idProductos, int idPedidos,
			int cantidad, double subtotal, double precio, String nombre_producto) {
		super();
		this.idDetallePedido = idDetallePedido;
		this.idProductos = idProductos;
		this.idPedidos = idPedidos;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.precio = precio;
		this.nombre_producto = nombre_producto;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public DetallePedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdDetallePedido() {
		return idDetallePedido;
	}
	public void setIdDetallePedido(int idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}
	public int getIdProductos() {
		return idProductos;
	}
	public void setIdProductos(int idProductos) {
		this.idProductos = idProductos;
	}
	public int getIdPedidos() {
		return idPedidos;
	}
	public void setIdPedidos(int idPedidos) {
		this.idPedidos = idPedidos;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	 
		
	public String toJsonCDDetallePedido() {
		return "{'idDetallePedido':" + idDetallePedido + ",'idProductos':" + idProductos + 
				",'idPedidos':" + idPedidos + ", 'cantidad':" + cantidad + ",'subtotal':" + subtotal + 
				",'precio':" + precio + ", 'nombre_producto':'"+nombre_producto+"'}";
	}
	
	
	
}
