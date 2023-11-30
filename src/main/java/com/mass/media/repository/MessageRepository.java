package com.mass.media.repository;

import com.mass.media.entity.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

  @Query("select message from MessageEntity message " +
      "where message.media.name = :mediaName " +
      "order by message.createDate desc")
  List<MessageEntity> findMessagesByMediaName(@Param("mediaName") String mediaName);

  @Query("select message from MessageEntity message " +
      "where message.author.firstName = :firstName " +
      "order by message.createDate desc")
  List<MessageEntity> findMessagesByAuthorFirstName(@Param("firstName") String firstName);

  @Query("select message from MessageEntity message " +
      "where message.author.lastName = :lastName " +
      "order by message.createDate desc")
  List<MessageEntity> findMessagesByAuthorLastName(@Param("lastName") String lastName);

  @Query("select message from MessageEntity message " +
      "where message.attachment.attachmentType = :attachmentType " +
      "order by message.createDate desc")
  List<MessageEntity> findMessagesByAttachmentType(@Param("attachmentType") String attachmentType);

  @Query("select message from MessageEntity message " +
      "where message.message like %:message% " +
      "order by message.createDate desc")
  List<MessageEntity> findByMessageLike(String message);
}
