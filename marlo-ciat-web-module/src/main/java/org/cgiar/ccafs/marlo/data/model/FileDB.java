package org.cgiar.ccafs.marlo.data.model;
// Generated Aug 9, 2016 10:35:04 AM by Hibernate Tools 4.3.1.Final

import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

/**
 * FileDB generated by hbm2java
 */
public class FileDB implements java.io.Serializable, IAuditLog {


  /**
   * 
   */
  private static final long serialVersionUID = 3977258629212295440L;
  @Expose
  private Long id;
  @Expose
  private String fileName;
  @Expose
  private String tokenId;

  public FileDB() {
  }


  public FileDB(String fileName, String tokenId) {
    this.fileName = fileName;
    this.tokenId = tokenId;
  }


  public String getFileName() {
    return this.fileName;
  }

  @Override
  public Long getId() {
    return this.id;
  }


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();

    sb.append("Id : ").append(this.getId());


    return sb.toString();

  }

  @Override
  public User getModifiedBy() {
    User u = new User();
    u.setId(new Long(3));
    return u;
  }

  public String getTokenId() {
    return this.tokenId;
  }


  @Override
  public boolean isActive() {

    return true;
  }


  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }


}

