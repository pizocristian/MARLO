package org.cgiar.ccafs.marlo.data.model;

import java.util.Date;

import com.google.gson.annotations.Expose;

// Generated Apr 18, 2017 3:48:50 PM by Hibernate Tools 3.4.0.CR1


/**
 * PartnerRequest generated by hbm2java
 */
public class PartnerRequest implements java.io.Serializable {


  @Expose
  private Long id;


  @Expose
  private LocElement locElement;

  @Expose
  private User modifiedBy;

  @Expose
  private User createdBy;

  @Expose
  private InstitutionType institutionType;

  @Expose
  private String partnerName;

  @Expose
  private String acronym;


  @Expose
  private String webPage;

  @Expose
  private Boolean acepted;

  @Expose
  private boolean active;

  @Expose
  private String modificationJustification;

  @Expose
  private Date activeSince;

  public PartnerRequest() {
  }

  public PartnerRequest(boolean active) {
    this.active = active;
  }

  public PartnerRequest(LocElement locElement, User modifiedBy, User createdBy, InstitutionType institutionType,
    String partnerName, String acronym, String webPage, Boolean acepted, boolean active,
    String modificationJustification) {
    this.locElement = locElement;
    this.modifiedBy = modifiedBy;
    this.createdBy = createdBy;
    this.institutionType = institutionType;
    this.partnerName = partnerName;
    this.acronym = acronym;
    this.webPage = webPage;
    this.acepted = acepted;
    this.active = active;
    this.modificationJustification = modificationJustification;
  }


  public Boolean getAcepted() {
    return acepted;
  }


  public String getAcronym() {
    return acronym;
  }


  public Date getActiveSince() {
    return activeSince;
  }

  public String getCountryInfo() {
    return this.locElement.getName();
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public Long getId() {
    return id;
  }


  public InstitutionType getInstitutionType() {
    return institutionType;
  }

  public LocElement getLocElement() {
    return locElement;
  }


  public String getModificationJustification() {
    return modificationJustification;
  }


  public User getModifiedBy() {
    return modifiedBy;
  }

  public String getPartnerInfo() {
    if (this.acronym != null && !this.acronym.isEmpty()) {
      return this.acronym + " - " + this.partnerName;
    }
    return this.partnerName;
  }


  public String getPartnerName() {
    return partnerName;
  }

  public String getWebPage() {
    return webPage;
  }

  public boolean isActive() {
    return active;
  }


  public void setAcepted(Boolean acepted) {
    this.acepted = acepted;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
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

  public void setInstitutionType(InstitutionType institutionType) {
    this.institutionType = institutionType;
  }

  public void setLocElement(LocElement locElement) {
    this.locElement = locElement;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setPartnerName(String partnerName) {
    this.partnerName = partnerName;
  }

  public void setWebPage(String webPage) {
    this.webPage = webPage;
  }


}

