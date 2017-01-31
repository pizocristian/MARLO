package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 3, 2017 1:26:41 PM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * IpElements generated by hbm2java
 */
public class IpElement implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -6191644559184410233L;


  @Expose
  private Long id;

  @Expose
  private IpElementType ipElementType;
  @Expose
  private IpProgram ipProgram;


  @Expose
  private User modifiedBy;


  @Expose
  private User createdBy;
  @Expose
  private String description;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;
  private Set<IpProjectContributionOverview> ipProjectContributionOverviews =
    new HashSet<IpProjectContributionOverview>(0);
  private Set<IpProjectContribution> ipProjectContributionsForMogId = new HashSet<IpProjectContribution>(0);
  private Set<OutcomeSynthesy> outcomeSynthesis = new HashSet<OutcomeSynthesy>(0);
  private Set<IpProjectContribution> ipProjectContributionsForMidOutcomeId = new HashSet<IpProjectContribution>(0);
  private Set<IpProgramElement> ipProgramElements = new HashSet<IpProgramElement>(0);
  private Set<IpIndicator> ipIndicators = new HashSet<IpIndicator>(0);
  private List<IpIndicator> indicators;
  private Set<MogSynthesy> mogSynthesis = new HashSet<MogSynthesy>(0);
  private Set<IpRelationship> ipRelationshipsForParentId = new HashSet<IpRelationship>(0);
  private Set<IpRelationship> ipRelationshipsForChildId = new HashSet<IpRelationship>(0);

  public IpElement() {
  }


  public IpElement(IpElementType ipElementType, IpProgram ipProgram, User usersByModifiedBy, User usersByCreatedBy,
    boolean isActive, Date activeSince, String modificationJustification) {
    this.ipElementType = ipElementType;
    this.ipProgram = ipProgram;
    this.modifiedBy = usersByModifiedBy;
    this.createdBy = usersByCreatedBy;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }


  public IpElement(Long id) {
    super();
    this.id = id;
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
    IpElement other = (IpElement) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (id.longValue() != other.getId().longValue()) {
      return false;
    }
    return true;
  }


  public Date getActiveSince() {
    return activeSince;
  }

  public String getComposedId() {
    StringBuilder composedID = new StringBuilder();
    // composedID.append("O");
    if (this.ipProgram != null && this.ipProgram.getId() >= 5) {
      composedID.append(this.ipProgram != null ? this.ipProgram.getAcronym() : "p_null");
    } else {
      composedID.append(this.ipProgram != null ? this.ipProgram.getAcronym() : "p_null");
    }
    composedID.append("-");
    composedID.append(this.ipElementType != null ? this.ipElementType.getName() : "t_null");
    // composedID.append(" #");
    // composedID.append(this.id);
    return composedID.toString();
  }

  public User getCreatedBy() {
    return createdBy;
  }


  public String getDescription() {
    return description;
  }


  @Override
  public Long getId() {
    return id;
  }

  public List<IpIndicator> getIndicators() {
    return indicators;
  }

  public IpElementType getIpElementType() {
    return ipElementType;
  }


  public Set<IpIndicator> getIpIndicators() {
    return ipIndicators;
  }


  public IpProgram getIpProgram() {
    return ipProgram;
  }


  public Set<IpProgramElement> getIpProgramElements() {
    return ipProgramElements;
  }


  public Set<IpProjectContributionOverview> getIpProjectContributionOverviews() {
    return ipProjectContributionOverviews;
  }


  public Set<IpProjectContribution> getIpProjectContributionsForMidOutcomeId() {
    return ipProjectContributionsForMidOutcomeId;
  }


  public Set<IpProjectContribution> getIpProjectContributionsForMogId() {
    return ipProjectContributionsForMogId;
  }


  public Set<IpRelationship> getIpRelationshipsForChildId() {
    return ipRelationshipsForChildId;
  }


  public Set<IpRelationship> getIpRelationshipsForParentId() {
    return ipRelationshipsForParentId;
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
    return modifiedBy;
  }


  public Set<MogSynthesy> getMogSynthesis() {
    return mogSynthesis;
  }


  public Set<OutcomeSynthesy> getOutcomeSynthesis() {
    return outcomeSynthesis;
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
    return active;
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


  public void setDescription(String description) {
    this.description = description;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIndicators(List<IpIndicator> indicators) {
    this.indicators = indicators;
  }


  public void setIpElementType(IpElementType ipElementType) {
    this.ipElementType = ipElementType;
  }


  public void setIpIndicators(Set<IpIndicator> ipIndicators) {
    this.ipIndicators = ipIndicators;
  }


  public void setIpProgram(IpProgram ipProgram) {
    this.ipProgram = ipProgram;
  }


  public void setIpProgramElements(Set<IpProgramElement> ipProgramElements) {
    this.ipProgramElements = ipProgramElements;
  }


  public void setIpProjectContributionOverviews(Set<IpProjectContributionOverview> ipProjectContributionOverviews) {
    this.ipProjectContributionOverviews = ipProjectContributionOverviews;
  }


  public void
    setIpProjectContributionsForMidOutcomeId(Set<IpProjectContribution> ipProjectContributionsForMidOutcomeId) {
    this.ipProjectContributionsForMidOutcomeId = ipProjectContributionsForMidOutcomeId;
  }


  public void setIpProjectContributionsForMogId(Set<IpProjectContribution> ipProjectContributionsForMogId) {
    this.ipProjectContributionsForMogId = ipProjectContributionsForMogId;
  }


  public void setIpRelationshipsForChildId(Set<IpRelationship> ipRelationshipsForChildId) {
    this.ipRelationshipsForChildId = ipRelationshipsForChildId;
  }


  public void setIpRelationshipsForParentId(Set<IpRelationship> ipRelationshipsForParentId) {
    this.ipRelationshipsForParentId = ipRelationshipsForParentId;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setMogSynthesis(Set<MogSynthesy> mogSynthesis) {
    this.mogSynthesis = mogSynthesis;
  }


  public void setOutcomeSynthesis(Set<OutcomeSynthesy> outcomeSynthesis) {
    this.outcomeSynthesis = outcomeSynthesis;
  }


}

