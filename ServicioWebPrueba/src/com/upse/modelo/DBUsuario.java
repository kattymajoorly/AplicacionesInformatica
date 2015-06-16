package com.upse.modelo;

import com.upse.entidades.Persona;
import com.upse.entidades.TipoUsuario;
import com.upse.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUsuario {
	
	public Integer nuevoUsuario(String nombres, String apellidos, String cedula, String email, String direccion, String telefono, Integer id_tipousuario, String alias, String dpassword){
		Integer resultado=0;
		
		DBManager dbm = new DBManager();
		Connection con = dbm.getConection();
		if (!validarIngresousuario(alias))
		{
		//Manejo de transaccion
		try {
			con.setAutoCommit(false);
			String sql = null;
			sql="CALL sp_ingresosUsuarios (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			pstm.setString(3, cedula);
			pstm.setString(4, email);
			pstm.setString(5, direccion);
			pstm.setString(6, telefono);
			pstm.setInt(7, id_tipousuario);
			pstm.setString(8, alias);
			pstm.setString(9, dpassword);
		
			
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
		}else{
			resultado=2;
			
		}
		return resultado;
	}
	public Boolean validarIngresousuario(String usuario){
        //busqueda de existencia de usuario 
        //conectarse a la red
       boolean existe = false;
        DBManager dbm = new DBManager();
        Connection con =dbm.getConection();
        if(con==null){
            System.out.println("error en conexion");
            return existe;
        }
         
        //sentencia a ejecutar
        Statement sentencia;
        //objeto para almacenar resultados
        ResultSet resultados;
        String sql = null;
     
        sql ="select * from datosusuario as du where du.alias= '"+usuario+"'"; 
       
        try{
        sentencia =con.createStatement();
        resultados=sentencia.executeQuery(sql);
        
        while(resultados.next()){
           existe=true;
        }
        
            
          }catch (SQLException e){
             
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
         
        try{
        con.close();
        }catch (SQLException e){
             
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return existe;
    }
	
	


}
