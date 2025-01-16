package com.naitik.journalapp.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntityRequest {
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

    public JournalEntityRequest(JournalEntity journalEntryEntity){
        this.title=journalEntryEntity.getTitle();
        this.content=journalEntryEntity.getContent();
        this.writer=journalEntryEntity.getWriter();
        this.date=journalEntryEntity.getDate();
        this.id=journalEntryEntity.getId();
    }
}


