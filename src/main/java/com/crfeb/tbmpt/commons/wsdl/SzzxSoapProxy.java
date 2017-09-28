package com.crfeb.tbmpt.commons.wsdl;

public class SzzxSoapProxy implements com.crfeb.tbmpt.commons.wsdl.SzzxSoap {
  private String _endpoint = null;
  private com.crfeb.tbmpt.commons.wsdl.SzzxSoap szzxSoap = null;
  
  public SzzxSoapProxy() {
    _initSzzxSoapProxy();
  }
  
  public SzzxSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSzzxSoapProxy();
  }
  
  private void _initSzzxSoapProxy() {
    try {
      szzxSoap = (new com.crfeb.tbmpt.commons.wsdl.SzzxLocator()).getSzzxSoap();
      if (szzxSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)szzxSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)szzxSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (szzxSoap != null)
      ((javax.xml.rpc.Stub)szzxSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.crfeb.tbmpt.commons.wsdl.SzzxSoap getSzzxSoap() {
    if (szzxSoap == null)
      _initSzzxSoapProxy();
    return szzxSoap;
  }
  
  public java.lang.String hqxlzxzb(java.lang.String xyys, java.lang.String sqys, java.lang.String sczb) throws java.rmi.RemoteException{
    if (szzxSoap == null)
      _initSzzxSoapProxy();
    return szzxSoap.hqxlzxzb(xyys, sqys, sczb);
  }
  
  
}