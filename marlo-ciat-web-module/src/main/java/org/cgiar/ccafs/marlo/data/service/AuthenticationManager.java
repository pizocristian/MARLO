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

package org.cgiar.ccafs.marlo.data.service;

import org.cgiar.ccafs.marlo.data.service.impl.AuthenticationManagerImp;

import com.google.inject.ImplementedBy;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
@ImplementedBy(AuthenticationManagerImp.class)
public interface AuthenticationManager {

  /**
   * This method verifies if the credentials received as parameter
   * corresponds with the credentials of some user in the database.
   * 
   * @param email
   * @param password - password encrypted with MD5.
   * @return true if the credentials are valid. False otherwise.
   */
  public boolean veirifyCredentials(String email, String password);
}
