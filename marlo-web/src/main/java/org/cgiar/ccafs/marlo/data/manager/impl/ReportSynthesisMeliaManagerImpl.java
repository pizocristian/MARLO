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
package org.cgiar.ccafs.marlo.data.manager.impl;


import org.cgiar.ccafs.marlo.data.dao.ReportSynthesisMeliaDAO;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisMeliaManager;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisMelia;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author CCAFS
 */
@Named
public class ReportSynthesisMeliaManagerImpl implements ReportSynthesisMeliaManager {


  private ReportSynthesisMeliaDAO reportSynthesisMeliaDAO;
  // Managers


  @Inject
  public ReportSynthesisMeliaManagerImpl(ReportSynthesisMeliaDAO reportSynthesisMeliaDAO) {
    this.reportSynthesisMeliaDAO = reportSynthesisMeliaDAO;


  }

  @Override
  public void deleteReportSynthesisMelia(long reportSynthesisMeliaId) {

    reportSynthesisMeliaDAO.deleteReportSynthesisMelia(reportSynthesisMeliaId);
  }

  @Override
  public boolean existReportSynthesisMelia(long reportSynthesisMeliaID) {

    return reportSynthesisMeliaDAO.existReportSynthesisMelia(reportSynthesisMeliaID);
  }

  @Override
  public List<ReportSynthesisMelia> findAll() {

    return reportSynthesisMeliaDAO.findAll();

  }

  @Override
  public ReportSynthesisMelia getReportSynthesisMeliaById(long reportSynthesisMeliaID) {

    return reportSynthesisMeliaDAO.find(reportSynthesisMeliaID);
  }

  @Override
  public ReportSynthesisMelia saveReportSynthesisMelia(ReportSynthesisMelia reportSynthesisMelia) {

    return reportSynthesisMeliaDAO.save(reportSynthesisMelia);
  }


}
