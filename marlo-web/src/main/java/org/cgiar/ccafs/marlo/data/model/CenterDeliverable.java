package org.cgiar.ccafs.marlo.data.model;
// Generated Mar 13, 2017 1:43:12 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CenterDeliverable generated by hbm2java
 */
public class CenterDeliverable extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 1756645330425186871L;


  @Expose
  private CenterProjectStatus projectStatus;


  @Expose
  private CenterProject project;


  @Expose
  private CapacityDevelopment capdev;


  @Expose
  private Boolean capdevD;

  @Expose
  private CenterDeliverableType deliverableType;


  @Expose
  private String name;

  @Expose
  private String description;

  @Expose
  private Date startDate;


  @Expose
  private Date endDate;


  @Expose
  private Date dateCreated;

  @Expose
  private Integer year;

  @Expose
  private CenterDeliverableCrosscutingTheme deliverableCrosscutingTheme;

  private Set<CenterDeliverableOutput> deliverableOutputs = new HashSet<CenterDeliverableOutput>(0);

  private Set<CenterSectionStatus> sectionStatuses = new HashSet<CenterSectionStatus>(0);

  private Set<CenterDeliverableDocument> deliverableDocuments = new HashSet<CenterDeliverableDocument>(0);


  private List<CenterDeliverableDocument> documents;


  private List<CenterDeliverableOutput> outputs;

  public CenterDeliverable() {
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
    CenterDeliverable other = (CenterDeliverable) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public CapacityDevelopment getCapdev() {
    return capdev;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public CenterDeliverableCrosscutingTheme getDeliverableCrosscutingTheme() {
    return deliverableCrosscutingTheme;
  }

  public Set<CenterDeliverableDocument> getDeliverableDocuments() {
    return deliverableDocuments;
  }

  public Set<CenterDeliverableOutput> getDeliverableOutputs() {
    return deliverableOutputs;
  }


  public CenterDeliverableType getDeliverableType() {
    return deliverableType;
  }

  public String getDescription() {
    return description;
  }

  public List<CenterDeliverableDocument> getDocuments() {
    return documents;
  }

  public Date getEndDate() {
    return endDate;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getName() {
    return name;
  }

  public List<CenterDeliverableOutput> getOutputs() {
    return outputs;
  }


  public CenterProject getProject() {
    return project;
  }


  public CenterProjectStatus getProjectStatus() {
    return projectStatus;
  }

  public Set<CenterSectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }


  public Date getStartDate() {
    return startDate;
  }

  public Integer getYear() {
    return year;
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }


  public Boolean isCapdevD() {
    return capdevD;
  }


  public void setCapdev(CapacityDevelopment capdev) {
    this.capdev = capdev;
  }


  public void setCapdevD(Boolean capdevD) {
    this.capdevD = capdevD;
  }


  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setDeliverableCrosscutingTheme(CenterDeliverableCrosscutingTheme deliverableCrosscutingTheme) {
    this.deliverableCrosscutingTheme = deliverableCrosscutingTheme;
  }


  public void setDeliverableDocuments(Set<CenterDeliverableDocument> deliverableDocuments) {
    this.deliverableDocuments = deliverableDocuments;
  }

  public void setDeliverableOutputs(Set<CenterDeliverableOutput> deliverableOutputs) {
    this.deliverableOutputs = deliverableOutputs;
  }


  public void setDeliverableType(CenterDeliverableType deliverableType) {
    this.deliverableType = deliverableType;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDocuments(List<CenterDeliverableDocument> documents) {
    this.documents = documents;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setName(String name) {
    this.name = name;
  }


  public void setOutputs(List<CenterDeliverableOutput> outputs) {
    this.outputs = outputs;
  }


  public void setProject(CenterProject project) {
    this.project = project;
  }


  public void setProjectStatus(CenterProjectStatus projectStatus) {
    this.projectStatus = projectStatus;
  }


  public void setSectionStatuses(Set<CenterSectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }


  public void setYear(Integer year) {
    this.year = year;
  }


  @Override
  public String toString() {
    return "CenterDeliverable [id=" + this.getId() + ", project=" + project + ", name=" + name + "]";
  }


}

