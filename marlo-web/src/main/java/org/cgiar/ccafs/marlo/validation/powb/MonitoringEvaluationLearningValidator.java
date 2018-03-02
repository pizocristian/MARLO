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

package org.cgiar.ccafs.marlo.validation.powb;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.PowbSynthesisManager;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.LiaisonInstitution;
import org.cgiar.ccafs.marlo.data.model.PowbMonitoringEvaluationLearningExercise;
import org.cgiar.ccafs.marlo.data.model.PowbSynthesis;
import org.cgiar.ccafs.marlo.data.model.PowbSynthesisSectionStatusEnum;
import org.cgiar.ccafs.marlo.utils.InvalidFieldsMessages;
import org.cgiar.ccafs.marlo.validation.BaseValidator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Named;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
@Named
public class MonitoringEvaluationLearningValidator extends BaseValidator {

  private final GlobalUnitManager crpManager;
  private final PowbSynthesisManager powbSynthesisManager;


  public MonitoringEvaluationLearningValidator(GlobalUnitManager crpManager,
    PowbSynthesisManager powbSynthesisManager) {
    super();
    this.crpManager = crpManager;
    this.powbSynthesisManager = powbSynthesisManager;
  }

  private Path getAutoSaveFilePath(PowbSynthesis powbSynthesis, long crpID, BaseAction baseAction) {

    GlobalUnit crp = crpManager.getGlobalUnitById(crpID);
    String composedClassName = powbSynthesis.getClass().getSimpleName();
    String actionFile = PowbSynthesisSectionStatusEnum.MEL.getStatus().replace("/", "_");
    String autoSaveFile =
      powbSynthesis.getId() + "_" + composedClassName + "_" + baseAction.getActualPhase().getDescription() + "_"
        + baseAction.getActualPhase().getYear() + "_" + crp.getAcronym() + "_" + actionFile + ".json";

    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);
  }

  public LiaisonInstitution getLiaisonInstitution(BaseAction action, long powbSynthesisID) {
    PowbSynthesis powbSynthesis = powbSynthesisManager.getPowbSynthesisById(powbSynthesisID);
    LiaisonInstitution liaisonInstitution = powbSynthesis.getLiaisonInstitution();
    return liaisonInstitution;
  }

  public boolean isPMU(LiaisonInstitution liaisonInstitution) {
    boolean isFP = false;
    if (liaisonInstitution != null) {
      if (liaisonInstitution.getCrpProgram() == null) {
        isFP = true;
      }
    }
    return isFP;
  }

  public void validate(BaseAction action, PowbSynthesis powbSynthesis, boolean saving) {
    action.setInvalidFields(new HashMap<>());
    if (powbSynthesis != null) {
      if (!saving) {
        Path path = this.getAutoSaveFilePath(powbSynthesis, action.getCrpID(), action);

        if (path.toFile().exists()) {
          action.addMissingField("draft");
        }
      }

      if (this.isPMU(this.getLiaisonInstitution(action, powbSynthesis.getId()))) {
        this.validateMEL(action, powbSynthesis);
      }

      if (!this.isPMU(this.getLiaisonInstitution(action, powbSynthesis.getId()))) {
        if (powbSynthesis.getPowbMonitoringEvaluationLearning() != null) {
          if (powbSynthesis.getPowbMonitoringEvaluationLearning().getExercises() != null) {
            if (powbSynthesis.getPowbMonitoringEvaluationLearning().getExercises().size() == 0) {

              action.addMessage(action.getText("monitoringLearning.plannedStudies"));
              action.getInvalidFields().put("list-powbSynthesis.powbMonitoringEvaluationLearning.exercises",
                action.getText(InvalidFieldsMessages.EMPTYLIST, new String[] {"list-exercises"}));

            } else {
              for (int i = 0; i < powbSynthesis.getPowbMonitoringEvaluationLearning().getExercises().size(); i++) {
                PowbMonitoringEvaluationLearningExercise exercise =
                  powbSynthesis.getPowbMonitoringEvaluationLearning().getExercises().get(i);
                this.validateExercises(action, exercise, i);
              }
            }

          }
        }
      }

      if (!action.getFieldErrors().isEmpty()) {
        action.addActionError(action.getText("saving.fields.required"));
      } else if (action.getValidationMessage().length() > 0) {
        action.addActionMessage(
          " " + action.getText("saving.missingFields", new String[] {action.getValidationMessage().toString()}));
      }

      this.saveMissingFields(powbSynthesis, action.getActualPhase().getDescription(), action.getActualPhase().getYear(),
        PowbSynthesisSectionStatusEnum.MEL.getStatus(), action);
    }
  }

  public void validateExercises(BaseAction action, PowbMonitoringEvaluationLearningExercise exercise, int i) {

    List<String> params = new ArrayList<String>();
    params.add(String.valueOf(i + 1));

    if (!(this.isValidString(exercise.getExercise()) && this.wordCount(exercise.getExercise()) <= 100)) {
      action.addMessage(action.getText("monitoringLearning.plannedStudies.studyLearningExercise", params));
      action.getInvalidFields().put(
        "input-powbSynthesis.powbMonitoringEvaluationLearning.exercises[" + i + "].exercise",
        InvalidFieldsMessages.EMPTYFIELD);
    }

    if (!(this.isValidString(exercise.getComments()) && this.wordCount(exercise.getComments()) <= 100)) {
      action.addMessage(action.getText("monitoringLearning.plannedStudies.comments", params));
      action.getInvalidFields().put(
        "input-powbSynthesis.powbMonitoringEvaluationLearning.exercises[" + i + "].comments",
        InvalidFieldsMessages.EMPTYFIELD);
    }
  }

  public void validateMEL(BaseAction action, PowbSynthesis powbSynthesis) {
    if (powbSynthesis.getPowbMonitoringEvaluationLearning() != null) {
      if (!(this.isValidString(powbSynthesis.getPowbMonitoringEvaluationLearning().getHighlight())
        && this.wordCount(powbSynthesis.getPowbMonitoringEvaluationLearning().getHighlight()) <= 100)) {
        action.addMessage(action.getText("monitoringLearning.areasOfInterest"));
        action.getInvalidFields().put("input-powbSynthesis.powbMonitoringEvaluationLearning.highlight",
          InvalidFieldsMessages.EMPTYFIELD);
      } else {
        action.addMessage(action.getText("monitoringLearning.areasOfInterest"));
        action.getInvalidFields().put("input-powbSynthesis.powbMonitoringEvaluationLearning.highlight",
          InvalidFieldsMessages.EMPTYFIELD);
      }
    }
  }

}
