package com.upse.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.upse.entidades.Categoria;
import com.upse.entidades.DetallePedido;
import com.upse.entidades.Productos;

public class DBPedido {
	
	public Integer nuevoPedidoCabecera(Integer id_usuario, Date fecha, Double subtotal, Double iva, Double total){
		Integer resultado=0;
		//p_id_usuario integer, p_fecha date, p_subt double, p_iva double, p_total double
		DBManager dbm = new DBManager();
		Connection con = dbm.getConection();
		
		//Manejo de transaccion
		try {
			con.setAutoCommit(false);
			String sql = null;
			sql="CALL sp_pedidos (?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			pstm.setInt(1, id_usuario);
			pstm.setDate(2, (java.sql.Date) fecha);
			pstm.setDouble(3, subtotal);
			pstm.setDouble(4, iva);
			pstm.setDouble(5, total);
		
		
			
			//retorna el numero de filas afectadas
			int num = pstm.executeUpdate();
			//si hasta aqui todo ha ido bien commit a la transaccion
			if(num>0){
			con.commit();
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
	
	public Integer nuevoPedidoDetalle(ArrayList<DetallePedido> lista, Integer id_pedidos){
		Integer resultado=0;
		//p_id_usuario integer, p_fecha date, p_subt double, p_iva double, p_total double
		DBManager dbm = new DBManager();
		Connection con = dbm.getConection();
		int num=0;
		//Manejo de transaccion
		try {
			con.setAutoCommit(false);
			
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
			con.commit();
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
	
	
}