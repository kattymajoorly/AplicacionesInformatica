/**
 * ServicioWeb.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.upse.servicio;

public interface ServicioWeb extends java.rmi.Remote {
    public java.lang.String iniciosesion(java.lang.String usuario, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String registrousuario(java.lang.String nombres, java.lang.String apellidos, java.lang.String cedula, java.lang.String email, java.lang.String direccion, java.lang.String telefono, int id_tipousuario, java.lang.String alias, java.lang.String dpassword) throws java.rmi.RemoteException;
}
