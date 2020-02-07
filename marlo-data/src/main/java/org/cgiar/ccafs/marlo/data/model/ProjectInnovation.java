
package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 18, 2018 3:39:52 PM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

/**
 * ProjectInnovation generated by hbm2java
 */
public class ProjectInnovation extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 2705453715289551611L;

  @Expose
  private Project project;

  private ProjectInnovationInfo projectInnovationInfo;

  private Set<ProjectInnovationInfo> projectInnovationInfos = new HashSet<ProjectInnovationInfo>(0);

  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);

  private Set<ProjectInnovationCountry> projectInnovationCountries = new HashSet<ProjectInnovationCountry>(0);

  private Set<ProjectInnovationContributingOrganization> projectInnovationContributingOrganization =
    new HashSet<ProjectInnovationContributingOrganization>(0);

  private Set<ProjectInnovationOrganization> projectInnovationOrganizations =
    new HashSet<ProjectInnovationOrganization>(0);

  private Set<ProjectInnovationDeliverable> projectInnovationDeliverables =
    new HashSet<ProjectInnovationDeliverable>(0);

  private Set<ProjectInnovationCrp> projectInnovationCrps = new HashSet<ProjectInnovationCrp>(0);

  private List<String> countriesIds = new ArrayList<>();

  private List<ProjectInnovationOrganization> organizations;

  private List<ProjectInnovationDeliverable> deliverables;

  private List<ProjectInnovationContributingOrganization> contributingOrganizations;

  private List<ProjectInnovationCrp> crps;
  private List<ProjectInnovationCountry> countries;
  private String countriesIdsText;

  private Set<ProjectInnovationGeographicScope> projectInnovationGeographicScopes =
    new HashSet<ProjectInnovationGeographicScope>(0);

  private List<ProjectInnovationGeographicScope> geographicScopes;

  private Set<ProjectInnovationRegion> projectInnovationRegions = new HashSet<ProjectInnovationRegion>(0);

  private List<ProjectInnovationRegion> regions;

  // AR2018 Field
  private List<LiaisonInstitution> selectedFlahsgips;

  // Shared Project Innovations
  private List<ProjectInnovationShared> sharedInnovations;
  private Set<ProjectInnovationShared> projectInnovationShareds = new HashSet<ProjectInnovationShared>(0);

  // AR2019 Field
  private Set<ProjectInnovationCenter> projectInnovationCenters = new HashSet<ProjectInnovationCenter>(0);
  private Set<ProjectInnovationMilestone> projectInnovationMilestones = new HashSet<ProjectInnovationMilestone>(0);
  private Set<ProjectInnovationSubIdo> projectInnovationSubIdos = new HashSet<ProjectInnovationSubIdo>(0);
  private List<ProjectInnovationCenter> centers;
  private List<ProjectInnovationMilestone> milestones;
  private List<ProjectInnovationSubIdo> subIdos;

  public List<ProjectInnovationCenter> getCenters() {
    return centers;
  }

  public String getComposedName() {
    if ((this.projectInnovationInfo != null) && (this.projectInnovationInfo.getTitle() != null)
      && (this.projectInnovationInfo.getTitle().trim().length() > 0)) {
      return this.getId() + " - " + this.projectInnovationInfo.getTitle();
    } else {
      return "" + this.getId() + " - Untitled";
    }
  }

  public String getComposedNameAlternative() {
    if ((this.projectInnovationInfo != null) && (this.projectInnovationInfo.getTitle() != null)) {
      if (this.projectInnovationInfo.getTitle().trim().length() > 0) {
        return "(" + this.projectInnovationInfo.getYear() + ") ID" + this.getId() + " - "
          + this.projectInnovationInfo.getTitle();
      } else {
        return "(" + this.projectInnovationInfo.getYear() + ") ID" + this.getId() + " - Untitled";
      }

    } else {
      return "" + this.getId() + " - Untitled";
    }
  }


  public List<ProjectInnovationContributingOrganization> getContributingOrganizations() {
    return this.contributingOrganizations;
  }

  public List<ProjectInnovationContributingOrganization> getContributingOrganizations(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationContributingOrganization().stream()
      .filter(pc -> pc.isActive() && pc.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  public List<ProjectInnovationCountry> getCountries() {
    return this.countries;
  }

  public List<ProjectInnovationCountry> getCountries(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationCountries().stream()
      .filter(pc -> pc.isActive() && pc.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  public List<String> getCountriesIds() {
    return this.countriesIds;
  }

  public String getCountriesIdsText() {
    return this.countriesIdsText;
  }

  public List<ProjectInnovationCrp> getCrps() {
    return this.crps;
  }

  public List<ProjectInnovationDeliverable> getDeliverables() {
    return this.deliverables;
  }

  public List<ProjectInnovationGeographicScope> getGeographicScopes() {
    return this.geographicScopes;
  }

  public List<ProjectInnovationGeographicScope> getGeographicScopes(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationGeographicScopes().stream()
      .filter(pg -> pg.isActive() && pg.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public List<ProjectInnovationMilestone> getMilestones() {
    return milestones;
  }

  public List<ProjectInnovationOrganization> getOrganizations() {
    return this.organizations;
  }

  public Project getProject() {
    return this.project;
  }


  public Set<ProjectInnovationCenter> getProjectInnovationCenters() {
    return projectInnovationCenters;
  }

  public Set<ProjectInnovationContributingOrganization> getProjectInnovationContributingOrganization() {
    return this.projectInnovationContributingOrganization;
  }

  public Set<ProjectInnovationCountry> getProjectInnovationCountries() {
    return this.projectInnovationCountries;
  }

  public Set<ProjectInnovationCrp> getProjectInnovationCrps() {
    return this.projectInnovationCrps;
  }

  public List<ProjectInnovationCrp> getProjectInnovationCrps(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationCrps().stream()
      .filter(pc -> pc.isActive() && pc.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  public Set<ProjectInnovationDeliverable> getProjectInnovationDeliverables() {
    return this.projectInnovationDeliverables;
  }

  public Set<ProjectInnovationGeographicScope> getProjectInnovationGeographicScopes() {
    return this.projectInnovationGeographicScopes;
  }

  public ProjectInnovationInfo getProjectInnovationInfo() {
    return this.projectInnovationInfo;
  }


  public ProjectInnovationInfo getProjectInnovationInfo(Phase phase) {
    if (this.getProjectInnovationInfo() != null) {
      return this.getProjectInnovationInfo();
    } else {
      List<ProjectInnovationInfo> infos = this.projectInnovationInfos.stream().filter(c -> c != null
        && c.getPhase() != null && c.getPhase().getId() != null && c.getPhase().getId().longValue() == phase.getId())
        .collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setProjectInnovationInfo(infos.get(0));
        return this.getProjectInnovationInfo();
      } else {
        return null;
      }
    }
  }

  public Set<ProjectInnovationInfo> getProjectInnovationInfos() {
    return this.projectInnovationInfos;
  }

  public Set<ProjectInnovationMilestone> getProjectInnovationMilestones() {
    return projectInnovationMilestones;
  }

  public Set<ProjectInnovationOrganization> getProjectInnovationOrganizations() {
    return this.projectInnovationOrganizations;
  }

  public List<ProjectInnovationOrganization> getProjectInnovationOrganizations(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationOrganizations().stream()
      .filter(pc -> pc.isActive() && pc.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  public Set<ProjectInnovationRegion> getProjectInnovationRegions() {
    return this.projectInnovationRegions;
  }

  public Set<ProjectInnovationShared> getProjectInnovationShareds() {
    return this.projectInnovationShareds;
  }

  public Set<ProjectInnovationSubIdo> getProjectInnovationSubIdos() {
    return projectInnovationSubIdos;
  }

  public List<ProjectInnovationRegion> getRegions() {
    return this.regions;
  }

  public List<ProjectInnovationRegion> getRegions(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationRegions().stream()
      .filter(pc -> pc.isActive() && pc.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  public Set<SectionStatus> getSectionStatuses() {
    return this.sectionStatuses;
  }

  public List<LiaisonInstitution> getSelectedFlahsgips() {
    return this.selectedFlahsgips;
  }

  public List<ProjectInnovationShared> getSharedInnovations() {
    return this.sharedInnovations;
  }

  public List<ProjectInnovationSubIdo> getSubIdos() {
    return subIdos;
  }

  public List<ProjectInnovationSubIdo> getSubIdos(Phase phase) {
    return new ArrayList<>(this.getProjectInnovationSubIdos().stream().filter(ps -> ps.getPhase().equals(phase))
      .collect(Collectors.toList()));
  }

  public void setAllbyPhase(Phase phase) {
    // TODO: Include all others many to many Relationships
    if (this.getProjectInnovationInfo(phase) != null) {
      this.setCountries(this.getCountries(phase));
      this.setRegions(this.getRegions(phase));
      this.setGeographicScopes(this.getGeographicScopes(phase));
      this.setContributingOrganizations(this.getContributingOrganizations(phase));
      this.setCrps(this.getProjectInnovationCrps(phase));
      this.setOrganizations(this.getProjectInnovationOrganizations(phase));

    }

  }

  public void setCenters(List<ProjectInnovationCenter> centers) {
    this.centers = centers;
  }

  public void setContributingOrganizations(List<ProjectInnovationContributingOrganization> contributingOrganizations) {
    this.contributingOrganizations = contributingOrganizations;
  }

  public void setCountries(List<ProjectInnovationCountry> countries) {
    this.countries = countries;
  }

  public void setCountriesIds(List<String> countriesIds) {
    this.countriesIds = countriesIds;
  }

  public void setCountriesIdsText(String countriesIdsText) {
    this.countriesIdsText = countriesIdsText;
  }

  public void setCrps(List<ProjectInnovationCrp> crps) {
    this.crps = crps;
  }

  public void setDeliverables(List<ProjectInnovationDeliverable> deliverables) {
    this.deliverables = deliverables;
  }

  public void setGeographicScopes(List<ProjectInnovationGeographicScope> geographicScopes) {
    this.geographicScopes = geographicScopes;
  }

  public void setMilestones(List<ProjectInnovationMilestone> milestones) {
    this.milestones = milestones;
  }

  public void setOrganizations(List<ProjectInnovationOrganization> organizations) {
    this.organizations = organizations;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void setProjectInnovationCenters(Set<ProjectInnovationCenter> projectInnovationCenters) {
    this.projectInnovationCenters = projectInnovationCenters;
  }

  public void setProjectInnovationContributingOrganization(
    Set<ProjectInnovationContributingOrganization> projectInnovationContributingOrganization) {
    this.projectInnovationContributingOrganization = projectInnovationContributingOrganization;
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

  public void
    setProjectInnovationGeographicScopes(Set<ProjectInnovationGeographicScope> projectInnovationGeographicScopes) {
    this.projectInnovationGeographicScopes = projectInnovationGeographicScopes;
  }

  public void setProjectInnovationInfo(ProjectInnovationInfo projectInnovationInfo) {
    this.projectInnovationInfo = projectInnovationInfo;
  }

  public void setProjectInnovationInfos(Set<ProjectInnovationInfo> projectInnovationInfos) {
    this.projectInnovationInfos = projectInnovationInfos;
  }

  public void setProjectInnovationMilestones(Set<ProjectInnovationMilestone> projectInnovationMilestones) {
    this.projectInnovationMilestones = projectInnovationMilestones;
  }

  public void setProjectInnovationOrganizations(Set<ProjectInnovationOrganization> projectInnovationOrganizations) {
    this.projectInnovationOrganizations = projectInnovationOrganizations;
  }

  public void setProjectInnovationRegions(Set<ProjectInnovationRegion> projectInnovationRegions) {
    this.projectInnovationRegions = projectInnovationRegions;
  }

  public void setProjectInnovationShareds(Set<ProjectInnovationShared> projectInnovationShareds) {
    this.projectInnovationShareds = projectInnovationShareds;
  }

  public void setProjectInnovationSubIdos(Set<ProjectInnovationSubIdo> projectInnovationSubIdos) {
    this.projectInnovationSubIdos = projectInnovationSubIdos;
  }

  public void setRegions(List<ProjectInnovationRegion> regions) {
    this.regions = regions;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setSelectedFlahsgips(List<LiaisonInstitution> selectedFlahsgips) {
    this.selectedFlahsgips = selectedFlahsgips;
  }

  public void setSharedInnovations(List<ProjectInnovationShared> sharedInnovations) {
    this.sharedInnovations = sharedInnovations;
  }

  public void setSubIdos(List<ProjectInnovationSubIdo> subIdos) {
    this.subIdos = subIdos;
  }

  @Override
  public String toString() {
    return "ProjectInnovation [id=" + this.getId() + ", isActive=" + this.isActive() + "]";
  }
}
