package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 22, 2016 10:31:13 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * ProjectOutcome generated by hbm2java
 */
public class ProjectOutcome implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -768122169369973256L;
  @Expose
  private Long id;
  @Expose
  private CrpProgramOutcome crpProgramOutcome;
  @Expose
  private Project project;

  @Expose
  private User modifiedBy;
  @Expose

  private User createdBy;
  @Expose
  private long expectedValue;
  @Expose
  private long expectedUnit;
  @Expose
  private Long achievedValue;
  @Expose
  private String narrativeTarget;
  @Expose
  private String narrativeAchieved;

  @Expose
  private boolean active;
  @Expose
  private Date activeSince;
  @Expose
  private String modificationJustification;
  private Set<ProjectMilestone> projectMilestones = new HashSet<ProjectMilestone>(0);
  private Set<ProjectCommunication> projectCommunications = new HashSet<ProjectCommunication>(0);

  public ProjectOutcome() {
  }


  public ProjectOutcome(CrpProgramOutcome crpProgramOutcome, Project project, User usersByModifiedBy,
    User usersByCreatedBy, long expectedValue, long expectedUnit, Long achievedValue, String narrativeTarget,
    String narrativeAchieved, boolean isActive, Date activeSince, String modificationJustification,
    Set<ProjectMilestone> projectMilestoneses, Set<ProjectCommunication> projectCommunicationses) {
    this.crpProgramOutcome = crpProgramOutcome;
    this.project = project;
    this.modifiedBy = usersByModifiedBy;
    this.createdBy = usersByCreatedBy;
    this.expectedValue = expectedValue;
    this.expectedUnit = expectedUnit;
    this.achievedValue = achievedValue;
    this.narrativeTarget = narrativeTarget;
    this.narrativeAchieved = narrativeAchieved;

    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
    this.projectMilestones = projectMilestoneses;
    this.projectCommunications = projectCommunicationses;
  }

  public ProjectOutcome(CrpProgramOutcome crpProgramOutcome, Project project, User usersByModifiedBy,
    User usersByCreatedBy, long expectedValue, long expectedUnit, String narrativeTarget, boolean isActive,
    Date activeSince, String modificationJustification) {
    this.crpProgramOutcome = crpProgramOutcome;
    this.project = project;
    this.modifiedBy = usersByModifiedBy;
    this.createdBy = usersByCreatedBy;
    this.expectedValue = expectedValue;
    this.expectedUnit = expectedUnit;
    this.narrativeTarget = narrativeTarget;

    this.active = isActive;
    this.activeSince = activeSince;
    this.modificationJustification = modificationJustification;
  }


  public Long getAchievedValue() {
    return achievedValue;
  }


  public Date getActiveSince() {
    return activeSince;
  }


  public User getCreatedBy() {
    return createdBy;
  }


  public CrpProgramOutcome getCrpProgramOutcome() {
    return crpProgramOutcome;
  }


  public long getExpectedUnit() {
    return expectedUnit;
  }


  public long getExpectedValue() {
    return expectedValue;
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


  public String getModificationJustification() {
    return modificationJustification;
  }


  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }


  public String getNarrativeAchieved() {
    return narrativeAchieved;
  }


  public String getNarrativeTarget() {
    return narrativeTarget;
  }


  public Project getProject() {
    return project;
  }


  public Set<ProjectCommunication> getProjectCommunications() {
    return projectCommunications;
  }


  public Set<ProjectMilestone> getProjectMilestones() {
    return projectMilestones;
  }


  @Override
  public boolean isActive() {
    return active;
  }


  public void setAchievedValue(Long achievedValue) {
    this.achievedValue = achievedValue;
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


  public void setCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    this.crpProgramOutcome = crpProgramOutcome;
  }


  public void setExpectedUnit(long expectedUnit) {
    this.expectedUnit = expectedUnit;
  }


  public void setExpectedValue(long expectedValue) {
    this.expectedValue = expectedValue;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }


  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }


  public void setNarrativeAchieved(String narrativeAchieved) {
    this.narrativeAchieved = narrativeAchieved;
  }


  public void setNarrativeTarget(String narrativeTarget) {
    this.narrativeTarget = narrativeTarget;
  }


  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectCommunications(Set<ProjectCommunication> projectCommunications) {
    this.projectCommunications = projectCommunications;
  }


  public void setProjectMilestones(Set<ProjectMilestone> projectMilestones) {
    this.projectMilestones = projectMilestones;
  }


}

