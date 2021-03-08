package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 27, 2019 2:52:09 PM by Hibernate Tools 5.3.6.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisSrfProgressTargets generated by hbm2java
 */
public class ReportSynthesisSrfProgressTargetContribution extends MarloAuditableEntity
  implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 1867695696627239380L;

  @Expose
  private ReportSynthesisSrfProgress reportSynthesisSrfProgress;

  @Expose
  private SrfSloIndicatorTarget srfSloIndicatorTarget;

  @Expose
  private Boolean hasEvidence;

  public ReportSynthesisSrfProgressTargetContribution() {
  }

  public Boolean getHasEvidence() {
    return hasEvidence;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public ReportSynthesisSrfProgress getReportSynthesisSrfProgress() {
    return reportSynthesisSrfProgress;
  }

  public SrfSloIndicatorTarget getSrfSloIndicatorTarget() {
    return srfSloIndicatorTarget;
  }

  public void setHasEvidence(Boolean hasEvidence) {
    this.hasEvidence = hasEvidence;
  }

  public void setReportSynthesisSrfProgress(ReportSynthesisSrfProgress reportSynthesisSrfProgress) {
    this.reportSynthesisSrfProgress = reportSynthesisSrfProgress;
  }

  public void setSrfSloIndicatorTarget(SrfSloIndicatorTarget srfSloIndicatorTarget) {
    this.srfSloIndicatorTarget = srfSloIndicatorTarget;
  }

}

