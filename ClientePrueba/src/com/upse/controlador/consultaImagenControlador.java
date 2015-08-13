package com.upse.controlador;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Blob;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.xml.rpc.ServiceException;

import org.json.JSONArray;
import org.json.JSONException;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericComposer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;

import com.upse.servicio.Estados;
import com.upse.servicio.Productos;
import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;

public class consultaImagenControlador extends GenericForwardComposer<Component>{
@Wire
Image imagenPrueba;
Combobox cmb_imagen;
Label lblImagen;

@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	
	ServicioWebSoapBindingStub servicioprueba;
	servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
	String respuesta;
	respuesta = servicioprueba.consultaProducto("");
	ArrayList<Productos> listProductos= new ArrayList<Productos>();
	JSONArray jsonn;
	
	try {
		jsonn = new JSONArray(respuesta);
		for (int i=0 ;i< jsonn.length(); i++){
			
			int idProductos = jsonn.getJSONObject(i).getInt("idProductos");
			String nombre_producto = jsonn.getJSONObject(i).getString("nombre_producto");
			Double precio = jsonn.getJSONObject(i).getDouble("precio");
								
			Productos productos = new Productos();
			productos.setIdProductos(idProductos);
			productos.setEstado("A");
			productos.setNombre_producto(nombre_producto);
							
			productos.setPrecio(precio);
			
			listProductos.add(productos);
		}
	}catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ListModelList<Productos> modeloDeDatos = new ListModelList<Productos>(listProductos);
	cmb_imagen.setModel(modeloDeDatos);
}

public void onSelect$cmb_imagen(){
	Integer idProducto;
	String respuesta;
	ServicioWebSoapBindingStub servicioprueba;
	idProducto =cmb_imagen.getSelectedItem().getValue();
	try {
		servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
		respuesta = servicioprueba.consultarImagen(idProducto);
		//alert("/images/"+respuesta);
		//imagen.setSrc();
		imagenPrueba.setSrc("/images/"+respuesta);
		imagenPrueba.setWidth("150px");
		imagenPrueba.setHeight("150px");
		imagenPrueba.setVisible(true);
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}



}
