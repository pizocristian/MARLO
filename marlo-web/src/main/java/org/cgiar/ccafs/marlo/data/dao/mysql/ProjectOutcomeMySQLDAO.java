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


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.ProjectOutcomeDAO;
import org.cgiar.ccafs.marlo.data.model.ProjectOutcome;

import java.util.List;

import com.google.inject.Inject;

public class ProjectOutcomeMySQLDAO implements ProjectOutcomeDAO {

  private StandardDAO dao;

  @Inject
  public ProjectOutcomeMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteProjectOutcome(long projectOutcomeId) {
    ProjectOutcome projectOutcome = this.find(projectOutcomeId);
    projectOutcome.setActive(false);
    return this.save(projectOutcome) > 0;
  }

  @Override
  public boolean existProjectOutcome(long projectOutcomeID) {
    ProjectOutcome projectOutcome = this.find(projectOutcomeID);
    if (projectOutcome == null) {
      return false;
    }
    return true;

  }

  @Override
  public ProjectOutcome find(long id) {
    return dao.find(ProjectOutcome.class, id);

  }

  @Override
  public List<ProjectOutcome> findAll() {
    String query = "from " + ProjectOutcome.class.getName() + " where is_active=1";
    List<ProjectOutcome> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(ProjectOutcome projectOutcome) {
    if (projectOutcome.getId() == null) {
      dao.save(projectOutcome);
    } else {
      dao.update(projectOutcome);
    }


    return projectOutcome.getId();
  }


}