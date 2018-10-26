package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 28, 2017 2:21:11 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * DeliverableInfo generated by hbm2java
 */
public class DeliverableInfo extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -8647145762226231826L;
  @Expose
  private CrpClusterKeyOutput crpClusterKeyOutput;
  @Expose
  private CrpProgramOutcome crpProgramOutcome;
  @Expose
  private DeliverableType deliverableType;

  private Deliverable deliverable;
  @Expose
  private Phase phase;
  @Expose
  private String title;
  @Expose
  private String description;
  @Expose
  private String typeOther;
  @Expose
  private Integer newExpectedYear;
  @Expose
  private int year;
  @Expose
  private Integer status;
  @Expose
  private String statusDescription;
  @Expose
  private Boolean crossCuttingGender;
  @Expose
  private Boolean crossCuttingYouth;
  @Expose
  private Boolean crossCuttingCapacity;
  @Expose
  private Boolean crossCuttingNa;
  @Expose
  private Boolean adoptedLicense;
  @Expose
  private String license;
  @Expose
  private String otherLicense;
  @Expose
  private Boolean allowModifications;
  @Expose
  private Long crossCuttingScoreGender;
  @Expose
  private Long crossCuttingScoreYouth;
  @Expose
  private Long crossCuttingScoreCapacity;
  @Expose
  private Boolean isLocationGlobal;
  @Expose
  private RepIndGeographicScope geographicScope;
  @Expose
  private RepIndRegion region;


  public DeliverableInfo() {
  }


  public Boolean getAdoptedLicense() {
    return adoptedLicense;
  }


  public Boolean getAllowModifications() {
    return allowModifications;
  }

  public String getCapDevScoreName() {
    if (this.crossCuttingScoreCapacity != null) {
      if (this.crossCuttingScoreCapacity == Long.valueOf(CrossCuttingScoreEnum.SIGNIFICANT.getScoreId())) {
        return CrossCuttingScoreEnum.SIGNIFICANT.getScore();
      }

      if (this.crossCuttingScoreCapacity == Long.valueOf(CrossCuttingScoreEnum.PRINCIPAL.getScoreId())) {
        return CrossCuttingScoreEnum.PRINCIPAL.getScore();
      }
    }
    return null;
  }


  public Boolean getCrossCuttingCapacity() {
    return crossCuttingCapacity;
  }

  public Boolean getCrossCuttingGender() {
    return crossCuttingGender;
  }

  public Boolean getCrossCuttingNa() {
    return crossCuttingNa;
  }

  public Long getCrossCuttingScoreCapacity() {
    return crossCuttingScoreCapacity;
  }

  public Long getCrossCuttingScoreGender() {
    return crossCuttingScoreGender;
  }

  public Long getCrossCuttingScoreYouth() {
    return crossCuttingScoreYouth;
  }


  public Boolean getCrossCuttingYouth() {
    return crossCuttingYouth;
  }

  public CrpClusterKeyOutput getCrpClusterKeyOutput() {
    return crpClusterKeyOutput;
  }


  public CrpProgramOutcome getCrpProgramOutcome() {
    return crpProgramOutcome;
  }

  public Deliverable getDeliverable() {
    return deliverable;
  }

  public DeliverableType getDeliverableType() {
    return deliverableType;
  }


  public String getDescription() {
    return description;
  }


  public String getGenderScoreName() {
    if (this.crossCuttingScoreGender != null) {
      if (this.crossCuttingScoreGender == Long.valueOf(CrossCuttingScoreEnum.SIGNIFICANT.getScoreId())) {
        return CrossCuttingScoreEnum.SIGNIFICANT.getScore();
      }

      if (this.crossCuttingScoreGender == Long.valueOf(CrossCuttingScoreEnum.PRINCIPAL.getScoreId())) {
        return CrossCuttingScoreEnum.PRINCIPAL.getScore();
      }
    }
    return null;
  }


  public RepIndGeographicScope getGeographicScope() {
    return geographicScope;
  }


  public Boolean getIsLocationGlobal() {
    return isLocationGlobal;
  }


  public String getLicense() {
    return license;
  }


  public String getLicenseType() {
    if (license != null) {
      try {
        return LicensesTypeEnum.license(license).getValue();
      } catch (Exception e) {
        return null;
      }
    }
    return null;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public Integer getNewExpectedYear() {
    return newExpectedYear;
  }


  public String getOtherLicense() {
    return otherLicense;
  }


  public Phase getPhase() {
    return phase;
  }

  public RepIndRegion getRegion() {
    return region;
  }


  public Integer getStatus() {
    return status;
  }


  public String getStatusDescription() {
    return statusDescription;
  }


  public String getStatusName(Phase phase) {


    try {
      if (this.status != null) {
        if (status == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())) {
          if (phase.getYear() > this.getYear()) {
            return "Ready to be reported on ";
          }
        }
      }
      if (this.status != null) {
        return ProjectStatusEnum.getValue(this.status).getStatus() != null
          ? ProjectStatusEnum.getValue(this.status).getStatus() : "";
      } else {
        return "";
      }
    } catch (Exception e) {
      return "";
    }
  }


  public String getTitle() {
    return title;
  }


  public String getTypeOther() {
    return typeOther;
  }


  public int getYear() {
    return year;
  }


  public String getYouthScoreName() {
    if (this.crossCuttingScoreYouth != null) {
      if (this.crossCuttingScoreYouth == Long.valueOf(CrossCuttingScoreEnum.SIGNIFICANT.getScoreId())) {
        return CrossCuttingScoreEnum.SIGNIFICANT.getScore();
      }

      if (this.crossCuttingScoreYouth == Long.valueOf(CrossCuttingScoreEnum.PRINCIPAL.getScoreId())) {
        return CrossCuttingScoreEnum.PRINCIPAL.getScore();
      }
    }
    return null;
  }


  public Boolean isRequieriedReporting(int year) {

    if (status == null && this.year <= year) {
      return true;
    }

    if (status != null && this.year <= year
      && status.intValue() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId())) {
      return true;
    }

    if (status != null && newExpectedYear != null && this.newExpectedYear <= year
      && status.intValue() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())) {
      return true;
    }
    if (status != null && this.year == year
      && status.intValue() == Integer.parseInt(ProjectStatusEnum.Complete.getStatusId())) {
      return true;
    }

    if (status != null && newExpectedYear != null && this.newExpectedYear <= year
      && status.intValue() == Integer.parseInt(ProjectStatusEnum.Complete.getStatusId())) {
      return true;
    }

    return false;
  }


  public boolean requeriedFair() {
    try {
      if (this.getDeliverableType().getFair()) {
        return true;
      }
      if (this.getDeliverableType().getDeliverableCategory().getFair()) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }


  public void setAdoptedLicense(Boolean adoptedLicense) {
    this.adoptedLicense = adoptedLicense;
  }


  public void setAllowModifications(Boolean allowModifications) {
    this.allowModifications = allowModifications;
  }


  public void setCrossCuttingCapacity(Boolean crossCuttingCapacity) {
    this.crossCuttingCapacity = crossCuttingCapacity;
  }


  public void setCrossCuttingGender(Boolean crossCuttingGender) {
    this.crossCuttingGender = crossCuttingGender;
  }


  public void setCrossCuttingNa(Boolean crossCuttingNa) {
    this.crossCuttingNa = crossCuttingNa;
  }


  public void setCrossCuttingScoreCapacity(Long crossCuttingScoreCapacity) {
    this.crossCuttingScoreCapacity = crossCuttingScoreCapacity;
  }


  public void setCrossCuttingScoreGender(Long crossCuttingScoreGender) {
    this.crossCuttingScoreGender = crossCuttingScoreGender;
  }


  public void setCrossCuttingScoreYouth(Long crossCuttingScoreYouth) {
    this.crossCuttingScoreYouth = crossCuttingScoreYouth;
  }


  public void setCrossCuttingYouth(Boolean crossCuttingYouth) {
    this.crossCuttingYouth = crossCuttingYouth;
  }


  public void setCrpClusterKeyOutput(CrpClusterKeyOutput crpClusterKeyOutput) {
    this.crpClusterKeyOutput = crpClusterKeyOutput;
  }


  public void setCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    this.crpProgramOutcome = crpProgramOutcome;
  }


  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }


  public void setDeliverableType(DeliverableType deliverableType) {
    this.deliverableType = deliverableType;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public void setGeographicScope(RepIndGeographicScope geographicScope) {
    this.geographicScope = geographicScope;
  }


  public void setIsLocationGlobal(Boolean isLocationGlobal) {
    this.isLocationGlobal = isLocationGlobal;
  }


  public void setLicense(String license) {
    this.license = license;
  }

  public void setNewExpectedYear(Integer newExpectedYear) {
    this.newExpectedYear = newExpectedYear;
  }

  public void setOtherLicense(String otherLicense) {
    this.otherLicense = otherLicense;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setRegion(RepIndRegion region) {
    this.region = region;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setStatusDescription(String statusDescription) {
    this.statusDescription = statusDescription;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public void setTypeOther(String typeOther) {
    this.typeOther = typeOther;
  }


  public void setYear(int year) {
    this.year = year;
  }

  public void updateDeliverableInfo(DeliverableInfo update) {
    this.setAdoptedLicense(update.getAdoptedLicense());
    this.setAllowModifications(update.getAllowModifications());
    this.setCrossCuttingCapacity(update.getCrossCuttingCapacity());
    this.setCrossCuttingNa(update.getCrossCuttingNa());
    this.setCrossCuttingYouth(update.getCrossCuttingYouth());
    this.setCrpClusterKeyOutput(update.getCrpClusterKeyOutput());
    this.setCrpProgramOutcome(update.getCrpProgramOutcome());
    this.setDeliverable(update.getDeliverable());
    this.setDescription(update.getDescription());
    this.setLicense(update.getLicense());
    this.setNewExpectedYear(update.getNewExpectedYear());
    this.setOtherLicense(update.getOtherLicense());
    this.setStatus(update.getStatus());
    this.setTitle(update.getTitle());
    this.setTypeOther(update.getTypeOther());
    this.setDeliverableType(update.getDeliverableType());
    this.setCrossCuttingGender(update.getCrossCuttingGender());
    this.setYear(update.getYear());
    this.setStatusDescription(update.getStatusDescription());
    this.setCrossCuttingScoreGender(update.getCrossCuttingScoreGender());
    this.setCrossCuttingScoreYouth(update.getCrossCuttingScoreYouth());
    this.setCrossCuttingScoreCapacity(update.getCrossCuttingScoreCapacity());
    this.setGeographicScope(update.getGeographicScope());
    this.setRegion(update.getRegion());
  }


}

