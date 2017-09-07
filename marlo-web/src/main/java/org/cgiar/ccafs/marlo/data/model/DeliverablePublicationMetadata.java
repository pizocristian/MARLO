package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 26, 2017 10:34:59 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * DeliverablePublicationsMetada generated by hbm2java
 */
public class DeliverablePublicationMetadata implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 1781447896817590575L;


  @Expose
  private Long id;

  @Expose
  private Deliverable deliverable;
  @Expose
  private String volume;
  @Expose
  private String issue;
  @Expose
  private String pages;
  @Expose
  private String journal;
  @Expose
  private Boolean isiPublication;
  @Expose
  private Boolean nasr;
  @Expose
  private Boolean coAuthor;
  @Expose
  private Boolean publicationAcknowledge;
  @Expose
  private Phase phase;

  public DeliverablePublicationMetadata() {
  }

  public DeliverablePublicationMetadata(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public DeliverablePublicationMetadata(Deliverable deliverable, String volume, String issue, String pages,
    String journal, Boolean isiPublication, Boolean nasr, Boolean coAuthor) {
    this.deliverable = deliverable;
    this.volume = volume;
    this.issue = issue;
    this.pages = pages;
    this.journal = journal;
    this.isiPublication = isiPublication;
    this.nasr = nasr;
    this.coAuthor = coAuthor;
  }

  public Boolean getCoAuthor() {
    return this.coAuthor;
  }

  public Deliverable getDeliverable() {
    return this.deliverable;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  public Boolean getIsiPublication() {
    return this.isiPublication;
  }

  public String getIssue() {
    return this.issue;
  }

  public String getJournal() {
    return this.journal;
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
    return u;
  }

  public Boolean getNasr() {
    return this.nasr;
  }

  public String getPages() {
    return this.pages;
  }

  public Phase getPhase() {
    return phase;
  }

  public Boolean getPublicationAcknowledge() {
    return publicationAcknowledge;
  }

  public String getVolume() {
    return this.volume;
  }

  @Override
  public boolean isActive() {

    return true;
  }

  public void setCoAuthor(Boolean coAuthor) {
    this.coAuthor = coAuthor;
  }

  public void setDeliverable(Deliverable deliverable) {
    this.deliverable = deliverable;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIsiPublication(Boolean isiPublication) {
    this.isiPublication = isiPublication;
  }

  public void setIssue(String issue) {
    this.issue = issue;
  }

  public void setJournal(String journal) {
    this.journal = journal;
  }

  public void setNasr(Boolean nasr) {
    this.nasr = nasr;
  }

  public void setPages(String pages) {
    this.pages = pages;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setPublicationAcknowledge(Boolean publicationAcknowledge) {
    this.publicationAcknowledge = publicationAcknowledge;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }


}

