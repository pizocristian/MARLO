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

package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.model.ProjectInnovationGroup;

import java.util.List;

public interface ProjectInnovationGroupManager {

  /**
   * This method removes a specific projectInnovationGroup value from the database.
   * 
   * @param projectInnovationGroupId is the projectInnovationGroup identifier.
   * @return true if the projectInnovationGroup was successfully deleted, false otherwise.
   */
  public void deleteProjectInnovationGroup(long projectInnovationGroupId);


  /**
   * This method validate if the projectInnovationGroup identify with the given id exists in the system.
   * 
   * @param projectInnovationGroupID is a projectInnovationGroup identifier.
   * @return true if the projectInnovationGroup exists, false otherwise.
   */
  public boolean existProjectInnovationGroup(long projectInnovationGroupID);


  /**
   * This method gets a list of projectInnovationGroup that are active
   * 
   * @return a list from ProjectInnovationGroup null if no exist records
   */
  public List<ProjectInnovationGroup> findAll();


  /**
   * This method gets a projectInnovationGroup object by a given projectInnovationGroup identifier.
   * 
   * @param projectInnovationGroupID is the projectInnovationGroup identifier.
   * @return a ProjectInnovationGroup object.
   */
  public ProjectInnovationGroup getProjectInnovationGroupById(long projectInnovationGroupID);

  public ProjectInnovationGroup getProjectInnovationGroupById(long innovationowner, long innovationid, long phaseID);


  /**
   * This method saves the information of the given projectInnovationGroup
   * 
   * @param projectInnovationGroup - is the projectInnovationGroup object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the projectInnovationGroup
   *         was
   *         updated
   *         or -1 is some error occurred.
   */
  public ProjectInnovationGroup saveProjectInnovationGroup(ProjectInnovationGroup projectInnovationGroup);
}
