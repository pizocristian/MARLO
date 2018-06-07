package org.cgiar.ccafs.marlo.data.model;
// Generated May 21, 2018 2:10:20 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesis generated by hbm2java
 */
public class ReportSynthesis extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -1347662217447754044L;


  @Expose
  private Phase phase;


  @Expose
  private LiaisonInstitution liaisonInstitution;


  @Expose
  private ReportSynthesisCrpProgress reportSynthesisCrpProgress;

  @Expose
  private ReportSynthesisFinancialSummary reportSynthesisFinancialSummary;

  @Expose
  private ReportSynthesisGovernance reportSynthesisGovernance;

  @Expose
  private ReportSynthesisRisk reportSynthesisRisk;


  @Expose
  private ReportSynthesisCrossCuttingDimension reportSynthesisCrossCuttingDimension;

  @Expose
  private ReportSynthesisFlagshipProgress reportSynthesisFlagshipProgress;

  @Expose
  private ReportSynthesisProgramVariance reportSynthesisProgramVariance;


  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);


  public ReportSynthesis() {
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
    ReportSynthesis other = (ReportSynthesis) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public LiaisonInstitution getLiaisonInstitution() {
    return liaisonInstitution;
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

  public Phase getPhase() {
    return phase;
  }

  public ReportSynthesisCrossCuttingDimension getReportSynthesisCrossCuttingDimension() {
    return reportSynthesisCrossCuttingDimension;
  }

  public ReportSynthesisCrpProgress getReportSynthesisCrpProgress() {
    return reportSynthesisCrpProgress;
  }

  public ReportSynthesisFinancialSummary getReportSynthesisFinancialSummary() {
    return reportSynthesisFinancialSummary;
  }

  public ReportSynthesisFlagshipProgress getReportSynthesisFlagshipProgress() {
    return reportSynthesisFlagshipProgress;
  }


  public ReportSynthesisGovernance getReportSynthesisGovernance() {
    return reportSynthesisGovernance;
  }


  public ReportSynthesisProgramVariance getReportSynthesisProgramVariance() {
    return reportSynthesisProgramVariance;
  }


  public ReportSynthesisRisk getReportSynthesisRisk() {
    return reportSynthesisRisk;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }


  public void setLiaisonInstitution(LiaisonInstitution liaisonInstitution) {
    this.liaisonInstitution = liaisonInstitution;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void
    setReportSynthesisCrossCuttingDimension(ReportSynthesisCrossCuttingDimension reportSynthesisCrossCuttingDimension) {
    this.reportSynthesisCrossCuttingDimension = reportSynthesisCrossCuttingDimension;
  }

  public void setReportSynthesisCrpProgress(ReportSynthesisCrpProgress reportSynthesisCrpProgress) {
    this.reportSynthesisCrpProgress = reportSynthesisCrpProgress;
  }

  public void setReportSynthesisFinancialSummary(ReportSynthesisFinancialSummary reportSynthesisFinancialSummary) {
    this.reportSynthesisFinancialSummary = reportSynthesisFinancialSummary;
  }

  public void setReportSynthesisFlagshipProgress(ReportSynthesisFlagshipProgress reportSynthesisFlagshipProgress) {
    this.reportSynthesisFlagshipProgress = reportSynthesisFlagshipProgress;
  }

  public void setReportSynthesisGovernance(ReportSynthesisGovernance reportSynthesisGovernance) {
    this.reportSynthesisGovernance = reportSynthesisGovernance;
  }

  public void setReportSynthesisProgramVariance(ReportSynthesisProgramVariance reportSynthesisProgramVariance) {
    this.reportSynthesisProgramVariance = reportSynthesisProgramVariance;
  }


  public void setReportSynthesisRisk(ReportSynthesisRisk reportSynthesisRisk) {
    this.reportSynthesisRisk = reportSynthesisRisk;
  }


  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }


}

