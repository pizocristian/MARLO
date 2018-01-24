package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 22, 2016 10:31:13 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
  private BigDecimal expectedValue;
  @Expose
  private SrfTargetUnit expectedUnit;
  @Expose
  private Long achievedValue;
  @Expose
  private SrfTargetUnit achievedUnit;

  @Expose
  private String narrativeTarget;
  @Expose
  private String genderDimenssion;

  @Expose
  private String youthComponent;
  @Expose
  private Phase phase;


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


  private List<ProjectMilestone> milestones;
  private List<ProjectCommunication> communications;


  private List<ProjectNextuser> nextUsers;

  private Set<ProjectNextuser> projectNextusers = new HashSet<ProjectNextuser>(0);
  private Set<ProjectComponentLesson> projectComponentLessons = new HashSet<ProjectComponentLesson>(0);
  private ProjectComponentLesson projectComponentLesson;
  private ProjectComponentLesson projectComponentLessonPreview;
  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);
  private Set<ProjectOutcomeIndicator> projectOutcomeIndicators = new HashSet<ProjectOutcomeIndicator>(0);
  private List<ProjectOutcomeIndicator> indicators;


  public ProjectOutcome() {
  }


  public ProjectOutcome(CrpProgramOutcome crpProgramOutcome, Project project, User usersByModifiedBy,
    User usersByCreatedBy, BigDecimal expectedValue, SrfTargetUnit expectedUnit, Long achievedValue,
    String narrativeTarget, String narrativeAchieved, boolean isActive, Date activeSince,
    String modificationJustification, Set<ProjectMilestone> projectMilestoneses,
    Set<ProjectCommunication> projectCommunicationses) {
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
    User usersByCreatedBy, BigDecimal expectedValue, SrfTargetUnit expectedUnit, String narrativeTarget,
    boolean isActive, Date activeSince, String modificationJustification) {
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


  public SrfTargetUnit getAchievedUnit() {
    return achievedUnit;
  }

  public Long getAchievedValue() {
    return achievedValue;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public List<ProjectCommunication> getCommunications() {
    return communications;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public CrpProgramOutcome getCrpProgramOutcome() {
    return crpProgramOutcome;
  }

  public SrfTargetUnit getExpectedUnit() {
    return expectedUnit;
  }


  public BigDecimal getExpectedValue() {
    return expectedValue;
  }


  public String getGenderDimenssion() {
    return genderDimenssion;
  }


  @Override
  public Long getId() {
    return id;
  }


  public List<ProjectOutcomeIndicator> getIndicators() {
    return indicators;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }


  public List<ProjectMilestone> getMilestones() {
    return milestones;
  }


  @Override
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


  public List<ProjectNextuser> getNextUsers() {
    return nextUsers;
  }


  public Phase getPhase() {
    return phase;
  }


  public Project getProject() {
    return project;
  }


  public Set<ProjectCommunication> getProjectCommunications() {
    return projectCommunications;
  }

  public ProjectComponentLesson getProjectComponentLesson() {
    return projectComponentLesson;
  }

  public ProjectComponentLesson getProjectComponentLessonPreview() {
    return projectComponentLessonPreview;
  }


  public Set<ProjectComponentLesson> getProjectComponentLessons() {
    return projectComponentLessons;
  }


  public Set<ProjectMilestone> getProjectMilestones() {
    return projectMilestones;
  }


  public Set<ProjectNextuser> getProjectNextusers() {
    return projectNextusers;
  }


  public Set<ProjectOutcomeIndicator> getProjectOutcomeIndicators() {
    return projectOutcomeIndicators;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }


  public String getYouthComponent() {
    return youthComponent;
  }

  @Override
  public boolean isActive() {
    return active;
  }


  public void setAchievedUnit(SrfTargetUnit achievedUnit) {
    this.achievedUnit = achievedUnit;
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


  public void setCommunications(List<ProjectCommunication> communications) {
    this.communications = communications;
  }


  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }


  public void setCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    this.crpProgramOutcome = crpProgramOutcome;
  }


  public void setExpectedUnit(SrfTargetUnit expectedUnit) {
    this.expectedUnit = expectedUnit;
  }


  public void setExpectedValue(BigDecimal expectedValue) {
    this.expectedValue = expectedValue;
  }


  public void setGenderDimenssion(String genderDimenssion) {
    this.genderDimenssion = genderDimenssion;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setIndicators(List<ProjectOutcomeIndicator> indicators) {
    this.indicators = indicators;
  }


  public void setMilestones(List<ProjectMilestone> milestones) {
    this.milestones = milestones;
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


  public void setNextUsers(List<ProjectNextuser> nextUsers) {
    this.nextUsers = nextUsers;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectCommunications(Set<ProjectCommunication> projectCommunications) {
    this.projectCommunications = projectCommunications;
  }


  public void setProjectComponentLesson(ProjectComponentLesson projectComponentLesson) {
    this.projectComponentLesson = projectComponentLesson;
  }


  public void setProjectComponentLessonPreview(ProjectComponentLesson projectComponentLessonPreview) {
    this.projectComponentLessonPreview = projectComponentLessonPreview;
  }


  public void setProjectComponentLessons(Set<ProjectComponentLesson> projectComponentLessons) {
    this.projectComponentLessons = projectComponentLessons;
  }


  public void setProjectMilestones(Set<ProjectMilestone> projectMilestones) {
    this.projectMilestones = projectMilestones;
  }


  public void setProjectNextusers(Set<ProjectNextuser> projectNextusers) {
    this.projectNextusers = projectNextusers;
  }


  public void setProjectOutcomeIndicators(Set<ProjectOutcomeIndicator> projectOutcomeIndicators) {
    this.projectOutcomeIndicators = projectOutcomeIndicators;
  }


  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }


  public void setYouthComponent(String youthComponent) {
    this.youthComponent = youthComponent;
  }


  @Override
  public String toString() {
    return "ProjectOutcome [id=" + id + ", crpProgramOutcome=" + crpProgramOutcome + ", project=" + project
      + ", expectedValue=" + expectedValue + ", expectedUnit=" + expectedUnit + ", achievedValue=" + achievedValue
      + ", achievedUnit=" + achievedUnit + "]";
  }


}

