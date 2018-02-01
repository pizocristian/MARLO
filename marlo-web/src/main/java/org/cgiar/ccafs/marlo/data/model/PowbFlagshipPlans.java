package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 18, 2017 3:03:15 PM by Hibernate Tools 5.2.3.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;


/**
 * FundingSourceInfo generated by hbm2java
 */
public class PowbFlagshipPlans implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -4345297885836757489L;

  @Expose
  private Long id;

  @Expose
  private String planSummary;

  @Expose
  private FileDB flagshipProgramFile;

  @Expose
  private PowbSynthesis powbSynthesis;

  @Expose
  private boolean active;

  @Expose
  private User createdBy;

  @Expose
  private Date activeSince;

  @Expose
  private User modifiedBy;

  public PowbFlagshipPlans() {
  }

  public PowbFlagshipPlans(Long id, PowbSynthesis powbSynthesis, boolean active, User createdBy, Date activeSince,
    User modifiedBy) {
    this.id = id;
    this.powbSynthesis = powbSynthesis;
    this.active = active;
    this.createdBy = createdBy;
    this.activeSince = activeSince;
    this.modifiedBy = modifiedBy;
  }

  public PowbFlagshipPlans(Long id, String planSummary, FileDB flagshipProgramFile, PowbSynthesis powbSynthesis,
    boolean active, User createdBy, Date activeSince, User modifiedBy) {
    super();
    this.id = id;
    this.planSummary = planSummary;
    this.flagshipProgramFile = flagshipProgramFile;
    this.powbSynthesis = powbSynthesis;
    this.active = active;
    this.createdBy = createdBy;
    this.activeSince = activeSince;
    this.modifiedBy = modifiedBy;
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
    PowbFlagshipPlans other = (PowbFlagshipPlans) obj;
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


  public FileDB getFlagshipProgramFile() {
    return flagshipProgramFile;
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
    return modifiedBy;
  }

  public String getPlanSummary() {
    return planSummary;
  }


  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
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

  public void setActive(boolean active) {
    this.active = active;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setFlagshipProgramFile(FileDB flagshipProgramFile) {
    this.flagshipProgramFile = flagshipProgramFile;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setPlanSummary(String planSummary) {
    this.planSummary = planSummary;
  }

  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }

  @Override
  public String toString() {
    return "PowbFlagshipPlans [id=" + id + ", planSummary=" + planSummary + ", flagshipProgramFile="
      + flagshipProgramFile + ", powbSynthesis=" + powbSynthesis + "]";
  }


}