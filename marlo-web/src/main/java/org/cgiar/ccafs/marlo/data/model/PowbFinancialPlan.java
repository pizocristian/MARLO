package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 5, 2018 11:32:15 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * PowbEvidence generated by hbm2java
 */
public class PowbFinancialPlan implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -8817130891128431256L;


  @Expose
  private Long id;

  @Expose
  private User modifiedBy;

  private PowbSynthesis powbSynthesis;

  @Expose
  private User createdBy;

  @Expose
  private String financialPlanIssues;

  @Expose
  private boolean active;


  @Expose
  private Date activeSince;


  @Expose
  private String modificationJustification;

  public PowbFinancialPlan() {
  }

  public Date getActiveSince() {
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }

  public String getFinancialPlanIssues() {
    return financialPlanIssues;
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
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
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


  public void setFinancialPlanIssues(String financialPlanIssues) {
    this.financialPlanIssues = financialPlanIssues;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }

  @Override
  public String toString() {
    return "PowbCrpStaffing [id=" + id + ", powbSynthesis=" + powbSynthesis + ", financialPlanIssues="
      + financialPlanIssues + "]";
  }

}

