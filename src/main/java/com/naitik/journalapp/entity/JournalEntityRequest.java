package com.naitik.journalapp.entity;

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
    private  String title;
    private String content;
    private String writer;
    private LocalDateTime date;

    public JournalEntityRequest(JournalEntryEntity journalEntryEntity){
        this.title=journalEntryEntity.getTitle();
        this.content=journalEntryEntity.getContent();
        this.writer=journalEntryEntity.getWriter();
        this.date=journalEntryEntity.getDate();
        this.id=journalEntryEntity.getId();
    }
}


