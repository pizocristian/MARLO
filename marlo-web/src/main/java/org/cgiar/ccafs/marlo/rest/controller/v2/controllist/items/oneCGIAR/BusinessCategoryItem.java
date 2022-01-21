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

/**************
 * @author Diego Perez - CIAT/CCAFS
 **************/

package org.cgiar.ccafs.marlo.rest.controller.v2.controllist.items.oneCGIAR;

import org.cgiar.ccafs.marlo.data.manager.OneCGIARBusinessCategoryManager;
import org.cgiar.ccafs.marlo.data.model.OneCGIARBusinessCategory;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.rest.dto.BusinessCategoryDTO;
import org.cgiar.ccafs.marlo.rest.errors.FieldErrorDTO;
import org.cgiar.ccafs.marlo.rest.errors.MARLOFieldValidationException;
import org.cgiar.ccafs.marlo.rest.mappers.BusinessCategoryMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Named
public class BusinessCategoryItem<T> {

  private static final Logger LOG = LoggerFactory.getLogger(BusinessCategoryItem.class);

  // Mappers
  private BusinessCategoryMapper businessCategoryMapper;

  // managers
  private OneCGIARBusinessCategoryManager oneCGIARBussinessCategoryManager;

  @Inject
  public BusinessCategoryItem(OneCGIARBusinessCategoryManager oneCGIARBussinessCategoryManager,
    BusinessCategoryMapper businessCategoryMapper) {
    super();
    this.oneCGIARBussinessCategoryManager = oneCGIARBussinessCategoryManager;
    this.businessCategoryMapper = businessCategoryMapper;
  }

  public ResponseEntity<List<BusinessCategoryDTO>> getAll() {
    List<OneCGIARBusinessCategory> oneCGIARBussinessCategories = this.oneCGIARBussinessCategoryManager.getAll();

    List<BusinessCategoryDTO> bussinessCategoryDTOs = CollectionUtils.emptyIfNull(oneCGIARBussinessCategories).stream()
      .map(this.businessCategoryMapper::oneCGIARBussinesCategoryToBussinessCategoryDTO).collect(Collectors.toList());

    return Optional.ofNullable(bussinessCategoryDTOs).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  public ResponseEntity<BusinessCategoryDTO> getBusinessCategoryById(Long id, User user) {
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
    OneCGIARBusinessCategory oneCGIARBussinessCategory = null;
    if (id == null || id < 1L) {
      fieldErrors.add(new FieldErrorDTO("OneCGIARBusinessCategory", "id", "Invalid ID for an Business Category"));
    }
    // User validation???

    if (fieldErrors.isEmpty()) {
      oneCGIARBussinessCategory = this.oneCGIARBussinessCategoryManager.getOneCGIARBussinessCategoryById(id);
      if (oneCGIARBussinessCategory == null) {
        fieldErrors.add(new FieldErrorDTO("OneCGIARBusinessCategory", "id",
          "The Bussiness Category with id " + id + " does not exist"));
      }
    }

    if (!fieldErrors.isEmpty()) {
      throw new MARLOFieldValidationException("Field Validation errors", "", fieldErrors);
    }

    return Optional.ofNullable(oneCGIARBussinessCategory)
      .map(this.businessCategoryMapper::oneCGIARBussinesCategoryToBussinessCategoryDTO)
      .map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
