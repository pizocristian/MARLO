package org.cgiar.ccafs.marlo.data.model;
// Generated May 31, 2018 4:07:34 PM by Hibernate Tools 3.4.0.CR1


import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.Expose;

/**
 * ReportSynthesisCrossCuttingDimension generated by hbm2java
 */
public class ReportSynthesisCrossCuttingDimension extends MarloAuditableEntity
  implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = 187004710426450329L;

  private ReportSynthesis reportSynthesis;

  @Expose
  private String genderDescription;

  @Expose
  private String genderLessons;

  @Expose
  private String youthDescription;

  @Expose
  private String youthLessons;

  @Expose
  private String otherAspects;

  @Expose
  private String capDev;

  @Expose
  private String openData;

  @Expose
  private String intellectualAssets;

  @Expose
  private Double phdFemale;

  @Expose
  private Double phdMale;


  private Set<ReportSynthesisCrossCuttingDimensionAsset> reportSynthesisCrossCuttingDimensionAssets =
    new HashSet<ReportSynthesisCrossCuttingDimensionAsset>(0);

  private Set<ReportSynthesisCrossCuttingDimensionInnovation> reportSynthesisCrossCuttingDimensionInnovations =
    new HashSet<ReportSynthesisCrossCuttingDimensionInnovation>(0);


  private List<ReportSynthesisCrossCuttingDimensionInnovation> plannedInnovations;

  private String innovationsValue;

  private List<ProjectInnovation> innovations;

  private List<ReportSynthesisCrossCuttingDimensionAsset> plannedAssets;

  private String assetsValue;

  private List<DeliverableIntellectualAsset> assets;

  @Expose
  private String genderResearchFindings;

  @Expose
  private String genderLearned;

  @Expose
  private String genderProblemsArisen;

  @Expose
  private String youthContribution;

  @Expose
  private String youthResearchFindings;

  @Expose
  private String youthLearned;

  @Expose
  private String youthProblemsArisen;

  @Expose
  private String capDevKeyAchievements;

  @Expose
  private String climateChangeKeyAchievements;

  @Expose
  private Double traineesShortTermFemale;

  @Expose
  private Double traineesShortTermMale;


  @Expose
  private Double traineesLongTermFemale;


  @Expose
  private Double traineesLongTermMale;


  @Expose
  private Double traineesPhdFemale;


  @Expose
  private Double traineesPhdMale;


  public ReportSynthesisCrossCuttingDimension() {
  }


  /**
   * @return an array of integers (intellectual assets Id).
   */
  public long[] getAssetIds() {

    List<DeliverableIntellectualAsset> intellectualAssets = this.getAssets();
    if (intellectualAssets != null) {
      long[] ids = new long[intellectualAssets.size()];
      for (int i = 0; i < ids.length; i++) {
        ids[i] = intellectualAssets.get(i).getId();
      }
      return ids;
    }
    return null;
  }


  public List<DeliverableIntellectualAsset> getAssets() {
    return assets;
  }


  public String getAssetsValue() {
    return assetsValue;
  }


  public String getCapDev() {
    return capDev;
  }


  public String getCapDevKeyAchievements() {
    return capDevKeyAchievements;
  }


  public String getClimateChangeKeyAchievements() {
    return climateChangeKeyAchievements;
  }

  public String getGenderDescription() {
    return genderDescription;
  }


  public String getGenderLearned() {
    return genderLearned;
  }


  public String getGenderLessons() {
    return genderLessons;
  }


  public String getGenderProblemsArisen() {
    return genderProblemsArisen;
  }

  public String getGenderResearchFindings() {
    return genderResearchFindings;
  }

  /**
   * @return an array of integers (project Innovation Id).
   */
  public long[] getInnovationIds() {

    List<ProjectInnovation> projectInnovations = this.getInnovations();
    if (projectInnovations != null) {
      long[] ids = new long[projectInnovations.size()];
      for (int i = 0; i < ids.length; i++) {
        ids[i] = projectInnovations.get(i).getId();
      }
      return ids;
    }
    return null;
  }


  public List<ProjectInnovation> getInnovations() {
    return innovations;
  }


  public String getInnovationsValue() {
    return innovationsValue;
  }


  public String getIntellectualAssets() {
    return intellectualAssets;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  public String getOpenData() {
    return openData;
  }


  public String getOtherAspects() {
    return otherAspects;
  }


  public Double getPhdFemale() {
    return phdFemale;
  }


  public Double getPhdMale() {
    return phdMale;
  }


  public List<ReportSynthesisCrossCuttingDimensionAsset> getPlannedAssets() {
    return plannedAssets;
  }


  public List<ReportSynthesisCrossCuttingDimensionInnovation> getPlannedInnovations() {
    return plannedInnovations;
  }

  public ReportSynthesis getReportSynthesis() {
    return reportSynthesis;
  }

  public Set<ReportSynthesisCrossCuttingDimensionAsset> getReportSynthesisCrossCuttingDimensionAssets() {
    return reportSynthesisCrossCuttingDimensionAssets;
  }

  public Set<ReportSynthesisCrossCuttingDimensionInnovation> getReportSynthesisCrossCuttingDimensionInnovations() {
    return reportSynthesisCrossCuttingDimensionInnovations;
  }

  public Double getTraineesLongTermFemale() {
    return traineesLongTermFemale;
  }

  public Double getTraineesLongTermMale() {
    return traineesLongTermMale;
  }

  public Double getTraineesPhdFemale() {
    return traineesPhdFemale;
  }

  public Double getTraineesPhdMale() {
    return traineesPhdMale;
  }

  public Double getTraineesShortTermFemale() {
    return traineesShortTermFemale;
  }

  public Double getTraineesShortTermMale() {
    return traineesShortTermMale;
  }

  public String getYouthContribution() {
    return youthContribution;
  }

  public String getYouthDescription() {
    return youthDescription;
  }

  public String getYouthLearned() {
    return youthLearned;
  }

  public String getYouthLessons() {
    return youthLessons;
  }

  public String getYouthProblemsArisen() {
    return youthProblemsArisen;
  }

  public String getYouthResearchFindings() {
    return youthResearchFindings;
  }

  public void setAssets(List<DeliverableIntellectualAsset> assets) {
    this.assets = assets;
  }


  public void setAssetsValue(String assetsValue) {
    this.assetsValue = assetsValue;
  }

  public void setCapDev(String capDev) {
    this.capDev = capDev;
  }

  public void setCapDevKeyAchievements(String capDevKeyAchievements) {
    this.capDevKeyAchievements = capDevKeyAchievements;
  }

  public void setClimateChangeKeyAchievements(String climateChangeKeyAchievements) {
    this.climateChangeKeyAchievements = climateChangeKeyAchievements;
  }

  public void setGenderDescription(String genderDescription) {
    this.genderDescription = genderDescription;
  }

  public void setGenderLearned(String genderLearned) {
    this.genderLearned = genderLearned;
  }


  public void setGenderLessons(String genderLessons) {
    this.genderLessons = genderLessons;
  }


  public void setGenderProblemsArisen(String genderProblemsArisen) {
    this.genderProblemsArisen = genderProblemsArisen;
  }


  public void setGenderResearchFindings(String genderResearchFindings) {
    this.genderResearchFindings = genderResearchFindings;
  }


  public void setInnovations(List<ProjectInnovation> innovations) {
    this.innovations = innovations;
  }


  public void setInnovationsValue(String innovationsValue) {
    this.innovationsValue = innovationsValue;
  }


  public void setIntellectualAssets(String intellectualAssets) {
    this.intellectualAssets = intellectualAssets;
  }


  public void setOpenData(String openData) {
    this.openData = openData;
  }


  public void setOtherAspects(String otherAspects) {
    this.otherAspects = otherAspects;
  }

  public void setPhdFemale(Double phdFemale) {
    this.phdFemale = phdFemale;
  }


  public void setPhdMale(Double phdMale) {
    this.phdMale = phdMale;
  }


  public void setPlannedAssets(List<ReportSynthesisCrossCuttingDimensionAsset> plannedAssets) {
    this.plannedAssets = plannedAssets;
  }

  public void setPlannedInnovations(List<ReportSynthesisCrossCuttingDimensionInnovation> plannedInnovations) {
    this.plannedInnovations = plannedInnovations;
  }

  public void setReportSynthesis(ReportSynthesis reportSynthesis) {
    this.reportSynthesis = reportSynthesis;
  }

  public void setReportSynthesisCrossCuttingDimensionAssets(
    Set<ReportSynthesisCrossCuttingDimensionAsset> reportSynthesisCrossCuttingDimensionAssets) {
    this.reportSynthesisCrossCuttingDimensionAssets = reportSynthesisCrossCuttingDimensionAssets;
  }

  public void setReportSynthesisCrossCuttingDimensionInnovations(
    Set<ReportSynthesisCrossCuttingDimensionInnovation> reportSynthesisCrossCuttingDimensionInnovations) {
    this.reportSynthesisCrossCuttingDimensionInnovations = reportSynthesisCrossCuttingDimensionInnovations;
  }

  public void setTraineesLongTermFemale(Double traineesLongTermFemale) {
    this.traineesLongTermFemale = traineesLongTermFemale;
  }

  public void setTraineesLongTermMale(Double traineesLongTermMale) {
    this.traineesLongTermMale = traineesLongTermMale;
  }

  public void setTraineesPhdFemale(Double traineesPhdFemale) {
    this.traineesPhdFemale = traineesPhdFemale;
  }

  public void setTraineesPhdMale(Double traineesPhdMale) {
    this.traineesPhdMale = traineesPhdMale;
  }

  public void setTraineesShortTermFemale(Double traineesShortTermFemale) {
    this.traineesShortTermFemale = traineesShortTermFemale;
  }

  public void setTraineesShortTermMale(Double traineesShortTermMale) {
    this.traineesShortTermMale = traineesShortTermMale;
  }

  public void setYouthContribution(String youthContribution) {
    this.youthContribution = youthContribution;
  }

  public void setYouthDescription(String youthDescription) {
    this.youthDescription = youthDescription;
  }

  public void setYouthLearned(String youthLearned) {
    this.youthLearned = youthLearned;
  }

  public void setYouthLessons(String youthLessons) {
    this.youthLessons = youthLessons;
  }

  public void setYouthProblemsArisen(String youthProblemsArisen) {
    this.youthProblemsArisen = youthProblemsArisen;
  }


  public void setYouthResearchFindings(String youthResearchFindings) {
    this.youthResearchFindings = youthResearchFindings;
  }


}
