package org.cgiar.ccafs.marlo.data.model;
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * LocElementType generated by hbm2java
 */
public class LocElementType implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 1795563086382428049L;


  @Expose
  private Long id;

  @Expose
  private String name;


  @Expose
  private Crp crp;

  @Expose
  private boolean active;


  @Expose
  private User createdBy;


  @Expose
  private Date activeSince;


  @Expose
  private User modifiedBy;


  @Expose
  private String modificationJustification;


  @Expose
  private boolean scope;


  @Expose
  private Boolean hasCoordinates;

  private LocElementType locElementType;


  private Set<LocElement> locElements = new HashSet<LocElement>(0);

  private Set<LocElementType> locElementTypes = new HashSet<LocElementType>(0);

  private List<LocElement> locationElements;

  public LocElementType() {
  }


  public LocElementType(LocElementType locElementType, String name, Set<LocElement> locElements,
    Set<LocElementType> locElementTypes) {
    this.locElementType = locElementType;
    this.name = name;
    this.locElements = locElements;
    this.locElementTypes = locElementTypes;
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
    LocElementType other = (LocElementType) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public Crp getCrp() {
    return crp;
  }

  public Boolean getHasCoordinates() {
    return hasCoordinates;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public List<LocElement> getLocationElements() {
    return locationElements;
  }


  public Set<LocElement> getLocElements() {
    return this.locElements;
  }

  public LocElementType getLocElementType() {
    return this.locElementType;
  }

  public Set<LocElementType> getLocElementTypes() {
    return this.locElementTypes;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getModificationJustification() {
    return modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public boolean isScope() {
    return scope;
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

  public void setCrp(Crp crp) {
    this.crp = crp;
  }

  public void setHasCoordinates(Boolean hasCoordinates) {
    this.hasCoordinates = hasCoordinates;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setLocationElements(List<LocElement> locationElements) {
    this.locationElements = locationElements;
  }

  public void setLocElements(Set<LocElement> locElements) {
    this.locElements = locElements;
  }

  public void setLocElementType(LocElementType locElementType) {
    this.locElementType = locElementType;
  }

  public void setLocElementTypes(Set<LocElementType> locElementTypes) {
    this.locElementTypes = locElementTypes;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScope(boolean scope) {
    this.scope = scope;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}

