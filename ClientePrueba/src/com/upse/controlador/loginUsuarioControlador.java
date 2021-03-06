package com.upse.controlador;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.json.JSONArray;
import org.json.JSONException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import com.upse.servicio.Categoria;
import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;

public class loginUsuarioControlador extends GenericForwardComposer<Component> {

	@Wire
	Textbox textbox_usuario,textbox_password;
	Button btnIngresar;
	Label label_mensaje;
	//declarar variables globales
	String mensaje="";
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
	}
	
	public void onClick$btnIngresar()
	{
		String res="";
		ServicioWebSoapBindingStub ser;
		int id_usuario=0;
		String nombres="";
		String apellidos="";
		
		try {
			if(textbox_usuario.getValue().isEmpty() && textbox_password.getValue().isEmpty()){
				mensaje="Usuario y clave son requeridos";
			}else{
				ser = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
				res=ser.iniciosesion(textbox_usuario.getText(), textbox_password.getText());
			}
		} catch (MalformedURLException | ServiceException | WrongValueException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONArray jsonn;
		try {
			jsonn = new JSONArray("["+res+"]");
			for (int i=0 ;i< jsonn.length(); i++){
				id_usuario = jsonn.getJSONObject(i).getInt("id_usuario");
				nombres = jsonn.getJSONObject(i).getString("nombres");
				apellidos = jsonn.getJSONObject(i).getString("apellidos");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	
		//acceder al modelo
		//if(textbox_usuario.getValue().equals(" ")  && textbox_password.getValue().equals(" "))
		if(!res.contains("null")){
			alert(res);
			//Almacenar datos en la sesion
			//1. Obtener la sesion actual
			Session session = Sessions.getCurrent();
			
			//2. Almacenar dato en la sesion
			session.setAttribute("idUser", id_usuario);
			session.setAttribute("nombreU", nombres);
			session.setAttribute("apellidoU", apellidos);
			
			//redireccionar a la pagina principal 
			//Executions.sendRedirect("inicioBienvenido.zul");
			//Executions.sendRedirect("guardarPedido.zul");
			Executions.sendRedirect("inicioBienvenido.zul");
			
		}else{
			mensaje="Usuario y/o clave incorrecta";
		}
	
	
		label_mensaje.setValue(mensaje);
	}
	
}
