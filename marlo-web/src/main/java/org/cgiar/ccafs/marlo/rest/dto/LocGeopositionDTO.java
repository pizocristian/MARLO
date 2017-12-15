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

public class LocGeopositionDTO {


  private Long id;


  private Double latitude;


  private Double longitude;


  public Long getId() {
    return id;
  }


  public Double getLatitude() {
    return latitude;
  }


  public Double getLongitude() {
    return longitude;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }


  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }
}
