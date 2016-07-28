/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.ccafs.marlo.data.model;
// Generated Jul 13, 2016 11:45:52 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Project generated by hbm2java
 */
public class Project implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -5737088425960023585L;


  @Expose
  private Long id;


  @Expose
  private Crp crp;
  @Expose
  private LiaisonInstitution liaisonInstitution;
  @Expose
  private LiaisonUser liaisonUser;
  @Expose
  private User createdBy;


  @Expose
  private User modifiedBy;


  @Expose
  private String title;
  @Expose
  private String summary;
  @Expose
  private Date startDate;
  @Expose
  private Date endDate;
  @Expose
  private String type;
  @Expose
  private boolean global;
  @Expose
  private boolean cofinancing;
  @Expose
  private String leaderResponsabilities;
  @Expose
  private Boolean requiresWorkplanUpload;
  @Expose
  private String workplanName;
  @Expose
  private String bilateralContractName;
  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;
  @Expose
  private String annualReportToDornor;
  private Set<ProjectFocus> projectFocuses = new HashSet<ProjectFocus>(0);

  private Set<Submission> submissions = new HashSet<Submission>(0);
  private List<CrpProgram> flagships;
  private String flagshipValue;
  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);


  public Project() {
  }


  public Project(Crp crp, LiaisonInstitution liaisonInstitution, LiaisonUser liaisonUser, User usersByCreatedBy,
    User usersByModifiedBy, String title, String summary, Date startDate, Date endDate, String type, boolean isGlobal,
    boolean isCofinancing, String leaderResponsabilities, Boolean requiresWorkplanUpload, String workplanName,
    String bilateralContractName, boolean isActive, Date activeSince, String modificationJustification,
    String annualReportToDornor, Set<ProjectFocus> projectFocuseses, Set<Submission> submissions) {
    this.crp = crp;
    this.liaisonInstitution = liaisonInstitution;
    this.liaisonUser = liaisonUser;
    this.createdBy = usersByCreatedBy;
    this.modifiedBy = usersByModifiedBy;
    this.title = title;
    this.summary = summary;
    this.startDate = startDate;
    this.endDate = endDate;
    this.type = type;
    this.global = isGlobal;
    this.cofinancing = isCofinancing;
    this.leaderResponsabilities = leaderResponsabilities;
    this.requiresWorkplanUpload = requiresWorkplanUpload;
    this.workplanName = workplanName;
    this.bilateralContractName = bilateralContractName;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.annualReportToDornor = annualReportToDornor;
    this.projectFocuses = projectFocuseses;
    this.submissions = submissions;
  }

  public Project(Crp crp, User usersByModifiedBy, boolean isGlobal, boolean isCofinancing, boolean isActive,
    Date activeSince, String modificationJustification) {
    this.crp = crp;
    this.modifiedBy = usersByModifiedBy;
    this.global = isGlobal;
    this.cofinancing = isCofinancing;
    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
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
    Project other = (Project) obj;
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


  public String getAnnualReportToDornor() {
    return this.annualReportToDornor;
  }


  public String getBilateralContractName() {
    return this.bilateralContractName;
  }


  public User getCreatedBy() {
    return this.createdBy;
  }

  public Crp getCrp() {
    return crp;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public List<CrpProgram> getFlagships() {
    return flagships;
  }

  public String getFlagshipValue() {
    return flagshipValue;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public String getLeaderResponsabilities() {
    return this.leaderResponsabilities;
  }

  public LiaisonInstitution getLiaisonInstitution() {
    return this.liaisonInstitution;
  }

  public LiaisonUser getLiaisonUser() {
    return this.liaisonUser;
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

  public Set<ProjectFocus> getProjectFocuses() {
    return this.projectFocuses;
  }

  public Boolean getRequiresWorkplanUpload() {
    return this.requiresWorkplanUpload;
  }

  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public Set<Submission> getSubmissions() {
    return submissions;
  }

  public String getSummary() {
    return this.summary;
  }

  public String getTitle() {
    return this.title;
  }

  public String getType() {
    return this.type;
  }

  public String getWorkplanName() {
    return this.workplanName;
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

  public boolean isBilateralProject() {
    return (type != null) ? type.equals(APConstants.PROJECT_BILATERAL) : false;
  }

  /**
   * A project is bilateral stand alone if it is bilateral and it is NOT contributing to any Core project.
   * 
   * @return true if the project is bilateral stand alone, false if is bilateral and is contributing to some core
   *         project.
   */
  public boolean isBilateralStandAlone() {
    return (type != null) ? (this.isBilateralProject() && !this.cofinancing) : false;
  }

  public boolean isCofinancing() {
    return cofinancing;
  }

  public boolean isGlobal() {
    return global;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setAnnualReportToDornor(String annualReportToDornor) {
    this.annualReportToDornor = annualReportToDornor;
  }

  public void setBilateralContractName(String bilateralContractName) {
    this.bilateralContractName = bilateralContractName;
  }

  public void setCofinancing(boolean cofinancing) {
    this.cofinancing = cofinancing;
  }

  public void setCreatedBy(User usersByCreatedBy) {
    this.createdBy = usersByCreatedBy;
  }

  public void setCrp(Crp crp) {
    this.crp = crp;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public void setFlagships(List<CrpProgram> flagships) {
    this.flagships = flagships;
  }


  public void setFlagshipValue(String flagshipValue) {
    this.flagshipValue = flagshipValue;
  }


  public void setGlobal(boolean global) {
    this.global = global;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setLeaderResponsabilities(String leaderResponsabilities) {
    this.leaderResponsabilities = leaderResponsabilities;
  }


  public void setLiaisonInstitution(LiaisonInstitution liaisonInstitution) {
    this.liaisonInstitution = liaisonInstitution;
  }


  public void setLiaisonUser(LiaisonUser liaisonUser) {
    this.liaisonUser = liaisonUser;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User usersByModifiedBy) {
    this.modifiedBy = usersByModifiedBy;
  }

  public void setProjectFocuses(Set<ProjectFocus> projectFocuseses) {
    this.projectFocuses = projectFocuseses;
  }

  public void setRequiresWorkplanUpload(Boolean requiresWorkplanUpload) {
    this.requiresWorkplanUpload = requiresWorkplanUpload;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setSubmissions(Set<Submission> submissions) {
    this.submissions = submissions;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setType(String type) {
    this.type = type;
  }


  public void setWorkplanName(String workplanName) {
    this.workplanName = workplanName;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}

