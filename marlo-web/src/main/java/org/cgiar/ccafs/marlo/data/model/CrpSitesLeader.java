/*****************************************************************
 * This file is part of CCAFS Planning and Reporting Platform.
 * CCAFS P&R is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS P&R is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS P&R. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/
package org.cgiar.ccafs.marlo.data.model;
// Generated May 26, 2016 9:42:28 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * CrpSitesLeader generated by hbm2java
 */
public class CrpSitesLeader implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 1702272624319778464L;


  @Expose
  private Long id;

  @Expose
  private CrpsSiteIntegration crpsSiteIntegration;

  @Expose
  private User user;

  @Expose
  private boolean active;

  @Expose
  private boolean regional;
  @Expose
  private User createdBy;

  @Expose
  private Date activeSince;

  @Expose
  private User modifiedBy;

  @Expose
  private String modificationJustification;

  public CrpSitesLeader() {
  }

  public CrpSitesLeader(CrpsSiteIntegration crpsSitesIntegration, User user) {
    this.crpsSiteIntegration = crpsSitesIntegration;
    this.user = user;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    CrpSitesLeader other = (CrpSitesLeader) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public Date getActiveSince() {
    return activeSince;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public CrpsSiteIntegration getCrpsSiteIntegration() {
    return this.crpsSiteIntegration;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }

  public String getModificationJustification() {
    return modificationJustification;
  }

  @Override
  public User getModifiedBy() {
    return modifiedBy;
  }

  public User getUser() {
    return this.user;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public boolean isRegional() {
    return regional;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setActiveSince(Date activeSince) {
    this.activeSince = activeSince;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public void setCrpsSiteIntegration(CrpsSiteIntegration crpsSitesIntegration) {
    this.crpsSiteIntegration = crpsSitesIntegration;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public void setRegional(boolean regional) {
    this.regional = regional;
  }

  public void setUser(User user) {
    this.user = user;
  }


}

