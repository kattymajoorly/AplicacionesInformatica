package com.upse.controlador;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.swing.text.View;
import javax.xml.rpc.ServiceException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;

public class indexcontrolador extends GenericForwardComposer<Component>{

	@Wire
	Textbox textbox_nombre, textbox_apellidos, textbox_cedula, textbox_email;
	Textbox textbox_direccion, textbox_telefono, textbox_alias, textbox_pass;
	Button btnGuardar;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
	//	ServicioWebSoapBindingStub servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
		
		/*String nombres = "Elizabeth"; 
		String apellidos = "Diaz";
		String cedula = "0603534379";
		String email = "eliza";
		String direccion = "Salinas";
		String telefono = "388383";
		Integer id_tipousuario = 1;
		String alias = "ka";
		String dpassword= "222";
		
		//String respuesta = servicioprueba.registrousuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
		
		//alert(respuesta);*/
		
		
	
	}

	public void onClick$btnGuardar(){
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba;
				
		String nombres = textbox_nombre.getText().toString(); 
		String apellidos = textbox_apellidos.getText().toString() ;
		String cedula = textbox_cedula.getText().toString();
		String email = textbox_email.getText().toString();
		String direccion = textbox_direccion.getText().toString();
		String telefono = textbox_telefono.getText().toString();
		Integer id_tipousuario = 1;
		String alias = textbox_alias.getText().toString();
		String dpassword= textbox_pass.getText().toString();
		
		
		
		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
			respuesta = servicioprueba.registrousuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
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
		
		if (respuesta.equalsIgnoreCase("1")){
			//alert(respuesta);
			alert("Su usuario ah sido ingresado correctamente");
		}else{
			alert("Su usuario no ah sido ingresado correctamente");
		}
		
	

		
	}
		

	
	
	

}
