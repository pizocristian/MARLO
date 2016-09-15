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


/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public enum ProjectSectionStatusEnum {

  DESCRIPTION("description"), PARTNERS("partners"), LOCATIONS("locations"), OUTCOMES("outcomes"),
  CCAFSOUTCOMES("ccafsOutcomes"), OTHERCONTRIBUTIONS("otherContributions"), OUTPUTS("outputs"),
  DELIVERABLESLIST("deliverablesList"), DELIVERABLES("deliverables"), ACTIVITIES("activities"),
  BUDGET("budgetByPartners"), BUDGETBYCOA("budgetByCoAs"), NEXTUSERS("nextUsers"), CASESTUDIES("caseStudies"),
  HIGHLIGHTS("highlights"), LEVERAGES("leverages");

  public static ProjectSectionStatusEnum value(String status) {
    ProjectSectionStatusEnum[] lst = ProjectSectionStatusEnum.values();
    for (ProjectSectionStatusEnum projectSectionStatusEnum : lst) {
      if (projectSectionStatusEnum.getStatus().equals(status)) {
        return projectSectionStatusEnum;
      }
    }
    return null;
  }

  private String status;

  private ProjectSectionStatusEnum(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

}
