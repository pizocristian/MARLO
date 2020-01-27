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

package org.cgiar.ccafs.marlo.data.dao.mysql;


import org.cgiar.ccafs.marlo.data.dao.ProjectInnovationGroupDAO;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.SessionFactory;

@Named
public class ProjectInnovationGroupMySQLDAO extends AbstractMarloDAO<ProjectInnovationGroup, Long>
  implements ProjectInnovationGroupDAO {

  @Inject
  public ProjectInnovationGroupMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public void deleteProjectInnovationGroup(long projectInnovationGroupId) {
    ProjectInnovationGroup projectInnovationGroup = this.find(projectInnovationGroupId);
    this.delete(projectInnovationGroup);
  }

  @Override
  public boolean existProjectInnovationGroup(long projectInnovationGroupID) {
    ProjectInnovationGroup projectInnovationGroup = this.find(projectInnovationGroupID);
    if (projectInnovationGroup == null) {
      return false;
    }
    return true;
  }

  @Override
  public ProjectInnovationGroup find(long id) {
    return super.find(ProjectInnovationGroup.class, id);
  }

  @Override
  public List<ProjectInnovationGroup> findAll() {
    String query = "from " + ProjectInnovationGroup.class.getName();
    List<ProjectInnovationGroup> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return list = new ArrayList<ProjectInnovationGroup>();
  }

  @Override
  public ProjectInnovationGroup getProjectInnovationGroupById(long innovationowner, long innovationid, long phaseID) {
    String query = "from " + ProjectInnovationGroup.class.getName() + " where project_innovation_owner='"
      + innovationowner + "'" + " AND project_innovation_id='" + innovationid + "' AND id_phase='" + phaseID + "'";
    List<ProjectInnovationGroup> list = super.findAll(query);
    if (list.size() > 0) {
      return list.get(0);
    }
    return null;

  }

  @Override
  public ProjectInnovationGroup save(ProjectInnovationGroup projectInnovationGroup) {
    if (projectInnovationGroup.getId() == null) {
      super.saveEntity(projectInnovationGroup);
    } else {
      projectInnovationGroup = super.update(projectInnovationGroup);
    }
    return projectInnovationGroup;
  }

}
