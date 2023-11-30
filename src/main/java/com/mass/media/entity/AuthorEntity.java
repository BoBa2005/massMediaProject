package com.mass.media.entity;

import javax.persistence.*;

@Entity
@Table(name = "author")
public class AuthorEntity {

  private Long authorId;
  private String firstName;
  private String lastName;

  @Id
  @Column(name = "author_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  @Basic
  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Basic
  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
