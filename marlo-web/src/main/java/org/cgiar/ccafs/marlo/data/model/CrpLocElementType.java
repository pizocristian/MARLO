package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 26, 2017 10:31:52 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * CrpLocElementTypes generated by hbm2java
 */
public class CrpLocElementType implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = -5400709802535017070L;


  @Expose
  private Long id;


  @Expose
  private GlobalUnit crp;


  @Expose
  private LocElementType locElementType;


  public CrpLocElementType() {
  }

  public CrpLocElementType(GlobalUnit crp, LocElementType locElementType) {
    this.crp = crp;
    this.locElementType = locElementType;
  }

  public GlobalUnit getCrp() {
    return crp;
  }

  @Override
  public Long getId() {
    return id;
  }


  public LocElementType getLocElementType() {
    return locElementType;
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

  @Override
  public boolean isActive() {

    return true;
  }

  public void setCrp(GlobalUnit crp) {
    this.crp = crp;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setLocElementType(LocElementType locElementType) {
    this.locElementType = locElementType;
  }


}

