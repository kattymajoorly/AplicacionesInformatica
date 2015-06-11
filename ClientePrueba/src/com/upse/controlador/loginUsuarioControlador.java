package com.upse.controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

public class loginUsuarioControlador extends GenericForwardComposer<Component> {

	@Wire
	Textbox textbox_usuario,textbox_password;
	Button btnIngresar;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}
	
	
}
