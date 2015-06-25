package com.upse.modelo;

import com.upse.entidades.DatosLogin;
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
	
	
	public String ingresarLogin(String user, String pass){
		DatosLogin datosLogin = null;
		String cadenaLogin = "";
		
		//1. Conectarse a la bdd
		DBManager dbm = new DBManager();
		Connection con = dbm.getConection();
		if(con == null){
			System.out.println("Conexion es null");
			return cadenaLogin;
		}
		
		//2. Trabajar con la conexion
		java.sql.Statement sentencia;
		ResultSet resultados=null;
		String query="select per.nombres, per.apellidos, per.cedula, per.telefono, du.alias, us.id_usuario from usuario us inner join personas per on us.id_persona = per.id_persona " +
					 "inner join tipousuario tp on us.id_tipousuario = tp.id_tipousuario " +
					 "inner join datosusuario du on us.id_usuario = du.id_usuario " +
					 "where du.alias ='" + user + "' and " + "du.dpassword ='" + pass + "'";
						
		System.out.println(query);
		
		//ejecutar el query
		try {
			sentencia = con.createStatement();
			resultados = sentencia.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en ejecución de sentencia");
			e.printStackTrace();
		}
		
		try {
			//recorrer los resultados que haya arrojado los select
			datosLogin=new DatosLogin();
			
			while(resultados.next()){
				datosLogin.setNombres(resultados.getString("per.nombres"));
				datosLogin.setApellidos(resultados.getString("per.apellidos"));
				datosLogin.setCedula(resultados.getString("per.cedula"));
				//tel 
				datosLogin.setTelefono(resultados.getString("per.telefono"));
				datosLogin.setAlias(resultados.getString("du.alias"));
				datosLogin.setId_usuario(resultados.getInt("us.id_usuario"));
				//usuario.setNombreUsuario(resultados.getString("usu_usuario"));	
			}
			//estructura de json - conversion 
			cadenaLogin = "{'nombres':'"+datosLogin.getNombres()+"', 'apellidos':'"+datosLogin.getApellidos()+"'," +
					"'cedula': '"+datosLogin.getCedula()+"', 'telefono': '"+datosLogin.getTelefono()+"', 'alias':'"+datosLogin.getAlias()+"'," +
					"'id_usuario':'"+datosLogin.getId_usuario()+"'}";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cadenaLogin;
	}

	


}
