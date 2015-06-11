/**
 * ServicioWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.upse.servicio;

public interface ServicioWebService extends javax.xml.rpc.Service {
    public java.lang.String getServicioWebAddress();

    public com.upse.servicio.ServicioWeb getServicioWeb() throws javax.xml.rpc.ServiceException;

    public com.upse.servicio.ServicioWeb getServicioWeb(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
