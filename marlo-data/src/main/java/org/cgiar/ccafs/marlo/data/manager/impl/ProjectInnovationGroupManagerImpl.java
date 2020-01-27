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

/**************
 * @author Diego Perez - CIAT/CCAFS
 **************/

package org.cgiar.ccafs.marlo.data.manager.impl;

import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.dao.PhaseDAO;
import org.cgiar.ccafs.marlo.data.dao.ProjectInnovationGroupDAO;
import org.cgiar.ccafs.marlo.data.manager.ProjectInnovationGroupManager;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationGroup;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ProjectInnovationGroupManagerImpl implements ProjectInnovationGroupManager {

  private ProjectInnovationGroupDAO projectInnovationGroupDAO;
  // Managers
  private PhaseDAO phaseDAO;

  @Inject
  public ProjectInnovationGroupManagerImpl(ProjectInnovationGroupDAO projectInnovationGroupDAO, PhaseDAO phaseDAO) {
    this.projectInnovationGroupDAO = projectInnovationGroupDAO;
    this.phaseDAO = phaseDAO;
  }

  @Override
  public void deleteProjectInnovationGroup(long projectInnovationGroupId) {
    ProjectInnovationGroup projectInnovationGroup = this.getProjectInnovationGroupById(projectInnovationGroupId);

    // Conditions to Project Innovation Works In AR phase and Upkeep Phase
    if (projectInnovationGroup.getPhase().getDescription().equals(APConstants.PLANNING)
      && projectInnovationGroup.getPhase().getNext() != null) {
      this.deleteProjectInnovationGroupPhase(projectInnovationGroup.getPhase().getNext(),
        projectInnovationGroup.getProjectInnovationOwner(), projectInnovationGroup);
    }

    if (projectInnovationGroup.getPhase().getDescription().equals(APConstants.REPORTING)) {
      if (projectInnovationGroup.getPhase().getNext() != null
        && projectInnovationGroup.getPhase().getNext().getNext() != null) {
        Phase upkeepPhase = projectInnovationGroup.getPhase().getNext().getNext();
        if (upkeepPhase != null) {
          this.deleteProjectInnovationGroupPhase(upkeepPhase, projectInnovationGroup.getProjectInnovationOwner(),
            projectInnovationGroup);
        }
      }
    }

    projectInnovationGroupDAO.deleteProjectInnovationGroup(projectInnovationGroupId);
  }

  public void deleteProjectInnovationGroupPhase(Phase next, long innovationowner,
    ProjectInnovationGroup projectInnovationGroup) {
    Phase phase = phaseDAO.find(next.getId());

    List<ProjectInnovationGroup> projectInnovationGroups = phase.getProjectInnovationGroups().stream()
      .filter(c -> c.isActive() && c.getProjectInnovationOwner().longValue() == innovationowner
        && c.getProjectInnovation().getId().equals(projectInnovationGroup.getProjectInnovation().getId()))
      .collect(Collectors.toList());

    for (ProjectInnovationGroup projectInnovationGroupDB : projectInnovationGroups) {
      projectInnovationGroupDAO.deleteProjectInnovationGroup(projectInnovationGroupDB.getId());
    }

    if (phase.getNext() != null) {
      this.deleteProjectInnovationGroupPhase(phase.getNext(), innovationowner, projectInnovationGroup);
    }
  }

  @Override
  public boolean existProjectInnovationGroup(long projectInnovationGroupID) {
    return projectInnovationGroupDAO.existProjectInnovationGroup(projectInnovationGroupID);
  }

  @Override
  public List<ProjectInnovationGroup> findAll() {
    return projectInnovationGroupDAO.findAll();
  }

  @Override
  public ProjectInnovationGroup getProjectInnovationGroupById(long projectInnovationGroupID) {
    return projectInnovationGroupDAO.find(projectInnovationGroupID);
  }

  @Override
  public ProjectInnovationGroup getProjectInnovationGroupById(long innovationowner, long innovationid, long phaseID) {
    return projectInnovationGroupDAO.getProjectInnovationGroupById(innovationowner, innovationid, phaseID);
  }

  public void saveInnovationGroupPhase(Phase next, long innovationid, ProjectInnovationGroup projectInnovationGroup) {

    Phase phase = phaseDAO.find(next.getId());

    List<ProjectInnovationGroup> projectInnovatioGroups = phase.getProjectInnovationGroups().stream()
      .filter(c -> c.getProjectInnovationOwner().longValue() == innovationid
        && c.getProjectInnovation().getId().equals(projectInnovationGroup.getProjectInnovation().getId()))
      .collect(Collectors.toList());

    if (projectInnovatioGroups.isEmpty()) {
      ProjectInnovationGroup projectInnovationGroupAdd = new ProjectInnovationGroup();
      projectInnovationGroupAdd.setProjectInnovation(projectInnovationGroup.getProjectInnovation());
      projectInnovationGroupAdd.setPhase(phase);
      projectInnovationGroupAdd.setProjectInnovationOwner(projectInnovationGroup.getProjectInnovationOwner());
      projectInnovationGroupDAO.save(projectInnovationGroupAdd);
    }

    if (phase.getNext() != null) {
      this.saveInnovationGroupPhase(phase.getNext(), innovationid, projectInnovationGroup);
    }
  }

  @Override
  public ProjectInnovationGroup saveProjectInnovationGroup(ProjectInnovationGroup projectInnovationGroup) {
    ProjectInnovationGroup group = projectInnovationGroupDAO.save(projectInnovationGroup);
    Phase phase = phaseDAO.find(group.getPhase().getId());


    // Conditions to Project Innovation Works In AR phase and Upkeep Phase
    if (phase.getDescription().equals(APConstants.PLANNING) && phase.getNext() != null) {
      this.saveInnovationGroupPhase(group.getPhase().getNext(), group.getProjectInnovation().getId(),
        projectInnovationGroup);
    }

    if (phase.getDescription().equals(APConstants.REPORTING)) {
      if (phase.getNext() != null && phase.getNext().getNext() != null) {
        Phase upkeepPhase = phase.getNext().getNext();
        if (upkeepPhase != null) {
          this.saveInnovationGroupPhase(upkeepPhase, group.getProjectInnovation().getId(), projectInnovationGroup);
        }
      }
    }
    return group;
  }
}
