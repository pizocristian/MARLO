/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/

package org.cgiar.ccafs.marlo.ocs.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01
 * Generated source version: 2.2
 */
@WebServiceClient(name = "MarloService", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/",
  wsdlLocation = "http://beta.ciat.cgiar.org:80/abwinterface/MarloPort?WSDL")
public class MarloService extends Service {

  private final static URL MARLOSERVICE_WSDL_LOCATION;
  private final static WebServiceException MARLOSERVICE_EXCEPTION;
  private final static QName MARLOSERVICE_QNAME = new QName("http://logic.control.abw.ciat.cgiar.org/", "MarloService");

  static {
    URL url = null;
    WebServiceException e = null;
    try {
      url = new URL("http://beta.ciat.cgiar.org:80/abwinterface/MarloPort?WSDL");
    } catch (MalformedURLException ex) {
      e = new WebServiceException(ex);
    }
    MARLOSERVICE_WSDL_LOCATION = url;
    MARLOSERVICE_EXCEPTION = e;
  }

  private static URL __getWsdlLocation() {
    if (MARLOSERVICE_EXCEPTION != null) {
      throw MARLOSERVICE_EXCEPTION;
    }
    return MARLOSERVICE_WSDL_LOCATION;
  }

  public MarloService() {
    super(__getWsdlLocation(), MARLOSERVICE_QNAME);
  }

  public MarloService(URL wsdlLocation) {
    super(wsdlLocation, MARLOSERVICE_QNAME);
  }

  public MarloService(URL wsdlLocation, QName serviceName) {
    super(wsdlLocation, serviceName);
  }

  public MarloService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
    super(wsdlLocation, serviceName, features);
  }

  public MarloService(URL wsdlLocation, WebServiceFeature... features) {
    super(wsdlLocation, MARLOSERVICE_QNAME, features);
  }

  public MarloService(WebServiceFeature... features) {
    super(__getWsdlLocation(), MARLOSERVICE_QNAME, features);
  }

  /**
   * @return
   *         returns WSMarlo
   */
  @WebEndpoint(name = "MarloPort")
  public WSMarlo getMarloPort() {
    return super.getPort(new QName("http://logic.control.abw.ciat.cgiar.org/", "MarloPort"), WSMarlo.class);
  }

  /**
   * @param features
   *        A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy. Supported features not in the
   *        <code>features</code> parameter will have their default values.
   * @return
   *         returns WSMarlo
   */
  @WebEndpoint(name = "MarloPort")
  public WSMarlo getMarloPort(WebServiceFeature... features) {
    return super.getPort(new QName("http://logic.control.abw.ciat.cgiar.org/", "MarloPort"), WSMarlo.class, features);
  }

}
