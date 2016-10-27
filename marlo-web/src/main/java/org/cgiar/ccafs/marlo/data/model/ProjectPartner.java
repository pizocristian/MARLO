package org.cgiar.ccafs.marlo.data.model;
// Generated Jul 29, 2016 8:50:03 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * ProjectPartner generated by hbm2java
 */
public class ProjectPartner implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -8386210768059621143L;


  @Expose
  private Long id;

  @Expose
  private Institution institution;
  @Expose
  private Project project;
  @Expose
  private User createdBy;
  @Expose
  private User modifiedBy;
  @Expose
  private boolean active;

  @Expose
  private Date activeSince;


  @Expose
  private String modificationJustification;

  private Set<ProjectPartnerContribution> projectPartnerContributions = new HashSet<ProjectPartnerContribution>(0);


  private Set<ProjectPartnerContribution> projectPartnerContributors = new HashSet<ProjectPartnerContribution>(0);

  private Set<ProjectPartnerOverall> projectPartnerOveralls = new HashSet<ProjectPartnerOverall>(0);
  private Set<ProjectPartnerPerson> projectPartnerPersons = new HashSet<ProjectPartnerPerson>(0);
  private List<ProjectPartnerPerson> partnerPersons;
  private List<ProjectPartnerContribution> partnerContributors;

  public ProjectPartner() {
  }


  public ProjectPartner(Institution institution, Project project, User usersByCreatedBy, User usersByModifiedBy,
    boolean isActive, Date activeSince, String modificationJustification) {
    this.institution = institution;
    this.project = project;
    this.createdBy = usersByCreatedBy;
    this.modifiedBy = usersByModifiedBy;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }


  public ProjectPartner(Institution institution, Project project, User usersByCreatedBy, User usersByModifiedBy,
    boolean isActive, Date activeSince, String modificationJustification,
    Set<ProjectPartnerContribution> projectPartnerContributionsesForProjectPartnerId,
    Set<ProjectPartnerContribution> projectPartnerContributionsesForProjectPartnerContributorId,
    Set<ProjectPartnerOverall> projectPartnerOveralls, Set<ProjectPartnerPerson> projectPartnerPersonses) {
    this.institution = institution;
    this.project = project;
    this.createdBy = usersByCreatedBy;
    this.modifiedBy = usersByModifiedBy;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.projectPartnerContributions = projectPartnerContributionsesForProjectPartnerId;
    this.projectPartnerContributors = projectPartnerContributionsesForProjectPartnerContributorId;
    this.projectPartnerOveralls = projectPartnerOveralls;
    this.projectPartnerPersons = projectPartnerPersonses;
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
    ProjectPartner other = (ProjectPartner) obj;
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
    return this.createdBy;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public Institution getInstitution() {
    return this.institution;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }

  public String getModificationJustification() {
    return this.modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return this.modifiedBy;
  }


  public List<ProjectPartnerContribution> getPartnerContributors() {
    return partnerContributors;
  }

  public List<ProjectPartnerPerson> getPartnerPersons() {
    return partnerPersons;
  }

  public String getPersonComposedName(int partnerPersonID) {
    if (partnerPersonID <= 0) {
      return "";
    }

    for (ProjectPartnerPerson person : partnerPersons) {
      if (person.getId() == partnerPersonID) {
        StringBuilder str = new StringBuilder();
        str.append(person.getUser().getLastName());
        str.append(", ");
        str.append(person.getUser().getFirstName());
        str.append(" <");
        str.append(person.getUser().getEmail());
        str.append(">, ");
        if (institution.getAcronym() != null) {
          str.append(institution.getAcronym());
          str.append(" - ");
        }
        str.append(institution.getName());
        return str.toString();
      }
    }

    return "";
  }

  public Project getProject() {
    return project;
  }

  public Set<ProjectPartnerContribution> getProjectPartnerContributions() {
    return this.projectPartnerContributions;
  }

  public Set<ProjectPartnerContribution> getProjectPartnerContributors() {
    return this.projectPartnerContributors;
  }

  public Set<ProjectPartnerOverall> getProjectPartnerOveralls() {
    return this.projectPartnerOveralls;
  }

  public Set<ProjectPartnerPerson> getProjectPartnerPersons() {
    return this.projectPartnerPersons;
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


  /**
   * This methods validate if the current project partner has a contact person working as coordinator.
   * 
   * @return true if this project partner is coordinating the project. false otherwise.
   */
  public boolean isCoordinator() {
    for (ProjectPartnerPerson person : partnerPersons) {
      if (person.getContactType().equals(APConstants.PROJECT_PARTNER_PC)) {
        return true;
      }
    }
    return false;
  }

  /**
   * This methods validate if the current project partner has a contact person working as leader.
   * 
   * @return true if this project partner is leading the project. false otherwise.
   */
  public boolean isLeader() {
    for (ProjectPartnerPerson person : partnerPersons) {
      if (person.getContactType().equals(APConstants.PROJECT_PARTNER_PL)) {
        return true;
      }
    }
    return false;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }


  public void setCreatedBy(User usersByCreatedBy) {
    this.createdBy = usersByCreatedBy;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User usersByModifiedBy) {
    this.modifiedBy = usersByModifiedBy;
  }


  public void setPartnerContributors(List<ProjectPartnerContribution> partnerContributors) {
    this.partnerContributors = partnerContributors;
  }

  public void setPartnerPersons(List<ProjectPartnerPerson> partnerPersons) {
    this.partnerPersons = partnerPersons;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void
    setProjectPartnerContributions(Set<ProjectPartnerContribution> projectPartnerContributionsesForProjectPartnerId) {
    this.projectPartnerContributions = projectPartnerContributionsesForProjectPartnerId;
  }

  public void setProjectPartnerContributors(
    Set<ProjectPartnerContribution> projectPartnerContributionsesForProjectPartnerContributorId) {
    this.projectPartnerContributors = projectPartnerContributionsesForProjectPartnerContributorId;
  }

  public void setProjectPartnerOveralls(Set<ProjectPartnerOverall> projectPartnerOveralls) {
    this.projectPartnerOveralls = projectPartnerOveralls;
  }

  public void setProjectPartnerPersons(Set<ProjectPartnerPerson> projectPartnerPersonses) {
    this.projectPartnerPersons = projectPartnerPersonses;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}

