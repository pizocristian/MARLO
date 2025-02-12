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
package org.cgiar.ccafs.marlo.data.manager.impl;


import org.cgiar.ccafs.marlo.data.dao.CrpProgramOutcomeDAO;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramOutcomeManager;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcome;
import org.cgiar.ccafs.marlo.data.model.Phase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Christian Garcia
 */
@Named
public class CrpProgramOutcomeManagerImpl implements CrpProgramOutcomeManager {

  // Logger
  // private static final Logger LOG = LoggerFactory.getLogger(CrpProgramOutcomeManagerImpl.class);

  private CrpProgramOutcomeDAO crpProgramOutcomeDAO;
  // private CrpOutcomeSubIdoDAO crpOutcomeSubIdoDAO;
  // private CrpAssumptionDAO crpAssumptionDAO;
  // private CrpMilestoneManager crpMilestoneManager;
  // private CrpProgramManager crpProgramManager;
  // private CrpProgramOutcomeIndicatorDAO crpProgramOutcomeIndicatorDAO;
  //
  // private PhaseManager phaseManager;

  // Managers


  @Inject
  public CrpProgramOutcomeManagerImpl(
    CrpProgramOutcomeDAO crpProgramOutcomeDAO/*
                                              * ,
                                              * CrpOutcomeSubIdoDAO crpOutcomeSubIdoDAO, CrpAssumptionDAO
                                              * crpAssumptionDAO, PhaseManager phaseManager,
                                              * CrpMilestoneManager crpMilestoneManager, CrpProgramOutcomeIndicatorDAO
                                              * crpProgramOutcomeIndicatorDAO,
                                              * CrpProgramManager crpProgramManager
                                              */) {
    this.crpProgramOutcomeDAO = crpProgramOutcomeDAO;
    // this.crpOutcomeSubIdoDAO = crpOutcomeSubIdoDAO;
    // this.crpAssumptionDAO = crpAssumptionDAO;
    // this.crpProgramOutcomeIndicatorDAO = crpProgramOutcomeIndicatorDAO;
    // this.crpMilestoneManager = crpMilestoneManager;
    // this.phaseManager = phaseManager;
    // this.crpProgramManager = crpProgramManager;
  }

  /**
   * clone the milestones
   * 
   * @param crpProgramOutcome outcome original
   * @param crpProgramOutcomeAdd outcome new
   */


  // private void addCrpIndicators(CrpProgramOutcome crpProgramOutcome, CrpProgramOutcome crpProgramOutcomeAdd) {
  //
  // if (crpProgramOutcome.getIndicators() != null) {
  // for (CrpProgramOutcomeIndicator crpProgramOutcomeIndicator : crpProgramOutcome.getIndicators()) {
  // CrpProgramOutcomeIndicator crpIndicatorAdd = new CrpProgramOutcomeIndicator();
  // crpIndicatorAdd.setCrpProgramOutcome(crpProgramOutcomeAdd);
  // crpIndicatorAdd.setIndicator(crpProgramOutcomeIndicator.getIndicator());
  // crpIndicatorAdd.setComposeID(crpProgramOutcomeIndicator.getComposeID());
  // crpIndicatorAdd = crpProgramOutcomeIndicatorDAO.save(crpIndicatorAdd);
  // if (crpProgramOutcomeIndicator.getComposeID() == null) {
  // crpProgramOutcomeIndicator.setComposeID(crpProgramOutcomeAdd.getComposeID() + "-" + crpIndicatorAdd.getId());
  // crpIndicatorAdd.setComposeID(crpProgramOutcomeAdd.getComposeID() + "-" + crpIndicatorAdd.getId());
  // crpProgramOutcomeIndicatorDAO.save(crpIndicatorAdd);
  // }
  // }
  // }
  // }


  /**
   * clone or update the outcome for next phases
   * 
   * @param next the next phase to clone
   * @param crpProgramID the program id we are working
   * @param otucome the outcome to clone
   */
  // private void addCrpPorgramOutcomePhase(Phase next, long crpProgramID, CrpProgramOutcome outcome) {
  // Phase phase = phaseManager.getPhaseById(next.getId());
  // List<CrpProgramOutcome> outcomes =
  // phase.getOutcomes().stream().filter(c -> c.isActive() && c.getCrpProgram().getId().longValue() == crpProgramID
  // && c.getComposeID().equals(outcome.getComposeID())).collect(Collectors.toList());
  // if (outcomes.isEmpty()) {
  // CrpProgramOutcome outcomeAdd = new CrpProgramOutcome();
  // outcomeAdd.setPhase(phase);
  // outcomeAdd.setCrpProgram(outcome.getCrpProgram());
  // outcomeAdd.setSrfTargetUnit(outcome.getSrfTargetUnit());
  // outcomeAdd.setValue(outcome.getValue());
  // outcomeAdd.setYear(outcome.getYear());
  // outcomeAdd.setDescription(outcome.getDescription());
  // outcomeAdd.setComposeID(outcome.getComposeID());
  // outcomeAdd.setIndicator(outcome.getIndicator());
  // if (outcome.getFile() != null) {
  // if (outcome.getFile().getId() == null || outcome.getFile().getId().longValue() == -1) {
  // outcome.setFile(null);
  // }
  // }
  // outcomeAdd.setFile(outcome.getFile());
  // crpProgramOutcomeDAO.save(outcomeAdd);
  // this.addCrpSubIdos(outcome, outcomeAdd);
  // crpMilestoneManager.addCrpMilestones(outcome, outcomeAdd);
  // this.addCrpIndicators(outcome, outcomeAdd);
  //
  // } else {
  //
  // for (CrpProgramOutcome outcomePrev : outcomes) {
  // outcomePrev.setSrfTargetUnit(outcome.getSrfTargetUnit());
  // outcomePrev.setValue(outcome.getValue());
  // outcomePrev.setYear(outcome.getYear());
  // outcomePrev.setDescription(outcome.getDescription());
  // outcomePrev.setIndicator(outcome.getIndicator());
  // if (outcome.getFile() != null) {
  // if (outcome.getFile().getId() == null || outcome.getFile().getId().longValue() == -1) {
  // outcome.setFile(null);
  // }
  // }
  // outcomePrev.setFile(outcome.getFile());
  // outcomePrev = crpProgramOutcomeDAO.save(outcomePrev);
  // this.updateCrpSubIdos(outcomePrev, outcome);
  // crpMilestoneManager.updateMilestones(outcomePrev, outcome);
  // this.updateIndicators(outcomePrev, outcome);
  // }
  //
  // }
  //
  // if (phase.getNext() != null) {
  // this.addCrpPorgramOutcomePhase(phase.getNext(), crpProgramID, outcome);
  // }
  //
  //
  // }

  /**
   * clone the outcomes Sub idos
   * 
   * @param crpProgramOutcome outcome original
   * @param crpProgramOutcomeAdd outcome new
   */

  // private void addCrpSubIdos(CrpProgramOutcome crpProgramOutcome, CrpProgramOutcome crpProgramOutcomeAdd) {
  //
  // if (crpProgramOutcome.getSubIdos() != null) {
  // for (CrpOutcomeSubIdo crpOutcomeSubIdo : crpProgramOutcome.getSubIdos()) {
  // CrpOutcomeSubIdo crpOutcomeSubIdoAdd = new CrpOutcomeSubIdo();
  // crpOutcomeSubIdoAdd.setContribution(crpOutcomeSubIdo.getContribution());
  // crpOutcomeSubIdoAdd.setSrfSubIdo(crpOutcomeSubIdo.getSrfSubIdo());
  // crpOutcomeSubIdoAdd.setCrpProgramOutcome(crpProgramOutcomeAdd);
  // crpOutcomeSubIdoAdd.setPrimary(crpOutcomeSubIdo.getPrimary());
  // crpOutcomeSubIdoDAO.save(crpOutcomeSubIdoAdd);
  // for (CrpAssumption crpAssumption : crpOutcomeSubIdo.getCrpAssumptions().stream().filter(c -> c.isActive())
  // .collect(Collectors.toList())) {
  // CrpAssumption crpAssumptionAdd = new CrpAssumption();
  // crpAssumptionAdd.setCrpOutcomeSubIdo(crpOutcomeSubIdoAdd);
  // crpAssumptionAdd.setDescription(crpAssumption.getDescription());
  // crpAssumptionDAO.save(crpAssumptionAdd);
  //
  // }
  // }
  // }
  // }

  @Override
  public void deleteCrpProgramOutcome(long crpProgramOutcomeId) {

    crpProgramOutcomeDAO.deleteCrpProgramOutcome(crpProgramOutcomeId);
  }


  @Override
  public boolean existCrpProgramOutcome(long crpProgramOutcomeID) {

    return crpProgramOutcomeDAO.existCrpProgramOutcome(crpProgramOutcomeID);
  }

  @Override
  public List<CrpProgramOutcome> findAll() {

    return crpProgramOutcomeDAO.findAll();

  }

  @Override
  public List<CrpProgramOutcome> getAllCrpProgramOutcomesByComposedIdFromPhase(String composedId, long phaseId) {
    return crpProgramOutcomeDAO.getAllCrpProgramOutcomesByComposedIdFromPhase(composedId, phaseId);
  }

  @Override
  public List<CrpProgramOutcome> getAllCrpProgramOutcomesByPhase(long phaseId) {
    return crpProgramOutcomeDAO.getAllCrpProgramOutcomesByPhase(phaseId);
  }

  @Override
  public CrpProgramOutcome getCrpProgramOutcome(String composedId, Phase phase) {
    return crpProgramOutcomeDAO.getCrpProgramOutcome(composedId, phase);
  }

  @Override
  public CrpProgramOutcome getCrpProgramOutcomeById(long crpProgramOutcomeID) {

    return crpProgramOutcomeDAO.find(crpProgramOutcomeID);
  }

  @Override
  public void replicate(CrpProgramOutcome originalCrpProgramOutcome, Phase initialPhase) {
    Phase current = initialPhase;
    String outcomeComposedId = originalCrpProgramOutcome.getComposeID();

    while (current != null) {
      CrpProgramOutcome outcome = this.getCrpProgramOutcome(outcomeComposedId, current);
      if (outcome == null) {
        outcome = new CrpProgramOutcome();
      }

      outcome.copyFields(originalCrpProgramOutcome);
      outcome.setPhase(current);

      outcome = crpProgramOutcomeDAO.save(outcome);

      // this.addCrpSubIdos(originalCrpProgramOutcome, outcome);
      // crpMilestoneManager.updateMilestones(originalCrpProgramOutcome, outcome);
      // this.addCrpIndicators(originalCrpProgramOutcome, outcome);

      // LOG.debug(current.toString());
      current = current.getNext();
    }
  }

  // @Override
  // public CrpProgramOutcome saveCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
  // CrpProgramOutcome resultDao = crpProgramOutcomeDAO.save(crpProgramOutcome);
  // if (crpProgramOutcome.getPhase().getNext() != null) {
  // this.addCrpPorgramOutcomePhase(crpProgramOutcome.getPhase().getNext(), crpProgramOutcome.getCrpProgram().getId(),
  // crpProgramOutcome);
  // }
  //
  // return resultDao;
  // }

  @Override
  public CrpProgramOutcome saveCrpProgramOutcome(CrpProgramOutcome crpProgramOutcome) {
    return crpProgramOutcomeDAO.save(crpProgramOutcome);
  }

  /**
   * check the sub-idos and updated
   * 
   * @param programOutcomePrev outcome to update
   * @param programOutcome outcome modified
   */
  // private void updateCrpSubIdos(CrpProgramOutcome programOutcomePrev, CrpProgramOutcome programOutcome) {
  // for (CrpOutcomeSubIdo outcomeSubIdo : programOutcomePrev.getCrpOutcomeSubIdos().stream().filter(c -> c.isActive())
  // .collect(Collectors.toList())) {
  // if (programOutcome.getSubIdos() == null || programOutcome.getSubIdos().stream()
  // .filter(c -> c.getSrfSubIdo() != null && c.getSrfSubIdo().equals(outcomeSubIdo.getSrfSubIdo()))
  // .collect(Collectors.toList()).isEmpty()) {
  // crpOutcomeSubIdoDAO.deleteCrpOutcomeSubIdo(outcomeSubIdo.getId());
  // }
  // }
  // if (programOutcome.getSubIdos() != null) {
  // for (CrpOutcomeSubIdo outcomeSubIdo : programOutcome.getSubIdos()) {
  // if (programOutcomePrev.getCrpOutcomeSubIdos().stream()
  // .filter(c -> c.isActive() && c.getSrfSubIdo().equals(outcomeSubIdo.getSrfSubIdo()))
  // .collect(Collectors.toList()).isEmpty()) {
  //
  // CrpOutcomeSubIdo outcomeSubIdoAdd = new CrpOutcomeSubIdo();
  //
  // outcomeSubIdoAdd.setCrpProgramOutcome(programOutcomePrev);
  // outcomeSubIdoAdd.setContribution(outcomeSubIdo.getContribution());
  // outcomeSubIdoAdd.setSrfSubIdo(outcomeSubIdo.getSrfSubIdo());
  // outcomeSubIdo.setPrimary(outcomeSubIdo.getPrimary());
  // crpOutcomeSubIdoDAO.save(outcomeSubIdoAdd);
  //
  // }
  // }
  // }
  // }


  /**
   * check the indicators and updated
   * 
   * @param programOutcomePrev outcome to update
   * @param programOutcome outcome modified
   */
  // private void updateIndicators(CrpProgramOutcome programOutcomePrev, CrpProgramOutcome programOutcome) {
  // for (CrpProgramOutcomeIndicator crpProgramOutcomeIndicator : programOutcomePrev.getCrpProgramOutcomeIndicators()
  // .stream().filter(c -> c.isActive()).collect(Collectors.toList())) {
  // if (programOutcome.getIndicators() == null || programOutcome.getIndicators().stream()
  // .filter(c -> c.getComposeID() != null && c.getComposeID().equals(crpProgramOutcomeIndicator.getComposeID()))
  // .collect(Collectors.toList()).isEmpty()) {
  // crpProgramOutcomeIndicatorDAO.deleteCrpProgramOutcomeIndicator(crpProgramOutcomeIndicator.getId());
  // }
  // }
  // if (programOutcome.getIndicators() != null) {
  // for (CrpProgramOutcomeIndicator crpProgramOutcomeIndicator : programOutcome.getIndicators()) {
  //
  // if (programOutcomePrev.getCrpProgramOutcomeIndicators().stream()
  // .filter(c -> c.isActive() && c.getComposeID().equals(crpProgramOutcomeIndicator.getComposeID()))
  // .collect(Collectors.toList()).isEmpty()) {
  //
  //
  // CrpProgramOutcomeIndicator crpIndicatorAdd = new CrpProgramOutcomeIndicator();
  // crpIndicatorAdd.setCrpProgramOutcome(programOutcomePrev);
  // crpIndicatorAdd.setIndicator(crpProgramOutcomeIndicator.getIndicator());
  // crpIndicatorAdd.setComposeID(crpProgramOutcomeIndicator.getComposeID());
  // crpIndicatorAdd = crpProgramOutcomeIndicatorDAO.save(crpIndicatorAdd);
  // if (crpProgramOutcomeIndicator.getComposeID() == null
  // || crpProgramOutcomeIndicator.getComposeID().length() == 0) {
  // crpProgramOutcomeIndicator.setComposeID(programOutcomePrev.getComposeID() + "-" + crpIndicatorAdd.getId());
  // crpIndicatorAdd.setComposeID(programOutcomePrev.getComposeID() + "-" + crpIndicatorAdd.getId());
  // crpProgramOutcomeIndicatorDAO.save(crpIndicatorAdd);
  //
  // }
  //
  //
  // } else {
  // CrpProgramOutcomeIndicator crpIndicatortoUpdate = programOutcomePrev.getCrpProgramOutcomeIndicators().stream()
  // .filter(c -> c.isActive() && c.getComposeID().equals(crpProgramOutcomeIndicator.getComposeID()))
  // .collect(Collectors.toList()).get(0);
  // crpIndicatortoUpdate.setIndicator(crpProgramOutcomeIndicator.getIndicator());
  //
  // crpProgramOutcomeIndicatorDAO.save(crpIndicatortoUpdate);
  // }
  // }
  // }
  // }

  // @Override
  // public CrpProgramOutcome updateOutcome(CrpProgramOutcome programOutcomeIncoming, long phaseId, long crpProgramId) {
  // CrpProgramOutcome crpProgramOutcome = null;
  // Phase phase = phaseManager.getPhaseById(phaseId);
  //
  // if (programOutcomeIncoming.getId() == null) {
  // crpProgramOutcome = new CrpProgramOutcome();
  // crpProgramOutcome.setPhase(phase);
  // } else {
  // crpProgramOutcome = this.getCrpProgramOutcomeById(programOutcomeIncoming.getId());
  // }
  //
  // crpProgramOutcome.setDescription(programOutcomeIncoming.getDescription());
  // crpProgramOutcome.setIndicator(programOutcomeIncoming.getIndicator());
  // crpProgramOutcome.setSrfTargetUnit(programOutcomeIncoming.getSrfTargetUnit());
  // crpProgramOutcome.setValue(programOutcomeIncoming.getValue());
  // crpProgramOutcome.setYear(programOutcomeIncoming.getYear());
  // crpProgramOutcome.setPhase(phase);
  // if (programOutcomeIncoming.getFile() != null && programOutcomeIncoming.getFile().getId() == null) {
  // programOutcomeIncoming.setFile(null);
  // }
  //
  // crpProgramOutcome.setFile(programOutcomeIncoming.getFile());
  // crpProgramOutcome.setIndicators(programOutcomeIncoming.getIndicators());
  // crpProgramOutcome.setSubIdos(programOutcomeIncoming.getSubIdos());
  //
  // CrpProgram crpProgram = crpProgramManager.getCrpProgramById(crpProgramId);
  // crpProgramOutcome.setCrpProgram(crpProgram);
  //
  // CrpProgramOutcome resultDao = this.saveCrpProgramOutcome(crpProgramOutcome);
  //
  // this.addCrpSubIdos(programOutcomeIncoming, resultDao);
  // this.addCrpIndicators(programOutcomeIncoming, resultDao);
  //
  // if (crpProgramOutcome.getPhase().getNext() != null) {
  // this.replicate(resultDao, resultDao.getPhase().getNext());
  // }
  //
  // return resultDao;
  // }
}