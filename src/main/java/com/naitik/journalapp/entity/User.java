package com.naitik.journalapp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User {

    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;


    @DBRef
    private List<JournalEntryEntity> journalEntries = new ArrayList<>();
}
