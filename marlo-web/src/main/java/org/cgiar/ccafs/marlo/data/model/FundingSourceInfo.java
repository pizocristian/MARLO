package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 18, 2017 3:03:15 PM by Hibernate Tools 5.2.3.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Calendar;
import java.util.Date;

import com.google.gson.annotations.Expose;


/**
 * FundingSourceInfo generated by hbm2java
 */
public class FundingSourceInfo implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = 7728001542598152285L;


  @Expose
  private Long id;

  @Expose
  private String title;

  @Expose
  private String description;

  @Expose
  private Date startDate;

  @Expose
  private Date endDate;

  @Expose
  private String financeCode;

  @Expose
  private String contactPersonName;

  @Expose
  private String contactPersonEmail;
  @Expose
  private FileDB file;

  @Expose
  private Institution directDonor;

  @Expose
  private Institution originalDonor;


  @Expose
  private Integer status;


  @Expose
  private BudgetType budgetType;

  @Expose
  private PartnerDivision partnerDivision;

  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  @Expose
  private boolean global;

  @Expose
  private Boolean w1w2;

  @Expose
  private Phase phase;

  @Expose
  private FundingSource fundingSource;
  @Expose
  private Boolean synced;
  @Expose
  private Date syncedDate;

  @Expose
  private Date extensionDate;

  @Expose
  private Double grantAmount;

  @Expose
  private FileDB fileResearch;

  @Expose
  private boolean hasFileResearch;


  public FundingSourceInfo() {
  }


  public FundingSourceInfo(User modifiedBy, Institution institution, String description, Date startDate, Date endDate,
    String financeCode, String contactPersonName, String contactPersonEmail, BudgetType type,
    String modificationJustification, FundingSource fundingSource, Phase phase) {
    this.modifiedBy = modifiedBy;
    this.directDonor = institution;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.financeCode = financeCode;
    this.contactPersonName = contactPersonName;
    this.contactPersonEmail = contactPersonEmail;
    this.budgetType = type;
    this.modificationJustification = modificationJustification;
    this.fundingSource = fundingSource;
    this.phase = phase;
  }


  public FundingSourceInfo(User modifiedBy, String modificationJustification) {
    this.modifiedBy = modifiedBy;
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
    FundingSourceInfo other = (FundingSourceInfo) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public BudgetType getBudgetType() {
    return budgetType;
  }


  public String getComposedName() {
    try {
      return "<b> (FS" + this.id + ") " + this.getBudgetType().getName() + "</b> - " + this.title;
    } catch (Exception e) {
      return "<b> (FS" + this.id + ") </b> - " + this.title;
    }
  }


  public String getContactPersonEmail() {
    return contactPersonEmail;
  }


  public String getContactPersonName() {
    return contactPersonName;
  }

  public String getDescription() {
    return description;
  }

  public Institution getDirectDonor() {
    return directDonor;
  }

  public Date getEndDate() {
    return endDate;
  }


  public Date getExtensionDate() {
    return extensionDate;
  }


  public FileDB getFile() {
    return file;
  }


  public FileDB getFileResearch() {
    return fileResearch;
  }


  public String getFinanceCode() {
    return financeCode;
  }

  public FundingSource getFundingSource() {
    return fundingSource;
  }


  public Double getGrantAmount() {
    return grantAmount;
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
    User u = new User();
    u.setId(new Long(3));
    return u;
  }

  public Institution getOriginalDonor() {
    return originalDonor;
  }

  public PartnerDivision getPartnerDivision() {
    return partnerDivision;
  }

  public Phase getPhase() {
    return phase;
  }


  public Date getStartDate() {
    return startDate;
  }


  public Integer getStatus() {
    return status;
  }

  public String getStatusName() {
    if (status != null && status.intValue() != -1) {
      ProjectStatusEnum statusEnum = ProjectStatusEnum.getValue(status.intValue());
      if (statusEnum != null) {
        return statusEnum.getStatus();
      }
    }
    return "";

  }

  public Boolean getSynced() {
    return synced;
  }


  public Date getSyncedDate() {
    return syncedDate;
  }

  public String getTitle() {
    return title;
  }

  public Boolean getW1w2() {
    return w1w2;
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


  public boolean isGlobal() {
    return global;
  }


  public boolean isHasFileResearch() {
    return hasFileResearch;
  }


  public void setBudgetType(BudgetType budgetType) {
    this.budgetType = budgetType;
  }

  public void setContactPersonEmail(String contactPersonEmail) {
    this.contactPersonEmail = contactPersonEmail;
  }

  public void setContactPersonName(String contactPersonName) {
    this.contactPersonName = contactPersonName;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDirectDonor(Institution institution) {
    this.directDonor = institution;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public void setExtensionDate(Date extensionDate) {
    this.extensionDate = extensionDate;
  }


  public void setFile(FileDB file) {
    this.file = file;
  }


  public void setFileResearch(FileDB fileResearch) {
    this.fileResearch = fileResearch;
  }


  public void setFinanceCode(String financeCode) {
    this.financeCode = financeCode;
  }

  public void setFundingSource(FundingSource fundingSource) {
    this.fundingSource = fundingSource;
  }

  public void setGlobal(boolean global) {
    this.global = global;
  }

  public void setGrantAmount(Double grantAmount) {
    this.grantAmount = grantAmount;
  }

  public void setHasFileResearch(boolean hasFileResearch) {
    this.hasFileResearch = hasFileResearch;
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


  public void setOriginalDonor(Institution originalDonor) {
    this.originalDonor = originalDonor;
  }


  public void setPartnerDivision(PartnerDivision partnerDivision) {
    this.partnerDivision = partnerDivision;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }


  public void setSynced(Boolean synced) {
    this.synced = synced;
  }


  public void setSyncedDate(Date syncedDate) {
    this.syncedDate = syncedDate;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public void setW1w2(Boolean w1w2) {
    this.w1w2 = w1w2;
  }


  public void updateFundingSourceInfo(FundingSourceInfo fundingSourceInfoUpdate, Phase phase) {
    this.setBudgetType(fundingSourceInfoUpdate.getBudgetType());
    this.setEndDate(fundingSourceInfoUpdate.getEndDate());
    this.setModificationJustification(fundingSourceInfoUpdate.getModificationJustification());
    this.setModifiedBy(fundingSourceInfoUpdate.getModifiedBy());
    this.setStartDate(fundingSourceInfoUpdate.getStartDate());
    this.setStatus(fundingSourceInfoUpdate.getStatus());

    Calendar cal = Calendar.getInstance();
    if (fundingSourceInfoUpdate.getEndDate() != null) {
      cal.setTime(fundingSourceInfoUpdate.getEndDate());
      if (cal.get(Calendar.YEAR) < phase.getYear()) {
        if (fundingSourceInfoUpdate.getStatus() != null && fundingSourceInfoUpdate.getStatus().intValue() == Integer
          .parseInt(ProjectStatusEnum.Ongoing.getStatusId())) {
          this.setStatus(Integer.parseInt(ProjectStatusEnum.Complete.getStatusId()));
        }

      }
    }


    this.setTitle(fundingSourceInfoUpdate.getTitle());
    this.setDescription(fundingSourceInfoUpdate.getDescription());
    this.setContactPersonEmail(fundingSourceInfoUpdate.getContactPersonEmail());
    this.setContactPersonName(fundingSourceInfoUpdate.getContactPersonName());
    this.setFile(fundingSourceInfoUpdate.getFile());
    this.setFinanceCode(fundingSourceInfoUpdate.getFinanceCode());
    this.setGlobal(fundingSourceInfoUpdate.isGlobal());
    this.setDirectDonor(fundingSourceInfoUpdate.getDirectDonor());
    this.setPartnerDivision(fundingSourceInfoUpdate.getPartnerDivision());
    this.setW1w2(fundingSourceInfoUpdate.getW1w2());
    this.setFileResearch(fundingSourceInfoUpdate.getFileResearch());
    this.setHasFileResearch(fundingSourceInfoUpdate.isHasFileResearch());
  }

}

