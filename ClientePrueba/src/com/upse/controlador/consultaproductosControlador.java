package com.upse.controlador;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;

public class consultaproductosControlador extends GenericForwardComposer<Component>{
	
	@Wire
	Listbox listboxProductos;
	Textbox textboxBusqueda;


	public void onClick$buttonBusqueda(){
		//uso del modelo
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba;
		
		String nombreProducto = textboxBusqueda.getText().toString(); 
		
		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
			//Producto lista= servicioprueba.buscarUsuarios(textboxBusqueda.getValue());
			//respuesta = servicioprueba.consultaProducto(nombreProducto);
			respuesta = servicioprueba.consultaProducto(nombreProducto);
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!respuesta.contains("null")){
			alert(respuesta);
			
		}else{
			//System.out.println("producto no encontrado");
			alert("Producto no encontrado");
		}	
	}
}
