package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 17, 2016 3:47:49 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ProjectClusterActivity generated by hbm2java
 */
public class ProjectClusterActivity extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 7447742527635093703L;

  @Expose
  private CrpClusterOfActivity crpClusterOfActivity;

  @Expose
  private Project project;
  @Expose
  private Phase phase;

  public ProjectClusterActivity() {
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
    ProjectClusterActivity other = (ProjectClusterActivity) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public CrpClusterOfActivity getCrpClusterOfActivity() {
    return crpClusterOfActivity;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }


  public Phase getPhase() {
    return phase;
  }


  public Project getProject() {
    return project;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }


  public void setCrpClusterOfActivity(CrpClusterOfActivity crpClusterOfActivity) {
    this.crpClusterOfActivity = crpClusterOfActivity;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public String toString() {
    return "ProjectClusterActivity [id=" + this.getId() + ", crpClusterOfActivity=" + crpClusterOfActivity
      + ", project=" + project + "]";
  }


}

