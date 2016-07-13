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

package org.cgiar.ccafs.marlo.security.authentication;

import org.cgiar.ccafs.marlo.utils.APConfig;

import org.cgiar.ciat.auth.ADConexion;
import org.cgiar.ciat.auth.LDAPService;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * validate if the input user belongs in CGIAR active directory
 * 
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class LDAPAuthenticator implements Authenticator {

  public static Logger LOG = LoggerFactory.getLogger(LDAPAuthenticator.class);


  private APConfig config;

  @Inject
  public LDAPAuthenticator(APConfig config) {

    this.config = config;
  }

  @Override
  public boolean authenticate(String email, String password) {
    boolean logued = false;

    try {
      ADConexion con = null;
      LDAPService service = new LDAPService();
      if (config.isProduction()) {
        service.setInternalConnection(false);
      } else {
        service.setInternalConnection(true);
      }
      con = service.authenticateUser(email, password);

      if (con != null) {
        if (con.getLogin() != null) {
          logued = true;
        } else {
          System.out.println(con.getAuthenticationMessage());
          LOG.error("Authentication error  {}", con.getAuthenticationMessage());
        }
        con.closeContext();
      }
    } catch (Exception e) {
      LOG.error("Exception raised trying to log in the user '{}' against the active directory. ", email,
        e.getMessage());
    }
    return logued;
  }

}
