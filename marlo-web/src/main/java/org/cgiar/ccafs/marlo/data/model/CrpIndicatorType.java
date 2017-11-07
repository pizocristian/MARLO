package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpIndicatorTypes generated by hbm2java
 */
public class CrpIndicatorType implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 8563848764680434854L;


  @Expose
  private Long id;
  @Expose
  private String name;
  private Set<CrpIndicator> crpIndicators = new HashSet<CrpIndicator>(0);

  public CrpIndicatorType() {
  }

  public CrpIndicatorType(String name) {
    this.name = name;
  }


  public CrpIndicatorType(String name, Set<CrpIndicator> crpIndicatorses) {
    this.name = name;
    this.crpIndicators = crpIndicatorses;
  }

  public Set<CrpIndicator> getCrpIndicators() {
    return this.crpIndicators;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  @Override
  public String getModificationJustification() {

    return "";
  }

  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public boolean isActive() {

    return true;
  }


  public void setCrpIndicators(Set<CrpIndicator> crpIndicatorses) {
    this.crpIndicators = crpIndicatorses;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "CrpIndicatorType [id=" + id + ", name=" + name + "]";
  }


}

