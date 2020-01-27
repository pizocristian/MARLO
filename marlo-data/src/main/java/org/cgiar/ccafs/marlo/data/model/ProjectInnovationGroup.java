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

package org.cgiar.ccafs.marlo.data.model;

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

public class ProjectInnovationGroup extends MarloBaseEntity implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = -3905823497556503612L;


  @Expose
  private Long projectInnovationOwner;

  @Expose
  private Phase phase;

  @Expose
  private ProjectInnovation projectInnovation;


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
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


  public Phase getPhase() {
    return phase;
  }


  public ProjectInnovation getProjectInnovation() {
    return projectInnovation;
  }


  public Long getProjectInnovationOwner() {
    return projectInnovationOwner;
  }


  @Override
  public boolean isActive() {
    return true;
  }


  @Override
  public void setModifiedBy(User modifiedBy) {

  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setProjectInnovation(ProjectInnovation projectInnovation) {
    this.projectInnovation = projectInnovation;
  }

  public void setProjectInnovationOwner(Long projectInnovationOwner) {
    this.projectInnovationOwner = projectInnovationOwner;
  }


}
