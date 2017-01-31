package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 12, 2017 8:19:41 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpsPandr generated by hbm2java
 */
public class CrpPandr implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 6821920604810923220L;
  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String acronym;
  @Expose
  private boolean active;
  private Set<ProjectCrpContribution> projectCrpContributions = new HashSet<ProjectCrpContribution>(0);


  public CrpPandr() {
  }


  public String getAcronym() {
    return acronym;
  }


  @Override
  public Long getId() {
    return id;
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
    return name;
  }


  public Set<ProjectCrpContribution> getProjectCrpContributions() {
    return projectCrpContributions;
  }


  @Override
  public boolean isActive() {
    return active;
  }


  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setActive(boolean active) {
    this.active = active;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setName(String name) {
    this.name = name;
  }


  public void setProjectCrpContributions(Set<ProjectCrpContribution> projectCrpContributions) {
    this.projectCrpContributions = projectCrpContributions;
  }


}

