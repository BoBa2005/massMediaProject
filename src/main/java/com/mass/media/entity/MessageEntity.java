package com.mass.media.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
public class MessageEntity {

  private Long messageId;
  private Long mediaId;
  private Long authorId;
  private Long attachmentId;
  private String title;
  private String message;
  private Date createDate;
  private MediaEntity media;
  private AuthorEntity author;
  private AttachmentEntity attachment;

  @Id
  @Column(name = "message_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  @Basic
  @Column(name = "media_id")
  public Long getMediaId() {
    return mediaId;
  }

  public void setMediaId(Long mediaId) {
    this.mediaId = mediaId;
  }

  @Basic
  @Column(name = "author_id")
  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  @Basic
  @Column(name = "attachment_id")
  public Long getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Long attachmentId) {
    this.attachmentId = attachmentId;
  }

  @Basic
  @Column(name = "title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Basic
  @Column(name = "message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Basic
  @Column(name = "create_date")
  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "media_id", referencedColumnName = "media_id", insertable = false, updatable = false)
  public MediaEntity getMedia() {
    return media;
  }

  public void setMedia(MediaEntity media) {
    this.media = media;
  }

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false)
  public AuthorEntity getAuthor() {
    return author;
  }

  public void setAuthor(AuthorEntity author) {
    this.author = author;
  }

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "attachment_id", referencedColumnName = "attachment_id", insertable = false, updatable = false)
  public AttachmentEntity getAttachment() {
    return attachment;
  }

  public void setAttachment(AttachmentEntity attachment) {
    this.attachment = attachment;
  }
}
