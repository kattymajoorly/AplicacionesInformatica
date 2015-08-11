package com.upse.controlador;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.upse.servicio.Categoria;
import com.upse.servicio.DetallePedido;
import com.upse.servicio.Estados;
import com.upse.servicio.Pedido;
import com.upse.servicio.ServicioWebServiceLocator;
import com.upse.servicio.ServicioWebSoapBindingStub;
import com.upse.servicio.datosConsultaPedido;

public class consultaPedidoControlador extends GenericForwardComposer<Component>{
	
	@Wire
	Listbox listboxPedido;
	Combobox cmbEstado;
	Button buttonBusqueda,buttonFactura;
	
	public void llenarEstados(){
		
		ArrayList<Estados> listaEstado = null;
		
			listaEstado = new ArrayList<Estados>();
			
		Estados estado = new Estados();
		estado.setEstados("PENDIENTE");
		
		listaEstado.add(estado);
		
		estado = new Estados();
		estado.setEstados("REALIZADO");
		listaEstado.add(estado);
		
		ListModelList<Estados> modeloDeDatos = new ListModelList<Estados>(listaEstado);
		cmbEstado.setModel(modeloDeDatos);
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		llenarEstados();

		
	}
	
	public void onClick$buttonBusqueda(){
		ArrayList<datosConsultaPedido> ldp;
		Integer id_usuario;

		
		//LEER LA SESION
		//1.Obtener la session
		Session session = Sessions.getCurrent();
		//2.Leer atributo almacenado en la sesion
		//convertir a objeto generico de usuario
		
		id_usuario = (Integer) session.getAttribute("idUser");
		String Estado= cmbEstado.getSelectedItem().getLabel();
		
		if(id_usuario!=null){
			alert(id_usuario+"");
		
		String respuesta="";
		ServicioWebSoapBindingStub servicioprueba;
				
		try {
			servicioprueba = (ServicioWebSoapBindingStub) new ServicioWebServiceLocator().getServicioWeb(new URL("http://localhost:8080/ServicioWebPrueba/services/ServicioWeb"));
			respuesta = servicioprueba.consultaPedidoxUsuario(Estado,id_usuario);
			alert(respuesta);
			ArrayList<datosConsultaPedido> listPedido = null;
			
			listPedido = new ArrayList<datosConsultaPedido>();	
			JSONArray jsonn=null;
			try {
				jsonn = new JSONArray(respuesta);
				for (int i=0 ;i< jsonn.length(); i++){
					datosConsultaPedido pedido = new datosConsultaPedido();
					int id_pedidos = jsonn.getJSONObject(i).getInt("idConsulta");
					String fecha = jsonn.getJSONObject(i).getString("fecha");
					Double total = jsonn.getJSONObject(i).getDouble("total");
					pedido.setIdConsulta(id_pedidos);
					pedido.setFecha(fecha);
					pedido.setTotal(total);
					
					listPedido.add(pedido);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ListModelList<datosConsultaPedido> modeloDeDatos = new ListModelList<datosConsultaPedido>(listPedido);
			listboxPedido.setModel(modeloDeDatos);		
		
		
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
		}else{
		alert("id nulo");
		}
	}
	
	public void onClick$buttonFactura(){
		if (listboxPedido.getSelectedItem().getValue()!=null){
			String opcion="visualizar";
			datosConsultaPedido dc= listboxPedido.getSelectedItem().getValue();
			Integer idPedido = dc.getIdConsulta(); 
			Session session = Sessions.getCurrent();
				
		//2. Almacenar dato en la sesion
		session.setAttribute("opcion", opcion);
		session.setAttribute("idPedido", idPedido);
				
		Window win=(Window)Executions.createComponents("guardarPedido.zul", null, null);
		win.setClosable(true);
		win.doModal();
		
			
		}else{alert("Debe seleccionar un registro..");}
	}
	
	
	
	
	
	
	
}
