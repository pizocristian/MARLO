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


import org.cgiar.ccafs.marlo.data.dao.RepIndGeographicScopeDAO;
import org.cgiar.ccafs.marlo.data.manager.RepIndGeographicScopeManager;
import org.cgiar.ccafs.marlo.data.model.RepIndGeographicScope;

import java.util.List;

import javax.inject.Named;
import javax.inject.Inject;

/**
 * @author Christian Garcia
 */
@Named
public class RepIndGeographicScopeManagerImpl implements RepIndGeographicScopeManager {


  private RepIndGeographicScopeDAO repIndGeographicScopeDAO;
  // Managers


  @Inject
  public RepIndGeographicScopeManagerImpl(RepIndGeographicScopeDAO repIndGeographicScopeDAO) {
    this.repIndGeographicScopeDAO = repIndGeographicScopeDAO;


  }

  @Override
  public void deleteRepIndGeographicScope(long repIndGeographicScopeId) {

    repIndGeographicScopeDAO.deleteRepIndGeographicScope(repIndGeographicScopeId);
  }

  @Override
  public boolean existRepIndGeographicScope(long repIndGeographicScopeID) {

    return repIndGeographicScopeDAO.existRepIndGeographicScope(repIndGeographicScopeID);
  }

  @Override
  public List<RepIndGeographicScope> findAll() {

    return repIndGeographicScopeDAO.findAll();

  }

  @Override
  public RepIndGeographicScope getRepIndGeographicScopeById(long repIndGeographicScopeID) {

    return repIndGeographicScopeDAO.find(repIndGeographicScopeID);
  }

  @Override
  public RepIndGeographicScope saveRepIndGeographicScope(RepIndGeographicScope repIndGeographicScope) {

    return repIndGeographicScopeDAO.save(repIndGeographicScope);
  }


}
