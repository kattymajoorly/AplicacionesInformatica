package com.upse.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;

import com.upse.servicio.ServicioWebSoapBindingStub;

public class inicioControlador extends GenericForwardComposer<Component>{
	@Wire
	Button buttonBusquedaGuardar, buttonBusquedaConsulta;

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
	}
	
	public void onClick$buttonBusquedaGuardar(){
		//1. Obtener la sesion actual
		String opcion="nuevo";
		Session session = Sessions.getCurrent();
				
		//2. Almacenar dato en la sesion
		session.setAttribute("opciones", opcion);
		
		Executions.sendRedirect("guardarPedido.zul");
		
		
	}
	
	public void onClick$buttonBusquedaConsulta(){
		String opcion="visualizar";
		Session session = Sessions.getCurrent();
				
		//2. Almacenar dato en la sesion
		session.setAttribute("opciones", opcion);
		
		Executions.sendRedirect("consultaPedido.zul");
		
	}
	
	
	
	

}
