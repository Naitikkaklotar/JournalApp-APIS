package com.naitik.journalapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class JournalEntity {

    @Id
    private ObjectId id;
    private  String title;
    private String content;
    private String writer;

    private LocalDateTime date;

      public JournalEntity(JournalEntityRequest journalEntityRequest){
          this.id=journalEntityRequest.getId();
          this.title=journalEntityRequest.getTitle();
          this.content=journalEntityRequest.getContent();
          this.writer=journalEntityRequest.getWriter();
          this.date=journalEntityRequest.getDate();
  }



}
