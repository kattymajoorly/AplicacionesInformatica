package com.upse.controlador;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import org.json.JSONArray;
import org.json.JSONException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.upse.servicio.Categoria;
import com.upse.servicio.DetallePedido;
import com.upse.servicio.Productos;
import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;



public class pedidocontrolador extends GenericForwardComposer<Component> {

	@Wire
	Listbox listboxPedido;
	Label lbl_cliente, lbl_fecha, lbl_totalproducto, lbl_precio,lbl_subtotal,lbl_iva,lbl_total;
	Button btnAnadir, btnGuardar;
	Textbox textbox_cantidad;
	Combobox cmbProductos;
	ArrayList<Productos> listProductos = new ArrayList<Productos>();
	ArrayList<DetallePedido> listDetallePedido = new ArrayList<DetallePedido>();


	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		//LEER LA SESION
		//1.Obtener la session
		Session session = Sessions.getCurrent();
		//2.Leer atributo almacenado en la sesion
		//convertir a objeto generico de usuario
		
		Integer id_usuario = (Integer) session.getAttribute("idUser");
		
		if(id_usuario!=null){
			
			String nombres = session.getAttribute("nombreU") +" "+session.getAttribute("apellidoU");
			lbl_cliente.setValue(nombres);
			lbl_fecha.setValue(getFechaActual());
				
		}else{
			//no se ha logoneado o no existe la autentificacion
			//redirijir a la pagina de login
			Executions.sendRedirect("LoginUsuario.zul");
		}
		
		//para rellena el combo del Productos con l metodo de nombreProducto			
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba;
		
		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
			respuesta = servicioprueba.consultaProducto("");
			
				
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
			
			//if(listProductos.size() > 0){
				ListModelList<Productos> modeloDeDatos = new ListModelList<Productos>(listProductos);
				cmbProductos.setModel(modeloDeDatos);
			//}
		
					 
		} catch (RemoteException e) {
			// TODO Auto-generated catch blo(ed catch block
			e.printStackTrace();
		}

		
		}
	
	public void onSelect$cmbProductos(){
		//alert(""+cmbProductos.getSelectedItem().getValue());
		if (cmbProductos.getSelectedItem() != null){
			for(int i=0; i<listProductos.size(); i++){
				Integer idProducto = cmbProductos.getSelectedItem().getValue();
				if(listProductos.get(i).getIdProductos() == idProducto){
					lbl_precio.setValue(""+listProductos.get(i).getPrecio());
				}
			}
		}
		
	}
	
	//calcula el total del producto
	public void calcular(){
		double resultado;
		double precio;
		int cantidad;
		cantidad = Integer.parseInt(textbox_cantidad.getValue());
		precio = Double.parseDouble(lbl_precio.getValue());
		
		resultado = cantidad * precio;
		lbl_totalproducto.setValue(""+resultado);
		}
	
	
	public void onBlur$textbox_cantidad(){
		if(!textbox_cantidad.equals("") || !textbox_cantidad.equals(" ")){
			calcular();
		}
		
	}
	
	//calcular total de totales
	public void calculartotal(){
		double subtotal = 0;
		double total = 0;
		double iva;
		
		for(int i=0; i<listDetallePedido.size(); i++){
			subtotal = subtotal + listDetallePedido.get(i).getSubtotal();	
		}
		
		lbl_subtotal.setValue(""+subtotal);
		
		iva = subtotal * 0.12;
		lbl_iva.setValue(""+iva);
		
		total = subtotal + iva;
		lbl_total.setValue(""+total);
		
	}
	
	//para anadir un nuevo product al listbox
	public void onClick$btnAnadir(){
		DetallePedido detallePedido = new DetallePedido();
		Productos producto = new Productos();
		Integer idProductos=cmbProductos.getSelectedItem().getValue(); 
		String nombre_producto = cmbProductos.getSelectedItem().getLabel();
		
		producto.setIdProductos(idProductos);
		producto.setNombre_producto(nombre_producto);
		detallePedido.setProductos(producto);
		detallePedido.setIdProductos(idProductos);
		detallePedido.setCantidad(Integer.parseInt(textbox_cantidad.getValue()));
		detallePedido.setPrecio(Double.parseDouble(lbl_precio.getValue()));
		detallePedido.setSubtotal(Double.parseDouble(lbl_totalproducto.getValue()));
		
		listDetallePedido.add(detallePedido);
		ListModelList<DetallePedido> modeloDeDatos = new ListModelList<DetallePedido>(listDetallePedido);
		listboxPedido.setModel(modeloDeDatos);
		calculartotal();
		
	}
	
	
	//para obtener la fecha del sistema
	public String getFechaActual(){
		Calendar c1;
		 	c1 = Calendar.getInstance();	
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        return sdf.format(c1.getTime());
	}
	
	//para guardar pedido
	
	public void onClick$btnGuardar(){
		
	}
	
	

	
}
