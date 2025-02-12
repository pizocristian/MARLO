/**
 * ***************************************************************
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
 * ***************************************************************
 */
package org.cgiar.ccafs.marlo.action.projects;

import org.cgiar.ccafs.marlo.action.BaseAction;
import org.cgiar.ccafs.marlo.action.deliverable.dto.DeliverableSearchSummary;
import org.cgiar.ccafs.marlo.config.APConstants;
import org.cgiar.ccafs.marlo.data.manager.ActivityManager;
import org.cgiar.ccafs.marlo.data.manager.AuditLogManager;
import org.cgiar.ccafs.marlo.data.manager.CgiarCrossCuttingMarkerManager;
import org.cgiar.ccafs.marlo.data.manager.CrpClusterKeyOutputManager;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramManager;
import org.cgiar.ccafs.marlo.data.manager.CrpProgramOutcomeManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableActivityManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableClusterParticipantManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableCrossCuttingMarkerManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableCrpManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableCrpOutcomeManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableDataSharingFileManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableDisseminationManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableFundingSourceManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableGenderLevelManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableGeographicRegionManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableGeographicScopeManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableInfoManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableIntellectualAssetManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableLocationManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableMetadataElementManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableParticipantManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverablePartnerTypeManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableProjectOutcomeManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverablePublicationMetadataManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableQualityAnswerManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableQualityCheckManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableTraineesIndicatorManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableTypeManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableUserManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableUserPartnershipManager;
import org.cgiar.ccafs.marlo.data.manager.DeliverableUserPartnershipPersonManager;
import org.cgiar.ccafs.marlo.data.manager.FeedbackQACommentManager;
import org.cgiar.ccafs.marlo.data.manager.FeedbackQACommentableFieldsManager;
import org.cgiar.ccafs.marlo.data.manager.FileDBManager;
import org.cgiar.ccafs.marlo.data.manager.FundingSourceManager;
import org.cgiar.ccafs.marlo.data.manager.GenderTypeManager;
import org.cgiar.ccafs.marlo.data.manager.GlobalUnitManager;
import org.cgiar.ccafs.marlo.data.manager.InstitutionManager;
import org.cgiar.ccafs.marlo.data.manager.LocElementManager;
import org.cgiar.ccafs.marlo.data.manager.MetadataElementManager;
import org.cgiar.ccafs.marlo.data.manager.PartnerDivisionManager;
import org.cgiar.ccafs.marlo.data.manager.PhaseManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectDeliverableSharedManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectLp6ContributionDeliverableManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectOutcomeManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectPartnerManager;
import org.cgiar.ccafs.marlo.data.manager.ProjectPartnerPersonManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndFillingTypeManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndGenderYouthFocusLevelManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndGeographicScopeManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndPatentStatusManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndTrainingTermManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndTypeActivityManager;
import org.cgiar.ccafs.marlo.data.manager.RepIndTypeParticipantManager;
import org.cgiar.ccafs.marlo.data.manager.RepositoryChannelManager;
import org.cgiar.ccafs.marlo.data.manager.UserManager;
import org.cgiar.ccafs.marlo.data.model.Activity;
import org.cgiar.ccafs.marlo.data.model.CgiarCrossCuttingMarker;
import org.cgiar.ccafs.marlo.data.model.CrpClusterKeyOutput;
import org.cgiar.ccafs.marlo.data.model.CrpProgram;
import org.cgiar.ccafs.marlo.data.model.CrpProgramOutcome;
import org.cgiar.ccafs.marlo.data.model.Deliverable;
import org.cgiar.ccafs.marlo.data.model.DeliverableActivity;
import org.cgiar.ccafs.marlo.data.model.DeliverableClusterParticipant;
import org.cgiar.ccafs.marlo.data.model.DeliverableCrossCuttingMarker;
import org.cgiar.ccafs.marlo.data.model.DeliverableCrp;
import org.cgiar.ccafs.marlo.data.model.DeliverableCrpOutcome;
import org.cgiar.ccafs.marlo.data.model.DeliverableDataSharingFile;
import org.cgiar.ccafs.marlo.data.model.DeliverableDissemination;
import org.cgiar.ccafs.marlo.data.model.DeliverableFile;
import org.cgiar.ccafs.marlo.data.model.DeliverableFundingSource;
import org.cgiar.ccafs.marlo.data.model.DeliverableGenderLevel;
import org.cgiar.ccafs.marlo.data.model.DeliverableGeographicRegion;
import org.cgiar.ccafs.marlo.data.model.DeliverableGeographicScope;
import org.cgiar.ccafs.marlo.data.model.DeliverableInfo;
import org.cgiar.ccafs.marlo.data.model.DeliverableLocation;
import org.cgiar.ccafs.marlo.data.model.DeliverableMetadataElement;
import org.cgiar.ccafs.marlo.data.model.DeliverableParticipant;
import org.cgiar.ccafs.marlo.data.model.DeliverablePartnerType;
import org.cgiar.ccafs.marlo.data.model.DeliverableProjectOutcome;
import org.cgiar.ccafs.marlo.data.model.DeliverableQualityAnswer;
import org.cgiar.ccafs.marlo.data.model.DeliverableQualityCheck;
import org.cgiar.ccafs.marlo.data.model.DeliverableTraineesIndicator;
import org.cgiar.ccafs.marlo.data.model.DeliverableType;
import org.cgiar.ccafs.marlo.data.model.DeliverableUser;
import org.cgiar.ccafs.marlo.data.model.DeliverableUserPartnership;
import org.cgiar.ccafs.marlo.data.model.DeliverableUserPartnershipPerson;
import org.cgiar.ccafs.marlo.data.model.FeedbackQAComment;
import org.cgiar.ccafs.marlo.data.model.FeedbackQACommentableFields;
import org.cgiar.ccafs.marlo.data.model.FileDB;
import org.cgiar.ccafs.marlo.data.model.FundingSource;
import org.cgiar.ccafs.marlo.data.model.GenderType;
import org.cgiar.ccafs.marlo.data.model.GlobalUnit;
import org.cgiar.ccafs.marlo.data.model.Institution;
import org.cgiar.ccafs.marlo.data.model.LocElement;
import org.cgiar.ccafs.marlo.data.model.PartnerDivision;
import org.cgiar.ccafs.marlo.data.model.Phase;
import org.cgiar.ccafs.marlo.data.model.ProgramType;
import org.cgiar.ccafs.marlo.data.model.Project;
import org.cgiar.ccafs.marlo.data.model.ProjectBudget;
import org.cgiar.ccafs.marlo.data.model.ProjectDeliverableShared;
import org.cgiar.ccafs.marlo.data.model.ProjectFocus;
import org.cgiar.ccafs.marlo.data.model.ProjectLp6Contribution;
import org.cgiar.ccafs.marlo.data.model.ProjectLp6ContributionDeliverable;
import org.cgiar.ccafs.marlo.data.model.ProjectOutcome;
import org.cgiar.ccafs.marlo.data.model.ProjectPartner;
import org.cgiar.ccafs.marlo.data.model.ProjectPartnerPerson;
import org.cgiar.ccafs.marlo.data.model.ProjectPhase;
import org.cgiar.ccafs.marlo.data.model.ProjectStatusEnum;
import org.cgiar.ccafs.marlo.data.model.RepIndFillingType;
import org.cgiar.ccafs.marlo.data.model.RepIndGenderYouthFocusLevel;
import org.cgiar.ccafs.marlo.data.model.RepIndGeographicScope;
import org.cgiar.ccafs.marlo.data.model.RepIndPatentStatus;
import org.cgiar.ccafs.marlo.data.model.RepIndTrainingTerm;
import org.cgiar.ccafs.marlo.data.model.RepIndTypeActivity;
import org.cgiar.ccafs.marlo.data.model.RepIndTypeParticipant;
import org.cgiar.ccafs.marlo.data.model.RepositoryChannel;
import org.cgiar.ccafs.marlo.data.model.User;
import org.cgiar.ccafs.marlo.security.Permission;
import org.cgiar.ccafs.marlo.utils.APConfig;
import org.cgiar.ccafs.marlo.utils.AutoSaveReader;
import org.cgiar.ccafs.marlo.utils.doi.DOIService;
import org.cgiar.ccafs.marlo.validation.projects.DeliverableValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 * @author avalencia - CCAFS
 * @date Nov 8, 2017
 * @time 9:48:25 AM: Added repositoryChannel List from Database
 * @time 1:36:16 PM: Fix -1 id error
 */
public class DeliverableAction extends BaseAction {

  private static final long serialVersionUID = -4474372683580321612L;

  private static final long[] EMPTY_ARRAY = {};

  // Logger
  private final Logger logger = LoggerFactory.getLogger(DeliverableAction.class);

  // Managers
  private PhaseManager phaseManager;
  private ActivityManager activityManager;
  private AuditLogManager auditLogManager;
  private GlobalUnitManager crpManager;
  private DeliverableDataSharingFileManager deliverableDataSharingFileManager;
  private DeliverablePublicationMetadataManager deliverablePublicationMetadataManager;
  private DeliverableFundingSourceManager deliverableFundingSourceManager;
  private DeliverableUserManager deliverableUserManager;
  private DeliverableCrpManager deliverableCrpManager;
  private DeliverableGenderLevelManager deliverableGenderLevelManager;
  private DeliverableManager deliverableManager;
  private DeliverableInfoManager deliverableInfoManager;
  private DeliverableMetadataElementManager deliverableMetadataElementManager;
  private InstitutionManager institutionManager;
  private DeliverableQualityAnswerManager deliverableQualityAnswerManager;
  private DeliverableQualityCheckManager deliverableQualityCheckManager;
  private DeliverableDisseminationManager deliverableDisseminationManager;
  private DeliverableTypeManager deliverableTypeManager;
  private CrpProgramManager crpProgramManager;
  private FileDBManager fileDBManager;
  private GenderTypeManager genderTypeManager;
  private FundingSourceManager fundingSourceManager;
  private MetadataElementManager metadataElementManager;
  private ProjectManager projectManager;
  private ProjectPartnerManager projectPartnerManager;
  private ProjectPartnerPersonManager projectPartnerPersonManager;
  private PartnerDivisionManager partnerDivisionManager;
  private RepositoryChannelManager repositoryChannelManager;
  private DeliverableIntellectualAssetManager deliverableIntellectualAssetManager;
  private DeliverableParticipantManager deliverableParticipantManager;
  private DeliverableClusterParticipantManager deliverableClusterParticipantManager;
  private RepIndTypeActivityManager repIndTypeActivityManager;
  private RepIndTypeParticipantManager repIndTypeParticipantManager;
  private RepIndGeographicScopeManager repIndGeographicScopeManager;
  private LocElementManager locElementManager;
  private RepIndFillingTypeManager repIndFillingTypeManager;
  private RepIndPatentStatusManager repIndPatentStatusManager;
  private DeliverableLocationManager deliverableLocationManager;
  private CrpClusterKeyOutputManager crpClusterKeyOutputManager;
  private CrpProgramOutcomeManager crpProgramOutcomeManager;
  private RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager;
  private CgiarCrossCuttingMarkerManager cgiarCrossCuttingMarkerManager;
  private DeliverableCrossCuttingMarkerManager deliverableCrossCuttingMarkerManager;
  private ProjectLp6ContributionDeliverableManager projectLp6ContributionDeliverableManager;
  private RepIndTrainingTermManager repIndTrainingTermManager;
  private DeliverableGeographicScopeManager deliverableGeographicScopeManager;
  private DeliverableUserPartnershipManager deliverableUserPartnershipManager;
  private DeliverablePartnerTypeManager deliverablePartnerTypeManager;
  private DeliverableUserPartnershipPersonManager deliverableUserPartnershipPersonManager;
  private UserManager userManager;
  private DeliverableActivityManager deliverableActivityManager;
  private ProjectDeliverableSharedManager projectDeliverableSharedManager;
  private ProjectOutcomeManager projectOutcomeManager;
  private DeliverableProjectOutcomeManager deliverableProjectOutcomeManager;
  private DeliverableCrpOutcomeManager deliverableCrpOutcomeManager;
  private FeedbackQACommentManager feedbackQACommentManager;
  private FeedbackQACommentableFieldsManager feedbackQACommentableFieldsManager;
  private DeliverableTraineesIndicatorManager deliverableTraineesIndicatorManager;


  // Variables
  private List<DeliverableQualityAnswer> answers;
  private List<DeliverableQualityAnswer> answersDataDic;
  private List<RepositoryChannel> repositoryChannels;
  private ArrayList<GlobalUnit> crps;
  private ArrayList<CrpProgram> programs;
  private Deliverable deliverable;
  private long deliverableID;
  private List<DeliverableType> deliverableSubTypes;
  private Boolean has_specific_management_deliverables;
  private Boolean isManagingPartnerPersonRequerid;
  private List<DeliverableType> deliverableTypeParent;
  private DeliverableValidator deliverableValidator;
  private List<FundingSource> fundingSources;
  private List<Activity> activities;
  private List<GenderType> genderLevels;
  private List<CrpClusterKeyOutput> keyOutputs;
  private GlobalUnit loggedCrp;
  private List<ProjectPartnerPerson> partnerPersons;
  private List<ProjectPartner> partners;
  private Project project;
  private long projectID;
  private List<ProjectOutcome> projectOutcome;
  private List<CrpProgramOutcome> programOutcomes;
  private List<ProjectFocus> projectPrograms;
  private Map<String, String> status;
  private String transaction;
  private int indexTab;
  private List<PartnerDivision> divisions;
  private List<RepIndTypeActivity> repIndTypeActivities;
  private List<RepIndTrainingTerm> repIndTrainingTerms;
  private List<RepIndTypeParticipant> repIndTypeParticipants;
  private List<RepIndGeographicScope> repIndGeographicScopes;
  private List<LocElement> repIndRegions;
  private List<LocElement> countries;
  private List<RepIndFillingType> repIndFillingTypes;
  private List<RepIndPatentStatus> repIndPatentStatuses;
  private Map<String, String> statuses;
  private DeliverableGeographicRegionManager deliverableGeographicRegionManager;
  private List<CgiarCrossCuttingMarker> cgiarCrossCuttingMarkers;
  private List<Project> myProjects;
  private List<FeedbackQACommentableFields> feedbackComments;
  private boolean isDuplicated;
  private String DOI;
  private String handle;
  private String disseminationURL;


  private List<RepIndGenderYouthFocusLevel> focusLevels;
  // HJ 08/01/2019 new fileds Deliverable Partnerships
  private List<Institution> partnerInstitutions;

  private List<User> responsibleUsers;
  private Integer acceptationPercentage;
  private List<ProjectOutcome> projectOutcomes;
  private boolean existCurrentCluster;

  @Inject
  public DeliverableAction(APConfig config, DeliverableTypeManager deliverableTypeManager,
    DeliverableMetadataElementManager deliverableMetadataElementManager, DeliverableManager deliverableManager,
    GlobalUnitManager crpManager, ProjectManager projectManager,
    ProjectPartnerPersonManager projectPartnerPersonManager,
    ProjectLp6ContributionDeliverableManager projectLp6ContributionDeliverableManager, AuditLogManager auditLogManager,
    DeliverableValidator deliverableValidator, ProjectPartnerManager projectPartnerManager,
    FundingSourceManager fundingSourceManager, DeliverableFundingSourceManager deliverableFundingSourceManager,
    DeliverableGenderLevelManager deliverableGenderLevelManager,
    DeliverableQualityCheckManager deliverableQualityCheckManager, DeliverableCrpManager deliverableCrpManager,
    DeliverableQualityAnswerManager deliverableQualityAnswerManager,
    DeliverableDataSharingFileManager deliverableDataSharingFileManager, FileDBManager fileDBManager,
    DeliverableUserManager deliverableUserManager, GenderTypeManager genderTypeManager,
    DeliverablePublicationMetadataManager deliverablePublicationMetadataManager, InstitutionManager institutionManager,
    MetadataElementManager metadataElementManager, DeliverableDisseminationManager deliverableDisseminationManager,
    PartnerDivisionManager partnerDivisionManager, RepositoryChannelManager repositoryChannelManager,
    DeliverableInfoManager deliverableInfoManager, CrpProgramManager crpProgramManager,
    DeliverableIntellectualAssetManager deliverableIntellectualAssetManager,
    RepIndTypeActivityManager repIndTypeActivityManager, RepIndTypeParticipantManager repIndTypeParticipantManager,
    RepIndGeographicScopeManager repIndGeographicScopeManager, LocElementManager locElementManager,
    DeliverableParticipantManager deliverableParticipantManager, RepIndFillingTypeManager repIndFillingTypeManager,
    RepIndPatentStatusManager repIndPatentStatusManager, DeliverableLocationManager deliverableLocationManager,
    DeliverableGeographicRegionManager deliverableGeographicRegionManager,
    CrpClusterKeyOutputManager crpClusterKeyOutputManager,
    RepIndGenderYouthFocusLevelManager repIndGenderYouthFocusLevelManager,
    CgiarCrossCuttingMarkerManager cgiarCrossCuttingMarkerManager,
    DeliverableCrossCuttingMarkerManager deliverableCrossCuttingMarkerManager,
    RepIndTrainingTermManager repIndTrainingTermManager, ActivityManager activityManager,
    DeliverableGeographicScopeManager deliverableGeographicScopeManager,
    DeliverableUserPartnershipManager deliverableUserPartnershipManager,
    DeliverablePartnerTypeManager deliverablePartnerTypeManager, UserManager userManager,
    DeliverableUserPartnershipPersonManager deliverableUserPartnershipPersonManager,
    CrpProgramOutcomeManager crpProgramOutcomeManager, DeliverableActivityManager deliverableActivityManager,
    ProjectDeliverableSharedManager projectDeliverableSharedManager, PhaseManager phaseManager,
    ProjectOutcomeManager projectOutcomeManager, DeliverableProjectOutcomeManager deliverableProjectOutcomeManager,
    DeliverableCrpOutcomeManager deliverableCrpOutcomeManager,
    FeedbackQACommentableFieldsManager feedbackQACommentableFieldsManager,
    FeedbackQACommentManager feedbackQACommentManager,
    DeliverableClusterParticipantManager deliverableClusterParticipantManager,
    DeliverableTraineesIndicatorManager deliverableTraineesIndicatorManager) {
    super(config);
    this.activityManager = activityManager;
    this.deliverableManager = deliverableManager;
    this.deliverableTypeManager = deliverableTypeManager;
    this.crpManager = crpManager;
    this.deliverableUserManager = deliverableUserManager;
    this.projectManager = projectManager;
    this.deliverableInfoManager = deliverableInfoManager;
    this.institutionManager = institutionManager;
    this.deliverableCrpManager = deliverableCrpManager;
    this.deliverablePublicationMetadataManager = deliverablePublicationMetadataManager;
    this.projectPartnerPersonManager = projectPartnerPersonManager;
    this.auditLogManager = auditLogManager;
    this.deliverableValidator = deliverableValidator;
    this.projectPartnerManager = projectPartnerManager;
    this.projectLp6ContributionDeliverableManager = projectLp6ContributionDeliverableManager;
    this.genderTypeManager = genderTypeManager;
    this.deliverableFundingSourceManager = deliverableFundingSourceManager;
    this.fundingSourceManager = fundingSourceManager;
    this.deliverableGenderLevelManager = deliverableGenderLevelManager;
    this.deliverableQualityCheckManager = deliverableQualityCheckManager;
    this.deliverableQualityAnswerManager = deliverableQualityAnswerManager;
    this.fileDBManager = fileDBManager;
    this.deliverableDataSharingFileManager = deliverableDataSharingFileManager;
    this.metadataElementManager = metadataElementManager;
    this.deliverableMetadataElementManager = deliverableMetadataElementManager;
    this.deliverableDisseminationManager = deliverableDisseminationManager;
    this.partnerDivisionManager = partnerDivisionManager;
    this.repositoryChannelManager = repositoryChannelManager;
    this.crpProgramManager = crpProgramManager;
    this.deliverableIntellectualAssetManager = deliverableIntellectualAssetManager;
    this.deliverableParticipantManager = deliverableParticipantManager;
    this.deliverableClusterParticipantManager = deliverableClusterParticipantManager;
    this.repIndTypeActivityManager = repIndTypeActivityManager;
    this.repIndTypeParticipantManager = repIndTypeParticipantManager;
    this.repIndGeographicScopeManager = repIndGeographicScopeManager;
    this.locElementManager = locElementManager;
    this.repIndFillingTypeManager = repIndFillingTypeManager;
    this.repIndPatentStatusManager = repIndPatentStatusManager;
    this.deliverableLocationManager = deliverableLocationManager;
    this.deliverableGeographicRegionManager = deliverableGeographicRegionManager;
    this.crpClusterKeyOutputManager = crpClusterKeyOutputManager;
    this.repIndGenderYouthFocusLevelManager = repIndGenderYouthFocusLevelManager;
    this.cgiarCrossCuttingMarkerManager = cgiarCrossCuttingMarkerManager;
    this.deliverableCrossCuttingMarkerManager = deliverableCrossCuttingMarkerManager;
    this.repIndTrainingTermManager = repIndTrainingTermManager;
    this.deliverableGeographicScopeManager = deliverableGeographicScopeManager;
    this.deliverableUserPartnershipManager = deliverableUserPartnershipManager;
    this.deliverablePartnerTypeManager = deliverablePartnerTypeManager;
    this.userManager = userManager;
    this.deliverableUserPartnershipPersonManager = deliverableUserPartnershipPersonManager;
    this.crpProgramOutcomeManager = crpProgramOutcomeManager;
    this.deliverableActivityManager = deliverableActivityManager;
    this.projectDeliverableSharedManager = projectDeliverableSharedManager;
    this.phaseManager = phaseManager;
    this.projectOutcomeManager = projectOutcomeManager;
    this.deliverableProjectOutcomeManager = deliverableProjectOutcomeManager;
    this.deliverableCrpOutcomeManager = deliverableCrpOutcomeManager;
    this.feedbackQACommentableFieldsManager = feedbackQACommentableFieldsManager;
    this.feedbackQACommentManager = feedbackQACommentManager;
    this.deliverableTraineesIndicatorManager = deliverableTraineesIndicatorManager;
  }

  /**
   * @return current Deliverable cluster participant for actual deliverable
   */
  public DeliverableClusterParticipant actualDeliverableClusterParticipant() {
    DeliverableClusterParticipant clusterParticipant = null;
    try {
      clusterParticipant =
        deliverableClusterParticipantManager.getDeliverableClusterParticipantByDeliverableProjectPhase(deliverableID,
          projectID, this.getActualPhase().getId()).get(0);
    } catch (Exception e) {
      Log.error(e + " error getting actual cluster participant ID");
    }
    if (clusterParticipant != null && clusterParticipant.getId() != null) {
      return clusterParticipant;
    } else {
      return null;
    }
  }

  @Override
  public String cancel() {

    Path path = this.getAutoSaveFilePath();

    if (path.toFile().exists()) {
      path.toFile().delete();
    }
    deliverable.getDeliverableInfo().setCrpClusterKeyOutput(null);

    this.setDraft(false);
    Collection<String> messages = this.getActionMessages();
    if (!messages.isEmpty()) {
      messages.iterator().next();
      this.setActionMessages(null);
      this.addActionMessage("draft:" + this.getText("cancel.autoSave"));
    } else {
      this.addActionMessage("draft:" + this.getText("cancel.autoSave"));
    }
    messages = this.getActionMessages();

    return SUCCESS;
  }

  public Boolean candEditExpectedYear(long deliverableID) {
    Deliverable deliverable = deliverableManager.getDeliverableById(deliverableID);

    if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == null) {
      return false;
    }

    if (this.isReportingActive() || this.isUpKeepActive()) {
      if (((deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != null
        && deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != -1)
        && deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() == Integer
          .parseInt(ProjectStatusEnum.Complete.getStatusId()))
        || deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() == Integer
          .parseInt(ProjectStatusEnum.Extended.getStatusId())) {
        return true;
      }

    } else {
      if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() == Integer
        .parseInt(ProjectStatusEnum.Extended.getStatusId())
        || deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() == Integer
          .parseInt(ProjectStatusEnum.Cancelled.getStatusId())) {
        return true;
      }
    }

    return false;

  }

  public Boolean candEditYear(long deliverableID) {
    Deliverable deliverable = deliverableManager.getDeliverableById(deliverableID);
    if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == null) {
      return true;
    }

    if (deliverable.getDeliverableInfo(this.getActualPhase()).getYear() >= this.getActualPhase().getYear()) {
      return true;
    }

    if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() == Integer
      .parseInt(ProjectStatusEnum.Extended.getStatusId())
      || deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() == Integer
        .parseInt(ProjectStatusEnum.Ongoing.getStatusId())) {
      return false;
    }

    if (this.isDeliverableNew(deliverableID)) {
      return true;
    } else {
      return false;
    }

  }

  private void deleteDeliverableLocations(List<DeliverableLocation> locationsDB) {
    if (locationsDB != null) {
      for (DeliverableLocation deliverableLocation : locationsDB) {
        deliverableLocationManager.deleteDeliverableLocation(deliverableLocation.getId());
      }
    }
  }

  /**
   * Delete all LocElements Records when Geographic Scope is Global or NULL
   *
   * @param deliverable
   * @param phase
   */
  public void deleteLocElements(Deliverable deliverable, Phase phase, boolean isCountry) {
    if (isCountry) {
      if (deliverable.getDeliverableLocations() != null && deliverable.getDeliverableLocations().size() > 0) {

        List<DeliverableLocation> regionPrev = new ArrayList<>(deliverable.getDeliverableLocations().stream()
          .filter(nu -> nu.isActive() && nu.getPhase().getId().equals(phase.getId())).collect(Collectors.toList()));

        for (DeliverableLocation region : regionPrev) {

          deliverableLocationManager.deleteDeliverableLocation(region.getId());

        }
      }
    } else {
      if (deliverable.getDeliverableGeographicRegions() != null
        && deliverable.getDeliverableGeographicRegions().size() > 0) {

        List<DeliverableGeographicRegion> regionPrev =
          new ArrayList<>(deliverable.getDeliverableGeographicRegions().stream()
            .filter(nu -> nu.isActive() && nu.getPhase().getId().equals(phase.getId())).collect(Collectors.toList()));

        for (DeliverableGeographicRegion region : regionPrev) {

          deliverableGeographicRegionManager.deleteDeliverableGeographicRegion(region.getId());

        }

      }
    }
  }

  /**
   * @return true if the current cluster exist in cluster participant table
   */
  public boolean existCurrentClusterDB() {
    DeliverableClusterParticipant clusterParticipant = null;
    try {
      clusterParticipant =
        deliverableClusterParticipantManager.getDeliverableClusterParticipantByDeliverableProjectPhase(deliverableID,
          projectID, this.getActualPhase().getId()).get(0);
    } catch (Exception e) {
      Log.error(e + " error getting actual cluster participant ID");
    }
    if (clusterParticipant != null && clusterParticipant.getId() != null) {
      return true;
    } else {
      return false;
    }
  }

  /*
   * Fill the cluster participants list if the deliverable has shared clusters
   */
  public void fillClusterParticipantsList() {
    if (deliverable.getSharedDeliverables() != null && !deliverable.getSharedDeliverables().isEmpty()) {
      for (ProjectDeliverableShared projectDeliverable : deliverable.getSharedDeliverables()) {

        if (projectDeliverable.getProject().getId() != null) {
          Project project = projectManager.getProjectById(projectDeliverable.getProject().getId());
          if (project != null) {
            projectDeliverable.setProject(project);
          }
        }
        DeliverableClusterParticipant participant = new DeliverableClusterParticipant();
        participant.setProject(projectDeliverable.getProject());
        participant.setDeliverable(deliverable);
        participant.setPhase(this.getActualPhase());

        boolean projectExists = false;
        boolean actualProjectExists = false;
        if (deliverable.getClusterParticipant() != null && !deliverable.getClusterParticipant().isEmpty()) {
          for (DeliverableClusterParticipant clusterParticipant : deliverable.getClusterParticipant()) {
            if (clusterParticipant.getProject() != null && clusterParticipant.getProject().getId() != null
              && clusterParticipant.getProject().getId().equals(projectDeliverable.getProject().getId())) {
              projectExists = true;
            }

            if (clusterParticipant.getProject() != null && clusterParticipant.getProject().getId() != null
              && clusterParticipant.getProject().getId().equals(project.getId())) {
              actualProjectExists = true;
            }
          }
        }
        if (!projectExists) {
          participant = deliverableClusterParticipantManager.saveDeliverableClusterParticipant(participant);
          deliverable.getClusterParticipant().add(participant);
        }

        if (!actualProjectExists) {
          DeliverableClusterParticipant thisClusterParticipant = new DeliverableClusterParticipant();
          thisClusterParticipant.setProject(project);
          thisClusterParticipant.setDeliverable(deliverable);
          thisClusterParticipant.setPhase(this.getActualPhase());
          thisClusterParticipant =
            deliverableClusterParticipantManager.saveDeliverableClusterParticipant(thisClusterParticipant);
          deliverable.getClusterParticipant().add(thisClusterParticipant);
        }
      }
    }
  }

  public Integer getAcceptationPercentage() {
    return acceptationPercentage;
  }

  public List<Activity> getActivities() {
    return activities;
  }

  /**
   * Get the ID of the clusterParticipantObject for this cluster in actual phase
   * 
   * @return clusterParticipantID or 0 if the response is empty
   */
  public long getActualClusterParticipantID() {
    DeliverableClusterParticipant clusterParticipant = null;
    try {
      clusterParticipant =
        deliverableClusterParticipantManager.getDeliverableClusterParticipantByDeliverableProjectPhase(deliverableID,
          projectID, this.getActualPhase().getId()).get(0);
    } catch (Exception e) {
      Log.error(e + " error getting actual cluster participant ID");
    }
    if (clusterParticipant != null && clusterParticipant.getId() != null) {
      return clusterParticipant.getId();
    } else {
      return 0;
    }
  }

  public List<DeliverableQualityAnswer> getAnswers() {
    return answers;
  }


  public List<DeliverableQualityAnswer> getAnswersDataDic() {
    return answersDataDic;
  }


  private Path getAutoSaveFilePath() {

    // get the class simple name
    String composedClassName = deliverable.getClass().getSimpleName();
    // get the action name and replace / for _
    String actionFile = this.getActionName().replace("/", "_");
    // concatane name and add the .json extension
    String autoSaveFile = deliverable.getId() + "_" + composedClassName + "_" + this.getActualPhase().getName() + "_"
      + this.getActualPhase().getYear() + "_" + actionFile + ".json";

    return Paths.get(config.getAutoSaveFolder() + autoSaveFile);

  }

  public List<CgiarCrossCuttingMarker> getCgiarCrossCuttingMarkers() {
    return cgiarCrossCuttingMarkers;
  }

  public List<LocElement> getCountries() {
    return countries;
  }

  public ArrayList<GlobalUnit> getCrps() {
    return crps;
  }

  public Deliverable getDeliverable() {
    return deliverable;
  }

  /**
   * Get the information for the Cross Cutting marker in the form
   *
   * @param markerID
   * @return
   */
  public DeliverableCrossCuttingMarker getDeliverableCrossCuttingMarker(long markerID) {
    DeliverableCrossCuttingMarker crossCuttingMarker = new DeliverableCrossCuttingMarker();
    if (this.isDraft()) {
      // Cgiar Cross Cutting Markers Autosave
      if (deliverable.getCrossCuttingMarkers() != null) {
        for (DeliverableCrossCuttingMarker deliverableCrossCuttingMarker : deliverable.getCrossCuttingMarkers()) {
          if (deliverableCrossCuttingMarker.getCgiarCrossCuttingMarker().getId() == markerID) {
            crossCuttingMarker = deliverableCrossCuttingMarker;
          }
        }
      }
    } else {
      crossCuttingMarker = deliverableCrossCuttingMarkerManager.getDeliverableCrossCuttingMarkerId(deliverableID,
        markerID, this.getActualPhase().getId());
    }
    if (crossCuttingMarker != null) {
      return crossCuttingMarker;
    } else {
      return null;
    }
  }

  public long getDeliverableID() {
    return deliverableID;
  }

  public List<Map<String, Object>> getDeliverablesSubTypes(long deliverableTypeID) {
    List<Map<String, Object>> subTypes = new ArrayList<>();
    Map<String, Object> keyOutput;

    DeliverableType deliverableType = deliverableTypeManager.getDeliverableTypeById(deliverableTypeID);
    if (deliverableType != null) {
      if (deliverableType.getDeliverableTypes() != null) {
        for (DeliverableType deliverableSubType : deliverableType.getDeliverableTypes().stream()
          .collect(Collectors.toList())) {
          keyOutput = new HashMap<String, Object>();
          keyOutput.put("id", deliverableSubType.getId());
          keyOutput.put("name", deliverableSubType.getName());
          keyOutput.put("description", deliverableSubType.getDescription());
          keyOutput.put("fair", deliverableSubType.getFair());
          subTypes.add(keyOutput);
        }
      }
    }
    return subTypes;

  }

  public List<DeliverableType> getDeliverableSubTypes() {
    return deliverableSubTypes;
  }

  public List<DeliverableType> getDeliverableTypeParent() {
    return deliverableTypeParent;
  }

  public String getDeliverableUrl(String fileType) {
    return config.getDownloadURL() + "/" + this.getDeliverableUrlPath(fileType).replace('\\', '/');
  }

  public String getDeliverableUrlPath(String fileType) {
    return config.getProjectsBaseFolder(this.getCrpSession()) + File.separator + deliverable.getId() + File.separator
      + "deliverable" + File.separator + fileType + File.separator;
  }

  public List<PartnerDivision> getDivisions() {
    return divisions;
  }

  public List<FeedbackQACommentableFields> getFeedbackComments() {
    return feedbackComments;
  }

  public List<RepIndGenderYouthFocusLevel> getFocusLevels() {
    return focusLevels;
  }

  public List<FundingSource> getFundingSources() {
    return fundingSources;
  }

  public List<GenderType> getGenderLevels() {
    return genderLevels;
  }

  public int getIndexTab() {
    return indexTab;
  }

  public List<CrpClusterKeyOutput> getKeyOutputs() {
    return keyOutputs;
  }

  public GlobalUnit getLoggedCrp() {
    return loggedCrp;
  }

  public List<Project> getMyProjects() {
    return myProjects;
  }

  public List<Institution> getPartnerInstitutions() {
    return partnerInstitutions;
  }

  public List<ProjectPartnerPerson> getPartnerPersons() {
    return partnerPersons;
  }


  public List<ProjectPartner> getPartners() {
    return partners;
  }


  /**
   * @return an array of integers.
   */
  public long[] getPersonsIds(DeliverableUserPartnership deliverableUserPartnership) {
    if (deliverableUserPartnership != null) {
      List<DeliverableUserPartnershipPerson> pPersons = deliverableUserPartnership.getPartnershipPersons().stream()
        .filter(pp -> pp.getUser() != null && pp.getUser().getId() != null && pp.getUser().getId() > 0)
        .collect(Collectors.toList());
      if (pPersons != null) {
        long[] ids = new long[pPersons.size()];
        for (int i = 0; i < ids.length; i++) {
          if (pPersons.get(i).getUser() != null && pPersons.get(i).getUser().getId() != null) {
            ids[i] = pPersons.get(i).getUser().getId();
          }
        }
        return ids;
      }
    }

    return EMPTY_ARRAY;
  }

  public List<CrpProgramOutcome> getProgramOutcomes() {
    return programOutcomes;
  }

  public ArrayList<CrpProgram> getPrograms() {
    return programs;
  }

  public Project getProject() {
    return project;
  }

  public long getProjectID() {
    return projectID;
  }

  public List<ProjectOutcome> getProjectOutcome() {
    return projectOutcome;
  }

  public List<ProjectOutcome> getProjectOutcomes() {
    return projectOutcomes;
  }

  public List<ProjectFocus> getProjectPrograms() {
    return projectPrograms;
  }

  public List<RepIndFillingType> getRepIndFillingTypes() {
    return repIndFillingTypes;
  }

  public List<RepIndGeographicScope> getRepIndGeographicScopes() {
    return repIndGeographicScopes;
  }

  public List<RepIndPatentStatus> getRepIndPatentStatuses() {
    return repIndPatentStatuses;
  }

  public List<LocElement> getRepIndRegions() {
    return repIndRegions;
  }

  public List<RepIndTrainingTerm> getRepIndTrainingTerms() {
    return repIndTrainingTerms;
  }

  public List<RepIndTypeActivity> getRepIndTypeActivities() {
    return repIndTypeActivities;
  }

  public List<RepIndTypeParticipant> getRepIndTypeParticipants() {
    return repIndTypeParticipants;
  }

  public List<RepositoryChannel> getRepositoryChannels() {
    return repositoryChannels;
  }

  public List<User> getResponsibleUsers() {
    return responsibleUsers;
  }

  public Map<String, String> getStatus() {
    return status;
  }

  public Map<String, String> getStatuses() {
    return statuses;
  }

  /**
   * Get Trainees indicator - IPI 2.x
   * 
   * @return CrpProgramOutcome IPI 2.x object
   */
  public CrpProgramOutcome getTraineesIndicator() {
    CrpProgramOutcome crpProgramOutcomeIPI = new CrpProgramOutcome();

    try {
      DeliverableTraineesIndicator deliverableTraineesIndicator = null;
      if (programOutcomes != null && !programOutcomes.isEmpty()) {

        deliverableTraineesIndicator = deliverableTraineesIndicatorManager.findAll().stream()
          .filter(d -> d != null && d.getPhase() != null && d.getPhase().getId().equals(this.getActualPhase().getId()))
          .collect(Collectors.toList()).get(0);

        if (deliverableTraineesIndicator != null && deliverableTraineesIndicator.getIndicator() != null
          && !deliverableTraineesIndicator.getIndicator().isEmpty()) {
          String indicator = deliverableTraineesIndicator.getIndicator();
          crpProgramOutcomeIPI =
            programOutcomes.stream().filter(o -> o.getAcronym() != null && o.getAcronym().equals(indicator))
              .collect(Collectors.toList()).get(0);
        }
      }


    } catch (Exception e) {
      logger.error("unable to get IPI 2.x", e);
      return null;
    }
    return crpProgramOutcomeIPI;

  }

  /**
   * Get Trainees indicator BK old method - IPI 2.3
   * 
   * @return CrpProgramOutcome IPI 2.3 object
   */
  public CrpProgramOutcome getTraineesIndicatorBackup() {
    CrpProgramOutcome crpProgramOutcomeIPI = new CrpProgramOutcome();

    try {

      if (programOutcomes != null && !programOutcomes.isEmpty()) {
        crpProgramOutcomeIPI = programOutcomes.stream()
          .filter(o -> o.getAcronym() != null && o.getAcronym().equals("IPI 2.3")).collect(Collectors.toList()).get(0);
      }

    } catch (Exception e) {
      logger.error("unable to get IPI 2.3", e);
      return null;
    }
    return crpProgramOutcomeIPI;

  }

  public String getTransaction() {
    return transaction;
  }

  /**
   * HJ 08/01/2019
   *
   * @param institutionId
   * @return
   */
  public List<User> getUserList(Long institutionId) {

    List<User> users = new ArrayList<>();

    List<ProjectPartner> partnersTmp = projectPartnerManager.findAll().stream()
      .filter(pp -> pp.isActive() && pp.getProject().getId().equals(projectID)
        && pp.getPhase().getId().equals(this.getActualPhase().getId())
        && pp.getInstitution().getId().equals(institutionId))
      .collect(Collectors.toList());

    if (partnersTmp != null && !partnersTmp.isEmpty()) {
      ProjectPartner projectPartner = partnersTmp.get(0);
      List<ProjectPartnerPerson> partnerPersons = new ArrayList<>(
        projectPartner.getProjectPartnerPersons().stream().filter(pp -> pp.isActive()).collect(Collectors.toList()));
      for (ProjectPartnerPerson projectPartnerPerson : partnerPersons) {

        users.add(projectPartnerPerson.getUser());
      }
    }

    return users;
  }

  /**
   * Verify is deliverable has CapDev category
   * 
   * @return true when deliverable has capdev category
   */
  public boolean hasDeliverableCapdevCategory() {
    return (deliverable.getDeliverableInfo() != null && deliverable.getDeliverableInfo().getDeliverableType() != null
      && deliverable.getDeliverableInfo().getDeliverableType().getId() == 145);
  }

  /**
   * Verify if the deliverable is mapped to trainees indicator - IPI 2.3
   * 
   * @return Yes when deliverable is mapped to IPI 2.3
   */
  public boolean isDeliverableMappedToTrainessIndicator() {
    boolean isMappedToIndicator = false;
    try {
      CrpProgramOutcome crpProgramOutcomeIPI = new CrpProgramOutcome();

      if (programOutcomes != null && !programOutcomes.isEmpty()) {
        crpProgramOutcomeIPI = this.getTraineesIndicator();
      }

      // Check if deliverable is already mapped to IPI 2.3 object

      if (deliverable.getCrpOutcomes() != null && !deliverable.getCrpOutcomes().isEmpty()) {
        for (DeliverableCrpOutcome deliverableOutcome : deliverable.getCrpOutcomes()) {
          if (crpProgramOutcomeIPI != null && crpProgramOutcomeIPI.getId() != null
            && deliverableOutcome.getCrpProgramOutcome() != null
            && deliverableOutcome.getCrpProgramOutcome().getId() != null
            && deliverableOutcome.getCrpProgramOutcome().getId().equals(crpProgramOutcomeIPI.getId())) {
            isMappedToIndicator = true;
          }
        }
      }
      return isMappedToIndicator;
    } catch (Exception e) {
      logger.error("unable to verify if its mapped to IPI 2.3", e);
      return false;
    }
  }

  public boolean isDuplicated() {
    return isDuplicated;
  }

  public boolean isExistCurrentCluster() {
    return existCurrentCluster;
  }

  @Override
  public boolean isPPA(Institution institution) {
    if (institution == null) {
      return false;
    }

    if (institution.getId() != null) {
      institution = institutionManager.getInstitutionById(institution.getId());
      if (institution != null) {
        if (institution.getCrpPpaPartners().stream()
          .filter(c -> c.isActive() && c.getCrp().getId().longValue() == loggedCrp.getId().longValue() && c.isActive())
          .collect(Collectors.toList()).size() > 0) {
          return true;
        }
      }

    }

    return false;
  }

  @Override
  public void prepare() throws Exception {
    existCurrentCluster = false;
    // Get current CRP
    loggedCrp = (GlobalUnit) this.getSession().get(APConstants.SESSION_CRP);
    loggedCrp = crpManager.getGlobalUnitById(loggedCrp.getId());
    this.acceptationPercentage = APConstants.ACCEPTATION_PERCENTAGE;
    isDuplicated = false;

    try {
      deliverableID =
        Long.parseLong(StringUtils.trim(this.getRequest().getParameter(APConstants.PROJECT_DELIVERABLE_REQUEST_ID)));
    } catch (Exception e) {
      logger.error("unable to parse deliverableID", e);
      /**
       * Original code swallows the exception and didn't even log it. Now
       * we at least log it, but we need to revisit to see if we should
       * continue processing or re-throw the exception.
       */
    }
    has_specific_management_deliverables =
      this.hasSpecificities(APConstants.CRP_HAS_SPECIFIC_MANAGEMENT_DELIVERABLE_TYPES);
    isManagingPartnerPersonRequerid = this.hasSpecificities(APConstants.CRP_MANAGING_PARTNERS_CONTACT_PERSONS);

    divisions = new ArrayList<>(
      partnerDivisionManager.findAll().stream().filter(pd -> pd.isActive()).collect(Collectors.toList()));

    if (this.getRequest().getParameter(APConstants.TRANSACTION_ID) != null) {

      transaction = StringUtils.trim(this.getRequest().getParameter(APConstants.TRANSACTION_ID));
      Deliverable history = (Deliverable) auditLogManager.getHistory(transaction);

      if (history != null) {

        deliverable = history;

        if (deliverable.getDeliverableUserPartnerships() != null) {
          List<DeliverableUserPartnership> userPartnerships = new ArrayList<>(deliverable
            .getDeliverableUserPartnerships().stream().filter(dup -> dup.isActive()).collect(Collectors.toList()));
          for (DeliverableUserPartnership deliverableUserPartnership : userPartnerships) {
            if (deliverableUserPartnership.getId() != null) {
              DeliverableUserPartnership userPartnership =
                deliverableUserPartnershipManager.getDeliverableUserPartnershipById(deliverableUserPartnership.getId());
              deliverableUserPartnership.setDeliverablePartnerType(userPartnership.getDeliverablePartnerType());
            }
          }
        }

      } else {
        this.transaction = null;
        this.setTransaction("-1");
      }

    } else {
      deliverable = deliverableManager.getDeliverableById(deliverableID);
    }

    if (deliverable != null) {

      project = projectManager.getProjectById(deliverable.getProject().getId());
      projectID = project.getId();
      project.getProjecInfoPhase(this.getActualPhase());
      Path path = this.getAutoSaveFilePath();

      if (path.toFile().exists() && this.getCurrentUser().isAutoSave() && !this.isHttpPost()) {

        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(path.toFile()));
        Gson gson = new GsonBuilder().create();

        JsonObject jReader = gson.fromJson(reader, JsonObject.class);
        reader.close();

        AutoSaveReader autoSaveReader = new AutoSaveReader();

        deliverable = (Deliverable) autoSaveReader.readFromJson(jReader);

        // Geographic Scope List AutoSave
        boolean haveRegions = false;
        boolean haveCountries = false;

        if (deliverable.getGeographicScopes() != null) {
          for (DeliverableGeographicScope projectInnovationGeographicScope : deliverable.getGeographicScopes()) {
            projectInnovationGeographicScope.setRepIndGeographicScope(repIndGeographicScopeManager
              .getRepIndGeographicScopeById(projectInnovationGeographicScope.getRepIndGeographicScope().getId()));

            if (projectInnovationGeographicScope.getRepIndGeographicScope().getId() == 2) {
              haveRegions = true;
            }

            if (projectInnovationGeographicScope.getRepIndGeographicScope().getId() != 1
              && projectInnovationGeographicScope.getRepIndGeographicScope().getId() != 2) {
              haveCountries = true;
            }

          }
        }

        if (haveRegions) {
          // Deliverable Geographic Regions List Autosave
          if (deliverable.getDeliverableRegions() != null) {
            for (DeliverableGeographicRegion deliverableGeographicRegion : deliverable.getDeliverableRegions()) {
              deliverableGeographicRegion.setLocElement(
                locElementManager.getLocElementById(deliverableGeographicRegion.getLocElement().getId()));
            }
          }
        }

        if (haveCountries) {
          // Deliverable Countries List AutoSave
          if (deliverable.getCountriesIdsText() != null) {
            String[] countriesText = deliverable.getCountriesIdsText().replace("[", "").replace("]", "").split(",");
            List<String> countries = new ArrayList<>();
            for (String value : Arrays.asList(countriesText)) {
              countries.add(value.trim());
            }
            deliverable.setCountriesIds(countries);
          }
        }

        if (metadataElementManager.findAll() != null) {
          deliverable.setMetadata(new ArrayList<>(metadataElementManager.findAll()));
        }

        Deliverable deliverableDb = deliverableManager.getDeliverableById(deliverable.getId());

        deliverable.getDeliverableInfo().setId(deliverableDb.getDeliverableInfo(this.getActualPhase()).getId());
        deliverable.setProject(deliverableDb.getProject());
        project.setProjectInfo(deliverableDb.getProject().getProjecInfoPhase(this.getActualPhase()));
        project.setProjectLocations(deliverableDb.getProject().getProjectLocations());

        if (deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() == null) {
          deliverable.getDeliverableInfo(this.getActualPhase())
            .setNewExpectedYear(deliverableDb.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear());
        }

        if (deliverable.getDeliverableInfo(this.getActualPhase()).getCrpProgramOutcome() != null) {
          deliverable.getDeliverableInfo(this.getActualPhase())
            .setCrpProgramOutcome(deliverableDb.getDeliverableInfo(this.getActualPhase()).getCrpProgramOutcome());
        }

        if (deliverable.getFundingSources() != null) {
          for (DeliverableFundingSource fundingSource : deliverable.getFundingSources()) {
            if (fundingSource != null && fundingSource.getFundingSource() != null) {
              fundingSource
                .setFundingSource(fundingSourceManager.getFundingSourceById(fundingSource.getFundingSource().getId()));
              fundingSource.getFundingSource().getFundingSourceInfo(this.getActualPhase());
            }

          }
        }

        if (deliverable.getActivities() != null) {
          for (DeliverableActivity activity : deliverable.getActivities()) {
            if (activity != null && activity.getActivity() != null) {
              activity.setActivity(activityManager.getActivityById(activity.getActivity().getId()));
            }

          }
        }

        if (deliverable.getCrps() != null) {
          for (DeliverableCrp deliverableCrp : deliverable.getCrps()) {
            if (deliverableCrp != null) {
              if (deliverableCrp.getCrpProgram() == null || deliverableCrp.getCrpProgram().getId() == null
                || deliverableCrp.getCrpProgram().getId().intValue() == -1) {
                if (deliverableCrp.getGlobalUnit() != null && deliverableCrp.getGlobalUnit().getId() != null
                  && deliverableCrp.getGlobalUnit().getId().intValue() != -1) {
                  deliverableCrp.setGlobalUnit(crpManager.getGlobalUnitById(deliverableCrp.getGlobalUnit().getId()));
                }
              } else {
                deliverableCrp
                  .setCrpProgram(crpProgramManager.getCrpProgramById(deliverableCrp.getCrpProgram().getId()));
              }
            }
          }
        }
        if (deliverable.getQualityCheck() != null) {
          if (deliverable.getQualityCheck().getFileAssurance() != null) {
            if (deliverable.getQualityCheck().getFileAssurance().getId() != null) {
              FileDB db;
              // Set FileDB to null if an error occur (-1 id)
              try {
                db = fileDBManager.getFileDBById(deliverable.getQualityCheck().getFileAssurance().getId());
              } catch (IllegalArgumentException e) {
                db = null;
              }
              deliverable.getQualityCheck().setFileAssurance(db);
            }
          }

          if (deliverable.getQualityCheck().getFileDictionary() != null) {
            if (deliverable.getQualityCheck().getFileDictionary().getId() != null) {
              FileDB db;
              // Set FileDB to null if an error occur (-1 id)
              try {
                db = fileDBManager.getFileDBById(deliverable.getQualityCheck().getFileDictionary().getId());
              } catch (IllegalArgumentException e) {
                db = null;
              }
              deliverable.getQualityCheck().setFileDictionary(db);
            }
          }
        }
        if (deliverable.getDissemination() != null && deliverable.getDissemination().getType() != null) {
          String type = deliverable.getDissemination().getType();
          if (type != null) {
            switch (type) {
              case "intellectualProperty":
                deliverable.getDissemination().setIntellectualProperty(true);
                break;
              case "limitedExclusivity":
                deliverable.getDissemination().setLimitedExclusivity(true);
                break;
              case "restrictedUseAgreement":
                deliverable.getDissemination().setRestrictedUseAgreement(true);
                break;
              case "effectiveDateRestriction":
                deliverable.getDissemination().setEffectiveDateRestriction(true);
                break;
              case "notDisseminated":
                deliverable.getDissemination().setNotDisseminated(true);
              default:
                break;
            }
          }

        }

        // Cgiar Cross Cutting Markers Autosave
        if (deliverable.getCrossCuttingMarkers() != null) {
          for (DeliverableCrossCuttingMarker deliverableCrossCuttingMarker : deliverable.getCrossCuttingMarkers()) {
            deliverableCrossCuttingMarker.setCgiarCrossCuttingMarker(cgiarCrossCuttingMarkerManager
              .getCgiarCrossCuttingMarkerById(deliverableCrossCuttingMarker.getCgiarCrossCuttingMarker().getId()));
            if (deliverableCrossCuttingMarker.getRepIndGenderYouthFocusLevel() != null) {
              if (deliverableCrossCuttingMarker.getRepIndGenderYouthFocusLevel().getId() != -1) {
                deliverableCrossCuttingMarker
                  .setRepIndGenderYouthFocusLevel(repIndGenderYouthFocusLevelManager.getRepIndGenderYouthFocusLevelById(
                    deliverableCrossCuttingMarker.getRepIndGenderYouthFocusLevel().getId()));
              }
            }
          }
        }

        // Deliverable responsible
        if (deliverable.getResponsiblePartnership() != null) {
          for (DeliverableUserPartnership deliverableUserPartnership : deliverable.getResponsiblePartnership()) {
            if (deliverableUserPartnership.getInstitution() != null
              && deliverableUserPartnership.getInstitution().getId() != null) {
              deliverableUserPartnership.setInstitution(
                institutionManager.getInstitutionById(deliverableUserPartnership.getInstitution().getId()));

              if (deliverableUserPartnership.getPartnershipPersons() != null) {
                for (DeliverableUserPartnershipPerson person : deliverableUserPartnership.getPartnershipPersons()) {
                  if (person.getUser() != null && person.getUser().getId() != null) {
                    person.setUser(userManager.getUser(person.getUser().getId()));
                  }
                }
              }

            }
          }
        }

        // Deliverable others
        if (deliverable.getOtherPartnerships() != null) {
          for (DeliverableUserPartnership deliverableUserPartnership : deliverable.getOtherPartnerships()) {
            if (deliverableUserPartnership.getInstitution() != null
              && deliverableUserPartnership.getInstitution().getId() != null) {
              deliverableUserPartnership.setInstitution(
                institutionManager.getInstitutionById(deliverableUserPartnership.getInstitution().getId()));

              if (deliverableUserPartnership.getPartnershipPersons() != null) {
                for (DeliverableUserPartnershipPerson person : deliverableUserPartnership.getPartnershipPersons()) {
                  if (person.getUser() != null && person.getUser().getId() != null) {
                    person.setUser(userManager.getUser(person.getUser().getId()));
                  }
                }
              }

            }
          }
        }

        this.setDraft(true);
      } else {
        deliverable.getDeliverableInfo(this.getActualPhase());
        if (deliverable.getDeliverableInfo(this.getActualPhase()) == null) {
          deliverable.setDeliverableInfo(new DeliverableInfo());
        }
        // Deliverable shared Projects List
        if (this.deliverable.getProjectDeliverableShareds() != null) {
          this.deliverable.setSharedDeliverables(new ArrayList<>(this.deliverable.getProjectDeliverableShareds()
            .stream().filter(o -> o.isActive() && o.getPhase().getId().equals(this.getActualPhase().getId()))
            .collect(Collectors.toList())));
        }

        // Setup Geographic Scope
        if (deliverable.getDeliverableGeographicScopes() != null) {
          deliverable.setGeographicScopes(new ArrayList<>(deliverable.getDeliverableGeographicScopes().stream()
            .filter(o -> o.isActive() && o.getPhase().getId().equals(this.getActualPhase().getId()))
            .collect(Collectors.toList())));
        }

        // Deliverable Cluster participants
        if (deliverable.getDeliverableClusterParticipants() != null) {
          deliverable.setClusterParticipant(new ArrayList<>(deliverable.getDeliverableClusterParticipants().stream()
            .filter(o -> o.isActive() && o.getPhase().getId().equals(this.getActualPhase().getId()))
            .collect(Collectors.toList())));
        }

        // Deliverable Countries List
        if (deliverable.getDeliverableLocations() == null) {
          deliverable.setCountries(new ArrayList<>());
        } else {
          List<DeliverableLocation> countries = deliverableLocationManager
            .getDeliverableLocationbyPhase(deliverable.getId(), this.getActualPhase().getId());
          deliverable.setCountries(countries);
        }

        if (deliverable.getCountries() != null) {
          for (DeliverableLocation country : deliverable.getCountries()) {
            deliverable.getCountriesIds().add(country.getLocElement().getIsoAlpha2());
          }
        }

        // Deliverable Project Outcome list
        /*
         * if (deliverable.getDeliverableProjectOutcomes() != null) {
         * deliverable.setProjectOutcomes(new ArrayList<>(deliverable.getDeliverableProjectOutcomes().stream()
         * .filter(o -> o.getPhase().getId().equals(this.getActualPhase().getId())).collect(Collectors.toList())));
         * }
         */
        // Deliverable Crp Outcome list
        if (deliverable.getDeliverableCrpOutcomes() != null) {
          deliverable.setCrpOutcomes(new ArrayList<>(deliverable.getDeliverableCrpOutcomes().stream()
            .filter(o -> o.getPhase().getId().equals(this.getActualPhase().getId())).collect(Collectors.toList())));
        }

        // Expected Study Geographic Regions List
        if (deliverable.getDeliverableGeographicRegions() != null
          && !deliverable.getDeliverableGeographicRegions().isEmpty()) {
          deliverable.setDeliverableRegions(new ArrayList<>(deliverableGeographicRegionManager
            .getDeliverableGeographicRegionbyPhase(deliverable.getId(), this.getActualPhase().getId()).stream()
            .filter(le -> le.isActive() && le.getLocElement().getLocElementType().getId() == 1)
            .collect(Collectors.toList())));
        }

        deliverable.setFundingSources(deliverable.getDeliverableFundingSources().stream()
          .filter(c -> c.isActive() && c.getPhase() != null && c.getPhase().equals(this.getActualPhase()))
          .collect(Collectors.toList()));

        List<DeliverableActivity> deliverableActivities = new ArrayList<>();
        deliverableActivities = deliverableActivityManager.getDeliverableActivitiesByDeliverableID(deliverable.getId());
        if (deliverableActivities != null && !deliverableActivities.isEmpty()) {
          deliverableActivities = deliverableActivities.stream()
            .filter(da -> da.isActive() && da.getPhase() != null
              && da.getPhase().getId().equals(this.getActualPhase().getId()) && da.getActivity() != null
              && da.getActivity().isActive() && da.getActivity().getProject() != null
              && da.getActivity().getProject().getId().equals(project.getId()))
            .collect(Collectors.toList());

          deliverable.setActivities(deliverableActivities);
        }

        for (DeliverableFundingSource deliverableFundingSource : deliverable.getFundingSources()) {

          deliverableFundingSource.setFundingSource(
            fundingSourceManager.getFundingSourceById(deliverableFundingSource.getFundingSource().getId()));
          deliverableFundingSource.getFundingSource().setFundingSourceInfo(
            deliverableFundingSource.getFundingSource().getFundingSourceInfo(this.getActualPhase()));
          if (deliverableFundingSource.getFundingSource().getFundingSourceInfo() == null) {
            deliverableFundingSource.getFundingSource().setFundingSourceInfo(
              deliverableFundingSource.getFundingSource().getFundingSourceInfoLast(this.getActualPhase()));
          }
        }

        deliverable.setGenderLevels(deliverable.getDeliverableGenderLevels().stream()
          .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList()));

        if (this.isReportingActive() || this.isUpKeepActive()) {

          DeliverableQualityCheck deliverableQualityCheck = deliverableQualityCheckManager
            .getDeliverableQualityCheckByDeliverable(deliverable.getId(), this.getActualPhase().getId());
          deliverable.setQualityCheck(deliverableQualityCheck);

          if (deliverable.getDeliverableMetadataElements() != null) {
            deliverable.setMetadataElements(new ArrayList<>(deliverable.getDeliverableMetadataElements().stream()
              .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())));
          }

          try {
            DOI = deliverable.getDeliverableMetadataElements().stream()
              .filter(me -> me != null && me.getMetadataElement() != null && me.getMetadataElement().getId() != null
                && me.getMetadataElement().getId().longValue() == 36L && me.getPhase().equals(this.getActualPhase())
                && me.getDeliverable().getId().equals(deliverableID) && !StringUtils.isBlank(me.getElementValue()))
              .findFirst().orElse(null).getElementValue();
          } catch (Exception e) {
            Log.info(e);
          }

          try {
            handle = deliverable.getDeliverableMetadataElements().stream()
              .filter(me -> me != null && me.getMetadataElement() != null && me.getMetadataElement().getId() != null
                && me.getMetadataElement().getId().longValue() == 35L && me.getPhase().equals(this.getActualPhase())
                && me.getDeliverable().getId().equals(deliverableID) && !StringUtils.isBlank(me.getElementValue()))
              .findFirst().orElse(null).getElementValue();
          } catch (Exception e) {
            Log.info(e);
          }

          if (deliverable.getDeliverableDisseminations() != null) {
            deliverable.setDisseminations(new ArrayList<>(deliverable.getDeliverableDisseminations().stream()
              .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())));
            if (!deliverable.getDisseminations().isEmpty()) {
              deliverable.setDissemination(deliverable.getDisseminations().get(0));
            } else {
              deliverable.setDissemination(new DeliverableDissemination());
            }
          }

          if (deliverable.getDissemination() != null && deliverable.getDissemination().getDisseminationUrl() != null
            && !deliverable.getDissemination().getDisseminationUrl().isEmpty()) {
            disseminationURL = deliverable.getDissemination().getDisseminationUrl();
          }

          if (deliverable.getDeliverableDataSharingFiles() != null) {
            deliverable.setDataSharingFiles(new ArrayList<>(deliverable.getDeliverableDataSharingFiles().stream()
              .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())));
          }

          if (deliverable.getDeliverablePublicationMetadatas() != null) {
            deliverable
              .setPublicationMetadatas(new ArrayList<>(deliverable.getDeliverablePublicationMetadatas().stream()
                .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())));
          }
          if (!deliverable.getPublicationMetadatas().isEmpty()) {
            deliverable.setPublication(deliverable.getPublicationMetadatas().get(0));
          }

          if (deliverable.getDeliverableDataSharings() != null) {
            deliverable.setDataSharing(new ArrayList<>(deliverable.getDeliverableDataSharings().stream()
              .filter(c -> c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())));
          }

          deliverable.setUsers(deliverable.getDeliverableUsers().stream()
            .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList()));
          deliverable.setCrps(deliverable.getDeliverableCrps().stream()
            .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList()));
          deliverable.setFiles(new ArrayList<>());
          for (DeliverableDataSharingFile dataSharingFile : deliverable.getDeliverableDataSharingFiles().stream()
            .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())) {

            DeliverableFile deFile = new DeliverableFile();
            switch (dataSharingFile.getTypeId().toString()) {
              case APConstants.DELIVERABLE_FILE_LOCALLY_HOSTED:
                deFile.setHosted(APConstants.DELIVERABLE_FILE_LOCALLY_HOSTED_STR);
                deFile.setName(dataSharingFile.getFile().getFileName());
                break;

              case APConstants.DELIVERABLE_FILE_EXTERNALLY_HOSTED:
                deFile.setHosted(APConstants.DELIVERABLE_FILE_EXTERNALLY_HOSTED_STR);
                deFile.setName(dataSharingFile.getExternalFile());
                break;
            }
            deFile.setId(dataSharingFile.getId());
            deFile.setSize(0);
            deliverable.getFiles().add(deFile);
          }

          if (deliverable.getDeliverableParticipants() != null) {
            List<DeliverableParticipant> deliverableParticipants = deliverable.getDeliverableParticipants().stream()
              .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList());

            if (!deliverableParticipants.isEmpty()) {
              deliverable.setDeliverableParticipant(
                deliverableParticipantManager.getDeliverableParticipantById(deliverableParticipants.get(0).getId()));

              if (this.transaction != null && !this.transaction.equals("-1")) {
                if (deliverable.getDeliverableParticipant().getRepIndTypeActivity() != null
                  && deliverable.getDeliverableParticipant().getRepIndTypeActivity().getId() != null) {
                  deliverable.getDeliverableParticipant()
                    .setRepIndTypeActivity(repIndTypeActivityManager.getRepIndTypeActivityById(
                      deliverable.getDeliverableParticipant().getRepIndTypeActivity().getId()));
                }
                if (deliverable.getDeliverableParticipant().getRepIndTypeParticipant() != null
                  && deliverable.getDeliverableParticipant().getRepIndTypeParticipant().getId() != null) {
                  deliverable.getDeliverableParticipant()
                    .setRepIndTypeParticipant(repIndTypeParticipantManager.getRepIndTypeParticipantById(
                      deliverable.getDeliverableParticipant().getRepIndTypeParticipant().getId()));
                }
                if (deliverable.getDeliverableParticipant().getRepIndTrainingTerm() != null
                  && deliverable.getDeliverableParticipant().getRepIndTrainingTerm().getId() != null) {
                  deliverable.getDeliverableParticipant()
                    .setRepIndTrainingTerm(repIndTrainingTermManager.getRepIndTrainingTermById(
                      deliverable.getDeliverableParticipant().getRepIndTrainingTerm().getId()));
                }
              }
            } else {
              deliverable.setDeliverableParticipant(new DeliverableParticipant());
            }
          }
        }

        // Cgiar Cross Cutting Markers List
        if (deliverable.getCrossCuttingMarkers() != null) {
          deliverable.setCrossCuttingMarkers(new ArrayList<>(deliverable.getDeliverableCrossCuttingMarkers().stream()
            .filter(o -> o.isActive() && o.getPhase().getId().equals(this.getActualPhase().getId()))
            .collect(Collectors.toList())));
        }

        // Expected Study Projects List
        if (this.deliverable.getProjectDeliverableShareds() != null) {
          List<ProjectDeliverableShared> projectDeliverableShareds =
            new ArrayList<>(this.deliverable.getProjectDeliverableShareds().stream()
              .filter(o -> o.isActive() && o.getPhase().getId().equals(this.getActualPhase().getId()))
              .collect(Collectors.toList()));

          for (ProjectDeliverableShared projectDeliverableShared : projectDeliverableShareds) {

            Project sharedProject = deliverable.getProject();

            List<Deliverable> deliverables =
              sharedProject.getDeliverables().stream().filter(c -> c.isActive()).collect(Collectors.toList());
            /*
             * for (Deliverable deliverable : deliverables) {
             * if (deliverable.getDeliverableInfo(this.getActualPhase()) != null) {
             * this.policyList.add(deliverable);
             * }
             * }
             */

          }
        }

        // Shows the projects to create a shared link with their
        this.myProjects = new ArrayList<>();

        try {
          Phase phase = phaseManager.getPhaseById(this.getActualPhase().getId());
          if (phase != null && phase.getProjectPhases() != null) {
            for (ProjectPhase projectPhase : phase.getProjectPhases()) {
              if (projectPhase.getProject().getProjecInfoPhase(this.getActualPhase()) != null) {
                this.myProjects.add(projectPhase.getProject());
              }
              if (this.project != null) {
                this.myProjects.remove(this.project);
              }
            }
          }
        } catch (Exception e) {
          logger.error("unable to get projectPhases", e);
        }


        if (this.myProjects != null && !this.myProjects.isEmpty()) {
          this.myProjects.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));
        }

        /*
         * HJ 08/01/2019 Getting the Deliverable Partnerships Information
         * -- Deliverable responsible
         */
        if (deliverable.getDeliverableUserPartnerships() != null) {

          List<DeliverableUserPartnership> deList = deliverable.getDeliverableUserPartnerships().stream()
            .filter(dp -> dp.isActive() && dp.getPhase().getId().equals(this.getActualPhase().getId())
              && dp.getDeliverablePartnerType().getId().equals(APConstants.DELIVERABLE_PARTNERSHIP_TYPE_RESPONSIBLE))
            .collect(Collectors.toList());

          if (deList != null && !deList.isEmpty()) {
            try {
              Collections.sort(deList, (p1, p2) -> p1.getInstitution().getId().compareTo(p2.getInstitution().getId()));
            } catch (Exception e) {
              logger.error("unable to sort dlist", e);
            }
            deliverable.setResponsiblePartnership(new ArrayList<>());
            for (DeliverableUserPartnership deliverableUserPartnership : deList) {

              if (deliverableUserPartnership.getDeliverableUserPartnershipPersons() != null) {
                List<DeliverableUserPartnershipPerson> partnershipPersons =
                  new ArrayList<>(deliverableUserPartnership.getDeliverableUserPartnershipPersons().stream()
                    .filter(d -> d.isActive()).collect(Collectors.toList()));
                deliverableUserPartnership.setPartnershipPersons(partnershipPersons);
              }
              deliverable.getResponsiblePartnership().add(deliverableUserPartnership);
            }

          }
        }


        /*
         * HJ 08/01/2019 Getting the Deliverable Partnerships Information
         * -- Deliverable Others
         */
        if (deliverable.getDeliverableUserPartnerships() != null) {

          List<DeliverableUserPartnership> deList = deliverable.getDeliverableUserPartnerships().stream()
            .filter(dp -> dp.isActive() && dp.getPhase().getId().equals(this.getActualPhase().getId())
              && dp.getDeliverablePartnerType().getId().equals(APConstants.DELIVERABLE_PARTNERSHIP_TYPE_OTHER))
            .collect(Collectors.toList());

          if (deList != null && !deList.isEmpty()) {
            try {
              Collections.sort(deList, (p1, p2) -> p1.getInstitution().getId().compareTo(p2.getInstitution().getId()));
            } catch (Exception e) {
              logger.error("unable to sort dlist", e);
            }
            deliverable.setOtherPartnerships(new ArrayList<>());
            for (DeliverableUserPartnership deliverableUserPartnership : deList) {

              if (deliverableUserPartnership.getDeliverableUserPartnershipPersons() != null) {
                List<DeliverableUserPartnershipPerson> partnershipPersons =
                  new ArrayList<>(deliverableUserPartnership.getDeliverableUserPartnershipPersons().stream()
                    .filter(d -> d.isActive()).collect(Collectors.toList()));
                deliverableUserPartnership.setPartnershipPersons(partnershipPersons);
              }
              deliverable.getOtherPartnerships().add(deliverableUserPartnership);
            }

          }
        }

        this.setDraft(false);
      }

      this.setRepIndGeographicScopes(repIndGeographicScopeManager.findAll().stream()
        .sorted((g1, g2) -> g1.getName().compareTo(g2.getName())).collect(Collectors.toList()));
      repIndRegions = locElementManager.findAll().stream()
        .filter(c -> c.getLocElementType().getId().intValue() == 1 && c.isActive() && c.getIsoNumeric() != null)
        .collect(Collectors.toList());
      this.setCountries(locElementManager.findAll().stream()
        .filter(c -> c.isActive() && c.getLocElementType().getId() == 2).collect(Collectors.toList()));

      if (deliverable.getGenderLevels() != null) {
        for (DeliverableGenderLevel deliverableGenderLevel : deliverable.getGenderLevels()) {
          try {
            GenderType type = genderTypeManager.getGenderTypeById(deliverableGenderLevel.getGenderLevel());
            if (type != null) {
              deliverableGenderLevel.setNameGenderLevel(type.getDescription());
              deliverableGenderLevel.setDescriptionGenderLevel(type.getCompleteDescription());
            }
          } catch (Exception e) {
            logger.error("unable to update DeliverableGenderLevel", e);
          }
        }
      }

      genderLevels = new ArrayList<>();
      List<GenderType> genderTypes = null;
      if (this.hasSpecificities(APConstants.CRP_CUSTOM_GENDER)) {
        genderTypes = genderTypeManager.findAll().stream()
          .filter(
            c -> c.isActive() && c.getCrp() != null && c.getCrp().getId().longValue() == loggedCrp.getId().longValue())
          .collect(Collectors.toList());
      } else {
        genderTypes = genderTypeManager.findAll().stream().filter(c -> c.isActive() && c.getCrp() == null)
          .collect(Collectors.toList());
      }

      for (GenderType projectStatusEnum : genderTypes) {
        genderLevels.add(projectStatusEnum);
      }

      status = new HashMap<>();
      List<ProjectStatusEnum> list = Arrays.asList(ProjectStatusEnum.values());
      // Add all status
      for (ProjectStatusEnum projectStatusEnum : list) {
        status.put(projectStatusEnum.getStatusId(), projectStatusEnum.getStatus());
      }

      // Status rules for planning
      if (this.isPlanningActive() && !this.isUpKeepActive()) {
        if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() != null) {
          if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus().intValue() != Integer
            .parseInt(ProjectStatusEnum.Complete.getStatusId())) {
            status.remove(ProjectStatusEnum.Complete.getStatusId());
          }

        }

        if (this.isDeliverableNew(deliverableID)) {
          // NEW Deliverable
          status.remove(ProjectStatusEnum.Cancelled.getStatusId());
          status.remove(ProjectStatusEnum.Extended.getStatusId());
          status.remove(ProjectStatusEnum.Complete.getStatusId());
        } else {
          // OLD Deliverable
          if (deliverable.getDeliverableInfo(this.getActualPhase()).getYear() > this.getActualPhase().getYear()) {

            status.remove(ProjectStatusEnum.Extended.getStatusId());

          }
          if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() != null) {
            if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == Integer
              .parseInt(ProjectStatusEnum.Extended.getStatusId())) {
              status.remove(ProjectStatusEnum.Ongoing.getStatusId());
            }
          }
        }
        if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() != null) {
          if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == Integer
            .parseInt(ProjectStatusEnum.Extended.getStatusId())) {
            status.remove(ProjectStatusEnum.Complete.getStatusId());
          }
        }
      }

      crps = new ArrayList<GlobalUnit>();
      for (GlobalUnit crp : crpManager.findAll().stream()
        .filter(c -> c.getId() != this.getLoggedCrp().getId() && c.isActive()).collect(Collectors.toList())) {
        crps.add(crp);
      }
      crps.sort((c1, c2) -> c1.getComposedName().compareTo(c2.getComposedName()));

      programs = new ArrayList<CrpProgram>();
      for (CrpProgram program : crpProgramManager.findAll().stream().filter(c -> c.isActive()
        && c.getCrp().equals(this.loggedCrp) && c.getProgramType() == ProgramType.FLAGSHIP_PROGRAM_TYPE.getValue())
        .collect(Collectors.toList())) {
        programs.add(program);
      }
      programs.sort((f1, f2) -> f1.getAcronym().compareTo(f2.getAcronym()));

      deliverableTypeParent = new ArrayList<>(
        deliverableTypeManager.findAll().stream().filter(dt -> dt.isActive() && dt.getDeliverableCategory() == null
          && dt.getCrp() == null && !dt.getAdminType().booleanValue()).collect(Collectors.toList()));

      deliverableTypeParent.addAll(new ArrayList<>(deliverableTypeManager.findAll().stream()
        .filter(dt -> dt.isActive() && dt.getDeliverableCategory() == null && dt.getCrp() != null
          && dt.getCrp().getId().longValue() == loggedCrp.getId().longValue() && !dt.getAdminType().booleanValue())
        .collect(Collectors.toList())));

      if (project.getProjecInfoPhase(this.getActualPhase()).getAdministrative() != null
        && project.getProjecInfoPhase(this.getActualPhase()).getAdministrative().booleanValue()) {

        deliverableTypeParent
          .addAll(deliverableTypeManager.findAll().stream()
            .filter(dt -> dt.isActive() && dt.getDeliverableCategory() == null && dt.getCrp() == null
              && dt.getAdminType().booleanValue() && !has_specific_management_deliverables)
            .collect(Collectors.toList()));

        deliverableTypeParent.addAll(new ArrayList<>(deliverableTypeManager.findAll().stream()
          .filter(dt -> dt.isActive() && dt.getDeliverableCategory() == null && dt.getCrp() != null
            && dt.getCrp().getId().longValue() == loggedCrp.getId().longValue() && dt.getAdminType().booleanValue())
          .collect(Collectors.toList())));
      }

      if (deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType() != null
        && deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getId() != null
        && deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getId().longValue() != -1) {
        DeliverableType typeDB = deliverableTypeManager
          .getDeliverableTypeById(deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getId());
        deliverable.getDeliverableInfo(this.getActualPhase()).setDeliverableType(typeDB);
        Long deliverableTypeParentId =
          deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getDeliverableCategory().getId();

        deliverableSubTypes = new ArrayList<>(
          deliverableTypeManager.findAll().stream().filter(dt -> dt.isActive() && dt.getDeliverableCategory() != null
            && dt.getDeliverableCategory().getId() == deliverableTypeParentId).collect(Collectors.toList()));
      }

      projectOutcomes = new ArrayList<>();

      if (project.getProjectOutcomes() != null) {
        keyOutputs = new ArrayList<>();
        programOutcomes = new ArrayList<>();

        for (ProjectOutcome projectOutcome : project.getProjectOutcomes().stream()
          .filter(ca -> ca.isActive() && ca.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())) {

          if (!this.programOutcomes.contains(projectOutcome.getCrpProgramOutcome())) {
            this.programOutcomes.add(projectOutcome.getCrpProgramOutcome());
          }

          // Fill projectOutcomes List
          if (projectOutcome.getCrpProgramOutcome() != null
            && projectOutcome.getCrpProgramOutcome().getComposedName() != null) {
            projectOutcome.setComposedName(projectOutcome.getCrpProgramOutcome().getComposedName());
          } else {
            projectOutcome.setComposedName(projectOutcome.getId() + "");
          }
          projectOutcomes.add(projectOutcome);

        }
      }

      programOutcomes.sort((k1, k2) -> k1.getId().compareTo(k2.getId()));

      partners = new ArrayList<>();

      /*
       * HJ - 08/01/2019 Setting the Project partners in institutions List
       */
      partnerInstitutions = new ArrayList<>();

      List<ProjectPartner> partnersTmp =
        projectPartnerManager.findAll().stream().filter(pp -> pp.isActive() && pp.getProject().getId() == projectID
          && pp.getPhase().getId().equals(this.getActualPhase().getId())).collect(Collectors.toList());

      for (ProjectPartner partner : partnersTmp) {
        List<ProjectPartnerPerson> persons =
          partner.getProjectPartnerPersons().stream().filter(c -> c.isActive()).collect(Collectors.toList());
        if (!isManagingPartnerPersonRequerid) {
          partners.add(partner);
          partnerInstitutions.add(partner.getInstitution());
        } else {
          if (!persons.isEmpty()) {
            partners.add(partner);
            partnerInstitutions.add(partner.getInstitution());
          }
        }
      }

      partnerPersons = new ArrayList<>();
      /**
       * This for is not being used properly. The internal logic is
       * repeated for each partner that has been consulted. The partner
       * object of the cycle is not being used within it. jurodca 20180129
       */

      partnerPersons =
        partners.stream().flatMap(e -> e.getProjectPartnerPersons().stream()).collect(Collectors.toList());

      this.fundingSources = new ArrayList<>();

      for (ProjectBudget budget : project.getProjectBudgets().stream().filter(c -> c.isActive())
        .collect(Collectors.toList())) {

        FundingSource fundingSource = budget.getFundingSource();
        fundingSource.setFundingSourceInfo(fundingSource.getFundingSourceInfo(this.getActualPhase()));

        if (fundingSource.getFundingSourceInfo() == null) {
          fundingSource.setFundingSourceInfo(fundingSource.getFundingSourceInfoLast(this.getActualPhase()));

        }
        if (fundingSource.getFundingSourceInfo() != null) {
          this.fundingSources.add(fundingSource);
        }
      }
      Set<FundingSource> hs = new HashSet<FundingSource>();
      hs.addAll(this.fundingSources);
      this.fundingSources.clear();
      this.fundingSources.addAll(hs);
      fundingSources.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));

      // Add Activities
      this.activities = new ArrayList<>();

      for (Activity activity : project.getActivities()) {
        if (activity.isActive() && !this.activities.contains(activity)
          && activity.getPhase().getId().compareTo(this.getActualPhase().getId()) == 0) {
          this.activities.add(activity);
        }
      }

      // Add activities from the shared clusters
      /*
       * List<ProjectDeliverableShared> deliverablesShared = new ArrayList<>();
       * deliverablesShared =
       * projectDeliverableSharedManager.getByDeliverable(deliverable.getId(), this.getActualPhase().getId());
       * if (deliverablesShared != null && !deliverablesShared.isEmpty()) {
       * for (ProjectDeliverableShared deliverableShared : deliverablesShared) {
       * Project projectShared = deliverableShared.getProject();
       * List<Activity> sharedActivities = new ArrayList<>();
       * activityManager = null;
       * sharedActivities =
       * activityManager.getActivitiesByProject(projectShared.getId(), this.getActualPhase().getId());
       * if (sharedActivities != null) {
       * for (Activity activity : sharedActivities) {
       * if (!activities.contains(activity)) {
       * activities.add(activity);
       * }
       * }
       * }
       * }
       * }
       */

      if (activities != null && !activities.isEmpty()) {
        activities = activities.stream().filter(c -> c.isActive() && c.getActivityTitle() != null)
          .sorted((a1, a2) -> a1.getActivityTitle().getTitle().compareTo(a2.getActivityTitle().getTitle()))
          .collect(Collectors.toList());
      }


      List<DeliverableSearchSummary> deliverableDTOs = new ArrayList<>();
      if (this.hasSpecificities(APConstants.DUPLICATED_DELIVERABLES_FUNCTIONALITY_ACTIVE)) {
        deliverableDTOs = this.getDuplicatedDeliverableInformation(DOI, handle, disseminationURL, deliverableID);
        if (deliverableDTOs != null && !deliverableDTOs.isEmpty()) {
          // Set is duplicated field in true
          isDuplicated = true;
        } else {
          isDuplicated = false;
        }
        if (deliverable.getDeliverableInfo(this.getActualPhase()) != null) {
          deliverable.getDeliverableInfo(this.getActualPhase()).setDuplicated(isDuplicated);
          deliverableManager.saveDeliverable(deliverable);
        }
      }

      String params[] = {loggedCrp.getAcronym(), project.getId() + ""};
      this.setBasePermission(this.getText(Permission.PROJECT_DELIVERABLE_BASE_PERMISSION, params));

      // Cross Cutting Values List
      focusLevels = repIndGenderYouthFocusLevelManager.findAll();

      // Cross Cutting Markers
      cgiarCrossCuttingMarkers = cgiarCrossCuttingMarkerManager.findAll();

      if (this.isReportingActive() || this.isUpKeepActive()) {
        if (metadataElementManager.findAll() != null) {
          deliverable.setMetadata(new ArrayList<>(metadataElementManager.findAll()));
        }
        answers = new ArrayList<>(
          deliverableQualityAnswerManager.findAll().stream().filter(qa -> qa.isActive()).collect(Collectors.toList()));

        answersDataDic = new ArrayList<>();
        DeliverableQualityAnswer answer2 = new DeliverableQualityAnswer();
        answer2.setActive(true);
        answer2.setName("Yes");
        answer2.setId(2L);
        answersDataDic.add(answer2);

        DeliverableQualityAnswer answer3 = new DeliverableQualityAnswer();
        answer3.setActive(true);
        answer3.setName("No");
        answer3.setId(3L);
        answersDataDic.add(answer3);

        repositoryChannels = repositoryChannelManager.findAll();
        if (repositoryChannels != null && repositoryChannels.size() > 0) {
          repositoryChannels.sort((rc1, rc2) -> rc1.getShortName().compareTo(rc2.getShortName()));
        } else {
          repositoryChannels = new LinkedList<RepositoryChannel>();
        }

        if (deliverable.getFiles() != null) {
          deliverable.getFiles().sort((p1, p2) -> p1.getId().compareTo(p2.getId()));
        }

        this.setRepIndTypeActivities(repIndTypeActivityManager.findAll().stream()
          .sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList()));
        this.setRepIndTypeParticipants(repIndTypeParticipantManager.findAll().stream()
          .sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList()));
        this.setRepIndFillingTypes(repIndFillingTypeManager.findAll().stream()
          .sorted((r1, r2) -> r1.getName().compareTo(r2.getName())).collect(Collectors.toList()));
        this.setRepIndPatentStatuses(repIndPatentStatusManager.findAll().stream()
          .sorted((r1, r2) -> r1.getName().compareTo(r2.getName())).collect(Collectors.toList()));
        // Statuses
        statuses = new HashMap<>();
        List<ProjectStatusEnum> statusEnumList = Arrays.asList(ProjectStatusEnum.values());
        for (ProjectStatusEnum projectStatusEnum : statusEnumList) {
          statuses.put(projectStatusEnum.getStatusId(), projectStatusEnum.getStatus());
        }

        this.setRepIndTrainingTerms(repIndTrainingTermManager.findAll().stream()
          .sorted((t1, t2) -> t1.getId().compareTo(t2.getId())).collect(Collectors.toList()));
      }

      if (this.hasSpecificities(APConstants.DELIVERABLE_SHARED_CLUSTERS_TRAINEES_ACTIVE)) {
        this.fillClusterParticipantsList();
        existCurrentCluster = this.existCurrentClusterDB();
      }

      /*
       * get feedback comments
       */

      try {
        if (this.hasSpecificities(this.feedbackModule())) {

          feedbackComments = new ArrayList<>();
          feedbackComments = feedbackQACommentableFieldsManager.findAll().stream()
            .filter(f -> f.getSectionName() != null && f.getSectionName().equals("deliverable"))
            .collect(Collectors.toList());
          if (feedbackComments != null) {
            for (FeedbackQACommentableFields field : feedbackComments) {
              List<FeedbackQAComment> comments = new ArrayList<FeedbackQAComment>();
              comments = feedbackQACommentManager.findAll().stream()
                .filter(f -> f != null && f.getPhase() != null && f.getPhase().getId() != null
                  && f.getPhase().getId().equals(this.getActualPhase().getId())
                  && f.getParentId() == deliverable.getId() && f.getField() != null && f.getField().getId() != null
                  && f.getField().getId().equals(field.getId()))
                .collect(Collectors.toList());
              field.setQaComments(comments);
            }
          }
        }
      } catch (Exception e) {
      }

      // Deliverable remaining value
      if (deliverable.getDeliverableInfo() != null && deliverable.getDeliverableInfo().getRemainingPending() == null) {
        deliverable.getDeliverableInfo().setRemainingPending(false);
      }

      if (this.isHttpPost()) {

        if (deliverable.getOtherPartnerships() != null) {
          deliverable.getOtherPartnerships().clear();
        }
        if (deliverable.getContribution() != null) {
          deliverable.setContribution(null);
        }

        if (deliverableTypeParent != null) {
          deliverableTypeParent.clear();
        }

        if (deliverable.getPublication() != null) {
          deliverable.getPublication().setIsiPublication(null);
          deliverable.getPublication().setCoAuthor(null);
          deliverable.getPublication().setNasr(null);
        }

        deliverable.getDeliverableInfo(this.getActualPhase()).setDeliverableType(null);
        deliverable.getDeliverableInfo(this.getActualPhase()).setRegion(null);
        deliverable.getDeliverableInfo(this.getActualPhase()).setGeographicScope(null);
        deliverable.getDeliverableInfo(this.getActualPhase()).setCrpProgramOutcome(null);

        if (deliverable.getCrps() != null) {
          deliverable.getCrps().clear();
        }

        if (deliverable.getUsers() != null) {
          deliverable.getUsers().clear();
        }

        if (deliverable.getMetadataElements() != null) {
          deliverable.getMetadataElements().clear();
        }

        if (projectOutcome != null) {
          projectOutcome.clear();
        }

        if (deliverable.getResponsiblePartnership() != null) {
          deliverable.getResponsiblePartnership().clear();
        }

        if (deliverable.getFundingSources() != null) {
          deliverable.getFundingSources().clear();
        }

        if (deliverable.getActivities() != null) {
          deliverable.getActivities().clear();
        }

        if (deliverable.getGenderLevels() != null) {
          deliverable.getGenderLevels().clear();
        }

        if (deliverable.getDeliverableRegions() != null) {
          deliverable.getDeliverableRegions().clear();
        }

        if (deliverable.getGeographicScopes() != null) {
          deliverable.getGeographicScopes().clear();
        }

        if (deliverable.getSharedDeliverables() != null) {
          deliverable.getSharedDeliverables().clear();
        }

        deliverable.setQualityCheck(null);
        deliverable.getDeliverableInfo(this.getActualPhase()).setCrpClusterKeyOutput(null);

        if (deliverable.getDisseminations() != null) {
          deliverable.getDisseminations().clear();
        }
        if (deliverable.getDeliverableParticipant() != null) {
          deliverable.getDeliverableParticipant().setRepIndTypeActivity(null);
          deliverable.getDeliverableParticipant().setRepIndTypeParticipant(null);
          deliverable.getDeliverableParticipant().setRepIndTrainingTerm(null);
          deliverable.setDeliverableParticipant(null);
        }

        if (deliverable.getClusterParticipant() != null) {
          deliverable.getClusterParticipant().clear();
        }

        if (deliverable.getCountries() != null) {
          deliverable.getCountries().clear();
        }

        if (deliverable.getCrossCuttingMarkers() != null) {
          deliverable.getCrossCuttingMarkers().clear();
        }

        if (deliverable.getProjectOutcomes() != null) {
          deliverable.getProjectOutcomes().clear();
        }

        if (deliverable.getCrpOutcomes() != null) {
          deliverable.getCrpOutcomes().clear();
        }
      }

      try {
        indexTab = Integer.parseInt(this.getSession().get("indexTab").toString());
        this.getSession().remove("indexTab");
      } catch (Exception e) {
        indexTab = 0;
      }

    }

  }

  /**
   * Remove mapping with trainees indicator (IPI 2.3 indicator)
   */
  public void removeTraineesIndicatorMapping() {
    if (this.isDeliverableMappedToTrainessIndicator()) {
      try {
        if (deliverable.getCrpOutcomes() != null && !deliverable.getCrpOutcomes().isEmpty()) {
          DeliverableCrpOutcome deliverableCrpOutcome = deliverable.getCrpOutcomes().stream()
            .filter(d -> d != null && d.getCrpProgramOutcome() != null
              && d.getCrpProgramOutcome().getId().equals(this.getTraineesIndicator().getId()))
            .collect(Collectors.toList()).get(0);

          if (deliverableCrpOutcome != null) {
            deliverable.getCrpOutcomes().remove(deliverableCrpOutcome);
          }
        }
      } catch (Exception e) {
        logger.error("unable to remove mapping to IPI 2.3", e);
      }
    }
  }

  @Override
  public String save() {
    if (this.hasPermission("canEdit")) {
      this.getSession().put("indexTab", indexTab);
      // we update the mofification Justification only here.
      this.saveProjectAuditData();

      Deliverable deliverableManagedState = this.updateDeliverableInfo();
      this.updateDeliverableFundingSources(deliverableManagedState);
      this.saveDeliverableActivities(deliverableManagedState);

      // This gets a DeliverablePartnership responsible entity in managed state.
      // DeliverablePartnership partnershipResponsibleManaged = deliverable.getResponsiblePartner();
      Deliverable deliverableDB = deliverableManager.getDeliverableById(deliverableID);

      // gets delivetablePartnership responsible from database
      // DeliverablePartnership partnershipResponsibleDB = this.getDeliverablePartnershipResponsibleDB(deliverableDB);
      // this.saveUpdateDeliverablePartnershipResponsible(partnershipResponsibleDB, partnershipResponsibleManaged);
      // this.removeOthersDeliverablePartnerships(deliverableManagedState);
      // this.updateOtherDeliverablePartnerships();
      /*
       * 08/01 - HJ - Save the Deliverables Part Responsible
       */
      this.saveDeliverablePartnershipResponsible(deliverableDB);
      this.saveDeliverablePartnershipOther(deliverableDB);

      // Save Geographic Scope Data
      this.saveGeographicScope(deliverableManagedState, this.getActualPhase());

      // this.saveProjectOutcomes(deliverableDB, this.getActualPhase());
      this.saveCrpOutcomes(deliverableDB, this.getActualPhase());

      boolean haveRegions = false;
      boolean haveCountries = false;

      if (deliverable.getGeographicScopes() != null) {
        for (DeliverableGeographicScope deliverableGeographicScope : deliverable.getGeographicScopes()) {

          if (deliverableGeographicScope != null && deliverableGeographicScope.getRepIndGeographicScope() != null
            && deliverableGeographicScope.getRepIndGeographicScope().getId() == 2) {
            haveRegions = true;
          }

          if (deliverableGeographicScope != null && deliverableGeographicScope.getRepIndGeographicScope() != null
            && deliverableGeographicScope.getRepIndGeographicScope().getId() != 1
            && deliverableGeographicScope.getRepIndGeographicScope().getId() != 2) {
            haveCountries = true;
          }
        }
      }

      if (haveRegions) {
        // Save the Regions List
        this.saveDeliverableRegions(deliverableDB, this.getActualPhase(), deliverableManagedState);
      } else {
        this.deleteLocElements(deliverableManagedState, this.getActualPhase(), false);
      }

      if (haveCountries) {

        // Save Countries list
        this.saveDeliverableCountries(deliverableManagedState);
      } else {
        this.deleteLocElements(deliverableManagedState, this.getActualPhase(), true);
      }

      this.saveCrossCutting();
      this.saveProjects(deliverableDB);

      // Reporting and upkeep
      if (this.isReportingActive() || this.isUpKeepActive()) {
        if (deliverable.getQualityCheck() != null) {
          this.saveQualityCheck();
        }
        this.saveDissemination();
        this.saveMetadata();
        this.saveCrps();
        this.savePublicationMetadata();
        // Data Sharing is not longer used.
        this.saveDataSharing();
        this.saveUsers();
        this.saveParticipant();
        this.saveDuplicated();
        if (this.hasSpecificities(APConstants.DELIVERABLE_SHARED_CLUSTERS_TRAINEES_ACTIVE)) {
          this.saveDeliverableClusterParticipant();
        }
      }

      /*
       * Delete the field 'new expected year' when the status is different to Extended and this field has information
       */
      /*
       * if (deliverableManagedState.getDeliverableInfo() != null
       * && deliverableManagedState.getDeliverableInfo().getStatus() != null) {
       * if ((deliverableManagedState.getDeliverableInfo().getStatus() != 3
       * && deliverableManagedState.getDeliverableInfo().getStatus() != 4)
       * && deliverableManagedState.getDeliverableInfo().getNewExpectedYear() != null) {
       * deliverableManagedState.getDeliverableInfo().setNewExpectedYear(null);
       * }
       * }
       */
      deliverableInfoManager.saveDeliverableInfo(deliverableManagedState.getDeliverableInfo());

      /*
       * Delete the field 'justification' when the status is equals to Completed and this field has information
       */
      if (deliverableManagedState.getDeliverableInfo() != null
        && deliverableManagedState.getDeliverableInfo().getStatus() != null) {
        if ((deliverableManagedState.getDeliverableInfo().getStatus() == 3)
          && deliverableManagedState.getDeliverableInfo().getModificationJustification() != null) {
          deliverableManagedState.getDeliverableInfo().setModificationJustification(null);
        }
      }

      /*
       * Delete mapping to IPI 2.3
       */
      if (!this.hasDeliverableCapdevCategory() && (deliverable.getDeliverableParticipant() != null
        && deliverable.getDeliverableParticipant().getHasParticipants() != null
        && !deliverable.getDeliverableParticipant().getHasParticipants())) {

        this.removeTraineesIndicatorMapping();
      }

      if (this.hasSpecificities(APConstants.CRP_LP6_ACTIVE) && this.isReportingActive()
        && this.getProjectLp6ContributionValue(project.getId(), this.getActualPhase().getId())) {
        this.updateProjectLp6ContributionDeliverable();
      }

      List<String> relationsName = new ArrayList<>();
      relationsName.add(APConstants.PROJECT_DELIVERABLE_PARTNERSHIPS_RELATION);
      relationsName.add(APConstants.PROJECT_DELIVERABLE_INFO);
      relationsName.add(APConstants.PROJECT_DELIVERABLE_LOCATIONS);
      relationsName.add(APConstants.PROJECT_DELIVERABLE_FUNDING_RELATION);
      relationsName.add(APConstants.PROJECT_DELIVERABLE_GENDER_LEVELS);
      if (this.isReportingActive() || this.isUpKeepActive()) {
        relationsName.add(APConstants.PROJECT_DELIVERABLE_QUALITY_CHECK);
        relationsName.add(APConstants.PROJECT_DELIVERABLE_METADATA_ELEMENT);
        relationsName.add(APConstants.PROJECT_DELIVERABLE_DATA_SHARING_FILES);
        relationsName.add(APConstants.PROJECT_DELIVERABLE_PUBLICATION_METADATA);
        relationsName.add(APConstants.PROJECT_DELIVERABLE_DISEMINATIONS);
        relationsName.add(APConstants.PROJECT_DELIVERABLE_CRPS);
        relationsName.add(APConstants.PROJECT_DELIVERABLE_USERS);
        if (this.hasSpecificities(this.crpDeliverableIntellectualAsset())) {
          relationsName.add(APConstants.PROJECT_DELIVERABLES_INTELLECTUAL_RELATION);
        }
        relationsName.add(APConstants.PROJECT_DELIVERABLES_PARTICIPANT_RELATION);
      }

      /**
       * The following is required because we need to update something on
       * the @Deliverable if we want a row created in the auditlog table.
       */
      this.setModificationJustification(deliverableManagedState);
      deliverableManagedState = deliverableManager.saveDeliverable(deliverableManagedState, this.getActionName(),
        relationsName, this.getActualPhase());
      Path path = this.getAutoSaveFilePath();

      if (path.toFile().exists()) {
        path.toFile().delete();
      }

      if (this.getUrl() == null || this.getUrl().isEmpty()) {
        this.getActionMessages();
        if (!this.getInvalidFields().isEmpty()) {
          this.setActionMessages(null);
          List<String> keys = new ArrayList<String>(this.getInvalidFields().keySet());
          for (String key : keys) {
            this.addActionMessage(key + ": " + this.getInvalidFields().get(key));
          }

        } else {
          this.addActionMessage("message:" + this.getText("saving.saved"));
        }
        return SUCCESS;
      } else {
        this.addActionMessage("");
        this.setActionMessages(null);
        return REDIRECT;
      }

    } else {
      return NOT_AUTHORIZED;
    }

  }

  /**
   * Save Deliverable CrossCutting Information
   *
   * @param deliverable
   */
  public void saveCrossCutting() {

    // Save form Information
    if (this.deliverable.getCrossCuttingMarkers() != null) {
      for (DeliverableCrossCuttingMarker crossCuttingOwner : this.deliverable.getCrossCuttingMarkers()) {
        if (crossCuttingOwner.getId() == null) {
          DeliverableCrossCuttingMarker crossCuttingOwnerSave = new DeliverableCrossCuttingMarker();
          crossCuttingOwnerSave.setDeliverable(this.deliverable);
          crossCuttingOwnerSave.setPhase(this.getActualPhase());

          CgiarCrossCuttingMarker cgiarCrossCuttingMarker = cgiarCrossCuttingMarkerManager
            .getCgiarCrossCuttingMarkerById(crossCuttingOwner.getCgiarCrossCuttingMarker().getId());

          crossCuttingOwnerSave.setCgiarCrossCuttingMarker(cgiarCrossCuttingMarker);

          if (crossCuttingOwner.getRepIndGenderYouthFocusLevel() != null) {
            if (crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId() != null
              && crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId() != -1) {
              RepIndGenderYouthFocusLevel focusLevel = repIndGenderYouthFocusLevelManager
                .getRepIndGenderYouthFocusLevelById(crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId());
              crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(focusLevel);
            } else {
              crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(null);
            }
          } else {
            crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(null);
          }

          deliverableCrossCuttingMarkerManager.saveDeliverableCrossCuttingMarker(crossCuttingOwnerSave);
          // This is to add deliverableCrossCuttingMarker to generate correct auditlog.
          this.deliverable.getDeliverableCrossCuttingMarkers().add(crossCuttingOwnerSave);
        } else {
          boolean hasChanges = false;
          DeliverableCrossCuttingMarker crossCuttingOwnerSave =
            deliverableCrossCuttingMarkerManager.getDeliverableCrossCuttingMarkerById(crossCuttingOwner.getId());

          if (crossCuttingOwner.getRepIndGenderYouthFocusLevel() != null) {
            if (crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId() != null
              && crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId() != -1) {

              if (crossCuttingOwnerSave.getRepIndGenderYouthFocusLevel() != null) {
                if (crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId() != crossCuttingOwnerSave
                  .getRepIndGenderYouthFocusLevel().getId()) {
                  RepIndGenderYouthFocusLevel focusLevel = repIndGenderYouthFocusLevelManager
                    .getRepIndGenderYouthFocusLevelById(crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId());
                  crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(focusLevel);
                  hasChanges = true;
                }
              } else {
                RepIndGenderYouthFocusLevel focusLevel = repIndGenderYouthFocusLevelManager
                  .getRepIndGenderYouthFocusLevelById(crossCuttingOwner.getRepIndGenderYouthFocusLevel().getId());
                crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(focusLevel);
                hasChanges = true;
              }

            } else {
              crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(null);
              hasChanges = true;
            }
          } else {
            crossCuttingOwnerSave.setRepIndGenderYouthFocusLevel(null);
            hasChanges = true;
          }

          if (hasChanges) {
            deliverableCrossCuttingMarkerManager.saveDeliverableCrossCuttingMarker(crossCuttingOwnerSave);
          }
          // This is to add deliverableCrossCuttingMarker to generate correct auditlog.
          this.deliverable.getDeliverableCrossCuttingMarkers().add(crossCuttingOwnerSave);

        }
      }
    }
  }

  /**
   * Save Deliverable Crp Program Outcome Information
   * 
   * @param deliverable
   * @param phase
   */
  public void saveCrpOutcomes(Deliverable deliverable, Phase phase) {
    // Get the IPI 2.3 object
    CrpProgramOutcome crpProgramOutcomeIPI = this.getTraineesIndicator();
    boolean addIPI = false;
    try {

      // Check deliverable type
      if ((this.hasDeliverableCapdevCategory())
      /*
       * || (deliverable.getDeliverableParticipant() != null
       * && deliverable.getDeliverableParticipant().getHasParticipants() != null
       * && deliverable.getDeliverableParticipant().getHasParticipants())
       */) {
        addIPI = true;
      }

    } catch (Exception e) {
      logger.error("unable to map IPI 2.3", e);
    }

    // Search and deleted form Information
    try {
      if (deliverable.getDeliverableCrpOutcomes() != null && !deliverable.getDeliverableCrpOutcomes().isEmpty()) {

        List<DeliverableCrpOutcome> outcomePrev = new ArrayList<>(deliverable.getDeliverableCrpOutcomes().stream()
          .filter(nu -> nu.getPhase().getId().equals(phase.getId())).collect(Collectors.toList()));

        for (DeliverableCrpOutcome deliverableOutcome : outcomePrev) {
          if (this.deliverable.getCrpOutcomes() == null
            || !this.deliverable.getCrpOutcomes().contains(deliverableOutcome)) {
            this.deliverableCrpOutcomeManager.deleteDeliverableCrpOutcome(deliverableOutcome.getId(),
              this.getActualPhase().getId());
          }
        }
      }
    } catch (Exception e) {
      logger.error("unable to delete crp outcome", e);
    }

    // Save form Information
    if (this.deliverable.getCrpOutcomes() != null) {

      if (!this.isDeliverableMappedToTrainessIndicator() && addIPI) {
        DeliverableCrpOutcome deliverableCrpOutcome = new DeliverableCrpOutcome();
        deliverableCrpOutcome.setDeliverable(deliverable);
        deliverableCrpOutcome.setCrpProgramOutcome(crpProgramOutcomeIPI);
        this.deliverable.getCrpOutcomes().add(deliverableCrpOutcome);
      }

      for (DeliverableCrpOutcome deliverableOutcome : this.deliverable.getCrpOutcomes()) {
        DeliverableCrpOutcome deliverableOutcomeSave = new DeliverableCrpOutcome();

        if (deliverableOutcome != null) {
          // For new crp outcomes
          if (deliverableOutcome.getId() == null) {
            deliverableOutcomeSave.setDeliverable(deliverable);
            deliverableOutcomeSave.setPhase(phase);
          } else {
            // For old crp outcomes
            try {
              if (deliverableOutcome.getId() != null) {
                deliverableOutcomeSave =
                  deliverableCrpOutcomeManager.getDeliverableCrpOutcomeById(deliverableOutcome.getId());
              }
            } catch (Exception e) {
              logger.error("unable to get old crp outcome", e);
            }
          }

          if (deliverableOutcome.getCrpProgramOutcome() != null
            && deliverableOutcome.getCrpProgramOutcome().getId() != null) {
            CrpProgramOutcome outcome =
              crpProgramOutcomeManager.getCrpProgramOutcomeById(deliverableOutcome.getCrpProgramOutcome().getId());
            if (outcome != null) {
              deliverableOutcomeSave.setCrpProgramOutcome(outcome);
            }

            this.deliverableCrpOutcomeManager.saveDeliverableCrpOutcome(deliverableOutcomeSave);
            // This is to add studyCrpSave to generate correct auditlog.
            if (!this.deliverable.getDeliverableCrpOutcomes().contains(deliverableOutcomeSave)) {
              this.deliverable.getDeliverableCrpOutcomes().add(deliverableOutcomeSave);
            }
          }
        }
      }
    }
  }

  public void saveCrps() {
    if (deliverable.getCrps() == null) {
      deliverable.setCrps(new ArrayList<>());
    }
    /* Delete */
    if (deliverableID != 0 && deliverableManager.getDeliverableById(deliverableID) != null) {
      Deliverable deliverableDB = deliverableManager.getDeliverableById(deliverableID);
      for (DeliverableCrp deliverableCrp : deliverableDB.getDeliverableCrps().stream()
        .filter(c -> c != null && c.isActive() && c.getPhase() != null && c.getPhase().equals(this.getActualPhase()))
        .collect(Collectors.toList())) {
        if (deliverableCrp != null && deliverableCrp.getId() != null && deliverable.getCrps() != null
          && !deliverable.getCrps().contains(deliverableCrp)) {
          deliverableCrpManager.deleteDeliverableCrp(deliverableCrp.getId());
        }
      }
    }

    /* Save */
    if (deliverable.getCrps() != null) {
      for (DeliverableCrp deliverableCrp : deliverable.getCrps()) {
        if (deliverableCrp != null && deliverableCrp.getId() == null || deliverableCrp.getId().intValue() == -1) {
          deliverableCrp.setId(null);
          deliverableCrp.setDeliverable(deliverable);
          deliverableCrp.setPhase(this.getActualPhase());
          if (deliverableCrp.getGlobalUnit() != null && deliverableCrp.getGlobalUnit().getId() != null
            && deliverableCrp.getGlobalUnit().getId() != -1) {
            deliverableCrp.setCrpProgram(null);
          } else {
            deliverableCrp.setGlobalUnit(null);
          }
          deliverableCrpManager.saveDeliverableCrp(deliverableCrp);
        }
      }
    }
  }

  public void saveDataSharing() {
    if (deliverable.getFiles() == null) {
      deliverable.setFiles(new ArrayList<>());

    }
    List<DeliverableFile> filesPrev = new ArrayList<>();
    Deliverable deliverableDB = deliverableManager.getDeliverableById(deliverableID);

    for (DeliverableDataSharingFile dataSharingFile : deliverableDB.getDeliverableDataSharingFiles()) {

      DeliverableFile deFile = new DeliverableFile();
      switch (dataSharingFile.getTypeId().toString()) {
        case APConstants.DELIVERABLE_FILE_LOCALLY_HOSTED:
          deFile.setHosted(APConstants.DELIVERABLE_FILE_LOCALLY_HOSTED_STR);
          deFile.setName(dataSharingFile.getFile().getFileName());
          break;

        case APConstants.DELIVERABLE_FILE_EXTERNALLY_HOSTED:
          deFile.setHosted(APConstants.DELIVERABLE_FILE_EXTERNALLY_HOSTED_STR);
          deFile.setName(dataSharingFile.getExternalFile());
          break;
      }
      deFile.setId(dataSharingFile.getId());
      deFile.setSize(0);
      filesPrev.add(deFile);
    }

    for (DeliverableFile deliverableFile : filesPrev) {
      if (!deliverable.getFiles().contains(deliverableFile)) {
        deliverableDataSharingFileManager.deleteDeliverableDataSharingFile(deliverableFile.getId().longValue());
      }
    }

    for (DeliverableFile deliverableFile : deliverable.getFiles()) {
      if (deliverableFile.getId().longValue() == -1) {
        DeliverableDataSharingFile dataSharingFile = new DeliverableDataSharingFile();
        dataSharingFile.setDeliverable(deliverable);
        switch (deliverableFile.getHosted()) {

          case APConstants.DELIVERABLE_FILE_EXTERNALLY_HOSTED_STR:
            dataSharingFile.setTypeId(Integer.parseInt(APConstants.DELIVERABLE_FILE_EXTERNALLY_HOSTED));
            dataSharingFile.setExternalFile(deliverableFile.getName());
            break;
        }
        dataSharingFile.setPhase(this.getActualPhase());
        deliverableDataSharingFileManager.saveDeliverableDataSharingFile(dataSharingFile);
      }

    }
  }

  private void saveDeliverableActivities(Deliverable deliverablePrew) {
    if (deliverable.getActivities() != null) {
      if (deliverablePrew.getDeliverableActivities() != null && !deliverablePrew.getDeliverableActivities().isEmpty()) {
        List<DeliverableActivity> activityPrew = deliverablePrew.getDeliverableActivities().stream()
          .filter(dp -> dp.isActive() && dp.getPhase() != null && dp.getPhase().equals(this.getActualPhase()))
          .collect(Collectors.toList());

        for (DeliverableActivity deliverableActivity : activityPrew) {
          if (!deliverable.getActivities().contains(deliverableActivity)) {
            deliverableActivityManager.deleteDeliverableActivity(deliverableActivity.getId());
          }
        }
      }

      for (DeliverableActivity deliverableActivity : deliverable.getActivities()) {
        if (deliverableActivity.getId() == null || deliverableActivity.getId() == -1) {

          deliverableActivity.setDeliverable(deliverableManager.getDeliverableById(deliverableID));
          deliverableActivity.setPhase(this.getActualPhase());
          deliverableActivityManager.saveDeliverableActivity(deliverableActivity);
          // This add projectFocus to generate correct auditlog.
          deliverablePrew.getDeliverableActivities().add(deliverableActivity);
        }
      }
    }
  }

  /**
   * Save Deliverable Cluster Participants Information
   *
   * @param deliverable
   * @param phase
   */
  public void saveDeliverableClusterParticipant() {

    // Search and deleted form Information
    /*
     * if (deliverable.getDeliverableClusterParticipants() != null
     * && !deliverable.getDeliverableClusterParticipants().isEmpty()) {
     * List<DeliverableClusterParticipant> clusterParticipant =
     * new ArrayList<>(deliverable.getDeliverableClusterParticipants().stream()
     * .filter(nu -> nu.isActive() && nu.getPhase().getId().equals(this.getActualPhase().getId()))
     * .collect(Collectors.toList()));
     * for (DeliverableClusterParticipant participant : clusterParticipant) {
     * if (this.deliverable.getClusterParticipant() == null
     * || !this.deliverable.getClusterParticipant().contains(participant)) {
     * deliverableClusterParticipantManager.deleteDeliverableClusterParticipant(participant.getId());
     * }
     * }
     * }
     */
    // Save form Information
    if (this.deliverable.getClusterParticipant() != null) {
      DeliverableClusterParticipant participantSave;
      for (DeliverableClusterParticipant deliverableParticipant : deliverable.getClusterParticipant()) {
        if (deliverableParticipant != null) {
          participantSave = new DeliverableClusterParticipant();
          if (deliverableParticipant.getId() != null) {
            participantSave =
              deliverableClusterParticipantManager.getDeliverableClusterParticipantById(deliverableParticipant.getId());
          } else {
            try {
              if (deliverableParticipant.getProject() != null && deliverableParticipant.getProject().getId() != null) {
                participantSave = deliverableClusterParticipantManager
                  .getDeliverableClusterParticipantByDeliverableProjectPhase(deliverable.getId(),
                    deliverableParticipant.getProject().getId(), this.getActualPhase().getId())
                  .get(0);
              }
            } catch (Exception e) {
              Log.error(e + "error getting cluster participant id");
            }

          }
          if (deliverableParticipant.getParticipants() != null) {
            participantSave.setParticipants(deliverableParticipant.getParticipants());
          }
          if (deliverableParticipant.getFemales() != null) {
            participantSave.setFemales(deliverableParticipant.getFemales());
          }
          if (deliverableParticipant.getAfrican() != null) {
            participantSave.setAfrican(deliverableParticipant.getAfrican());
          }
          if (deliverableParticipant.getYouth() != null) {
            participantSave.setYouth(deliverableParticipant.getYouth());
          }
          if (deliverable != null) {
            participantSave.setDeliverable(deliverable);
          }
          participantSave.setPhase(this.getActualPhase());
          if (deliverableParticipant.getProject() != null && deliverableParticipant.getProject().getId() != null) {
            participantSave.setProject(deliverableParticipant.getProject());

          }
          deliverableClusterParticipantManager.saveDeliverableClusterParticipant(participantSave);
        }
      }
    }

    if ((deliverable.getSharedDeliverables() == null
      || (deliverable.getSharedDeliverables() != null && deliverable.getSharedDeliverables().isEmpty()))) {
      // for null deliverable cluster participants
      DeliverableClusterParticipant participantSave;
      participantSave = new DeliverableClusterParticipant();

      try {
        participantSave =
          deliverableClusterParticipantManager.getDeliverableClusterParticipantByDeliverableProjectPhase(
            deliverable.getId(), deliverable.getProject().getId(), this.getActualPhase().getId()).get(0);
      } catch (Exception e) {
        Log.info(e);
      }
      participantSave.setParticipants(deliverable.getDeliverableParticipant().getParticipants());
      participantSave.setFemales(deliverable.getDeliverableParticipant().getFemales());
      participantSave.setAfrican(deliverable.getDeliverableParticipant().getAfrican());
      participantSave.setYouth(deliverable.getDeliverableParticipant().getYouth());
      participantSave.setDeliverable(deliverable);
      participantSave.setPhase(this.getActualPhase());
      participantSave.setProject(deliverable.getProject());
      deliverableClusterParticipantManager.saveDeliverableClusterParticipant(participantSave);
    }
  }

  private void saveDeliverableCountries(Deliverable deliverableManagedState) {

    if (deliverable.getCountriesIds() != null || !deliverable.getCountriesIds().isEmpty()) {

      List<DeliverableLocation> countries =
        deliverableLocationManager.getDeliverableLocationbyPhase(deliverable.getId(), this.getActualPhase().getId());
      List<DeliverableLocation> countriesSave = new ArrayList<>();
      for (String countryIds : deliverable.getCountriesIds()) {
        if (countryIds != null && !countryIds.isEmpty()) {
          DeliverableLocation deliverableLocation = new DeliverableLocation();
          deliverableLocation.setLocElement(locElementManager.getLocElementByISOCode(countryIds));
          deliverableLocation.setDeliverable(deliverable);
          deliverableLocation.setPhase(this.getActualPhase());
          countriesSave.add(deliverableLocation);
          if (!countries.contains(deliverableLocation)) {
            deliverableLocationManager.saveDeliverableLocation(deliverableLocation);
            deliverableManagedState.getDeliverableLocations().add(deliverableLocation);
          }
        }
      }

      for (DeliverableLocation deliverableLocation : countries) {
        if (deliverableLocation != null) {
          if (!countriesSave.contains(deliverableLocation)) {
            deliverableLocationManager.deleteDeliverableLocation(deliverableLocation.getId());
          }
        }

      }
    }

  }

  /**
   * 08/01 save Deliverable Partnership Other
   *
   * @param deliverable
   */
  public void saveDeliverablePartnershipOther(Deliverable deliverableDB) {

    if (deliverableDB.getDeliverableUserPartnerships() != null
      && deliverableDB.getDeliverableUserPartnerships().size() > 0) {

      List<DeliverableUserPartnership> deliverableUserPartnershipPrev =
        deliverableDB.getDeliverableUserPartnerships().stream()
          .filter(dp -> dp.isActive() && dp.getPhase().getId().equals(this.getActualPhase().getId())
            && dp.getDeliverablePartnerType().getId().equals(APConstants.DELIVERABLE_PARTNERSHIP_TYPE_OTHER))
          .collect(Collectors.toList());

      for (DeliverableUserPartnership deliverableUserPartnership : deliverableUserPartnershipPrev) {
        if (this.deliverable.getOtherPartnerships() == null
          || !this.deliverable.getOtherPartnerships().contains(deliverableUserPartnership)) {

          deliverableUserPartnershipManager.deleteDeliverableUserPartnership(deliverableUserPartnership.getId());
        }
      }

    }

    DeliverablePartnerType deliverablePartnerType =
      deliverablePartnerTypeManager.getDeliverablePartnerTypeById(APConstants.DELIVERABLE_PARTNERSHIP_TYPE_OTHER);
    if (this.deliverable.getOtherPartnerships() != null) {

      for (DeliverableUserPartnership deliverableUserPartnership : this.deliverable.getOtherPartnerships()) {
        if (deliverableUserPartnership.getId() != null) {

          DeliverableUserPartnership deliverableUserPartnershipSave =
            deliverableUserPartnershipManager.getDeliverableUserPartnershipById(deliverableUserPartnership.getId());

          if (deliverableUserPartnership.getInstitution() != null
            && deliverableUserPartnership.getInstitution().getId() != null) {
            if (deliverableUserPartnership.getInstitution().getId() != -1) {
              Institution institution =
                institutionManager.getInstitutionById(deliverableUserPartnership.getInstitution().getId());
              deliverableUserPartnershipSave.setInstitution(institution);

              if (deliverableUserPartnership.getPartnershipPersons() != null) {
                deliverableUserPartnershipSave
                  .setPartnershipPersons(deliverableUserPartnership.getPartnershipPersons());
              }

              deliverableUserPartnershipSave =
                deliverableUserPartnershipManager.saveDeliverableUserPartnership(deliverableUserPartnershipSave);

              this.saveDeliverableUserPartnershipsPersons(deliverableUserPartnership, deliverableUserPartnershipSave);
            } else {
              deliverableUserPartnershipManager.deleteDeliverableUserPartnership(deliverableUserPartnership.getId());
            }
          }

        } else {

          DeliverableUserPartnership deliverableUserPartnershipSave = new DeliverableUserPartnership();
          deliverableUserPartnershipSave.setPhase(this.getActualPhase());
          deliverableUserPartnershipSave.setDeliverable(deliverableDB);
          deliverableUserPartnershipSave.setCreatedBy(this.getCurrentUser());
          deliverableUserPartnershipSave.setDeliverablePartnerType(deliverablePartnerType);

          if (deliverableUserPartnership.getInstitution() != null
            && deliverableUserPartnership.getInstitution().getId() != null) {
            if (deliverableUserPartnership.getInstitution().getId() != -1) {
              Institution institution =
                institutionManager.getInstitutionById(deliverableUserPartnership.getInstitution().getId());
              deliverableUserPartnershipSave.setInstitution(institution);

              if (deliverableUserPartnership.getPartnershipPersons() != null) {
                deliverableUserPartnershipSave
                  .setPartnershipPersons(deliverableUserPartnership.getPartnershipPersons());
              }

              deliverableUserPartnershipSave =
                deliverableUserPartnershipManager.saveDeliverableUserPartnership(deliverableUserPartnershipSave);

              this.saveDeliverableUserPartnershipsPersons(deliverableUserPartnership, deliverableUserPartnershipSave);
            }
          }

        }
      }

    }

  }

  /**
   * 08/01 save Deliverable Partnership Responsible
   *
   * @param deliverable
   */
  public void saveDeliverablePartnershipResponsible(Deliverable deliverableDB) {

    if (deliverableDB.getDeliverableUserPartnerships() != null
      && deliverableDB.getDeliverableUserPartnerships().size() > 0) {

      List<DeliverableUserPartnership> deliverableUserPartnershipPrev =
        deliverableDB.getDeliverableUserPartnerships().stream()
          .filter(dp -> dp.isActive() && dp.getPhase().getId().equals(this.getActualPhase().getId())
            && dp.getDeliverablePartnerType().getId().equals(APConstants.DELIVERABLE_PARTNERSHIP_TYPE_RESPONSIBLE))
          .collect(Collectors.toList());

      for (DeliverableUserPartnership deliverableUserPartnership : deliverableUserPartnershipPrev) {
        if (this.deliverable.getResponsiblePartnership() == null
          || !this.deliverable.getResponsiblePartnership().contains(deliverableUserPartnership)) {

          deliverableUserPartnershipManager.deleteDeliverableUserPartnership(deliverableUserPartnership.getId());
        }
      }

    }

    DeliverablePartnerType deliverablePartnerType =
      deliverablePartnerTypeManager.getDeliverablePartnerTypeById(APConstants.DELIVERABLE_PARTNERSHIP_TYPE_RESPONSIBLE);
    if (this.deliverable.getResponsiblePartnership() != null) {

      for (DeliverableUserPartnership deliverableUserPartnership : this.deliverable.getResponsiblePartnership()) {
        if (deliverableUserPartnership.getId() != null) {

          DeliverableUserPartnership deliverableUserPartnershipSave =
            deliverableUserPartnershipManager.getDeliverableUserPartnershipById(deliverableUserPartnership.getId());

          if (deliverableUserPartnership.getInstitution().getId() != null) {
            if (deliverableUserPartnership.getInstitution().getId() != -1) {
              Institution institution =
                institutionManager.getInstitutionById(deliverableUserPartnership.getInstitution().getId());
              deliverableUserPartnershipSave.setInstitution(institution);

              if (deliverableUserPartnership.getPartnershipPersons() != null) {
                deliverableUserPartnershipSave
                  .setPartnershipPersons(deliverableUserPartnership.getPartnershipPersons());
              }

              deliverableUserPartnershipSave =
                deliverableUserPartnershipManager.saveDeliverableUserPartnership(deliverableUserPartnershipSave);

              this.saveDeliverableUserPartnershipsPersons(deliverableUserPartnership, deliverableUserPartnershipSave);
            } else {
              deliverableUserPartnershipManager.deleteDeliverableUserPartnership(deliverableUserPartnership.getId());
            }
          }

        } else {

          DeliverableUserPartnership deliverableUserPartnershipSave = new DeliverableUserPartnership();
          deliverableUserPartnershipSave.setPhase(this.getActualPhase());
          deliverableUserPartnershipSave.setDeliverable(deliverableDB);
          deliverableUserPartnershipSave.setCreatedBy(this.getCurrentUser());
          deliverableUserPartnershipSave.setDeliverablePartnerType(deliverablePartnerType);

          if (deliverableUserPartnership.getInstitution() != null
            && deliverableUserPartnership.getInstitution().getId() != null) {
            if (deliverableUserPartnership.getInstitution().getId() != -1) {
              Institution institution =
                institutionManager.getInstitutionById(deliverableUserPartnership.getInstitution().getId());
              deliverableUserPartnershipSave.setInstitution(institution);

              if (deliverableUserPartnership.getPartnershipPersons() != null) {
                deliverableUserPartnershipSave
                  .setPartnershipPersons(deliverableUserPartnership.getPartnershipPersons());
              }

              deliverableUserPartnershipSave =
                deliverableUserPartnershipManager.saveDeliverableUserPartnership(deliverableUserPartnershipSave);

              this.saveDeliverableUserPartnershipsPersons(deliverableUserPartnership, deliverableUserPartnershipSave);
            }
          }

        }
      }

    }

  }

  public void saveDeliverableRegions(Deliverable deliverable, Phase phase, Deliverable deliverableManagedState) {

    // Search and deleted form Information
    if (deliverable.getDeliverableGeographicRegions() != null
      && deliverable.getDeliverableGeographicRegions().size() > 0) {

      List<DeliverableGeographicRegion> regionPrev =
        deliverableGeographicRegionManager.getDeliverableGeographicRegionbyPhase(deliverable.getId(), phase.getId())
          .stream().filter(le -> le.isActive() && le.getLocElement().getLocElementType().getId() == 1)
          .collect(Collectors.toList());
      //
      // new ArrayList<>(projectExpectedStudy.getProjectExpectedStudyRegions().stream()
      // .filter(nu -> nu.isActive() && nu.getPhase().getId() == phase.getId()).collect(Collectors.toList()));

      for (DeliverableGeographicRegion deliverableRegion : regionPrev) {
        if (deliverable.getDeliverableRegions() == null
          || !deliverable.getDeliverableRegions().contains(deliverableRegion)) {
          deliverableGeographicRegionManager.deleteDeliverableGeographicRegion(deliverableRegion.getId());
        }
      }
    }

    // Save form Information
    if (deliverable.getDeliverableRegions() != null) {
      for (DeliverableGeographicRegion deliverableRegion : deliverable.getDeliverableRegions()) {
        if (deliverableRegion != null) {
          if (deliverableRegion.getId() == null && deliverableRegion.getLocElement() != null) {
            DeliverableGeographicRegion deliverableRegionSave = new DeliverableGeographicRegion();
            deliverableRegionSave.setDeliverable(deliverable);
            deliverableRegionSave.setPhase(phase);

            LocElement locElement = locElementManager.getLocElementById(deliverableRegion.getLocElement().getId());

            deliverableRegionSave.setLocElement(locElement);

            deliverableGeographicRegionManager.saveDeliverableGeographicRegion(deliverableRegionSave);
            // This is to add regions to generate correct auditlog.
            deliverableManagedState.getDeliverableGeographicRegions().add(deliverableRegionSave);
          }
        }
      }
    }
  }

  private void saveDeliverableUserPartnershipsPersons(DeliverableUserPartnership deliverableUserPartnership,
    DeliverableUserPartnership deliverableUserPartnershipDB) {

    if (deliverableUserPartnershipDB.getDeliverableUserPartnershipPersons() != null
      && !deliverableUserPartnershipDB.getDeliverableUserPartnershipPersons().isEmpty()) {

      List<DeliverableUserPartnershipPerson> deliverableUserPartnershipPersonsPrev = deliverableUserPartnershipDB
        .getDeliverableUserPartnershipPersons().stream().filter(dp -> dp.isActive()).collect(Collectors.toList());

      for (DeliverableUserPartnershipPerson deliverableUserPartnershipPerson : deliverableUserPartnershipPersonsPrev) {
        if (deliverableUserPartnership.getPartnershipPersons() == null
          || !deliverableUserPartnership.getPartnershipPersons().contains(deliverableUserPartnershipPerson)) {
          deliverableUserPartnershipPersonManager
            .deleteDeliverableUserPartnershipPerson(deliverableUserPartnershipPerson.getId());
        }
      }

    }

    if (deliverableUserPartnership.getPartnershipPersons() != null) {

      for (DeliverableUserPartnershipPerson person : deliverableUserPartnership.getPartnershipPersons()) {

        if (person.getId() != null) {
          DeliverableUserPartnershipPerson deliverableUserPartnershipPersonNew =
            deliverableUserPartnershipPersonManager.getDeliverableUserPartnershipPersonById(person.getId());
          if (person.getUser() != null && person.getUser().getId() != null) {
            if (!person.getUser().getId().equals(deliverableUserPartnershipPersonNew.getUser().getId())) {
              deliverableUserPartnershipPersonNew.setUser(userManager.getUser(person.getUser().getId()));
              deliverableUserPartnershipPersonManager
                .saveDeliverableUserPartnershipPerson(deliverableUserPartnershipPersonNew);
            }
          } else {
            deliverableUserPartnershipPersonManager.deleteDeliverableUserPartnershipPerson(person.getId());
          }
        } else {
          if (person.getUser() != null && person.getUser().getId() != null) {
            DeliverableUserPartnershipPerson deliverableUserPartnershipPersonNew =
              new DeliverableUserPartnershipPerson();
            deliverableUserPartnershipPersonNew.setUser(userManager.getUser(person.getUser().getId()));
            deliverableUserPartnershipPersonNew.setDeliverableUserPartnership(deliverableUserPartnershipDB);
            deliverableUserPartnershipPersonManager
              .saveDeliverableUserPartnershipPerson(deliverableUserPartnershipPersonNew);
          }
        }
      }

    }

  }

  public void saveDissemination() {
    if (deliverable.getDissemination() != null) {

      DeliverableDissemination dissemination = new DeliverableDissemination();
      if (deliverable.getDissemination().getId() != null && deliverable.getDissemination().getId() != -1) {
        dissemination =
          deliverableDisseminationManager.getDeliverableDisseminationById(deliverable.getDissemination().getId());
      } else {
        dissemination = new DeliverableDissemination();
        dissemination.setDeliverable(deliverableManager.getDeliverableById(deliverableID));

      }

      dissemination.setSynced(deliverable.getDissemination().getSynced());

      if (deliverable.getDissemination().getIsOpenAccess() != null) {
        dissemination.setIsOpenAccess(deliverable.getDissemination().getIsOpenAccess());
        if (!deliverable.getDissemination().getIsOpenAccess().booleanValue()) {
          String type = deliverable.getDissemination().getType();
          if (type != null) {
            switch (type) {
              case "intellectualProperty":
                dissemination.setNotDisseminated(false);
                dissemination.setIntellectualProperty(true);
                dissemination.setLimitedExclusivity(false);
                dissemination.setRestrictedUseAgreement(false);
                dissemination.setEffectiveDateRestriction(false);

                dissemination.setRestrictedAccessUntil(null);
                dissemination.setRestrictedEmbargoed(null);

                break;
              case "limitedExclusivity":
                dissemination.setNotDisseminated(false);
                dissemination.setIntellectualProperty(false);
                dissemination.setLimitedExclusivity(true);
                dissemination.setRestrictedUseAgreement(false);
                dissemination.setEffectiveDateRestriction(false);

                dissemination.setRestrictedAccessUntil(null);
                dissemination.setRestrictedEmbargoed(null);

                break;
              case "restrictedUseAgreement":
                dissemination.setNotDisseminated(false);
                dissemination.setIntellectualProperty(false);
                dissemination.setLimitedExclusivity(false);
                dissemination.setRestrictedUseAgreement(true);
                dissemination.setEffectiveDateRestriction(false);

                dissemination.setRestrictedAccessUntil(deliverable.getDissemination().getRestrictedAccessUntil());
                dissemination.setRestrictedEmbargoed(null);

                break;
              case "effectiveDateRestriction":
                dissemination.setNotDisseminated(false);
                dissemination.setIntellectualProperty(false);
                dissemination.setLimitedExclusivity(false);
                dissemination.setRestrictedUseAgreement(false);
                dissemination.setEffectiveDateRestriction(true);

                dissemination.setRestrictedAccessUntil(null);
                dissemination.setRestrictedEmbargoed(deliverable.getDissemination().getRestrictedEmbargoed());

                break;
              case "notDisseminated":

                dissemination.setNotDisseminated(true);
                dissemination.setIntellectualProperty(false);
                dissemination.setLimitedExclusivity(false);
                dissemination.setRestrictedUseAgreement(false);
                dissemination.setEffectiveDateRestriction(false);

                dissemination.setRestrictedAccessUntil(null);
                dissemination.setRestrictedEmbargoed(null);

                dissemination.setRestrictedAccessUntil(null);
                dissemination.setRestrictedEmbargoed(null);

                break;
              default:
                break;
            }
          }
        } else {

          dissemination.setNotDisseminated(false);
          dissemination.setIntellectualProperty(false);
          dissemination.setLimitedExclusivity(false);
          dissemination.setRestrictedUseAgreement(false);
          dissemination.setEffectiveDateRestriction(false);

          dissemination.setRestrictedAccessUntil(null);
          dissemination.setRestrictedEmbargoed(null);
        }
      } else {

        dissemination.setIsOpenAccess(null);
        dissemination.setNotDisseminated(false);
        dissemination.setIntellectualProperty(false);
        dissemination.setLimitedExclusivity(false);
        dissemination.setRestrictedUseAgreement(false);
        dissemination.setEffectiveDateRestriction(false);

        dissemination.setRestrictedAccessUntil(null);
        dissemination.setRestrictedEmbargoed(null);
      }

      if (deliverable.getDissemination().getAlreadyDisseminated() != null) {
        dissemination.setAlreadyDisseminated(deliverable.getDissemination().getAlreadyDisseminated());
        if (deliverable.getDissemination().getAlreadyDisseminated().booleanValue()) {
          if (deliverable.getDissemination().getDisseminationUrl() != null) {
            dissemination.setDisseminationUrl(deliverable.getDissemination().getDisseminationUrl().trim());
          } else {
            dissemination.setDisseminationUrl(deliverable.getDissemination().getDisseminationUrl());
          }
          dissemination.setDisseminationChannel(deliverable.getDissemination().getDisseminationChannel());
        } else {
          dissemination.setDisseminationUrl(null);
          dissemination.setDisseminationChannel(null);
          dissemination.setConfidential(deliverable.getDissemination().getConfidential());
          dissemination.setConfidentialUrl(deliverable.getDissemination().getConfidentialUrl());
        }
      } else {
        dissemination.setAlreadyDisseminated(null);
        dissemination.setDisseminationUrl(null);
        dissemination.setDisseminationChannel(null);
        dissemination.setConfidential(null);
        dissemination.setConfidentialUrl(null);
      }

      if (deliverable.getDissemination().getAlreadyDisseminated() != null
        && deliverable.getDissemination().getAlreadyDisseminated() == true) {
        dissemination.setConfidential(null);
      }

      boolean hasDOI =
        deliverable.getDissemination().getHasDOI() != null && deliverable.getDissemination().getHasDOI() == true;
      dissemination.setHasDOI(hasDOI);

      if (hasDOI && deliverable.getDissemination().getArticleUrl() != null
        && !deliverable.getDissemination().getArticleUrl().isEmpty()) {
        dissemination.setArticleUrl(deliverable.getDissemination().getArticleUrl());
      } else {
        dissemination.setArticleUrl(null);
      }

      dissemination.setPhase(this.getActualPhase());
      deliverableDisseminationManager.saveDeliverableDissemination(dissemination);

    }
  }

  /**
   * Check Deliverable duplicated status
   */
  public void saveDuplicated() {
    if (this.hasSpecificities(APConstants.DUPLICATED_DELIVERABLES_FUNCTIONALITY_ACTIVE)) {
      Deliverable deliverableBase = deliverableManager.getDeliverableById(deliverableID);
      DeliverableInfo deliverableInfoDb = deliverableBase.getDeliverableInfo(this.getActualPhase());
      String doi = null;
      String handle = null;
      String disseminationURL = null;

      if (deliverable.getMetadataElements() != null) {
        try {
          doi = deliverable.getMetadataElements().stream()
            .filter(me -> me != null && me.getMetadataElement() != null && me.getMetadataElement().getId() != null
              && me.getMetadataElement().getId().longValue() == 36L && !StringUtils.isBlank(me.getElementValue()))
            .findFirst().orElse(null).getElementValue();
        } catch (Exception e) {
          Log.info(e);
        }

        try {
          handle = deliverable.getMetadataElements().stream()
            .filter(me -> me != null && me.getMetadataElement() != null && me.getMetadataElement().getId() != null
              && me.getMetadataElement().getId().longValue() == 35L && !StringUtils.isBlank(me.getElementValue()))
            .findFirst().orElse(null).getElementValue();
        } catch (Exception e) {
          Log.info(e);
        }
      }

      if (deliverable.getDissemination() != null && deliverable.getDissemination().getDisseminationUrl() != null
        && !deliverable.getDissemination().getDisseminationUrl().isEmpty()) {
        disseminationURL = deliverable.getDissemination().getDisseminationUrl();
      }


      List<DeliverableSearchSummary> deliverableDTOs = new ArrayList<>();

      deliverableDTOs = this.getDuplicatedDeliverableInformation(doi, handle, disseminationURL, deliverableID);
      if (deliverableDTOs != null && !deliverableDTOs.isEmpty()) {
        // Set is duplicated field in true
        isDuplicated = true;
      } else {
        isDuplicated = false;
      }

      if (deliverableInfoDb != null) {
        deliverableInfoDb.setDuplicated(isDuplicated);
        deliverableBase.setDeliverableInfo(deliverableInfoDb);
        deliverableInfoManager.saveDeliverableInfo(deliverableBase.getDeliverableInfo());
      }
    }
  }

  /**
   * Save Deliverable Geographic Scope Information
   *
   * @param deliverable
   * @param phase
   */
  public void saveGeographicScope(Deliverable deliverable, Phase phase) {

    // Search and deleted form Information
    if (deliverable.getDeliverableGeographicScopes() != null
      && deliverable.getDeliverableGeographicScopes().size() > 0) {

      List<DeliverableGeographicScope> scopePrev = new ArrayList<>(deliverable.getDeliverableGeographicScopes().stream()
        .filter(nu -> nu.isActive() && nu.getPhase().getId().equals(phase.getId())).collect(Collectors.toList()));

      for (DeliverableGeographicScope deliverableScope : scopePrev) {
        if (this.deliverable.getGeographicScopes() == null
          || !this.deliverable.getGeographicScopes().contains(deliverableScope)) {
          deliverableGeographicScopeManager.deleteDeliverableGeographicScope(deliverableScope.getId());
        }
      }
    }

    // Save form Information
    if (this.deliverable.getGeographicScopes() != null) {
      for (DeliverableGeographicScope deliverableScope : this.deliverable.getGeographicScopes()) {
        if (deliverableScope != null) {
          if (deliverableScope.getId() == null) {
            DeliverableGeographicScope deliverableScopeSave = new DeliverableGeographicScope();
            deliverableScopeSave.setDeliverable(deliverable);
            deliverableScopeSave.setPhase(phase);
            RepIndGeographicScope repIndGeographicScope = null;
            if (deliverableScope.getRepIndGeographicScope() != null
              && deliverableScope.getRepIndGeographicScope().getId() != null) {
              repIndGeographicScope = repIndGeographicScopeManager
                .getRepIndGeographicScopeById(deliverableScope.getRepIndGeographicScope().getId());
            }

            if (repIndGeographicScope != null) {
              deliverableScopeSave.setRepIndGeographicScope(repIndGeographicScope);
            }

            deliverableGeographicScopeManager.saveDeliverableGeographicScope(deliverableScopeSave);
            // This is to add innovationCrpSave to generate correct auditlog.
            this.deliverable.getDeliverableGeographicScopes().add(deliverableScopeSave);
          }
        }
      }
    }
  }

  public void saveMetadata() {
    if (deliverable.getMetadataElements() != null) {
      for (DeliverableMetadataElement deliverableMetadataElement : deliverable.getMetadataElements()) {
        if (deliverableMetadataElement != null && deliverableMetadataElement.getMetadataElement() != null) {
          deliverableMetadataElement.setDeliverable(deliverable);
          deliverableMetadataElement.setPhase(this.getActualPhase());
          // Search by DOI
          if (deliverableMetadataElement.getMetadataElement().getId() != null
            && 36L == deliverableMetadataElement.getMetadataElement().getId()) {
            String cleanDoi = DOIService.tryGetDoiName(deliverableMetadataElement.getElementValue());
            if (deliverableMetadataElement.getElementValue() != null
              && !deliverableMetadataElement.getElementValue().isEmpty() && !cleanDoi.isEmpty()) {
              deliverableMetadataElement.setElementValue(cleanDoi);
              deliverableMetadataElement.setHide(true);
            }
          } else {
            // Search by handle
            if (deliverableMetadataElement.getMetadataElement().getId() != null
              && 35L == deliverableMetadataElement.getMetadataElement().getId()) {
              // String cleanHandle = HandleService.tryGetDoiName(deliverableMetadataElement.getElementValue());
              String cleanHandle = deliverableMetadataElement.getElementValue();
              if (deliverableMetadataElement.getElementValue() != null
                && !deliverableMetadataElement.getElementValue().isEmpty() && !cleanHandle.isEmpty()) {
                deliverableMetadataElement.setElementValue(cleanHandle);
                deliverableMetadataElement.setHide(true);
              }
            }
          }
          deliverableMetadataElementManager.saveDeliverableMetadataElement(deliverableMetadataElement);
        }
      }
    }
  }

  private void saveParticipant() {
    long clusterParticipantID = this.getActualClusterParticipantID();
    // If deliverable is mapped to IPI 2.3 the hasTrainees question is selected as YES
    if (this.isDeliverableMappedToTrainessIndicator()) {
      this.setYesHasTraineesQuestion();
    }

    if (deliverable.getDeliverableParticipant() != null
      && deliverable.getDeliverableParticipant().getHasParticipants() != null) {
      DeliverableParticipant participant = new DeliverableParticipant();
      DeliverableParticipant deliverableParticipantDB = new DeliverableParticipant();
      List<DeliverableParticipant> deliverableParticipants = deliverableParticipantManager
        .findDeliverableParticipantByDeliverableAndPhase(deliverable.getId(), this.getActualPhase().getId());
      if (deliverableParticipants != null && deliverableParticipants.size() > 0) {
        deliverableParticipantDB = deliverableParticipants.get(0);
        if (deliverableParticipants.size() > 1) {
          logger.warn("There is more than one deliverableParticipant in database for deliverable: "
            + deliverable.getId() + ", phase: " + this.getActualPhase().getComposedName());
        }
      }
      if (deliverableParticipantDB != null && deliverableParticipantDB.getId() != null
        && deliverableParticipantDB.getId() != -1) {
        participant = deliverableParticipantManager.getDeliverableParticipantById(deliverableParticipantDB.getId());
      } else {
        participant.setId(null);
        participant.setDeliverable(deliverableManager.getDeliverableById(deliverableID));
        participant.setPhase(this.getActualPhase());
        participant = deliverableParticipantManager.saveDeliverableParticipant(participant);
      }

      participant.setHasParticipants(deliverable.getDeliverableParticipant().getHasParticipants());

      if (participant.getHasParticipants()) {
        participant.setEventActivityName(deliverable.getDeliverableParticipant().getEventActivityName());
        participant.setFocus(deliverable.getDeliverableParticipant().getFocus());
        participant.setLikelyOutcomes(deliverable.getDeliverableParticipant().getLikelyOutcomes());
        if (deliverable.getDeliverableParticipant().getRepIndTypeActivity() != null
          && deliverable.getDeliverableParticipant().getRepIndTypeActivity().getId() != -1) {
          RepIndTypeActivity repIndTypeActivity = repIndTypeActivityManager
            .getRepIndTypeActivityById(deliverable.getDeliverableParticipant().getRepIndTypeActivity().getId());

          participant.setRepIndTypeActivity(repIndTypeActivity);

          if (participant.getRepIndTypeActivity().getId().equals(this.getReportingIndTypeActivityAcademicDegree())) {
            participant.setAcademicDegree(deliverable.getDeliverableParticipant().getAcademicDegree());
          } else {
            participant.setAcademicDegree(null);
          }
          if (participant.getRepIndTypeActivity().getIsFormal()) {
            participant.setRepIndTrainingTerm(deliverable.getDeliverableParticipant().getRepIndTrainingTerm());
          } else {
            participant.setRepIndTrainingTerm(null);
          }

        } else {
          participant.setRepIndTypeActivity(null);
          participant.setAcademicDegree(null);
          participant.setRepIndTrainingTerm(null);
        }
        participant.setParticipants(deliverable.getDeliverableParticipant().getParticipants());
        if (deliverable.getDeliverableParticipant().getEstimateParticipants() != null) {
          participant.setEstimateParticipants(deliverable.getDeliverableParticipant().getEstimateParticipants());
        } else {
          participant.setEstimateParticipants(false);
        }
        participant.setFemales(deliverable.getDeliverableParticipant().getFemales());
        if (deliverable.getDeliverableParticipant().getEstimateFemales() != null) {
          participant.setEstimateFemales(deliverable.getDeliverableParticipant().getEstimateFemales());
        } else {
          participant.setEstimateFemales(false);
        }
        if (deliverable.getDeliverableParticipant().getDontKnowFemale() != null) {
          participant.setDontKnowFemale(deliverable.getDeliverableParticipant().getDontKnowFemale());
        } else {
          participant.setDontKnowFemale(false);
        }
        if (deliverable.getDeliverableParticipant().getRepIndTypeParticipant() != null
          && deliverable.getDeliverableParticipant().getRepIndTypeParticipant().getId() != -1) {
          participant.setRepIndTypeParticipant(deliverable.getDeliverableParticipant().getRepIndTypeParticipant());
        } else {
          participant.setRepIndTypeParticipant(null);
        }
        participant.setAfrican(deliverable.getDeliverableParticipant().getAfrican());
        if (deliverable.getDeliverableParticipant().getEstimateAfrican() != null) {
          participant.setEstimateAfrican(deliverable.getDeliverableParticipant().getEstimateAfrican());
        } else {
          participant.setEstimateAfrican(false);
        }
        participant.setYouth(deliverable.getDeliverableParticipant().getYouth());
        if (deliverable.getDeliverableParticipant().getEstimateYouth() != null) {
          participant.setEstimateYouth(deliverable.getDeliverableParticipant().getEstimateYouth());
        } else {
          participant.setEstimateYouth(false);
        }

        participant.setActive(true);

        if (this.hasSpecificities(APConstants.DELIVERABLE_SHARED_CLUSTERS_TRAINEES_ACTIVE)) {

          // save participant information in cluster participant table for actual cluster
          if (deliverable.getSharedDeliverables() == null || deliverable.getSharedDeliverables().isEmpty()) {

            if (clusterParticipantID != 0) {
              DeliverableClusterParticipant clusterParticipant = new DeliverableClusterParticipant();
              try {
                clusterParticipant =
                  deliverableClusterParticipantManager.getDeliverableClusterParticipantById(clusterParticipantID);
              } catch (Exception e) {
                Log.error(e + " error getting cluster participant by ID");
              }
              if (clusterParticipant != null) {
                clusterParticipant.setDeliverable(deliverable);
                clusterParticipant.setPhase(this.getActualPhase());
                clusterParticipant.setProject(project);
                clusterParticipant.setFemales(deliverable.getDeliverableParticipant().getFemales());
                clusterParticipant.setAfrican(deliverable.getDeliverableParticipant().getAfrican());
                clusterParticipant.setYouth(deliverable.getDeliverableParticipant().getYouth());
                clusterParticipant.setParticipants(deliverable.getDeliverableParticipant().getParticipants());
                deliverableClusterParticipantManager.saveDeliverableClusterParticipant(clusterParticipant);
              }
            }
          }
        }
      } else {
        participant.setEventActivityName(null);
        participant.setRepIndTypeActivity(null);
        participant.setAcademicDegree(null);
        participant.setParticipants(null);
        participant.setEstimateParticipants(null);
        participant.setFemales(null);
        participant.setEstimateFemales(null);
        participant.setDontKnowFemale(null);
        participant.setRepIndTypeParticipant(null);
        participant.setRepIndTrainingTerm(null);
        participant.setAfrican(null);
        participant.setEstimateAfrican(null);
        participant.setYouth(null);
        participant.setEstimateYouth(null);
        participant.setFocus(null);
        participant.setLikelyOutcomes(null);

        // Delete cluster participants
        if (deliverable.getClusterParticipant() != null) {
          for (DeliverableClusterParticipant clusterParticipant : deliverable.getClusterParticipant()) {
            if (clusterParticipant != null && clusterParticipant.getId() != null) {
              deliverableClusterParticipantManager.deleteDeliverableClusterParticipant(clusterParticipant.getId());
            }
          }
        }
      }
      deliverableParticipantManager.saveDeliverableParticipant(participant);
    }
  }

  /**
   * All we are doing here is setting the modification justification.
   */
  private void saveProjectAuditData() {
    // projectDB is a managed hibernate, project is in a detached state
    Project projectDB = projectManager.getProjectById(project.getId());

    projectDB.setModificationJustification(this.getJustification());
    // No need to call save as hibernate will detect the changes and auto flush.
  }

  /**
   * Save Deliverable Project Outcome Information
   * 
   * @param delivearble
   * @param phase
   */
  public void saveProjectOutcomes(Deliverable deliverable, Phase phase) {

    // Search and deleted form Information
    if (deliverable.getDeliverableProjectOutcomes() != null && deliverable.getDeliverableProjectOutcomes().size() > 0) {

      List<DeliverableProjectOutcome> outcomePrev = new ArrayList<>(deliverable.getDeliverableProjectOutcomes().stream()
        .filter(nu -> nu.getPhase().getId().equals(phase.getId())).collect(Collectors.toList()));

      for (DeliverableProjectOutcome deliverableOutcome : outcomePrev) {
        if (this.deliverable.getProjectOutcomes() == null
          || !this.deliverable.getProjectOutcomes().contains(deliverableOutcome)) {
          this.deliverableProjectOutcomeManager.deleteDeliverableProjectOutcome(deliverableOutcome.getId(),
            this.getActualPhase().getId());
        }
      }
    }

    // Save form Information
    if (this.deliverable.getProjectOutcomes() != null && !this.deliverable.getProjectOutcomes().isEmpty()) {
      for (DeliverableProjectOutcome deliverableOutcome : this.deliverable.getProjectOutcomes()) {
        if (deliverableOutcome.getId() == null) {
          DeliverableProjectOutcome deliverableOutcomeSave = new DeliverableProjectOutcome();
          deliverableOutcomeSave.setDeliverable(deliverable);
          deliverableOutcomeSave.setPhase(phase);

          if (deliverableOutcome.getProjectOutcome() != null
            && deliverableOutcome.getProjectOutcome().getId() != null) {
            ProjectOutcome outcome =
              projectOutcomeManager.getProjectOutcomeById(deliverableOutcome.getProjectOutcome().getId());
            deliverableOutcomeSave.setProjectOutcome(outcome);

            this.deliverableProjectOutcomeManager.saveDeliverableProjectOutcome(deliverableOutcomeSave);
            // This is to add studyCrpSave to generate correct auditlog.
            this.deliverable.getDeliverableProjectOutcomes().add(deliverableOutcomeSave);
          }
        }
      }
    }
  }

  public void saveProjects(Deliverable deliverableDB) {
    try {
      DeliverableClusterParticipant deliverableClusterParticipantDelete = new DeliverableClusterParticipant();
      // Search and deleted form Information
      if (deliverableDB.getProjectDeliverableShareds() != null
        && !deliverableDB.getProjectDeliverableShareds().isEmpty()) {

        List<ProjectDeliverableShared> projectPrev = new ArrayList<>(deliverableDB.getProjectDeliverableShareds()
          .stream().filter(nu -> nu.isActive() && nu.getPhase().getId().equals(this.getActualPhase().getId()))
          .collect(Collectors.toList()));

        for (ProjectDeliverableShared deliverableProject : projectPrev) {
          if (this.deliverable.getSharedDeliverables() == null
            || !this.deliverable.getSharedDeliverables().contains(deliverableProject)) {
            projectDeliverableSharedManager.deleteProjectDeliverableShared(deliverableProject.getId());

            try {
              // try to delete cluster participants summary
              if (deliverableProject != null && deliverableProject.getProject() != null
                && deliverableProject.getProject().getId() != null
                && this.deliverable.getClusterParticipant() != null) {

                deliverableClusterParticipantDelete =
                  deliverableClusterParticipantManager.getDeliverableClusterParticipantByDeliverableProjectPhase(
                    deliverableProject.getDeliverable().getId(), deliverableProject.getProject().getId(),
                    this.getActualPhase().getId()).get(0);
                if (deliverableClusterParticipantDelete != null
                  && deliverableClusterParticipantDelete.getId() != null) {

                  DeliverableClusterParticipant actualClusterParticipant = new DeliverableClusterParticipant();
                  actualClusterParticipant = this.actualDeliverableClusterParticipant();
                  if (actualClusterParticipant != null && actualClusterParticipant.getId() != null
                    && (!deliverableClusterParticipantDelete.getId().equals(actualClusterParticipant.getId()))) {
                    deliverableClusterParticipantManager
                      .deleteDeliverableClusterParticipant(deliverableClusterParticipantDelete.getId());
                  }
                }
              }
            } catch (Exception e) {
              logger.error(e + "error deleting cluster participant");
            }
          }
        }
      }

    } catch (Exception e) {
      logger.error("unable to get cluster shared", e);
    }

    // Save form Information
    try {
      if (this.deliverable.getSharedDeliverables() != null & !this.deliverable.getSharedDeliverables().isEmpty()) {
        for (ProjectDeliverableShared deliverableProject : this.deliverable.getSharedDeliverables()) {
          if (deliverableProject != null && deliverableProject.getId() == null) {
            ProjectDeliverableShared deliverableProjectSave = new ProjectDeliverableShared();
            deliverableProjectSave.setDeliverable(deliverableDB);
            deliverableProjectSave.setPhase(this.getActualPhase());

            Project project = this.projectManager.getProjectById(deliverableProject.getProject().getId());

            deliverableProjectSave.setProject(project);

            this.projectDeliverableSharedManager.saveProjectDeliverableShared(deliverableProjectSave);
            // This is to add studyProjectSave to generate correct
            // auditlog.
            this.deliverable.getProjectDeliverableShareds().add(deliverableProjectSave);
          }
        }
      }
    } catch (Exception e) {
      logger.error("unable to get cluster shared", e);
    }

  }

  public void savePublicationMetadata() {
    if (deliverable.getPublication() != null) {
      deliverable.getPublication().setDeliverable(deliverable);
      if (deliverable.getPublication().getId() != null && deliverable.getPublication().getId().intValue() == -1) {
        deliverable.getPublication().setId(null);
      }
      deliverable.getPublication().setPhase(this.getActualPhase());
      deliverablePublicationMetadataManager.saveDeliverablePublicationMetadata(deliverable.getPublication());

    }
  }

  public void saveQualityCheck() {
    DeliverableQualityCheck qualityCheck;
    if (deliverable.getQualityCheck() != null && deliverable.getQualityCheck().getId() != -1) {
      qualityCheck =
        deliverableQualityCheckManager.getDeliverableQualityCheckById(deliverable.getQualityCheck().getId());
    } else {
      qualityCheck = new DeliverableQualityCheck();
      qualityCheck.setDeliverable(deliverableManager.getDeliverableById(deliverable.getId()));
    }

    if (deliverable.getQualityCheck().getDataDictionary() != null) {
      long id = deliverable.getQualityCheck().getDataDictionary().getId();
      DeliverableQualityAnswer answer = deliverableQualityAnswerManager.getDeliverableQualityAnswerById(id);

      qualityCheck.setDataDictionary(answer);
    }
    if (deliverable.getQualityCheck().getQualityAssurance() != null) {
      long id = deliverable.getQualityCheck().getQualityAssurance().getId();
      DeliverableQualityAnswer answer = deliverableQualityAnswerManager.getDeliverableQualityAnswerById(id);

      qualityCheck.setQualityAssurance(answer);
    }

    if (deliverable.getQualityCheck().getDataTools() != null) {
      long id = deliverable.getQualityCheck().getDataTools().getId();
      DeliverableQualityAnswer answer = deliverableQualityAnswerManager.getDeliverableQualityAnswerById(id);

      qualityCheck.setDataTools(answer);
    }

    if (deliverable.getQualityCheck().getFileAssurance() != null) {
      if (deliverable.getQualityCheck().getFileAssurance().getId() != null
        && deliverable.getQualityCheck().getQualityAssurance() != null
        && Integer.valueOf(deliverable.getQualityCheck().getQualityAssurance().getId().intValue())
          .equals(APConstants.DELIVERABLE_QUALITY_ANSWER_YES)) {
        FileDB fileDb;
        // Set FileDB to null if an exception occurs (-1 id)
        try {
          fileDb = fileDBManager.getFileDBById(deliverable.getQualityCheck().getFileAssurance().getId());
        } catch (IllegalArgumentException e) {
          fileDb = null;
        }
        qualityCheck.setFileAssurance(fileDb);
      } else {
        qualityCheck.setFileAssurance(null);
      }
    } else {
      qualityCheck.setFileAssurance(null);
    }
    if (qualityCheck.getFileAssurance() != null) {
      if (qualityCheck.getFileAssurance().getId() == null) {
        qualityCheck.setFileAssurance(null);
      }
    }

    if (deliverable.getQualityCheck().getFileDictionary() != null) {
      if (deliverable.getQualityCheck().getFileDictionary().getId() != null
        && deliverable.getQualityCheck().getDataDictionary() != null
        && Integer.valueOf(deliverable.getQualityCheck().getDataDictionary().getId().intValue())
          .equals(APConstants.DELIVERABLE_QUALITY_ANSWER_YES)) {
        FileDB fileDb;
        // Set FileDB to null if an exception occurs (-1 id)
        try {
          fileDb = fileDBManager.getFileDBById(deliverable.getQualityCheck().getFileDictionary().getId());
        } catch (IllegalArgumentException e) {
          fileDb = null;
        }
        qualityCheck.setFileDictionary(fileDb);
      } else {
        qualityCheck.setFileDictionary(null);
      }
    } else {
      qualityCheck.setFileDictionary(null);
    }
    if (qualityCheck.getFileDictionary() != null) {
      if (qualityCheck.getFileDictionary().getId() == null) {
        qualityCheck.setFileDictionary(null);
      }
    }

    if (deliverable.getQualityCheck().getFileTools() != null) {
      if (deliverable.getQualityCheck().getFileTools().getId() != null
        && deliverable.getQualityCheck().getDataTools() != null
        && Integer.valueOf(deliverable.getQualityCheck().getDataTools().getId().intValue())
          .equals(APConstants.DELIVERABLE_QUALITY_ANSWER_YES)) {
        FileDB fileDb;
        // Set FileDB to null if an exception occurs (-1 id)
        try {
          fileDb = fileDBManager.getFileDBById(deliverable.getQualityCheck().getFileTools().getId());
        } catch (IllegalArgumentException e) {
          fileDb = null;
        }
        qualityCheck.setFileTools(fileDb);
      } else {
        qualityCheck.setFileTools(null);
      }
    } else {
      qualityCheck.setFileTools(null);
    }
    if (qualityCheck.getFileTools() != null) {
      if (qualityCheck.getFileTools().getId() == null) {
        qualityCheck.setFileTools(null);
      }
    }

    qualityCheck.setLinkAssurance(deliverable.getQualityCheck().getLinkAssurance());
    qualityCheck.setLinkDictionary(deliverable.getQualityCheck().getLinkDictionary());
    qualityCheck.setLinkTools(deliverable.getQualityCheck().getLinkTools());

    qualityCheck.setPhase(this.getActualPhase());
    deliverableQualityCheckManager.saveDeliverableQualityCheck(qualityCheck);

  }

  public void saveUsers() {
    if (deliverable.getUsers() == null) {

      deliverable.setUsers(new ArrayList<>());
    }
    Deliverable deliverableDB = deliverableManager.getDeliverableById(deliverableID);
    for (DeliverableUser deliverableUser : deliverableDB.getDeliverableUsers().stream()
      .filter(c -> c.isActive() && c.getPhase().equals(this.getActualPhase())).collect(Collectors.toList())) {
      if (deliverable.getUsers() != null) {
        if (!deliverable.getUsers().contains(deliverableUser)) {
          deliverableUserManager.deleteDeliverableUser(deliverableUser.getId());
        }
      }
    }

    if (deliverable.getUsers() != null) {
      for (DeliverableUser deliverableUser : deliverable.getUsers()) {

        if (deliverableUser.getId() == null || deliverableUser.getId().intValue() == -1) {
          DeliverableUser deliverableUserSave = new DeliverableUser();

          deliverableUserSave.setLastName(deliverableUser.getLastName());
          deliverableUserSave.setFirstName(deliverableUser.getFirstName());
          deliverableUserSave.setElementId(deliverableUser.getElementId());
          deliverableUserSave.setPhase(this.getActualPhase());
          deliverableUserSave.setDeliverable(deliverable);
          deliverableUserManager.saveDeliverableUser(deliverableUserSave);
        } else {
          deliverableUser.setLastName(deliverableUser.getLastName());
          deliverableUser.setFirstName(deliverableUser.getFirstName());
          deliverableUser.setElementId(deliverableUser.getElementId());
          deliverableUser.setPhase(this.getActualPhase());
          deliverableUser.setDeliverable(deliverable);
          deliverableUserManager.saveDeliverableUser(deliverableUser);
        }
      }
    }
  }

  public void setAcceptationPercentage(Integer acceptationPercentage) {
    this.acceptationPercentage = acceptationPercentage;
  }

  public void setActivities(List<Activity> activities) {
    this.activities = activities;
  }

  public void setAnswers(List<DeliverableQualityAnswer> answers) {
    this.answers = answers;
  }

  public void setAnswersDataDic(List<DeliverableQualityAnswer> answersDataDic) {
    this.answersDataDic = answersDataDic;
  }

  public void setCgiarCrossCuttingMarkers(List<CgiarCrossCuttingMarker> cgiarCrossCuttingMarkers) {
    this.cgiarCrossCuttingMarkers = cgiarCrossCuttingMarkers;
  }

  public void setCountries(List<LocElement> countries) {
    this.countries = countries;
  }

  public void setCrps(ArrayList<GlobalUnit> crps) {
    this.crps = crps;
  }

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setDeliverableID(long deliverableID) {
    this.deliverableID = deliverableID;
  }

  public void setDeliverableSubTypes(List<DeliverableType> deliverableSubTypes) {
    this.deliverableSubTypes = deliverableSubTypes;
  }

  public void setDeliverableTypeParent(List<DeliverableType> deliverableTypeParent) {
    this.deliverableTypeParent = deliverableTypeParent;
  }

  public void setDivisions(List<PartnerDivision> divisions) {
    this.divisions = divisions;
  }

  public void setDuplicated(boolean isDuplicated) {
    this.isDuplicated = isDuplicated;
  }

  public void setExistCurrentCluster(boolean existCurrentCluster) {
    this.existCurrentCluster = existCurrentCluster;
  }

  public void setFeedbackComments(List<FeedbackQACommentableFields> feedbackComments) {
    this.feedbackComments = feedbackComments;
  }

  public void setFocusLevels(List<RepIndGenderYouthFocusLevel> focusLevels) {
    this.focusLevels = focusLevels;
  }

  public void setFundingSources(List<FundingSource> fundingSources) {
    this.fundingSources = fundingSources;
  }

  public void setGenderLevels(List<GenderType> genderLevels) {
    this.genderLevels = genderLevels;
  }

  public void setIndexTab(int indexTab) {
    this.indexTab = indexTab;
  }

  public void setKeyOutputs(List<CrpClusterKeyOutput> keyOutputs) {
    this.keyOutputs = keyOutputs;
  }

  public void setLoggedCrp(GlobalUnit loggedCrp) {
    this.loggedCrp = loggedCrp;
  }

  public void setMyProjects(List<Project> myProjects) {
    this.myProjects = myProjects;
  }

  public void setPartnerInstitutions(List<Institution> partnerInstitutions) {
    this.partnerInstitutions = partnerInstitutions;
  }

  public void setPartnerPersons(List<ProjectPartnerPerson> partnerPersons) {
    this.partnerPersons = partnerPersons;
  }

  public void setPartners(List<ProjectPartner> partners) {
    this.partners = partners;
  }

  public void setProgramOutcomes(List<CrpProgramOutcome> programOutcomes) {
    this.programOutcomes = programOutcomes;
  }

  public void setPrograms(ArrayList<CrpProgram> programs) {
    this.programs = programs;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public void setProjectID(long projectID) {
    this.projectID = projectID;
  }

  public void setProjectOutcome(List<ProjectOutcome> projectOutcome) {
    this.projectOutcome = projectOutcome;
  }

  public void setProjectOutcomes(List<ProjectOutcome> projectOutcomes) {
    this.projectOutcomes = projectOutcomes;
  }

  public void setProjectPrograms(List<ProjectFocus> projectPrograms) {
    this.projectPrograms = projectPrograms;
  }

  public void setRepIndFillingTypes(List<RepIndFillingType> repIndFillingTypes) {
    this.repIndFillingTypes = repIndFillingTypes;
  }

  public void setRepIndGeographicScopes(List<RepIndGeographicScope> repIndGeographicScopes) {
    this.repIndGeographicScopes = repIndGeographicScopes;
  }

  public void setRepIndPatentStatuses(List<RepIndPatentStatus> repIndPatentStatuses) {
    this.repIndPatentStatuses = repIndPatentStatuses;
  }

  public void setRepIndRegions(List<LocElement> repIndRegions) {
    this.repIndRegions = repIndRegions;
  }

  public void setRepIndTrainingTerms(List<RepIndTrainingTerm> repIndTrainingTerms) {
    this.repIndTrainingTerms = repIndTrainingTerms;
  }

  public void setRepIndTypeActivities(List<RepIndTypeActivity> repIndTypeActivities) {
    this.repIndTypeActivities = repIndTypeActivities;
  }

  public void setRepIndTypeParticipants(List<RepIndTypeParticipant> repIndTypeParticipants) {
    this.repIndTypeParticipants = repIndTypeParticipants;
  }

  public void setRepositoryChannels(List<RepositoryChannel> repositoryChannels) {
    this.repositoryChannels = repositoryChannels;
  }

  public void setResponsibleUsers(List<User> responsibleUsers) {
    this.responsibleUsers = responsibleUsers;
  }

  public void setStatus(Map<String, String> status) {
    this.status = status;
  }

  public void setStatuses(Map<String, String> statuses) {
    this.statuses = statuses;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  /**
   * Select by default 'yes' answer in has trainees question
   */
  public void setYesHasTraineesQuestion() {
    if (deliverable.getDeliverableParticipant() != null) {
      deliverable.getDeliverableParticipant().setHasParticipants(true);
    }
  }

  /**
   * Save and update list of deliverables funding sources for all phases
   *
   * @param deliverablePrew
   */
  private void updateDeliverableFundingSources(Deliverable deliverablePrew) {
    if (deliverable.getFundingSources() != null) {
      if (deliverablePrew.getDeliverableFundingSources() != null
        && deliverablePrew.getDeliverableFundingSources().size() > 0) {
        List<DeliverableFundingSource> fundingSourcesPrew = deliverablePrew.getDeliverableFundingSources().stream()
          .filter(dp -> dp.isActive() && dp.getPhase() != null && dp.getPhase().equals(this.getActualPhase()))
          .collect(Collectors.toList());

        for (DeliverableFundingSource deliverableFundingSource : fundingSourcesPrew) {
          if (!deliverable.getFundingSources().contains(deliverableFundingSource)) {
            deliverableFundingSourceManager.deleteDeliverableFundingSource(deliverableFundingSource.getId());
          }
        }
      }

      for (DeliverableFundingSource deliverableFundingSource : deliverable.getFundingSources()) {
        if (deliverableFundingSource.getId() == null || deliverableFundingSource.getId() == -1) {

          deliverableFundingSource.setDeliverable(deliverableManager.getDeliverableById(deliverableID));
          deliverableFundingSource.setPhase(this.getActualPhase());
          deliverableFundingSourceManager.saveDeliverableFundingSource(deliverableFundingSource);
          // This add projectFocus to generate correct auditlog.
          deliverablePrew.getDeliverableFundingSources().add(deliverableFundingSource);
        }
      }
    }
  }

  /**
   * deliverableDb is in a managed state, deliverable is in a detached state.
   *
   * @return Deliverable with a deliverableInfo with client data
   */
  private Deliverable updateDeliverableInfo() {

    Deliverable deliverableBase = deliverableManager.getDeliverableById(deliverableID);
    DeliverableInfo deliverableInfoDb = deliverableBase.getDeliverableInfo(this.getActualPhase());

    deliverableInfoDb.setTitle(deliverable.getDeliverableInfo(this.getActualPhase()).getTitle());
    deliverableInfoDb.setDescription(deliverable.getDeliverableInfo(this.getActualPhase()).getDescription());

    deliverableInfoDb.setYear(deliverable.getDeliverableInfo(this.getActualPhase()).getYear());

    if (deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != null) {
      deliverableInfoDb.setNewExpectedYear(deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear());
    }

    if (this.isPlanningActive() && !this.isUpKeepActive()) {
      if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == Integer
        .parseInt(ProjectStatusEnum.Extended.getStatusId())
        && deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != null) {

        if (deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != -1) {
          deliverableInfoDb
            .setNewExpectedYear(deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear());
        }
      }
    } else {
      if ((deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == Integer
        .parseInt(ProjectStatusEnum.Extended.getStatusId())
        || deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() == Integer
          .parseInt(ProjectStatusEnum.Complete.getStatusId()))
        && deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != null) {

        if (deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear() != -1) {
          deliverableInfoDb
            .setNewExpectedYear(deliverable.getDeliverableInfo(this.getActualPhase()).getNewExpectedYear());
        }

      }
    }

    if (deliverable.getDeliverableInfo(this.getActualPhase()).getStatus() != null) {
      deliverableInfoDb.setStatus(deliverable.getDeliverableInfo(this.getActualPhase()).getStatus());
    }

    if (deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType() != null
      && deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getId() != null
      && deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getId().longValue() != -1) {
      DeliverableType deliverableType = deliverableTypeManager
        .getDeliverableTypeById(deliverable.getDeliverableInfo(this.getActualPhase()).getDeliverableType().getId());

      deliverableInfoDb.setDeliverableType(deliverableType);
    } else {
      deliverableInfoDb.setDeliverableType(null);
    }

    // Set CrpProgramOutcome to null if has an -1 id
    if (deliverable.getDeliverableInfo().getCrpProgramOutcome() == null
      || deliverable.getDeliverableInfo().getCrpProgramOutcome().getId() == null
      || deliverable.getDeliverableInfo().getCrpProgramOutcome().getId().longValue() == -1) {
      deliverableInfoDb.setCrpProgramOutcome(null);
    } else {
      deliverableInfoDb.setCrpProgramOutcome(crpProgramOutcomeManager.getCrpProgramOutcomeById(
        deliverable.getDeliverableInfo(this.getActualPhase()).getCrpProgramOutcome().getId()));
    }
    deliverableInfoDb
      .setStatusDescription(deliverable.getDeliverableInfo(this.getActualPhase()).getStatusDescription());
    if (this.isReportingActive() || this.isUpKeepActive()) {

      if (deliverable.getDeliverableInfo(this.getActualPhase()).getAdoptedLicense() != null) {
        deliverableInfoDb.setAdoptedLicense(deliverable.getDeliverableInfo(this.getActualPhase()).getAdoptedLicense());
        if (deliverable.getDeliverableInfo(this.getActualPhase()).getAdoptedLicense().booleanValue()) {
          deliverableInfoDb
            .setAdoptedLicense(deliverable.getDeliverableInfo(this.getActualPhase()).getAdoptedLicense());
        }
      }
    }

    deliverableInfoDb.setModificationJustification(this.getJustification());

    deliverableBase.setDeliverableInfo(deliverableInfoDb);

    return deliverableBase;
  }

  private void updateProjectLp6ContributionDeliverable() {
    List<ProjectLp6ContributionDeliverable> projectLp6ContributionDeliverables =
      new ArrayList<ProjectLp6ContributionDeliverable>();
    if (deliverable.getDeliverableLp6s() != null) {
      deliverable.getDeliverableLp6s().stream()
        .filter(dl -> dl.isActive() && dl.getPhase().equals(this.getActualPhase())).collect(Collectors.toList());
    }

    boolean haslp6Dleiverables =
      projectLp6ContributionDeliverables != null && !projectLp6ContributionDeliverables.isEmpty();

    // Save projectLp6ContributionDeliverable
    if (deliverable.getContribution() && !haslp6Dleiverables) {

      ProjectLp6ContributionDeliverable projectLp6ContributionDeliverable = new ProjectLp6ContributionDeliverable();

      List<ProjectLp6Contribution> projectLp6Contribution = project.getProjectLp6Contributions().stream()
        .filter(pl -> pl.isActive() && pl.getPhase().equals(this.getActualPhase())).collect(Collectors.toList());

      projectLp6ContributionDeliverable.setProjectLp6Contribution(projectLp6Contribution.get(0));

      projectLp6ContributionDeliverable.setPhase(this.getActualPhase());
      projectLp6ContributionDeliverable.setDeliverable(deliverable);
      projectLp6ContributionDeliverableManager.saveProjectLp6ContributionDeliverable(projectLp6ContributionDeliverable);

    }

    // Delete projectLp6ContributionDeliverable
    if (!deliverable.getContribution() && haslp6Dleiverables) {
      for (ProjectLp6ContributionDeliverable projectLp6ContributionDeliverable : projectLp6ContributionDeliverables) {
        projectLp6ContributionDeliverableManager
          .deleteProjectLp6ContributionDeliverable(projectLp6ContributionDeliverable.getId());
      }
    }
  }

  @Override
  public void validate() {
    if (save) {
      deliverableValidator.validate(this, deliverable, true);
    }
  }

}
