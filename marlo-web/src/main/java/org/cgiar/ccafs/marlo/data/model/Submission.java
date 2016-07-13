/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning & 
 * Outcomes Platform (MARLO). * MARLO is free software: you can redistribute it and/or modify
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
// Generated Jul 7, 2016 11:23:47 AM by Hibernate Tools 4.3.1.Final


import java.util.Date;

/**
 * Submission generated by hbm2java
 */
public class Submission implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 235989260663664812L;
  private Long id;
  private CrpProgram crpProgram;
  private User user;
  private Date dateTime;
  private String modificationJustification;
  private String cycle;
  private Short year;

  public Submission() {
  }


  public Submission(CrpProgram crpProgram, User user, Date dateTime) {
    this.crpProgram = crpProgram;
    this.user = user;
    this.dateTime = dateTime;
  }

  public Submission(CrpProgram crpProgram, User user, Date dateTime, String modificationJustification, String cycle,
    Short year) {
    this.crpProgram = crpProgram;
    this.user = user;
    this.dateTime = dateTime;
    this.modificationJustification = modificationJustification;
    this.cycle = cycle;
    this.year = year;
  }

  public CrpProgram getCrpProgram() {
    return this.crpProgram;
  }

  public String getCycle() {
    return this.cycle;
  }

  public Date getDateTime() {
    return this.dateTime;
  }

  public Long getId() {
    return this.id;
  }

  public String getModificationJustification() {
    return this.modificationJustification;
  }

  public User getUser() {
    return this.user;
  }

  public Short getYear() {
    return this.year;
  }

  public void setCrpProgram(CrpProgram crpProgram) {
    this.crpProgram = crpProgram;
  }

  public void setCycle(String cycle) {
    this.cycle = cycle;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModificationJustification(String modificationJustification) {
    this.modificationJustification = modificationJustification;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setYear(Short year) {
    this.year = year;
  }


}

