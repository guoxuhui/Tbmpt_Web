/**
 * SzzxLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crfeb.tbmpt.commons.wsdl;

public class SzzxLocator extends org.apache.axis.client.Service implements com.crfeb.tbmpt.commons.wsdl.Szzx {

    public SzzxLocator() {
    }


    public SzzxLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SzzxLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SzzxSoap
    private java.lang.String SzzxSoap_address = "http://118.178.93.159:8003/Szzx.asmx";

    public java.lang.String getSzzxSoapAddress() {
        return SzzxSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SzzxSoapWSDDServiceName = "SzzxSoap";

    public java.lang.String getSzzxSoapWSDDServiceName() {
        return SzzxSoapWSDDServiceName;
    }

    public void setSzzxSoapWSDDServiceName(java.lang.String name) {
        SzzxSoapWSDDServiceName = name;
    }

    public com.crfeb.tbmpt.commons.wsdl.SzzxSoap getSzzxSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SzzxSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSzzxSoap(endpoint);
    }

    public com.crfeb.tbmpt.commons.wsdl.SzzxSoap getSzzxSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.crfeb.tbmpt.commons.wsdl.SzzxSoapStub _stub = new com.crfeb.tbmpt.commons.wsdl.SzzxSoapStub(portAddress, this);
            _stub.setPortName(getSzzxSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSzzxSoapEndpointAddress(java.lang.String address) {
        SzzxSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.crfeb.tbmpt.commons.wsdl.SzzxSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.crfeb.tbmpt.commons.wsdl.SzzxSoapStub _stub = new com.crfeb.tbmpt.commons.wsdl.SzzxSoapStub(new java.net.URL(SzzxSoap_address), this);
                _stub.setPortName(getSzzxSoapWSDDServiceName());
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
        if ("SzzxSoap".equals(inputPortName)) {
            return getSzzxSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("celiang", "Szzx");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("celiang", "SzzxSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SzzxSoap".equals(portName)) {
            setSzzxSoapEndpointAddress(address);
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
