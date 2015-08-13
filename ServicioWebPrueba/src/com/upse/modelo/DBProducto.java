package com.upse.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.upse.entidades.Categoria;
import com.upse.entidades.Productos;


public class DBProducto {
	
	//se debe buscar por categoria, nombre de producto, idcategoria ==> (idproducto,descripcion, precio), general ==> muestra categoria 
	
	
		public ArrayList<Productos> consultaProducto(String nombreProducto){
			
			ArrayList<Productos> lista = null;
			
			//1. Conectarse a la bdd
				DBManager dbm = new DBManager();
				Connection con = dbm.getConection();
				if(con == null){
					System.out.println("Conexion es null");
				}
					
				//sentencia a ejecutar
		        Statement sentencia;
		        //objeto para almacenar resultados
		        ResultSet resultados = null;
				String sql = null;	
				
				sql = "select pro.id_productos, pro.nombre_producto, pro.descripcion, cat.id_categoria ,cat.nombre_categoria, pro.precio, pro.stock_minimo, pro.stock_actual " +
						"from productos pro inner join categoria cat on pro.id_categoria = cat.id_categoria and pro.nombre_producto like '%"+nombreProducto+"%'";
				
						
				//ejecutar el query
				try {
					sentencia = con.createStatement();
					resultados = sentencia.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en ejecución de sentencia");
					e.printStackTrace();
				}
				
			try {
				//recorrer los resultados que haya arrojado los select
				lista =new ArrayList<Productos>();
				
				while(resultados.next()){
					Productos listaProducto = new Productos();
					Categoria listaCategoria = new Categoria();
					listaProducto.setIdProductos(resultados.getInt("pro.id_productos"));
					listaProducto.setNombre_producto(resultados.getString("pro.nombre_producto"));
					listaProducto.setDescripcion(resultados.getString("pro.descripcion"));
					
					listaCategoria.setIdCategoria(resultados.getInt("cat.id_categoria"));
					listaCategoria.setNombre_categoria(resultados.getString("cat.nombre_categoria"));
					listaProducto.setCategoria(listaCategoria);
					listaProducto.setPrecio(resultados.getDouble("pro.precio"));
					listaProducto.setStock_minimo(resultados.getInt("pro.stock_minimo"));
					listaProducto.setStock_actual(resultados.getInt("pro.stock_actual"));
								
					lista.add(listaProducto);
				}
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			return lista;
	        
		}
		
	public ArrayList<Categoria> consultaProductoCategoria(){
			
			ArrayList<Categoria> lista = null;
			
			//1. Conectarse a la bdd
				DBManager dbm = new DBManager();
				Connection con = dbm.getConection();
				if(con == null){
					System.out.println("Conexion es null");
				}
					
				//sentencia a ejecutar
		        Statement sentencia;
		        //objeto para almacenar resultados
		        ResultSet resultados = null;
				String sql = null;	
				
				sql = "select * from categoria";
				
						
				//ejecutar el query
				try {
					sentencia = con.createStatement();
					resultados = sentencia.executeQuery(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en ejecución de sentencia");
					e.printStackTrace();
				}
				
			try {
				//recorrer los resultados que haya arrojado los select
				lista =new ArrayList<Categoria>();
				
				while(resultados.next()){
					Categoria listaCategoria = new Categoria();
					
					listaCategoria.setIdCategoria(resultados.getInt("id_categoria"));
					listaCategoria.setNombre_categoria(resultados.getString("nombre_categoria"));
					
								
					lista.add(listaCategoria);
				}
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			return lista;
	        
		}

	public ArrayList<Productos> consultaProductoxCategoria(Integer idCategoria){
		
		ArrayList<Productos> lista = null;
		
		//1. Conectarse a la bdd
			DBManager dbm = new DBManager();
			Connection con = dbm.getConection();
			if(con == null){
				System.out.println("Conexion es null");
			}
				
			//sentencia a ejecutar
	        Statement sentencia;
	        //objeto para almacenar resultados
	        ResultSet resultados = null;
			String sql = null;	
			
			sql = "SELECT pro.id_productos, pro.nombre_producto, cat.id_categoria, cat.nombre_categoria FROM productos pro, categoria cat " +
					"WHERE cat.id_categoria=pro.id_categoria and cat.id_categoria="+idCategoria+"";
			
			//ejecutar el query
			try {
				sentencia = con.createStatement();
				resultados = sentencia.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en ejecución de sentencia");
				e.printStackTrace();
			}
			
		try {
			//recorrer los resultados que haya arrojado los select
			lista =new ArrayList<Productos>();
			
			while(resultados.next()){
				Categoria listaCategoria = new Categoria();
				Productos listaProducto = new Productos();
				
				listaCategoria.setIdCategoria(resultados.getInt("id_categoria"));
				listaCategoria.setNombre_categoria(resultados.getString("nombre_categoria"));
				listaProducto.setCategoria(listaCategoria);
				listaProducto.setIdProductos(resultados.getInt("pro.id_productos"));
				listaProducto.setNombre_producto(resultados.getString("pro.nombre_producto"));
				
			
				lista.add(listaProducto);
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return lista;
	    
	}


	public ArrayList<Productos> consultaProductoDetalle(Integer idProducto){
		
		ArrayList<Productos> lista = null;
		
		//1. Conectarse a la bdd
			DBManager dbm = new DBManager();
			Connection con = dbm.getConection();
			if(con == null){
				System.out.println("Conexion es null");
			}
					//sentencia a ejecutar
	        Statement sentencia;
	        //objeto para almacenar resultados
	        ResultSet resultados = null;
			String sql = null;	
			
			sql = "SELECT pro.id_productos, pro.nombre_producto, pro.descripcion, pro.precio, cat.id_categoria " +
					"FROM productos pro, categoria cat WHERE cat.id_categoria=pro.id_categoria and pro.id_productos="+idProducto+"";
			System.out.println(sql);
			
					
			//ejecutar el query
			try {
				sentencia = con.createStatement();
				resultados = sentencia.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en ejecución de sentencia");
				e.printStackTrace();
			}
			
		try {
			//recorrer los resultados que haya arrojado los select
			lista =new ArrayList<Productos>();
			
			while(resultados.next()){
				Productos listaProducto = new Productos();
				Categoria listaCategoria = new Categoria();
				listaCategoria.setIdCategoria(resultados.getInt("cat.id_categoria"));
				listaProducto.setCategoria(listaCategoria);
				
				listaProducto.setIdProductos(resultados.getInt("pro.id_productos"));
				listaProducto.setNombre_producto(resultados.getString("pro.nombre_producto"));
				listaProducto.setDescripcion(resultados.getString("pro.descripcion"));
				listaProducto.setPrecio(resultados.getDouble("pro.precio"));
				
			
				lista.add(listaProducto);
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return lista;
	    
	}

	public String consultaProdImagen(Integer idProducto){
		String strImagen = "default.jpg";
		//1. Conectarse a la bdd
			DBManager dbm = new DBManager();
			Connection con = dbm.getConection();
			if(con == null){
				System.out.println("Conexion es null");
			}
				
			//sentencia a ejecutar
	        Statement sentencia;
	        //objeto para almacenar resultados
	        ResultSet resultados = null;
			String sql = null;	
			
			sql = "SELECT pro.pathimagen FROM productos pro where id_productos="+idProducto;
			System.out.println(sql);
							
			//ejecutar el query
			try {
				sentencia = con.createStatement();
				resultados = sentencia.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error en ejecución de sentencia");
				e.printStackTrace();
			}
			
		try {
			//recorrer los resultados que haya arrojado los select
			
			while(resultados.next()){
			strImagen=(String) resultados.getString("pathimagen");
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return strImagen;
	    
	}


	
	
	
}
