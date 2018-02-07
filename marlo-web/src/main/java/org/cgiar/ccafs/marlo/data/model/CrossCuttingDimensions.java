package org.cgiar.ccafs.marlo.data.model;
// Generated Feb 6, 2018 11:25:23 AM by Hibernate Tools 5.2.5.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CrossCuttingDimensions generated by hbm2java
 */
public class CrossCuttingDimensions implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -4319505222454498554L;

  @Expose
  private Long id;

  private PowbSynthesis powbSynthesis;
  @Expose
  private String summarize;
  @Expose
  private String assets;
  @Expose
  private boolean isActive;
  @Expose
  private Long createdBy;
  @Expose
  private Long modifiedBy;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;


  public CrossCuttingDimensions() {
  }


  public Date getActiveSince() {
    return this.activeSince;
  }

  public String getAssets() {
    return this.assets;
  }

  public Long getCreatedBy() {
    return this.createdBy;
  }

  @Override
  public Long getId() {
    return this.id;
  }


  @Override
  public String getLogDeatil() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getModificationJustification() {
    return this.modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    // TODO Auto-generated method stub
    return null;
  }

  public PowbSynthesis getPowbSynthesis() {
    return powbSynthesis;
  }


  public String getSummarize() {
    return this.summarize;
  }


  @Override
  public boolean isActive() {
    return isActive;
  }

  public boolean isIsActive() {
    return this.isActive;
  }


  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }


  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setAssets(String assets) {
    this.assets = assets;
  }


  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(Long modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setPowbSynthesis(PowbSynthesis powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }


  public void setSummarize(String summarize) {
    this.summarize = summarize;
  }


}

