package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import java.util.Date;

/**
 * IpProjectContributionOverviews generated by hbm2java
 */
public class IpProjectContributionOverview implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -3171833178725261483L;
  private Long id;
  private IpElement ipElement;
  private Project project;
  private User createdBy;
  private User modifiedBy;
  private int year;
  private String anualContribution;
  private String briefSummary;
  private String genderContribution;
  private String summaryGender;
  private boolean active;

  private Date activeSince;

  private String modificationJustification;

  public Date getActiveSince() {
    return activeSince;
  }

  public String getAnualContribution() {
    return anualContribution;
  }

  public String getBriefSummary() {
    return briefSummary;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public String getGenderContribution() {
    return genderContribution;
  }

  public Long getId() {
    return id;
  }

  public IpElement getIpElement() {
    return ipElement;
  }

  public String getModificationJustification() {
    return modificationJustification;
  }

  public User getModifiedBy() {
    return modifiedBy;
  }

  public Project getProject() {
    return project;
  }

  public String getSummaryGender() {
    return summaryGender;
  }

  public int getYear() {
    return year;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setAnualContribution(String anualContribution) {
    this.anualContribution = anualContribution;
  }

  public void setBriefSummary(String briefSummary) {
    this.briefSummary = briefSummary;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setGenderContribution(String genderContribution) {
    this.genderContribution = genderContribution;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIpElement(IpElement ipElement) {
    this.ipElement = ipElement;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void setSummaryGender(String summaryGender) {
    this.summaryGender = summaryGender;
  }

  public void setYear(int year) {
    this.year = year;
  }


}

