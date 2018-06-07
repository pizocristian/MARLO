package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 20, 2018 11:51:50 AM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectInnovationDeliverable generated by hbm2java
 */
public class ProjectInnovationDeliverable extends MarloBaseEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 8880718376889459922L;


  @Expose
  private ProjectInnovation projectInnovation;

  @Expose
  private Phase phase;

  @Expose
  private Deliverable deliverable;

  public ProjectInnovationDeliverable() {
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
    ProjectInnovationDeliverable other = (ProjectInnovationDeliverable) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public String getComposedName() {
    return this.getDeliverable().getComposedName();
  }


  public Deliverable getDeliverable() {
    return deliverable;
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

  public Deliverable getRelationship() {
    return deliverable;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  @Override
  public boolean isActive() {
    return true;
  }


  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProjectInnovation(ProjectInnovation projectInnovation) {
    this.projectInnovation = projectInnovation;
  }


}

