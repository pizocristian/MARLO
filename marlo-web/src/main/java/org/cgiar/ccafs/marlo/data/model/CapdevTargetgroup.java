package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated Jun 9, 2017 1:20:25 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CapdevTargetgroup generated by hbm2java
 */
public class CapdevTargetgroup extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Expose
  private CapacityDevelopment capacityDevelopment;

  @Expose
  private TargetGroup targetGroups;

  public CapdevTargetgroup() {
  }

  public CapacityDevelopment getCapacityDevelopment() {
    return this.capacityDevelopment;
  }

  @Override
  public String getLogDeatil() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public TargetGroup getTargetGroups() {
    return this.targetGroups;
  }


  public void setCapacityDevelopment(CapacityDevelopment capacityDevelopment) {
    this.capacityDevelopment = capacityDevelopment;
  }


  public void setTargetGroups(TargetGroup targetGroups) {
    this.targetGroups = targetGroups;
  }


}

