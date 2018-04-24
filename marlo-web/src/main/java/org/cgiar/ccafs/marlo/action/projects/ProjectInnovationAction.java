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

package org.cgiar.ccafs.marlo.action.projects;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.DeliverableManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectExpectedStudyManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationCountryManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationCrpManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationDeliverableManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationInfoManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationOrganizationManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndGenderYouthFocusLevelManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndGeographicScopeManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndInnovationTypeManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndOrganizationTypeManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndPhaseResearchPartnershipManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndRegionManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndStageInnovationManager;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableInfo;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectExpectedStudy;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovation;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationCountry;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationCrp;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationDeliverable;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationOrganization;
import org.cgiar.ccafs.marlo.data.model.RepIndGenderYouthFocusLevel;
import org.cgiar.ccafs.marlo.data.model.RepIndGeographicScope;
import org.cgiar.ccafs.marlo.data.model.RepIndInnovationType;
import org.cgiar.ccafs.marlo.data.model.RepIndOrganizationType;
import org.cgiar.ccafs.marlo.data.model.RepIndPhaseResearchPartnership;
import org.cgiar.ccafs.marlo.data.model.RepIndRegion;
import org.cgiar.ccafs.marlo.data.model.RepIndStageInnovation;
import org.cgiar.ccafs.marlo.data.model.TypeExpectedStudiesEnum;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.AutoSaveReader;
import org.cgiar.ccafs.marlo.validation.projects.ProjectInnovationValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class ProjectInnovationAction extends BaseAction {


  private static final long serialVersionUID = 2025842196563364380L;


  private long projectID;

  private long innovationID;

  private Project project;
  private ProjectInnovation innovation;
  private ProjectInnovation innovationDB;
  private GlobalUnit loggedCrp;
  private List<RepIndPhaseResearchPartnership> phaseResearchList;
  private List<RepIndStageInnovation> stageInnovationList;

  private List<RepIndGeographicScope> geographicScopeList;
  private List<RepIndInnovationType> innovationTypeList;
  private List<RepIndRegion> regionList;
  private List<LocElement> countries;
  private List<ProjectExpectedStudy> expectedStudyList;
  private List<Deliverable> deliverableList;
  private List<GlobalUnit> crpList;
  private List<RepIndGenderYouthFocusLevel> focusLevelList;
  private List<RepIndOrganizationType> organizationTypeList;

  private ProjectInnovationManager projectInnovationManager;
  private GlobalUnitManager globalUnitManager;
  private ProjectManager projectManager;
  private PhaseManager phaseManager;
  private RepIndPhaseResearchPartnershipManager repIndPhaseResearchPartnershipManager;
  private RepIndStageInnovationManager repIndStageInnovationManager;
  private RepIndGeographicScopeManager repIndGeographicScopeManager;
  private RepIndInnovationTypeManager repIndInnovationTypeManager;
  private RepIndRegionManager repIndRegionManager;
  private LocElementManager locElementManager;
  private ProjectExpectedStudyManager projectExpectedStudyManager;
  private DeliverableManager deriverableManager;
  private RepIndGenderYouthFocusLevelManager focusLevelManager;
  private ProjectInnovationInfoManager projectInnovationInfoManager;
  private ProjectInnovationCrpManager projectInnovationCrpManager;
  private ProjectInnovationOrganizationManager projectInnovationOrganizationManager;
  private ProjectInnovationDeliverableManager projectInnovationDeliverableManager;
  private ProjectInnovationCountryManager projectInnovationCountryManager;
  private RepIndOrganizationTypeManager repIndOrganizationTypeManager;
  private ProjectInnovationValidator validator;

  @Inject
  public ProjectInnovationAction(APConfig config, GlobalUnitManager globalUnitManager,
    ProjectInnovationManager projectInnovationManager, ProjectManager projectManager, PhaseManager phaseManager,
    RepIndPhaseResearchPartnershipManager repIndPhaseResearchPartnershipManager,
    RepIndStageInnovationManager repIndStageInnovationManager,
    RepIndGeographicScopeManager repIndGeographicScopeManager, RepIndInnovationTypeManager repIndInnovationTypeManager,
    RepIndRegionManager repIndRegionManager, LocElementManager locElementManager,
    ProjectExpectedStudyManager projectExpectedStudyManager, DeliverableManager deriverableManager,
    RepIndGenderYouthFocusLevelManager focusLevelManager, ProjectInnovationInfoManager projectInnovationInfoManager,
    ProjectInnovationCrpManager projectInnovationCrpManager,
    ProjectInnovationOrganizationManager projectInnovationOrganizationManager,
    ProjectInnovationDeliverableManager projectInnovationDeliverableManager,
    ProjectInnovationCountryManager projectInnovationCountryManager,
    RepIndOrganizationTypeManager repIndOrganizationTypeManager, ProjectInnovationValidator validator) {
    super(config);
    this.projectInnovationManager = projectInnovationManager;
    this.globalUnitManager = globalUnitManager;
    this.projectManager = projectManager;
    this.phaseManager = phaseManager;
    this.repIndPhaseResearchPartnershipManager = repIndPhaseResearchPartnershipManager;
    this.repIndStageInnovationManager = repIndStageInnovationManager;
    this.repIndGeographicScopeManager = repIndGeographicScopeManager;
    this.repIndInnovationTypeManager = repIndInnovationTypeManager;
    this.repIndRegionManager = repIndRegionManager;
    this.locElementManager = locElementManager;
    this.projectExpectedStudyManager = projectExpectedStudyManager;
    this.deriverableManager = deriverableManager;
    this.focusLevelManager = focusLevelManager;
    this.projectInnovationInfoManager = projectInnovationInfoManager;
    this.projectInnovationCrpManager = projectInnovationCrpManager;
    this.projectInnovationOrganizationManager = projectInnovationOrganizationManager;
    this.projectInnovationDeliverableManager = projectInnovationDeliverableManager;
    this.projectInnovationCountryManager = projectInnovationCountryManager;
    this.repIndOrganizationTypeManager = repIndOrganizationTypeManager;
    this.validator = validator;


  }

  /**
   * The name of the autosave file is constructed and the path is searched
   * 
   * @return Auto save file path
   */
  private Path getAutoSaveFilePath() {
    // get the class simple name
    String composedClassName = innovation.getClass().getSimpleName();
    // get the action name and replace / for _
    String actionFile = this.getActionName().replace("/", "_");
    // concatenate name and add the .json extension
    String autoSaveFile = innovation.getId() + "_" + composedClassName + "_" + this.getActualPhase().getDescription()
      + "_" + this.getActualPhase().getYear() + "_" + actionFile + ".json";
    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);
  }


  public List<LocElement> getCountries() {
    return countries;
  }


  @Override
  public List<GlobalUnit> getCrpList() {
    return crpList;
  }

  public List<Deliverable> getDeliverableList() {
    return deliverableList;
  }

  public List<ProjectExpectedStudy> getExpectedStudyList() {
    return expectedStudyList;
  }

  public List<RepIndGenderYouthFocusLevel> getFocusLevelList() {
    return focusLevelList;
  }

  public List<RepIndGeographicScope> getGeographicScopeList() {
    return geographicScopeList;
  }


  public ProjectInnovation getInnovation() {
    return innovation;
  }

  public long getInnovationID() {
    return innovationID;
  }

  public List<RepIndInnovationType> getInnovationTypeList() {
    return innovationTypeList;
  }

  public GlobalUnit getLoggedCrp() {
    return loggedCrp;
  }

  public List<RepIndOrganizationType> getOrganizationTypeList() {
    return organizationTypeList;
  }

  public List<RepIndPhaseResearchPartnership> getPhaseResearchList() {
    return phaseResearchList;
  }

  public Project getProject() {
    return project;
  }

  public long getProjectID() {
    return projectID;
  }

  public List<RepIndRegion> getRegionList() {
    return regionList;
  }

  public List<RepIndStageInnovation> getStageInnovationList() {
    return stageInnovationList;
  }

  @Override
  public void prepare() throws Exception {

    loggedCrp = (GlobalUnit) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = globalUnitManager.getGlobalUnitById(loggedCrp.getId());

    innovationID =
      Integer.parseInt(StringUtils.trim(this.getRequest().getParameter(APConstants.INNOVATION_REQUEST_ID)));

    innovation = projectInnovationManager.getProjectInnovationById(innovationID);


    if (innovation != null) {

      project = projectManager.getProjectById(innovation.getProject().getId());
      projectID = project.getId();


      Phase phase = phaseManager.getPhaseById(this.getActualPhase().getId());


      Path path = this.getAutoSaveFilePath();
      if (path.toFile().exists() && this.getCurrentUser().isAutoSave()) {

        // Autosave File in
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(path.toFile()));
        Gson gson = new GsonBuilder().create();
        JsonObject jReader = gson.fromJson(reader, JsonObject.class);
        reader.close();

        AutoSaveReader autoSaveReader = new AutoSaveReader();

        innovation = (ProjectInnovation) autoSaveReader.readFromJson(jReader);

        // Innovation Countries List AutoSave
        if (innovation.getCountriesIdsText() != null) {
          String[] countriesText = innovation.getCountriesIdsText().replace("[", "").replace("]", "").split(",");
          List<Long> countries = new ArrayList<>();
          for (String value : Arrays.asList(countriesText)) {
            countries.add(Long.parseLong(value.trim()));
          }
          innovation.setCountriesIds(countries);
        }

        // Innovation Organization Type List Autosave
        if (innovation.getOrganizations() != null) {
          for (ProjectInnovationOrganization projectInnovationOrganization : innovation.getOrganizations()) {
            projectInnovationOrganization.setRepIndOrganizationType(repIndOrganizationTypeManager
              .getRepIndOrganizationTypeById(projectInnovationOrganization.getRepIndOrganizationType().getId()));
          }
        }

        // Innovation Deliverable List Autosave
        if (innovation.getDeliverables() != null) {
          for (ProjectInnovationDeliverable projectInnovationDeliverable : innovation.getDeliverables()) {
            projectInnovationDeliverable.setDeliverable(
              deriverableManager.getDeliverableById(projectInnovationDeliverable.getDeliverable().getId()));
          }
        }

        // Innovation Crp List Autosave
        if (innovation.getCrps() != null) {
          for (ProjectInnovationCrp projectInnovationCrp : innovation.getCrps()) {
            projectInnovationCrp
              .setGlobalUnit(globalUnitManager.getGlobalUnitById(projectInnovationCrp.getGlobalUnit().getId()));
          }
        }


      } else {

        // Innovation Countries List
        if (innovation.getProjectInnovationCountries() == null) {
          innovation.setCountries(new ArrayList<>());
        } else {
          List<ProjectInnovationCountry> countries =
            projectInnovationCountryManager.getInnovationCountrybyPhase(innovation.getId(), phase.getId());
          innovation.setCountries(countries);
        }

        // Innovation Organization Type List
        if (innovation.getProjectInnovationOrganizations() != null) {
          innovation.setOrganizations(new ArrayList<>(innovation.getProjectInnovationOrganizations().stream()
            .filter(o -> o.isActive() && o.getPhase().getId() == phase.getId()).collect(Collectors.toList())));
        }

        // Innovation Deliverable List
        if (innovation.getProjectInnovationDeliverables() != null) {
          innovation.setDeliverables(new ArrayList<>(innovation.getProjectInnovationDeliverables().stream()
            .filter(d -> d.isActive() && d.getPhase().getId() == phase.getId()).collect(Collectors.toList())));
        }

        // Innovation Crp list
        if (innovation.getProjectInnovationCrps() != null) {
          innovation.setCrps(new ArrayList<>(innovation.getProjectInnovationCrps().stream()
            .filter(c -> c.isActive() && c.getPhase().getId() == phase.getId()).collect(Collectors.toList())));
        }
      }

      // Getting The list
      countries = locElementManager.findAll().stream().filter(c -> c.getLocElementType().getId().intValue() == 2)
        .collect(Collectors.toList());


      phaseResearchList = repIndPhaseResearchPartnershipManager.findAll();
      stageInnovationList = repIndStageInnovationManager.findAll();
      geographicScopeList = repIndGeographicScopeManager.findAll();
      innovationTypeList = repIndInnovationTypeManager.findAll();
      regionList = repIndRegionManager.findAll();
      focusLevelList = focusLevelManager.findAll();
      organizationTypeList = repIndOrganizationTypeManager.findAll();


      expectedStudyList = projectExpectedStudyManager.findAll().stream()
        .filter(ex -> ex.isActive() && ex.getType() != null
          && ex.getType() == TypeExpectedStudiesEnum.OUTCOMECASESTUDY.getId() && ex.getPhase().getId() == phase.getId())
        .collect(Collectors.toList());

      List<DeliverableInfo> infos = phase
        .getDeliverableInfos().stream().filter(c -> c.getDeliverable().getProject() != null
          && c.getDeliverable().getProject().equals(project) && c.getDeliverable().isActive())
        .collect(Collectors.toList());
      deliverableList = new ArrayList<>();
      for (DeliverableInfo deliverableInfo : infos) {
        Deliverable deliverable = deliverableInfo.getDeliverable();
        deliverable.setDeliverableInfo(deliverableInfo);
        deliverableList.add(deliverable);
      }


      crpList = globalUnitManager.findAll().stream()
        .filter(gu -> gu.isActive() && (gu.getGlobalUnitType().getId() == 1 || gu.getGlobalUnitType().getId() == 3))
        .collect(Collectors.toList());

    }

    innovationDB = projectInnovationManager.getProjectInnovationById(innovationID);

    String params[] = {loggedCrp.getAcronym(), project.getId() + ""};
    this.setBasePermission(this.getText(Permission.PROJECT_INNOVATIONS_BASE_PERMISSION, params));
  }


  @Override
  public String save() {
    if (this.hasPermission("canEdit")) {


      List<String> relationsName = new ArrayList<>();
      relationsName.add(APConstants.PROJECT_INNOVATION_COUNTRY_RELATION);
      relationsName.add(APConstants.PROJECT_INNOVATION_ORGANIZATION_RELATION);
      relationsName.add(APConstants.PROJECT_INNOVATION_CRP_RELATION);
      relationsName.add(APConstants.PROJECT_DELIVERABLE_CRP_RELATION);

      innovation.setActiveSince(new Date());
      innovation.setModifiedBy(this.getCurrentUser());
      innovation.setModificationJustification(this.getJustification());
      innovation.setCreatedBy(innovationDB.getCreatedBy());
      innovation.setActive(true);

      // Save the Countries List (ProjectInnovationcountry)
      if (innovation.getCountriesIds() != null || !innovation.getCountriesIds().isEmpty()) {

        List<ProjectInnovationCountry> countries = projectInnovationCountryManager
          .getInnovationCountrybyPhase(innovation.getId(), this.getActualPhase().getId());
        List<ProjectInnovationCountry> countriesSave = new ArrayList<>();
        for (Long countryIds : innovation.getCountriesIds()) {
          ProjectInnovationCountry countryInn = new ProjectInnovationCountry();
          countryInn.setLocElement(locElementManager.getLocElementById(countryIds));
          countryInn.setProjectInnovation(innovation);
          countryInn.setPhase(this.getActualPhase());
          countriesSave.add(countryInn);
          if (!countries.contains(countryInn)) {
            projectInnovationCountryManager.saveProjectInnovationCountry(countryInn);
          }
        }

        for (ProjectInnovationCountry projectInnovationCountry : countries) {
          if (!countriesSave.contains(projectInnovationCountry)) {
            projectInnovationCountryManager.deleteProjectInnovationCountry(projectInnovationCountry.getId());
          }
        }

      }


      innovation.getProjectInnovationInfo().setPhase(this.getActualPhase());
      innovation.getProjectInnovationInfo().setProjectInnovation(innovation);
      projectInnovationInfoManager.saveProjectInnovationInfo(innovation.getProjectInnovationInfo());
      projectInnovationManager.saveProjectInnovation(innovation, this.getActionName(), relationsName);

      return SUCCESS;
    } else {
      return NOT_AUTHORIZED;
    }
  }

  /**
   * Save Project Innovation Organization Information
   * 
   * @param projectInnovation
   * @param phase
   */
  public void saveOrganizations(ProjectInnovation projectInnovation, Phase phase) {

    // Search and Delete Info
    if (projectInnovation.getProjectInnovationOrganizations() != null
      && projectInnovation.getProjectInnovationOrganizations().size() > 0) {

      List<ProjectInnovationOrganization> organizationPrev =
        new ArrayList<>(projectInnovation.getProjectInnovationOrganizations().stream()
          .filter(nu -> nu.isActive() && nu.getPhase().getId() == phase.getId()).collect(Collectors.toList()));

      for (ProjectInnovationOrganization innovationOrganization : organizationPrev) {
        if (!innovation.getOrganizations().contains(innovationOrganization)) {
          projectInnovationOrganizationManager.deleteProjectInnovationOrganization(innovationOrganization.getId());
        }
      }
    }
  }


  public void setCountries(List<LocElement> countries) {
    this.countries = countries;
  }

  public void setCrpList(List<GlobalUnit> crpList) {
    this.crpList = crpList;
  }

  public void setDeliverableList(List<Deliverable> deliverableList) {
    this.deliverableList = deliverableList;
  }

  public void setExpectedStudyList(List<ProjectExpectedStudy> expectedStudyList) {
    this.expectedStudyList = expectedStudyList;
  }

  public void setFocusLevelList(List<RepIndGenderYouthFocusLevel> focusLevelList) {
    this.focusLevelList = focusLevelList;
  }

  public void setGeographicScopeList(List<RepIndGeographicScope> geographicScopeList) {
    this.geographicScopeList = geographicScopeList;
  }

  public void setInnovation(ProjectInnovation innovation) {
    this.innovation = innovation;
  }

  public void setInnovationID(long innovationID) {
    this.innovationID = innovationID;
  }

  public void setInnovationTypeList(List<RepIndInnovationType> innovationTypeList) {
    this.innovationTypeList = innovationTypeList;
  }

  public void setLoggedCrp(GlobalUnit loggedCrp) {
    this.loggedCrp = loggedCrp;
  }

  public void setOrganizationTypeList(List<RepIndOrganizationType> organizationTypeList) {
    this.organizationTypeList = organizationTypeList;
  }

  public void setPhaseResearchList(List<RepIndPhaseResearchPartnership> phaseResearchList) {
    this.phaseResearchList = phaseResearchList;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void setProjectID(long projectID) {
    this.projectID = projectID;
  }

  public void setRegionList(List<RepIndRegion> regionList) {
    this.regionList = regionList;
  }

  public void setStageInnovationList(List<RepIndStageInnovation> stageInnovationList) {
    this.stageInnovationList = stageInnovationList;
  }

  @Override
  public void validate() {
    if (save) {
      validator.validate(this, project, innovation, true);
    }
  }


}
