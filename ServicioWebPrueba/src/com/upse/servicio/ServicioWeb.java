package com.upse.servicio;


import java.util.ArrayList;
import java.util.Iterator;

import com.upse.entidades.DatosLogin;
import com.upse.entidades.Productos;
import com.upse.modelo.DBProducto;
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
	
	public String consultaProducto(String nombreProducto){
		String resultado = "[";
		Integer contador = 0;
		
		DBProducto dbproductos = new DBProducto();
		ArrayList<Productos> res = dbproductos.consultaProducto(nombreProducto);
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		Iterator r = res.listIterator();
		while(r.hasNext()){
			contador = contador + 1;
			Productos pro = (Productos) r.next();
			//contador ==1 para la concatenacion  xq el primer objeto no lleva coma entonces si eso es asi
			//noce pone la coma
			if (contador== 1){
				resultado = resultado + pro.toJsonCDProducto();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (contador != 1){
				resultado = resultado + ", " +pro.toJsonCDProducto();
			}
			
		}
		return resultado + "]";
	}
	
	
}
