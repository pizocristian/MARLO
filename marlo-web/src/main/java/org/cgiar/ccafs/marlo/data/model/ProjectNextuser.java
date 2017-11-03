package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 25, 2016 11:50:53 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * ProjectNextuser generated by hbm2java
 */
public class ProjectNextuser implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -6144197606440114685L;

  @Expose
  private Long id;


  @Expose
  private ProjectOutcome projectOutcome;

  @Expose
  private User createdBy;

  @Expose
  private User modifiedBy;

  @Expose
  private String nextUser;

  @Expose
  private String knowledge;

  @Expose
  private String strategies;

  @Expose
  private boolean active;

  @Expose
  private Date activeSince;

  @Expose
  private String modificationJustification;

  public ProjectNextuser() {
  }

  public ProjectNextuser(ProjectOutcome projectOutcome, User usersByModifiedBy, boolean isActive, Date activeSince,
    String modificationJustification) {
    this.projectOutcome = projectOutcome;
    this.modifiedBy = usersByModifiedBy;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }

  public ProjectNextuser(ProjectOutcome projectOutcome, User usersByCreatedBy, User usersByModifiedBy, String nextUser,
    String knowledge, String strategies, boolean isActive, Date activeSince, String modificationJustification) {
    this.projectOutcome = projectOutcome;
    this.createdBy = usersByCreatedBy;
    this.modifiedBy = usersByModifiedBy;
    this.nextUser = nextUser;
    this.knowledge = knowledge;
    this.strategies = strategies;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
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
    ProjectNextuser other = (ProjectNextuser) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }


  @Override
  public Long getId() {
    return id;
  }


  public String getKnowledge() {
    return knowledge;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }


  @Override
  public String getModificationJustification() {
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public String getNextUser() {
    return nextUser;
  }


  public ProjectOutcome getProjectOutcome() {
    return projectOutcome;
  }


  public String getStrategies() {
    return strategies;
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
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setKnowledge(String knowledge) {
    this.knowledge = knowledge;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setNextUser(String nextUser) {
    this.nextUser = nextUser;
  }


  public void setProjectOutcome(ProjectOutcome projectOutcome) {
    this.projectOutcome = projectOutcome;
  }


  public void setStrategies(String strategies) {
    this.strategies = strategies;
  }

  @Override
  public String toString() {
    return "ProjectNextuser [id=" + id + ", projectOutcome=" + projectOutcome + ", nextUser=" + nextUser
      + ", knowledge=" + knowledge + ", strategies=" + strategies + "]";
  }


}

