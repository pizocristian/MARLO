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

package org.cgiar.ccafs.marlo.rest.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NewKeyExternalPartnershipDTO {

  @ApiModelProperty(notes = "Partnership / Main area", position = 3)
  private List<String> partnershipMainAreaIds;
  // private String partnershipMainAreaId;

  @ApiModelProperty(notes = "Description of partnership aim", position = 2)
  private String description;

  @ApiModelProperty(notes = "Flagship / Module", position = 1)
  private String flagshipProgramId;
  // private String flagshipProgramId;

  @ApiModelProperty(notes = "Phase (AR, POWB) - Year", position = 4)
  private PhaseDTO phase;

  @ApiModelProperty(notes = "Institutions linked to the partnership", position = 4)
  private List<String> institutionsIds;

  // report synthesis key partnership missing!

  public String getDescription() {
    return description;
  }


  public String getFlagshipProgramId() {
    return flagshipProgramId;
  }


  public List<String> getInstitutionsIds() {
    return institutionsIds;
  }


  public List<String> getPartnershipMainAreaIds() {
    return partnershipMainAreaIds;
  }


  public PhaseDTO getPhase() {
    return phase;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public void setFlagshipProgramId(String flagshipProgramId) {
    this.flagshipProgramId = flagshipProgramId;
  }


  public void setInstitutionsIds(List<String> institutionsIds) {
    this.institutionsIds = institutionsIds;
  }


  public void setPartnershipMainAreaIds(List<String> partnershipMainAreaIds) {
    this.partnershipMainAreaIds = partnershipMainAreaIds;
  }


  public void setPhase(PhaseDTO phase) {
    this.phase = phase;
  }

}
