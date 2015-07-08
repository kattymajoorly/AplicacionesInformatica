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
			sql = "select pro.id_productos, pro.nombre_producto, cat.nombre_categoria, pro.precio, pro.stock" +
				  "from productos pro inner join categoria cat on pro.id_categoria = cat.id_categoria and pro.nombre_producto like '%"+nombreProducto+"%'";
			
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
				listaProducto.setIdProductos(resultados.getInt("pro.id_productos"));
				listaProducto.setNombre_producto(resultados.getString("pro.nombre_producto"));
				
				listaCategoria.setNombre_categoria(resultados.getString("cat.nombre_categoria"));
				listaProducto.setCategoria(listaCategoria);
				listaProducto.setPrecio(resultados.getDouble("pro.precio"));
				listaProducto.setStock(resultados.getInt("pro.stock"));
							
				lista.add(listaProducto);
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return lista;
        
	}
	
}
