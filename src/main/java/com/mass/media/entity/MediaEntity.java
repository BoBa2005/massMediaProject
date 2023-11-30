package com.mass.media.entity;

import javax.persistence.*;

@Entity
@Table(name = "media")
public class MediaEntity {

  private Long mediaId;
  private Long countryId;
  private String name;
  private String url;
  private CountryEntity country;

  @Id
  @Column(name = "media_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getMediaId() {
    return mediaId;
  }

  public void setMediaId(Long mediaId) {
    this.mediaId = mediaId;
  }

  @Basic
  @Column(name = "country_id")
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

  @Basic
  @Column(name = "url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "country_id", referencedColumnName = "country_id", insertable = false, updatable = false)
  public CountryEntity getCountry() {
    return country;
  }

  public void setCountry(CountryEntity country) {
    this.country = country;
  }
}
