/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning & 
 * Outcomes Platform (MARLO). * MARLO is free software: you can redistribute it and/or modify
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

package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.manager.impl.CrpManagerImp;
import org.cgiar.ccafs.marlo.data.model.Crp;

import java.util.List;

import com.google.inject.ImplementedBy;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
@ImplementedBy(CrpManagerImp.class)
public interface CrpManager {


  /**
   * This method removes a specific crp value from the database.
   * 
   * @param crpId is the crp identifier.
   * @return true if the crp was successfully deleted, false otherwise.
   */
  public boolean deleteCrp(long crpId);


  /**
   * This method validate if the crp identify with the given id exists in the system.
   * 
   * @param crpID is a crp identifier.
   * @return true if the crp exists, false otherwise.
   */
  public boolean existCrp(long crpID);


  /**
   * This method gets a list of crp that are active
   * 
   * @return a list from Crp null if no exist records
   */
  public List<Crp> findAll();

  /**
   * This method get a crp by it acronym
   * 
   * @param acronym
   * @return a Crp object or null if the input acronym is invalid.
   */
  public Crp findCrpByAcronym(String acronym);

  /**
   * This method gets a crp object by a given crp identifier.
   * 
   * @param crpID is the crp identifier.
   * @return a Crp object.
   */
  public Crp getCrpById(long crpID);

  /**
   * This method saves the information of the given crp
   * 
   * @param crp - is the crp object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the crp was
   *         updated
   *         or -1 is some error occurred.
   */
  public long saveCrp(Crp crp);

}
