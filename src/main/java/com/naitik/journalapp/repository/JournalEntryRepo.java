package com.naitik.journalapp.repository;

import com.naitik.journalapp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId>{
    public List<JournalEntity> findByTitle(String title);

    List<JournalEntity> findByTitleAndContent(String title, String content);

    List<JournalEntity> findByContent(String content);


}
