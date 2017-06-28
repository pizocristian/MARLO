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
package org.cgiar.ccafs.marlo.data.service.impl;


import org.cgiar.ccafs.marlo.data.dao.ICenterObjectiveDAO;
import org.cgiar.ccafs.marlo.data.model.CenterObjective;
import org.cgiar.ccafs.marlo.data.service.ICenterObjectiveService;

import java.util.List;

import com.google.inject.Inject;

/**
 * @author Christian Garcia
 */
public class CenterObjectiveService implements ICenterObjectiveService {


  private ICenterObjectiveDAO researchObjectiveDAO;

  // Managers


  @Inject
  public CenterObjectiveService(ICenterObjectiveDAO researchObjectiveDAO) {
    this.researchObjectiveDAO = researchObjectiveDAO;


  }

  @Override
  public boolean deleteResearchObjective(long researchObjectiveId) {

    return researchObjectiveDAO.deleteResearchObjective(researchObjectiveId);
  }

  @Override
  public boolean existResearchObjective(long researchObjectiveID) {

    return researchObjectiveDAO.existResearchObjective(researchObjectiveID);
  }

  @Override
  public List<CenterObjective> findAll() {

    return researchObjectiveDAO.findAll();

  }

  @Override
  public CenterObjective getResearchObjectiveById(long researchObjectiveID) {

    return researchObjectiveDAO.find(researchObjectiveID);
  }

  @Override
  public List<CenterObjective> getResearchObjectivesByUserId(Long userId) {
    return researchObjectiveDAO.getResearchObjectivesByUserId(userId);
  }

  @Override
  public long saveResearchObjective(CenterObjective researchObjective) {

    return researchObjectiveDAO.save(researchObjective);
  }


}
