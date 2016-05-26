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
// Generated May 17, 2016 9:53:46 AM by Hibernate Tools 4.3.1.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class User implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 3674438945983473335L;
  private Long id;
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String password;
  private boolean cgiarUser;
  private Long createdBy;
  private boolean active;
  private Date lastLogin;
  private Set<UserRole> userRoles = new HashSet<UserRole>(0);
  private Set<CrpUser> crpUsers = new HashSet<CrpUser>(0);

  public User() {
  }


  public User(String email, String password, boolean cgiarUser, boolean active) {
    this.email = email;
    this.password = password;
    this.cgiarUser = cgiarUser;
    this.active = active;
  }

  public User(String firstName, String lastName, String username, String email, String password, boolean cgiarUser,
    Long createdBy, boolean active, Date lastLogin, Set<UserRole> userRoles, Set<CrpUser> crpUsers) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.password = password;
    this.cgiarUser = cgiarUser;
    this.active = active;
    this.createdBy = createdBy;

    this.lastLogin = lastLogin;
    this.userRoles = userRoles;
    this.crpUsers = crpUsers;
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
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }


  public Long getCreatedBy() {
    return this.createdBy;
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

  public Long getId() {
    return this.id;
  }

  public Date getLastLogin() {
    return this.lastLogin;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getPassword() {
    return this.password;
  }

  public String getUsername() {
    return this.username;
  }

  public Set<UserRole> getUserRoles() {
    return this.userRoles;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isCgiarUser() {
    return cgiarUser;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setCgiarUser(boolean cgiarUser) {
    this.cgiarUser = cgiarUser;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
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


  public void setId(Long id) {
    this.id = id;
  }


  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }


}

