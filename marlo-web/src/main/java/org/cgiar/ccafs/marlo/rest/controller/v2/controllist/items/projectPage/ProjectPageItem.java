/*****************************************************************
 * \ * This file is part of Managing Agricultural Research for Learning &
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

package org.cgiar.ccafs.marlo.rest.controller.v2.controllist.items.projectPage;

import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpClusterOfActivityManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectLocationManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.model.Activity;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableDissemination;
import org.cgiar.ccafs.marlo.data.model.DeliverableInfo;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectFocus;
import org.cgiar.ccafs.marlo.data.model.ProjectLocation;
import org.cgiar.ccafs.marlo.rest.dto.ProjectPageDTO;
import org.cgiar.ccafs.marlo.rest.errors.FieldErrorDTO;
import org.cgiar.ccafs.marlo.rest.errors.MARLOFieldValidationException;
import org.cgiar.ccafs.marlo.rest.mappers.ProjectPageMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Luis Benavides - CIAT/CCAFS
 */

@Named
public class ProjectPageItem<T> {

  private static final String STRING_TRUE = "true";
  private ProjectManager projectManager;
  private PhaseManager phaseManager;
  private GlobalUnitManager globalUnitManager;
  private ProjectLocationManager projectLocationManager;
  private LocElementManager locElementManager;
  private CrpClusterOfActivityManager crpClusterOfActivityManager;
  private ProjectPageMapper projectPageMapper;


  @Inject
  public ProjectPageItem(ProjectManager projectManager, PhaseManager phaseManager, GlobalUnitManager globalUnitManager,
    ProjectLocationManager projectLocationManager, LocElementManager locElementManager,
    CrpClusterOfActivityManager crpClusterOfActivityManager, ProjectPageMapper projectPageMapper) {
    this.projectManager = projectManager;
    this.phaseManager = phaseManager;
    this.globalUnitManager = globalUnitManager;
    this.projectPageMapper = projectPageMapper;
    this.projectLocationManager = projectLocationManager;
    this.locElementManager = locElementManager;
    this.crpClusterOfActivityManager = crpClusterOfActivityManager;
  }

  public List<ProjectPageDTO> findAllProjectPage(String globalUnitAcronym) {
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
    List<ProjectPageDTO> ppList = new ArrayList<ProjectPageDTO>();
    GlobalUnit globalUnit = globalUnitManager.findGlobalUnitByAcronym(globalUnitAcronym);
    List<Project> projectList = new ArrayList<Project>();
    if (globalUnit == null) {
      fieldErrors.add(new FieldErrorDTO("ProjectPageDTO", "GlobalUnitEntity", "Invalid CGIAR entity acronym"));
    } else {
      Boolean crpProjectPage = globalUnit.getCustomParameters().stream()
        .filter(c -> c.getParameter().getKey().equalsIgnoreCase(APConstants.CRP_PROJECT_PAGE))
        .allMatch(t -> (t.getValue() == null) ? false : t.getValue().equalsIgnoreCase(STRING_TRUE));
      // is project web page expose enable by CRP parameter
      if (crpProjectPage) {

        for (Project project : projectManager.getProjectWebPageList(globalUnit.getId())) {
          project = (project != null) ? this.getProjectInformation(project, globalUnit) : null;
          projectList.add(project);
        }
      }
    }

    if (!fieldErrors.isEmpty()) {
      throw new MARLOFieldValidationException("Field Validation errors", "", fieldErrors);
    }
    ppList = projectList.stream().map(projects -> this.projectPageMapper.projectToProjectPageDTO(projects))
      .collect(Collectors.toList());
    return ppList;
  }

  /**
   * find a project requesting by Id
   * 
   * @param id
   * @return
   */
  public ResponseEntity<ProjectPageDTO> findProjectPageById(Long id, String globalUnitAcronym) {

    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    Project project = projectManager.getProjectById(id);

    if (project == null) {
      fieldErrors.add(new FieldErrorDTO("ProjectPageDTO", "Project", "Invalid Project code"));
    }

    GlobalUnit globalUnit = globalUnitManager.findGlobalUnitByAcronym(globalUnitAcronym);

    if (globalUnit == null) {
      fieldErrors.add(new FieldErrorDTO("ProjectPageDTO", "GlobalUnitEntity", "Invalid CGIAR entity acronym"));
    } else {
      Boolean crpProjectPage = globalUnit.getCustomParameters().stream()
        .filter(c -> c.getParameter().getKey().equalsIgnoreCase(APConstants.CRP_PROJECT_PAGE))
        .allMatch(t -> (t.getValue() == null) ? false : t.getValue().equalsIgnoreCase(STRING_TRUE));

      if (crpProjectPage) {
        project = (project != null) ? this.getProjectInformation(project, globalUnit) : null;
      } else {
        fieldErrors.add(new FieldErrorDTO("ProjectPageDTO", "GlobalUnitEntity", "CGIAR entity not autorized"));
      }
    }

    if (!fieldErrors.isEmpty()) {
      throw new MARLOFieldValidationException("Field Validation errors", "", fieldErrors);
    }

    return Optional.ofNullable(project).map(this.projectPageMapper::projectToProjectPageDTO)
      .map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * for this method get the project information
   * 
   * @param project
   * @return
   */
  public Project getProjectInformation(Project project, GlobalUnit globalUnit) {

    Phase phase = phaseManager.getActivePhase(globalUnit.getId());

    // Get the Project Info
    project.getProjecInfoPhase(phase);


    // Get the Flagship and Programs Regions that belongs in the Project
    List<CrpProgram> programs = new ArrayList<>();
    for (ProjectFocus projectFocuses : project.getProjectFocuses().stream()
      .filter(c -> c.isActive() && c.getPhase() != null && c.getPhase().getId().equals(phase.getId())
        && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue()
        && c.getCrpProgram().getCrp().getId().equals(globalUnit.getId()))
      .collect(Collectors.toList())) {
      programs.add(projectFocuses.getCrpProgram());
    }

    List<CrpProgram> regions = new ArrayList<>();

    for (ProjectFocus projectFocuses : project.getProjectFocuses().stream()
      .filter(c -> c.isActive() && c.getPhase() != null && c.getPhase().getId().equals(phase.getId())
        && c.getCrpProgram().getProgramType() == ProgramType.REGIONAL_PROGRAM_TYPE.getValue()
        && c.getCrpProgram().getCrp().getId().equals(globalUnit.getId()))
      .collect(Collectors.toList())) {
      regions.add(projectFocuses.getCrpProgram());
    }

    List<ProjectLocation> projectRegions = new ArrayList<ProjectLocation>();
    List<ProjectLocation> projectCountries = new ArrayList<ProjectLocation>();
    for (ProjectLocation projectLocations : projectLocationManager.findAll().stream()
      .filter(c -> c.isActive() && c.getPhase() != null && c.getPhase().getId().equals(phase.getId())
        && c.getProject().getId().longValue() == project.getId().longValue())
      .collect(Collectors.toList())) {
      LocElement loc = locElementManager.getLocElementById(projectLocations.getLocElement().getId());
      if (loc != null) {
        projectLocations.setLocElement(loc);
        if (loc.getLocElementType().getId().longValue() == 1) {
          projectRegions.add(projectLocations);
        } else if (loc.getLocElementType().getId().longValue() == 2) {
          projectCountries.add(projectLocations);
        }
      }
    }

    // activities ongoing or complete
    List<Activity> activities = project.getActivities().stream()
      .filter(a -> a.isActive() && a.getPhase().equals(phase)
        && (a.getActivityStatus().longValue() == 3 || a.getActivityStatus().longValue() == 2))
      .collect(Collectors.toList());

    // Deliverables complete

    List<Deliverable> deliverables = new ArrayList<Deliverable>();
    for (Deliverable deliverable : project.getDeliverables().stream().filter(c -> c.isActive())
      .collect(Collectors.toList())) {
      DeliverableInfo deliverableInfo = deliverable.getDeliverableInfo(phase);
      if (deliverableInfo != null && deliverableInfo.getStatus().longValue() == 3) {
        deliverable.setDeliverableInfo(deliverableInfo);
        DeliverableDissemination deliverableDissemination = deliverable.getDissemination(phase);
        deliverable.setDissemination(deliverableDissemination);
        deliverable.setIsFindable(this.isF(deliverable));
        deliverable.setIsAccesible(this.isA(deliverable));
        deliverable.setIsInteroperable(this.isI(deliverable));
        deliverable.setIsReusable(this.isR(deliverable));
        deliverables.add(deliverable);
      }
    }


    project.setRegions(regions);
    project.setFlagships(programs);
    project.setProjectRegions(projectRegions);
    project.setLocations(projectCountries);
    project.setProjectActivities(activities);
    project.setProjectDeliverables(deliverables);


    return project;
  }

  private Boolean isA(Deliverable deliverableBD) {
    try {
      if (deliverableBD.getDissemination().getIsOpenAccess() != null
        && deliverableBD.getDissemination().getIsOpenAccess().booleanValue()) {
        return true;
      }

      if (deliverableBD.getDissemination().getIsOpenAccess() == null) {
        return null;
      }
      return false;
    } catch (Exception e) {
      return null;
    }
  }

  private Boolean isF(Deliverable deliverableBD) {
    try {
      if (deliverableBD.getDissemination().getAlreadyDisseminated() != null) {
        if (deliverableBD.getDissemination().getAlreadyDisseminated().booleanValue()) {
          if (deliverableBD.getDissemination().getDisseminationChannel() != null) {
            if (deliverableBD.getDissemination().getDisseminationChannel().equals("other")) {
              if (deliverableBD.getDissemination().getDisseminationUrl() != null
                && !deliverableBD.getDissemination().getDisseminationUrl().trim().isEmpty()) {
                return true;
              }
            } else {
              if (deliverableBD.getDissemination().getSynced() != null
                && deliverableBD.getDissemination().getSynced()) {
                return true;
              }
            }
          }
        } else {
          return false;
        }
      } else {
        return null;
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  private Boolean isI(Deliverable deliverableBD) {
    try {
      if (deliverableBD.getDissemination().getAlreadyDisseminated() != null
        && deliverableBD.getDissemination().getAlreadyDisseminated().booleanValue()) {
        String channel = deliverableBD.getDissemination().getDisseminationChannel();
        String link = deliverableBD.getDissemination().getDisseminationUrl().replaceAll(" ", "%20");;
        if (channel == null || channel.equals("-1")) {
          return null;
        }
        if (link == null || link.equals("-1") || link.isEmpty()) {
          return null;
        }

        // If the deliverable is synced
        if (deliverableBD.getDissemination().getSynced() != null
          && deliverableBD.getDissemination().getSynced().booleanValue()) {
          return true;
        }
        return null;
      }
      if (deliverableBD.getDissemination().getAlreadyDisseminated() == null) {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  public Boolean isR(Deliverable deliverableBD) {
    try {

      if (deliverableBD.getDeliverableInfo().getAdoptedLicense() == null) {
        return null;
      }
      if (deliverableBD.getDeliverableInfo().getAdoptedLicense()) {
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }

}
