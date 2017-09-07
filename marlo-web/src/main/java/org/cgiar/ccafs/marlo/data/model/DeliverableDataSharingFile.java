package org.cgiar.ccafs.marlo.data.model;

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

// Generated Jan 5, 2017 7:38:48 AM by Hibernate Tools 3.4.0.CR1


/**
 * DeliverableDataSharingFile generated by hbm2java
 */
public class DeliverableDataSharingFile implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -5369311052598007274L;


  @Expose
  private Long id;


  private Deliverable deliverable;


  @Expose
  private FileDB file;

  @Expose
  private Integer typeId;

  @Expose
  private String externalFile;

  @Expose
  private Phase phase;


  public DeliverableDataSharingFile() {
  }


  public DeliverableDataSharingFile(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public DeliverableDataSharingFile(Deliverable deliverable, FileDB file, Integer type) {
    this.deliverable = deliverable;
    this.file = file;

  }

  public DeliverableDataSharingFile(Long id) {
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
    DeliverableDataSharingFile other = (DeliverableDataSharingFile) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Deliverable getDeliverable() {
    return deliverable;
  }

  public String getExternalFile() {
    return externalFile;
  }

  public FileDB getFile() {
    return file;
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

    return "";
  }


  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }


  public Phase getPhase() {
    return phase;
  }


  public Integer getTypeId() {
    return typeId;
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


  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setExternalFile(String externalFile) {
    this.externalFile = externalFile;
  }

  public void setFile(FileDB file) {
    this.file = file;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }


}

