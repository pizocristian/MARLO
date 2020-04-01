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

package org.cgiar.ccafs.marlo.rest.controller.v2.controllist;

import org.cgiar.ccafs.marlo.data.manager.UserManager;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.rest.controller.v2.controllist.items.QAToken.QATokenItem;
import org.cgiar.ccafs.marlo.rest.dto.QATokenAuthDTO;
import org.cgiar.ccafs.marlo.rest.errors.NotFoundException;
import org.cgiar.ccafs.marlo.security.Permission;

import javax.inject.Inject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luis Benavides - CIAT/CCAFS
 */
// @ApiIgnore
@RestController
@Api(tags = "QA Token")
public class QAToken {

  private static final Logger LOG = LoggerFactory.getLogger(QAToken.class);
  private QATokenItem<QAToken> qATokenItem;
  private final UserManager userManager;


  @Inject
  public QAToken(QATokenItem<QAToken> qATokenItem, UserManager userManager) {
    this.qATokenItem = qATokenItem;
    this.userManager = userManager;
  }

  private User getCurrentUser() {
    Subject subject = SecurityUtils.getSubject();
    Long principal = (Long) subject.getPrincipal();
    User user = this.userManager.getUser(principal);
    return user;
  }

  @RequiresPermissions(Permission.FULL_READ_REST_API_PERMISSION)
  @RequestMapping(value = "/qatoken/{name}/{username}/{email}/{smoCode}", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<QATokenAuthDTO> getToken(@ApiParam(value = "Name", required = true) @PathVariable String name,
    @ApiParam(value = "User name", required = true) @PathVariable String username,
    @ApiParam(value = "Email", required = true) @PathVariable String email,
    @ApiParam(value = "SMO code", required = true) @PathVariable String smoCode) {

    ResponseEntity<QATokenAuthDTO> response =
      qATokenItem.getToken(name, username, email, smoCode, this.getCurrentUser());

    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      throw new NotFoundException("404", "Not Found");
    }
    return response;
  }

}
