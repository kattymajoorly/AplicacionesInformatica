package com.upse.servicio;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;

import com.upse.entidades.Categoria;
import com.upse.entidades.DatosLogin;
import com.upse.entidades.DetallePedido;
import com.upse.entidades.Pedido;
import com.upse.entidades.Productos;
import com.upse.entidades.datosConsultaPedido;
import com.upse.modelo.DBPedido;
import com.upse.modelo.DBProducto;
import com.upse.modelo.DBUsuario;

public class ServicioWeb {
	
	//EN ESTE METODO SE REALIZA UN REGISTRO DE USUARIO 
	public String registrousuario(String nombres, String apellidos, String cedula, String email, String direccion, String telefono, Integer id_tipousuario, String alias, String dpassword){
		String resultado = "0";
		
		DBUsuario dbusuario = new DBUsuario();
		Integer r = dbusuario.nuevoUsuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
		// 1: se ah agregado correctamente
		if(r==1){
			resultado= "1";
		}
		//2: el usuario ya esta registrado
		if(r==2){
			resultado= "2";
		}
		//0: no se pudo ingresar
		if(r==0){
			resultado="0";
		}
		return resultado;
	}
	
	
	//METODO REALIZADO PARA EL INICIO DE SESION
	public String iniciosesion(String usuario, String password)
	{
		String res="";
		
		DBUsuario s=new DBUsuario();
		
		res = s.ingresarLogin(usuario, password);
		
		return res;
	}
	
	
	
	//CONSULTA DE PRODUCTO
	
	//CONSULTA DE IMAGEN DEL PRODUCTO
	public String consultarImagen(Integer idProducto){
			String img=null;
			DBProducto dbproductos = new DBProducto();
			img=dbproductos.consultaProdImagen(idProducto);
			return img;
	}
	
	//METODO PARA CONSULTA DE PRODUCTO MEDIANTE EL PARAMETRO DEL NOMBRE DE PRODUCTO
	public String consultaProducto(String nombreProducto){
		String resultado = "[";
		Integer contador = 0;
		
		DBProducto dbproductos = new DBProducto();
		ArrayList<Productos> res = dbproductos.consultaProducto(nombreProducto);
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		Iterator r = res.listIterator();
		while(r.hasNext()){
			contador = contador + 1;
			Productos pro = (Productos) r.next();
			//contador ==1 para la concatenacion  xq el primer objeto no lleva coma entonces si eso es asi
			//noce pone la coma
			if (contador== 1){
				resultado = resultado + pro.toJsonCDProducto();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (contador != 1){
				resultado = resultado + ", " +pro.toJsonCDProducto();
			}
			
		}
		return resultado + "]";
	}
	
	
	//METODO REALIZADO PARA RELAIZAR LA BUSQUEDA MEDIANTE LA CATEGORIA EN GENERAL SIN ENVIO DE PARAMETRO
	public String consultaProductoCategoria(){
		String resultado = "[";
		Integer contador = 0;
		
		DBProducto dbproductos = new DBProducto();
		ArrayList<Categoria> res = dbproductos.consultaProductoCategoria();
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		Iterator r = res.listIterator();
		while(r.hasNext()){
			contador = contador + 1;
			Categoria cat = (Categoria) r.next();
			//contador ==1 para la concatenacion  xq el primer objeto no lleva coma entonces si eso es asi
			//noce pone la coma
			if (contador== 1){
				resultado = resultado + cat.toJsonCDProducto();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (contador != 1){
				resultado = resultado + ", " +cat.toJsonCDProducto();
			}
			
		}
		return resultado + "]";
	}
	
	
	//METODO REALIZADO PARA CONSULTA DE PRODUCTO X CATEGORIA MEDIANTE EL PARAMETRO DEL ID DE ESA CATEGORIA
	public String consultaProductoxCategoria(Integer idCategoria){
		String resultado = "[";
		Integer contador = 0;
		
		DBProducto dbproductos = new DBProducto();
		ArrayList<Productos> res = dbproductos.consultaProductoxCategoria(idCategoria);
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		Iterator r = res.listIterator();
		while(r.hasNext()){
			contador = contador + 1;
			Productos pro = (Productos) r.next();
			//contador ==1 para la concatenacion  xq el primer objeto no lleva coma entonces si eso es asi
			//noce pone la coma
			if (contador== 1){
				resultado = resultado + pro.toJsonCDProducto();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (contador != 1){
				resultado = resultado + ", " +pro.toJsonCDProducto();
			}
			
		}
		return resultado + "]";
	}
	
	
	//CONSULTA DEL DETALLE DEL PRODUCTO PARAMETRO DE ENVIO EL ID DE ELPRODUCTO ELEGIDO
	public String consultaProductoDetalle(Integer idProducto){
		String resultado = "[";
		Integer contador = 0;
		
		DBProducto dbproductos = new DBProducto();
		ArrayList<Productos> res = dbproductos.consultaProductoDetalle(idProducto);
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		Iterator r = res.listIterator();
		while(r.hasNext()){
			contador = contador + 1;
			Productos pro = (Productos) r.next();
			//contador ==1 para la concatenacion  xq el primer objeto no lleva coma entonces si eso es asi
			//noce pone la coma
			if (contador== 1){
				resultado = resultado + pro.toJsonCDProducto();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (contador != 1){
				resultado = resultado + ", " +pro.toJsonCDProducto();
			}
			
		}
		return resultado + "]";
	}
	
	
	//VALIDACION DE STOCK EN EL PRODUCTO
	//EN ESTE METODO NOCE PODRA GUARDAR UN PEDIDO SI LA CANTIDA INGRESADA SOBREPASA A LA CANTIDAD ACTUAL 
	public String validar_stock(Integer id_producto, Integer cantidad){
		Integer sa, sm;
		String respuesta;
		DBPedido db=new DBPedido();
		sm= db.stockminimo(id_producto);
		sa= db.stockactual(id_producto);
	
		if(cantidad>sa)
		{
			respuesta = "0";
		}else {
			if((sa-cantidad)>=sm){
				respuesta= "1";
			}else{
				respuesta = "0";
			}
			
		}
		return respuesta;
	}
	
	
	
	//PEDIDO
	//GUARDAR PEDIDO TANTO CABECERA COMO DETALLE
	public String guardarpedido(Integer id_usuario, String fecha, Double subtotal, Double iva, Double total, String jsondetalle){
		String resultado = "0";
		ArrayList<DetallePedido> listadetalle=new ArrayList<DetallePedido>();
		JSONArray jsonn;
		try {
			
			jsonn = new JSONArray(jsondetalle);
			for (int i=0 ;i< jsonn.length(); i++){
				Integer id_producto=jsonn.getJSONObject(i).getInt("idProductos");
				Integer cantidad=jsonn.getJSONObject(i).getInt("cantidad");
				Double detsubtotal=jsonn.getJSONObject(i).getDouble("subtotal");
				DetallePedido ped= new DetallePedido();
				ped.setIdProductos(id_producto);
				ped.setCantidad(cantidad);
				ped.setSubtotal(detsubtotal);
				listadetalle.add(ped);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBPedido dbpedido = new DBPedido();
		Integer r = dbpedido.pedidoGuardado(id_usuario, fecha, subtotal, iva, total, listadetalle);
		// 1: se ah agregado correctamente
		if(r==1){
			resultado= "1";
		}
				//0: no se pudo ingresar
		if(r==0){
			resultado="0";
		}
		return resultado;
	}
	
	
	//CONSULTAS PARA PEDIDO POR EL USUARIO INGRESADO SESION
	//EN ESTE METODO SE REALIZA UNA CONSULTA POR ESTADO DEL PEDIDO
	public String consultaPedidoxUsuario(String estadoPedido, Integer idUsuario){
		String resultado = "[";
		Integer contador = 0;
		DBPedido dbpedido = new DBPedido();
		ArrayList<datosConsultaPedido> res = dbpedido.consultaPedidoxUsuario(estadoPedido,idUsuario);
		
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		if (res!=null){
		for (int i=0;i<res.size();i++){
			
			if (i== 0){
				resultado = resultado + res.get(i).toJsonCDConsulta();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (i > 0){
				resultado = resultado + ", " + res.get(i).toJsonCDConsulta();
			}
			
		}
		}
		return resultado + "]";
	}
	
	
	//CONSULTA DE PEDIDO POR USUARIO EL DETALLE DE ESA FACTURA
	//ES DECIR SE REALIZA UNA CONSULTA POR EL ID DEL PEDIDO ELEGIDO
	public String consultaPedidoxDetalleLista(Integer idPedido){
		String resultado = "[";
		Integer contador = 0;
		DBPedido dbpedido = new DBPedido();
		ArrayList<DetallePedido> res = dbpedido.consultaPedidoxDetalle(idPedido);
		
		//lisIteraor devuelve un arreglo de todos los indices de la lista
		//lisIterator recorre todo la lista
		
		//variable de tipo Iterator es para recorrer la lista
		// se pueden usar otros metodos como for o foreach pero el mas optimo el Iterator.
		if(res!=null){
		for(int i = 0 ; i< res.size(); i++){
			
			if (i== 0){
				resultado = resultado + res.get(i).toJsonConsultaDetPedido();
			}
			
			//si se pone contador !=1 entonces en el array se le pone la coma
			if (i > 0){
				resultado = resultado + ", " +res.get(i).toJsonConsultaDetPedido();
			}
			
		}
		}
		
		return resultado + "]";
	}
	
	//este es x objeto mmm
	//CONSULTA DEL PEDIDO POR CABECERA DEL PEDIDO ELEGIDO
	public String consultaPedidoxId(Integer idPedido){
		String resultado="[";
		DBPedido dbpedido = new DBPedido();
		
		Pedido pe = new Pedido();
		
		pe = dbpedido.consultaPedidoxId(idPedido);
			
		resultado = resultado +  pe.toJsonCDPedido();
			
		
		return resultado + "]";
	}
	
}
