package com.upse.entidades;

public class DetallePedido {
	private int idDetallePedido;
	private int idProductos;
	private int idPedidos;
	private int cantidad;
	private double subtotal;
	private Productos productos;
	private Pedido pedido;
	public DetallePedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetallePedido(int idDetallePedido, int idProductos, int idPedidos,
			int cantidad, double subtotal, Productos productos, Pedido pedido) {
		super();
		this.idDetallePedido = idDetallePedido;
		this.idProductos = idProductos;
		this.idPedidos = idPedidos;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.productos = productos;
		this.pedido = pedido;
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
	public Productos getProductos() {
		return productos;
	}
	public void setProductos(Productos productos) {
		this.productos = productos;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	public String toJsonCDPedidoDe() {
		return "{'idDetallePedido':" + idDetallePedido + ", 'productos':" + productos.toJsonCDProductoDet() + 
				", 'cantidad':" + cantidad + ", 'subtotal':" + subtotal + "}";
	}	
	
	public String toJsonConsultaDetPedido() {
		return "{'productos':" + productos.toJsonCDProductoDet() + 
				", 'cantidad':" + cantidad + ", 'subtotal':" + subtotal + "}";
	}
		
}
