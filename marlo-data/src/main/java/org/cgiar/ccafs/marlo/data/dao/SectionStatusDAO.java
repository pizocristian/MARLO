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


package org.cgiar.ccafs.marlo.data.dao;

import org.cgiar.ccafs.marlo.data.model.SectionStatus;

import java.util.List;


public interface SectionStatusDAO {

  /**
   * This method removes a specific sectionStatus value from the database.
   * 
   * @param sectionStatusId is the sectionStatus identifier.
   * @return true if the sectionStatus was successfully deleted, false otherwise.
   */
  public void deleteSectionStatus(long sectionStatusId);

  /**
   * This method validate if the sectionStatus identify with the given id exists in the system.
   * 
   * @param sectionStatusID is a sectionStatus identifier.
   * @return true if the sectionStatus exists, false otherwise.
   */
  public boolean existSectionStatus(long sectionStatusID);

  /**
   * This method gets a sectionStatus object by a given sectionStatus identifier.
   * 
   * @param sectionStatusID is the sectionStatus identifier.
   * @return a SectionStatus object.
   */
  public SectionStatus find(long id);

  /**
   * This method gets a list of sectionStatus that are active
   * 
   * @return a list from SectionStatus null if no exist records
   */
  public List<SectionStatus> findAll();

  public List<SectionStatus> getSectionsStatusByReportSynthesis(long synthesisID, String cycle, int year,
    Boolean upkeep, String sectionName);

  public SectionStatus getSectionStatusByCaseStudy(long caseStudyID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByCrpIndicators(long ipLiaisonInstitutionID, String cycle, int year,
    Boolean upkeep, String sectionName);

  public SectionStatus getSectionStatusByCrpProgam(long crpProgramID, String sectionName, String cylce, int year,
    Boolean upkeep);

  public SectionStatus getSectionStatusByDeliverable(long deliverableID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByFundingSource(long fundingSource, String cycle, Integer year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByIpProgram(long ipProgramID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByPowbSynthesis(long powbSynthesisID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByProject(long projectID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByProjectCofunded(long projectID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByProjectContributionToLP6(long projectLp6ContributionID, String cycle, int year,
    Boolean upkeep, String sectionName);

  public SectionStatus getSectionStatusByProjectExpectedStudy(long expectedID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByProjectHighlight(long projectHighlightID, String cycle, int year,
    Boolean upkeep, String sectionName);

  public SectionStatus getSectionStatusByProjectImpacts(Long projectImpactID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByProjectInnovation(long projectInnovationID, String cycle, int year,
    Boolean upkeep, String sectionName);

  public SectionStatus getSectionStatusByProjectOutcome(long projectID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByProjectPolicy(long projectPolicyID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusByReportSynthesis(long synthesisID, String cycle, int year, Boolean upkeep,
    String sectionName);

  public SectionStatus getSectionStatusBySynteshisMog(long ipProgramID, String cycle, int year, Boolean upkeep,
    String sectionName);

  /**
   * This method saves the information of the given sectionStatus
   * 
   * @param sectionStatus - is the sectionStatus object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the sectionStatus was
   *         updated
   *         or -1 is some error occurred.
   */
  public SectionStatus save(SectionStatus sectionStatus);

}
