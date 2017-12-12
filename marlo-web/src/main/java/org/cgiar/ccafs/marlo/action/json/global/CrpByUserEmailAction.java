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

package org.cgiar.ccafs.marlo.action.json.global;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.action.json.project.FlaghshipsByCrpAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.CrpManager;
import org.cgiar.ccafs.marlo.data.model.Crp;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christian Garcia - CIAT/CCAFS
 */
public class CrpByUserEmailAction extends BaseAction {

  /**
   * 
   */
  private static final long serialVersionUID = -976200901679526774L;
  private final Logger logger = LoggerFactory.getLogger(FlaghshipsByCrpAction.class);
  private List<Map<String, Object>> crps;

  private String userEmail;
  private CrpManager crpManager;

  @Inject
  public CrpByUserEmailAction(APConfig config, CrpManager crpManager) {
    super(config);
    this.crpManager = crpManager;
  }


  @Override
  public String execute() throws Exception {
    crps = new ArrayList<Map<String, Object>>();
    Map<String, Object> crpMap;
    List<Crp> crps = crpManager.crpUsers(userEmail);
    for (Crp crp : crps) {
      try {
        crpMap = new HashMap<String, Object>();
        crpMap.put("id", crp.getId());
        crpMap.put("name", crp.getName());
        crpMap.put("acronym", crp.getAcronym());


        this.crps.add(crpMap);
      } catch (Exception e) {
        logger.error("unable to add flagship to crps list", e);
        /**
         * Original code swallows the exception and didn't even log it. Now we at least log it,
         * but we need to revisit to see if we should continue processing or re-throw the exception.
         */
      }
    }
    return SUCCESS;

  }


  public List<Map<String, Object>> getCrps() {
    return crps;
  }

  @Override
  public void prepare() throws Exception {
    Map<String, Object> parameters = this.getParameters();
    userEmail = StringUtils.trim(((String[]) parameters.get(APConstants.USER_EMAIL))[0]);
  }


  public void setCrps(List<Map<String, Object>> flagships) {
    this.crps = flagships;
  }


}
