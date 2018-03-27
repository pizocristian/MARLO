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


package org.cgiar.ccafs.marlo.action.bi;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.MD5Convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;


public class BiSaikuAnalytics extends BaseAction {

  private static final long serialVersionUID = 1L;

  private String urlSaiku;


  @Inject
  public BiSaikuAnalytics(APConfig config) {
    super(config);

  }

  @Override
  public String execute() throws Exception {

    Date today;
    String dateOut;
    SimpleDateFormat dateFormatter;
    dateFormatter = new SimpleDateFormat("dd-MM-yyyy", this.getLocale());

    today = new Date();

    dateOut = dateFormatter.format(today);

    String token = MD5Convert.stringToMD5(dateOut + "SomeExtraText" + "destination1");

    this.urlSaiku = this.getText("bi.serverurl") + token + "&dst=destination_1";

    return SUCCESS;

  }

  public String getUrlSaiku() {
    return urlSaiku;
  }


  @Override
  public void prepare() throws Exception {
    // In the future here will validate the roles and the security.
  }


  public void setUrlSaiku(String urlSaiku) {
    this.urlSaiku = urlSaiku;
  }


}
