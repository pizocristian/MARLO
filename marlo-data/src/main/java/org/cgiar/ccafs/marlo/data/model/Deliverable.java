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

package org.cgiar.ccafs.marlo.data.model;

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;

/**
 * @author Hermes Jiménez - CIAT/CCAFS
 */
public class Deliverable extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = 1867818669925473872L;


  @Expose
  private Project project;
  @Expose
  private Date createDate;
  @Expose
  private GlobalUnit crp;
  @Expose
  private Phase phase;
  @Expose
  private Boolean isPublication;
  private String commentStatus;

  private String tagTitle;
  private List<DeliverableActivity> activities;
  private DeliverableInfo deliverableInfo;
  private DeliverableDissemination dissemination;
  private DeliverableQualityCheck qualityCheck;
  private Set<DeliverableGenderLevel> deliverableGenderLevels = new HashSet<DeliverableGenderLevel>(0);
  private List<DeliverableGenderLevel> genderLevels;
  private Set<DeliverableActivity> deliverableActivities = new HashSet<DeliverableActivity>(0);
  private Set<SectionStatus> sectionStatuses = new HashSet<SectionStatus>(0);
  private Set<DeliverableFundingSource> deliverableFundingSources = new HashSet<DeliverableFundingSource>(0);
  private List<DeliverableFundingSource> fundingSources;
  private Set<DeliverableQualityCheck> deliverableQualityChecks = new HashSet<DeliverableQualityCheck>(0);
  private Set<DeliverableMetadataElement> deliverableMetadataElements = new HashSet<DeliverableMetadataElement>(0);
  private Set<DeliverableDissemination> deliverableDisseminations = new HashSet<DeliverableDissemination>(0);
  private Set<DeliverableDataSharingFile> deliverableDataSharingFiles = new HashSet<DeliverableDataSharingFile>(0);
  private Set<DeliverablePublicationMetadata> deliverablePublicationMetadatas =
    new HashSet<DeliverablePublicationMetadata>(0);
  private Set<DeliverableDataSharing> deliverableDataSharings = new HashSet<DeliverableDataSharing>(0);
  private List<DeliverableMetadataElement> metadataElements;
  private List<DeliverableDissemination> disseminations;
  private List<DeliverableDataSharingFile> dataSharingFiles;
  private List<DeliverableFile> files;
  private List<DeliverablePublicationMetadata> publicationMetadatas;
  private Set<DeliverableLocation> deliverableLocations = new HashSet<DeliverableLocation>(0);
  private List<String> countriesIds = new ArrayList<>();
  private List<DeliverableLocation> countries;
  private String countriesIdsText;
  private DeliverablePublicationMetadata publication;
  private List<DeliverableDataSharing> dataSharing;
  private Set<DeliverableProgram> deliverablePrograms = new HashSet<DeliverableProgram>(0);
  private Set<DeliverableLeader> deliverableLeaders = new HashSet<DeliverableLeader>(0);
  private List<DeliverableProgram> programs;
  private List<DeliverableProgram> regions;
  private String flagshipValue;
  private String regionsValue;
  private List<DeliverableLeader> leaders;
  private List<MetadataElement> metadata = new ArrayList<>();
  private Set<DeliverableCrp> deliverableCrps = new HashSet<DeliverableCrp>(0);
  private List<DeliverableCrp> crps;
  private Set<DeliverableUser> deliverableUsers = new HashSet<DeliverableUser>(0);
  private Set<DeliverableInfo> deliverableInfos = new HashSet<DeliverableInfo>(0);
  private List<DeliverableUser> users;
  private Set<DeliverableIntellectualAsset> deliverableIntellectualAssets =
    new HashSet<DeliverableIntellectualAsset>(0);
  private Set<DeliverableParticipant> deliverableParticipants = new HashSet<DeliverableParticipant>(0);
  private Set<DeliverableClusterParticipant> deliverableClusterParticipants =
    new HashSet<DeliverableClusterParticipant>(0);
  private List<DeliverableClusterParticipant> clusterParticipant;
  private DeliverableParticipant deliverableParticipant;
  private Set<DeliverableGeographicRegion> deliverableGeographicRegions = new HashSet<DeliverableGeographicRegion>(0);
  private List<DeliverableGeographicRegion> deliverableRegions;
  private Set<DeliverableCrossCuttingMarker> deliverableCrossCuttingMarkers =
    new HashSet<DeliverableCrossCuttingMarker>(0);
  private List<DeliverableCrossCuttingMarker> crossCuttingMarkers;
  private Set<ProjectLp6ContributionDeliverable> deliverableLp6s = new HashSet<ProjectLp6ContributionDeliverable>(0);

  private Boolean contribution;

  private Set<DeliverableGeographicScope> deliverableGeographicScopes = new HashSet<DeliverableGeographicScope>(0);
  private List<DeliverableGeographicScope> geographicScopes;
  // AR2018 Field
  private List<LiaisonInstitution> selectedFlahsgips;
  private Set<DeliverableUserPartnership> deliverableUserPartnerships = new HashSet<DeliverableUserPartnership>(0);

  // HJ New Deliverable PartnerShips Fields
  private List<DeliverableUserPartnership> responsiblePartnership;
  private List<DeliverableUserPartnership> otherPartnerships;

  // deliverable wos sync
  private Set<DeliverableMetadataExternalSources> deliverableMetadataExternalSources = new HashSet<>(0);
  private List<DeliverableMetadataExternalSources> metadataExternalSources;
  private DeliverableMetadataExternalSources externalSource;

  private Set<DeliverableAltmetricInfo> deliverableAltmetricInfos = new HashSet<>(0);
  private List<DeliverableAltmetricInfo> altmetricInfos;
  private DeliverableAltmetricInfo deliverableAltmetricInfo;

  // Shared Project Innovations
  private List<ProjectDeliverableShared> sharedDeliverables;
  private Set<ProjectDeliverableShared> projectDeliverableShareds = new HashSet<ProjectDeliverableShared>(0);

  private List<DeliverableProjectOutcome> projectOutcomes;
  private Set<DeliverableProjectOutcome> deliverableProjectOutcomes = new HashSet<DeliverableProjectOutcome>(0);

  private List<DeliverableCrpOutcome> crpOutcomes;
  private Set<DeliverableCrpOutcome> deliverableCrpOutcomes = new HashSet<DeliverableCrpOutcome>(0);

  private String sharedWithProjects;
  private String sharedWithMe;
  private String owner;
  private String responsible;

  // CLARISA FAIR Principles
  private Boolean isFindable;

  private Boolean isAccesible;


  private Boolean isInteroperable;


  private Boolean isReusable;


  public Deliverable() {
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }

    Deliverable other = (Deliverable) obj;
    if (this.getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!this.getId().equals(other.getId())) {
      return false;
    }
    return true;
  }

  public List<DeliverableActivity> getActivities() {
    return activities;
  }

  public List<DeliverableAltmetricInfo> getAltmetricInfos() {
    return altmetricInfos;
  }

  public List<DeliverableClusterParticipant> getClusterParticipant() {
    return clusterParticipant;
  }

  public String getCommentStatus() {
    return commentStatus;
  }

  public String getComposedName() {
    String status = "";
    String year = "";
    // Get status name
    if (this.getDeliverableInfo() != null && this.getDeliverableInfo().getStatus() != null) {
      switch (this.getDeliverableInfo().getStatus()) {

        case 1:
          status = "New";
          break;
        case 2:
          status = "On Going";
          break;
        case 3:
          status = "Completed";
          break;
        case 4:
          status = "Extended";
          break;
        case 5:
          status = "Cancelled";
          break;
      }

      // get new expected year for extended deliverables and set the value in year variable
      if (this.getDeliverableInfo().getStatus().intValue() == Integer
        .parseInt(ProjectStatusEnum.Extended.getStatusId())) {
        if (this.getDeliverableInfo().getNewExpectedYear() != null
          && this.getDeliverableInfo().getNewExpectedYear() != -1
          && this.getDeliverableInfo().getNewExpectedYear() != 1) {
          year = this.getDeliverableInfo().getNewExpectedYear() + "";
        }
      }

      // get the year for on going deliverables and set the value in year variable
      if (this.getDeliverableInfo().getStatus().intValue() == Integer
        .parseInt(ProjectStatusEnum.Ongoing.getStatusId())) {
        if (this.getDeliverableInfo().getYear() != 0 && this.getDeliverableInfo().getYear() != -1
          && this.getDeliverableInfo().getYear() != 1) {
          year = this.getDeliverableInfo().getYear() + "";
        }
      }

      // get the year/new expected year for completed and set the value in year variable
      if (this.getDeliverableInfo().getStatus().intValue() == Integer
        .parseInt(ProjectStatusEnum.Complete.getStatusId())) {
        if (this.getDeliverableInfo().getNewExpectedYear() != 0 && this.getDeliverableInfo().getNewExpectedYear() != -1
          && this.getDeliverableInfo().getNewExpectedYear() != 1) {
          year = this.getDeliverableInfo().getNewExpectedYear() + "";
        } else {
          if (this.getDeliverableInfo().getYear() != 0 && this.getDeliverableInfo().getYear() != -1
            && this.getDeliverableInfo().getYear() != 1) {
            year = this.getDeliverableInfo().getYear() + "";
          }
        }
      }

      // get the year/new expected year for cancelled and set the value in year variable
      if (this.getDeliverableInfo().getStatus().intValue() == Integer
        .parseInt(ProjectStatusEnum.Cancelled.getStatusId())) {
        if (this.getDeliverableInfo().getNewExpectedYear() != 0 && this.getDeliverableInfo().getNewExpectedYear() != -1
          && this.getDeliverableInfo().getNewExpectedYear() != 1) {
          year = this.getDeliverableInfo().getNewExpectedYear() + "";
        } else {
          if (this.getDeliverableInfo().getYear() != 0 && this.getDeliverableInfo().getNewExpectedYear() != -1
            && this.getDeliverableInfo().getYear() != 1) {
            year = this.getDeliverableInfo().getYear() + "";
          }
        }
      }

    }

    if (this.getDeliverableInfo() != null) {
      try {

        String statusInfo = status + " " + year;
        if (statusInfo != null && !statusInfo.isEmpty()) {
          statusInfo = statusInfo.trim();
        }

        return "<b> (D" + this.getId() + ") " + this.getDeliverableInfo().getDeliverableType().getName() + "</b> - "
          + this.getDeliverableInfo().getTitle() + " (" + statusInfo + ")";
      } catch (Exception e) {
        return "<b> (D" + this.getId() + ") </b> - " + this.getDeliverableInfo().getTitle();

      }
    }
    return null;

  }

  public Boolean getContribution() {
    return contribution;
  }

  public List<DeliverableLocation> getCountries() {
    return countries;
  }

  public List<String> getCountriesIds() {
    return countriesIds;
  }

  public String getCountriesIdsText() {
    return countriesIdsText;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public List<DeliverableCrossCuttingMarker> getCrossCuttingMarkers() {
    return crossCuttingMarkers;
  }

  public GlobalUnit getCrp() {
    return crp;
  }

  public List<DeliverableCrpOutcome> getCrpOutcomes() {
    return crpOutcomes;
  }

  public List<DeliverableCrp> getCrps() {
    return crps;
  }

  public List<DeliverableDataSharing> getDataSharing() {
    return dataSharing;
  }

  public List<DeliverableDataSharingFile> getDataSharingFiles() {
    return dataSharingFiles;
  }

  public Set<DeliverableActivity> getDeliverableActivities() {
    return deliverableActivities;
  }

  public DeliverableAltmetricInfo getDeliverableAltmetricInfo() {
    return deliverableAltmetricInfo;
  }

  public DeliverableAltmetricInfo getDeliverableAltmetricInfo(Phase phase) {
    if (this.getDeliverableAltmetricInfo() != null) {
      return this.getDeliverableAltmetricInfo();
    } else {
      List<DeliverableAltmetricInfo> infos =
        this.getDeliverableAltmetricInfos().stream().filter(c -> c.getPhase() != null && c.getPhase().getId() != null
          && c.getPhase().getId().equals(phase.getId()) && c.isActive()).collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setDeliverableAltmetricInfo(infos.get(0));
        return this.getDeliverableAltmetricInfo();
      }
    }

    return null;
  }

  public Set<DeliverableAltmetricInfo> getDeliverableAltmetricInfos() {
    return deliverableAltmetricInfos;
  }

  public Set<DeliverableClusterParticipant> getDeliverableClusterParticipants() {
    return deliverableClusterParticipants;
  }

  public Set<DeliverableCrossCuttingMarker> getDeliverableCrossCuttingMarkers() {
    return deliverableCrossCuttingMarkers;
  }

  public Set<DeliverableCrpOutcome> getDeliverableCrpOutcomes() {
    return deliverableCrpOutcomes;
  }

  public Set<DeliverableCrp> getDeliverableCrps() {
    return deliverableCrps;
  }

  public Set<DeliverableDataSharingFile> getDeliverableDataSharingFiles() {
    return deliverableDataSharingFiles;
  }

  public Set<DeliverableDataSharing> getDeliverableDataSharings() {
    return deliverableDataSharings;
  }


  public Set<DeliverableDissemination> getDeliverableDisseminations() {
    return deliverableDisseminations;
  }

  public Set<DeliverableFundingSource> getDeliverableFundingSources() {
    return deliverableFundingSources;
  }

  public Set<DeliverableGenderLevel> getDeliverableGenderLevels() {
    return deliverableGenderLevels;
  }

  public Set<DeliverableGeographicRegion> getDeliverableGeographicRegions() {
    return deliverableGeographicRegions;
  }

  public Set<DeliverableGeographicScope> getDeliverableGeographicScopes() {
    return deliverableGeographicScopes;
  }


  public DeliverableInfo getDeliverableInfo() {
    return deliverableInfo;
  }


  public DeliverableInfo getDeliverableInfo(Phase phase) {
    if (this.getDeliverableInfo() != null) {
      return this.getDeliverableInfo();
    } else {
      List<DeliverableInfo> infos = this
        .getDeliverableInfos().stream().filter(c -> c.getPhase() != null && c.getPhase().getId() != null
          && phase != null && phase.getId() != null && c.getPhase().getId().equals(phase.getId()) && c.isActive())
        .collect(Collectors.toList());
      if (!infos.isEmpty()) {
        this.setDeliverableInfo(infos.get(0));
        return this.getDeliverableInfo();
      }
    }

    return null;
  }


  public Set<DeliverableInfo> getDeliverableInfos() {
    return deliverableInfos;
  }


  public Set<DeliverableIntellectualAsset> getDeliverableIntellectualAssets() {
    return deliverableIntellectualAssets;
  }


  public Set<DeliverableLeader> getDeliverableLeaders() {
    return deliverableLeaders;
  }


  public Set<DeliverableLocation> getDeliverableLocations() {
    return deliverableLocations;
  }


  public Set<ProjectLp6ContributionDeliverable> getDeliverableLp6s() {
    return deliverableLp6s;
  }


  public Set<DeliverableMetadataElement> getDeliverableMetadataElements() {
    return deliverableMetadataElements;
  }


  public Set<DeliverableMetadataExternalSources> getDeliverableMetadataExternalSources() {
    return deliverableMetadataExternalSources;
  }


  public DeliverableParticipant getDeliverableParticipant() {
    return deliverableParticipant;
  }

  public Set<DeliverableParticipant> getDeliverableParticipants() {
    return deliverableParticipants;
  }

  public Set<DeliverableProgram> getDeliverablePrograms() {
    return deliverablePrograms;
  }


  public Set<DeliverableProjectOutcome> getDeliverableProjectOutcomes() {
    return deliverableProjectOutcomes;
  }


  public Set<DeliverablePublicationMetadata> getDeliverablePublicationMetadatas() {
    return deliverablePublicationMetadatas;
  }


  public Set<DeliverableQualityCheck> getDeliverableQualityChecks() {
    return deliverableQualityChecks;
  }

  public List<DeliverableGeographicRegion> getDeliverableRegions() {
    return deliverableRegions;
  }

  public Set<DeliverableUserPartnership> getDeliverableUserPartnerships() {
    return deliverableUserPartnerships;
  }

  public Set<DeliverableUser> getDeliverableUsers() {
    return deliverableUsers;
  }

  public DeliverableDissemination getDissemination() {
    return dissemination;
  }


  public DeliverableDissemination getDissemination(Phase phase) {
    List<DeliverableDissemination> deliverableDisseminations = this.getDeliverableDisseminations().stream()
      .filter(dd -> dd.isActive() && dd.getPhase().equals(phase)).collect(Collectors.toList());
    if (deliverableDisseminations != null && !deliverableDisseminations.isEmpty()) {
      return deliverableDisseminations.get(0);
    }
    return new DeliverableDissemination();
  }

  public List<DeliverableDissemination> getDisseminations() {
    return disseminations;
  }

  public DeliverableMetadataExternalSources getExternalSource() {
    return externalSource;
  }

  public DeliverableMetadataExternalSources getExternalSource(Phase phase) {
    DeliverableMetadataExternalSources externalSourceFound = this.getExternalSource();
    if (externalSourceFound == null) {
      List<DeliverableMetadataExternalSources> externalSourcesFound = this.getDeliverableMetadataExternalSources()
        .stream().filter(e -> e != null && e.getId() != null && e.getPhase() != null && e.getPhase().equals(phase))
        .collect(Collectors.toList());
      if (!externalSourcesFound.isEmpty()) {
        externalSourceFound = externalSourcesFound.get(0);
        this.setExternalSource(externalSourceFound);
      }
    }

    return externalSourceFound;
  }

  public List<DeliverableFile> getFiles() {
    return files;
  }


  public String getFlagshipValue() {
    return flagshipValue;
  }

  public List<DeliverableFundingSource> getFundingSources() {
    return fundingSources;
  }

  public List<DeliverableGenderLevel> getGenderLevels() {
    return genderLevels;
  }

  public List<DeliverableGeographicScope> getGeographicScopes() {
    return geographicScopes;
  }

  public long getID(int metadataID) {

    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              return dmetadata.getId().longValue();
            }
          }
        }


      }

    }

    return -1;
  }

  public Boolean getIsAccesible() {
    return isAccesible;
  }

  public Boolean getIsFindable() {
    return isFindable;
  }

  public Boolean getIsInteroperable() {
    return isInteroperable;
  }

  public Boolean getIsPublication() {
    return isPublication;
  }


  public Boolean getIsReusable() {
    return isReusable;
  }

  public List<DeliverableLeader> getLeaders() {
    return leaders;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public long getMElementID(int metadataID) {
    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              return dmetadata.getId().longValue();
            }
          }
        }
      }
    }
    return -1;
  }

  public List<MetadataElement> getMetadata() {
    return metadata;
  }

  public DeliverableMetadataElement getMetadata(long metadataID) {
    String value = "";
    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              return dmetadata;
            }
          }
        }


      }
    }
    return null;
  }


  public List<DeliverableMetadataElement> getMetadataElements() {
    return metadataElements;
  }

  public List<DeliverableMetadataElement> getMetadataElements(Phase phase) {
    List<DeliverableMetadataElement> deliverableMetadataElements = this.getDeliverableMetadataElements().stream()
      .filter(dm -> dm.isActive() && dm.getPhase().equals(phase)).collect(Collectors.toList());
    if (deliverableMetadataElements != null && !deliverableMetadataElements.isEmpty()) {
      return deliverableMetadataElements;
    }
    return new ArrayList<DeliverableMetadataElement>();
  }

  public List<DeliverableMetadataExternalSources> getMetadataExternalSources() {
    return metadataExternalSources;
  }

  public long getMetadataID(String metadataName) {
    for (MetadataElement mData : metadata) {
      if (mData != null) {
        if (mData.getEcondedName().equals(metadataName)) {
          return mData.getId();
        }
      }
    }
    return -1;
  }

  public int getMetadataIndex(String metadataName) {
    int c = 0;
    for (MetadataElement mData : metadata) {
      if (mData != null) {
        if (mData.getEcondedName().equals(metadataName)) {
          return c;
        }
      }

      c++;
    }
    return -1;
  }

  public String getMetadataValue(long metadataID) {
    String value = "";
    if (metadataElements != null) {
      for (DeliverableMetadataElement dmetadata : metadataElements) {
        if (dmetadata != null) {
          if (dmetadata.getMetadataElement() != null && dmetadata.getMetadataElement().getId() != null) {
            if (dmetadata.getMetadataElement().getId() == metadataID) {
              value = dmetadata.getElementValue();
            }
          }
        }


      }
    }
    return value;
  }

  public String getMetadataValue(String metadataName) {
    if (metadataElements != null) {
      for (DeliverableMetadataElement mData : metadataElements) {
        if (mData != null) {
          if (mData.getMetadataElement() != null) {
            if (mData.getMetadataElement().getElement().equals(metadataName)) {
              return mData.getElementValue();
            }
          }
        }

      }
    }
    return "";
  }

  public String getMetadataValueByEncondedName(String metadataName) {
    if (metadataElements != null) {
      for (DeliverableMetadataElement mData : metadataElements) {
        if (mData != null) {
          if (mData.getMetadataElement() != null) {
            if (mData.getMetadataElement().getEcondedName().equals(metadataName)) {
              return mData.getElementValue();
            }
          }
        }

      }
    }
    return "";
  }

  public List<DeliverableUserPartnership> getOtherPartnerships() {
    return otherPartnerships;
  }

  public String getOwner() {
    return owner;
  }

  public Phase getPhase() {
    return phase;
  }

  public List<DeliverableProgram> getPrograms() {
    return programs;
  }

  public Project getProject() {
    return project;
  }

  public Set<ProjectDeliverableShared> getProjectDeliverableShareds() {
    return projectDeliverableShareds;
  }

  public List<DeliverableProjectOutcome> getProjectOutcomes() {
    return projectOutcomes;
  }

  public DeliverablePublicationMetadata getPublication() {
    return publication;
  }

  public DeliverablePublicationMetadata getPublication(Phase phase) {
    List<DeliverablePublicationMetadata> deliverablePublications = this.getDeliverablePublicationMetadatas().stream()
      .filter(dp -> dp.isActive() && dp.getPhase().getId().equals(phase.getId())).collect(Collectors.toList());
    if (deliverablePublications != null && !deliverablePublications.isEmpty()) {
      return deliverablePublications.get(0);
    }
    return new DeliverablePublicationMetadata();
  }

  public List<DeliverablePublicationMetadata> getPublicationMetadatas() {
    return publicationMetadatas;
  }

  public DeliverableQualityCheck getQualityCheck() {
    return qualityCheck;
  }

  public List<DeliverableProgram> getRegions() {
    return regions;
  }

  public String getRegionsValue() {
    return regionsValue;
  }

  public String getResponsible() {
    return responsible;
  }


  public List<DeliverableUserPartnership> getResponsiblePartnership() {
    return responsiblePartnership;
  }

  public Set<SectionStatus> getSectionStatuses() {
    return sectionStatuses;
  }

  public List<LiaisonInstitution> getSelectedFlahsgips() {
    return selectedFlahsgips;
  }


  public List<ProjectDeliverableShared> getSharedDeliverables() {
    return sharedDeliverables;
  }


  public String getSharedWithMe() {
    return sharedWithMe;
  }


  public String getSharedWithProjects() {
    return sharedWithProjects;
  }


  public String getTagTitle() {
    return tagTitle;
  }


  public List<DeliverableUser> getUsers() {
    return users;
  }


  public List<DeliverableUser> getUsers(Phase phase) {
    return new ArrayList<>(this.getDeliverableUsers().stream()
      .filter(du -> du.isActive() && du.getPhase().equals(phase)).collect(Collectors.toList()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }


  public void setActivities(List<DeliverableActivity> activities) {
    this.activities = activities;
  }


  public void setAltmetricInfos(List<DeliverableAltmetricInfo> altmetricInfos) {
    this.altmetricInfos = altmetricInfos;
  }


  public void setClusterParticipant(List<DeliverableClusterParticipant> clusterParticipant) {
    this.clusterParticipant = clusterParticipant;
  }

  public void setCommentStatus(String commentStatus) {
    this.commentStatus = commentStatus;
  }

  public void setContribution(Boolean contribution) {
    this.contribution = contribution;
  }

  public void setCountries(List<DeliverableLocation> countries) {
    this.countries = countries;
  }


  public void setCountriesIds(List<String> countriesIds) {
    this.countriesIds = countriesIds;
  }


  public void setCountriesIdsText(String countriesIdsText) {
    this.countriesIdsText = countriesIdsText;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


  public void setCrossCuttingMarkers(List<DeliverableCrossCuttingMarker> crossCuttingMarkers) {
    this.crossCuttingMarkers = crossCuttingMarkers;
  }


  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }


  public void setCrpOutcomes(List<DeliverableCrpOutcome> crpOutcomes) {
    this.crpOutcomes = crpOutcomes;
  }

  public void setCrps(List<DeliverableCrp> crps) {
    this.crps = crps;
  }

  public void setDataSharing(List<DeliverableDataSharing> dataSharing) {
    this.dataSharing = dataSharing;
  }

  public void setDataSharingFiles(List<DeliverableDataSharingFile> dataSharingFiles) {
    this.dataSharingFiles = dataSharingFiles;
  }

  public void setDeliverableActivities(Set<DeliverableActivity> deliverableActivities) {
    this.deliverableActivities = deliverableActivities;
  }

  public void setDeliverableAltmetricInfo(DeliverableAltmetricInfo deliverableAltmetricInfo) {
    this.deliverableAltmetricInfo = deliverableAltmetricInfo;
  }

  public void setDeliverableAltmetricInfos(Set<DeliverableAltmetricInfo> deliverableAltmetricInfos) {
    this.deliverableAltmetricInfos = deliverableAltmetricInfos;
  }

  public void setDeliverableClusterParticipants(Set<DeliverableClusterParticipant> deliverableClusterParticipants) {
    this.deliverableClusterParticipants = deliverableClusterParticipants;
  }

  public void setDeliverableCrossCuttingMarkers(Set<DeliverableCrossCuttingMarker> deliverableCrossCuttingMarkers) {
    this.deliverableCrossCuttingMarkers = deliverableCrossCuttingMarkers;
  }

  public void setDeliverableCrpOutcomes(Set<DeliverableCrpOutcome> deliverableCrpOutcomes) {
    this.deliverableCrpOutcomes = deliverableCrpOutcomes;
  }


  public void setDeliverableCrps(Set<DeliverableCrp> deliverableCrps) {
    this.deliverableCrps = deliverableCrps;
  }

  public void setDeliverableDataSharingFiles(Set<DeliverableDataSharingFile> deliverableDataSharingFiles) {
    this.deliverableDataSharingFiles = deliverableDataSharingFiles;
  }

  public void setDeliverableDataSharings(Set<DeliverableDataSharing> deliverableDataSharings) {
    this.deliverableDataSharings = deliverableDataSharings;
  }

  public void setDeliverableDisseminations(Set<DeliverableDissemination> deliverableDisseminations) {
    this.deliverableDisseminations = deliverableDisseminations;
  }

  public void setDeliverableFundingSources(Set<DeliverableFundingSource> deliverableFundingSources) {
    this.deliverableFundingSources = deliverableFundingSources;
  }

  public void setDeliverableGenderLevels(Set<DeliverableGenderLevel> deliverableGenderLevels) {
    this.deliverableGenderLevels = deliverableGenderLevels;
  }

  public void setDeliverableGeographicRegions(Set<DeliverableGeographicRegion> deliverableGeographicRegions) {
    this.deliverableGeographicRegions = deliverableGeographicRegions;
  }

  public void setDeliverableGeographicScopes(Set<DeliverableGeographicScope> deliverableGeographicScopes) {
    this.deliverableGeographicScopes = deliverableGeographicScopes;
  }

  public void setDeliverableInfo(DeliverableInfo deliverableInfo) {
    this.deliverableInfo = deliverableInfo;
  }

  public void setDeliverableInfos(Set<DeliverableInfo> deliverableInfos) {
    this.deliverableInfos = deliverableInfos;
  }

  public void setDeliverableIntellectualAssets(Set<DeliverableIntellectualAsset> deliverableIntellectualAssets) {
    this.deliverableIntellectualAssets = deliverableIntellectualAssets;
  }

  public void setDeliverableLeaders(Set<DeliverableLeader> deliverableLeaders) {
    this.deliverableLeaders = deliverableLeaders;
  }


  public void setDeliverableLocations(Set<DeliverableLocation> deliverableLocations) {
    this.deliverableLocations = deliverableLocations;
  }

  public void setDeliverableLp6s(Set<ProjectLp6ContributionDeliverable> deliverableLp6s) {
    this.deliverableLp6s = deliverableLp6s;
  }

  public void setDeliverableMetadataElements(Set<DeliverableMetadataElement> deliverableMetadataElements) {
    this.deliverableMetadataElements = deliverableMetadataElements;
  }


  public void
    setDeliverableMetadataExternalSources(Set<DeliverableMetadataExternalSources> deliverableMetadataExternalSources) {
    this.deliverableMetadataExternalSources = deliverableMetadataExternalSources;
  }

  public void setDeliverableParticipant(DeliverableParticipant deliverableParticipant) {
    this.deliverableParticipant = deliverableParticipant;
  }

  public void setDeliverableParticipants(Set<DeliverableParticipant> deliverableParticipants) {
    this.deliverableParticipants = deliverableParticipants;
  }

  public void setDeliverablePrograms(Set<DeliverableProgram> deliverablePrograms) {
    this.deliverablePrograms = deliverablePrograms;
  }

  public void setDeliverableProjectOutcomes(Set<DeliverableProjectOutcome> deliverableProjectOutcomes) {
    this.deliverableProjectOutcomes = deliverableProjectOutcomes;
  }

  public void setDeliverablePublicationMetadatas(Set<DeliverablePublicationMetadata> deliverablePublicationMetadatas) {
    this.deliverablePublicationMetadatas = deliverablePublicationMetadatas;
  }

  public void setDeliverableQualityChecks(Set<DeliverableQualityCheck> deliverableQualityChecks) {
    this.deliverableQualityChecks = deliverableQualityChecks;
  }

  public void setDeliverableRegions(List<DeliverableGeographicRegion> deliverableRegions) {
    this.deliverableRegions = deliverableRegions;
  }

  public void setDeliverableUserPartnerships(Set<DeliverableUserPartnership> deliverableUserPartnerships) {
    this.deliverableUserPartnerships = deliverableUserPartnerships;
  }

  public void setDeliverableUsers(Set<DeliverableUser> deliverableUsers) {
    this.deliverableUsers = deliverableUsers;
  }

  public void setDissemination(DeliverableDissemination dissemination) {
    this.dissemination = dissemination;
  }

  public void setDisseminations(List<DeliverableDissemination> disseminations) {
    this.disseminations = disseminations;
  }

  public void setExternalSource(DeliverableMetadataExternalSources externalSource) {
    this.externalSource = externalSource;
  }

  public void setFiles(List<DeliverableFile> files) {
    this.files = files;
  }

  public void setFlagshipValue(String flagshipValue) {
    this.flagshipValue = flagshipValue;
  }

  public void setFundingSources(List<DeliverableFundingSource> fundingSources) {
    this.fundingSources = fundingSources;
  }

  public void setGenderLevels(List<DeliverableGenderLevel> genderLevels) {
    this.genderLevels = genderLevels;
  }

  public void setGeographicScopes(List<DeliverableGeographicScope> geographicScopes) {
    this.geographicScopes = geographicScopes;
  }

  public void setIsAccesible(Boolean isAccesible) {
    this.isAccesible = isAccesible;
  }


  public void setIsFindable(Boolean isFindable) {
    this.isFindable = isFindable;
  }


  public void setIsInteroperable(Boolean isInteroperable) {
    this.isInteroperable = isInteroperable;
  }


  public void setIsPublication(Boolean isPublication) {
    this.isPublication = isPublication;
  }


  public void setIsReusable(Boolean isReusable) {
    this.isReusable = isReusable;
  }


  public void setLeaders(List<DeliverableLeader> leaders) {
    this.leaders = leaders;
  }


  public void setMetadata(List<MetadataElement> metadata) {
    this.metadata = metadata;
  }


  public void setMetadataElements(List<DeliverableMetadataElement> metadataElements) {
    this.metadataElements = metadataElements;
  }


  public void setMetadataExternalSources(List<DeliverableMetadataExternalSources> metadataExternalSources) {
    this.metadataExternalSources = metadataExternalSources;
  }


  public void setOtherPartnerships(List<DeliverableUserPartnership> otherPartnerships) {
    this.otherPartnerships = otherPartnerships;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }


  public void setPhase(Phase phase) {
    this.phase = phase;
  }


  public void setPrograms(List<DeliverableProgram> programs) {
    this.programs = programs;
  }

  public void setProject(Project project) {
    this.project = project;
  }


  public void setProjectDeliverableShareds(Set<ProjectDeliverableShared> projectDeliverableShareds) {
    this.projectDeliverableShareds = projectDeliverableShareds;
  }


  public void setProjectOutcomes(List<DeliverableProjectOutcome> projectOutcomes) {
    this.projectOutcomes = projectOutcomes;
  }


  public void setPublication(DeliverablePublicationMetadata publication) {
    this.publication = publication;
  }


  public void setPublicationMetadatas(List<DeliverablePublicationMetadata> publicationMetadatas) {
    this.publicationMetadatas = publicationMetadatas;
  }

  public void setQualityCheck(DeliverableQualityCheck qualityCheck) {
    this.qualityCheck = qualityCheck;
  }

  public void setRegions(List<DeliverableProgram> regions) {
    this.regions = regions;
  }

  public void setRegionsValue(String regionsValue) {
    this.regionsValue = regionsValue;
  }

  public void setResponsible(String responsible) {
    this.responsible = responsible;
  }

  public void setResponsiblePartnership(List<DeliverableUserPartnership> responsiblePartnership) {
    this.responsiblePartnership = responsiblePartnership;
  }

  public void setSectionStatuses(Set<SectionStatus> sectionStatuses) {
    this.sectionStatuses = sectionStatuses;
  }

  public void setSelectedFlahsgips(List<LiaisonInstitution> selectedFlahsgips) {
    this.selectedFlahsgips = selectedFlahsgips;
  }

  public void setSharedDeliverables(List<ProjectDeliverableShared> sharedDeliverables) {
    this.sharedDeliverables = sharedDeliverables;
  }

  public void setSharedWithMe(String sharedWithMe) {
    this.sharedWithMe = sharedWithMe;
  }

  public void setSharedWithProjects(String sharedWithProjects) {
    this.sharedWithProjects = sharedWithProjects;
  }

  public void setTagTitle(String tagTitle) {
    this.tagTitle = tagTitle;
  }

  public void setUsers(List<DeliverableUser> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Deliverable [id=" + this.getId() + ", project=" + project + ", active=" + this.isActive() + ", phase="
      + phase + ", isPublication=" + isPublication + ", deliverableInfo=" + deliverableInfo + "]";
  }

}
