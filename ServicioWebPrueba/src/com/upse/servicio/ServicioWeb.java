package com.upse.servicio;

import com.upse.modelo.DBUsuario;

public class ServicioWeb {
	
	public String registrousuario(String nombres, String apellidos, String cedula, String email, String direccion, String telefono, Integer id_tipousuario, String alias, String dpassword){
		String resultado = "0";
		
		DBUsuario dbusuario = new DBUsuario();
		Integer r = dbusuario.nuevoUsuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
		//aki
		if(r==1){
			resultado= "1";
		}
		/*if(r==2){
			resultado= "2";
		}*/
		if(r==0){
			resultado="0";
		}
		return resultado;
	}
	
	
	
}
