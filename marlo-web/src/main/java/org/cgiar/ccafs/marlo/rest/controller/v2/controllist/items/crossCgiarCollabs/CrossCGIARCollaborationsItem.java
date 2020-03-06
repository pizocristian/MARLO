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

package org.cgiar.ccafs.marlo.rest.controller.v2.controllist.items.crossCgiarCollabs;

import org.cgiar.ccafs.marlo.data.manager.CrpProgramManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.LiaisonInstitutionManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisKeyPartnershipCollaborationCrpManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisKeyPartnershipCollaborationManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisKeyPartnershipManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisManager;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.CrpUser;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.LiaisonInstitution;
import org.cgiar.ccafs.marlo.data.model.MarloBaseEntity;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesis;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisKeyPartnership;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisKeyPartnershipCollaboration;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisKeyPartnershipCollaborationCrp;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.rest.dto.CrossCGIARCollaborationDTO;
import org.cgiar.ccafs.marlo.rest.dto.NewCrossCGIARCollaborationDTO;
import org.cgiar.ccafs.marlo.rest.errors.FieldErrorDTO;
import org.cgiar.ccafs.marlo.rest.errors.MARLOFieldValidationException;
import org.cgiar.ccafs.marlo.rest.mappers.KeyPartnershipCollaborationMapper;
import org.cgiar.ccafs.marlo.utils.ChangeTracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**************
 * @author German C. Martinez - CIAT/CCAFS
 **************/

@Named
public class CrossCGIARCollaborationsItem<T> {

  private static final Logger LOG = LoggerFactory.getLogger(CrossCGIARCollaborationsItem.class);

  private GlobalUnitManager globalUnitManager;
  private PhaseManager phaseManager;
  private CrpProgramManager crpProgramManager;
  private LiaisonInstitutionManager liaisonInstitutionManager;
  private ReportSynthesisManager reportSynthesisManager;
  private ReportSynthesisKeyPartnershipManager reportSynthesisKeyPartnershipManager;
  private ReportSynthesisKeyPartnershipCollaborationManager reportSynthesisKeyPartnershipCollaborationManager;
  private ReportSynthesisKeyPartnershipCollaborationCrpManager reportSynthesisKeyPartnershipCollaborationCrpManager;

  private KeyPartnershipCollaborationMapper keyPartnershipCollaborationMapper;

  @Inject
  public CrossCGIARCollaborationsItem(GlobalUnitManager globalUnitManager, PhaseManager phaseManager,
    CrpProgramManager crpProgramManager, LiaisonInstitutionManager liaisonInstitutionManager,
    ReportSynthesisManager reportSynthesisManager,
    ReportSynthesisKeyPartnershipManager reportSynthesisKeyPartnershipManager,
    ReportSynthesisKeyPartnershipCollaborationManager reportSynthesisKeyPartnershipCollaborationManager,
    ReportSynthesisKeyPartnershipCollaborationCrpManager reportSynthesisKeyPartnershipCollaborationCrpManager,
    KeyPartnershipCollaborationMapper keyPartnershipCollaborationMapper) {
    super();
    this.globalUnitManager = globalUnitManager;
    this.phaseManager = phaseManager;
    this.crpProgramManager = crpProgramManager;
    this.liaisonInstitutionManager = liaisonInstitutionManager;
    this.reportSynthesisManager = reportSynthesisManager;
    this.reportSynthesisKeyPartnershipManager = reportSynthesisKeyPartnershipManager;
    this.reportSynthesisKeyPartnershipCollaborationManager = reportSynthesisKeyPartnershipCollaborationManager;
    this.reportSynthesisKeyPartnershipCollaborationCrpManager = reportSynthesisKeyPartnershipCollaborationCrpManager;
    this.keyPartnershipCollaborationMapper = keyPartnershipCollaborationMapper;
  }

  public Long createCrossCGIARCollaboration(NewCrossCGIARCollaborationDTO newCrossCGIARCollaborationDTO,
    String entityAcronym, User user) {
    Long crossCGIARCollaborationID = null;
    ReportSynthesisKeyPartnershipCollaboration keyPartnershipCollaboration =
      new ReportSynthesisKeyPartnershipCollaboration();
    ReportSynthesisKeyPartnershipCollaborationCrp keyPartnershipCollaborationCrp;
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    GlobalUnit globalUnitEntity = this.globalUnitManager.findGlobalUnitByAcronym(entityAcronym);
    if (globalUnitEntity == null) {
      fieldErrors.add(new FieldErrorDTO("createCrossCGIARCollaboration", "GlobalUnitEntity",
        entityAcronym + " is an invalid CGIAR entity acronym"));
    }

    Set<CrpUser> lstUser = user.getCrpUsers();
    if (!lstUser.stream().anyMatch(crp -> crp.getCrp().getAcronym().equalsIgnoreCase(entityAcronym))) {
      fieldErrors
        .add(new FieldErrorDTO("createCrossCGIARCollaboration", "GlobalUnitEntity", "CGIAR entity not autorized"));
    }

    Phase phase = phaseManager.findAll().stream()
      .filter(p -> p.getCrp().getAcronym().equalsIgnoreCase(entityAcronym)
        && p.getYear() == newCrossCGIARCollaborationDTO.getPhase().getYear()
        && p.getName().equalsIgnoreCase(newCrossCGIARCollaborationDTO.getPhase().getName()))
      .findFirst().get();

    if (phase == null) {
      fieldErrors.add(new FieldErrorDTO("createCrossCGIARCollaboration", "Phase",
        newCrossCGIARCollaborationDTO.getPhase().getYear() + " is an invalid year."));
    }

    if (fieldErrors.isEmpty()) {
      Set<GlobalUnit> collaborationCrps = new HashSet<>();
      CrpProgram crpProgram = null;
      ReportSynthesis reportSynthesis = null;
      ReportSynthesisKeyPartnership reportSynthesisKeyPartnership = null;
      LiaisonInstitution liaisonInstitution = null;

      // start GlobalUnit
      if (newCrossCGIARCollaborationDTO.getCollaborationCrpIds() != null
        && !newCrossCGIARCollaborationDTO.getCollaborationCrpIds().isEmpty()) {
        collaborationCrps = newCrossCGIARCollaborationDTO.getCollaborationCrpIds().stream()
          .filter(s -> s != null && !s.trim().isEmpty()).map(new Function<String, GlobalUnit>() {

            @Override
            public GlobalUnit apply(String globalUnitId) {
              GlobalUnit globalUnit = globalUnitManager.findGlobalUnitBySMOCode(globalUnitId.trim());
              if (globalUnit == null) {
                fieldErrors.add(new FieldErrorDTO("createCrossCGIARCollaboration", "GlobalUnitEntity",
                  globalUnitId + " is an invalid Global Unit SMO code."));
              } else {
                if (!globalUnit.isActive()) {
                  fieldErrors.add(new FieldErrorDTO("createCrossCGIARCollaboration", "GlobalUnitEntity",
                    "The Global Unit with SMO code " + globalUnitId + " is not active."));
                }
              }

              return globalUnit;
            }
          }).collect(Collectors.toSet());
      } else {
        fieldErrors.add(
          new FieldErrorDTO("createCrossCGIARCollaboration", "GlobalUnitList", "Please enter the collaborating crps."));
      }
      // end GlobalUnit

      // start CrpProgram (flagshipProgram)
      if (newCrossCGIARCollaborationDTO.getFlagshipProgramId() != null
        && !newCrossCGIARCollaborationDTO.getFlagshipProgramId().trim().isEmpty()) {
        crpProgram =
          crpProgramManager.getCrpProgramBySmoCode(newCrossCGIARCollaborationDTO.getFlagshipProgramId().trim());
        if (crpProgram == null) {
          fieldErrors.add(new FieldErrorDTO("createCrossCGIARCollaboration", "FlagshipEntity",
            newCrossCGIARCollaborationDTO.getFlagshipProgramId() + " is an invalid CRP Program SMO code."));
        }
      } else {
        fieldErrors.add(new FieldErrorDTO("createCrossCGIARCollaboration", "FlagshipEntity",
          "CRP Program SMO code can not be null nor empty."));
      }
      // end CrpProgram (flagshipProgram)

      // start ReportSynthesis
      if (crpProgram != null) {
        liaisonInstitution = liaisonInstitutionManager.findByAcronym(crpProgram.getAcronym());
        reportSynthesis = reportSynthesisManager.findSynthesis(phase.getId(), liaisonInstitution.getId());
      }
      // end ReportSynthesis

      // start ReportSynthesisKeyPartnership
      if (reportSynthesis != null) {
        reportSynthesisKeyPartnership = reportSynthesis.getReportSynthesisKeyPartnership();
      }
      // end ReportSynthesisKeyPartnership

      // all validated! now it is supposed to be ok to save the entities
      if (fieldErrors.isEmpty()) {
        // creating new ReportSynthesis if it does not exist
        if (reportSynthesis == null) {
          reportSynthesis = new ReportSynthesis();
          liaisonInstitution = liaisonInstitutionManager.findByAcronym(crpProgram.getAcronym());
          reportSynthesis.setLiaisonInstitution(liaisonInstitution);
          reportSynthesis.setPhase(phase);
          reportSynthesisManager.saveReportSynthesis(reportSynthesis);
        }

        // creating new ReportSynthesisKeyPartnership if it does not exist
        if (reportSynthesisKeyPartnership == null) {
          reportSynthesisKeyPartnership = new ReportSynthesisKeyPartnership();
          reportSynthesisKeyPartnership.setReportSynthesis(reportSynthesis);
          reportSynthesisKeyPartnershipManager.saveReportSynthesisKeyPartnership(reportSynthesisKeyPartnership);
        }

        keyPartnershipCollaboration.setDescription(newCrossCGIARCollaborationDTO.getDescription());
        keyPartnershipCollaboration.setReportSynthesisKeyPartnership(reportSynthesisKeyPartnership);
        keyPartnershipCollaboration.setValueAdded(newCrossCGIARCollaborationDTO.getValueAdded());
        ReportSynthesisKeyPartnershipCollaboration keyPartnershipCollaborationDB =
          reportSynthesisKeyPartnershipCollaborationManager
            .saveReportSynthesisKeyPartnershipCollaboration(keyPartnershipCollaboration);
        if (keyPartnershipCollaborationDB != null) {
          crossCGIARCollaborationID = keyPartnershipCollaborationDB.getId();
          // start GlobalUnit
          for (GlobalUnit collaborationCrp : collaborationCrps) {
            keyPartnershipCollaborationCrp = new ReportSynthesisKeyPartnershipCollaborationCrp();
            keyPartnershipCollaborationCrp.setGlobalUnit(collaborationCrp);
            keyPartnershipCollaborationCrp.setReportSynthesisKeyPartnershipCollaboration(keyPartnershipCollaborationDB);
            reportSynthesisKeyPartnershipCollaborationCrpManager
              .saveReportSynthesisKeyPartnershipCollaborationCrp(keyPartnershipCollaborationCrp).getId();
          }
          // end GlobalUnit
        }
      }

    }

    if (!fieldErrors.isEmpty()) {
      fieldErrors.forEach(e -> System.out.println(e.getMessage()));
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    return crossCGIARCollaborationID;
  }

  public ResponseEntity<CrossCGIARCollaborationDTO> deleteCrossCGIARCollaborationById(Long id,
    String CGIARentityAcronym, Integer repoYear, String repoPhase, User user) {
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    GlobalUnit globalUnitEntity = this.globalUnitManager.findGlobalUnitByAcronym(CGIARentityAcronym);
    if (globalUnitEntity == null) {
      fieldErrors.add(new FieldErrorDTO("deleteCrossCGIARCollaboration", "GlobalUnitEntity",
        CGIARentityAcronym + " is an invalid CGIAR entity acronym"));
    }

    Phase phase =
      this.phaseManager.findAll().stream().filter(c -> c.getCrp().getAcronym().equalsIgnoreCase(CGIARentityAcronym)
        && c.getYear() == repoYear && c.getName().equalsIgnoreCase(repoPhase)).findFirst().get();
    if (phase == null) {
      fieldErrors.add(new FieldErrorDTO("deleteCrossCGIARCollaboration", "phase",
        repoPhase + ' ' + repoYear + " is an invalid phase"));
    }

    ReportSynthesisKeyPartnershipCollaboration keyPartnershipCollaboration =
      reportSynthesisKeyPartnershipCollaborationManager.getReportSynthesisKeyPartnershipCollaborationById(id);

    if (keyPartnershipCollaboration != null) {
      keyPartnershipCollaboration.setReportSynthesisKeyPartnershipCollaborationCrps(
        reportSynthesisKeyPartnershipCollaborationCrpManager.findAll().stream()
          .filter(c -> c.isActive() && c.getReportSynthesisKeyPartnershipCollaboration()
            .getReportSynthesisKeyPartnership().getReportSynthesis().getPhase().getId().equals(phase.getId()))
          .collect(Collectors.toSet()));

      keyPartnershipCollaboration.getReportSynthesisKeyPartnershipCollaborationCrps().stream()
        .filter(c -> c.getReportSynthesisKeyPartnershipCollaboration().getId() == keyPartnershipCollaboration.getId())
        .map(MarloBaseEntity::getId).forEach(
          reportSynthesisKeyPartnershipCollaborationCrpManager::deleteReportSynthesisKeyPartnershipCollaborationCrp);

      reportSynthesisKeyPartnershipCollaborationManager
        .deleteReportSynthesisKeyPartnershipCollaboration(keyPartnershipCollaboration.getId());
    } else {
      fieldErrors
        .add(new FieldErrorDTO("deleteCrossCGIARCollaboration", "ReportSynthesisKeyPartnershipCollaborationEntity",
          id + " is an invalid Report Synthesis Key Partnership Collaboration Code"));
    }

    if (!fieldErrors.isEmpty()) {
      fieldErrors.forEach(e -> System.out.println(e.getMessage()));
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    return Optional.ofNullable(keyPartnershipCollaboration)
      .map(this.keyPartnershipCollaborationMapper::keyPartnershipCollaborationToCrossCGIARCollaboration)
      .map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  public List<CrossCGIARCollaborationDTO> findAllCrossCGIARCollaborationsByGlobalUnit(String CGIARentityAcronym,
    Integer repoYear, String repoPhase, User user) {

    List<CrossCGIARCollaborationDTO> crossCGIARCollaborations = new ArrayList<>();
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    GlobalUnit globalUnitEntity = this.globalUnitManager.findGlobalUnitByAcronym(CGIARentityAcronym);
    if (globalUnitEntity == null) {
      fieldErrors.add(new FieldErrorDTO("findCrossCGIARCollaborationByGlobalUnit", "GlobalUnitEntity",
        CGIARentityAcronym + " is an invalid CGIAR entity acronym"));
    }

    Phase phase =
      this.phaseManager.findAll().stream().filter(c -> c.getCrp().getAcronym().equalsIgnoreCase(CGIARentityAcronym)
        && c.getYear() == repoYear && c.getName().equalsIgnoreCase(repoPhase)).findFirst().get();
    if (phase == null) {
      fieldErrors
        .add(new FieldErrorDTO("findCrossCGIARCollaborationByGlobalUnit", "phase", repoYear + " is an invalid year"));
    }

    if (fieldErrors.isEmpty()) {
      // not all ReportSynthesis have a ReportSynthesisKeyPartnership, so we need to filter out those to avoid
      // exceptions
      crossCGIARCollaborations = reportSynthesisManager.findAll().stream()
        .filter(rs -> rs.getPhase().getId() == phase.getId() && rs.getReportSynthesisKeyPartnership() != null)
        .flatMap(rs -> rs.getReportSynthesisKeyPartnership().getReportSynthesisKeyPartnershipCollaborations().stream())
        .map(keyPartnershipCollaborationMapper::keyPartnershipCollaborationToCrossCGIARCollaboration)
        .collect(Collectors.toList());
    }

    if (!fieldErrors.isEmpty()) {
      fieldErrors.forEach(e -> System.out.println(e.getMessage()));
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    return crossCGIARCollaborations;
  }

  public ResponseEntity<CrossCGIARCollaborationDTO> findCrossCGIARCollaborationById(Long id, String CGIARentityAcronym,
    Integer repoYear, String repoPhase, User user) {
    // TODO: Include all security validations
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    Set<CrpUser> lstUser = user.getCrpUsers();
    if (!lstUser.stream().anyMatch(crp -> crp.getCrp().getAcronym().equalsIgnoreCase(CGIARentityAcronym))) {
      fieldErrors
        .add(new FieldErrorDTO("findKeyExternalPartnership", "GlobalUnitEntity", "CGIAR entity not autorized"));
    }

    GlobalUnit globalUnitEntity = this.globalUnitManager.findGlobalUnitByAcronym(CGIARentityAcronym);
    if (globalUnitEntity == null) {
      fieldErrors.add(new FieldErrorDTO("findCrossCGIARCollaboration", "GlobalUnitEntity",
        CGIARentityAcronym + " is not a valid CGIAR entity acronym"));
    }

    Phase phase =
      this.phaseManager.findAll().stream().filter(c -> c.getCrp().getAcronym().equalsIgnoreCase(CGIARentityAcronym)
        && c.getYear() == repoYear && c.getName().equalsIgnoreCase(repoPhase)).findFirst().get();
    if (phase == null) {
      fieldErrors.add(new FieldErrorDTO("findCrossCGIARCollaboration", "phase", repoYear + " is an invalid year"));
    }

    ReportSynthesisKeyPartnershipCollaboration keyPartnershipCollaboration =
      reportSynthesisKeyPartnershipCollaborationManager.getReportSynthesisKeyPartnershipCollaborationById(id);
    if (keyPartnershipCollaboration == null) {
      fieldErrors
        .add(new FieldErrorDTO("findCrossCGIARCollaboration", "ReportSynthesisKeyPartnershipCollaborationEntity",
          id + " is an invalid id of a Report Synthesis Key Partnership Collaboration"));
    }

    // TODO more validations!

    // Validate all fields
    if (!fieldErrors.isEmpty()) {
      // fieldErrors.forEach(e -> System.out.println(e.getMessage()));
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    return Optional.ofNullable(keyPartnershipCollaboration)
      .map(this.keyPartnershipCollaborationMapper::keyPartnershipCollaborationToCrossCGIARCollaboration)
      .map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  public Long putCrossCGIARCollaborationById(Long idCrossCGIARCollaboration,
    NewCrossCGIARCollaborationDTO newCrossCGIARCollaborationDTO, String CGIARentityAcronym, User user) {
    Long idCrossCGIARCollaborationDB = null;
    List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

    GlobalUnit globalUnitEntity = this.globalUnitManager.findGlobalUnitByAcronym(CGIARentityAcronym);
    if (globalUnitEntity == null) {
      fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "GlobalUnitEntity",
        CGIARentityAcronym + " is an invalid CGIAR entity acronym"));
    }

    if (newCrossCGIARCollaborationDTO.getPhase() == null) {
      fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "PhaseEntity", "Phase must not be null"));
    }

    if (newCrossCGIARCollaborationDTO.getPhase().getName() == null
      || newCrossCGIARCollaborationDTO.getPhase().getYear() == null) {
      fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "PhaseEntity", "Phase is invalid"));
    }

    // i suppose is safe now
    Phase phase = this.phaseManager.findAll().stream()
      .filter(c -> c.getCrp().getAcronym().equalsIgnoreCase(CGIARentityAcronym)
        && c.getYear() == newCrossCGIARCollaborationDTO.getPhase().getYear()
        && c.getName().equalsIgnoreCase(newCrossCGIARCollaborationDTO.getPhase().getName()))
      .findFirst().orElse(null);
    if (phase == null) {
      fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "PhaseEntity",
        newCrossCGIARCollaborationDTO.getPhase().getYear() + " is an invalid year"));
    }

    CrpProgram crpProgram = null;
    if (newCrossCGIARCollaborationDTO.getFlagshipProgramId() != null) {
      crpProgram = crpProgramManager.getCrpProgramBySmoCode(newCrossCGIARCollaborationDTO.getFlagshipProgramId());
      if (crpProgram == null) {
        fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "CrpProgramEntity",
          newCrossCGIARCollaborationDTO.getFlagshipProgramId() + " is an invalid CRP Program SMO Code"));
      }

    } else {
      fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "CrpProgramEntity",
        newCrossCGIARCollaborationDTO.getFlagshipProgramId() + " is an invalid CRP Program SMO Code"));
    }

    ReportSynthesisKeyPartnershipCollaboration keyPartnershipCollaboration =
      reportSynthesisKeyPartnershipCollaborationManager
        .getReportSynthesisKeyPartnershipCollaborationById(idCrossCGIARCollaboration);
    if (keyPartnershipCollaboration == null) {
      fieldErrors
        .add(new FieldErrorDTO("putCrossCGIARCollaboration", "ReportSynthesisKeyPartnershipCollaborationEntity",
          +idCrossCGIARCollaboration + " is an invalid Report Synthesis Key Partnership Collaboration Code"));
    }

    if (fieldErrors.isEmpty()) {
      idCrossCGIARCollaborationDB = keyPartnershipCollaboration.getId();
      Set<String> base = keyPartnershipCollaboration.getReportSynthesisKeyPartnershipCollaborationCrps().stream()
        .filter(c -> c != null && c.getId() != null).map(c -> c.getGlobalUnit().getSmoCode())
        .collect(Collectors.toSet());

      // We track the changes. As a result we get a map where the list with the true key means the elements are present
      // in the database and are no longer selected; false means the elements are new and need to be saved.
      // TODO it might be a better way to do this...
      Map<Boolean, List<String>> changes =
        ChangeTracker.trackChanges(base, newCrossCGIARCollaborationDTO.getCollaborationCrpIds());

      changes.get(Boolean.TRUE).stream().filter(m -> m != null && !m.trim().isEmpty())
        .map(ch -> reportSynthesisKeyPartnershipCollaborationCrpManager.getByCollaborationIdAndGlobalUnitId(
          // we know the globalUnit exists by now, so its safe to do what is below
          keyPartnershipCollaboration.getId(), globalUnitManager.findGlobalUnitBySMOCode(ch).getId()))
        .map(ReportSynthesisKeyPartnershipCollaborationCrp::getId).forEach(
          reportSynthesisKeyPartnershipCollaborationCrpManager::deleteReportSynthesisKeyPartnershipCollaborationCrp);

      changes.get(Boolean.FALSE).stream().filter(m -> m != null && !m.trim().isEmpty()).forEach(new Consumer<String>() {

        @Override
        public void accept(String smoCode) {
          GlobalUnit incoming = globalUnitManager.findGlobalUnitBySMOCode(smoCode);
          if (incoming == null) {
            fieldErrors.add(new FieldErrorDTO("putCrossCGIARCollaboration", "GlobalUnitEntity",
              smoCode + " is an invalid Global Unit SMO Code"));
          } else {
            ReportSynthesisKeyPartnershipCollaborationCrp newCollab =
              new ReportSynthesisKeyPartnershipCollaborationCrp();
            newCollab.setGlobalUnit(incoming);
            newCollab.setReportSynthesisKeyPartnershipCollaboration(keyPartnershipCollaboration);
            reportSynthesisKeyPartnershipCollaborationCrpManager
              .saveReportSynthesisKeyPartnershipCollaborationCrp(newCollab);
          }
        }
      });

      keyPartnershipCollaboration.setDescription(newCrossCGIARCollaborationDTO.getDescription());
      keyPartnershipCollaboration.setValueAdded(newCrossCGIARCollaborationDTO.getValueAdded());

      LiaisonInstitution liaisonInstitution = liaisonInstitutionManager.findByAcronym(crpProgram.getAcronym());
      if (liaisonInstitution == null) {
        fieldErrors.add(new FieldErrorDTO("putKeyExternalPartnership", "LiaisonInstitutionEntity",
          "A Liaison Institution with the acronym " + crpProgram.getAcronym() + " could not be found"));
      }

      ReportSynthesis reportSynthesis = reportSynthesisManager.findSynthesis(phase.getId(), liaisonInstitution.getId());
      if (reportSynthesis == null) {
        fieldErrors.add(new FieldErrorDTO("putKeyExternalPartnership", "ReportSynthesisEntity",
          "A report entity linked to the Phase with id " + phase.getId() + " and Liaison Institution with id "
            + liaisonInstitution.getId() + " could not be found"));
      }

      ReportSynthesisKeyPartnership reportSynthesisKeyPartnership = reportSynthesis.getReportSynthesisKeyPartnership();
      keyPartnershipCollaboration.setReportSynthesisKeyPartnership(reportSynthesisKeyPartnership);
    }

    if (!fieldErrors.isEmpty()) {
      throw new MARLOFieldValidationException("Field Validation errors", "",
        fieldErrors.stream()
          .sorted(Comparator.comparing(FieldErrorDTO::getField, Comparator.nullsLast(Comparator.naturalOrder())))
          .collect(Collectors.toList()));
    }

    reportSynthesisKeyPartnershipCollaborationManager
      .saveReportSynthesisKeyPartnershipCollaboration(keyPartnershipCollaboration);
    return idCrossCGIARCollaborationDB;
  }

}
