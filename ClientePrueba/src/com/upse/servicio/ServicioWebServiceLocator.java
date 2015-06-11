/**
 * ServicioWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.upse.servicio;

public class ServicioWebServiceLocator extends org.apache.axis.client.Service implements com.upse.servicio.ServicioWebService {

    public ServicioWebServiceLocator() {
    }


    public ServicioWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicioWeb
    private java.lang.String ServicioWeb_address = "http://localhost:8080/ServicioWebPrueba/services/ServicioWeb";

    public java.lang.String getServicioWebAddress() {
        return ServicioWeb_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicioWebWSDDServiceName = "ServicioWeb";

    public java.lang.String getServicioWebWSDDServiceName() {
        return ServicioWebWSDDServiceName;
    }

    public void setServicioWebWSDDServiceName(java.lang.String name) {
        ServicioWebWSDDServiceName = name;
    }

    public com.upse.servicio.ServicioWeb getServicioWeb() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicioWeb_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicioWeb(endpoint);
    }

    public com.upse.servicio.ServicioWeb getServicioWeb(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.upse.servicio.ServicioWebSoapBindingStub _stub = new com.upse.servicio.ServicioWebSoapBindingStub(portAddress, this);
            _stub.setPortName(getServicioWebWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicioWebEndpointAddress(java.lang.String address) {
        ServicioWeb_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.upse.servicio.ServicioWeb.class.isAssignableFrom(serviceEndpointInterface)) {
                com.upse.servicio.ServicioWebSoapBindingStub _stub = new com.upse.servicio.ServicioWebSoapBindingStub(new java.net.URL(ServicioWeb_address), this);
                _stub.setPortName(getServicioWebWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServicioWeb".equals(inputPortName)) {
            return getServicioWeb();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servicio.upse.com", "ServicioWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servicio.upse.com", "ServicioWeb"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServicioWeb".equals(portName)) {
            setServicioWebEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
