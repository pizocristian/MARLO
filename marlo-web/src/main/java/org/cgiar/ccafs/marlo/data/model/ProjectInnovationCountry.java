package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 20, 2018 11:51:50 AM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectInnovationCountry generated by hbm2java
 */
public class ProjectInnovationCountry extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 647788757422554260L;

  @Expose
  private ProjectInnovation projectInnovation;


  @Expose
  private Phase phase;


  @Expose
  private LocElement locElement;

  public ProjectInnovationCountry() {
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
    ProjectInnovationCountry other = (ProjectInnovationCountry) obj;
    if (locElement == null) {
      if (other.locElement != null) {
        return false;
      }
    } else if (!locElement.getId().equals(other.locElement.getId())) {
      return false;
    }
    return true;
  }


  public LocElement getLocElement() {
    return locElement;
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
    result = prime * result + ((locElement == null) ? 0 : locElement.hashCode());
    return result;
  }

  @Override
  public boolean isActive() {
    return true;
  }


  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }


  @Override
  public void setModifiedBy(User modifiedBy) {

  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setProjectInnovation(ProjectInnovation projectInnovation) {
    this.projectInnovation = projectInnovation;
  }


}

