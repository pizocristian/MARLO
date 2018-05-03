package org.cgiar.ccafs.marlo.data.model;


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class DeliverableParticipantLocation implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -5492622285870637617L;


  @Expose
  private Long id;
  @Expose
  private DeliverableParticipant deliverableParticipant;
  @Expose
  private LocElement locElement;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private User createdBy;
  @Expose
  private User modifiedBy;
  @Expose
  private String modificationJustification;

  public DeliverableParticipantLocation() {
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
    DeliverableParticipantLocation other = (DeliverableParticipantLocation) obj;
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
    return this.activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public DeliverableParticipant getDeliverableParticipant() {
    return deliverableParticipant;
  }


  @Override
  public Long getId() {
    return this.id;
  }


  public LocElement getLocElement() {
    return locElement;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  @Override
  public String getModificationJustification() {
    return this.modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
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


  public void setDeliverableParticipant(DeliverableParticipant deliverableParticipant) {
    this.deliverableParticipant = deliverableParticipant;
  }


  public void setId(Long id) {
    this.id = id;
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


  @Override
  public String toString() {
    return "DeliverableParticipantLocation [id=" + id + ", deliverableParticipant=" + deliverableParticipant
      + ", locElement=" + locElement + ", active=" + active + "]";
  }


}

