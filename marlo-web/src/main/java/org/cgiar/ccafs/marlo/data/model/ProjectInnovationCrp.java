package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 23, 2018 2:35:54 PM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectInnovationCrp generated by hbm2java
 */
public class ProjectInnovationCrp implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 7399357387397427743L;


  @Expose
  private Long id;


  @Expose
  private ProjectInnovation projectInnovation;

  @Expose
  private Phase phase;

  @Expose
  private GlobalUnit globalUnit;

  public ProjectInnovationCrp() {
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ProjectInnovationCrp other = (ProjectInnovationCrp) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!this.getId().equals(other.id)) {
      return false;
    }
    return true;
  }


  public GlobalUnit getGlobalUnit() {
    return globalUnit;
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

  public Phase getPhase() {
    return phase;
  }

  public ProjectInnovation getProjectInnovation() {
    return projectInnovation;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean isActive() {
    return true;
  }


  public void setGlobalUnit(GlobalUnit globalUnit) {
    this.globalUnit = globalUnit;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProjectInnovation(ProjectInnovation projectInnovation) {
    this.projectInnovation = projectInnovation;
  }


}

