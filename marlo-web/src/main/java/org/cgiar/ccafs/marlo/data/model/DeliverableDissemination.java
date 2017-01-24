package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 5, 2017 7:38:48 AM by Hibernate Tools 3.4.0.CR1


import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * DeliverableDissemination generated by hbm2java
 */
public class DeliverableDissemination implements java.io.Serializable {


  private static final long serialVersionUID = 3873609958921714313L;


  @Expose
  private Long id;


  @Expose
  private Deliverable deliverable;

  @Expose
  private Boolean isOpenAccess;

  @Expose
  private Boolean intellectualProperty;

  @Expose
  private Boolean limitedExclusivity;

  @Expose
  private Boolean restrictedUseAgreement;

  @Expose
  private Date restrictedAccessUntil;

  @Expose
  private Boolean effectiveDateRestriction;

  @Expose
  private Date restrictedEmbargoed;

  @Expose
  private Boolean alreadyDisseminated;

  @Expose
  private String disseminationChannel;

  @Expose
  private String disseminationUrl;

  @Expose
  private String disseminationChannelName;


  public DeliverableDissemination() {
  }

  public DeliverableDissemination(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public DeliverableDissemination(Deliverable deliverable, Boolean isOpenAccess, Boolean intellectualProperty,
    Boolean limitedExclusivity, Boolean restrictedUseAgreement, Date restrictedAccessUntil,
    Boolean effectiveDateRestriction, Date restrictedEmbargoed, Boolean alreadyDisseminated,
    String disseminationChannel, String disseminationUrl, String disseminationChannelName) {
    this.deliverable = deliverable;
    this.isOpenAccess = isOpenAccess;
    this.intellectualProperty = intellectualProperty;
    this.limitedExclusivity = limitedExclusivity;
    this.restrictedUseAgreement = restrictedUseAgreement;
    this.restrictedAccessUntil = restrictedAccessUntil;
    this.effectiveDateRestriction = effectiveDateRestriction;
    this.restrictedEmbargoed = restrictedEmbargoed;
    this.alreadyDisseminated = alreadyDisseminated;
    this.disseminationChannel = disseminationChannel;
    this.disseminationUrl = disseminationUrl;
    this.disseminationChannelName = disseminationChannelName;
  }


  public Boolean getAlreadyDisseminated() {
    return alreadyDisseminated;
  }


  public Deliverable getDeliverable() {
    return deliverable;
  }


  public String getDisseminationChannel() {
    return disseminationChannel;
  }


  public String getDisseminationChannelName() {
    return disseminationChannelName;
  }


  public String getDisseminationUrl() {
    return disseminationUrl;
  }


  public Boolean getEffectiveDateRestriction() {
    return effectiveDateRestriction;
  }


  public Long getId() {
    return id;
  }


  public Boolean getIntellectualProperty() {
    return intellectualProperty;
  }


  public Boolean getIsOpenAccess() {
    return isOpenAccess;
  }


  public Boolean getLimitedExclusivity() {
    return limitedExclusivity;
  }


  public Date getRestrictedAccessUntil() {
    return restrictedAccessUntil;
  }

  public Date getRestrictedEmbargoed() {
    return restrictedEmbargoed;
  }

  public Boolean getRestrictedUseAgreement() {
    return restrictedUseAgreement;
  }

  public String getType() {
    if (intellectualProperty != null && intellectualProperty) {
      return DisseminationTypeEnum.INTECLLECTUAL_PROPERTY.getValue();
    }
    if (limitedExclusivity != null && limitedExclusivity) {
      return DisseminationTypeEnum.LIMITED_EXCLUSIVITY.getValue();
    }
    if (restrictedUseAgreement != null && restrictedUseAgreement) {
      return DisseminationTypeEnum.RESTRICTED_USER_AGREEMENT.getValue();
    }
    if (effectiveDateRestriction != null && effectiveDateRestriction) {
      return DisseminationTypeEnum.EMBARGOED_PERIODS.getValue();
    }

    return null;
  }

  public void setAlreadyDisseminated(Boolean alreadyDisseminated) {
    this.alreadyDisseminated = alreadyDisseminated;
  }

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setDisseminationChannel(String disseminationChannel) {
    this.disseminationChannel = disseminationChannel;
  }

  public void setDisseminationChannelName(String disseminationChannelName) {
    this.disseminationChannelName = disseminationChannelName;
  }

  public void setDisseminationUrl(String disseminationUrl) {
    this.disseminationUrl = disseminationUrl;
  }

  public void setEffectiveDateRestriction(Boolean effectiveDateRestriction) {
    this.effectiveDateRestriction = effectiveDateRestriction;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIntellectualProperty(Boolean intellectualProperty) {
    this.intellectualProperty = intellectualProperty;
  }

  public void setIsOpenAccess(Boolean isOpenAccess) {
    this.isOpenAccess = isOpenAccess;
  }

  public void setLimitedExclusivity(Boolean limitedExclusivity) {
    this.limitedExclusivity = limitedExclusivity;
  }

  public void setRestrictedAccessUntil(Date restrictedAccessUntil) {
    this.restrictedAccessUntil = restrictedAccessUntil;
  }

  public void setRestrictedEmbargoed(Date restrictedEmbargoed) {
    this.restrictedEmbargoed = restrictedEmbargoed;
  }


  public void setRestrictedUseAgreement(Boolean restrictedUseAgreement) {
    this.restrictedUseAgreement = restrictedUseAgreement;
  }


}

