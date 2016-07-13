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

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class InstitutionType implements java.io.Serializable {


  static final long serialVersionUID = -943657365260109270L;

  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String acronym;
  private Set<Institution> institutions = new HashSet<Institution>(0);

  public InstitutionType() {
  }

  public InstitutionType(String name) {
    this.name = name;
  }

  public InstitutionType(String name, String acronym, Set<Institution> institutions) {
    this.name = name;
    this.acronym = acronym;
    this.institutions = institutions;
  }

  public String getAcronym() {
    return this.acronym;
  }

  public Long getId() {
    return this.id;
  }

  public Set<Institution> getInstitutions() {
    return institutions;
  }

  public String getName() {
    return this.name;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setInstitutions(Set<Institution> institutions) {
    this.institutions = institutions;
  }

  public void setName(String name) {
    this.name = name;
  }

}
