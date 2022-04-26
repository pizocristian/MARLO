package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 30, 2018 10:52:36 AM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.Expose;

/**
 * ProjectExpectedStudyInfo generated by hbm2java
 */
public class FeedbackQAComment extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -7806050142645120199L;

  @Expose
  private Phase phase;
  @Expose
  private InternalQaCommentableFields field;
  @Expose
  private long screen;
  @Expose
  private long object;
  @Expose
  private String comment;
  @Expose
  private String status;
  @Expose
  private FeedbackComment reply;

  public FeedbackQAComment() {
  }

  public FeedbackQAComment(Phase phase, InternalQaCommentableFields field, long screen, long object, String comment,
    String status, FeedbackComment reply) {
    super();
    this.phase = phase;
    this.field = field;
    this.screen = screen;
    this.object = object;
    this.comment = comment;
    this.status = status;
    this.reply = reply;
  }

  public Map<String, Object> convertToMap() {
    ObjectMapper oMapper = new ObjectMapper();
    Map<String, Object> map = oMapper.convertValue(this, Map.class);

    return map;
  }

  public String getComment() {
    return comment;
  }

  public InternalQaCommentableFields getField() {
    return field;
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

  public long getObject() {
    return object;
  }

  public Phase getPhase() {
    return phase;
  }

  public FeedbackComment getReply() {
    return reply;
  }

  public long getScreen() {
    return screen;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public boolean isActive() {
    return true;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setField(InternalQaCommentableFields field) {
    this.field = field;
  }

  @Override
  public void setModifiedBy(User modifiedBy) {
    // TODO Auto-generated method stub
  }

  public void setObject(long object) {
    this.object = object;
  }

  public void setPhase(Phase phase) {
    this.phase = phase;
  }

  public void setReply(FeedbackComment reply) {
    this.reply = reply;
  }

  public void setScreen(long screen) {
    this.screen = screen;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}

