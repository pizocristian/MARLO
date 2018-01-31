package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 23, 2018 10:50:08 AM by Hibernate Tools 4.3.1.Final


import java.util.Date;

/**
 * EmailLog generated by hbm2java
 */
public class EmailLog implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 5854981906967240444L;
  private Long id;
  private String to;
  private String cc;
  private String bbc;
  private String subject;
  private String message;
  private Integer tried;
  private Date date;
  private String error;
  private Boolean succes;
  private String fileName;

  private byte[] fileContent;


  public EmailLog() {
  }


  public EmailLog(String to, String cc, String bbc, String subject, String message, Integer tried, Date date,
    String error, Boolean succes) {
    this.to = to;
    this.cc = cc;
    this.bbc = bbc;
    this.subject = subject;
    this.message = message;
    this.tried = tried;
    this.date = date;
    this.error = error;
    this.succes = succes;
  }


  public String getBbc() {
    return this.bbc;
  }

  public String getCc() {
    return this.cc;
  }


  public Date getDate() {
    return this.date;
  }

  public String getError() {
    return this.error;
  }

  public byte[] getFileContent() {
    return fileContent;
  }

  public String getFileName() {
    return fileName;
  }

  public Long getId() {
    return this.id;
  }

  public String getMessage() {
    return this.message;
  }

  public String getSubject() {
    return this.subject;
  }

  public Boolean getSucces() {
    return this.succes;
  }

  public String getTo() {
    return this.to;
  }

  public Integer getTried() {
    return this.tried;
  }

  public void setBbc(String bbc) {
    this.bbc = bbc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setError(String error) {
    this.error = error;
  }

  public void setFileContent(byte[] fileContent) {
    this.fileContent = fileContent;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setSucces(Boolean succes) {
    this.succes = succes;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setTried(Integer tried) {
    this.tried = tried;
  }


}

