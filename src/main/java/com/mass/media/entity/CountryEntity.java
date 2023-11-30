package com.mass.media.entity;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class CountryEntity {

  private Long countryId;
  private String name;

  @Id
  @Column(name = "country_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
