/*****************************************************************
 * This file is part of CCAFS Planning and Reporting Platform.
 * CCAFS P&R is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * CCAFS P&R is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with CCAFS P&R. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/

package org.cgiar.ccafs.marlo.validation.projects;


import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.BudgetTypeManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectBudget;
import org.cgiar.ccafs.marlo.data.model.ProjectBudgetsCluserActvity;
import org.cgiar.ccafs.marlo.data.model.ProjectClusterActivity;
import org.cgiar.ccafs.marlo.validation.BaseValidator;
import org.cgiar.ccafs.marlo.validation.model.ProjectValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;


/**
 * @author Christian Garcia. - CIAT/CCAFS
 */

public class ProjectBudgetsCoAValidator extends BaseValidator {

  private boolean hasErros;

  private BudgetTypeManager budgetTypeManager;
  private ProjectManager projectManager;

  @Inject
  public ProjectBudgetsCoAValidator(ProjectValidator projectValidator, BudgetTypeManager budgetTypeManager,
    ProjectManager projectManager) {
    super();

    this.projectManager = projectManager;
    this.budgetTypeManager = budgetTypeManager;
  }

  public boolean hasBudgets(Long type, int year, long projectID) {
    Project projectBD = projectManager.getProjectById(projectID);
    List<ProjectBudget> budgets = projectBD.getProjectBudgets()
      .stream().filter(c -> c.isActive() && c.getYear() == year
        && c.getBudgetType().getId().longValue() == type.longValue() && (c.getAmount() != null && c.getAmount() > 0))
      .collect(Collectors.toList());

    return budgets.size() > 0;
  }

  public boolean isHasErros() {
    return hasErros;
  }

  public void replaceAll(StringBuilder builder, String from, String to) {
    int index = builder.indexOf(from);
    while (index != -1) {
      builder.replace(index, index + from.length(), to);
      index += to.length(); // Move to the end of the replacement
      index = builder.indexOf(from, index);
    }
  }

  public void setHasErros(boolean hasErros) {
    this.hasErros = hasErros;
  }


  public void validate(BaseAction action, Project project) {
    hasErros = false;
    if (project != null) {

      Project projectDB = projectManager.getProjectById(project.getId());
      List<ProjectClusterActivity> activities =
        projectDB.getProjectClusterActivities().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (!activities.isEmpty()) {
        if (project.getBudgetsCluserActvities() == null) {
          project.setBudgetsCluserActvities(new ArrayList<ProjectBudgetsCluserActvity>());
        }
        if (!project.getBudgetsCluserActvities().isEmpty()) {
          if (this.hasBudgets(new Long(1), action.getCurrentCycleYear(), project.getId())) {
            this.validateBudgets(action, project.getBudgetsCluserActvities().stream()
              .filter(c -> c.getBudgetType().getId().longValue() == 1).collect(Collectors.toList()), new Long(1));
          }
          if (this.hasBudgets(new Long(2), action.getCurrentCycleYear(), project.getId())) {
            this.validateBudgets(action, project.getBudgetsCluserActvities().stream()
              .filter(c -> c.getBudgetType().getId().longValue() == 2).collect(Collectors.toList()), new Long(2));
          }
          if (this.hasBudgets(new Long(3), action.getCurrentCycleYear(), project.getId())) {
            this.validateBudgets(action, project.getBudgetsCluserActvities().stream()
              .filter(c -> c.getBudgetType().getId().longValue() == 3).collect(Collectors.toList()), new Long(3));
          }
          if (this.hasBudgets(new Long(4), action.getCurrentCycleYear(), project.getId())) {
            this.validateBudgets(action, project.getBudgetsCluserActvities().stream()
              .filter(c -> c.getBudgetType().getId().longValue() == 4).collect(Collectors.toList()), new Long(4));
          }
        } else {
          this.addMessage(action.getText("project.budgets"));
        }
      }

      if (!action.getFieldErrors().isEmpty()) {
        hasErros = true;
        action.addActionError(action.getText("saving.fields.required"));
      } else if (validationMessage.length() > 0) {
        action
          .addActionMessage(" " + action.getText("saving.missingFields", new String[] {validationMessage.toString()}));
      }
      if (action.isReportingActive()) {
        this.saveMissingFields(project, APConstants.REPORTING, action.getPlanningYear(), "partners");
      } else {
        this.saveMissingFields(project, APConstants.PLANNING, action.getPlanningYear(), "partners");
      }
      // Saving missing fields.

    }
  }


  public void validateBudgets(BaseAction action, List<ProjectBudgetsCluserActvity> projectBudgetsCluserActvities,
    long type) {
    List<String> params = new ArrayList<String>();
    params.add(budgetTypeManager.getBudgetTypeById(type).getName());
    double amount = 0;
    double gender = 0;

    for (ProjectBudgetsCluserActvity projectBudgetsCluserActvity : projectBudgetsCluserActvities) {
      if (projectBudgetsCluserActvity.getAmount() == null) {
        projectBudgetsCluserActvity.setAmount(new Double(0));
      }
      if (projectBudgetsCluserActvity.getGenderPercentage() == null) {
        projectBudgetsCluserActvity.setGenderPercentage(new Double(0));
      }
      amount = amount + projectBudgetsCluserActvity.getAmount().doubleValue();
      gender = gender + projectBudgetsCluserActvity.getGenderPercentage().doubleValue();

    }

    if (amount < 100) {
      this.addMessage(action.getText("project.budget.coa.amount", params));
    }
    if (gender < 100) {
      this.addMessage(action.getText("project.budget.coa.gender", params));
    }
  }


}
