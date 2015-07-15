package com.upse.controlador;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.xml.rpc.ServiceException;


import org.json.JSONArray;
import org.json.JSONException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;


import com.google.gson.JsonArray;
import com.upse.servicio.Categoria;
import com.upse.servicio.Productos;
import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;

public class productoxcategoriaControlador extends GenericForwardComposer<Component>{
	
	@Wire
	Listbox listboxCategoria, listboxCategoriaxProducto, listboxDescripcionPro;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba;
		

		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
			respuesta = servicioprueba.consultaProductoCategoria();
			ArrayList<Categoria> listCategoria = null;
			
			listCategoria = new ArrayList<Categoria>();	
			JSONArray jsonn= new JSONArray(respuesta);
			for (int i=0 ;i< jsonn.length(); i++){
				int idCategoria = jsonn.getJSONObject(i).getInt("idCategoria");
				String nombre_categoria = jsonn.getJSONObject(i).getString("nombre_categoria");
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(idCategoria);
				categoria.setEstado("A");
				categoria.setNombre_categoria(nombre_categoria);
				listCategoria.add(categoria);
			}
			ListModelList<Categoria> modeloDeDatos = new ListModelList<Categoria>(listCategoria);
			listboxCategoria.setModel(modeloDeDatos);			 
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
}



	
public void onSelect$listboxCategoria(){
	if (listboxCategoria.getSelectedItem()!=null){
	
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba=null;
		
		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Categoria c = listboxCategoria.getSelectedItem().getValue();
		if(c!=null){
			
				try {
					respuesta = servicioprueba.consultaProductoxCategoria(c.getIdCategoria());
					//alert(respuesta);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<Productos> listProductos = null;
				
				listProductos = new ArrayList<Productos>();	
				JSONArray jsonn;
				
				
				try {
					jsonn = new JSONArray(respuesta);
							for (int i=0 ;i< jsonn.length(); i++){
						
						int idProductos = jsonn.getJSONObject(i).getInt("idProductos");
						String nombre_producto = jsonn.getJSONObject(i).getString("nombre_producto");
						//int idCategoria =jsonn.getJSONObject(i).getInt("idCategoria");
						//String nombre_categoria = jsonn.getJSONObject(i).getString("nombre_categoria");
						
						//Categoria categoria = new Categoria();
						//categoria.setIdCategoria(idCategoria);
						//categoria.setNombre_categoria(nombre_categoria);
						
						Productos productos = new Productos();
						//productos.setIdCategoria(idProductos);
						productos.setEstado("A");
						productos.setIdProductos(idProductos);
						productos.setNombre_producto(nombre_producto);
						
						//productos.setCategoria(categoria);
						listProductos.add(productos);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ListModelList<Productos> modeloDeDatos = new ListModelList<Productos>(listProductos);
				listboxCategoriaxProducto.setModel(modeloDeDatos);
		}
	}
	
		
	}

public void onSelect$listboxCategoriaxProducto(){
	if (listboxCategoriaxProducto.getSelectedItem()!=null){
	
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba=null;
		
		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Productos p = listboxCategoriaxProducto.getSelectedItem().getValue();
		if(p!=null){
			
				try {
					respuesta = servicioprueba.consultaProductoDetalle(p.getIdProductos());
					//alert(respuesta);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ArrayList<Productos> listProductos = null;
				
				listProductos = new ArrayList<Productos>();	
				JSONArray jsonn;
				
				
				try {
					jsonn = new JSONArray(respuesta);
					for (int i=0 ;i< jsonn.length(); i++){
						
						int idProductos = jsonn.getJSONObject(i).getInt("idProductos");
						String nombre_producto = jsonn.getJSONObject(i).getString("nombre_producto");
						String descripcion = jsonn.getJSONObject(i).getString("descripcion");
						Double precio = jsonn.getJSONObject(i).getDouble("precio");
						//int idCategoria =jsonn.getJSONObject(i).getInt("idCategoria");
						//String nombre_categoria = jsonn.getJSONObject(i).getString("nombre_categoria");
						
						//Categoria categoria = new Categoria();
						//categoria.setIdCategoria(idCategoria);
						//categoria.setNombre_categoria(nombre_categoria);
						
						Productos productos = new Productos();
						productos.setIdCategoria(idProductos);
						productos.setEstado("A");
						productos.setNombre_producto(nombre_producto);
						productos.setDescripcion(descripcion);
						productos.setPrecio(precio);
						
						
						//productos.setCategoria(categoria);
						listProductos.add(productos);
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ListModelList<Productos> modeloDeDatos = new ListModelList<Productos>(listProductos);
				listboxDescripcionPro.setModel(modeloDeDatos);
		}
	}
	
		
	}


	
}
