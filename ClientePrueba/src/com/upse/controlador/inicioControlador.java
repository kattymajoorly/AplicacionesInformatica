package com.upse.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

import com.upse.servicio.ServicioWebSoapBindingStub;

public class inicioControlador extends GenericForwardComposer<Component>{
	@Wire
	Label label_usuario, label_cedula, label_telefono;

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		
	}
	
	

}
