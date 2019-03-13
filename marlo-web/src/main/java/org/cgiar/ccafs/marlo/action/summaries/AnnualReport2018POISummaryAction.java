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

import org.cgiar.ccafs.marlo.data.manager.CrpProgramManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.PowbExpenditureAreasManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndSynthesisIndicatorManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisCrossCgiarCollaborationManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisCrossCuttingDimensionManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisCrpProgressManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisCrpProgressTargetManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisExternalPartnershipManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisFinancialSummaryBudgetManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisFlagshipProgressManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisFlagshipProgressMilestoneManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisMeliaManager;
import org.cgiar.ccafs.marlo.data.manager.ReportSynthesisSrfProgressTargetManager;
import org.cgiar.ccafs.marlo.data.model.CrossCuttingDimensionTableDTO;
import org.cgiar.ccafs.marlo.data.model.CrpMilestone;
import org.cgiar.ccafs.marlo.data.model.CrpOutcomeSubIdo;
import org.cgiar.ccafs.marlo.data.model.CrpPpaPartner;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcome;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.LiaisonInstitution;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.PowbEvidencePlannedStudyDTO;
import org.cgiar.ccafs.marlo.data.model.PowbExpenditureAreas;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectBudgetsFlagship;
import org.cgiar.ccafs.marlo.data.model.ProjectExpectedStudyInfo;
import org.cgiar.ccafs.marlo.data.model.ProjectFocus;
import org.cgiar.ccafs.marlo.data.model.ProjectInnovationInfo;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.data.model.RepIndSynthesisIndicator;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesis;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisCrossCgiarCollaboration;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisCrossCuttingAssetDTO;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisCrossCuttingDimension;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisCrossCuttingInnovationDTO;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisExternalPartnership;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisExternalPartnershipDTO;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFinancialSummary;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFinancialSummaryBudget;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFlagshipProgress;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFlagshipProgressMilestone;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFundingUseExpendituryArea;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisFundingUseSummary;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisGovernance;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisIndicator;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisMeliaEvaluation;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisProgramVariance;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisRisk;
import org.cgiar.ccafs.marlo.data.model.ReportSynthesisSrfProgressTarget;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.POIField;
import org.cgiar.ccafs.marlo.utils.POISummary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnualReport2018POISummaryAction extends BaseSummariesAction implements Summary {

  private static final long serialVersionUID = 2828551630719082089L;
  // private static final String ProgramType = null;
  private static Logger LOG = LoggerFactory.getLogger(AnnualReport2018POISummaryAction.class);

  private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

    CTStyle ctStyle = CTStyle.Factory.newInstance();

    ctStyle.setStyleId(strStyleId);

    CTString styleName = CTString.Factory.newInstance();
    styleName.setVal(strStyleId);
    ctStyle.setName(styleName);

    CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
    indentNumber.setVal(BigInteger.valueOf(headingLevel));

    // lower number > style is more prominent in the formats bar
    ctStyle.setUiPriority(indentNumber);

    CTOnOff onoffnull = CTOnOff.Factory.newInstance();
    ctStyle.setUnhideWhenUsed(onoffnull);

    // style shows up in the formats bar
    ctStyle.setQFormat(onoffnull);

    // style defines a heading of the given level
    CTPPr ppr = CTPPr.Factory.newInstance();
    ppr.setOutlineLvl(indentNumber);
    ctStyle.setPPr(ppr);

    XWPFStyle style = new XWPFStyle(ctStyle);

    // is a null op if already defined
    XWPFStyles styles = docxDocument.createStyles();

    style.setType(STStyleType.PARAGRAPH);
    styles.addStyle(style);
  }

  public static double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  // Managers
  private PowbExpenditureAreasManager powbExpenditureAreasManager;
  private ReportSynthesisManager reportSynthesisManager;
  private ReportSynthesisSrfProgressTargetManager reportSynthesisSrfProgressTargetManager;
  private RepIndSynthesisIndicatorManager repIndSynthesisIndicatorManager;
  private ReportSynthesisExternalPartnershipManager reportSynthesisExternalPartnershipManager;
  private ReportSynthesisMeliaManager reportSynthesisMeliaManager;
  private ReportSynthesisCrossCgiarCollaborationManager reportSynthesisCrossCgiarCollaborationManager;
  private ReportSynthesisFlagshipProgressManager reportSynthesisFlagshipProgressManager;
  private ReportSynthesisFinancialSummaryBudgetManager reportSynthesisFinancialSummaryBudgetManager;
  private ReportSynthesisFlagshipProgressMilestoneManager reportSynthesisFlagshipProgressMilestoneManager;
  private ReportSynthesisCrossCuttingDimensionManager reportSynthesisCrossCuttingDimensionManager;
  private ReportSynthesisCrpProgressManager reportSynthesisCrpProgressManager;
  private CrpProgramManager crpProgramManager;


  // Parameters
  private POISummary poiSummary;
  private LiaisonInstitution pmuInstitution;
  private ReportSynthesis reportSynthesisPMU;
  private long startTime;
  private XWPFDocument document;
  private CrossCuttingDimensionTableDTO tableC;
  private NumberFormat currencyFormat;
  private DecimalFormat percentageFormat;
  private List<CrpProgram> flagships;
  private List<PowbEvidencePlannedStudyDTO> flagshipPlannedList;
  private List<LiaisonInstitution> flagshipLiaisonInstitutions;
  private List<ReportSynthesisCrossCuttingInnovationDTO> flagshipPlannedInnovations;


  Double totalw1w2 = 0.0, totalw1w2Planned = 0.0, totalCenter = 0.0, grandTotal = 0.0, totalw1w2Actual = 0.0,
    totalW3Actual = 0.0, totalW3Bilateral = 0.0, totalW3Planned = 0.0, grandTotalPlanned = 0.0, grandTotalActual = 0.0;
  // Streams
  private InputStream inputStream;

  // DOC bytes
  private byte[] bytesDOC;

  public AnnualReport2018POISummaryAction(APConfig config, GlobalUnitManager crpManager, PhaseManager phaseManager,
    PowbExpenditureAreasManager powbExpenditureAreasManager, ReportSynthesisManager reportSynthesisManager,
    ReportSynthesisCrpProgressTargetManager reportSynthesisCrpProgressTargetManager,
    RepIndSynthesisIndicatorManager repIndSynthesisIndicatorManager, ProjectManager projectManager,
    ReportSynthesisExternalPartnershipManager reportSynthesisExternalPartnershipManager,
    ReportSynthesisMeliaManager reportSynthesisMeliaManager,
    ReportSynthesisCrossCgiarCollaborationManager reportSynthesisCrossCgiarCollaborationManager,
    ReportSynthesisFlagshipProgressManager reportSynthesisFlagshipProgressManager,
    ReportSynthesisFinancialSummaryBudgetManager reportSynthesisFinancialSummaryBudgetManager,
    ReportSynthesisFlagshipProgressMilestoneManager reportSynthesisFlagshipProgressMilestoneManager,
    ReportSynthesisCrossCuttingDimensionManager reportSynthesisCrossCuttingDimensionManager,
    ReportSynthesisCrpProgressManager reportSynthesisCrpProgressManager,
    ReportSynthesisSrfProgressTargetManager reportSynthesisSrfProgressTargetManager,
    CrpProgramManager crpProgramManager) {
    super(config, crpManager, phaseManager, projectManager);
    document = new XWPFDocument();
    poiSummary = new POISummary();
    currencyFormat = NumberFormat.getCurrencyInstance();
    percentageFormat = new DecimalFormat("##.##%");
    this.powbExpenditureAreasManager = powbExpenditureAreasManager;
    this.reportSynthesisManager = reportSynthesisManager;
    this.reportSynthesisSrfProgressTargetManager = reportSynthesisSrfProgressTargetManager;
    this.repIndSynthesisIndicatorManager = repIndSynthesisIndicatorManager;
    this.reportSynthesisExternalPartnershipManager = reportSynthesisExternalPartnershipManager;
    this.reportSynthesisMeliaManager = reportSynthesisMeliaManager;
    this.reportSynthesisCrossCgiarCollaborationManager = reportSynthesisCrossCgiarCollaborationManager;
    this.reportSynthesisFlagshipProgressManager = reportSynthesisFlagshipProgressManager;
    this.reportSynthesisFinancialSummaryBudgetManager = reportSynthesisFinancialSummaryBudgetManager;
    this.reportSynthesisFlagshipProgressMilestoneManager = reportSynthesisFlagshipProgressMilestoneManager;
    this.reportSynthesisCrossCuttingDimensionManager = reportSynthesisCrossCuttingDimensionManager;
    this.reportSynthesisCrpProgressManager = reportSynthesisCrpProgressManager;
    this.crpProgramManager = crpProgramManager;
  }

  private void addAdjustmentDescription() {
    List<ReportSynthesisFlagshipProgress> reportSynthesisFlagshipProgressManagerList =
      reportSynthesisFlagshipProgressManager.findAll().stream().filter(fp -> fp.isActive()
        && fp.getReportSynthesis().isActive() && fp.getReportSynthesis().getPhase().equals(this.getSelectedPhase()))
        .collect(Collectors.toList());

    if (reportSynthesisFlagshipProgressManagerList != null && !reportSynthesisFlagshipProgressManagerList.isEmpty()) {

      for (ReportSynthesisFlagshipProgress reportSynthesisFlagshipProgress : reportSynthesisFlagshipProgressManagerList) {
        String acronym = "", summary = "";
        if (reportSynthesisFlagshipProgress.getReportSynthesis().getLiaisonInstitution().getAcronym() != null) {
          acronym = reportSynthesisFlagshipProgress.getReportSynthesis().getLiaisonInstitution().getAcronym();
        }
        if (reportSynthesisFlagshipProgress.getSummary() != null
          && !reportSynthesisFlagshipProgress.getSummary().isEmpty()) {
          summary = acronym + ": " + reportSynthesisFlagshipProgress.getSummary() + "\n";
          poiSummary.textParagraph(document.createParagraph(), summary);
        }
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
          crossCuttingCapacityDevelopment = crossCutting.getCapDevKeyAchievements();
        }
      }
    }

    if (crossCuttingCapacityDevelopment != null && !crossCuttingCapacityDevelopment.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingCapacityDevelopment);
    }
  }

  private void addCrossCuttingClimateChange() {
    String crossCuttingClimateChange = "";
    if (reportSynthesisPMU != null) {
      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingClimateChange = crossCutting.getClimateChangeKeyAchievements();
        }
      }
    }

    if (crossCuttingClimateChange != null && !crossCuttingClimateChange.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), crossCuttingClimateChange);
    }
  }

  private void addCrossCuttingGender() {

    String crossCuttingGenderResearchFindings = "";
    String crossCuttingGenderLearned = "";
    String crossCuttingGenderProblemsArimes = "";
    if (reportSynthesisPMU != null) {
      // Cross Cutting Gender Info
      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingGenderResearchFindings = crossCutting.getGenderResearchFindings();
          crossCuttingGenderLearned = crossCutting.getGenderLearned();
          crossCuttingGenderProblemsArimes = crossCutting.getGenderProblemsArisen();
        }
      }
    }
    if (crossCuttingGenderResearchFindings != null && !crossCuttingGenderResearchFindings.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingGenderResearchFindings);
      poiSummary.convertHTMLTags(document, crossCuttingGenderResearchFindings);
    }

    if (crossCuttingGenderLearned != null && !crossCuttingGenderLearned.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingGenderLearned);
      poiSummary.convertHTMLTags(document, crossCuttingGenderLearned);

    }

    if (crossCuttingGenderProblemsArimes != null && !crossCuttingGenderProblemsArimes.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingGenderProblemsArimes);
      poiSummary.convertHTMLTags(document, crossCuttingGenderProblemsArimes);
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
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingIntellectualAssets);
      poiSummary.convertHTMLTags(document, crossCuttingIntellectualAssets);

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
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingOpenData);
      poiSummary.convertHTMLTags(document, crossCuttingOpenData);
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
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingOtherAspects);
      poiSummary.convertHTMLTags(document, crossCuttingOtherAspects);

    }

  }

  private void addCrossCuttingYouth() {

    String crossCuttingYouthContribution = "";
    String crossCuttingYouthResearchFindings = "";
    String crossCuttingYouthLearned = "";
    String crossCuttingYouthProblemsArisen = "";
    if (reportSynthesisPMU != null) {

      // Cross Cutting Gender Info
      if (reportSynthesisPMU.getReportSynthesisCrossCuttingDimension() != null) {
        ReportSynthesisCrossCuttingDimension crossCutting =
          reportSynthesisPMU.getReportSynthesisCrossCuttingDimension();
        if (crossCutting != null) {
          crossCuttingYouthContribution = crossCutting.getYouthDescription();
          crossCuttingYouthResearchFindings = crossCutting.getYouthLessons();
          crossCuttingYouthLearned = crossCutting.getYouthLessons();
          crossCuttingYouthProblemsArisen = crossCutting.getYouthLessons();
        }
      }
    }
    if (crossCuttingYouthContribution != null && !crossCuttingYouthContribution.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingYouthContribution);
      poiSummary.convertHTMLTags(document, crossCuttingYouthContribution);
    }

    if (crossCuttingYouthResearchFindings != null && !crossCuttingYouthResearchFindings.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingYouthResearchFindings);
      poiSummary.convertHTMLTags(document, crossCuttingYouthResearchFindings);
    }
    if (crossCuttingYouthLearned != null && !crossCuttingYouthLearned.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingYouthLearned);
      poiSummary.convertHTMLTags(document, crossCuttingYouthLearned);
    }

    if (crossCuttingYouthProblemsArisen != null && !crossCuttingYouthProblemsArisen.isEmpty()) {
      // poiSummary.textParagraph(document.createParagraph(), crossCuttingYouthProblemsArisen);
      poiSummary.convertHTMLTags(document, crossCuttingYouthProblemsArisen);
    }
  }


  private void addCrossPartnerships() {
    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisCrossCgiar() != null
        && reportSynthesisPMU.getReportSynthesisCrossCgiar().getHighlights() != null) {
        // poiSummary.textParagraph(document.createParagraph(),
        // reportSynthesisPMU.getReportSynthesisCrossCgiar().getHighlights());
        poiSummary.convertHTMLTags(document, reportSynthesisPMU.getReportSynthesisCrossCgiar().getHighlights());
      }
    }
  }

  private void addCrpProgressOutcomes() {
    if (reportSynthesisPMU != null && reportSynthesisPMU.getReportSynthesisCrpProgress() != null
      && reportSynthesisPMU.getReportSynthesisCrpProgress().getSummaries() != null) {
      String synthesisCrpSummaries = reportSynthesisPMU.getReportSynthesisCrpProgress().getSummaries() != null
        ? reportSynthesisPMU.getReportSynthesisCrpProgress().getSummaries() : "";
      poiSummary.textParagraph(document.createParagraph(), synthesisCrpSummaries);
    }
  }

  private void addExpectedCrp() {
    if (reportSynthesisPMU != null && reportSynthesisPMU.getReportSynthesisSrfProgress() != null
      && reportSynthesisPMU.getReportSynthesisSrfProgress().getSummary() != null) {
      String synthesisCrpDescription = reportSynthesisPMU.getReportSynthesisSrfProgress().getSummary() != null
        ? reportSynthesisPMU.getReportSynthesisSrfProgress().getSummary() : "";
      poiSummary.textParagraph(document.createParagraph(), synthesisCrpDescription);
    }
  }

  private void addExternalPartnerships() {

    String keyExternal = "";

    if (reportSynthesisPMU != null) {

      if (reportSynthesisPMU.getReportSynthesisExternalPartnership() != null) {
        ReportSynthesisExternalPartnership externalPartnership =
          reportSynthesisPMU.getReportSynthesisExternalPartnership();
        if (externalPartnership != null) {
          keyExternal = externalPartnership.getHighlights();
        }
      }
    }

    if (keyExternal != null && !keyExternal.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), keyExternal);
    }

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

  private void addFundingSummarize() {
    String brieflySummarize = "";
    if (reportSynthesisPMU != null) {
      if (reportSynthesisPMU.getReportSynthesisFundingUseSummary() != null) {
        ReportSynthesisFundingUseSummary funding = reportSynthesisPMU.getReportSynthesisFundingUseSummary();
        if (funding != null) {
          brieflySummarize = funding.getInterestingPoints();
        }
      }
    }

    if (brieflySummarize != null && !brieflySummarize.isEmpty()) {
      poiSummary.textParagraph(document.createParagraph(), brieflySummarize);
    }
  }

  private void addImprovingEfficiency() {
    if (reportSynthesisPMU != null) {
      if (reportSynthesisPMU.getReportSynthesisEfficiency() != null
        && reportSynthesisPMU.getReportSynthesisEfficiency().getDescription() != null) {
        poiSummary.textParagraph(document.createParagraph(),
          reportSynthesisPMU.getReportSynthesisEfficiency().getDescription());
      }
    }
  }

  public void addIntellectualAssets() {
    if (reportSynthesisPMU != null) {
      if (reportSynthesisPMU.getReportSynthesisIntellectualAsset() != null
        && reportSynthesisPMU.getReportSynthesisIntellectualAsset().getManaged() != null
        && reportSynthesisPMU.getReportSynthesisIntellectualAsset().getPatents() != null
        && reportSynthesisPMU.getReportSynthesisIntellectualAsset().getCriticalIssues() != null) {
        poiSummary.textParagraph(document.createParagraph(),
          reportSynthesisPMU.getReportSynthesisIntellectualAsset().getManaged());
        poiSummary.textParagraph(document.createParagraph(),
          reportSynthesisPMU.getReportSynthesisIntellectualAsset().getPatents());
        poiSummary.textParagraph(document.createParagraph(),
          reportSynthesisPMU.getReportSynthesisIntellectualAsset().getCriticalIssues());

      }
    }
  }

  private void addManagement() {
    poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.management.governance"));
    this.addManagementGovernance();
    // poiSummary.textParagraph(document.createParagraph(), CRPManagementGovernanceDescription);

    poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.management.risk"));
    this.addManagementRisks();

    // poiSummary.textParagraph(document.createParagraph(), managementRisksTitleDescription);
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
      .sorted((c1, c2) -> c1.getInstitution().getAcronymName().compareTo(c2.getInstitution().getAcronymName()))
      .collect(Collectors.toList());
    if (crpPpaPartnerList != null && !crpPpaPartnerList.isEmpty()) {
      for (CrpPpaPartner crpPpaPartner : crpPpaPartnerList) {
        if (participantingCenters.isEmpty()) {
          participantingCenters = crpPpaPartner.getInstitution().getAcronymName();
        } else {
          participantingCenters += ", " + crpPpaPartner.getInstitution().getAcronymName();
        }
      }
    }
    poiSummary.textParagraph(document.createParagraph(),
      this.getText("summaries.annualReport.participantingCenters") + ": " + participantingCenters + ":");
  }

  public void addReportSynthesisMelia() {
    if (reportSynthesisPMU != null) {
      if (reportSynthesisPMU.getReportSynthesisMelia() != null
        && reportSynthesisPMU.getReportSynthesisMelia().getSummary() != null) {
        poiSummary.textParagraph(document.createParagraph(), reportSynthesisPMU.getReportSynthesisMelia().getSummary());
      }
    }
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

  public void createPageFooter() {
    CTP ctp = CTP.Factory.newInstance();

    // this add page number incremental
    ctp.addNewR().addNewPgNum();

    XWPFParagraph codePara = new XWPFParagraph(ctp, document);
    XWPFParagraph[] paragraphs = new XWPFParagraph[1];
    paragraphs[0] = codePara;

    // position of number
    codePara.setAlignment(ParagraphAlignment.CENTER);

    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();

    try {
      XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
      headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, paragraphs);
    } catch (IOException e) {
      LOG.error("Failed to createFooter. Exception: " + e.getMessage());
    }
  }


  public void createTableA1() {
    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader =
      {new POIField(this.getText("annualReport.crpProgress.selectSLOTarget"), ParagraphAlignment.LEFT),
        new POIField(this.getText("annualReport.crpProgress.summaryNewEvidence.readText"), ParagraphAlignment.LEFT),
        new POIField(this.getText("annualReport.crpProgress.additionalContribution"), ParagraphAlignment.LEFT),};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);


    /*
     * Get all crp Progress Targets and compare the slo indicador Target id with the actual slotarget id
     */

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    data = new ArrayList<>();
    // Table A-1 Evidence on Progress

    List<ReportSynthesisSrfProgressTarget> listSrfProgressTargets =
      reportSynthesisSrfProgressTargetManager.findAll().stream().filter(t -> t.isActive()).collect(Collectors.toList());
    if (listSrfProgressTargets != null && !listSrfProgressTargets.isEmpty()) {
      listSrfProgressTargets = listSrfProgressTargets.stream().filter(l -> l.getReportSynthesisSrfProgress().getId()
        .equals(reportSynthesisPMU.getReportSynthesisSrfProgress().getId())).collect(Collectors.toList());
      for (ReportSynthesisSrfProgressTarget reportSynthesisSrfProgressTarget : listSrfProgressTargets.stream()
        .filter(c -> c.getSrfSloIndicatorTarget().getTargetsIndicator() != null)
        .sorted((c1, c2) -> c1.getSrfSloIndicatorTarget().getTargetsIndicator()
          .compareTo(c2.getSrfSloIndicatorTarget().getTargetsIndicator()))
        .collect(Collectors.toList())) {
        String sloTarget = "", briefSummaries = "", additionalContribution = "";
        if (reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget() != null) {
          if (reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget().getTargetsIndicator() != null
            && !reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget().getTargetsIndicator().isEmpty()) {
            sloTarget = reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget().getTargetsIndicator();
          }
          if (reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget().getNarrative() != null
            && !reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget().getNarrative().isEmpty()) {
            sloTarget += " " + reportSynthesisSrfProgressTarget.getSrfSloIndicatorTarget().getNarrative();
          }
        }

        briefSummaries = reportSynthesisSrfProgressTarget.getBirefSummary() != null
          ? reportSynthesisSrfProgressTarget.getBirefSummary() : "";
        additionalContribution = reportSynthesisSrfProgressTarget.getAdditionalContribution() != null
          ? reportSynthesisSrfProgressTarget.getAdditionalContribution() : "";

        Boolean bold = false;
        String blackColor = "000000";
        String blueColor = "000099";
        POIField[] sData = {new POIField(sloTarget, ParagraphAlignment.LEFT, bold, blackColor),
          new POIField(briefSummaries, ParagraphAlignment.LEFT, bold, blueColor),
          new POIField(additionalContribution, ParagraphAlignment.LEFT, bold, blueColor)};
        data = Arrays.asList(sData);
        datas.add(data);
      }
    }

    poiSummary.textTable(document, headers, datas, true, "tableA1AnnualReport");
  }

  private void createTableA2() {

    /*
     * Get all Progress Expected study info and compare the actual phase
     */

    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader = {new POIField(this.getText("summaries.annualReport.tableA2.field1"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableA2.field2"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableA2.field3"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableA2.field4"), ParagraphAlignment.LEFT)};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);


    List<List<POIField>> datas = new ArrayList<>();;
    List<POIField> data;
    // Get liaison institution list
    List<LiaisonInstitution> liaisonInstitutionsList =
      new ArrayList<>(this.getLoggedCrp().getLiaisonInstitutions().stream()
        .filter(c -> c.getCrpProgram() != null && c.isActive()
          && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
        .collect(Collectors.toList()));
    liaisonInstitutionsList.sort(Comparator.comparing(LiaisonInstitution::getAcronym));
    flagshipPlannedList = reportSynthesisCrpProgressManager.getPlannedList(liaisonInstitutionsList,
      this.getSelectedPhase().getId(), this.getLoggedCrp(), pmuInstitution);

    if (flagshipPlannedList != null && !flagshipPlannedList.isEmpty()) {
      for (PowbEvidencePlannedStudyDTO powbEvidencePlannedStudyDTO : flagshipPlannedList.stream()
        .sorted((f1, f2) -> f1.getProjectExpectedStudy().getProjectExpectedStudyInfo(this.getSelectedPhase()).getTitle()
          .compareTo(f2.getProjectExpectedStudy().getProjectExpectedStudyInfo(this.getSelectedPhase()).getTitle()))
        .collect(Collectors.toList())) {
        String title = "", outcomeReportLink = "", subIdo = "", describeGender = "", describeYouth = "",
          describeCapDev = "", additional = "", linkToEvidence = "";

        /** creating download link **/
        String year = powbEvidencePlannedStudyDTO.getProjectExpectedStudy()
          .getProjectExpectedStudyInfo(this.getSelectedPhase()).getYear() + "";
        String cycle = this.getCurrentCycle();
        String study = powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getId() + "";

        outcomeReportLink = this.getBaseUrl() + "/projects/" + this.getLoggedCrp().getAcronym()
          + "/studySummary.do?studyID=" + study + "&cycle=" + cycle + "&year=" + year;

        if (powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getProjectExpectedStudyInfo() != null) {
          ProjectExpectedStudyInfo projectExpectedStudyInfo =
            powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getProjectExpectedStudyInfo();
          data = new ArrayList<>();
          title = projectExpectedStudyInfo.getTitle() != null && !projectExpectedStudyInfo.getTitle().isEmpty()
            ? projectExpectedStudyInfo.getTitle() : this.getText("global.untitled");
          if (powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSubIdos() != null
            && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSubIdos().isEmpty()) {
            for (int j = 0; j < powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSubIdos().size(); j++) {
              subIdo += "\n •" + powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getSubIdos().get(j)
                .getSrfSubIdo().getDescription();
            }
          }

          if (powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getProjectExpectedStudyInfo()
            .getReferencesText() != null
            && !powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getProjectExpectedStudyInfo().getReferencesText()
              .isEmpty()) {
            linkToEvidence =
              powbEvidencePlannedStudyDTO.getProjectExpectedStudy().getProjectExpectedStudyInfo().getReferencesText();
          }

          if (projectExpectedStudyInfo.getGenderLevel() != null) {
            if (projectExpectedStudyInfo.getDescribeGender() == null) {
              describeGender = projectExpectedStudyInfo.getGenderLevel().getName() + "\n";
            } else {
              describeGender = projectExpectedStudyInfo.getGenderLevel().getName() + "\n"
                + projectExpectedStudyInfo.getDescribeGender();
            }
          }
          if (projectExpectedStudyInfo.getYouthLevel() != null) {
            if (projectExpectedStudyInfo.getDescribeYouth() == null) {
              describeYouth = projectExpectedStudyInfo.getYouthLevel().getName();
            } else {
              describeYouth =
                projectExpectedStudyInfo.getYouthLevel().getName() + "\n" + projectExpectedStudyInfo.getDescribeYouth();
            }
          }
          if (projectExpectedStudyInfo.getCapdevLevel() != null) {
            if (projectExpectedStudyInfo.getDescribeCapdev() == null) {
              describeCapDev = projectExpectedStudyInfo.getCapdevLevel().getName();
            } else {
              describeCapDev = projectExpectedStudyInfo.getCapdevLevel().getName() + "\n"
                + projectExpectedStudyInfo.getDescribeCapdev();
            }
          }

          Boolean bold = false;
          String blueColor = "0000EE";
          additional = "Gender: " + describeGender + "\nYouth: " + describeYouth + " \nCapDev: " + describeCapDev;
          POIField[] sData = {new POIField(title, ParagraphAlignment.LEFT, bold, blueColor, outcomeReportLink),
            new POIField(subIdo, ParagraphAlignment.LEFT), new POIField(linkToEvidence, ParagraphAlignment.LEFT),
            new POIField(additional, ParagraphAlignment.LEFT)};
          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableA2AnnualReport");
  }

  private void createTableB() {

    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader = {new POIField(this.getText("expectedProgress.tableA.fp"), ParagraphAlignment.LEFT),
      new POIField(this.getText("expectedProgress.tableA.subIDO"), ParagraphAlignment.LEFT),
      new POIField(this.getText("expectedProgress.tableA.outcomes"), ParagraphAlignment.LEFT),
      new POIField(this.getText("expectedProgress.tableA.milestone") + "*", ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableB.field5"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableB.field6"), ParagraphAlignment.LEFT)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    for (CrpProgram flagship : flagships) {

      data = new ArrayList<>();
      int outcome_index = 0;

      for (CrpProgramOutcome outcome : flagship.getOutcomes()) {
        String subIDO = "";
        int milestone_index = 0;
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
        for (CrpMilestone crpMilestone : outcome.getMilestones()) {
          String FP = "", outcomes = "", milestone = "", status = "", evidence = "";
          Boolean isFlagshipRow = (outcome_index == 0) && (milestone_index == 0);
          Boolean isOutcomeRow = (milestone_index == 0);
          if (isFlagshipRow) {
            FP = "  " + flagship.getAcronym() + " ";
          } else {
            FP = " ";
          }
          if (isOutcomeRow) {
            outcomes = outcome.getComposedName();
          } else {
            outcomes = " ";
          }
          milestone = crpMilestone.getComposedName() != null ? crpMilestone.getComposedName() : "";
          ReportSynthesisFlagshipProgressMilestone reportSynthesisFlagshipProgressMilestoneList =
            this.getReportSynthesisFlagshipProgressProgram(crpMilestone.getId(), flagship.getId());
          if (reportSynthesisFlagshipProgressMilestoneList != null
            && reportSynthesisFlagshipProgressMilestoneList.getId() != null) {
            evidence = reportSynthesisFlagshipProgressMilestoneList.getEvidence();
            status = reportSynthesisFlagshipProgressMilestoneList.getStatusName();
          }

          POIField[] sData = {new POIField(FP, ParagraphAlignment.LEFT), new POIField(subIDO, ParagraphAlignment.LEFT),
            new POIField(outcomes, ParagraphAlignment.LEFT), new POIField(milestone, ParagraphAlignment.LEFT),
            new POIField(status, ParagraphAlignment.LEFT), new POIField(evidence, ParagraphAlignment.LEFT)};
          data = Arrays.asList(sData);
          datas.add(data);

          milestone_index++;
        }
        outcome_index++;
      }
    }

    poiSummary.textTable(document, headers, datas, false, "tableBAnnualReport");
  }

  private void createTableC() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("crossCuttingDimensions.tableC.crossCutting"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.principal"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.significant"), ParagraphAlignment.CENTER),
        new POIField(this.getText("crossCuttingDimensions.tableC.notTargeted"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualreport.tablec.field5"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    // Setup Table C
    tableC = reportSynthesisCrossCuttingDimensionManager.getTableC(this.getSelectedPhase(), this.getLoggedCrp());
    if (tableC != null) {
      POIField[] sData = {new POIField("Gender", ParagraphAlignment.LEFT),
        new POIField(percentageFormat.format(round(tableC.getPercentageGenderPrincipal() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageGenderSignificant() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageGenderNotScored() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(String.valueOf((int) tableC.getTotal()), ParagraphAlignment.CENTER)};
      data = Arrays.asList(sData);
      datas.add(data);
      POIField[] sData2 = {new POIField("Youth", ParagraphAlignment.LEFT),
        new POIField(percentageFormat.format(round(tableC.getPercentageYouthPrincipal() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageYouthSignificant() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageYouthNotScored() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField("", ParagraphAlignment.LEFT)};
      data = Arrays.asList(sData2);
      datas.add(data);
      POIField[] sData3 = {new POIField("CapDev", ParagraphAlignment.LEFT),
        new POIField(percentageFormat.format(round(tableC.getPercentageCapDevPrincipal() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageCapDevSignificant() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField(percentageFormat.format(round(tableC.getPercentageCapDevNotScored() / 100, 4)),
          ParagraphAlignment.CENTER),
        new POIField("", ParagraphAlignment.LEFT)};
      data = Arrays.asList(sData3);
      datas.add(data);
    }

    poiSummary.textTable(document, headers, datas, true, "tableCAnnualReport");
  }

  public void createTableD1() {
    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader =
      {new POIField(this.getText("summaries.annualReport.tableD1.field1"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD1.field2"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD1.field3"), ParagraphAlignment.CENTER),
        new POIField(this.getText("summaries.annualReport.tableD1.field4"), ParagraphAlignment.CENTER)};


    List<RepIndSynthesisIndicator> listRepIndSynthesis = repIndSynthesisIndicatorManager.findAll();
    List<ReportSynthesisIndicator> reportSynthesisIndicatorList =
      reportSynthesisPMU.getReportSynthesisIndicatorGeneral().getReportSynthesisIndicators().stream()
        .filter(
          si -> si.isActive() && si.getRepIndSynthesisIndicator() != null && si.getRepIndSynthesisIndicator().isMarlo())
        .sorted((i1, i2) -> i1.getRepIndSynthesisIndicator().getIndicator()
          .compareTo(i2.getRepIndSynthesisIndicator().getIndicator()))
        .collect(Collectors.toList());

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    String lastType = "";

    if (listRepIndSynthesis != null && !listRepIndSynthesis.isEmpty()) {
      for (RepIndSynthesisIndicator repIndSynthesisIndicator : listRepIndSynthesis) {
        String type = "", indicator = "", name = "", dataRep = "", comments = "";

        if (type.equals(lastType)) {
          type = "";
        } else {
          lastType = type;
        }

        type = repIndSynthesisIndicator.getType();
        indicator = repIndSynthesisIndicator.getIndicator();
        name = repIndSynthesisIndicator.getName();

        if (reportSynthesisIndicatorList != null && !reportSynthesisIndicatorList.isEmpty()) {
          for (ReportSynthesisIndicator reportSynthesisIndicator : reportSynthesisIndicatorList) {
            if (reportSynthesisIndicator.getRepIndSynthesisIndicator().getId() == repIndSynthesisIndicator.getId()) {

              if (reportSynthesisIndicator.getData() != null) {
                dataRep = reportSynthesisIndicator.getData();
              }

              if (reportSynthesisIndicator.getComment() != null) {
                comments = reportSynthesisIndicator.getComment();
              }

            }
          }
        }

        Boolean bold = false;
        String blackColor = "000000";
        POIField[] sData = {new POIField(type, ParagraphAlignment.CENTER, bold, blackColor),
          new POIField(indicator + "." + name, ParagraphAlignment.LEFT, bold, blackColor),
          new POIField(dataRep, ParagraphAlignment.LEFT, bold, blackColor),
          new POIField(comments, ParagraphAlignment.LEFT, bold, blackColor)};
        data = new ArrayList<>();
        data = Arrays.asList(sData);
        datas.add(data);
      }
    }
    poiSummary.textTable(document, headers, datas, true, "tableD1AnnualReport");

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

    flagshipPlannedInnovations = reportSynthesisCrossCuttingDimensionManager.getPlannedInnovationList(
      flagshipLiaisonInstitutions, this.getSelectedPhase().getId(), this.getLoggedCrp(), pmuInstitution);

    if (flagshipPlannedInnovations != null && !flagshipPlannedInnovations.isEmpty()) {
      for (ReportSynthesisCrossCuttingInnovationDTO reportSynthesisCrossCuttingInnovationDTO : flagshipPlannedInnovations
        .stream()
        .sorted((f1, f2) -> f1.getProjectInnovation().getProjectInnovationInfo(this.getSelectedPhase()).getTitle()
          .compareTo(f2.getProjectInnovation().getProjectInnovationInfo(this.getSelectedPhase()).getTitle()))
        .collect(Collectors.toList())) {
        String title = " ", stage = "", degree = " ", contribution = " ", geographicScope = " ";
        if (reportSynthesisCrossCuttingInnovationDTO.getProjectInnovation()
          .getProjectInnovationInfo(this.getSelectedPhase()) != null) {
          ProjectInnovationInfo projectInnovationInfo = reportSynthesisCrossCuttingInnovationDTO.getProjectInnovation()
            .getProjectInnovationInfo(this.getSelectedPhase());
          title = projectInnovationInfo.getTitle() != null && !projectInnovationInfo.getTitle().isEmpty()
            ? projectInnovationInfo.getTitle() : this.getText("global.untitled");
          stage = projectInnovationInfo.getRepIndStageInnovation() != null
            && projectInnovationInfo.getRepIndStageInnovation().getName() != null
            && !projectInnovationInfo.getRepIndStageInnovation().getName().isEmpty()
              ? projectInnovationInfo.getRepIndStageInnovation().getName() : this.getText("global.untitled");
          if (projectInnovationInfo.getRepIndDegreeInnovation() != null) {
            degree = projectInnovationInfo.getRepIndDegreeInnovation().getName();
          }
          if (projectInnovationInfo.getRepIndContributionOfCrp() != null) {
            contribution = projectInnovationInfo.getRepIndContributionOfCrp().getName();
          }
          if (projectInnovationInfo.getRepIndGeographicScope() != null) {
            geographicScope = projectInnovationInfo.getRepIndGeographicScope().getName();
          }

          if (title != null && stage != null && degree != null && contribution != null && geographicScope != null) {
            POIField[] sData =
              {new POIField(title, ParagraphAlignment.CENTER), new POIField(stage, ParagraphAlignment.CENTER),
                new POIField(degree, ParagraphAlignment.CENTER), new POIField(contribution, ParagraphAlignment.CENTER),
                new POIField(geographicScope, ParagraphAlignment.CENTER)};

            data = Arrays.asList(sData);
            datas.add(data);
          }
        }
      }
    }

    poiSummary.textTable(document, headers, datas, false, "tableD2AnnualReport");

  }

  private void createTableE() {
    List<ReportSynthesisCrossCuttingAssetDTO> flagshipPlannedAssets =
      reportSynthesisCrossCuttingDimensionManager.getPlannedAssetsList(flagshipLiaisonInstitutions,
        this.getSelectedPhase().getId(), this.getLoggedCrp(), pmuInstitution);
    List<List<POIField>> headers = new ArrayList<>();

    POIField[] sHeader = {new POIField(this.getText("summaries.annualReport.tableE.field1"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableE.field2"), ParagraphAlignment.CENTER),
      new POIField(this.getText("summaries.annualReport.tableE.field3"), ParagraphAlignment.CENTER),
      new POIField(this.getText("summaries.annualReport.tableE.field4"), ParagraphAlignment.CENTER),
      new POIField(this.getText("summaries.annualReport.tableE.field5"), ParagraphAlignment.CENTER),
      new POIField(this.getText("summaries.annualReport.tableE.field6"), ParagraphAlignment.CENTER)};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;


    if (flagshipPlannedAssets != null && !flagshipPlannedAssets.isEmpty()) {
      for (ReportSynthesisCrossCuttingAssetDTO reportSynthesisCrossCuttingAssetDTO : flagshipPlannedAssets) {
        String year = "", patent = "", applicant = "", aditional = "", registration = "", communication = "",
          status = "";

        if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getDeliverable()
          .getDeliverableInfo(this.getSelectedPhase()) != null) {
          if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getDeliverable()
            .getDeliverableInfo().getStatus() != null
            && reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getDeliverable()
              .getDeliverableInfo().getStatus() != -1) {
            status = ProjectStatusEnum.getValue(reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset()
              .getDeliverable().getDeliverableInfo().getStatus()).getStatus();
          }
          if (status.equals(ProjectStatusEnum.Extended.getStatus())) {
            year = reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getDeliverable()
              .getDeliverableInfo().getNewExpectedYear() + "";
          } else {
            year = reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getDeliverable()
              .getDeliverableInfo().getYear() + "";
          }
        }

        if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getApplicant() != null) {
          applicant = reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getApplicant();
        }

        if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getType() != null) {
          patent = reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getTypeName();
        }
        if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getAdditionalInformation() != null) {
          aditional = reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getAdditionalInformation();
        }

        if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getLink() != null) {
          registration = reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getLink();
        }

        if (reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getPublicCommunication() != null) {
          communication =
            reportSynthesisCrossCuttingAssetDTO.getDeliverableIntellectualAsset().getPublicCommunication();
        }

        POIField[] sData =
          {new POIField(year, ParagraphAlignment.CENTER), new POIField(applicant, ParagraphAlignment.LEFT),
            new POIField(patent, ParagraphAlignment.CENTER), new POIField(aditional, ParagraphAlignment.LEFT),
            new POIField(registration, ParagraphAlignment.LEFT), new POIField(communication, ParagraphAlignment.LEFT)};
        data = Arrays.asList(sData);
        datas.add(data);
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableAAnnualReport");
  }

  private void createTableF() {

    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader =
      {new POIField(this.getText("financialPlan.tableF.expenditureArea") + "*", ParagraphAlignment.LEFT),
        new POIField(this.getText("financialPlan.tableF.estimatedPercentage",
          new String[] {String.valueOf(this.getSelectedYear())}) + "**", ParagraphAlignment.LEFT),
        new POIField(this.getText("financialPlan.tableF.comments2017"), ParagraphAlignment.LEFT)};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;


    if (reportSynthesisPMU.getReportSynthesisFundingUseSummary().getReportSynthesisFundingUseExpendituryAreas() != null
      && !reportSynthesisPMU.getReportSynthesisFundingUseSummary().getReportSynthesisFundingUseExpendituryAreas()
        .isEmpty()) {
      reportSynthesisPMU.getReportSynthesisFundingUseSummary()
        .setExpenditureAreas(new ArrayList<>(reportSynthesisPMU.getReportSynthesisFundingUseSummary()
          .getReportSynthesisFundingUseExpendituryAreas().stream().filter(t -> t.isActive())
          .sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).collect(Collectors.toList())));
    } else {
      reportSynthesisPMU.getReportSynthesisFundingUseSummary().setExpenditureAreas(new ArrayList<>());
      List<PowbExpenditureAreas> expAreas =
        new ArrayList<>(powbExpenditureAreasManager.findAll().stream().filter(x -> x.isActive() && x.getIsExpenditure())
          .sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).collect(Collectors.toList()));
      for (PowbExpenditureAreas powbExpenditureAreas : expAreas) {
        ReportSynthesisFundingUseExpendituryArea fundingUseExpenditureArea =
          new ReportSynthesisFundingUseExpendituryArea();
        fundingUseExpenditureArea.setExpenditureArea(powbExpenditureAreas);
        reportSynthesisPMU.getReportSynthesisFundingUseSummary().getExpenditureAreas().add(fundingUseExpenditureArea);
      }
    }
    List<ReportSynthesisFundingUseExpendituryArea> reportSynthesisFundingUseExpendituryAreaList =
      reportSynthesisPMU.getReportSynthesisFundingUseSummary().getExpenditureAreas();
    Double totalEstimatedPercentajeFS = 0.0;
    if (reportSynthesisFundingUseExpendituryAreaList != null
      && !reportSynthesisFundingUseExpendituryAreaList.isEmpty()) {
      for (ReportSynthesisFundingUseExpendituryArea reportSynthesisFundingUseExpendituryArea : reportSynthesisFundingUseExpendituryAreaList) {

        String expenditureArea = "", commentsSpace = "";
        Double estimatedPercentajeFS = 0.0;
        expenditureArea = reportSynthesisFundingUseExpendituryArea.getExpenditureArea().getExpenditureArea() != null
          ? reportSynthesisFundingUseExpendituryArea.getExpenditureArea().getExpenditureArea() : "";
        estimatedPercentajeFS = reportSynthesisFundingUseExpendituryArea.getW1w2Percentage() != null
          ? reportSynthesisFundingUseExpendituryArea.getW1w2Percentage() : 0;
        commentsSpace = reportSynthesisFundingUseExpendituryArea.getComments() != null
          ? reportSynthesisFundingUseExpendituryArea.getComments() : "";

        totalEstimatedPercentajeFS += estimatedPercentajeFS;
        POIField[] sData = {new POIField(expenditureArea, ParagraphAlignment.LEFT),
          new POIField(percentageFormat.format(round(estimatedPercentajeFS / 100, 4)), ParagraphAlignment.CENTER),
          new POIField(commentsSpace, ParagraphAlignment.LEFT)};
        data = Arrays.asList(sData);
        datas.add(data);
      }

    }

    Boolean bold = true;
    String blackColor = "000000";

    totalw1w2 = reportSynthesisFinancialSummaryBudgetManager.getTotalW1W2ActualExpenditure(reportSynthesisPMU.getId());

    POIField[] sData = {new POIField("TOTAL FUNDING (AMOUNT)***", ParagraphAlignment.LEFT, bold, blackColor),
      new POIField(currencyFormat.format(round(((totalw1w2) * totalEstimatedPercentajeFS / 100) / 1000, 2)),
        ParagraphAlignment.CENTER, bold, blackColor)};

    data = Arrays.asList(sData);
    datas.add(data);

    poiSummary.textTable(document, headers, datas, true, "tableFAnnualReport");
  }

  private void createTableG() {
    List<List<POIField>> headers = new ArrayList<>();

    List<LiaisonInstitution> liaisonInstitutions = new ArrayList<>(this.getLoggedCrp().getLiaisonInstitutions().stream()
      .filter(c -> c.getCrpProgram() != null && c.isActive()
        && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList()));

    POIField[] sHeader = {new POIField(this.getText("summaries.annualReport.tableG.field1"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableG.field2"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableG.field3"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableG.field4"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableG.field5"), ParagraphAlignment.LEFT)};

    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);
    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    List<ReportSynthesisExternalPartnershipDTO> flagshipExternalPlannedList =
      reportSynthesisExternalPartnershipManager.getPlannedPartnershipList(liaisonInstitutions,
        this.getSelectedPhase().getId(), this.getLoggedCrp(), pmuInstitution);

    if (flagshipExternalPlannedList != null && !flagshipExternalPlannedList.isEmpty()) {
      for (int i = 0; i < flagshipExternalPlannedList.size(); i++) {

        String FP = "", stage = "", partner = "", partnerType = "", mainArea = "";
        if (flagshipExternalPlannedList.get(i).getProjectPartnerPartnership() != null) {

          // **Getting flagships **/

          for (ProjectFocus projectFocuses : flagshipExternalPlannedList.get(i).getProjectPartnerPartnership()
            .getProjectPartner().getProject().getProjectFocuses().stream()
            .sorted((o1, o2) -> o1.getCrpProgram().getAcronym().compareTo(o2.getCrpProgram().getAcronym()))
            .filter(
              c -> c.isActive() && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue()
                && c.getPhase() != null && c.getPhase().equals(this.getSelectedPhase()))
            .collect(Collectors.toList())) {
            if (FP != "") {
              FP += ", ";
            }
            FP += crpProgramManager.getCrpProgramById(projectFocuses.getCrpProgram().getId()).getAcronym();

          }

          if (flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getProjectPartner().getInstitution()
            .getName() != null
            && !flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getProjectPartner().getInstitution()
              .getName().isEmpty()) {
            partner = flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getProjectPartner()
              .getInstitution().getName();
          }
          if (flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getPartnershipResearchPhases() != null
            && !flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getPartnershipResearchPhases()
              .isEmpty()) {
            for (int j = 0; j < flagshipExternalPlannedList.get(i).getProjectPartnerPartnership()
              .getPartnershipResearchPhases().size(); j++) {

              stage += flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getPartnershipResearchPhases()
                .get(j).getRepIndPhaseResearchPartnership().getName() + "\n";
            }
          }
          if (stage == null || stage.isEmpty()) {
            stage = this.getText("global.untitled");
          }
          if (flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getMainArea() != null) {
            mainArea = flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getMainArea();
          }
          partnerType = flagshipExternalPlannedList.get(i).getProjectPartnerPartnership().getProjectPartner()
            .getInstitution().getInstitutionType().getRepIndOrganizationType().getName();

          POIField[] sData = {new POIField(FP, ParagraphAlignment.CENTER),
            new POIField(stage, ParagraphAlignment.CENTER), new POIField(partner, ParagraphAlignment.CENTER),
            new POIField(partnerType, ParagraphAlignment.CENTER), new POIField(mainArea, ParagraphAlignment.LEFT)};
          data = Arrays.asList(sData);
          datas.add(data);
        }
      }
    }
    poiSummary.textTable(document, headers, datas, false, "tableAAnnualReport");
  }


  private void createTableH() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {new POIField(this.getText("summaries.annualReport.tableG.crpName"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableG.description"), ParagraphAlignment.LEFT),
      new POIField(this.getText("summaries.annualReport.tableG.relevantFP"), ParagraphAlignment.LEFT)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    List<ReportSynthesisCrossCgiarCollaboration> reportSynthesisCrossCgiarCollaborationList =
      reportSynthesisCrossCgiarCollaborationManager.getFlagshipCollaborations(flagshipLiaisonInstitutions,
        this.getSelectedPhase().getId());
    if (reportSynthesisCrossCgiarCollaborationList != null && !reportSynthesisCrossCgiarCollaborationList.isEmpty()) {
      for (ReportSynthesisCrossCgiarCollaboration reportSynthesisCrossCgiarCollaboration : reportSynthesisCrossCgiarCollaborationList) {
        String crpPlatform = " ", descriptionCollaboration = " ", relevantFP = " ";

        if (reportSynthesisCrossCgiarCollaboration.getGlobalUnit().getAcronym() != null) {
          crpPlatform = reportSynthesisCrossCgiarCollaboration.getGlobalUnit().getAcronym();
        }

        if (reportSynthesisCrossCgiarCollaboration.getDescription() != null) {
          descriptionCollaboration = reportSynthesisCrossCgiarCollaboration.getDescription();
        }

        if (reportSynthesisCrossCgiarCollaboration.getFlagship() != null) {
          relevantFP = reportSynthesisCrossCgiarCollaboration.getFlagship();
        }

        POIField[] sData = {new POIField(crpPlatform, ParagraphAlignment.CENTER),
          new POIField(descriptionCollaboration, ParagraphAlignment.LEFT),
          new POIField(relevantFP, ParagraphAlignment.LEFT)};
        data = Arrays.asList(sData);
        datas.add(data);
      }
    }

    poiSummary.textTable(document, headers, datas, false, "tableAAnnualReport");
  }


  private void createTableI1() {

    List<List<POIField>> headers = new ArrayList<>();
    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    POIField[] sHeader = {
      new POIField(
        this.getText("annualReport.melia.tableI.studies", new String[] {String.valueOf(this.getSelectedYear())}),
        ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.tableI.status"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.tableI.comments"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<LiaisonInstitution> liaisonInstitutionsList =
      new ArrayList<>(this.getLoggedCrp().getLiaisonInstitutions().stream()
        .filter(c -> c.getCrpProgram() != null && c.isActive()
          && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
        .collect(Collectors.toList()));
    liaisonInstitutionsList.sort(Comparator.comparing(LiaisonInstitution::getAcronym));

    flagshipPlannedList = reportSynthesisMeliaManager.getMeliaPlannedList(liaisonInstitutionsList,
      this.getSelectedPhase().getId(), this.getLoggedCrp(), pmuInstitution);
    if (flagshipPlannedList != null && !flagshipPlannedList.isEmpty()) {
      for (int i = 0; i < flagshipPlannedList.size(); i++) {
        String studies = "", status = "", comments = "";
        studies = flagshipPlannedList.get(i).getProjectExpectedStudy()
          .getProjectExpectedStudyInfo(this.getSelectedPhase()).getTitle();
        status = flagshipPlannedList.get(i).getProjectExpectedStudy()
          .getProjectExpectedStudyInfo(this.getSelectedPhase()).getStatusName();
        if (flagshipPlannedList.get(i).getProjectExpectedStudy().getProjectExpectedStudyInfo(this.getSelectedPhase())
          .getTopLevelComments() != null) {
          comments = flagshipPlannedList.get(i).getProjectExpectedStudy()
            .getProjectExpectedStudyInfo(this.getSelectedPhase()).getTopLevelComments();
        }

        POIField[] sData = {new POIField(studies, ParagraphAlignment.LEFT),
          new POIField(status, ParagraphAlignment.CENTER), new POIField(comments, ParagraphAlignment.LEFT)};
        data = Arrays.asList(sData);
        datas.add(data);
      }
    }

    poiSummary.textTable(document, headers, datas, false, "tableIAnnualReport");
  }

  private void createTableI2() {
    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {new POIField(this.getText("annualReport.melia.evaluation.name"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.evaluation.recommendation"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.evaluation.managementResponse") + " - Action Plan",
        ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.evaluation.whom"), ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.melia.evaluation.when"), ParagraphAlignment.CENTER),
      new POIField(this.getText("summaries.annualReport.tableI2.field6"), ParagraphAlignment.CENTER)};
    List<POIField> header = Arrays.asList(sHeader);
    headers.add(header);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;
    if (reportSynthesisPMU.getReportSynthesisMelia().getReportSynthesisMeliaEvaluations() != null
      && !reportSynthesisPMU.getReportSynthesisMelia().getReportSynthesisMeliaEvaluations().isEmpty()) {
      List<ReportSynthesisMeliaEvaluation> reportSynthesisMeliaEvaluationList =
        reportSynthesisPMU.getReportSynthesisMelia().getReportSynthesisMeliaEvaluations().stream()
          .filter(e -> e.isActive()).collect(Collectors.toList());

      for (ReportSynthesisMeliaEvaluation reportSynthesisMeliaEvaluation : reportSynthesisMeliaEvaluationList) {
        String nameEvaluation = "", recommendation = "", response = "", whom = "", when = "", status = "";

        if (reportSynthesisMeliaEvaluation.getNameEvaluation() != null) {
          nameEvaluation = reportSynthesisMeliaEvaluation.getNameEvaluation();
        }
        if (reportSynthesisMeliaEvaluation.getRecommendation() != null) {
          recommendation = reportSynthesisMeliaEvaluation.getRecommendation();
        }
        if (reportSynthesisMeliaEvaluation.getManagementResponse() != null) {
          response = reportSynthesisMeliaEvaluation.getManagementResponse();
        }
        if (reportSynthesisMeliaEvaluation.getTextWhom() != null) {
          whom = reportSynthesisMeliaEvaluation.getTextWhom();
        }
        if (reportSynthesisMeliaEvaluation.getTextWhen() != null) {
          when = reportSynthesisMeliaEvaluation.getTextWhen();
        }

        if (reportSynthesisMeliaEvaluation.getStatus() != null && reportSynthesisMeliaEvaluation.getStatus() != -1) {
          int temp = Integer.parseInt(reportSynthesisMeliaEvaluation.getStatus().toString());
          status = ProjectStatusEnum.getValue(temp).getStatus();
        }

        POIField[] sData = {new POIField(nameEvaluation, ParagraphAlignment.CENTER),
          new POIField(recommendation, ParagraphAlignment.LEFT), new POIField(response, ParagraphAlignment.LEFT),
          new POIField(whom, ParagraphAlignment.LEFT), new POIField(when, ParagraphAlignment.LEFT),
          new POIField(status, ParagraphAlignment.LEFT)};

        data = Arrays.asList(sData);
        datas.add(data);
      }
    }

    poiSummary.textTable(document, headers, datas, false, "tableIAnnualReport");
  }

  private void createTableJ() {
    if (reportSynthesisPMU.getReportSynthesisFinancialSummary().getReportSynthesisFinancialSummaryBudgets() != null
      && !reportSynthesisPMU.getReportSynthesisFinancialSummary().getReportSynthesisFinancialSummaryBudgets()
        .isEmpty()) {
      reportSynthesisPMU.getReportSynthesisFinancialSummary()
        .setBudgets(new ArrayList<>(
          reportSynthesisPMU.getReportSynthesisFinancialSummary().getReportSynthesisFinancialSummaryBudgets().stream()
            .filter(t -> t.isActive()).collect(Collectors.toList())));
    } else {

      flagshipLiaisonInstitutions.sort(Comparator.comparing(LiaisonInstitution::getAcronym));
      reportSynthesisPMU.getReportSynthesisFinancialSummary().setBudgets(new ArrayList<>());
      for (LiaisonInstitution liInstitution : flagshipLiaisonInstitutions) {
        ReportSynthesisFinancialSummaryBudget financialSummaryBudget = new ReportSynthesisFinancialSummaryBudget();
        financialSummaryBudget.setLiaisonInstitution(liInstitution);
        reportSynthesisPMU.getReportSynthesisFinancialSummary().getBudgets().add(financialSummaryBudget);
      }

      List<PowbExpenditureAreas> expAreas = new ArrayList<>(powbExpenditureAreasManager.findAll().stream()
        .filter(x -> x.isActive() && !x.getIsExpenditure()).collect(Collectors.toList()));
      for (PowbExpenditureAreas powbExpenditureAreas : expAreas) {
        ReportSynthesisFinancialSummaryBudget financialSummaryBudget = new ReportSynthesisFinancialSummaryBudget();
        financialSummaryBudget.setExpenditureArea(powbExpenditureAreas);
        reportSynthesisPMU.getReportSynthesisFinancialSummary().getBudgets().add(financialSummaryBudget);
      }
    }


    List<ReportSynthesisFinancialSummaryBudget> reportSynthesisFinancialSummaryBudgetList =
      reportSynthesisPMU.getReportSynthesisFinancialSummary().getBudgets();

    List<List<POIField>> headers = new ArrayList<>();
    POIField[] sHeader = {new POIField("", ParagraphAlignment.CENTER), new POIField(
      this.getText("annualReport.financial.tableJ.budget", new String[] {String.valueOf(this.getSelectedYear())}) + "*",
      ParagraphAlignment.CENTER), new POIField("", ParagraphAlignment.CENTER),
      new POIField("", ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.financial.tableJ.expenditure") + "*", ParagraphAlignment.CENTER),
      new POIField("", ParagraphAlignment.CENTER), new POIField("", ParagraphAlignment.CENTER),
      new POIField(this.getText("annualReport.financial.tableJ.difference") + "*", ParagraphAlignment.CENTER),
      new POIField("", ParagraphAlignment.CENTER), new POIField("", ParagraphAlignment.CENTER)};

    POIField[] sHeader2 = {new POIField(" ", ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.w1w2"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.w3bilateral"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.total"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.w1w2"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.w3bilateral"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.total"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.w1w2"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.w3bilateral"), ParagraphAlignment.CENTER),
      new POIField(this.getText("financialPlan.tableE.total"), ParagraphAlignment.CENTER)};

    List<POIField> header = Arrays.asList(sHeader);
    List<POIField> header2 = Arrays.asList(sHeader2);
    headers.add(header);
    headers.add(header2);

    List<List<POIField>> datas = new ArrayList<>();
    List<POIField> data;

    double totalW1w2Difference = 0.0, totalW3Difference = 0.0, grandTotalDifference = 0.0;
    if (reportSynthesisFinancialSummaryBudgetList != null && !reportSynthesisFinancialSummaryBudgetList.isEmpty()) {

      for (ReportSynthesisFinancialSummaryBudget reportSynthesisFinancialSummaryBudget : reportSynthesisFinancialSummaryBudgetList) {

        String category = "";
        Double w1w2Planned = 0.0, w3Planned = 0.0, w1w2Actual = 0.0, w3Actual = 0.0, totalPlanned = 0.0,
          totalActual = 0.0, w1w2Difference = 0.0, w3Difference = 0.0, totalDifference = 0.0;

        /** Getting category name **/
        if (reportSynthesisFinancialSummaryBudget.getLiaisonInstitution() != null) {
          category = reportSynthesisFinancialSummaryBudget.getLiaisonInstitution().getComposedName();
        } else if (reportSynthesisFinancialSummaryBudget.getExpenditureArea().getExpenditureArea() != null) {
          category = reportSynthesisFinancialSummaryBudget.getExpenditureArea().getExpenditureArea();
        }
        if (reportSynthesisFinancialSummaryBudget.getW1Planned() != null) {
          w1w2Planned = reportSynthesisFinancialSummaryBudget.getW1Planned();
        }

        if (reportSynthesisFinancialSummaryBudget.getW3Planned() != null) {
          w3Planned = reportSynthesisFinancialSummaryBudget.getW3Planned()
            + reportSynthesisFinancialSummaryBudget.getBilateralPlanned();
        }

        totalPlanned = w1w2Planned + w3Planned;
        if (reportSynthesisFinancialSummaryBudget.getW1Actual() != null) {
          w1w2Actual = reportSynthesisFinancialSummaryBudget.getW1Actual();
        }

        if (reportSynthesisFinancialSummaryBudget.getW3Actual() != null) {
          w3Actual = reportSynthesisFinancialSummaryBudget.getW3Actual()
            + reportSynthesisFinancialSummaryBudget.getBilateralActual();
        }

        totalActual = w1w2Actual + w3Actual;

        w1w2Difference = w1w2Planned - w1w2Actual;
        w3Difference = w3Planned - w3Actual;
        totalDifference = totalPlanned - totalActual;

        totalw1w2Planned += w1w2Planned;
        totalW3Planned += w3Planned;
        grandTotalPlanned += totalPlanned;

        totalw1w2Actual += w1w2Actual;
        totalW3Actual += w3Actual;
        grandTotalActual += totalActual;

        totalW1w2Difference += w1w2Difference;
        totalW3Difference += w3Difference;
        grandTotalDifference += totalDifference;

        POIField[] sData = {new POIField(category, ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(w1w2Planned / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(w3Planned / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(totalPlanned / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(w1w2Actual / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(w3Actual / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(totalActual / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(w1w2Difference / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(w3Difference / 1000, 2)), ParagraphAlignment.CENTER),
          new POIField(currencyFormat.format(round(totalDifference / 1000, 2)), ParagraphAlignment.CENTER)};

        data = Arrays.asList(sData);
        datas.add(data);
      }
    }

    Boolean bold = true;
    String blackColor = "000000";
    POIField[] sData = {new POIField("CRP Total", ParagraphAlignment.CENTER, bold, blackColor),

      new POIField(currencyFormat.format(round(totalw1w2Planned / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),
      new POIField(currencyFormat.format(round(totalW3Planned / 1000, 2)), ParagraphAlignment.CENTER, bold, blackColor),
      new POIField(currencyFormat.format(round(grandTotalPlanned / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),
      new POIField(currencyFormat.format(round(totalw1w2Actual / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),
      new POIField(currencyFormat.format(round(totalW3Actual / 1000, 2)), ParagraphAlignment.CENTER, bold, blackColor),
      new POIField(currencyFormat.format(round(grandTotalActual / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),
      new POIField(currencyFormat.format(round(totalW1w2Difference / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),
      new POIField(currencyFormat.format(round(totalW3Difference / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),
      new POIField(currencyFormat.format(round(grandTotalDifference / 1000, 2)), ParagraphAlignment.CENTER, bold,
        blackColor),};

    data = Arrays.asList(sData);
    datas.add(data);

    poiSummary.textTable(document, headers, datas, true, "tableJAnnualReport");
  }

  @Override
  public String execute() throws Exception {
    if (this.getSelectedPhase() == null) {
      return NOT_FOUND;
    }


    if (this.isEntityCRP()) {
      try {

        CTDocument1 doc = document.getDocument();
        CTBody body = doc.getBody();
        poiSummary.pageHeader(document, this.getText("summaries.annualReportCRP2018.header"));

        // Get datetime
        ZonedDateTime timezone = ZonedDateTime.now();
        String zone = timezone.getOffset() + "";
        if (zone.equals("Z")) {
          zone = "+0";
        }

        this.createPageFooter();
        // poiSummary.pageFooter(document, "This report was generated on " + currentDate);

        // Cover
        poiSummary.textLineBreak(document, 6);
        poiSummary.textHeadCoverTitleAR2018(document.createParagraph(),
          this.getText("summaries.annualReportCRP2018.mainTitle"));
        document.createParagraph().setPageBreak(true);

        // Table of contents
        document.createTOC();

        // the body content
        XWPFParagraph paragraph = document.createParagraph();
        CTP ctP = paragraph.getCTP();
        CTSimpleField toc = ctP.addNewFldSimple();
        toc.setInstr("TOC \\h");
        toc.setDirty(STOnOff.TRUE);
        XWPFRun run = paragraph.createRun();

        // Toc section
        addCustomHeadingStyle(document, "heading 1", 1);
        addCustomHeadingStyle(document, "heading 2", 1);
        addCustomHeadingStyle(document, "heading 3", 1);
        addCustomHeadingStyle(document, "heading 4", 2);
        addCustomHeadingStyle(document, "heading 5", 2);
        addCustomHeadingStyle(document, "heading 6", 2);
        addCustomHeadingStyle(document, "heading 7", 2);
        addCustomHeadingStyle(document, "heading 8", 2);
        addCustomHeadingStyle(document, "heading 9", 2);
        addCustomHeadingStyle(document, "heading 10", 2);
        addCustomHeadingStyle(document, "heading 11", 2);
        addCustomHeadingStyle(document, "heading 12", 2);
        addCustomHeadingStyle(document, "heading 13", 2);
        addCustomHeadingStyle(document, "heading 14", 2);
        addCustomHeadingStyle(document, "heading 15", 1);
        addCustomHeadingStyle(document, "heading 16", 2);
        addCustomHeadingStyle(document, "heading 17", 2);
        addCustomHeadingStyle(document, "heading 18", 2);
        addCustomHeadingStyle(document, "heading 19", 2);
        addCustomHeadingStyle(document, "heading 20", 2);
        addCustomHeadingStyle(document, "heading 21", 2);
        addCustomHeadingStyle(document, "heading 22", 2);
        addCustomHeadingStyle(document, "heading 23", 2);
        addCustomHeadingStyle(document, "heading 24", 2);
        addCustomHeadingStyle(document, "heading 25", 1);

        // First page
        document.createParagraph().setPageBreak(true);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReportCRP2018.cover"));
        paragraph.setStyle("heading 1");

        String unitName = this.getLoggedCrp().getAcronym() != null && !this.getLoggedCrp().getAcronym().isEmpty()
          ? this.getLoggedCrp().getAcronym() : this.getLoggedCrp().getName();
        poiSummary.textParagraph(document.createParagraph(),
          this.getText("summaries.annualReportCRP2018.unitName") + ": " + unitName);
        poiSummary.textParagraph(document.createParagraph(), this.getText("summaries.annualReport.LeadCenter") + ":");
        this.addParticipatingCenters();

        // Part A - Narrative section
        // First page
        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReportCRP2018.narrative"));
        paragraph.setStyle("heading 2");

        // section 1 - Key Results
        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport.keyResults"));
        paragraph.setStyle("heading 3");

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.keyResults.crpProgress"));
        paragraph.setStyle("heading 4");
        this.addExpectedCrp();
        this.addCrpProgressOutcomes();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.keyResults.progressTowards"));
        paragraph.setStyle("heading 5");
        this.addAdjustmentDescription();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.keyResults.overall"));
        paragraph.setStyle("heading 6");

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.keyResults.progress"));
        paragraph.setStyle("heading 7");

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.keyResults.variance"));
        paragraph.setStyle("heading 8");

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.keyResults.altmetric"));
        paragraph.setStyle("heading 9");

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.crossCutting"));
        paragraph.setStyle("heading 10");

        // poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.crossCutting.gender"));
        paragraph.setStyle("heading 11");
        this.addCrossCuttingGender();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.crossCutting.youth"));
        paragraph.setStyle("heading 12");
        this.addCrossCuttingYouth();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.crossCutting.capacity"));
        paragraph.setStyle("heading 13");
        this.addCrossCuttingCapacityDevelopment();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.crossCutting.climateChange"));
        paragraph.setStyle("heading 14");
        this.addCrossCuttingClimateChange();

        // section 2 - Effectiveness and Efficiency
        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness"));
        paragraph.setStyle("heading 15");

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.management"));
        paragraph.setStyle("heading 16");
        this.addVariancePlanned();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.partnerships"));
        paragraph.setStyle("heading 17");
        this.addFundingSummarize();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.highlights"));
        paragraph.setStyle("heading 18");
        this.addExternalPartnerships();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.crossCgiar"));
        paragraph.setStyle("heading 19");
        this.addCrossPartnerships();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.intellectual"));
        paragraph.setStyle("heading 20");
        this.addIntellectualAssets();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.monitoring"));
        paragraph.setStyle("heading 21");
        this.addImprovingEfficiency();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.efficiency"));
        paragraph.setStyle("heading 22");
        this.addImprovingEfficiency();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.risk"));
        paragraph.setStyle("heading 23");
        this.addImprovingEfficiency();

        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(13);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.effectiveness.funding"));
        paragraph.setStyle("heading 24");
        this.addFundingSummarize();

        // section 3 - Financial summary
        poiSummary.textLineBreak(document, 1);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReport2018.financial"));
        paragraph.setStyle("heading 25");
        // this.addManagement();

        /* Create a landscape text Section */
        paragraph = document.createParagraph();
        CTSectPr sectionTable = body.getSectPr();
        CTPageSz pageSizeTable = sectionTable.addNewPgSz();
        CTP ctpTable = paragraph.getCTP();
        CTPPr brTable = ctpTable.addNewPPr();
        brTable.setSectPr(sectionTable);
        /* standard Letter page size */
        pageSizeTable.setOrient(STPageOrientation.LANDSCAPE);
        pageSizeTable.setW(BigInteger.valueOf(842 * 20));
        pageSizeTable.setH(BigInteger.valueOf(595 * 20));
        this.loadTablePMU();

        // Table a1
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead1Title(document.createParagraph(), "TABLES");
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableA.title"));
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableA1.title"));
        this.createTableA1();

        // Table a2
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableA2.title"));
        // this.createTableA2();

        // Table b
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableB.title"));
        this.createTableB();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableB.description1"));
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableB.description2"));

        // Table c
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("annualReport.ccDimensions.tableCTitle"));
        this.createTableC();

        // Table d
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableD.title"));

        // Table d1
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableD1.title"));
        // this.createTableD1();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableD1.footer"));

        // Table d2
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableD2.title"));
        this.createTableD2();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableD2.footer"));

        // Table e
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("annualReport.ccDimensions.tableETitle", new String[] {String.valueOf(this.getSelectedYear())}));
        this.createTableE();
        poiSummary.textNotes(document.createParagraph(), this.getText("intellectualAsset.title.help2017"));

        // Table f
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("financialPlan.tableF.title2017"));
        // this.createTableF();
        poiSummary.textNotes(document.createParagraph(), this.getText("financialPlan.tableF.expenditureArea.help"));
        poiSummary.textNotes(document.createParagraph(), this.getText("financialPlan.tableF.expenditureArea.help2017"));
        poiSummary.textNotes(document.createParagraph(),
          "**" + this.getText("summaries.annualReport.tableJ.description.help2"));


        // Table g
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableG.title"));
        this.createTableG();

        // Table h
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableH.title"));
        this.createTableH();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.powb.tableG.description.help"));

        // Table i1
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableI.title"));
        poiSummary.textHead3Title(document.createParagraph(),
          this.getText("annualReport.melia.tableI.title", new String[] {String.valueOf(this.getSelectedYear())})
            + " POWB");
        this.createTableI1();

        // Table i2
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead3Title(document.createParagraph(), this.getText("annualReport.melia.evaluation.title"));
        // this.createTableI2();

        // Table j
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableJ.title"));
        // this.createTableJ();
        poiSummary.textNotes(document.createParagraph(),
          this.getText("summaries.annualReport.tableJ.description.help2"));

        poiSummary.textNotes(document.createParagraph(),
          "*" + this.getText("summaries.annualReport.tableJ.description.help"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        document.write(os);
        bytesDOC = os.toByteArray();
        os.close();
        document.close();
      } catch (Exception e) {
        LOG.error("Error generating " + this.getFileName() + ". Exception: " + e.getMessage());
        throw e;
      }
    }

    if (this.isEntityPlatform()) {
      try {

        CTDocument1 doc = document.getDocument();
        CTBody body = doc.getBody();
        poiSummary.pageHeader(document, this.getText("summaries.annualReportPlatform2018.header"));

        // Get datetime
        ZonedDateTime timezone = ZonedDateTime.now();
        String zone = timezone.getOffset() + "";
        if (zone.equals("Z")) {
          zone = "+0";
        }

        this.createPageFooter();
        // poiSummary.pageFooter(document, "This report was generated on " + currentDate);

        // Cover
        poiSummary.textLineBreak(document, 6);
        poiSummary.textHeadCoverTitleAR2018(document.createParagraph(),
          this.getText("summaries.annualReportPlatform2018.mainTitle"));
        document.createParagraph().setPageBreak(true);

        // Table of contents
        document.createTOC();

        // the body content
        XWPFParagraph paragraph = document.createParagraph();
        CTP ctP = paragraph.getCTP();
        CTSimpleField toc = ctP.addNewFldSimple();
        toc.setInstr("TOC \\h");
        toc.setDirty(STOnOff.TRUE);
        XWPFRun run = paragraph.createRun();

        // Toc section
        addCustomHeadingStyle(document, "heading 1", 1);
        addCustomHeadingStyle(document, "heading 2", 2);

        // First page
        document.createParagraph().setPageBreak(true);
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.setFontSize(14);
        run.setBold(true);
        run.setText(this.getText("summaries.annualReportPlatform2018.cover"));
        paragraph.setStyle("heading 2");
        String unitName = this.getLoggedCrp().getAcronym() != null && !this.getLoggedCrp().getAcronym().isEmpty()
          ? this.getLoggedCrp().getAcronym() : this.getLoggedCrp().getName();
        poiSummary.textParagraph(document.createParagraph(),
          this.getText("summaries.annualReportPlatform2018.unitName") + ": " + unitName);
        run.setText(this.getText("summaries.annualReport.LeadCenter") + ":");
        poiSummary.textLineBreak(document, 1);
        this.addParticipatingCenters();

        // section 1 - key results
        poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.keyResults"));
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.keyResults.crpProgress"));
        this.addExpectedCrp();
        this.addCrpProgressOutcomes();
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.keyResults.progressFlagships"));
        this.addAdjustmentDescription();
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.keyResults.dimensions"));
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

        // section 2 - variance from planned program
        poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.effectiveness"));
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.effectiveness.program"));
        this.addVariancePlanned();

        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.effectiveness.funding"));
        this.addFundingSummarize();
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.effectiveness.partnership"));
        this.addExternalPartnerships();
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.effectiveness.cross"));
        this.addCrossPartnerships();
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.effectiveness.mel"));
        this.addReportSynthesisMelia();
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("summaries.annualReport.effectiveness.efficiency"));
        this.addImprovingEfficiency();

        // section 3 - crp management
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead1Title(document.createParagraph(), this.getText("summaries.annualReport.management"));
        // this.addManagement();

        /* Create a landscape text Section */
        XWPFParagraph para = document.createParagraph();
        CTSectPr sectionTable = body.getSectPr();
        CTPageSz pageSizeTable = sectionTable.addNewPgSz();
        CTP ctpTable = para.getCTP();
        CTPPr brTable = ctpTable.addNewPPr();
        brTable.setSectPr(sectionTable);
        /* standard Letter page size */
        pageSizeTable.setOrient(STPageOrientation.LANDSCAPE);
        pageSizeTable.setW(BigInteger.valueOf(842 * 20));
        pageSizeTable.setH(BigInteger.valueOf(595 * 20));
        this.loadTablePMU();

        // Table a1
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead1Title(document.createParagraph(), "TABLES");
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableA.title"));
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableA1.title"));
        this.createTableA1();

        // Table a2
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableA2.title"));
        this.createTableA2();

        // Table b
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableB.title"));
        this.createTableB();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableB.description1"));
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableB.description2"));

        // Table c
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("annualReport.ccDimensions.tableCTitle"));
        this.createTableC();

        // Table d
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableD.title"));

        // Table d1
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableD1.title"));
        this.createTableD1();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableD1.footer"));

        // Table d2
        poiSummary.textHead3Title(document.createParagraph(), this.getText("summaries.annualReport.tableD2.title"));
        this.createTableD2();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.annualReport.tableD2.footer"));

        // Table e
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(),
          this.getText("annualReport.ccDimensions.tableETitle", new String[] {String.valueOf(this.getSelectedYear())}));
        this.createTableE();
        poiSummary.textNotes(document.createParagraph(), this.getText("intellectualAsset.title.help2017"));

        // Table f
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("financialPlan.tableF.title2017"));
        this.createTableF();
        poiSummary.textNotes(document.createParagraph(), this.getText("financialPlan.tableF.expenditureArea.help"));
        poiSummary.textNotes(document.createParagraph(), this.getText("financialPlan.tableF.expenditureArea.help2017"));
        poiSummary.textNotes(document.createParagraph(),
          "**" + this.getText("summaries.annualReport.tableJ.description.help2"));


        // Table g
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableG.title"));
        this.createTableG();

        // Table h
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableH.title"));
        this.createTableH();
        poiSummary.textNotes(document.createParagraph(), this.getText("summaries.powb.tableG.description.help"));

        // Table i1
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableI.title"));
        poiSummary.textHead3Title(document.createParagraph(),
          this.getText("annualReport.melia.tableI.title", new String[] {String.valueOf(this.getSelectedYear())})
            + " POWB");
        this.createTableI1();

        // Table i2
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead3Title(document.createParagraph(), this.getText("annualReport.melia.evaluation.title"));
        this.createTableI2();

        // Table j
        poiSummary.textLineBreak(document, 1);
        poiSummary.textHead2Title(document.createParagraph(), this.getText("summaries.annualReport.tableJ.title"));
        this.createTableJ();
        poiSummary.textNotes(document.createParagraph(),
          this.getText("summaries.annualReport.tableJ.description.help2"));

        poiSummary.textNotes(document.createParagraph(),
          "*" + this.getText("summaries.annualReport.tableJ.description.help"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        document.write(os);
        bytesDOC = os.toByteArray();
        os.close();
        document.close();
      } catch (Exception e) {
        LOG.error("Error generating " + this.getFileName() + ". Exception: " + e.getMessage());
        throw e;
      }
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

  @Override
  public String getFileName() {
    StringBuffer fileName = new StringBuffer();
    String crp = this.getLoggedCrp().getAcronym();
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
    fileName.append(this.getSelectedYear() + "_");
    fileName.append(crp + "-");
    fileName.append("AR_");
    fileName.append(sdf.format(date));
    fileName.append(".docx");
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

  @Override
  public InputStream getInputStream() {
    if (inputStream == null) {
      inputStream = new ByteArrayInputStream(bytesDOC);
    }
    return inputStream;
  }


  public List<Deliverable> getProjectDeliverables(Project project, Phase phase) {

    List<Deliverable> deliverables = new ArrayList<>();


    deliverables = project.getDeliverables().stream()
      .filter(d -> d.isActive() && d.getProject() != null && d.getProject().isActive()
        && d.getProject().getGlobalUnitProjects().stream()
          .filter(gup -> gup.isActive() && gup.getGlobalUnit().getId().equals(this.getLoggedCrp().getId()))
          .collect(Collectors.toList()).size() > 0
        && d.getDeliverableInfo(phase) != null && d.getDeliverableInfo(phase).getStatus() != null
        && ((d.getDeliverableInfo(phase).getStatus().intValue() == Integer
          .parseInt(ProjectStatusEnum.Complete.getStatusId())
          && (d.getDeliverableInfo(phase).getYear() >= this.getCurrentCycleYear()
            || (d.getDeliverableInfo(phase).getNewExpectedYear() != null
              && d.getDeliverableInfo(phase).getNewExpectedYear().intValue() >= this.getCurrentCycleYear())))
          || (d.getDeliverableInfo(phase).getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Extended.getStatusId())
            && (d.getDeliverableInfo(phase).getNewExpectedYear() != null
              && d.getDeliverableInfo(phase).getNewExpectedYear().intValue() == this.getCurrentCycleYear()))
          || (d.getDeliverableInfo(phase).getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Cancelled.getStatusId())
            && d.getDeliverableInfo().isActive()
            && (d.getDeliverableInfo(phase).getYear() == this.getCurrentCycleYear()
              || (d.getDeliverableInfo(phase).getNewExpectedYear() != null
                && d.getDeliverableInfo(phase).getNewExpectedYear().intValue() == this.getCurrentCycleYear()))))
        && (d.getDeliverableInfo(phase).getStatus().intValue() == Integer
          .parseInt(ProjectStatusEnum.Extended.getStatusId())
          || d.getDeliverableInfo(phase).getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Complete.getStatusId())
          || d.getDeliverableInfo(phase).getStatus().intValue() == Integer
            .parseInt(ProjectStatusEnum.Cancelled.getStatusId())))
      .collect(Collectors.toList());


    return deliverables;
  }

  public ReportSynthesisFlagshipProgressMilestone getReportSynthesisFlagshipProgressProgram(Long crpMilestoneID,
    Long crpProgramID) {
    List<ReportSynthesisFlagshipProgressMilestone> flagshipProgressMilestonesPrev =
      reportSynthesisFlagshipProgressMilestoneManager.findByProgram(crpProgramID);
    List<ReportSynthesisFlagshipProgressMilestone> flagshipProgressMilestones = flagshipProgressMilestonesPrev.stream()
      .filter(c -> c.getCrpMilestone().getId().longValue() == crpMilestoneID.longValue() && c.isActive())
      .collect(Collectors.toList());
    if (!flagshipProgressMilestones.isEmpty()) {
      return flagshipProgressMilestones.get(0);
    }
    return new ReportSynthesisFlagshipProgressMilestone();
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
    Set<Project> myProjects = new HashSet<Project>();
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
        if (!crpProgram.getMilestones().isEmpty()) {
          validOutcomes.add(crpProgramOutcome);
        }
      }
      crpProgram.setOutcomes(validOutcomes);
      this.loadFlagShipBudgetInfo(crpProgram);

    }
  }

  @Override
  public void prepare() {
    this.setGeneralParameters();

    pmuInstitution = this.getLoggedCrp().getLiaisonInstitutions().stream()
      .filter(c -> c.getCrpProgram() == null && c.getAcronym().equals("PMU")).collect(Collectors.toList()).get(0);

    reportSynthesisPMU = reportSynthesisManager.findSynthesis(this.getSelectedPhase().getId(), pmuInstitution.getId());


    // Get the list of liaison institutions Flagships.
    flagshipLiaisonInstitutions = this.getLoggedCrp().getLiaisonInstitutions().stream()
      .filter(c -> c.getCrpProgram() != null && c.isActive()
        && c.getCrpProgram().getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
      .collect(Collectors.toList());
    flagshipLiaisonInstitutions.sort(Comparator.comparing(LiaisonInstitution::getAcronym));

    // Calculate time to generate report
    startTime = System.currentTimeMillis();
    LOG.info(
      "Start report download: " + this.getFileName() + ". User: " + this.getCurrentUser().getComposedCompleteName()
        + ". CRP: " + this.getLoggedCrp().getAcronym() + ". Cycle: " + this.getSelectedCycle());
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }


}
