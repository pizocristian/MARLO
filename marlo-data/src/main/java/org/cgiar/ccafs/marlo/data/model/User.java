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

package org.cgiar.ccafs.marlo.data.model;
// Generated May 17, 2016 9:53:46 AM by Hibernate Tools 4.3.1.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * Users generated by hbm2java
 */
public class User extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 3674438945983473335L;

  @Expose
  private String firstName;


  @Expose
  private String lastName;

  @Expose
  private String username;


  @Expose
  private String email;

  @Expose
  private String password;


  @Expose
  private boolean cgiarUser;

  private boolean autoSave;

  private Boolean agreeTerms;

  private Date lastLogin;

  private Set<UserRole> userRoles = new HashSet<UserRole>(0);

  private Set<CenterLeader> researchLeaders = new HashSet<CenterLeader>(0);

  private Set<CrpUser> crpUsers = new HashSet<CrpUser>(0);

  private Set<CrpSitesLeader> crpSitesLeaders = new HashSet<CrpSitesLeader>(0);

  private Set<CrpClusterActivityLeader> crpClusterActivityLeaders = new HashSet<CrpClusterActivityLeader>(0);

  private Set<LiaisonUser> liasonsUsers = new HashSet<LiaisonUser>(0);

  private Set<IpLiaisonUser> ipLiaisonUsers = new HashSet<IpLiaisonUser>(0);

  private Set<Submission> submissions = new HashSet<Submission>(0);


  private Set<ProjectPartnerPerson> projectPartnerPersons = new HashSet<ProjectPartnerPerson>(0);
  private List<CrpUser> crpUser;
  // MARLO CIAT relations
  private Set<CrpProgramLeader> crpProgramLeaders = new HashSet<CrpProgramLeader>(0);

  // This variable is use for get information in Deliverable user Partnerships
  private boolean pdSelected;
  private boolean pdCanSelect;


  public User() {
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
    User other = (User) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }


  public Boolean getAgreeTerms() {
    if (agreeTerms == null) {
      return false;
    }
    return agreeTerms;
  }


  /**
   * This method returns the user's full name.
   * 
   * @return a String that represents the user's full name.
   *         e.g. Héctor Tobón
   */
  public String getComposedCompleteName() {
    return this.firstName + " " + this.lastName;
  }

  public String getComposedID() {
    String composedId = this.email.split("@")[0] + "-" + this.getId();
    return composedId;
  }

  /**
   * This method returns a composed way to show a User.
   * 
   * @return a String that represents a User.
   *         e.g. Tobón, Héctor <h.f.tobon@cgiar.org>
   */
  public String getComposedName() {
    if (this.getId() == null || this.getId() == -1) {
      return "";
    }
    return this.getLastName() + ", " + this.getFirstName() + " <" + this.getEmail() + ">";
  }

  public String getComposedNameWithoutEmail() {
    if (this.getId() == null || this.getId() == -1) {
      return "";
    }
    return this.getLastName() + ", " + this.getFirstName();
  }

  public Set<CrpClusterActivityLeader> getCrpClusterActivityLeaders() {
    return crpClusterActivityLeaders;
  }


  public Set<CrpProgramLeader> getCrpProgramLeaders() {
    return crpProgramLeaders;
  }

  public Set<CrpSitesLeader> getCrpSitesLeaders() {
    return crpSitesLeaders;
  }

  public List<CrpUser> getCrpUser() {
    return crpUser;
  }

  public Set<CrpUser> getCrpUsers() {
    return this.crpUsers;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstName() {
    return this.firstName;
  }


  public Set<IpLiaisonUser> getIpLiaisonUsers() {
    return ipLiaisonUsers;
  }


  public Date getLastLogin() {
    return this.lastLogin;
  }

  public String getLastName() {
    return this.lastName;
  }

  public Set<LiaisonUser> getLiasonsUsers() {
    return liasonsUsers;
  }

  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public String getPassword() {
    return this.password;
  }


  public Set<ProjectPartnerPerson> getProjectPartnerPersons() {
    return projectPartnerPersons;
  }


  public Set<CenterLeader> getResearchLeaders() {
    return researchLeaders;
  }

  public Set<Submission> getSubmissions() {
    return submissions;
  }

  public String getUsername() {
    return this.username;
  }

  public Set<UserRole> getUserRoles() {
    return this.userRoles;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  public boolean isAutoSave() {
    return autoSave;
  }

  public boolean isCgiarUser() {
    return cgiarUser;
  }

  public boolean isPdCanSelect() {
    return pdCanSelect;
  }


  public boolean isPdSelected() {
    return pdSelected;
  }


  public void setAgreeTerms(Boolean agreeTerms) {
    this.agreeTerms = agreeTerms;
  }

  public void setAutoSave(boolean autoSave) {
    this.autoSave = autoSave;
  }


  public void setCgiarUser(boolean cgiarUser) {
    this.cgiarUser = cgiarUser;
  }

  public void setCrpClusterActivityLeaders(Set<CrpClusterActivityLeader> crpClusterActivityLeaders) {
    this.crpClusterActivityLeaders = crpClusterActivityLeaders;
  }


  public void setCrpProgramLeaders(Set<CrpProgramLeader> crpProgramLeaders) {
    this.crpProgramLeaders = crpProgramLeaders;
  }

  public void setCrpSitesLeaders(Set<CrpSitesLeader> crpSitesLeaders) {
    this.crpSitesLeaders = crpSitesLeaders;
  }

  public void setCrpUser(List<CrpUser> crpUser) {
    this.crpUser = crpUser;
  }

  public void setCrpUsers(Set<CrpUser> crpUsers) {
    this.crpUsers = crpUsers;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public void setIpLiaisonUsers(Set<IpLiaisonUser> ipLiaisonUsers) {
    this.ipLiaisonUsers = ipLiaisonUsers;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setLiasonsUsers(Set<LiaisonUser> liasonsUsers) {
    this.liasonsUsers = liasonsUsers;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public void setPdCanSelect(boolean pdCanSelect) {
    this.pdCanSelect = pdCanSelect;
  }


  public void setPdSelected(boolean pdSelected) {
    this.pdSelected = pdSelected;
  }


  public void setProjectPartnerPersons(Set<ProjectPartnerPerson> projectPartnerPersons) {
    this.projectPartnerPersons = projectPartnerPersons;
  }

  public void setResearchLeaders(Set<CenterLeader> researchLeaders) {
    this.researchLeaders = researchLeaders;
  }

  public void setSubmissions(Set<Submission> submissions) {
    this.submissions = submissions;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  @Override
  public String toString() {
    return "User [id=" + this.getId() + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
      + ", email=" + email + ", password=" + password + ", cgiarUser=" + cgiarUser + ", Active=" + this.isActive()
      + ", lastLogin=" + lastLogin + "]";
  }
}

