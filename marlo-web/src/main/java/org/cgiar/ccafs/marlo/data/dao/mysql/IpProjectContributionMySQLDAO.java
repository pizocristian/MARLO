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


package org.cgiar.ccafs.marlo.data.dao.mysql;

import org.cgiar.ccafs.marlo.data.dao.IpProjectContributionDAO;
import org.cgiar.ccafs.marlo.data.model.IpProjectContribution;

import java.util.List;

import com.google.inject.Inject;

public class IpProjectContributionMySQLDAO implements IpProjectContributionDAO {

  private StandardDAO dao;

  @Inject
  public IpProjectContributionMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteIpProjectContribution(long ipProjectContributionId) {
    IpProjectContribution ipProjectContribution = this.find(ipProjectContributionId);
    ipProjectContribution.setActive(false);
    return this.save(ipProjectContribution) > 0;
  }

  @Override
  public boolean existIpProjectContribution(long ipProjectContributionID) {
    IpProjectContribution ipProjectContribution = this.find(ipProjectContributionID);
    if (ipProjectContribution == null) {
      return false;
    }
    return true;

  }

  @Override
  public IpProjectContribution find(long id) {
    return dao.find(IpProjectContribution.class, id);

  }

  @Override
  public List<IpProjectContribution> findAll() {
    String query = "from " + IpProjectContribution.class.getName() + " where is_active=1";
    List<IpProjectContribution> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(IpProjectContribution ipProjectContribution) {
    if (ipProjectContribution.getId() == null) {
      dao.save(ipProjectContribution);
    } else {
      dao.update(ipProjectContribution);
    }


    return ipProjectContribution.getId();
  }


}