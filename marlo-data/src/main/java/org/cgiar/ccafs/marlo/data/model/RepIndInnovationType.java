package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 18, 2018 1:21:50 PM by Hibernate Tools 3.4.0.CR1

import com.google.gson.annotations.Expose;

/**
 * RepIndInnovationType generated by hbm2java
 */
public class RepIndInnovationType extends MarloBaseEntity implements java.io.Serializable {

  private static final long serialVersionUID = -6713102131489490716L;
  @Expose
  private String name;
  @Expose
  private String definition;

  private Boolean isOneCGIAR;
  private Boolean isMarlo;


  public RepIndInnovationType() {
  }


  public String getDefinition() {
    return this.definition;
  }


  public Boolean getIsMarlo() {
    return isMarlo;
  }


  public Boolean getIsOneCGIAR() {
    return isOneCGIAR;
  }

  public String getName() {
    return this.name;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public void setIsMarlo(Boolean isMarlo) {
    this.isMarlo = isMarlo;
  }

  public void setIsOneCGIAR(Boolean isOneCGIAR) {
    this.isOneCGIAR = isOneCGIAR;
  }

  public void setName(String name) {
    this.name = name;
  }


}

