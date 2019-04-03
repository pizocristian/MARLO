package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 23, 2018 10:50:08 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * EmailLog generated by hbm2java
 */
public class ClarisaMonitoring implements java.io.Serializable {

  private static final long serialVersionUID = -7393202991461143862L;

  private Long id;
  private User user;
  private GlobalUnit globalUnit;
  private LocElement locElement;

  private String serviceName;


  private String serviceUrl;


  private String serviceType;
  private String userIp;
  Date date;

  public ClarisaMonitoring() {
  }

  public Date getDate() {
    return date;
  }


  public GlobalUnit getGlobalUnit() {
    return globalUnit;
  }


  public Long getId() {
    return id;
  }

  public LocElement getLocElement() {
    return locElement;
  }


  public String getServiceName() {
    return serviceName;
  }


  public String getServiceType() {
    return serviceType;
  }


  public String getServiceUrl() {
    return serviceUrl;
  }


  public User getUser() {
    return user;
  }


  public String getUserIp() {
    return userIp;
  }


  public void setDate(Date date) {
    this.date = date;
  }


  public void setGlobalUnit(GlobalUnit globalUnit) {
    this.globalUnit = globalUnit;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }


  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }


  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }


  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }


  public void setUser(User user) {
    this.user = user;
  }


  public void setUserIp(String userIp) {
    this.userIp = userIp;
  }


}

