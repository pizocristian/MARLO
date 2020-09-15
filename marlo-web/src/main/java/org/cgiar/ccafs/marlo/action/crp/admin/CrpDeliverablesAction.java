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

package org.cgiar.ccafs.marlo.action.crp.admin;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.data.manager.DeliverableInfoManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableInfo;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;


/**
 * DeliverablesReplicationAction:
 * 
 * @author Andrés Valencia - CIAT/CCAFS
 */
public class CrpDeliverablesAction extends BaseAction {

  private static final long serialVersionUID = 6392973543544674655L;

  // private static Logger logger = LoggerFactory.getLogger(CrpDeliverablesAction.class);

  // Managers
  private DeliverableManager deliverableManager;
  private DeliverableInfoManager deliverableInfoManager;
  private PhaseManager phaseManager;
  private ProjectManager projectManager;
  private GlobalUnitManager globalUnitManager;

  // Variables
  private String entityByPhaseList;
  private List<GlobalUnit> crps;
  private List<Phase> phases;
  private long selectedPhaseID;
  private Long phaseID;
  private Long deliverableID;
  private Long projectID;
  private List<Deliverable> deliverables;
  private List<Project> projects;
  private String moveToSelection;


  @Inject
  public CrpDeliverablesAction(APConfig config, PhaseManager phaseManager, DeliverableManager deliverableManager,
    DeliverableInfoManager deliverableInfoManager, ProjectManager projectManager, GlobalUnitManager globalUnitManager) {
    super(config);
    this.phaseManager = phaseManager;
    this.deliverableManager = deliverableManager;
    this.deliverableInfoManager = deliverableInfoManager;
    this.projectManager = projectManager;
    this.globalUnitManager = globalUnitManager;
  }

  public List<GlobalUnit> getCrps() {
    return crps;
  }

  public Long getDeliverableID() {
    return deliverableID;
  }

  public List<Deliverable> getDeliverables() {
    return deliverables;
  }

  public String getEntityByPhaseList() {
    return entityByPhaseList;
  }

  public String getMoveToSelection() {
    return moveToSelection;
  }


  @Override
  public Long getPhaseID() {
    return phaseID;
  }

  @Override
  public List<Phase> getPhases() {
    return phases;
  }

  public Long getProjectID() {
    return projectID;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public long getSelectedPhaseID() {
    return selectedPhaseID;
  }


  public void moveDeliverablesPhase() {

    Deliverable deliverable = deliverableManager.getDeliverableById(deliverableID);
    Phase phaseToMove = phaseManager.getPhaseById(phaseID);

    if (deliverable != null && deliverable.getDeliverableInfo(this.getActualPhase()) != null) {
      List<DeliverableInfo> deliverableInfos = deliverableInfoManager
        .findAll().stream().filter(di -> di != null && di.getDeliverable() != null && di.isActive()
          && di.getDeliverable().getId() != null && di.getDeliverable().getId().equals(deliverable.getId()))
        .collect(Collectors.toList());

      if (deliverableInfos != null && !deliverableInfos.isEmpty()) {

        // Change the created phase in deliverable and create the deliverable info for the 'phase to move'
        deliverable.setPhase(phaseToMove);
        deliverableManager.saveDeliverable(deliverable);

        DeliverableInfo deliverableInfo = deliverable.getDeliverableInfo(this.getActualPhase());
        DeliverableInfo deliverableInfoToMove = deliverableInfo;
        // deliverableInfo.setActive(false);
        // deliverableInfoManager.saveDeliverableInfo(deliverableInfo);

        if (deliverable.getDeliverableInfo(phaseToMove) == null) {
          deliverableInfoToMove.setPhase(phaseToMove);
          deliverableInfoManager.saveDeliverableInfo(deliverableInfoToMove);
        }

        // Desactivate deliverables info with previous phases to 'phase to move'
        for (DeliverableInfo info : deliverableInfos) {
          if (info.getPhase() != null && info.getPhase().getId() != null
            && info.getPhase().getId() < phaseToMove.getId()) {
            info.setActive(false);
            deliverableInfoManager.saveDeliverableInfo(info);
          }
        }

      }
    }

  }

  public void moveDeliverablesProject() {
    if (phaseID != 0 && deliverableID != 0) {
      Deliverable deliverable = deliverableManager.getDeliverableById(deliverableID);
      Project project = projectManager.getProjectById(projectID);

      // Change the project for selected deliverable
      if (deliverable != null) {
        deliverable.setProject(project);
        deliverableManager.saveDeliverable(deliverable);
      }
    }

  }

  @Override
  public void prepare() throws Exception {
    super.prepare();
    crps = globalUnitManager.findAll().stream()
      .filter(c -> c.isMarlo() && c.isActive() && c.getAcronym().equals(this.getCurrentCrp().getAcronym()))
      .collect(Collectors.toList());

    phases =
      phaseManager.findAll().stream().filter(c -> c.isActive() && c.getCrp() != null && this.getCurrentCrp() != null
        && c.getCrp().getId().equals(this.getCurrentCrp().getId())).collect(Collectors.toList());

    deliverables = deliverableManager.getDeliverablesByPhase(this.getActualPhase().getId());

    if (deliverables != null && !deliverables.isEmpty()) {
      for (Deliverable deliverable : deliverables) {
        if (deliverable != null && deliverable.getId() != null) {
          deliverable = deliverableManager.getDeliverableById(deliverable.getId());
        }
      }
    }

    String[] statuses = null;
    projects = projectManager.getActiveProjectsByPhase(this.getActualPhase(), 0, statuses);

    if (projects != null && !projects.isEmpty()) {
      for (Project project : projects) {
        if (project != null && project.getId() != null) {
          project = projectManager.getProjectById(project.getId());
        }
      }
    }
  }

  @Override
  public String save() {
    if (this.canAccessSuperAdmin()) {
      if (deliverableID != null && deliverableID != 0) {
        if (moveToSelection != null && !moveToSelection.isEmpty()) {

          switch (moveToSelection) {
            case "project":
              if (projectID != null && projectID != 0) {
                this.moveDeliverablesProject();
              }
              break;
            case "phase":
              if (phaseID != null && phaseID != 0) {
                this.moveDeliverablesPhase();
              }
              break;
          }

        }
      }

      return SUCCESS;
    } else {
      return NOT_AUTHORIZED;
    }
  }

  public void setCrps(List<GlobalUnit> crps) {
    this.crps = crps;
  }

  public void setDeliverableID(Long deliverableID) {
    this.deliverableID = deliverableID;
  }

  public void setDeliverables(List<Deliverable> deliverables) {
    this.deliverables = deliverables;
  }

  public void setEntityByPhaseList(String entityByPhaseList) {
    this.entityByPhaseList = entityByPhaseList;
  }

  public void setMoveToSelection(String moveToSelection) {
    this.moveToSelection = moveToSelection;
  }

  @Override
  public void setPhaseID(Long phaseID) {
    this.phaseID = phaseID;
  }

  public void setPhases(List<Phase> phases) {
    this.phases = phases;
  }

  public void setProjectID(Long projectID) {
    this.projectID = projectID;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public void setSelectedPhaseID(long selectedPhaseID) {
    this.selectedPhaseID = selectedPhaseID;
  }


}