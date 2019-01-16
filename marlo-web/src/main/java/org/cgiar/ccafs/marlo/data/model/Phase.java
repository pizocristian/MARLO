package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 11, 2017 9:08:14 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * Phases generated by hbm2java
 */
public class Phase extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -1603080182029677862L;


  private GlobalUnit crp;

  @Expose
  private String name;


  // TODO - change this to use the PhaseDescription enum
  @Expose
  private String description;

  @Expose
  private int year;


  @Expose
  private Boolean upkeep;

  @Expose
  private Boolean editable;


  @Expose
  private Boolean visible;


  @Expose
  private Phase next;


  @Expose
  private Date startDate;


  @Expose
  private Date endDate;

  private Set<ProjectPhase> projectPhases = new HashSet<ProjectPhase>(0);

  private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);

  private Set<ProjectFocus> projectFocuses = new HashSet<ProjectFocus>(0);

  private Set<ProjectClusterActivity> projectClusters = new HashSet<ProjectClusterActivity>(0);


  private Set<CrpPpaPartner> crpPpaPartner = new HashSet<CrpPpaPartner>(0);

  private Set<ProjectPartner> partners = new HashSet<ProjectPartner>(0);

  private Set<CrpProgramOutcome> outcomes = new HashSet<CrpProgramOutcome>(0);

  private Set<CrpClusterOfActivity> clusters = new HashSet<CrpClusterOfActivity>(0);

  private Set<ProjectOutcome> projectOutcomes = new HashSet<ProjectOutcome>(0);
  private Set<Activity> projectActivites = new HashSet<Activity>(0);
  private Set<ProjectLocation> projectLocations = new HashSet<ProjectLocation>(0);
  private Set<DeliverableInfo> deliverableInfos = new HashSet<DeliverableInfo>(0);
  private Set<DeliverableFundingSource> deliverableFundingSources = new HashSet<DeliverableFundingSource>(0);
  private Set<DeliverablePartnership> deliverablePartnerships = new HashSet<DeliverablePartnership>(0);
  private Set<ProjectBudget> projectBudgets = new HashSet<ProjectBudget>(0);
  private Set<FundingSourceInfo> fundingSourceInfo = new HashSet<FundingSourceInfo>(0);
  private Set<FundingSourceInstitution> fundingSourceInstitutions = new HashSet<FundingSourceInstitution>(0);
  private Set<FundingSourceLocation> fundingSourceLocations = new HashSet<FundingSourceLocation>(0);
  private Set<FundingSourceBudget> fundingSourceBudgets = new HashSet<FundingSourceBudget>(0);
  private Set<ProjectBudgetsCluserActvity> projectBudgetsActivities = new HashSet<ProjectBudgetsCluserActvity>(0);
  private Set<ProjectExpectedStudy> projectExpectedStudies = new HashSet<ProjectExpectedStudy>(0);
  private Set<ProjectBudgetsFlagship> projectBudgetsFlagships = new HashSet<ProjectBudgetsFlagship>(0);
  private Set<PowbSynthesis> powbSynthesis = new HashSet<PowbSynthesis>(0);
  private Set<ReportSynthesis> reportSynthesis = new HashSet<ReportSynthesis>(0);
  private Set<ProjectLeverage> projectLeverages = new HashSet<ProjectLeverage>(0);
  private Set<ProjectHighlightInfo> projectHighlightInfos = new HashSet<ProjectHighlightInfo>(0);
  private Set<ProjectHighlightType> projectHighligthsTypes = new HashSet<ProjectHighlightType>(0);
  private Set<ProjectHighlightCountry> projectHighlightCountries = new HashSet<ProjectHighlightCountry>(0);
  private Set<ProjectInnovationInfo> projectInnovationInfos = new HashSet<ProjectInnovationInfo>(0);
  private Set<ProjectInnovationDeliverable> projectInnovationDeliverables =
    new HashSet<ProjectInnovationDeliverable>(0);
  private Set<ProjectInnovationCountry> projectInnovationCountries = new HashSet<ProjectInnovationCountry>(0);
  private Set<ProjectInnovationOrganization> projectInnovationOrganizations =
    new HashSet<ProjectInnovationOrganization>(0);
  private Set<ProjectInnovationCrp> projectInnovationCrps = new HashSet<ProjectInnovationCrp>(0);
  private Set<DeliverableIntellectualAsset> deliverableIntellectualAssets =
    new HashSet<DeliverableIntellectualAsset>(0);
  private Set<ProjectExpectedStudyFlagship> projectExpectedStudyFlagships =
    new HashSet<ProjectExpectedStudyFlagship>(0);
  private Set<ProjectExpectedStudyCountry> projectExpectedStudyCountries = new HashSet<ProjectExpectedStudyCountry>(0);
  private Set<ProjectExpectedStudyCrp> projectExpectedStudyCrps = new HashSet<ProjectExpectedStudyCrp>(0);
  private Set<ProjectExpectedStudyInstitution> projectExpectedStudyInstitutions =
    new HashSet<ProjectExpectedStudyInstitution>(0);
  private Set<ProjectExpectedStudySrfTarget> projectExpectedStudySrfTargets =
    new HashSet<ProjectExpectedStudySrfTarget>(0);
  private Set<ProjectExpectedStudyInfo> projectExpectedStudyInfos = new HashSet<ProjectExpectedStudyInfo>(0);
  private Set<ProjectExpectedStudySubIdo> projectExpectedStudySubIdos = new HashSet<ProjectExpectedStudySubIdo>(0);
  private Set<ExpectedStudyProject> expectedStudyProjects = new HashSet<ExpectedStudyProject>(0);
  private Set<DeliverableActivity> deliverableActivities = new HashSet<DeliverableActivity>(0);
  private Set<DeliverableLocation> deliverableLocations = new HashSet<DeliverableLocation>(0);
  private Set<ProjectExpectedStudyRegion> projectExpectedStudyRegions = new HashSet<ProjectExpectedStudyRegion>(0);
  private Set<DeliverableGeographicRegion> deliverableGeographicRegions = new HashSet<DeliverableGeographicRegion>(0);
  private Set<ProjectPolicyInfo> projectPolicyInfos = new HashSet<ProjectPolicyInfo>(0);

  private Set<ProjectPolicyCountry> projectPolicyCountries = new HashSet<ProjectPolicyCountry>(0);


  private Set<ProjectPolicyOwner> projectPolicyOwners = new HashSet<ProjectPolicyOwner>(0);


  private Set<ProjectPolicyCrp> projectPolicyCrps = new HashSet<ProjectPolicyCrp>(0);


  private Set<ProjectPolicySubIdo> projectPolicySubIdos = new HashSet<ProjectPolicySubIdo>(0);


  private Set<ProjectPolicyCrossCuttingMarker> projectPolicyCrossCuttingMarkers =
    new HashSet<ProjectPolicyCrossCuttingMarker>(0);


  private Set<Lp6ContributionGeographicScope> Lp6ContributionGeographicScopes =
    new HashSet<Lp6ContributionGeographicScope>(0);

  private Set<ProjectLp6ContributionDeliverable> ProjectLp6ContributionDeliverables =
    new HashSet<ProjectLp6ContributionDeliverable>(0);


  private Set<ProjectLp6Contribution> ProjectLp6Contributions = new HashSet<ProjectLp6Contribution>(0);


  public Phase() {
  }

  public Phase(GlobalUnit crp, String description, int year) {
    this.crp = crp;
    this.description = description;
    this.year = year;
  }

  public Phase(GlobalUnit crp, String description, int year, Set<ProjectPhase> projectPhases) {
    this.crp = crp;
    this.description = description;
    this.year = year;
    this.projectPhases = projectPhases;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null) {
      return false;
    }

    Phase other = (Phase) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public Set<CrpClusterOfActivity> getClusters() {
    return clusters;
  }

  public String getComposedName() {
    return this.name + " - " + year;
  }

  public GlobalUnit getCrp() {
    return crp;
  }

  public Set<CrpPpaPartner> getCrpPpaPartner() {
    return crpPpaPartner;
  }

  public Set<DeliverableActivity> getDeliverableActivities() {
    return deliverableActivities;
  }

  public Set<DeliverableFundingSource> getDeliverableFundingSources() {
    return deliverableFundingSources;
  }

  public Set<DeliverableGeographicRegion> getDeliverableGeographicRegions() {
    return deliverableGeographicRegions;
  }

  public Set<DeliverableInfo> getDeliverableInfos() {
    return deliverableInfos;
  }


  public Set<DeliverableIntellectualAsset> getDeliverableIntellectualAssets() {
    return deliverableIntellectualAssets;
  }


  public Set<DeliverableLocation> getDeliverableLocations() {
    return deliverableLocations;
  }


  public Set<DeliverablePartnership> getDeliverablePartnerships() {
    return deliverablePartnerships;
  }


  public String getDescription() {
    return this.description;
  }

  public Boolean getEditable() {
    return editable;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Set<ExpectedStudyProject> getExpectedStudyProjects() {
    return expectedStudyProjects;
  }

  public Set<FundingSourceBudget> getFundingSourceBudgets() {
    return fundingSourceBudgets;
  }

  public Set<FundingSourceInfo> getFundingSourceInfo() {
    return fundingSourceInfo;
  }

  public Set<FundingSourceInstitution> getFundingSourceInstitutions() {
    return fundingSourceInstitutions;
  }

  public Set<FundingSourceLocation> getFundingSourceLocations() {
    return fundingSourceLocations;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public Set<Lp6ContributionGeographicScope> getLp6ContributionGeographicScopes() {
    return Lp6ContributionGeographicScopes;
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

  public String getName() {
    return name;
  }

  public Phase getNext() {
    return next;
  }

  public Set<CrpProgramOutcome> getOutcomes() {
    return outcomes;
  }

  public Set<ProjectPartner> getPartners() {
    return partners;
  }

  public Set<PowbSynthesis> getPowbSynthesis() {
    return powbSynthesis;
  }

  public Set<Activity> getProjectActivites() {
    return projectActivites;
  }

  public Set<ProjectBudget> getProjectBudgets() {
    return projectBudgets;
  }

  public Set<ProjectBudgetsCluserActvity> getProjectBudgetsActivities() {
    return projectBudgetsActivities;
  }

  public Set<ProjectBudgetsFlagship> getProjectBudgetsFlagships() {
    return projectBudgetsFlagships;
  }

  public Set<ProjectClusterActivity> getProjectClusters() {
    return projectClusters;
  }

  public Set<ProjectExpectedStudy> getProjectExpectedStudies() {
    return projectExpectedStudies;
  }

  public Set<ProjectExpectedStudyCountry> getProjectExpectedStudyCountries() {
    return projectExpectedStudyCountries;
  }


  public Set<ProjectExpectedStudyCrp> getProjectExpectedStudyCrps() {
    return projectExpectedStudyCrps;
  }

  public Set<ProjectExpectedStudyFlagship> getProjectExpectedStudyFlagships() {
    return projectExpectedStudyFlagships;
  }


  public Set<ProjectExpectedStudyInfo> getProjectExpectedStudyInfos() {
    return projectExpectedStudyInfos;
  }

  public Set<ProjectExpectedStudyInstitution> getProjectExpectedStudyInstitutions() {
    return projectExpectedStudyInstitutions;
  }

  public Set<ProjectExpectedStudyRegion> getProjectExpectedStudyRegions() {
    return projectExpectedStudyRegions;
  }


  public Set<ProjectExpectedStudySrfTarget> getProjectExpectedStudySrfTargets() {
    return projectExpectedStudySrfTargets;
  }

  public Set<ProjectExpectedStudySubIdo> getProjectExpectedStudySubIdos() {
    return projectExpectedStudySubIdos;
  }

  public Set<ProjectFocus> getProjectFocuses() {
    return projectFocuses;
  }

  public Set<ProjectHighlightCountry> getProjectHighlightCountries() {
    return projectHighlightCountries;
  }

  public Set<ProjectHighlightInfo> getProjectHighlightInfos() {
    return projectHighlightInfos;
  }

  public Set<ProjectHighlightType> getProjectHighligthsTypes() {
    return projectHighligthsTypes;
  }

  public Set<ProjectInfo> getProjectInfos() {
    return projectInfos;
  }

  public Set<ProjectInnovationCountry> getProjectInnovationCountries() {
    return projectInnovationCountries;
  }

  public Set<ProjectInnovationCrp> getProjectInnovationCrps() {
    return projectInnovationCrps;
  }


  public Set<ProjectInnovationDeliverable> getProjectInnovationDeliverables() {
    return projectInnovationDeliverables;
  }


  public Set<ProjectInnovationInfo> getProjectInnovationInfos() {
    return projectInnovationInfos;
  }


  public Set<ProjectInnovationOrganization> getProjectInnovationOrganizations() {
    return projectInnovationOrganizations;
  }


  public Set<ProjectLeverage> getProjectLeverages() {
    return projectLeverages;
  }


  public Set<ProjectLocation> getProjectLocations() {
    return projectLocations;
  }


  public Set<ProjectLp6ContributionDeliverable> getProjectLp6ContributionDeliverables() {
    return ProjectLp6ContributionDeliverables;
  }


  public Set<ProjectLp6Contribution> getProjectLp6Contributions() {
    return ProjectLp6Contributions;
  }

  public Set<ProjectOutcome> getProjectOutcomes() {
    return projectOutcomes;
  }

  public Set<ProjectPhase> getProjectPhases() {
    return this.projectPhases;
  }

  public Set<ProjectPolicyCountry> getProjectPolicyCountries() {
    return projectPolicyCountries;
  }


  public Set<ProjectPolicyCrossCuttingMarker> getProjectPolicyCrossCuttingMarkers() {
    return projectPolicyCrossCuttingMarkers;
  }


  public Set<ProjectPolicyCrp> getProjectPolicyCrps() {
    return projectPolicyCrps;
  }

  public Set<ProjectPolicyInfo> getProjectPolicyInfos() {
    return projectPolicyInfos;
  }


  public Set<ProjectPolicyOwner> getProjectPolicyOwners() {
    return projectPolicyOwners;
  }

  public Set<ProjectPolicySubIdo> getProjectPolicySubIdos() {
    return projectPolicySubIdos;
  }

  public Set<ReportSynthesis> getReportSynthesis() {
    return reportSynthesis;
  }


  public Date getStartDate() {
    return startDate;
  }

  public Boolean getUpkeep() {
    return upkeep;
  }

  public Boolean getVisible() {
    return visible;
  }

  public int getYear() {
    return this.year;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  @Override
  public boolean isActive() {

    return true;
  }


  public Boolean isReporting() {
    return description.equals(APConstants.REPORTING);
  }


  public void setClusters(Set<CrpClusterOfActivity> clusters) {
    this.clusters = clusters;
  }


  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }


  public void setCrpPpaPartner(Set<CrpPpaPartner> crpPpaPartner) {
    this.crpPpaPartner = crpPpaPartner;
  }


  public void setDeliverableActivities(Set<DeliverableActivity> deliverableActivities) {
    this.deliverableActivities = deliverableActivities;
  }


  public void setDeliverableFundingSources(Set<DeliverableFundingSource> deliverableFundingSources) {
    this.deliverableFundingSources = deliverableFundingSources;
  }


  public void setDeliverableGeographicRegions(Set<DeliverableGeographicRegion> deliverableGeographicRegions) {
    this.deliverableGeographicRegions = deliverableGeographicRegions;
  }


  public void setDeliverableInfos(Set<DeliverableInfo> deliverableInfos) {
    this.deliverableInfos = deliverableInfos;
  }


  public void setDeliverableIntellectualAssets(Set<DeliverableIntellectualAsset> deliverableIntellectualAssets) {
    this.deliverableIntellectualAssets = deliverableIntellectualAssets;
  }

  public void setDeliverableLocations(Set<DeliverableLocation> deliverableLocations) {
    this.deliverableLocations = deliverableLocations;
  }

  public void setDeliverablePartnerships(Set<DeliverablePartnership> deliverablePartnerships) {
    this.deliverablePartnerships = deliverablePartnerships;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setEditable(Boolean editable) {
    this.editable = editable;
  }


  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }


  public void setExpectedStudyProjects(Set<ExpectedStudyProject> expectedStudyProjects) {
    this.expectedStudyProjects = expectedStudyProjects;
  }


  public void setFundingSourceBudgets(Set<FundingSourceBudget> fundingSourceBudgets) {
    this.fundingSourceBudgets = fundingSourceBudgets;
  }


  public void setFundingSourceInfo(Set<FundingSourceInfo> fundingSourceInfo) {
    this.fundingSourceInfo = fundingSourceInfo;
  }


  public void setFundingSourceInstitutions(Set<FundingSourceInstitution> fundingSourceInstitutions) {
    this.fundingSourceInstitutions = fundingSourceInstitutions;
  }


  public void setFundingSourceLocations(Set<FundingSourceLocation> fundingSourceLocations) {
    this.fundingSourceLocations = fundingSourceLocations;
  }


  public void setLp6ContributionGeographicScopes(Set<Lp6ContributionGeographicScope> lp6ContributionGeographicScopes) {
    Lp6ContributionGeographicScopes = lp6ContributionGeographicScopes;
  }


  @Override
  public void setModifiedBy(User modifiedBy) {

  }


  public void setName(String name) {
    this.name = name;
  }


  public void setNext(Phase next) {
    this.next = next;
  }


  public void setOutcomes(Set<CrpProgramOutcome> otucomes) {
    this.outcomes = otucomes;
  }


  public void setPartners(Set<ProjectPartner> partners) {
    this.partners = partners;
  }


  public void setPowbSynthesis(Set<PowbSynthesis> powbSynthesis) {
    this.powbSynthesis = powbSynthesis;
  }


  public void setProjectActivites(Set<Activity> projectActivites) {
    this.projectActivites = projectActivites;
  }

  public void setProjectBudgets(Set<ProjectBudget> projectBudgets) {
    this.projectBudgets = projectBudgets;
  }

  public void setProjectBudgetsActivities(Set<ProjectBudgetsCluserActvity> projectBudgetsActivities) {
    this.projectBudgetsActivities = projectBudgetsActivities;
  }


  public void setProjectBudgetsFlagships(Set<ProjectBudgetsFlagship> projectBudgetsFlagships) {
    this.projectBudgetsFlagships = projectBudgetsFlagships;
  }

  public void setProjectClusters(Set<ProjectClusterActivity> projectClusters) {
    this.projectClusters = projectClusters;
  }


  public void setProjectExpectedStudies(Set<ProjectExpectedStudy> projectExpectedStudies) {
    this.projectExpectedStudies = projectExpectedStudies;
  }


  public void setProjectExpectedStudyCountries(Set<ProjectExpectedStudyCountry> projectExpectedStudyCountries) {
    this.projectExpectedStudyCountries = projectExpectedStudyCountries;
  }

  public void setProjectExpectedStudyCrps(Set<ProjectExpectedStudyCrp> projectExpectedStudyCrps) {
    this.projectExpectedStudyCrps = projectExpectedStudyCrps;
  }

  public void setProjectExpectedStudyFlagships(Set<ProjectExpectedStudyFlagship> projectExpectedStudyFlagships) {
    this.projectExpectedStudyFlagships = projectExpectedStudyFlagships;
  }

  public void setProjectExpectedStudyInfos(Set<ProjectExpectedStudyInfo> projectExpectedStudyInfos) {
    this.projectExpectedStudyInfos = projectExpectedStudyInfos;
  }


  public void
    setProjectExpectedStudyInstitutions(Set<ProjectExpectedStudyInstitution> projectExpectedStudyInstitutions) {
    this.projectExpectedStudyInstitutions = projectExpectedStudyInstitutions;
  }


  public void setProjectExpectedStudyRegions(Set<ProjectExpectedStudyRegion> projectExpectedStudyRegions) {
    this.projectExpectedStudyRegions = projectExpectedStudyRegions;
  }


  public void setProjectExpectedStudySrfTargets(Set<ProjectExpectedStudySrfTarget> projectExpectedStudySrfTargets) {
    this.projectExpectedStudySrfTargets = projectExpectedStudySrfTargets;
  }


  public void setProjectExpectedStudySubIdos(Set<ProjectExpectedStudySubIdo> projectExpectedStudySubIdos) {
    this.projectExpectedStudySubIdos = projectExpectedStudySubIdos;
  }

  public void setProjectFocuses(Set<ProjectFocus> projectFocuses) {
    this.projectFocuses = projectFocuses;
  }


  public void setProjectHighlightCountries(Set<ProjectHighlightCountry> projectHighlightCountries) {
    this.projectHighlightCountries = projectHighlightCountries;
  }


  public void setProjectHighlightInfos(Set<ProjectHighlightInfo> projectHighlightInfos) {
    this.projectHighlightInfos = projectHighlightInfos;
  }


  public void setProjectHighligthsTypes(Set<ProjectHighlightType> projectHighligthsTypes) {
    this.projectHighligthsTypes = projectHighligthsTypes;
  }

  public void setProjectInfos(Set<ProjectInfo> projectInfos) {
    this.projectInfos = projectInfos;
  }


  public void setProjectInnovationCountries(Set<ProjectInnovationCountry> projectInnovationCountries) {
    this.projectInnovationCountries = projectInnovationCountries;
  }

  public void setProjectInnovationCrps(Set<ProjectInnovationCrp> projectInnovationCrps) {
    this.projectInnovationCrps = projectInnovationCrps;
  }

  public void setProjectInnovationDeliverables(Set<ProjectInnovationDeliverable> projectInnovationDeliverables) {
    this.projectInnovationDeliverables = projectInnovationDeliverables;
  }


  public void setProjectInnovationInfos(Set<ProjectInnovationInfo> projectInnovationInfos) {
    this.projectInnovationInfos = projectInnovationInfos;
  }


  public void setProjectInnovationOrganizations(Set<ProjectInnovationOrganization> projectInnovationOrganizations) {
    this.projectInnovationOrganizations = projectInnovationOrganizations;
  }


  public void setProjectLeverages(Set<ProjectLeverage> projectLeverages) {
    this.projectLeverages = projectLeverages;
  }

  public void setProjectLocations(Set<ProjectLocation> projectLocations) {
    this.projectLocations = projectLocations;
  }


  public void
    setProjectLp6ContributionDeliverables(Set<ProjectLp6ContributionDeliverable> projectLp6ContributionDeliverables) {
    ProjectLp6ContributionDeliverables = projectLp6ContributionDeliverables;
  }

  public void setProjectLp6Contributions(Set<ProjectLp6Contribution> projectLp6Contributions) {
    ProjectLp6Contributions = projectLp6Contributions;
  }

  public void setProjectOutcomes(Set<ProjectOutcome> projectOutcomes) {
    this.projectOutcomes = projectOutcomes;
  }

  public void setProjectPhases(Set<ProjectPhase> projectPhases) {
    this.projectPhases = projectPhases;
  }

  public void setProjectPolicyCountries(Set<ProjectPolicyCountry> projectPolicyCountries) {
    this.projectPolicyCountries = projectPolicyCountries;
  }

  public void
    setProjectPolicyCrossCuttingMarkers(Set<ProjectPolicyCrossCuttingMarker> projectPolicyCrossCuttingMarkers) {
    this.projectPolicyCrossCuttingMarkers = projectPolicyCrossCuttingMarkers;
  }

  public void setProjectPolicyCrps(Set<ProjectPolicyCrp> projectPolicyCrps) {
    this.projectPolicyCrps = projectPolicyCrps;
  }

  public void setProjectPolicyInfos(Set<ProjectPolicyInfo> projectPolicyInfos) {
    this.projectPolicyInfos = projectPolicyInfos;
  }

  public void setProjectPolicyOwners(Set<ProjectPolicyOwner> projectPolicyOwners) {
    this.projectPolicyOwners = projectPolicyOwners;
  }

  public void setProjectPolicySubIdos(Set<ProjectPolicySubIdo> projectPolicySubIdos) {
    this.projectPolicySubIdos = projectPolicySubIdos;
  }

  public void setReportSynthesis(Set<ReportSynthesis> reportSynthesis) {
    this.reportSynthesis = reportSynthesis;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setUpkeep(Boolean upkeep) {
    this.upkeep = upkeep;
  }


  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "Phase [id=" + this.getId() + ", name=" + name + ", description=" + description + ", year=" + year
      + ", upkeep=" + upkeep + "]";
  }
}
