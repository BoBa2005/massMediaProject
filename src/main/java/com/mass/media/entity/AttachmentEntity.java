package com.mass.media.entity;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class AttachmentEntity {

  private Long attachmentId;
  private String attachmentType;
  private String url;

  @Id
  @Column(name = "attachment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Long attachmentId) {
    this.attachmentId = attachmentId;
  }

  @Basic
  @Column(name = "attachment_type")
  public String getAttachmentType() {
    return attachmentType;
  }

  public void setAttachmentType(String attachmentType) {
    this.attachmentType = attachmentType;
  }

  @Basic
  @Column(name = "url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
