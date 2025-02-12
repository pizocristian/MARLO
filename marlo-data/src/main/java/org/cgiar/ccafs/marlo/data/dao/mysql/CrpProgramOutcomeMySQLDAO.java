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

import org.cgiar.ccafs.marlo.data.dao.CrpProgramOutcomeDAO;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcome;
import org.cgiar.ccafs.marlo.data.model.Phase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

@Named
public class CrpProgramOutcomeMySQLDAO extends AbstractMarloDAO<CrpProgramOutcome, Long>
  implements CrpProgramOutcomeDAO {


  @Inject
  public CrpProgramOutcomeMySQLDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public void deleteCrpProgramOutcome(long crpProgramOutcomeId) {
    CrpProgramOutcome crpProgramOutcome = this.find(crpProgramOutcomeId);
    crpProgramOutcome.setActive(false);
    this.save(crpProgramOutcome);
  }

  @Override
  public boolean existCrpProgramOutcome(long crpProgramOutcomeID) {
    CrpProgramOutcome crpProgramOutcome = this.find(crpProgramOutcomeID);
    if (crpProgramOutcome == null) {
      return false;
    }
    return true;

  }

  @Override
  public CrpProgramOutcome find(long id) {
    return super.find(CrpProgramOutcome.class, id);

  }


  @Override
  public List<CrpProgramOutcome> findAll() {
    String query = "from " + CrpProgramOutcome.class.getName() + " where is_active=1";
    List<CrpProgramOutcome> list = super.findAll(query);
    if (list.size() > 0) {
      return list;
    }
    return null;

  }


  @Override
  public List<CrpProgramOutcome> getAllCrpProgramOutcomesByComposedIdFromPhase(String composedId, long phaseId) {
    String query = "select distinct cpo from CrpProgramOutcome cpo " + "where composeID=:composeID and active=true "
      + "and phase.id >= :phaseId";
    Query createQuery = this.getSessionFactory().getCurrentSession().createQuery(query);
    createQuery.setParameter("composeID", composedId);
    createQuery.setParameter("phaseId", phaseId);
    List<CrpProgramOutcome> resultList = super.findAll(createQuery);
    return resultList;
  }

  @Override
  public List<CrpProgramOutcome> getAllCrpProgramOutcomesByPhase(long phaseId) {
    String query =
      "select distinct cpo from CrpProgramOutcome cpo " + "where active=true " + "and phase.id >= :phaseId";
    Query createQuery = this.getSessionFactory().getCurrentSession().createQuery(query);
    createQuery.setParameter("phaseId", phaseId);
    List<CrpProgramOutcome> resultList = super.findAll(createQuery);
    return resultList;
  }

  @Override
  public CrpProgramOutcome getCrpProgramOutcome(String composedId, Phase phase) {

    String query = "select distinct pp from CrpProgramOutcome  pp "
      + "where composeID=:composeID and phase.id=:phaseID and active=true";
    Query createQuery = this.getSessionFactory().getCurrentSession().createQuery(query);
    createQuery.setParameter("composeID", composedId);
    createQuery.setParameter("phaseID", phase.getId());
    Object findSingleResult = super.findSingleResult(CrpProgramOutcome.class, createQuery);
    CrpProgramOutcome crpProgramOutcome = (CrpProgramOutcome) findSingleResult;

    return crpProgramOutcome;
  }

  @Override
  public CrpProgramOutcome save(CrpProgramOutcome crpProgramOutcome) {
    if (crpProgramOutcome.getId() == null) {
      super.saveEntity(crpProgramOutcome);
    } else {
      crpProgramOutcome = super.update(crpProgramOutcome);
    }


    return crpProgramOutcome;
  }

}