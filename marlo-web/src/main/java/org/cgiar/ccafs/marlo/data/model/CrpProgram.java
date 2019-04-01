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
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * CrpProgram generated by hbm2java
 */
public class CrpProgram extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = -799819119525448185L;

  @Expose
  private GlobalUnit crp;

  @Expose
  private String name;

  @Expose
  private String acronym;

  @Expose
  private String smoCode;

  @Expose
  private int programType;


  private String action;

  @Expose
  private CenterArea researchArea;

  private Set<CrpClusterOfActivity> crpClusterOfActivities = new HashSet<CrpClusterOfActivity>(0);

  private Set<CrpProgramLeader> crpProgramLeaders = new HashSet<CrpProgramLeader>(0);

  private Set<CrpProgramOutcome> crpProgramOutcomes = new HashSet<CrpProgramOutcome>(0);

  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);

  private Set<CrpProgramCountry> crpProgramCountries = new HashSet<CrpProgramCountry>(0);

  private Set<Submission> submissions = new HashSet<Submission>(0);

  private List<String> selectedCountries;

  private List<CrpProgramOutcome> outcomes;

  private List<CrpClusterOfActivity> clusterofActivities;

  private Set<LiaisonInstitution> liaisonInstitutions = new HashSet<LiaisonInstitution>(0);

  private Set<ProjectFocus> projectFocuses = new HashSet<ProjectFocus>(0);

  private Set<CenterTopic> researchTopics = new HashSet<CenterTopic>(0);

  private Set<CenterImpact> researchImpacts = new HashSet<CenterImpact>(0);

  private List<CenterImpact> impacts;

  private List<CenterTopic> topics;

  private Set<CenterOutput> centerOutputs = new HashSet<CenterOutput>(0);

  private Set<CenterSectionStatus> centerSectionStatuses = new HashSet<CenterSectionStatus>(0);

  private Set<CenterSubmission> centerSubmissions = new HashSet<CenterSubmission>(0);

  private List<CrpProgramLeader> leaders;

  private List<CrpProgramLeader> managers;

  private List<PowbExpectedCrpProgress> powbs;

  private List<CrpMilestone> milestones;

  @Expose
  private String color;

  @Expose
  private Boolean baseLine;

  private double w1;

  private double w3;

  private double centerFunds;
  private PowbCollaboration collaboration;

  private PowbSynthesis synthesis;

  private Set<DeliverableProgram> deliverablePrograms = new HashSet<DeliverableProgram>(0);


  public CrpProgram() {
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    CrpProgram other = (CrpProgram) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public String getAcronym() {
    return this.acronym;
  }

  public String getAction() {
    return this.action;
  }

  public Boolean getBaseLine() {
    return this.baseLine;
  }

  public String getCenterComposedName() {
    String name = "";
    if (this.getResearchArea() != null) {
      name = name + this.getResearchArea().getAcronym() + " : " + this.getName();
    }

    return name;
  }

  public double getCenterFunds() {
    return this.centerFunds;
  }

  public Set<CenterOutput> getCenterOutputs() {
    return this.centerOutputs;
  }


  public Set<CenterSectionStatus> getCenterSectionStatuses() {
    return this.centerSectionStatuses;
  }


  public Set<CenterSubmission> getCenterSubmissions() {
    return this.centerSubmissions;
  }

  public List<CrpClusterOfActivity> getClusterofActivities() {
    return this.clusterofActivities;
  }

  public PowbCollaboration getCollaboration() {
    return this.collaboration;
  }

  public String getColor() {
    return this.color;
  }


  public String getComposedName() {
    return this.acronym + ": " + this.name;
  }


  public GlobalUnit getCrp() {
    return this.crp;
  }

  public Set<CrpClusterOfActivity> getCrpClusterOfActivities() {
    return this.crpClusterOfActivities;
  }

  public Set<CrpProgramCountry> getCrpProgramCountries() {
    return this.crpProgramCountries;
  }

  public Set<CrpProgramLeader> getCrpProgramLeaders() {
    return this.crpProgramLeaders;
  }

  public Set<CrpProgramOutcome> getCrpProgramOutcomes() {
    return this.crpProgramOutcomes;
  }

  public Set<DeliverableProgram> getDeliverablePrograms() {
    return this.deliverablePrograms;
  }

  public List<CenterImpact> getImpacts() {
    return this.impacts;
  }

  public List<CrpProgramLeader> getLeaders() {
    return this.leaders;
  }

  public Set<LiaisonInstitution> getLiaisonInstitutions() {
    return this.liaisonInstitutions;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());

    return sb.toString();
  }

  public List<CrpProgramLeader> getManagers() {
    return this.managers;
  }


  public List<CrpMilestone> getMilestones() {
    return this.milestones;
  }

  public String getName() {
    return this.name;
  }

  public List<CrpProgramOutcome> getOutcomes() {
    return this.outcomes;
  }

  public List<PowbExpectedCrpProgress> getPowbs() {
    return this.powbs;
  }

  public int getProgramType() {
    return this.programType;
  }


  public Set<ProjectFocus> getProjectFocuses() {
    return this.projectFocuses;
  }

  public CenterArea getResearchArea() {
    return this.researchArea;
  }

  public Set<CenterImpact> getResearchImpacts() {
    return this.researchImpacts;
  }

  public Set<CenterTopic> getResearchTopics() {
    return this.researchTopics;
  }


  public Set<SectionStatus> getSectionStatuses() {
    return this.sectionStatuses;
  }

  public List<String> getSelectedCountries() {
    return this.selectedCountries;
  }

  public String getSmoCode() {
    return this.smoCode;
  }


  public Set<Submission> getSubmissions() {
    return this.submissions;
  }

  public PowbSynthesis getSynthesis() {
    return this.synthesis;
  }

  public List<CenterTopic> getTopics() {
    return this.topics;
  }


  public double getW1() {
    return this.w1;
  }

  public double getW3() {
    return this.w3;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setBaseLine(Boolean baseLine) {
    this.baseLine = baseLine;
  }


  public void setCenterFunds(double centerFunds) {
    this.centerFunds = centerFunds;
  }

  public void setCenterOutputs(Set<CenterOutput> centerOutputs) {
    this.centerOutputs = centerOutputs;
  }

  public void setCenterSectionStatuses(Set<CenterSectionStatus> centerSectionStatuses) {
    this.centerSectionStatuses = centerSectionStatuses;
  }


  public void setCenterSubmissions(Set<CenterSubmission> centerSubmissions) {
    this.centerSubmissions = centerSubmissions;
  }

  public void setClusterofActivities(List<CrpClusterOfActivity> clusterofActivities) {
    this.clusterofActivities = clusterofActivities;
  }

  public void setCollaboration(PowbCollaboration collaboration) {
    this.collaboration = collaboration;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }

  public void setCrpClusterOfActivities(Set<CrpClusterOfActivity> crpClusterOfActivities) {
    this.crpClusterOfActivities = crpClusterOfActivities;
  }

  public void setCrpProgramCountries(Set<CrpProgramCountry> crpProgramCountries) {
    this.crpProgramCountries = crpProgramCountries;
  }

  public void setCrpProgramLeaders(Set<CrpProgramLeader> crpProgramLeaders) {
    this.crpProgramLeaders = crpProgramLeaders;
  }

  public void setCrpProgramOutcomes(Set<CrpProgramOutcome> crpProgramOutcomes) {
    this.crpProgramOutcomes = crpProgramOutcomes;
  }

  public void setDeliverablePrograms(Set<DeliverableProgram> deliverablePrograms) {
    this.deliverablePrograms = deliverablePrograms;
  }

  public void setImpacts(List<CenterImpact> impacts) {
    this.impacts = impacts;
  }

  public void setLeaders(List<CrpProgramLeader> leaders) {
    this.leaders = leaders;
  }

  public void setLiaisonInstitutions(Set<LiaisonInstitution> liaisonInstitutions) {
    this.liaisonInstitutions = liaisonInstitutions;
  }

  public void setManagers(List<CrpProgramLeader> managers) {
    this.managers = managers;
  }

  public void setMilestones(List<CrpMilestone> milestones) {
    this.milestones = milestones;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setOutcomes(List<CrpProgramOutcome> outcomes) {
    this.outcomes = outcomes;
  }

  public void setPowbs(List<PowbExpectedCrpProgress> powbs) {
    this.powbs = powbs;
  }

  public void setProgramType(int programType) {
    this.programType = programType;
  }

  public void setProjectFocuses(Set<ProjectFocus> projectFocuses) {
    this.projectFocuses = projectFocuses;
  }

  public void setResearchArea(CenterArea researchArea) {
    this.researchArea = researchArea;
  }

  public void setResearchImpacts(Set<CenterImpact> researchImpacts) {
    this.researchImpacts = researchImpacts;
  }

  public void setResearchTopics(Set<CenterTopic> researchTopics) {
    this.researchTopics = researchTopics;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setSelectedCountries(List<String> selectedCountries) {
    this.selectedCountries = selectedCountries;
  }

  public void setSmoCode(String smoCode) {
    this.smoCode = smoCode;
  }

  public void setSubmissions(Set<Submission> submissions) {
    this.submissions = submissions;
  }

  public void setSynthesis(PowbSynthesis synthesis) {
    this.synthesis = synthesis;
  }

  public void setTopics(List<CenterTopic> topics) {
    this.topics = topics;
  }

  public void setW1(double w1) {
    this.w1 = w1;
  }

  public void setW3(double w3) {
    this.w3 = w3;
  }

  @Override
  public String toString() {
    return "CrpProgram [id=" + this.getId() + ", crp=" + this.crp + ", name=" + this.name + ", acronym=" + this.acronym
      + ", programType=" + this.programType + "]";
  }

}
