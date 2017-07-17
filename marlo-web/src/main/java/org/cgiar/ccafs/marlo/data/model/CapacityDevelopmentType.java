package org.cgiar.ccafs.marlo.data.model;
// default package
// Generated May 31, 2017 11:43:12 AM by Hibernate Tools 3.4.0.CR1


import java.util.HashSet;
import java.util.Set;

/**
 * CapacityDevelopmentTypes generated by hbm2java
 */
public class CapacityDevelopmentType implements java.io.Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;
  private String category;
  private Set capacityDevelopments = new HashSet(0);

  public CapacityDevelopmentType() {
  }


  public CapacityDevelopmentType(String name) {
    this.name = name;
  }

  public CapacityDevelopmentType(String name, Set capacityDevelopments, String category) {
    this.name = name;
    this.capacityDevelopments = capacityDevelopments;
    this.category = category;
  }

  public Set getCapacityDevelopments() {
    return this.capacityDevelopments;
  }

  public String getCategory() {
    return category;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setCapacityDevelopments(Set capacityDevelopments) {
    this.capacityDevelopments = capacityDevelopments;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public void setName(String name) {
    this.name = name;
  }


}

