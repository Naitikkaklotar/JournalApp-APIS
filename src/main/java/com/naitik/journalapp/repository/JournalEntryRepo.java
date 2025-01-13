package com.naitik.journalapp.repository;

import com.naitik.journalapp.entity.JournalEntryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntryEntity, ObjectId>{
    public List<JournalEntryEntity> findByTitle(String title);

    List<JournalEntryEntity> findByTitleAndContent(String title, String content);

    List<JournalEntryEntity> findByContent(String content);


}
