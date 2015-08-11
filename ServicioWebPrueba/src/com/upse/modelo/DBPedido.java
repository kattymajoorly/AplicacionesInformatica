package com.upse.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.upse.entidades.Categoria;
import com.upse.entidades.DatosLogin;
import com.upse.entidades.DetallePedido;
import com.upse.entidades.Pedido;
import com.upse.entidades.Persona;
import com.upse.entidades.Productos;
import com.upse.entidades.Usuario;
import com.upse.entidades.datosConsultaPedido;

public class DBPedido {
		
		public Integer nuevoPedidoCabecera(Integer id_usuario, String fecha, Double subtotal, Double iva, Double total,Connection con){
			Integer resultado=0;
			//p_id_usuario integer, p_fecha date, p_subt double, p_iva double, p_total double
			//Manejo de transaccion
			try {
				String sql = null;
				sql="CALL sp_pedidos (?,?,?,?,?)";
				PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				pstm.setInt(1, id_usuario);
				pstm.setString(2, fecha);
				pstm.setDouble(3, subtotal);
				pstm.setDouble(4, iva);
				pstm.setDouble(5, total);
				
				//retorna el numero de filas afectadas
				int num = pstm.executeUpdate();
				//si hasta aqui todo ha ido bien commit a la transaccion
				if(num>0){
				resultado = 1;
				
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultado=0;
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return resultado;

		}
		
		public Integer stockminimo(Integer id_producto){
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
			int lista=0;
			sql = "select p.stock_minimo from productos p where p.id_productos=" +id_producto;		
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
				lista=resultados.getInt("stock_minimo");	
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
		}
		
		public Integer stockactual(Integer id_producto){
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
			int lista=0;
			sql = "select p.stock_actual from productos p where p.id_productos=" +id_producto;		
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
				lista=resultados.getInt("stock_actual");	
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return lista;
		}
		
		
		public Integer nuevoPedidoDetalle(ArrayList<DetallePedido> lista, Integer id_pedidos, Connection con){
 			Integer resultado=0;
 			//p_id_usuario integer, p_fecha date, p_subt double, p_iva double, p_total double
 			
 			int num=0;
 			//Manejo de transaccion
 			try {
 					
 				String sql = null;
 				for(int i=0; i<lista.size(); i++)
 				{
 				sql="CALL sp_detalle_pedido (?,?,?,?)";
 				PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
 				
 				pstm.setInt(1, lista.get(i).getIdProductos());
 				pstm.setInt(2, id_pedidos);
 				pstm.setInt(3, lista.get(i).getCantidad());
 				pstm.setDouble(4, lista.get(i).getSubtotal());
 			
 				//retorna el numero de filas afectadas
 				num = pstm.executeUpdate();
 				//si hasta aqui todo ha ido bien commit a la transaccion
 				}
 				if(num>0){
 				resultado = 1;
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 				resultado=0;
 				try {
 					con.rollback();
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
 			}
 			
 				
 			
 			return resultado;
 
 		}
		
		public Integer pedidoGuardado(Integer id_usuario, String fecha, Double subtotal, Double iva, Double total, ArrayList<DetallePedido> listaDetalles){
 			Integer idCabecera = 0;
 			Integer Resultado = 0, resultadocabecera=0, resultadodetalle=0;
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
 			
 			try {
 				con.setAutoCommit(false);
 			} catch (SQLException e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
 			
 			sql = "select max(id_pedidos) as idcabecera from pedidos where estado='A'";
 			
 			resultadocabecera=nuevoPedidoCabecera(id_usuario, fecha, subtotal, iva, total,con);		
 			if (resultadocabecera==1){
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
 					while(resultados.next()){
 					idCabecera=resultados.getInt("idcabecera");
 				}
 				}catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 				}
 				resultadodetalle=nuevoPedidoDetalle(listaDetalles,idCabecera,con);
 				if(resultadodetalle==1){
 					try {
						con.commit();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 					Resultado=1;
 				}else{
 					Resultado=0;
 				}
 				
 			}else{
 				try {
 					con.rollback();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			}
 			
 			return Resultado;
 				
 		}
		
	/*	
	public ArrayList<Pedido> consultaPedidoxUsuario(String estadoPedido){
						
				ArrayList<Pedido> lista = null;
				
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
					
					sql = "select pe.fecha as Fecha, pe.id_pedidos as NumeroFactura, pe.total as Total from pedidos pe where pe.estadoPedido="+estadoPedido;
							
					
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
					lista =new ArrayList<Pedido>();
					
					while(resultados.next()){
						Pedido listaPedido = new Pedido();
						listaPedido.setFecha(resultados.getString("Fecha"));
						listaPedido.setIdPedidos(resultados.getInt("NumeroFactura"));
						listaPedido.setTotal(resultados.getDouble("Total"));
																				
						lista.add(listaPedido);
					}
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				return lista;
		        
	}*/
		
		public ArrayList<datosConsultaPedido> consultaPedidoxUsuario(String estadoPedido, Integer id_Usuario){
			
			ArrayList<datosConsultaPedido> lista = null;
			
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
				
				sql = "select pe.fecha, pe.id_pedidos, pe.total from pedidos " +
						"pe inner join usuario usu on pe.id_usuario= usu.id_usuario " +
						"inner join personas per on usu.id_persona=per.id_persona where pe.estadoPedido='"+estadoPedido+"' and usu.id_usuario="+id_Usuario;
						
				
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
				lista =new ArrayList<datosConsultaPedido>();
				
				while(resultados.next()){
					//Pedido listaPedido = new Pedido();
					datosConsultaPedido listaPedido = new datosConsultaPedido(); 
					
					listaPedido.setFecha(resultados.getString("fecha"));
					listaPedido.setIdConsulta(resultados.getInt("id_pedidos"));
					listaPedido.setTotal(resultados.getDouble("total"));
																			
					lista.add(listaPedido);
				}
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return lista;
}
	
	
	public Pedido consultaPedidoxId(Integer idPedido){
		
				
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
			
			sql = "select pe.fecha as Fecha, pe.id_pedidos as NumeroFactura," +
					"per.nombres as Nombres, per.apellidos as Apellidos, " +
					"per.cedula as Cedula, pe.subtotal as Subtotal, pe.total_iva as IVA, " +
					"pe.total as TotalFactura from pedidos " +
					"pe inner join usuario usu on pe.id_usuario = usu.id_usuario inner join personas per on usu.id_persona = per.id_persona " +
					"where pe.id_pedidos="+idPedido+"";
			
			System.out.println(sql);
			
			Pedido  listaPedido = new Pedido();	
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
				Usuario usuario = new Usuario();
				Persona persona = new Persona();
				
				persona.setNombres(resultados.getString("Nombres"));
				persona.setApellidos(resultados.getString("Apellidos"));
				persona.setCedula(resultados.getString("Cedula"));
				usuario.setPersona(persona);
				
				listaPedido.setIdPedidos(resultados.getInt("NumeroFactura"));
				listaPedido.setFecha(resultados.getString("Fecha"));
				listaPedido.setSubtotal(resultados.getInt("Subtotal"));
				listaPedido.setTotal_iva(resultados.getDouble("IVA"));
				listaPedido.setTotal(resultados.getDouble("TotalFactura"));	
				listaPedido.setUsuario(usuario);
			
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return listaPedido;
	    
	}
	
	public ArrayList<DetallePedido> consultaPedidoxDetalle(Integer idPedido){
		
		ArrayList<DetallePedido> lista = null;
		
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
			
			sql = "select pro.nombre_producto, det.cantidad, pro.precio, det.subtotal" +
				  " from pedidos pe inner join detallepedido det on pe.id_pedidos = det.id_pedidos inner join productos pro on det.id_productos = pro.id_productos" +
				  " where det.id_pedidos='"+ idPedido+"'";
			/*sql = w"select pro.nombre_producto, pro.descripcion, det.cantidad, pro.precio, det.subtotal " +
					"from detallepedido det inner join productos pro on det.id_productos = pro.id_productos " +
					"where det.id_pedidos='"+ idPedido+"'";*/
					
			
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
			lista =new ArrayList<DetallePedido>();
			
			while(resultados.next()){
				
				Productos listaProducto = new Productos();
				DetallePedido listadetalle = new DetallePedido();
				
				listaProducto.setNombre_producto(resultados.getString("nombre_producto"));
				//listaProducto.setDescripcion(resultados.getString("descripcion"));
				listaProducto.setPrecio(resultados.getDouble("precio"));
				listadetalle.setProductos(listaProducto);
				
				listadetalle.setCantidad(resultados.getInt("cantidad"));
				listadetalle.setSubtotal(resultados.getDouble("subtotal"));
				listadetalle.setProductos(listaProducto);
																						
				lista.add(listadetalle);
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return lista;
        
	}

	
	
		
		
			
}
