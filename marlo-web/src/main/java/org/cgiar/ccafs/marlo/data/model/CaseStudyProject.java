package org.cgiar.ccafs.marlo.data.model;
// Generated Dec 12, 2016 3:55:58 PM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CaseStudyProjects generated by hbm2java
 */
public class CaseStudyProject implements java.io.Serializable, IAuditLog {

  /**
   * 
   */
  private static final long serialVersionUID = -1004953642319195188L;
  @Expose
  private Long id;
  private CaseStudy caseStudy;
  @Expose
  private Project project;

  public CaseStudyProject() {
  }

  public CaseStudyProject(CaseStudy caseStudy, Project project) {
    this.caseStudy = caseStudy;
    this.project = project;
  }


  public CaseStudy getCaseStudy() {
    return caseStudy;
  }


  @Override
  public Long getId() {
    return id;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();
  }


  @Override
  public String getModificationJustification() {

    return "";
  }


  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return null;
  }


  public Project getProject() {
    return project;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public void setCaseStudy(CaseStudy caseStudy) {
    this.caseStudy = caseStudy;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setProject(Project project) {
    this.project = project;
  }


}

