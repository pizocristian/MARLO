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

package org.cgiar.ccafs.marlo.data.manager.impl;

import org.cgiar.ccafs.marlo.data.dao.OneCGIARTechnologyDeploymentStageDAO;
import org.cgiar.ccafs.marlo.data.manager.OneCGIARTechnologyDevelopmentStageManager;
import org.cgiar.ccafs.marlo.data.model.OneCGIARTechnologyDevelopmentStage;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class OneCGIARTechnologyDevelopmentStageManagerImpl implements OneCGIARTechnologyDevelopmentStageManager {

  private OneCGIARTechnologyDeploymentStageDAO oneCGIARTechnologyDeploymentStageDAO;

  @Inject
  public OneCGIARTechnologyDevelopmentStageManagerImpl(
    OneCGIARTechnologyDeploymentStageDAO oneCGIARTechnologyDeploymentStageDAO) {
    super();
    this.oneCGIARTechnologyDeploymentStageDAO = oneCGIARTechnologyDeploymentStageDAO;
  }

  @Override
  public List<OneCGIARTechnologyDevelopmentStage> getAll() {
    return oneCGIARTechnologyDeploymentStageDAO.getAll();
  }

  @Override
  public OneCGIARTechnologyDevelopmentStage getOneCGIARTechnologyDeploymentStageById(long id) {
    return oneCGIARTechnologyDeploymentStageDAO.getOneCGIARTechnologyDeploymentStageById(id);
  }

}
