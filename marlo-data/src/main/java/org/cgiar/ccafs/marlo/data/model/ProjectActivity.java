package org.cgiar.ccafs.marlo.data.model;
// Generated Jan 8, 2019 3:50:56 PM by Hibernate Tools 5.3.6.Final


import org.cgiar.ccafs.marlo.data.IAuditLog;

import com.google.gson.annotations.Expose;

public class ProjectActivity extends MarloAuditableEntity implements java.io.Serializable, IAuditLog {

  private static final long serialVersionUID = -4753410776769726974L;

  @Expose
  private Project project;


  @Override
  public String getLogDeatil() {
    StringBuilder sb = new StringBuilder();
    sb.append("Id : ").append(this.getId());
    return sb.toString();
  }


  @Override
  public String toString() {
    return "ProjectActivity [id=" + this.getId() + ", isActive=" + this.isActive() + "]";
  }

}

