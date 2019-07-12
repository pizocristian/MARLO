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
package org.cgiar.ccafs.marlo.data.manager;

import org.cgiar.ccafs.marlo.data.model.GeneralStatusTable;

import java.util.List;


/**
 * @author CCAFS
 */

public interface GeneralStatusTableManager {


  /**
   * This method removes a specific generalStatusTable value from the database.
   * 
   * @param generalStatusTableId is the generalStatusTable identifier.
   * @return true if the generalStatusTable was successfully deleted, false otherwise.
   */
  public void deleteGeneralStatusTable(long generalStatusTableId);


  /**
   * This method validate if the generalStatusTable identify with the given id exists in the system.
   * 
   * @param generalStatusTableID is a generalStatusTable identifier.
   * @return true if the generalStatusTable exists, false otherwise.
   */
  public boolean existGeneralStatusTable(long generalStatusTableID);


  /**
   * This method gets a list of generalStatusTable that are active
   * 
   * @return a list from GeneralStatusTable null if no exist records
   */
  public List<GeneralStatusTable> findAll();


  /**
   * This method gets a generalStatusTable object by a given generalStatusTable identifier.
   * 
   * @param generalStatusTableID is the generalStatusTable identifier.
   * @return a GeneralStatusTable object.
   */
  public GeneralStatusTable getGeneralStatusTableById(long generalStatusTableID);

  /**
   * This method saves the information of the given generalStatusTable
   * 
   * @param generalStatusTable - is the generalStatusTable object with the new information to be added/updated.
   * @return a number greater than 0 representing the new ID assigned by the database, 0 if the generalStatusTable was
   *         updated
   *         or -1 is some error occurred.
   */
  public GeneralStatusTable saveGeneralStatusTable(GeneralStatusTable generalStatusTable);

}
