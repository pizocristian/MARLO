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

package org.cgiar.ccafs.marlo.action.summaries;

import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.LiaisonInstitutionManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.PowbCrpStaffingCategoriesManager;
import org.cgiar.ccafs.marlo.data.manager.PowbExpectedCrpProgressManager;
import org.cgiar.ccafs.marlo.data.manager.PowbExpenditureAreasManager;
import org.cgiar.ccafs.marlo.data.manager.PowbSynthesisManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectExpectedStudyManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisManager;
import org.cgiar.ccafs.marlo.data.model.CrossCuttingDimensionTableDTO;
import org.cgiar.ccafs.marlo.data.model.CrpMilestone;
import org.cgiar.ccafs.marlo.data.model.CrpOutcomeSubIdo;
import org.cgiar.ccafs.marlo.data.model.CrpPpaPartner;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcome;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableInfo;
import org.cgiar.ccafs.marlo.data.model.GlobalUnitProject;
import org.cgiar.ccafs.marlo.data.model.LiaisonInstitution;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.PowbCollaborationGlobalUnit;
import org.cgiar.ccafs.marlo.data.model.PowbEvidence;
import org.cgiar.ccafs.marlo.data.model.PowbEvidencePlannedStudy;
import org.cgiar.ccafs.marlo.data.model.PowbEvidencePlannedStudyDTO;
import org.cgiar.ccafs.marlo.data.model.PowbExpectedCrpProgress;
import org.cgiar.ccafs.marlo.data.model.PowbExpenditureAreas;
import org.cgiar.ccafs.marlo.data.model.PowbFinancialExpenditure;
import org.cgiar.ccafs.marlo.data.model.PowbFinancialPlannedBudget;
import org.cgiar.ccafs.marlo.data.model.PowbSynthesis;
import org.cgiar.ccafs.marlo.data.model.PowbSynthesisSectionStatusEnum;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectBudgetsFlagship;
import org.cgiar.ccafs.marlo.data.model.ProjectExpectedStudy;
import org.cgiar.ccafs.marlo.data.model.ProjectFocus;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesis;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisCrossCuttingDimension;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisCrpProgress;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFinancialSummary;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFundingUseSummary;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisGovernance;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisProgramVariance;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisRisk;
import org.cgiar.ccafs.marlo.data.model.TypeExpectedStudiesEnum;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.POIField;
import org.cgiar.ccafs.marlo.utils.POISummary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnualReportPOISummaryAction extends BaseSummariesAction implements Summary {

  private static final long serialVersionUID = 2828551630719082089L;
  private static Logger LOG = LoggerFactory.getLogger(AnualReportPOISummaryAction.class);

  public static double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  // Managers
  private PowbExpectedCrpProgressManager powbExpectedCrpProgressManager;
  private ProjectExpectedStudyManager projectExpectedStudyManager;
  private PowbSynthesisManager powbSynthesisManager;
  private PowbExpenditureAreasManager powbExpenditureAreasManager;
  private LiaisonInstitutionManager liaisonInstitutionManager;
  private PowbCrpStaffingCategoriesManager powbCrpStaffingCategoriesManager;
  private ReportSynthesisManager reportSynthesisManager;

  // Parameters
  private POISummary poiSummary;
  private List<PowbSynthesis> powbSynthesisList;
  private List<ReportSynthesis> reportSysthesisList;
  private LiaisonInstitution pmuInstitution;
  private PowbSynthesis powbSynthesisPMU;
  private ReportSynthesis reportSynthesisPMU;
  private long startTime;
  private XWPFDocument document;
  private List<PowbEvidencePlannedStudyDTO> flagshipPlannedList;
  private List<DeliverableInfo> deliverableList;
  private CrossCuttingDimensionTableDTO tableC;
  private NumberFormat currencyFormat;
  private DecimalFormat percentageFormat;
  private List<CrpProgram> flagships;
  // Parameter for tables E and F
  Double totalCarry = 0.0, totalw1w2 = 0.0, totalw3Bilateral = 0.0, totalCenter = 0.0, grandTotal = 0.0;
  // Streams
  private InputStream inputStream;

  // DOC bytes
  private byte[] bytesDOC;

  public AnualReportPOISummaryAction(APConfig config, GlobalUnitManager crpManager, PhaseManager phaseManager,
    PowbExpectedCrpProgressManager powbExpectedCrpProgressManager,
    ProjectExpectedStudyManager projectExpectedStudyManager, PowbSynthesisManager powbSynthesisManager,
    PowbExpenditureAreasManager powbExpenditureAreasManager, LiaisonInstitutionManager liaisonInstitutionManager,
    PowbCrpStaffingCategoriesManager powbCrpStaffingCategoriesManager, ReportSynthesisManager reportSynthesisManager) {
    super(config, crpManager, phaseManager);
    document = new XWPFDocument();
    poiSummary = new POISummary();
    currencyFormat = NumberFormat.getCurrencyInstance();
    percentageFormat = new DecimalFormat("##.##%");
    this.powbExpectedCrpProgressManager = powbExpectedCrpProgressManager;
    this.projectExpectedStudyManager = projectExpectedStudyManager;
    this.powbSynthesisManager = powbSynthesisManager;
    this.powbExpenditureAreasManager = powbExpenditureAreasManager;
    this.liaisonInstitutionManager = liaisonInstitutionManager;
    this.powbCrpStaffingCategoriesManager = powbCrpStaffingCategoriesManager;
    this.reportSynthesisManager = reportSynthesisManager;
  }

  private void addAdjustmentDescription() {
    if (powbSynthesisPMU != null) {
      String adjustmentsDescription = "";
      if (powbSynthesisPMU.getPowbToc() != null) {
        adjustmentsDescription = powbSynthesisPMU.getPowbToc().getTocOverall() != null
          && !powbSynthesisPMU.getPowbToc().getTocOverall().trim().isEmpty()
            ? powbSynthesisPMU.getPowbToc().getTocOverall() : "";
      }
      poiSummary.textParagraph(document.createParagraph(), adjustmentsDescription);
      if (powbSynthesisPMU.getPowbToc() != null && powbSynthesisPMU.getPowbToc().getFile() != null) {
        poiSummary.textHyperlink(
          this.getPowbPath(powbSynthesisPMU.getLiaisonInstitution(),
            this.getLoggedCrp().getAcronym() + "_"
              + PowbSynthesisSectionStatusEnum.TOC_ADJUSTMENTS.getStatus().toString())
            + powbSynthesisPMU.getPowbToc().getFile().getFileName().replaceAll(" ", "%20"),
          "URL: " + powbSynthesisPMU.getPowbToc().getFile().getFileName(), document.createParagraph());
      }
    }
  }

  private void addCollaboration() {
    String newKeyExternalPartnershipsDescription = "";
    String newContributionPlatformsDescription = "";
    String newCrossCRPInteractionsDescription = "";
    String expectedEffortsCountryCoordinationDescription = "";
    if (powbSynthesisPMU != null) {
      // Collaboration and integration
      if (powbSynthesisPMU.getCollaboration() != null) {
        newKeyExternalPartnershipsDescription = powbSynthesisPMU.getCollaboration().getKeyExternalPartners() != null
          && !powbSynthesisPMU.getCollaboration().getKeyExternalPartners().trim().isEmpty()
            ? powbSynthesisPMU.getCollaboration().getKeyExternalPartners() : "";

        newContributionPlatformsDescription = powbSynthesisPMU.getCollaboration().getCotributionsPlatafforms() != null
          && !powbSynthesisPMU.getCollaboration().getCotributionsPlatafforms().trim().isEmpty()
            ? powbSynthesisPMU.getCollaboration().getCotributionsPlatafforms() : "";

        newCrossCRPInteractionsDescription = powbSynthesisPMU.getCollaboration().getCrossCrp() != null
          && !powbSynthesisPMU.getCollaboration().getCrossCrp().trim().isEmpty()
            ? powbSynthesisPMU.getCollaboration().getCrossCrp() : "";

        expectedEffortsCountryCoordinationDescription =
          powbSynthesisPMU.getCollaboration().getEffostornCountry() != null
            && !powbSynthesisPMU.getCollaboration().getEffostornCountry().trim().isEmpty()
              ? powbSynthesisPMU.getCollaboration().getEffostornCountry() : "";
      }
    }
  }

  private void addCrossCuttingCapacityDevelopment() {

    String crossCuttingCapacityDevelopment = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingCapacityDevelopment = crossCutting.getCapDev();
        }
      }
    }

    if (crossCuttingCapacityDevelopment != null && !crossCuttingCapacityDevelopment.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingCapacityDevelopment);
    }


  }

  private void addCrossCuttingGender() {

    String crossCuttingGenderDescription = "";
    String crossCuttingGenderLessons = "";
    if (reportSynthesisPMU != null) {

      // Cross Cutting Gender Info
      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingGenderDescription = crossCutting.getGenderDescription();
          crossCuttingGenderLessons = crossCutting.getGenderLessons();
        }
      }
    }

    if (crossCuttingGenderDescription != null && !crossCuttingGenderDescription.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingGenderDescription);
    }

    if (crossCuttingGenderLessons != null && !crossCuttingGenderLessons.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingGenderLessons);
    }

  }

  private void addCrossCuttingIntellectualAssets() {

    String crossCuttingIntellectualAssets = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingIntellectualAssets = crossCutting.getIntellectualAssets();
        }
      }
    }

    if (crossCuttingIntellectualAssets != null && !crossCuttingIntellectualAssets.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingIntellectualAssets);
    }

  }

  private void addCrossCuttingOpenData() {

    String crossCuttingOpenData = "";
    if (reportSynthesisPMU != null) {

      // Cross Cutting Gender Info
      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingOpenData = crossCutting.getOpenData();
        }
      }
    }

    if (crossCuttingOpenData != null && !crossCuttingOpenData.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingOpenData);
    }

  }

  private void addCrossCuttingOtherAspects() {

    String crossCuttingOtherAspects = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingOtherAspects = crossCutting.getOtherAspects();
        }
      }
    }

    if (crossCuttingOtherAspects != null && !crossCuttingOtherAspects.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingOtherAspects);
    }

  }

  private void addCrossCuttingYouth() {

    String crossCuttingYouthDescription = "";
    String crossCuttingYouthLessons = "";
    if (reportSynthesisPMU != null) {

      // Cross Cutting Gender Info
      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingYouthDescription = crossCutting.getYouthDescription();
          crossCuttingYouthLessons = crossCutting.getYouthLessons();
        }
      }
    }

    if (crossCuttingYouthDescription != null && !crossCuttingYouthDescription.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingYouthDescription);
    }

    if (crossCuttingYouthLessons != null && !crossCuttingYouthLessons.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingYouthLessons);
    }

  }

  private void addCrpStaffing() {
    String staffingDescription = "";
    if (powbSynthesisPMU != null) {
      // TOC
      if (powbSynthesisPMU.getCrpStaffing() != null) {
        staffingDescription = powbSynthesisPMU.getCrpStaffing().getStaffingIssues() != null
          && !powbSynthesisPMU.getCrpStaffing().getStaffingIssues().trim().isEmpty()
            ? powbSynthesisPMU.getCrpStaffing().getStaffingIssues() : "";
      }
      poiSummary.textParagraph(document.createParagraph(), staffingDescription);
    }
  }


  private void addEvidence() {
    String evidenceDescription = "";
    if (powbSynthesisPMU != null) {
      // Evidence
      if (powbSynthesisPMU.getPowbEvidence() != null) {
        PowbEvidence powbEvidence = powbSynthesisPMU.getPowbEvidence();
        if (powbEvidence != null) {
          evidenceDescription = powbEvidence.getNarrative() != null && !powbEvidence.getNarrative().trim().isEmpty()
            ? powbEvidence.getNarrative() : "";
        }
      }
      poiSummary.textParagraph(document.createParagraph(), evidenceDescription);
    }
  }


  private void addExpectedCrp() {

    if (reportSynthesisPMU != null) {
      String synthesisCrpDescription = "";
      String synthesisCrpSummaries = "";

      synthesisCrpDescription = reportSynthesisPMU.getReportSynthesisCrpProgress().getOverallProgress();
      synthesisCrpSummaries = reportSynthesisPMU.getReportSynthesisFlagshipProgress().getSummary();
      // CRP Progress
      List<ReportSynthesisCrpProgress> reportSynthesisCrpProgressList = null;
      // reportSysthesisPMU.getReportSynthesisCrpProgress().stream().filter(e ->
      // e.isActive()).collect(Collectors.toList());

      if (reportSynthesisCrpProgressList != null && !reportSynthesisCrpProgressList.isEmpty()) {
        ReportSynthesisCrpProgress reportSynthesisCrpProgress = reportSynthesisCrpProgressList.get(0);
        synthesisCrpDescription = reportSynthesisCrpProgress.getOverallProgress() != null
          && !reportSynthesisCrpProgress.getOverallProgress().trim().isEmpty()
            ? reportSynthesisCrpProgress.getOverallProgress() : "";
      }
      poiSummary.textParagraph(document.createParagraph(), synthesisCrpDescription);

      // poiSummary.textParagraph(document.createParagraph(), synthesisCrpSummaries);
    }
  }

  private void addFinancialPlan() {
    String financialPlanDescription = "";
    if (powbSynthesisPMU != null) {
      // Financial Plan
      if (powbSynthesisPMU.getFinancialPlan() != null) {
        financialPlanDescription = powbSynthesisPMU.getFinancialPlan().getFinancialPlanIssues() != null
          && !powbSynthesisPMU.getFinancialPlan().getFinancialPlanIssues().trim().isEmpty()
            ? powbSynthesisPMU.getFinancialPlan().getFinancialPlanIssues() : "";
      }
    }
    poiSummary.textParagraph(document.createParagraph(), financialPlanDescription);

  }

  private void addFinancialSummary() {

    String financialSummaryNarrative = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisFinancialSummary() != null) {
        ReportSynthesisFinancialSummary financialSummary = reportSynthesisPMU.getReportSynthesisFinancialSummary();
        if (financialSummary != null) {
          financialSummaryNarrative = financialSummary.getNarrative();
        }
      }
    }

    if (financialSummaryNarrative != null && !financialSummaryNarrative.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), financialSummaryNarrative);
    }
  }

  private void addFlagshipPlans() {
    for (LiaisonInstitution liaisonInstitution : this.getFlagships()) {
      String plansCRPFlagshipDescription = "";
      List<PowbSynthesis> powbSynthesisFL = powbSynthesisList.stream()
        .filter(p -> p.isActive() && p.getLiaisonInstitution().equals(liaisonInstitution)).collect(Collectors.toList());
      PowbSynthesis powbSynthesis = null;
      if (powbSynthesisFL != null && powbSynthesisFL.size() > 0) {
        powbSynthesis = powbSynthesisFL.get(0);
      }
      String liaisonName = liaisonInstitution.getAcronym() != null && !liaisonInstitution.getAcronym().isEmpty()
        ? liaisonInstitution.getAcronym() : liaisonInstitution.getName();
      plansCRPFlagshipDescription += liaisonName + ": ";

      if (powbSynthesis != null && powbSynthesis.getPowbFlagshipPlans() != null) {
        if (powbSynthesis.getPowbFlagshipPlans().getPlanSummary() != null) {
          plansCRPFlagshipDescription += powbSynthesis.getPowbFlagshipPlans().getPlanSummary();
        }
        poiSummary.textParagraph(document.createParagraph(), plansCRPFlagshipDescription);
        if (powbSynthesis.getPowbFlagshipPlans() != null
          && powbSynthesis.getPowbFlagshipPlans().getFlagshipProgramFile() != null) {
          poiSummary.textHyperlink(
            this.getPowbPath(liaisonInstitution,
              this.getLoggedCrp().getAcronym() + "_"
                + PowbSynthesisSectionStatusEnum.FLAGSHIP_PLANS.getStatus().toString())
              + powbSynthesis.getPowbFlagshipPlans().getFlagshipProgramFile().getFileName().replaceAll(" ", "%20"),
            "URL: " + powbSynthesis.getPowbFlagshipPlans().getFlagshipProgramFile().getFileName(),
            document.createParagraph());
        }
      }
    }
  }

  private void addFundingSummarize() {

    String brieflySummarize = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisFundingUseSummary() != null) {
        ReportSynthesisFundingUseSummary funding = reportSynthesisPMU.getReportSynthesisFundingUseSummary();
        if (funding != null) {
          brieflySummarize = funding.getMainArea();
        }
      }
    }

    if (brieflySummarize != null && !brieflySummarize.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), brieflySummarize);
    }

  }

  private void addImprovingEfficiency() {

    String improvingEfficiencyDescription = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisFundingUseSummary() != null) {
        ReportSynthesisFundingUseSummary funding = reportSynthesisPMU.getReportSynthesisFundingUseSummary();
        if (funding != null) {
          improvingEfficiencyDescription = funding.getMainArea();
        }
      }
    }

    if (improvingEfficiencyDescription != null && !improvingEfficiencyDescription.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), improvingEfficiencyDescription);
    }
  }

  private void addManagement() {
    // Crp Management
    String managementRisksTitleDescription = "", CRPManagementGovernanceDescription = "";
    if (powbSynthesisPMU != null) {
      // management risk
      if (powbSynthesisPMU.getPowbManagementRisk() != null) {
        managementRisksTitleDescription = powbSynthesisPMU.getPowbManagementRisk().getHighlight() != null
          && !powbSynthesisPMU.getPowbManagementRisk().getHighlight().trim().isEmpty()
            ? powbSynthesisPMU.getPowbManagementRisk().getHighlight() : "";
      }
      // Governance
      if (powbSynthesisPMU.getPowbManagementGovernance() != null) {
        CRPManagementGovernanceDescription = powbSynthesisPMU.getPowbManagementGovernance().getDescription() != null
          && !powbSynthesisPMU.getPowbManagementGovernance().getDescription().trim().isEmpty()
            ? powbSynthesisPMU.getPowbManagementGovernance().getDescription() : "";
      }
    }
    poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.management.governance"));
    this.addManagementGovernance();
    poiSummary.textParagraph(document.createParagraph(), CRPManagementGovernanceDescription);

    poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.management.risk"));
    this.addManagementRisks();

    poiSummary.textParagraph(document.createParagraph(), managementRisksTitleDescription);
    poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.management.financial"));
    this.addFinancialSummary();
  }

  private void addManagementGovernance() {

    String managementGovernanceDescription = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisGovernance() != null) {
        ReportSynthesisGovernance governance = reportSynthesisPMU.getReportSynthesisGovernance();
        if (governance != null) {
          managementGovernanceDescription = governance.getDescription();
        }
      }
    }

    if (managementGovernanceDescription != null && !managementGovernanceDescription.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), managementGovernanceDescription);
    }
  }

  private void addManagementRisks() {

    String managementRiskBrief = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisRisk() != null) {
        ReportSynthesisRisk risk = reportSynthesisPMU.getReportSynthesisRisk();
        if (risk != null) {
          managementRiskBrief = risk.getBriefSummary();
        }
      }
    }

    if (managementRiskBrief != null && !managementRiskBrief.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), managementRiskBrief);
    }
  }


  private void addParticipatingCenters() {
    String participantingCenters = "";
    List<CrpPpaPartner> crpPpaPartnerList = this.getLoggedCrp().getCrpPpaPartners().stream()
      .filter(c -> c.isActive() && c.getPhase() != null && c.getPhase().equals(this.getSelectedPhase()))
      .collect(Collectors.toList());
    if (crpPpaPartnerList != null && !crpPpaPartnerList.isEmpty()) {
      for (CrpPpaPartner crpPpaPartner : crpPpaPartnerList) {
        if (participantingCenters.isEmpty()) {
          if (crpPpaPartner.getInstitution().getAcronym() != null
            && !crpPpaPartner.getInstitution().getAcronym().trim().isEmpty()) {
            participantingCenters = crpPpaPartner.getInstitution().getAcronym();
          } else {
            participantingCenters = crpPpaPartner.getInstitution().getName();
          }
        } else {
          if (crpPpaPartner.getInstitution().getAcronym() != null
            && !crpPpaPartner.getInstitution().getAcronym().trim().isEmpty()) {
            participantingCenters += ", " + crpPpaPartner.getInstitution().getAcronym();
          } else {
            participantingCenters += ", " + crpPpaPartner.getInstitution().getName();
          }
        }
      }
    }
    poiSummary.textParagraph(document.createParagraph(),
      this.getText("summaries.annualReport.participantingCenters") + ": " + participantingCenters + ":");
  }

  private void addVariancePlanned() {

    String variancePlanned = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisProgramVariance() != null) {
        ReportSynthesisProgramVariance programVariance = reportSynthesisPMU.getReportSynthesisProgramVariance();
        if (programVariance != null) {
          variancePlanned = programVariance.getDescription();
        }
      }
    }

    if (variancePlanned != null && !variancePlanned.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), variancePlanned);
    }

  }

  public void createTableA1() {
    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader = {
      new POIField(this.getText("annualReport.crpProgress.selectSLOTarget")
        + this.getText("summaries.annualReport.tableA1.targetTitle2"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.crpProgress.summaryNewEvidence.readText"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.crpProgress.additionalContribution"), ParagraphAlignment.CENTER),};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    String FP, subIDO = "", outcomes;

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (CrpProgram flagship : flagships) {
      int flagshipIndex = 0;
      data = new ArrayList<>();
      for (CrpProgramOutcome outcome : flagship.getOutcomes()) {
        subIDO = "";
        for (CrpOutcomeSubIdo subIdo : outcome.getSubIdos()) {
          if (subIdo.getSrfSubIdo() != null) {
            if (subIDO.isEmpty()) {
              if (subIdo.getSrfSubIdo().getSrfIdo().isIsCrossCutting()) {
                subIDO = "• CC " + subIdo.getSrfSubIdo().getDescription();
              } else {
                subIDO = "• " + subIdo.getSrfSubIdo().getDescription();
              }
            } else {
              if (subIdo.getSrfSubIdo().getSrfIdo().isIsCrossCutting()) {
                subIDO += "\n • CC " + subIdo.getSrfSubIdo().getDescription();
              } else {
                subIDO += "\n • " + subIdo.getSrfSubIdo().getDescription();
              }
            }
          }
        }
        outcomes = outcome.getComposedName();

        if (flagshipIndex == 0) {
          FP = flagship.getAcronym();
        } else {
          FP = " ";
        }

        Boolean bold = false;
        String blackColor = "000000";
        String redColor = "c00000";
        POIField[] sData = {new POIField(FP, ParagraphAlignment.LEFT, bold, blackColor),
          new POIField(subIDO, ParagraphAlignment.LEFT, bold, blackColor),
          new POIField(outcomes, ParagraphAlignment.LEFT, bold, blackColor)};
        data = Arrays.asList(sData);
        datas.add(data);
        flagshipIndex++;
      }
    }


    poiSummary.textTable(document, headers, datas, false, "tableAAnnualReport");
  }

  private void createTableA2() {

    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader = {new POIField(this.getText("summaries.annualReport.tableA2.field1"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableA2.field2"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableA2.field3"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableA2.field4"), ParagraphAlignment.LEFT)};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    String FP, outcomes, milestone, assessment, meansVerifications;

    List<List<POIField>> datas = new ArrayList<>();

    List<POIField> data;

    for (CrpProgram flagship : flagships) {
      data = new ArrayList<>();
      int outcome_index = 0;
      for (CrpProgramOutcome outcome : flagship.getOutcomes()) {
        int milestone_index = 0;
        for (CrpMilestone crpMilestone : outcome.getMilestones()) {
          Boolean isFlagshipRow = (outcome_index == 0) && (milestone_index == 0);
          Boolean isOutcomeRow = (milestone_index == 0);
          if (isFlagshipRow) {
            FP = flagship.getAcronym();
          } else {
            FP = " ";
          }
          if (isOutcomeRow) {
            outcomes = outcome.getComposedName();
          } else {
            outcomes = " ";
          }
          milestone = crpMilestone.getComposedName();

          PowbExpectedCrpProgress milestoneProgress =
            this.getPowbExpectedCrpProgressProgram(crpMilestone.getId(), flagship.getId());
          assessment =
            milestoneProgress.getAssesmentName() != null && !milestoneProgress.getAssesmentName().trim().isEmpty()
              ? milestoneProgress.getAssesmentName() : " ";
          meansVerifications = milestoneProgress.getMeans() != null && !milestoneProgress.getMeans().trim().isEmpty()
            ? milestoneProgress.getMeans() : " ";

          POIField[] sData =
            {new POIField(FP, ParagraphAlignment.CENTER), new POIField(outcomes, ParagraphAlignment.LEFT),
              new POIField(milestone, ParagraphAlignment.LEFT), new POIField(assessment, ParagraphAlignment.CENTER)};
          data = Arrays.asList(sData);
          datas.add(data);

          milestone_index++;
        }
        outcome_index++;
      }
    }


    poiSummary.textTable(document, headers, datas, false, "tableA");
  }


  private void createTableB() {
    /*
     * List<List<POIField>> headers = new ArrayList<>();
     * POIField[] sHeader = {new POIField(this.getText("expectedProgress.tableA.fp"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("expectedProgress.tableA.subIDO"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("expectedProgress.tableA.outcomes"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("expectedProgress.tableA.milestone" + "*"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("annualReport.flagshipProgress.tableB.explanation"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("Provide " + "annualReport.flagshipProgress.tableB.status"),
     * ParagraphAlignment.CENTER)};
     * List<POIField> header = Arrays.asList(sHeader);
     * headers.add(header);
     * String FP, outcomes, milestone, assessment, meansVerifications;
     * List<List<POIField>> datas = new ArrayList<>();
     * List<POIField> data;
     * for (CrpProgram flagship : flagships) {
     * data = new ArrayList<>();
     * int outcome_index = 0;
     * for (CrpProgramOutcome outcome : flagship.getOutcomes()) {
     * int milestone_index = 0;
     * for (CrpMilestone crpMilestone : outcome.getMilestones()) {
     * Boolean isFlagshipRow = (outcome_index == 0) && (milestone_index == 0);
     * Boolean isOutcomeRow = (milestone_index == 0);
     * if (isFlagshipRow) {
     * FP = flagship.getAcronym();
     * } else {
     * FP = " ";
     * }
     * if (isOutcomeRow) {
     * outcomes = outcome.getComposedName();
     * } else {
     * outcomes = " ";
     * }
     * milestone = crpMilestone.getComposedName();
     * PowbExpectedCrpProgress milestoneProgress =
     * this.getPowbExpectedCrpProgressProgram(crpMilestone.getId(), flagship.getId());
     * assessment =
     * milestoneProgress.getAssesmentName() != null && !milestoneProgress.getAssesmentName().trim().isEmpty()
     * ? milestoneProgress.getAssesmentName() : " ";
     * meansVerifications = milestoneProgress.getMeans() != null && !milestoneProgress.getMeans().trim().isEmpty()
     * ? milestoneProgress.getMeans() : " ";
     * POIField[] sData = {new POIField(FP, ParagraphAlignment.CENTER),
     * new POIField(outcomes, ParagraphAlignment.LEFT), new POIField(milestone, ParagraphAlignment.LEFT),
     * new POIField(outcomes, ParagraphAlignment.LEFT), new POIField(milestone, ParagraphAlignment.LEFT),
     * new POIField(meansVerifications, ParagraphAlignment.LEFT),
     * new POIField(assessment, ParagraphAlignment.CENTER)};
     * data = Arrays.asList(sData);
     * datas.add(data);
     * milestone_index++;
     * }
     * outcome_index++;
     * }
     * }
     * poiSummary.textTable(document, headers, datas, false, "tableA")
     */
  }


  /*
   * private void createTableB() {
   * List<List<POIField>> headers = new ArrayList<>();
   * POIField[] sHeader = {new POIField(this.getText("evidenceRelevant.table.plannedTopic"), ParagraphAlignment.CENTER),
   * new POIField(this.getText("evidenceRelevant.tablePlannedStudies.geographicScope"), ParagraphAlignment.CENTER),
   * new POIField(this.getText("evidenceRelevant.tablePlannedStudies.relevant"), ParagraphAlignment.CENTER),
   * new POIField(this.getText("evidenceRelevant.tablePlannedStudies.comments"), ParagraphAlignment.CENTER)};
   * List<POIField> header = Arrays.asList(sHeader);
   * headers.add(header);
   * List<List<POIField>> datas = new ArrayList<>();
   * List<POIField> data;
   * this.getFpPlannedList(this.getFlagships(), this.getSelectedPhase().getId());
   * for (PowbEvidencePlannedStudyDTO powbEvidencePlannedStudyDTO : flagshipPlannedList.stream()
   * .filter(p -> p.getProjectExpectedStudy() != null && p.getProjectExpectedStudy().getType() != null
   * && (p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.OUTCOMECASESTUDY.getId()
   * || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.IMPACTASSESMENT.getId()
   * || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.ADOPTIONSTUDY.getId()))
   * .collect(Collectors.toList())) {
   * String plannedStudy = "", geographicScope = "", revelantSubIDO = "", comments = "";
   * plannedStudy = powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getTopicStudy() != null
   * && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getTopicStudy().trim().isEmpty()
   * ? powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getTopicStudy() : " ";
   * geographicScope = powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getScopeName() != null
   * && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getScopeName().trim().isEmpty()
   * ? powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getScopeName() : " ";
   * if (powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSubIdo() != null
   * && powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSubIdo().getDescription() != null
   * && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSubIdo().getDescription().trim().isEmpty()) {
   * revelantSubIDO += "• " + powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSubIdo().getDescription();
   * }
   * if (powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSloIndicator() != null
   * && powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSloIndicator().getTitle() != null
   * && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSloIndicator().getTitle().trim().isEmpty()) {
   * if (revelantSubIDO.isEmpty()) {
   * revelantSubIDO +=
   * "• " + powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSloIndicator().getTitle();
   * } else {
   * revelantSubIDO +=
   * "\n• " + powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSrfSloIndicator().getTitle();
   * }
   * }
   * if (revelantSubIDO.isEmpty()) {
   * revelantSubIDO = " ";
   * }
   * comments = powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getComments() != null
   * && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getComments().trim().isEmpty()
   * ? powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getComments() : " ";
   * POIField[] sData = {new POIField(plannedStudy, ParagraphAlignment.CENTER),
   * new POIField(geographicScope, ParagraphAlignment.CENTER), new POIField(revelantSubIDO, ParagraphAlignment.LEFT),
   * new POIField(comments, ParagraphAlignment.LEFT)};
   * data = Arrays.asList(sData);
   * datas.add(data);
   * }
   * poiSummary.textTable(document, headers, datas, false, "tableB");
   * }
   */

  private void createTableC() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("crossCuttingDimensions.tableC.crossCutting"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.principal"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.significant"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.notTargeted"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.overall"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    List<List<POIField>> datas = new ArrayList<>();

    List<POIField> data;
    this.tableCInfo(this.getSelectedPhase());

    if (tableC != null) {
      POIField[] sData = {new POIField("Gender", ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageGenderPrincipal() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageGenderSignificant() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageGenderNotScored() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(String.valueOf((int) tableC.getTotal()), ParagraphAlignment.CENTER)};
      data = Arrays.asList(sData);
      datas.add(data);
      POIField[] sData2 = {new POIField("Youth", ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageYouthPrincipal() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageYouthSignificant() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageYouthNotScored() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField("", ParagraphAlignment.CENTER)};
      data = Arrays.asList(sData2);
      datas.add(data);
      POIField[] sData3 = {new POIField("CapDev", ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageCapDevPrincipal() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageCapDevSignificant() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageCapDevNotScored() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField("", ParagraphAlignment.CENTER)};
      data = Arrays.asList(sData3);
      datas.add(data);
    }

    poiSummary.textTable(document, headers, datas, true, "tableC");
  }

  private void createTableD() {

    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("summaries.annualReport.tableD1.field1"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD1.field2"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD1.field3"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD1.field4"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (PowbSynthesis powbSynthesis : powbSynthesisList) {
      List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitList =
        powbSynthesis.getPowbCollaborationGlobalUnits().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (powbCollaborationGlobalUnitList != null && !powbCollaborationGlobalUnitList.isEmpty()) {
        for (PowbCollaborationGlobalUnit powbCollaborationGlobalUnit : powbCollaborationGlobalUnitList) {
          String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";
          if (powbCollaborationGlobalUnit.getGlobalUnit() != null) {
            crpPlatform = powbCollaborationGlobalUnit.getGlobalUnit().getAcronym() != null
              && !powbCollaborationGlobalUnit.getGlobalUnit().getAcronym().isEmpty()
                ? powbCollaborationGlobalUnit.getGlobalUnit().getAcronym()
                : powbCollaborationGlobalUnit.getGlobalUnit().getName();
          }

          descriptionCollaboration =
            powbCollaborationGlobalUnit.getBrief() != null && !powbCollaborationGlobalUnit.getBrief().isEmpty()
              ? powbCollaborationGlobalUnit.getBrief() : " ";
          relevantFP =
            powbCollaborationGlobalUnit.getFlagship() != null && !powbCollaborationGlobalUnit.getFlagship().isEmpty()
              ? powbCollaborationGlobalUnit.getFlagship() : " ";

          POIField[] sData =
            {new POIField(crpPlatform, ParagraphAlignment.CENTER), new POIField(crpPlatform, ParagraphAlignment.CENTER),
              new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
              new POIField(relevantFP, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableG");


    /*
     * List<List<POIField>> headers = new ArrayList<>();
     * POIField[] sHeader =
     * {new POIField(this.getText("summaries.annualReport.tableD1.field1"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("summaries.annualReport.tableD1.field2"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("summaries.annualReport.tableD1.field3"), ParagraphAlignment.CENTER),
     * new POIField(this.getText("summaries.annualReport.tableD1.field4"), ParagraphAlignment.CENTER)};
     * List<POIField> header = Arrays.asList(sHeader);
     * headers.add(header);
     * List<List<POIField>> datas = new ArrayList<>();
     * List<POIField> data;
     * Double totalFemale = 0.0, totalMale = 0.0, totalFemaleNoCg = 0.0, totalMaleNoCg = 0.0;
     * for (PowbCrpStaffingCategories crpStaffingCategory : powbCrpStaffingCategoriesManager.findAll().stream()
     * .filter(c -> c.isActive()).collect(Collectors.toList())) {
     * String category = "";
     * Double female = 0.0, femaleNoCg = 0.0, totalFTE = 0.0, femalePercentaje = 0.0, male = 0.0, maleNoCg = 0.0;
     * category = crpStaffingCategory.getCategory();
     * if (powbSynthesisPMU != null) {
     * List<PowbSynthesisCrpStaffingCategory> powbSynthesisCrpStaffingCategoryList = crpStaffingCategory
     * .getPowbSynthesisCrpStaffingCategory().stream().sorted((c1, c2) -> c1.getId().compareTo(c2.getId()))
     * .filter(
     * pc -> pc.isActive() && pc.getPowbSynthesis() != null && pc.getPowbSynthesis().equals(powbSynthesisPMU))
     * .collect(Collectors.toList());
     * if (powbSynthesisCrpStaffingCategoryList != null && powbSynthesisCrpStaffingCategoryList.size() > 0) {
     * PowbSynthesisCrpStaffingCategory powbSynthesisCrpStaffingCategory =
     * powbSynthesisCrpStaffingCategoryList.get(0);
     * female =
     * powbSynthesisCrpStaffingCategory.getFemale() != null ? powbSynthesisCrpStaffingCategory.getFemale() : 0.0;
     * femaleNoCg = powbSynthesisCrpStaffingCategory.getFemaleNoCgiar() != null
     * ? powbSynthesisCrpStaffingCategory.getFemaleNoCgiar() : 0.0;
     * femalePercentaje = powbSynthesisCrpStaffingCategory.getFemalePercentage() / 100.0;
     * male = powbSynthesisCrpStaffingCategory.getMale() != null ? powbSynthesisCrpStaffingCategory.getMale() : 0.0;
     * maleNoCg = powbSynthesisCrpStaffingCategory.getMaleNoCgiar() != null
     * ? powbSynthesisCrpStaffingCategory.getMaleNoCgiar() : 0.0;
     * totalFemale += female;
     * totalFemaleNoCg += femaleNoCg;
     * totalMale += male;
     * totalMaleNoCg += maleNoCg;
     * totalFTE = powbSynthesisCrpStaffingCategory.getTotalFTE();
     * }
     * }
     * POIField[] sData = {
     * new POIField(category, ParagraphAlignment.LEFT),
     * new POIField(String.valueOf(round(female, 2)) + " (" + round(femaleNoCg, 2) + ")", ParagraphAlignment.CENTER),
     * new POIField(String.valueOf(round(male, 2)) + " (" + round(maleNoCg, 2) + ")", ParagraphAlignment.CENTER),
     * new POIField(percentageFormat.format(round(femalePercentaje, 4)), ParagraphAlignment.CENTER)};
     * data = Arrays.asList(sData);
     * datas.add(data);
     * }
     * PowbSynthesisCrpStaffingCategory powbSynthesisCrpStaffingCategory = new PowbSynthesisCrpStaffingCategory();
     * powbSynthesisCrpStaffingCategory.setMale(totalMale);
     * powbSynthesisCrpStaffingCategory.setMaleNoCgiar(totalMaleNoCg);
     * powbSynthesisCrpStaffingCategory.setFemale(totalFemale);
     * powbSynthesisCrpStaffingCategory.setFemaleNoCgiar(totalFemaleNoCg);
     * Boolean bold = true;
     * String blackColor = "000000";
     * POIField[] sData = {new POIField("Total CRP", ParagraphAlignment.LEFT, bold, blackColor),
     * new POIField(
     * String.valueOf(round(powbSynthesisCrpStaffingCategory.getFemale(), 2)) + " ("
     * + round(powbSynthesisCrpStaffingCategory.getFemaleNoCgiar(), 2) + ")",
     * ParagraphAlignment.CENTER, bold, blackColor),
     * new POIField(
     * String.valueOf(round(powbSynthesisCrpStaffingCategory.getMale(), 2)) + " ("
     * + round(powbSynthesisCrpStaffingCategory.getMaleNoCgiar(), 2) + ")",
     * ParagraphAlignment.CENTER, bold, blackColor),
     * new POIField(String.valueOf(round(powbSynthesisCrpStaffingCategory.getTotalFTE(), 2)), ParagraphAlignment.CENTER,
     * bold, blackColor),
     * new POIField(percentageFormat.format(round(powbSynthesisCrpStaffingCategory.getFemalePercentage() / 100.0, 4)),
     * ParagraphAlignment.CENTER, bold, blackColor)};
     * data = Arrays.asList(sData);
     * datas.add(data);
     * poiSummary.textTable(document, headers, datas, false, "tableD");
     */
  }

  private void createTableD2() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("summaries.annualReport.tableD2.field1"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD2.field2"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD2.field3"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD2.field4"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD2.field5"), ParagraphAlignment.CENTER),};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (PowbSynthesis powbSynthesis : powbSynthesisList) {
      List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitList =
        powbSynthesis.getPowbCollaborationGlobalUnits().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (powbCollaborationGlobalUnitList != null && !powbCollaborationGlobalUnitList.isEmpty()) {
        for (PowbCollaborationGlobalUnit powbCollaborationGlobalUnit : powbCollaborationGlobalUnitList) {
          String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";
          if (powbCollaborationGlobalUnit.getGlobalUnit() != null) {
            crpPlatform = powbCollaborationGlobalUnit.getGlobalUnit().getAcronym() != null
              && !powbCollaborationGlobalUnit.getGlobalUnit().getAcronym().isEmpty()
                ? powbCollaborationGlobalUnit.getGlobalUnit().getAcronym()
                : powbCollaborationGlobalUnit.getGlobalUnit().getName();
          }

          descriptionCollaboration =
            powbCollaborationGlobalUnit.getBrief() != null && !powbCollaborationGlobalUnit.getBrief().isEmpty()
              ? powbCollaborationGlobalUnit.getBrief() : " ";
          relevantFP =
            powbCollaborationGlobalUnit.getFlagship() != null && !powbCollaborationGlobalUnit.getFlagship().isEmpty()
              ? powbCollaborationGlobalUnit.getFlagship() : " ";

          POIField[] sData =
            {new POIField(crpPlatform, ParagraphAlignment.CENTER), new POIField(crpPlatform, ParagraphAlignment.CENTER),
              new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
              new POIField(relevantFP, ParagraphAlignment.LEFT), new POIField(relevantFP, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableG");

  }

  private void createTableE() {
    try {
      List<List<POIField>> headers = new ArrayList<>();
      POIField[] sHeader = {new POIField("", ParagraphAlignment.CENTER),
        new POIField(
          this.getText("financialPlan.tableE.plannedBudget", new String[] {String.valueOf(this.getSelectedYear())}),
          ParagraphAlignment.CENTER),
        new POIField("", ParagraphAlignment.CENTER), new POIField("", ParagraphAlignment.CENTER),
        new POIField("", ParagraphAlignment.CENTER), new POIField("", ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableE.field1"), ParagraphAlignment.CENTER)};
      POIField[] sHeader2 = {new POIField(" ", ParagraphAlignment.CENTER),
        new POIField(
          this.getText("financialPlan.tableE.carryOver", new String[] {String.valueOf(this.getSelectedYear() - 1)}),
          ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableE.field2"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableE.field3"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableE.field4"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableE.field5"), ParagraphAlignment.CENTER),
        new POIField(" ", ParagraphAlignment.CENTER)};

      List<POIField> header = Arrays.asList(sHeader);
      List<POIField> header2 = Arrays.asList(sHeader2);
      headers.add(header);
      headers.add(header2);

      List<List<POIField>> datas = new ArrayList<>();
      List<POIField> data;
      powbSynthesisPMU.setPowbFinancialPlannedBudgetList(powbSynthesisPMU.getPowbFinancialPlannedBudget().stream()
        .filter(fp -> fp.isActive()).collect(Collectors.toList()));
      // Flagships
      List<LiaisonInstitution> flagships = this.getFlagships();
      if (flagships != null && !flagships.isEmpty()) {
        for (LiaisonInstitution flagship : flagships) {
          Double carry = 0.0, w1w2 = 0.0, w3Bilateral = 0.0, center = 0.0, total = 0.0;
          String category = "", comments = "";
          category = flagship.getAcronym();
          PowbFinancialPlannedBudget powbFinancialPlannedBudget =
            this.getPowbFinancialPlanBudget(flagship.getId(), true);
          if (powbFinancialPlannedBudget != null) {
            w1w2 = powbFinancialPlannedBudget.getW1w2() != null ? powbFinancialPlannedBudget.getW1w2() : 0.0;
            carry = powbFinancialPlannedBudget.getCarry() != null ? powbFinancialPlannedBudget.getCarry() : 0.0;
            w3Bilateral =
              powbFinancialPlannedBudget.getW3Bilateral() != null ? powbFinancialPlannedBudget.getW3Bilateral() : 0.0;
            center =
              powbFinancialPlannedBudget.getCenterFunds() != null ? powbFinancialPlannedBudget.getCenterFunds() : 0.0;
            total = powbFinancialPlannedBudget.getTotalPlannedBudget() != null
              ? powbFinancialPlannedBudget.getTotalPlannedBudget() : 0.0;
            comments = powbFinancialPlannedBudget.getComments() == null
              || powbFinancialPlannedBudget.getComments().trim().isEmpty() ? " "
                : powbFinancialPlannedBudget.getComments();
          }
          totalCarry += carry;
          totalw1w2 += w1w2;
          totalw3Bilateral += w3Bilateral;
          totalCenter += center;
          grandTotal += total;
          POIField[] sData = {new POIField(category, ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(carry, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(w1w2, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(w3Bilateral, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(center, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(total, 2)), ParagraphAlignment.CENTER),
            new POIField(comments, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
      // Expenditure areas
      List<PowbExpenditureAreas> powbExpenditureAreas = this.getPlannedBudgetAreas();
      if (powbExpenditureAreas != null && !powbExpenditureAreas.isEmpty()) {
        for (PowbExpenditureAreas powbExpenditureArea : powbExpenditureAreas) {
          Double carry = 0.0, w1w2 = 0.0, w3Bilateral = 0.0, center = 0.0, total = 0.0;
          String category = "", comments = "";
          category = powbExpenditureArea.getExpenditureArea();
          PowbFinancialPlannedBudget powbFinancialPlannedBudget =
            this.getPowbFinancialPlanBudget(powbExpenditureArea.getId(), false);
          if (powbFinancialPlannedBudget != null) {
            w1w2 = powbFinancialPlannedBudget.getW1w2() != null ? powbFinancialPlannedBudget.getW1w2() : 0.0;
            carry = powbFinancialPlannedBudget.getCarry() != null ? powbFinancialPlannedBudget.getCarry() : 0.0;
            w3Bilateral =
              powbFinancialPlannedBudget.getW3Bilateral() != null ? powbFinancialPlannedBudget.getW3Bilateral() : 0.0;
            center =
              powbFinancialPlannedBudget.getCenterFunds() != null ? powbFinancialPlannedBudget.getCenterFunds() : 0.0;
            total = powbFinancialPlannedBudget.getTotalPlannedBudget() != null
              ? powbFinancialPlannedBudget.getTotalPlannedBudget() : 0.0;
            comments = powbFinancialPlannedBudget.getComments() == null
              || powbFinancialPlannedBudget.getComments().trim().isEmpty() ? " "
                : powbFinancialPlannedBudget.getComments();
          }
          totalCarry += carry;
          totalw1w2 += w1w2;
          totalw3Bilateral += w3Bilateral;
          totalCenter += center;
          grandTotal += total;
          POIField[] sData = {new POIField(category, ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(carry, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(w1w2, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(w3Bilateral, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(center, 2)), ParagraphAlignment.CENTER),
            new POIField(currencyFormat.format(round(total, 2)), ParagraphAlignment.CENTER),
            new POIField(comments, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);

        }
      }

      Boolean bold = true;
      String blackColor = "000000";
      POIField[] sData = {new POIField("CRP Total", ParagraphAlignment.CENTER, bold, blackColor),
        new POIField(currencyFormat.format(round(totalCarry, 2)), ParagraphAlignment.CENTER, bold, blackColor),
        new POIField(currencyFormat.format(round(totalw1w2, 2)), ParagraphAlignment.CENTER, bold, blackColor),
        new POIField(currencyFormat.format(round(totalw3Bilateral, 2)), ParagraphAlignment.CENTER, bold, blackColor),
        new POIField(currencyFormat.format(round(totalCenter, 2)), ParagraphAlignment.CENTER, bold, blackColor),
        new POIField(currencyFormat.format(round(grandTotal, 2)), ParagraphAlignment.CENTER, bold, blackColor),
        new POIField(" ", ParagraphAlignment.LEFT, bold, blackColor)};

      data = Arrays.asList(sData);
      datas.add(data);

      poiSummary.textTable(document, headers, datas, false, "tableE");
    } catch (Exception e) {

    }
  }

  private void createTableF() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("financialPlan.tableF.expenditureArea") + "*", ParagraphAlignment.CENTER),
        new POIField(this.getText("financialPlan.tableF.estimatedPercentage",
          new String[] {String.valueOf(this.getSelectedYear())}) + "**", ParagraphAlignment.CENTER),
        new POIField(this.getText("financialPlan.tableF.comments"), ParagraphAlignment.CENTER)};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;


    Double totalEstimatedPercentajeFS = 0.0;
    // Expenditure areas
    for (PowbExpenditureAreas powbExpenditureArea : powbExpenditureAreasManager.findAll().stream()
      .filter(a -> a.isActive() && a.getIsExpenditure()).collect(Collectors.toList())) {
      Double estimatedPercentajeFS = 0.0;
      String expenditureArea = "", commentsSpace = "";
      expenditureArea = powbExpenditureArea.getExpenditureArea();
      if (powbSynthesisPMU != null) {
        List<PowbFinancialExpenditure> powbFinancialExpenditureList =
          powbExpenditureArea.getPowbFinancialExpenditures().stream()
            .filter(f -> f.isActive() && f.getPowbSynthesis().equals(powbSynthesisPMU)).collect(Collectors.toList());
        if (powbFinancialExpenditureList != null && !powbFinancialExpenditureList.isEmpty()) {
          PowbFinancialExpenditure powbFinancialExpenditure = powbFinancialExpenditureList.get(0);
          estimatedPercentajeFS = powbFinancialExpenditure.getW1w2Percentage();
          commentsSpace =
            powbFinancialExpenditure.getComments() == null || powbFinancialExpenditure.getComments().trim().isEmpty()
              ? " " : powbFinancialExpenditure.getComments();
          totalEstimatedPercentajeFS += estimatedPercentajeFS;
        }
      }

      POIField[] sData = {new POIField(expenditureArea, ParagraphAlignment.LEFT),
        new POIField(percentageFormat.format(round(estimatedPercentajeFS / 100, 4)), ParagraphAlignment.CENTER),
        new POIField(commentsSpace, ParagraphAlignment.LEFT)};
      data = Arrays.asList(sData);
      datas.add(data);
    }


    Boolean bold = true;
    String blackColor = "000000";

    POIField[] sData = {new POIField("Total Funding (Amount)", ParagraphAlignment.LEFT, bold, blackColor),
      new POIField(currencyFormat.format(round((totalw1w2 * totalEstimatedPercentajeFS) / 100, 2)),
        ParagraphAlignment.CENTER, bold, blackColor),
      new POIField(" ", ParagraphAlignment.LEFT, bold, blackColor)};

    data = Arrays.asList(sData);
    datas.add(data);

    poiSummary.textTable(document, headers, datas, true, "tableF");
  }

  private void createTableG() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {
      new POIField(
        this.getText("monitoringLearning.table.plannedStudies", new String[] {String.valueOf(this.getSelectedYear())}),
        ParagraphAlignment.CENTER),
      new POIField(this.getText("monitoringLearning.table.comments"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    this.getFpPlannedList(this.getFlagships(), this.getSelectedPhase().getId());
    for (PowbEvidencePlannedStudyDTO powbEvidencePlannedStudyDTO : flagshipPlannedList.stream()
      .filter(p -> p.getProjectExpectedStudy() != null && p.getProjectExpectedStudy().getType() != null
        && (p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.EVAULATION.getId()
          || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.IMPACTASSESMENT.getId()
          || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.LEARNING.getId()
          || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.IMPACTCASESTUDY.getId()
          || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.CRP_PTF.getId()
          || p.getProjectExpectedStudy().getType() == TypeExpectedStudiesEnum.REVIEW.getId()))
      .collect(Collectors.toList())) {
      String plannedStudy = "", comments = "";
      plannedStudy = powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getTopicStudy() != null
        && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getTopicStudy().trim().isEmpty()
          ? powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getTopicStudy() : " ";
      comments = powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getComments() != null
        && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getComments().trim().isEmpty()
          ? powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getComments() : " ";

      POIField[] sData =
        {new POIField(plannedStudy, ParagraphAlignment.LEFT), new POIField(comments, ParagraphAlignment.LEFT)};

      data = Arrays.asList(sData);
      datas.add(data);
    }

    poiSummary.textTable(document, headers, datas, false, "tableH");
  }

  private void createTableH() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("summaries.annualReport.tableG.crpName"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableG.description"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableG.relevantFP"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (PowbSynthesis powbSynthesis : powbSynthesisList) {
      List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitList =
        powbSynthesis.getPowbCollaborationGlobalUnits().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (powbCollaborationGlobalUnitList != null && !powbCollaborationGlobalUnitList.isEmpty()) {
        for (PowbCollaborationGlobalUnit powbCollaborationGlobalUnit : powbCollaborationGlobalUnitList) {
          String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";
          if (powbCollaborationGlobalUnit.getGlobalUnit() != null) {
            crpPlatform = powbCollaborationGlobalUnit.getGlobalUnit().getAcronym() != null
              && !powbCollaborationGlobalUnit.getGlobalUnit().getAcronym().isEmpty()
                ? powbCollaborationGlobalUnit.getGlobalUnit().getAcronym()
                : powbCollaborationGlobalUnit.getGlobalUnit().getName();
          }

          descriptionCollaboration =
            powbCollaborationGlobalUnit.getBrief() != null && !powbCollaborationGlobalUnit.getBrief().isEmpty()
              ? powbCollaborationGlobalUnit.getBrief() : " ";
          relevantFP =
            powbCollaborationGlobalUnit.getFlagship() != null && !powbCollaborationGlobalUnit.getFlagship().isEmpty()
              ? powbCollaborationGlobalUnit.getFlagship() : " ";

          POIField[] sData = {new POIField(crpPlatform, ParagraphAlignment.CENTER),
            new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
            new POIField(relevantFP, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableG");
  }

  private void createTableI1() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {new POIField(this.getText("annualReport.melia.tableI.studies"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.tableI.status"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.tableI.comments"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (PowbSynthesis powbSynthesis : powbSynthesisList) {
      List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitList =
        powbSynthesis.getPowbCollaborationGlobalUnits().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (powbCollaborationGlobalUnitList != null && !powbCollaborationGlobalUnitList.isEmpty()) {
        for (PowbCollaborationGlobalUnit powbCollaborationGlobalUnit : powbCollaborationGlobalUnitList) {
          String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";
          if (powbCollaborationGlobalUnit.getGlobalUnit() != null) {
            crpPlatform = powbCollaborationGlobalUnit.getGlobalUnit().getAcronym() != null
              && !powbCollaborationGlobalUnit.getGlobalUnit().getAcronym().isEmpty()
                ? powbCollaborationGlobalUnit.getGlobalUnit().getAcronym()
                : powbCollaborationGlobalUnit.getGlobalUnit().getName();
          }

          descriptionCollaboration =
            powbCollaborationGlobalUnit.getBrief() != null && !powbCollaborationGlobalUnit.getBrief().isEmpty()
              ? powbCollaborationGlobalUnit.getBrief() : " ";
          relevantFP =
            powbCollaborationGlobalUnit.getFlagship() != null && !powbCollaborationGlobalUnit.getFlagship().isEmpty()
              ? powbCollaborationGlobalUnit.getFlagship() : " ";

          POIField[] sData = {new POIField(crpPlatform, ParagraphAlignment.CENTER),
            new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
            new POIField(relevantFP, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableG");
  }

  private void createTableI2() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {new POIField(this.getText("annualReport.melia.evaluation.name"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.evaluation.recommendation"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.evaluation.managementResponse") + " - Action Plan",
        ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (PowbSynthesis powbSynthesis : powbSynthesisList) {
      List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitList =
        powbSynthesis.getPowbCollaborationGlobalUnits().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (powbCollaborationGlobalUnitList != null && !powbCollaborationGlobalUnitList.isEmpty()) {
        for (PowbCollaborationGlobalUnit powbCollaborationGlobalUnit : powbCollaborationGlobalUnitList) {
          String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";
          if (powbCollaborationGlobalUnit.getGlobalUnit() != null) {
            crpPlatform = powbCollaborationGlobalUnit.getGlobalUnit().getAcronym() != null
              && !powbCollaborationGlobalUnit.getGlobalUnit().getAcronym().isEmpty()
                ? powbCollaborationGlobalUnit.getGlobalUnit().getAcronym()
                : powbCollaborationGlobalUnit.getGlobalUnit().getName();
          }

          descriptionCollaboration =
            powbCollaborationGlobalUnit.getBrief() != null && !powbCollaborationGlobalUnit.getBrief().isEmpty()
              ? powbCollaborationGlobalUnit.getBrief() : " ";
          relevantFP =
            powbCollaborationGlobalUnit.getFlagship() != null && !powbCollaborationGlobalUnit.getFlagship().isEmpty()
              ? powbCollaborationGlobalUnit.getFlagship() : " ";

          POIField[] sData = {new POIField(crpPlatform, ParagraphAlignment.CENTER),
            new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
            new POIField(relevantFP, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableG");
  }

  private void createTableJ() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {new POIField(this.getText("annualReport.financial.tableJ.budget"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.financial.tableJ.expenditure") + " 2017*", ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.financial.tableJ.difference"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    for (PowbSynthesis powbSynthesis : powbSynthesisList) {
      List<PowbCollaborationGlobalUnit> powbCollaborationGlobalUnitList =
        powbSynthesis.getPowbCollaborationGlobalUnits().stream().filter(c -> c.isActive()).collect(Collectors.toList());
      if (powbCollaborationGlobalUnitList != null && !powbCollaborationGlobalUnitList.isEmpty()) {
        for (PowbCollaborationGlobalUnit powbCollaborationGlobalUnit : powbCollaborationGlobalUnitList) {
          String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";
          if (powbCollaborationGlobalUnit.getGlobalUnit() != null) {
            crpPlatform = powbCollaborationGlobalUnit.getGlobalUnit().getAcronym() != null
              && !powbCollaborationGlobalUnit.getGlobalUnit().getAcronym().isEmpty()
                ? powbCollaborationGlobalUnit.getGlobalUnit().getAcronym()
                : powbCollaborationGlobalUnit.getGlobalUnit().getName();
          }

          descriptionCollaboration =
            powbCollaborationGlobalUnit.getBrief() != null && !powbCollaborationGlobalUnit.getBrief().isEmpty()
              ? powbCollaborationGlobalUnit.getBrief() : " ";
          relevantFP =
            powbCollaborationGlobalUnit.getFlagship() != null && !powbCollaborationGlobalUnit.getFlagship().isEmpty()
              ? powbCollaborationGlobalUnit.getFlagship() : " ";

          POIField[] sData = {new POIField(crpPlatform, ParagraphAlignment.CENTER),
            new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
            new POIField(relevantFP, ParagraphAlignment.LEFT)};

          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableG");
  }


  @Override
  public String execute() throws Exception {
    try {
      /* Create a portrait text Section */
      CTDocument1 doc = document.getDocument();
      CTBody body = doc.getBody();

      poiSummary.pageHeader(document, this.getText("summaries.annualReport.header"));
      // Get datetime
      ZonedDateTime timezone = ZonedDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-d 'at' HH:mm ");
      String zone = timezone.getOffset() + "";
      if (zone.equals("Z")) {
        zone = "+0";
      }

      String currentDate = timezone.format(format) + "(GMT" + zone + ")";
      // poiSummary.pageFooter(document, "This report was generated on " + currentDate);

      // Cover
      poiSummary.textLineBreak(document, 13);
      poiSummary.textHeadCoverTitle(document.createParagraph(), this.getText("summaries.annualReport.mainTitle"));

      // First page
      poiSummary.textLineBreak(document, 14);
      poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.mainTitle2"));
      poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.cover"));
      String unitName = this.getLoggedCrp().getAcronym() != null && !this.getLoggedCrp().getAcronym().isEmpty()
        ? this.getLoggedCrp().getAcronym() : this.getLoggedCrp().getName();
      poiSummary.textParagraph(document.createParagraph(),
        this.getText("summaries.annualReport.unitName") + ": " + unitName);
      poiSummary.textParagraph(document.createParagraph(),
        this.getText("summaries.annualReport.LeadCenter") + ": " + " name ");
      this.addParticipatingCenters();

      // section 1 - key results
      poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.keyResults"));
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.crpProgress"));
      this.addExpectedCrp();
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.progressFlagships"));
      this.addAdjustmentDescription();
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions"));
      // this.addEvidence();
      poiSummary.textHead3Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions.gender"));
      this.addCrossCuttingGender();
      poiSummary.textHead3Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions.youth"));
      this.addCrossCuttingYouth();
      poiSummary.textHead3Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions.otherAspects"));
      this.addCrossCuttingOtherAspects();
      poiSummary.textHead3Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions.capacityDevelopment"));
      this.addCrossCuttingCapacityDevelopment();
      poiSummary.textHead3Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions.openData"));
      this.addCrossCuttingOpenData();
      poiSummary.textHead3Title(document.createParagraph(),
        this.getText("summaries.annualReport.keyResults.dimensions.intellectualAssets"));
      this.addCrossCuttingIntellectualAssets();


      this.addFlagshipPlans();

      // section 2 - variance from planned program
      poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.effectiveness"));
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.effectiveness.program"));
      this.addVariancePlanned();

      this.addCrpStaffing();
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.effectiveness.funding"));
      // this.addFinancialPlan();
      this.addFundingSummarize();
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.effectiveness.partnership"));
      this.addCollaboration();
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.effectiveness.cross"));
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.effectiveness.mel"));
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("summaries.annualReport.effectiveness.efficiency"));
      this.addImprovingEfficiency();

      // section 3 - crp management
      poiSummary.textLineBreak(document, 1);
      poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.management"));
      this.addManagement();

      /* Create a landscape text Section */

      /*
       * XWPFParagraph para = document.createParagraph();
       * CTSectPr sectionTable = body.getSectPr();
       * CTPageSz pageSizeTable = sectionTable.addNewPgSz();
       * CTP ctpTable = para.getCTP();
       * CTPPr brTable = ctpTable.addNewPPr();
       * brTable.setSectPr(sectionTable);
       * pageSizeTable.setOrient(STPageOrientation.PORTRAIT);
       */
      /*
       * pageSizeTable.setW(BigInteger.valueOf(842 * 20));
       * pageSizeTable.setH(BigInteger.valueOf(595 * 20));
       */

      // XWPFParagraph paragraph = document.createParagraph();


      this.loadTablePMU();

      // Table a1
      poiSummary.textLineBreak(document, 1);
      // poiSummary.textHead1Title(paragraph, "TABLES");
      poiSummary.textHead1Title(document.createParagraph(), "TABLES");
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableA.title"));
      poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableA1.title"));
      this.createTableA1();

      // Table a2
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableA2.title"));
      this.createTableA2();

      // Table b
      poiSummary.textNotes(document.createParagraph(), "*" + this.getText("summaries.annualReport.tableA2.footer"));
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableB.title"));
      this.createTableB();

      // Table c
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(), this.getText("annualReport.ccDimensions.tableCTitle"));
      this.createTableC();

      // Table d
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableD.title"));

      // Table d1
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableD1.title"));
      this.createTableD();

      // Table d2
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableD2.title"));
      this.createTableD2();

      // Table e
      poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableD1.footer"));
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(),
        this.getText("annualReport.ccDimensions.tableETitle", new String[] {String.valueOf(this.getSelectedYear())}));
      this.createTableE();

      // Table f
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(), this.getText("financialPlan.tableF.title2017"));
      this.createTableF();
      poiSummary.textNotes(document.createParagraph(), this.getText("financialPlan.tableF.expenditureArea.help2017"));

      // Table g
      poiSummary.textNotes(document.createParagraph(),
        this.getText("financialPlan.tableF.estimatedPercentage.help2017"));
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableG.title"));
      this.createTableG();

      // Table h
      poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableG.description.help"));
      document.createParagraph().setPageBreak(true); // Fast Page Break
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableH.title"));
      this.createTableH();

      // Table i
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableI.title"));
      poiSummary.textHead3Title(document.createParagraph(), this.getText("annualReport.melia.tableI.title"));
      this.createTableI1();
      poiSummary.textHead3Title(document.createParagraph(), this.getText("annualReport.melia.evaluation.title"));
      this.createTableI2();

      // Table j
      poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableJ.title"));
      document.createParagraph().setPageBreak(true); // Fast Page Break
      this.createTableJ();
      poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableJ.description.help"));


      ByteArrayOutputStream os = new ByteArrayOutputStream();
      document.write(os);
      bytesDOC = os.toByteArray();
      os.close();
      document.close();
    } catch (Exception e) {
      LOG.error("Error generating POWB Summary " + e.getMessage());
      throw e;
    }

    // Calculate time of generation
    long stopTime = System.currentTimeMillis();
    stopTime = stopTime - startTime;
    LOG.info("Downloaded successfully: " + this.getFileName() + ". User: "
      + this.getCurrentUser().getComposedCompleteName() + ". CRP: " + this.getLoggedCrp().getAcronym() + ". Cycle: "
      + this.getSelectedCycle() + ". Time to generate: " + stopTime + "ms.");
    return SUCCESS;
  }

  @Override
  public int getContentLength() {
    return bytesDOC.length;
  }

  @Override
  public String getContentType() {
    return "application/docx";
  }

  public List<PowbExpenditureAreas> getExpenditureAreas() {
    List<PowbExpenditureAreas> expenditureAreaList = powbExpenditureAreasManager.findAll().stream()
      .filter(e -> e.isActive() && e.getIsExpenditure()).collect(Collectors.toList());
    if (expenditureAreaList != null) {
      return expenditureAreaList;
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public String getFileName() {
    StringBuffer fileName = new StringBuffer();


    fileName.append(this.getLoggedCrp().getAcronym() + "_2017_CRP_AR_");
    fileName.append(".docx");

    /*
     * fileName.append(this.getLoggedCrp().getAcronym() + "-POWBSynthesis-");
     * fileName.append(this.getSelectedYear() + "_");
     * fileName.append(new SimpleDateFormat("yyyyMMdd-HHmm").format(new Date()));
     * fileName.append(".docx");
     */
    return fileName.toString();
  }

  public List<LiaisonInstitution> getFlagships() {
    List<LiaisonInstitution> flagshipsList = this.getLoggedCrp().getLiaisonInstitutions().stream()
      .filter(c -> c.getCrpProgram() != null
        && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue() && c.isActive())
      .collect(Collectors.toList());
    if (flagshipsList != null) {
      flagshipsList.sort(Comparator.comparing(LiaisonInstitution::getAcronym));
      return flagshipsList;
    } else {
      return new ArrayList<>();
    }
  }

  public void getFpPlannedList(List<LiaisonInstitution> lInstitutions, long phaseID) {
    flagshipPlannedList = new ArrayList<>();
    try {
      if (projectExpectedStudyManager.findAll() != null) {
        List<ProjectExpectedStudy> expectedStudies = new ArrayList<>(projectExpectedStudyManager.findAll().stream()
          .filter(ps -> ps.isActive() && ps.getPhase() != null && ps.getPhase() == phaseID
            && ps.getProject().getGlobalUnitProjects().stream()
              .filter(gup -> gup.isActive() && gup.isOrigin()
                && gup.getGlobalUnit().getId().equals(this.getLoggedCrp().getId()))
              .collect(Collectors.toList()).size() > 0)
          .collect(Collectors.toList()));

        for (ProjectExpectedStudy projectExpectedStudy : expectedStudies) {
          PowbEvidencePlannedStudyDTO dto = new PowbEvidencePlannedStudyDTO();
          projectExpectedStudy.getProject()
            .setProjectInfo(projectExpectedStudy.getProject().getProjecInfoPhase(this.getSelectedPhase()));
          dto.setProjectExpectedStudy(projectExpectedStudy);
          if (projectExpectedStudy.getProject().getProjectInfo().getAdministrative() != null
            && projectExpectedStudy.getProject().getProjectInfo().getAdministrative()) {
            dto.setLiaisonInstitutions(new ArrayList<>());
            dto.getLiaisonInstitutions().add(this.pmuInstitution);
          } else {
            List<ProjectFocus> projectFocuses = new ArrayList<>(projectExpectedStudy.getProject().getProjectFocuses()
              .stream().filter(pf -> pf.isActive() && pf.getPhase().getId() == phaseID).collect(Collectors.toList()));
            List<LiaisonInstitution> liaisonInstitutions = new ArrayList<>();
            for (ProjectFocus projectFocus : projectFocuses) {
              liaisonInstitutions.addAll(projectFocus.getCrpProgram().getLiaisonInstitutions().stream()
                .filter(li -> li.isActive() && li.getCrpProgram() != null
                  && li.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
                .collect(Collectors.toList()));
            }
            dto.setLiaisonInstitutions(liaisonInstitutions);
          }

          flagshipPlannedList.add(dto);
        }

        List<PowbEvidencePlannedStudy> evidencePlannedStudies = new ArrayList<>();
        for (LiaisonInstitution liaisonInstitution : lInstitutions) {
          PowbSynthesis powbSynthesis = powbSynthesisManager.findSynthesis(phaseID, liaisonInstitution.getId());
          if (powbSynthesis != null) {
            if (powbSynthesis.getPowbEvidence() != null) {
              if (powbSynthesis.getPowbEvidence().getPowbEvidencePlannedStudies() != null) {
                List<PowbEvidencePlannedStudy> studies = new ArrayList<>(powbSynthesis.getPowbEvidence()
                  .getPowbEvidencePlannedStudies().stream().filter(s -> s.isActive()).collect(Collectors.toList()));
                if (studies != null || !studies.isEmpty()) {
                  for (PowbEvidencePlannedStudy powbEvidencePlannedStudy : studies) {
                    evidencePlannedStudies.add(powbEvidencePlannedStudy);
                  }
                }
              }
            }
          }
        }

        List<PowbEvidencePlannedStudyDTO> removeList = new ArrayList<>();
        for (PowbEvidencePlannedStudyDTO dto : flagshipPlannedList) {

          List<LiaisonInstitution> removeLiaison = new ArrayList<>();
          for (LiaisonInstitution liaisonInstitution : dto.getLiaisonInstitutions()) {
            PowbSynthesis powbSynthesis = powbSynthesisManager.findSynthesis(phaseID, liaisonInstitution.getId());
            if (powbSynthesis != null) {
              if (powbSynthesis.getPowbEvidence() != null) {

                PowbEvidencePlannedStudy evidencePlannedStudyNew = new PowbEvidencePlannedStudy();
                evidencePlannedStudyNew = new PowbEvidencePlannedStudy();
                evidencePlannedStudyNew.setProjectExpectedStudy(dto.getProjectExpectedStudy());
                evidencePlannedStudyNew.setPowbEvidence(powbSynthesis.getPowbEvidence());

                if (evidencePlannedStudies.contains(evidencePlannedStudyNew)) {
                  removeLiaison.add(liaisonInstitution);
                }
              }
            }
          }

          for (LiaisonInstitution li : removeLiaison) {
            dto.getLiaisonInstitutions().remove(li);
          }

          if (dto.getLiaisonInstitutions().isEmpty()) {
            removeList.add(dto);
          }
        }


        for (PowbEvidencePlannedStudyDTO i : removeList) {
          flagshipPlannedList.remove(i);
        }

      }
    } catch (Exception e) {

    }
  }

  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      inputStream = new ByteArrayInputStream(bytesDOC);
    }
    return inputStream;
  }

  public List<PowbExpenditureAreas> getPlannedBudgetAreas() {
    List<PowbExpenditureAreas> plannedBudgetAreasList = powbExpenditureAreasManager.findAll().stream()
      .filter(e -> e.isActive() && !e.getIsExpenditure()).collect(Collectors.toList());
    if (plannedBudgetAreasList != null) {
      return plannedBudgetAreasList;
    } else {
      return new ArrayList<>();
    }
  }

  /**
   * get the PMU institution
   * 
   * @param institution
   * @return
   */

  public PowbExpectedCrpProgress getPowbExpectedCrpProgressProgram(Long crpMilestoneID, Long crpProgramID) {
    List<PowbExpectedCrpProgress> powbExpectedCrpProgresses =
      powbExpectedCrpProgressManager.findByProgram(crpProgramID);
    List<PowbExpectedCrpProgress> powbExpectedCrpProgressMilestone = powbExpectedCrpProgresses.stream()
      .filter(c -> c.getCrpMilestone().getId().longValue() == crpMilestoneID.longValue() && c.isActive())
      .collect(Collectors.toList());
    if (!powbExpectedCrpProgressMilestone.isEmpty()) {
      return powbExpectedCrpProgressMilestone.get(0);
    }
    return new PowbExpectedCrpProgress();
  }


  public PowbFinancialPlannedBudget getPowbFinancialPlanBudget(Long plannedBudgetRelationID, Boolean isLiaison) {
    if (isLiaison) {
      LiaisonInstitution liaisonInstitution =
        liaisonInstitutionManager.getLiaisonInstitutionById(plannedBudgetRelationID);
      if (liaisonInstitution != null) {
        List<PowbFinancialPlannedBudget> powbFinancialPlannedBudgetList = powbSynthesisPMU
          .getPowbFinancialPlannedBudgetList().stream()
          .filter(
            p -> p.getLiaisonInstitution() != null && p.getLiaisonInstitution().getId().equals(plannedBudgetRelationID))
          .collect(Collectors.toList());
        if (powbFinancialPlannedBudgetList != null && !powbFinancialPlannedBudgetList.isEmpty()) {
          PowbFinancialPlannedBudget powbFinancialPlannedBudget = powbFinancialPlannedBudgetList.get(0);

          if (liaisonInstitution.getCrpProgram() != null) {
            powbFinancialPlannedBudget.setW1w2(liaisonInstitution.getCrpProgram().getW1());
            powbFinancialPlannedBudget.setW3Bilateral(liaisonInstitution.getCrpProgram().getW3());
            powbFinancialPlannedBudget.setCenterFunds(liaisonInstitution.getCrpProgram().getCenterFunds());
          }

          return powbFinancialPlannedBudget;
        } else {
          PowbFinancialPlannedBudget powbFinancialPlannedBudget = new PowbFinancialPlannedBudget();
          powbFinancialPlannedBudget.setLiaisonInstitution(liaisonInstitution);

          if (liaisonInstitution.getCrpProgram() != null) {
            powbFinancialPlannedBudget.setW1w2(new Double(liaisonInstitution.getCrpProgram().getW1()));
            powbFinancialPlannedBudget.setW3Bilateral(liaisonInstitution.getCrpProgram().getW3());
            powbFinancialPlannedBudget.setCenterFunds(liaisonInstitution.getCrpProgram().getCenterFunds());
          }

          return powbFinancialPlannedBudget;
        }
      } else {
        return null;
      }
    } else {
      PowbExpenditureAreas powbExpenditureArea =
        powbExpenditureAreasManager.getPowbExpenditureAreasById(plannedBudgetRelationID);

      if (powbExpenditureArea != null) {
        List<PowbFinancialPlannedBudget> powbFinancialPlannedBudgetList =
          powbSynthesisPMU.getPowbFinancialPlannedBudgetList().stream().filter(p -> p.getPowbExpenditureArea() != null
            && p.getPowbExpenditureArea().getId().equals(plannedBudgetRelationID)).collect(Collectors.toList());
        if (powbFinancialPlannedBudgetList != null && !powbFinancialPlannedBudgetList.isEmpty()) {
          PowbFinancialPlannedBudget powbFinancialPlannedBudget = powbFinancialPlannedBudgetList.get(0);
          if (powbExpenditureArea.getExpenditureArea().equals("CRP Management & Support Cost")) {
            this.loadPMU(powbExpenditureArea);
            powbFinancialPlannedBudget.setW1w2(powbExpenditureArea.getW1());
            powbFinancialPlannedBudget.setW3Bilateral(powbExpenditureArea.getW3());
            powbFinancialPlannedBudget.setCenterFunds(powbExpenditureArea.getCenterFunds());
          }
          return powbFinancialPlannedBudget;
        } else {

          PowbFinancialPlannedBudget powbFinancialPlannedBudget = new PowbFinancialPlannedBudget();
          powbFinancialPlannedBudget.setPowbExpenditureArea(powbExpenditureArea);
          if (powbExpenditureArea.getExpenditureArea().equals("CRP Management & Support Cost")) {
            this.loadPMU(powbExpenditureArea);
            powbFinancialPlannedBudget.setW1w2(powbExpenditureArea.getW1());
            powbFinancialPlannedBudget.setW3Bilateral(powbExpenditureArea.getW3());
            powbFinancialPlannedBudget.setCenterFunds(powbExpenditureArea.getCenterFunds());
          }
          return powbFinancialPlannedBudget;
        }
      } else {
        return null;
      }
    }
  }


  // Method to download link file
  public String getPowbPath(LiaisonInstitution liaisonInstitution, String actionName) {
    return config.getDownloadURL() + "/" + this.getPowbSourceFolder(liaisonInstitution, actionName).replace('\\', '/');
  }

  // Method to get the download folder
  private String getPowbSourceFolder(LiaisonInstitution liaisonInstitution, String actionName) {
    return APConstants.POWB_FOLDER.concat(File.separator).concat(this.getCrpSession()).concat(File.separator)
      .concat(liaisonInstitution.getAcronym()).concat(File.separator).concat(actionName.replace("/", "_"))
      .concat(File.separator);
  }

  public boolean isPMU(LiaisonInstitution institution) {
    if (institution.getAcronym().equals("PMU")) {
      return true;
    }
    return false;
  }


  public void loadFlagShipBudgetInfo(CrpProgram crpProgram) {
    List<ProjectFocus> projects =
      crpProgram.getProjectFocuses().stream().filter(c -> c.getProject().isActive() && c.isActive()
        && c.getPhase() != null && c.getPhase().equals(this.getSelectedPhase())).collect(Collectors.toList());
    Set<Project> myProjects = new HashSet();
    for (ProjectFocus projectFocus : projects) {
      Project project = projectFocus.getProject();
      if (project.isActive()) {
        project.setProjectInfo(project.getProjecInfoPhase(this.getSelectedPhase()));
        if (project.getProjectInfo() != null && project.getProjectInfo().getStatus() != null) {
          if (project.getProjectInfo().getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Ongoing.getStatusId())
            || project.getProjectInfo().getStatus().intValue() == Integer
              .parseInt(ProjectStatusEnum.Extended.getStatusId())) {
            myProjects.add(project);
          }
        }


      }
    }
    for (Project project : myProjects) {

      double w1 = project.getCoreBudget(this.getSelectedPhase().getYear(), this.getSelectedPhase());
      double w3 = project.getW3Budget(this.getSelectedPhase().getYear(), this.getSelectedPhase());
      double bilateral = project.getBilateralBudget(this.getSelectedPhase().getYear(), this.getSelectedPhase());
      List<ProjectBudgetsFlagship> budgetsFlagships = project.getProjectBudgetsFlagships().stream()
        .filter(c -> c.isActive() && c.getCrpProgram().getId().longValue() == crpProgram.getId().longValue()
          && c.getPhase().equals(this.getSelectedPhase()) && c.getYear() == this.getSelectedPhase().getYear())
        .collect(Collectors.toList());
      double percentageW1 = 0;
      double percentageW3 = 0;
      double percentageB = 0;

      if (!this.getCountProjectFlagships(project.getId())) {
        percentageW1 = 100;
        percentageW3 = 100;
        percentageB = 100;

      }
      for (ProjectBudgetsFlagship projectBudgetsFlagship : budgetsFlagships) {
        switch (projectBudgetsFlagship.getBudgetType().getId().intValue()) {
          case 1:
            percentageW1 = percentageW1 + projectBudgetsFlagship.getAmount();
            break;
          case 2:
            percentageW3 = percentageW3 + projectBudgetsFlagship.getAmount();
            break;
          case 3:
            percentageB = percentageB + projectBudgetsFlagship.getAmount();
            break;
          default:
            break;
        }
      }
      w1 = w1 * (percentageW1) / 100;
      w3 = w3 * (percentageW3) / 100;
      bilateral = bilateral * (percentageB) / 100;
      crpProgram.setW1(crpProgram.getW1() + w1);
      crpProgram.setW3(crpProgram.getW3() + w3 + bilateral);


    }
  }


  public void loadPMU(PowbExpenditureAreas liaisonInstitution) {

    Set<Project> myProjects = new HashSet();
    for (GlobalUnitProject projectFocus : this.getLoggedCrp().getGlobalUnitProjects().stream()
      .filter(c -> c.isActive() && c.isOrigin()).collect(Collectors.toList())) {
      Project project = projectFocus.getProject();
      if (project.isActive()) {
        project.setProjectInfo(project.getProjecInfoPhase(this.getSelectedPhase()));
        if (project.getProjectInfo() != null && project.getProjectInfo().getStatus() != null) {
          if (project.getProjectInfo().getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Ongoing.getStatusId())
            || project.getProjectInfo().getStatus().intValue() == Integer
              .parseInt(ProjectStatusEnum.Extended.getStatusId())) {
            if (project.getProjecInfoPhase(this.getSelectedPhase()).getAdministrative() != null
              && project.getProjecInfoPhase(this.getSelectedPhase()).getAdministrative().booleanValue()) {
              myProjects.add(project);
            }

          }
        }
      }
    }
    for (Project project : myProjects) {

      double w1 = project.getCoreBudget(this.getSelectedPhase().getYear(), this.getSelectedPhase());
      double w3 = project.getW3Budget(this.getSelectedPhase().getYear(), this.getSelectedPhase());
      double bilateral = project.getBilateralBudget(this.getSelectedPhase().getYear(), this.getSelectedPhase());
      double centerFunds = project.getCenterBudget(this.getSelectedPhase().getYear(), this.getSelectedPhase());

      double percentageW1 = 0;
      double percentageW3 = 0;
      double percentageB = 0;
      double percentageCenterFunds = 0;


      percentageW1 = 100;
      percentageW3 = 100;
      percentageB = 100;
      percentageCenterFunds = 100;


      w1 = w1 * (percentageW1) / 100;
      w3 = w3 * (percentageW3) / 100;
      bilateral = bilateral * (percentageB) / 100;
      centerFunds = centerFunds * (percentageCenterFunds) / 100;

      liaisonInstitution.setW1(liaisonInstitution.getW1() + w1);
      liaisonInstitution.setW3(liaisonInstitution.getW3() + w3 + bilateral);
      liaisonInstitution.setCenterFunds(liaisonInstitution.getCenterFunds() + centerFunds);

    }
  }

  public void loadTablePMU() {
    flagships = this.getLoggedCrp().getCrpPrograms().stream()
      .filter(c -> c.isActive() && c.getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList());
    flagships.sort((p1, p2) -> p1.getAcronym().compareTo(p2.getAcronym()));

    for (CrpProgram crpProgram : flagships) {
      crpProgram.setMilestones(new ArrayList<>());
      crpProgram.setW1(new Double(0));
      crpProgram.setW3(new Double(0));

      crpProgram.setOutcomes(crpProgram.getCrpProgramOutcomes().stream()
        .filter(c -> c.isActive() && c.getPhase().equals(this.getSelectedPhase())).collect(Collectors.toList()));
      List<CrpProgramOutcome> validOutcomes = new ArrayList<>();
      for (CrpProgramOutcome crpProgramOutcome : crpProgram.getOutcomes()) {

        crpProgramOutcome.setMilestones(crpProgramOutcome.getCrpMilestones().stream()
          .filter(c -> c.isActive() && c.getYear().intValue() == this.getSelectedPhase().getYear())
          .collect(Collectors.toList()));
        crpProgramOutcome.setSubIdos(
          crpProgramOutcome.getCrpOutcomeSubIdos().stream().filter(c -> c.isActive()).collect(Collectors.toList()));
        crpProgram.getMilestones().addAll(crpProgramOutcome.getMilestones());
        /* Change requested by htobon: Show outcomes without milestones for table A1 */
        // if (!crpProgram.getMilestones().isEmpty()) {
        validOutcomes.add(crpProgramOutcome);
        // }
      }
      crpProgram.setOutcomes(validOutcomes);
      this.loadFlagShipBudgetInfo(crpProgram);

    }
  }

  @Override
  public void prepare() {
    this.setGeneralParameters();
    powbSynthesisList =
      this.getSelectedPhase().getPowbSynthesis().stream().filter(ps -> ps.isActive()).collect(Collectors.toList());

    List<PowbSynthesis> powbSynthesisPMUList = powbSynthesisList.stream()
      .filter(p -> p.isActive() && p.getLiaisonInstitution().equals(pmuInstitution)).collect(Collectors.toList());
    if (powbSynthesisPMUList != null && !powbSynthesisPMUList.isEmpty()) {
      powbSynthesisPMU = powbSynthesisPMUList.get(0);
    }

    reportSysthesisList =
      this.getSelectedPhase().getReportSynthesis().stream().filter(ps -> ps.isActive()).collect(Collectors.toList());

    System.out.println("test");
    LiaisonInstitution pmuInstitution = this.getLoggedCrp().getLiaisonInstitutions().stream()
      .filter(c -> c.getCrpProgram() == null && c.getAcronym().equals("PMU")).collect(Collectors.toList()).get(0);

    reportSynthesisPMU = reportSynthesisManager.findSynthesis(this.getSelectedPhase().getId(), pmuInstitution.getId());

    List<ReportSynthesis> reportSysthesisPMUList = reportSysthesisList.stream()
      .filter(p -> p.isActive() && p.getLiaisonInstitution().equals(pmuInstitution)).collect(Collectors.toList());
    if (reportSysthesisPMUList != null && !reportSysthesisPMUList.isEmpty()) {
      reportSynthesisPMU = reportSysthesisPMUList.get(0);
    }

    // Calculate time to generate report
    startTime = System.currentTimeMillis();
    LOG.info(
      "Start report download: " + this.getFileName() + ". User: " + this.getCurrentUser().getComposedCompleteName()
        + ". CRP: " + this.getLoggedCrp().getAcronym() + ". Cycle: " + this.getSelectedCycle());
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  /**
   * List all the deliverables of the Crp to make the calculations in the Cross Cutting Socores.
   * 
   * @param pashe - The phase that get the deliverable information.
   */
  public void tableCInfo(Phase phase) {
    List<Deliverable> deliverables = new ArrayList<>();
    deliverableList = new ArrayList<>();
    int iGenderPrincipal = 0;
    int iGenderSignificant = 0;
    int iGenderNa = 0;
    int iYouthPrincipal = 0;
    int iYouthSignificant = 0;
    int iYouthNa = 0;
    int iCapDevPrincipal = 0;
    int iCapDevSignificant = 0;
    int iCapDevNa = 0;


    for (GlobalUnitProject globalUnitProject : this.getLoggedCrp().getGlobalUnitProjects().stream()
      .filter(p -> p.isActive() && p.getProject() != null && p.getProject().isActive()
        && (p.getProject().getProjecInfoPhase(phase) != null
          && p.getProject().getProjectInfo().getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Ongoing.getStatusId())
          || p.getProject().getProjecInfoPhase(phase) != null && p.getProject().getProjectInfo().getStatus()
            .intValue() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())))
      .collect(Collectors.toList())) {

      for (Deliverable deliverable : globalUnitProject.getProject().getDeliverables().stream().filter(d -> d.isActive()
        && d.getDeliverableInfo(phase) != null
        && ((d.getDeliverableInfo().getStatus() == null && d.getDeliverableInfo().getYear() == phase.getYear())
          || (d.getDeliverableInfo().getStatus() != null
            && d.getDeliverableInfo().getStatus()
              .intValue() == Integer.parseInt(ProjectStatusEnum.Extended.getStatusId())
            && d.getDeliverableInfo().getNewExpectedYear() != null
            && d.getDeliverableInfo().getNewExpectedYear() == phase.getYear())
          || (d.getDeliverableInfo().getStatus() != null && d.getDeliverableInfo().getYear() == phase.getYear() && d
            .getDeliverableInfo().getStatus().intValue() == Integer.parseInt(ProjectStatusEnum.Ongoing.getStatusId()))))
        .collect(Collectors.toList())) {
        deliverables.add(deliverable);
      }

    }


    if (deliverables != null && !deliverables.isEmpty()) {
      for (Deliverable deliverable : deliverables) {
        DeliverableInfo deliverableInfo = deliverable.getDeliverableInfo(phase);
        if (deliverableInfo.isActive()) {
          deliverableList.add(deliverableInfo);
          boolean bGender = false;
          boolean bYouth = false;
          boolean bCapDev = false;
          if (deliverableInfo.getCrossCuttingNa() != null && deliverableInfo.getCrossCuttingNa()) {
            iGenderNa++;
            iYouthNa++;
            iCapDevNa++;
          } else {
            // Gender
            if (deliverableInfo.getCrossCuttingGender() != null && deliverableInfo.getCrossCuttingGender()) {
              bGender = true;
              if (deliverableInfo.getCrossCuttingScoreGender() != null
                && deliverableInfo.getCrossCuttingScoreGender() == 1) {
                iGenderSignificant++;
              } else if (deliverableInfo.getCrossCuttingScoreGender() != null
                && deliverableInfo.getCrossCuttingScoreGender() == 2) {
                iGenderPrincipal++;
              } else if (deliverableInfo.getCrossCuttingScoreGender() == null) {
                iGenderNa++;
              }
            }

            // Youth
            if (deliverableInfo.getCrossCuttingYouth() != null && deliverableInfo.getCrossCuttingYouth()) {
              bYouth = true;
              if (deliverableInfo.getCrossCuttingScoreYouth() != null
                && deliverableInfo.getCrossCuttingScoreYouth() == 1) {
                iYouthSignificant++;
              } else if (deliverableInfo.getCrossCuttingScoreYouth() != null
                && deliverableInfo.getCrossCuttingScoreYouth() == 2) {
                iYouthPrincipal++;
              } else if (deliverableInfo.getCrossCuttingScoreYouth() == null) {
                iYouthNa++;
              }
            }

            // CapDev
            if (deliverableInfo.getCrossCuttingCapacity() != null && deliverableInfo.getCrossCuttingCapacity()) {
              bCapDev = true;
              if (deliverableInfo.getCrossCuttingScoreCapacity() != null
                && deliverableInfo.getCrossCuttingScoreCapacity() == 1) {
                iCapDevSignificant++;
              } else if (deliverableInfo.getCrossCuttingScoreCapacity() != null
                && deliverableInfo.getCrossCuttingScoreCapacity() == 2) {
                iCapDevPrincipal++;
              } else if (deliverableInfo.getCrossCuttingScoreCapacity() == null) {
                iCapDevNa++;
              }
            }

            if (!bGender) {
              iGenderNa++;
            }
            if (!bYouth) {
              iYouthNa++;
            }
            if (!bCapDev) {
              iCapDevNa++;
            }
          }
        }
      }
      tableC = new CrossCuttingDimensionTableDTO();
      int iDeliverableCount = deliverableList.size();

      tableC.setTotal(iDeliverableCount);

      double dGenderPrincipal = (iGenderPrincipal * 100.0) / iDeliverableCount;
      double dGenderSignificant = (iGenderSignificant * 100.0) / iDeliverableCount;
      double dGenderNa = (iGenderNa * 100.0) / iDeliverableCount;
      double dYouthPrincipal = (iYouthPrincipal * 100.0) / iDeliverableCount;
      double dYouthSignificant = (iYouthSignificant * 100.0) / iDeliverableCount;
      double dYouthNa = (iYouthNa * 100.0) / iDeliverableCount;
      double dCapDevPrincipal = (iCapDevPrincipal * 100.0) / iDeliverableCount;
      double dCapDevSignificant = (iCapDevSignificant * 100.0) / iDeliverableCount;
      double dCapDevNa = (iCapDevNa * 100.0) / iDeliverableCount;


      // Gender
      tableC.setGenderPrincipal(iGenderPrincipal);
      tableC.setGenderSignificant(iGenderSignificant);
      tableC.setGenderScored(iGenderNa);

      tableC.setPercentageGenderPrincipal(dGenderPrincipal);
      tableC.setPercentageGenderSignificant(dGenderSignificant);
      tableC.setPercentageGenderNotScored(dGenderNa);
      // Youth
      tableC.setYouthPrincipal(iYouthPrincipal);
      tableC.setYouthSignificant(iYouthSignificant);
      tableC.setYouthScored(iYouthNa);

      tableC.setPercentageYouthPrincipal(dYouthPrincipal);
      tableC.setPercentageYouthSignificant(dYouthSignificant);
      tableC.setPercentageYouthNotScored(dYouthNa);
      // CapDev
      tableC.setCapDevPrincipal(iCapDevPrincipal);
      tableC.setCapDevSignificant(iCapDevSignificant);
      tableC.setCapDevScored(iCapDevNa);

      tableC.setPercentageCapDevPrincipal(dCapDevPrincipal);
      tableC.setPercentageCapDevSignificant(dCapDevSignificant);
      tableC.setPercentageCapDevNotScored(dCapDevNa);


    }

  }


}
