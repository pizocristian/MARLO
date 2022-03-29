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

package org.cgiar.ccafs.marlo.rest.controller.v2.controllist.items.submissiontools;

import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.rest.dto.InitiativesDTO;
import org.cgiar.ccafs.marlo.rest.errors.FieldErrorDTO;
import org.cgiar.ccafs.marlo.rest.errors.MARLOFieldValidationException;
import org.cgiar.ccafs.marlo.rest.mappers.InitiativeMapper;
import org.cgiar.ccafs.marlo.rest.services.submissionTools.InitiativesList;
import org.cgiar.ccafs.marlo.rest.services.submissionTools.Response;
import org.cgiar.ccafs.marlo.utils.APConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Configuration
@PropertySource("classpath:global.properties")
@Named
public class InitiativesItem<T> {

  public GlobalUnitManager globalUnitManager;
  public InitiativeMapper initiativeMapper;

  protected APConfig config;

  @Inject
  public InitiativesItem(InitiativeMapper initiativeMapper, APConfig config, GlobalUnitManager globalUnitManager) {
    super();
    this.initiativeMapper = initiativeMapper;
    this.config = config;
    this.globalUnitManager = globalUnitManager;
  }

  public ResponseEntity<List<InitiativesDTO>> getInitiatives() {
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
    List<InitiativesDTO> initiativeListDTO = new ArrayList<InitiativesDTO>();
    Response response = null;
    String url = config.getUrlSubmissionTools();

    if (url != null) {
      try {
        // getting token
        JsonElement json = this.getSubmissionElement(url + "initiatives");

        response = new Gson().fromJson(json, Response.class);
        if (response.getResponse() != null) {
          InitiativesList initiativesList = response.getResponse();
          if (initiativesList.getInitiatives() != null) {
            initiativeListDTO = initiativesList.getInitiatives().stream()
              .map(init -> this.initiativeMapper.initiativeToInitiativesDTO(init)).collect(Collectors.toList());
          }

        }
      } catch (Exception e) {
        e.printStackTrace();
        fieldErrors.add(new FieldErrorDTO("getInitiatives", "JSON element",
          "Error trying to get data from service  " + e.getMessage()));
      }

    }

    if (!fieldErrors.isEmpty()) {
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    return Optional.ofNullable(initiativeListDTO).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  public JsonElement getSubmissionElement(String url) throws MalformedURLException, IOException {
    URL submissionToolsUrl = new URL(url);
    String loginData = config.getSubmissionToolsUser() + ":" + config.getSubmissionToolsPassword();
    String encoded = Base64.encodeBase64String(loginData.getBytes());
    HttpURLConnection conn = (HttpURLConnection) submissionToolsUrl.openConnection();
    conn.setRequestProperty("Authorization", "Basic " + encoded);


    JsonElement element = null;
    try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
      element = new JsonParser().parse(reader);
    } catch (FileNotFoundException fnfe) {
      element = JsonNull.INSTANCE;
    }
    return element;
  }

  public ResponseEntity<List<InitiativesDTO>> updateNameAllInitiatives() {
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();
    List<InitiativesDTO> initiativeListDTO = new ArrayList<InitiativesDTO>();
    Map<String, List<InitiativesDTO>> initiativeMap = new TreeMap<>();
    Response response = null;
    String url = config.getUrlSubmissionTools();
    if (url != null) {
      try {
        // getting token
        JsonElement json = this.getSubmissionElement(url + "initiatives/all-status");

        response = new Gson().fromJson(json, Response.class);
        if (response.getResponse() != null) {
          InitiativesList initiativesList = response.getResponse();
          if (initiativesList.getInitiatives() != null) {
            initiativeMap = initiativesList.getInitiatives().stream()
              .map(init -> this.initiativeMapper.initiativeToInitiativesDTO(init))
              .collect(Collectors.groupingBy(InitiativesDTO::getOfficial_code));

            initiativeMap.values().forEach(l -> Collections.sort(l,
              Comparator.comparing(InitiativesDTO::getStageId).thenComparing(InitiativesDTO::getActive).reversed()));
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        fieldErrors.add(new FieldErrorDTO("updateNameAllInitiatives", "JSON element",
          "Error trying to get data from service " + e.getMessage()));
      }
    }

    if (!fieldErrors.isEmpty()) {
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    for (Entry<String, List<InitiativesDTO>> initiativeEntry : initiativeMap.entrySet()) {
      // In theory this is ok, as if an entry exists in the map, is because at least one initiative was found
      InitiativesDTO initiativeDTO = initiativeEntry.getValue().get(0);
      if (initiativeDTO != null && StringUtils.isNotBlank(initiativeDTO.getOfficial_code())
        && StringUtils.isNotBlank(initiativeDTO.getName())) {
        GlobalUnit initiative =
          this.globalUnitManager.findGlobalUnitByAcronym(StringUtils.trimToEmpty(initiativeDTO.getOfficial_code()));
        if (initiative != null && initiative.getId() != null) {
          initiative.setName(StringUtils.trimToEmpty(initiativeDTO.getName()));
          initiative.setActive(initiativeDTO.getActive() != 0);
          initiative = this.globalUnitManager.saveGlobalUnit(initiative);
        }

        initiativeListDTO.add(initiativeDTO);
      }
    }

    initiativeListDTO.sort(Comparator.comparing(InitiativesDTO::getOfficial_code));

    return Optional.ofNullable(initiativeListDTO).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
