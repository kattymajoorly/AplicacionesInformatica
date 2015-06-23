package com.upse.servicio;

import com.upse.entidades.DatosLogin;
import com.upse.modelo.DBUsuario;

public class ServicioWeb {
	
	public String registrousuario(String nombres, String apellidos, String cedula, String email, String direccion, String telefono, Integer id_tipousuario, String alias, String dpassword){
		String resultado = "0";
		
		DBUsuario dbusuario = new DBUsuario();
		Integer r = dbusuario.nuevoUsuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
		// 1: se ah agregado correctamente
		if(r==1){
			resultado= "1";
		}
		//2: el usuario ya esta registrado
		if(r==2){
			resultado= "2";
		}
		//0: no se pudo ingresar
		if(r==0){
			resultado="0";
		}
		return resultado;
	}
	public String iniciosesion(String usuario, String password)
	{
		String res="";
		
		DBUsuario s=new DBUsuario();
		
		res = s.ingresarLogin(usuario, password);
		
		return res;
	}
	
	
}
