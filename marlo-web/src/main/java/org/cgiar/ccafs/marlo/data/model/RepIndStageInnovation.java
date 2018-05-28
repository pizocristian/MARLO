package org.cgiar.ccafs.marlo.data.model;
// Generated Apr 18, 2018 1:21:50 PM by Hibernate Tools 3.4.0.CR1

import com.google.gson.annotations.Expose;

/**
 * RepIndStageInnovation generated by hbm2java
 */
public class RepIndStageInnovation implements java.io.Serializable {


  private static final long serialVersionUID = 8631221361540916292L;
  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String definition;

  public RepIndStageInnovation() {
  }

  public RepIndStageInnovation(String name, String definition) {
    this.name = name;
    this.definition = definition;
  }

  public String getDefinition() {
    return this.definition;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }


}

