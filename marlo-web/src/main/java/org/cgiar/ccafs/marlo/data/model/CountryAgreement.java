package org.cgiar.ccafs.marlo.data.model;
// Generated Sep 8, 2017 2:12:52 PM by Hibernate Tools 5.2.5.Final


/**
 * CountriesAgreement generated by hbm2java
 */
public class CountryAgreement extends MarloBaseEntity implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Agreement agreements;
  private String code;
  private String description;
  private Double percentage;

  public CountryAgreement() {
  }

  public Agreement getAgreements() {
    return this.agreements;
  }

  public String getCode() {
    return this.code;
  }

  public String getDescription() {
    return this.description;
  }

  public Double getPercentage() {
    return this.percentage;
  }

  public void setAgreements(Agreement agreements) {
    this.agreements = agreements;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPercentage(Double percentage) {
    this.percentage = percentage;
  }


}

