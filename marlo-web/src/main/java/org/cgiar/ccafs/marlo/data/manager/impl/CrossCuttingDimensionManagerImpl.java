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

import org.cgiar.ccafs.marlo.data.dao.CrossCuttingDimensionsDAO;
import org.cgiar.ccafs.marlo.data.manager.CrossCuttingDimensionManager;
import org.cgiar.ccafs.marlo.data.model.CrossCuttingDimensions;
import org.cgiar.ccafs.marlo.data.model.dto.CrossCuttingDimensionTableDTO;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CrossCuttingDimensionManagerImpl implements CrossCuttingDimensionManager {

  private CrossCuttingDimensionsDAO crossCuttingDAO;

  @Inject
  public CrossCuttingDimensionManagerImpl(CrossCuttingDimensionsDAO crossCuttingDAO) {
    this.crossCuttingDAO = crossCuttingDAO;
  }

  @Override
  public CrossCuttingDimensions loadCrossCuttingDimensionByPMU(Long liaisonInstitution) {
    return null;
  }

  @Override
  public CrossCuttingDimensionTableDTO loadTableByLiaisonAndPhase(Long liaisonInstitution, Long phaseId) {

    CrossCuttingDimensionTableDTO tableC = crossCuttingDAO.getTableC(liaisonInstitution, phaseId);

    // get the values of participation of every dimension

    if (tableC != null) {
      int total = tableC.getGenderPrincipal() + tableC.getGenderSignificant() + tableC.getGenderScored();
      total += tableC.getYouthPrincipal() + tableC.getYouthSignificant() + tableC.getYouthScored();
      total += tableC.getCapDevPrincipal() + tableC.getCapDevSignificant() + tableC.getCapDevScored();

      tableC.setTotal(new Double(total));

      // Gender Principal
      if (total == 0) {
        tableC.setPercentageGenderPrincipal(new Double(0));
      } else {
        double percentage = (tableC.getGenderPrincipal() * 100) / total;
        tableC.setPercentageGenderPrincipal(new Double(percentage));
      }

      // Gender Significant
      if (total == 0) {
        tableC.setPercentageGenderSignificant(new Double(0));
      } else {
        double percentage = (tableC.getGenderSignificant() * 100) / total;
        tableC.setPercentageGenderSignificant(new Double(percentage));
      }

      // Gender Scored
      if (total == 0) {
        tableC.setPercentageGenderNotScored(new Double(0));
      } else {
        double percentage = (tableC.getGenderScored() * 100) / total;
        tableC.setPercentageGenderNotScored(new Double(percentage));
      }

      // Youth Principal
      if (total == 0) {
        tableC.setPercentageYouthPrincipal(new Double(0));
      } else {
        double percentage = (tableC.getYouthPrincipal() * 100) / total;
        tableC.setPercentageYouthPrincipal(new Double(percentage));
      }

      // Youth Significant
      if (total == 0) {
        tableC.setPercentageYouthSignificant(new Double(0));
      } else {
        double percentage = (tableC.getYouthSignificant() * 100) / total;
        tableC.setPercentageYouthSignificant(new Double(percentage));
      }

      // Youth Scored
      if (total == 0) {
        tableC.setPercentageYouthNotScored(new Double(0));
      } else {
        double percentage = (tableC.getYouthScored() * 100) / total;
        tableC.setPercentageYouthNotScored(new Double(percentage));
      }


      // CapDev Principal
      if (total == 0) {
        tableC.setPercentageCapDevPrincipal(new Double(0));
      } else {
        double percentage = (tableC.getCapDevPrincipal() * 100) / total;
        tableC.setPercentageCapDevPrincipal(new Double(percentage));
      }

      // CapDev Significant
      if (total == 0) {
        tableC.setPercentageCapDevSignificant(new Double(0));
      } else {
        double percentage = (tableC.getCapDevSignificant() * 100) / total;
        tableC.setPercentageCapDevSignificant(new Double(percentage));
      }

      // CapDev Scored
      if (total == 0) {
        tableC.setPercentageCapDevNotScored(new Double(0));
      } else {
        double percentage = (tableC.getCapDevScored() * 100) / total;
        tableC.setPercentageCapDevNotScored(new Double(percentage));
      }


    }


    return tableC;
  }

  @Override
  public CrossCuttingDimensions saveCrossCuttingDimensions(CrossCuttingDimensions crossCuttingDimensions) {

    return crossCuttingDAO.save(crossCuttingDimensions);

  }

}
