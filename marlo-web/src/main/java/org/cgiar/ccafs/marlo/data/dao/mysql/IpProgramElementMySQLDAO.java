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

import org.cgiar.ccafs.marlo.data.dao.IpProgramElementDAO;
import org.cgiar.ccafs.marlo.data.model.IpProgramElement;

import java.util.List;

import com.google.inject.Inject;

public class IpProgramElementMySQLDAO implements IpProgramElementDAO {

  private StandardDAO dao;

  @Inject
  public IpProgramElementMySQLDAO(StandardDAO dao) {
    this.dao = dao;
  }

  @Override
  public boolean deleteIpProgramElement(long ipProgramElementId) {
    IpProgramElement ipProgramElement = this.find(ipProgramElementId);
    return dao.delete(ipProgramElement);
  }

  @Override
  public boolean existIpProgramElement(long ipProgramElementID) {
    IpProgramElement ipProgramElement = this.find(ipProgramElementID);
    if (ipProgramElement == null) {
      return false;
    }
    return true;

  }

  @Override
  public IpProgramElement find(long id) {
    return dao.find(IpProgramElement.class, id);

  }

  @Override
  public List<IpProgramElement> findAll() {
    String query = "from " + IpProgramElement.class.getName() + " where is_active=1";
    List<IpProgramElement> list = dao.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }

  @Override
  public long save(IpProgramElement ipProgramElement) {
    if (ipProgramElement.getId() == null) {
      dao.save(ipProgramElement);
    } else {
      dao.update(ipProgramElement);
    }


    return ipProgramElement.getId();
  }


}