package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 5, 2018 11:32:15 AM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * PowbEvidence generated by hbm2java
 */
public class PowbCrpStaffing extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -8817130891128431256L;

  private PowbSynthesis powbSynthesis;

  @Expose
  private String staffingIssues;

  public PowbCrpStaffing() {
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
  }


  public String getStaffingIssues() {
    return staffingIssues;
  }

  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }


  public void setStaffingIssues(String staffingIssues) {
    this.staffingIssues = staffingIssues;
  }

  @Override
  public String toString() {
    return "PowbCrpStaffing [id=" + this.getId() + ", powbSynthesis=" + powbSynthesis + ", staffingIssues="
      + staffingIssues + "]";
  }

}

