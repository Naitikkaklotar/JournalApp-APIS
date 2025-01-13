package com.naitik.journalapp.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class JournalEntryEntity {

    @Id
    private ObjectId id;
    @NotNull
    @Size(min = 2, message = "Error: Title should have atleast 2 character")
    private  String title;

    @NotNull
    @Size(min = 2, message = "Error: content should have atleast 2 character")
    private String content;

    @NotNull
    @Size(min = 3, message = "Error: Writer should have atleast 3 character")
    private String writer;

    private LocalDateTime date;

      public JournalEntryEntity(JournalEntityRequest journalEntityRequest){
          this.id=journalEntityRequest.getId();
          this.title=journalEntityRequest.getTitle();
          this.content=journalEntityRequest.getContent();
          this.writer=journalEntityRequest.getTitle();
          this.date=journalEntityRequest.getDate();
  }



}
