package com.upse.servicio;

public class ServicioWebProxy implements com.upse.servicio.ServicioWeb {
  private String _endpoint = null;
  private com.upse.servicio.ServicioWeb servicioWeb = null;
  
  public ServicioWebProxy() {
    _initServicioWebProxy();
  }
  
  public ServicioWebProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioWebProxy();
  }
  
  private void _initServicioWebProxy() {
    try {
      servicioWeb = (new com.upse.servicio.ServicioWebServiceLocator()).getServicioWeb();
      if (servicioWeb != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicioWeb)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicioWeb)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicioWeb != null)
      ((javax.xml.rpc.Stub)servicioWeb)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.upse.servicio.ServicioWeb getServicioWeb() {
    if (servicioWeb == null)
      _initServicioWebProxy();
    return servicioWeb;
  }
  
  public java.lang.String iniciosesion(java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException{
    if (servicioWeb == null)
      _initServicioWebProxy();
    return servicioWeb.iniciosesion(usuario, password);
  }
  
  public java.lang.String registrousuario(java.lang.String nombres, java.lang.String apellidos, java.lang.String cedula, java.lang.String email, java.lang.String direccion, java.lang.String telefono, int id_tipousuario, java.lang.String alias, java.lang.String dpassword) throws java.rmi.RemoteException{
    if (servicioWeb == null)
      _initServicioWebProxy();
    return servicioWeb.registrousuario(nombres, apellidos, cedula, email, direccion, telefono, id_tipousuario, alias, dpassword);
  }
  
  
}