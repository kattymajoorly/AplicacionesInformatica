package com.upse.servicio;

import com.upse.modelo.DBUsuario;

public class ServicioWeb {
	
	public String registrousuario(String nombres, String apellidos, String cedula, String email, String direccion, String telefono, Integer id_tipousuario, String alias, String dpassword){
		String resultado = "0";
		
		DBUsuario dbusuario = new DBUsuario();
		Boolean r = dbusuario.nuevoUsuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
		if(r){
			resultado= "1";
		}else{
			resultado= "0";
		}
		
		return resultado;
	}
	
	
}
