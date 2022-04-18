package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 30, 2018 10:52:36 AM by Hibernate Tools 3.4.0.CR1

import org.cgiar.ccafs.marlo.data.IAuditLog;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.annotations.Expose;

/**
 * ProjectExpectedStudyInfo generated by hbm2java
 */
public class InternalQaCommentableFields extends MarloBaseEntity implements java.io.Serializable, IAuditLog {


  private static final long serialVersionUID = -7806050142645120199L;

  @Expose
  private String sectionName;
  @Expose
  private String parentName;
  @Expose
  private String parentId;
  @Expose
  private String tableName;
  @Expose
  private String fieldName;
  @Expose
  private String frontName;
  @Expose
  private boolean active;

  public InternalQaCommentableFields() {
  }

  public InternalQaCommentableFields(String sectionName, String parentName, String parentId, String tableName,
    String fieldName, String frontName) {
    super();
    this.sectionName = sectionName;
    this.parentName = parentName;
    this.parentId = parentId;
    this.tableName = tableName;
    this.fieldName = fieldName;
    this.frontName = frontName;
  }


  public Map<String, Object> convertToMap() {
    ObjectMapper oMapper = new ObjectMapper();

    Map<String, Object> map = oMapper.convertValue(this, Map.class);

    return map;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getFrontName() {
    return frontName;
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

  public String getParentId() {
    return parentId;
  }

  public String getParentName() {
    return parentName;
  }

  public String getSectionName() {
    return sectionName;
  }

  public String getTableName() {
    return tableName;
  }

  @Override
  public boolean isActive() {
    return true;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public void setFrontName(String frontName) {
    this.frontName = frontName;
  }

  @Override
  public void setModifiedBy(User modifiedBy) {
    // TODO Auto-generated method stub
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

}

