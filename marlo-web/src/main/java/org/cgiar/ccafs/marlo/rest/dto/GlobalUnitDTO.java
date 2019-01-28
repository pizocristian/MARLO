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

package org.cgiar.ccafs.marlo.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class GlobalUnitDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Code of CGIAR entity")
	private String code;

	@ApiModelProperty(notes = "CGIAR entity name")
	@NotNull
	private String name;

	@ApiModelProperty(notes = "Acronym of CGIAR entity")
	@NotNull
	private String acronym;

	@ApiModelProperty(notes = "CGIAR entity type")
	@NotNull
	private GlobalUnitTypeDTO globalUnitTypeDTO;

	public String getAcronym() {
		return this.acronym;
	}

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GlobalUnitTypeDTO getGlobalUnitTypeDTO() {
		return globalUnitTypeDTO;
	}

	public void setGlobalUnitTypeDTO(GlobalUnitTypeDTO globalUnitTypeDTO) {
		this.globalUnitTypeDTO = globalUnitTypeDTO;
	}

}