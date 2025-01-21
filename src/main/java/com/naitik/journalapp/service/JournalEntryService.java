package com.naitik.journalapp.service;

import com.naitik.journalapp.entity.JournalEntityRequest;
import com.naitik.journalapp.entity.JournalEntity;
import com.naitik.journalapp.exception.CustomDatabaseException;
import com.naitik.journalapp.exception.CustomNotFoundException;
import com.naitik.journalapp.repository.JournalEntryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class JournalEntryService {

    private final JournalEntryRepo journalEntryRepo;

    public JournalEntityRequest saveEntry(JournalEntityRequest journalEntityRequest) {
        if (journalEntityRequest == null) {
            log.warn("Attempt to save null journal entry.");
            throw new CustomDatabaseException("Journal entry cannot be null.");
        }
        try {
            journalEntityRequest.setDate(LocalDateTime.now());
            log.info("Saving journal entry: {}", journalEntityRequest);
            JournalEntity journalEntity = new JournalEntity(journalEntityRequest);
            journalEntity = journalEntryRepo.save(journalEntity);

            log.info("Journal entry saved successfully with ID: {}", journalEntity.getId());
            return new JournalEntityRequest(journalEntity);
        } catch (DataAccessException e) {
            log.error("Database error while saving journal entry: {}", e.getMessage(), e);
            throw new CustomDatabaseException("Error saving journal entry.", e);
        }
    }



    public List<JournalEntityRequest> getAll() {
        try {
            log.info("Fetching all journal entries.");

            // Fetching all journal entries from the database
            List<JournalEntity> entries = journalEntryRepo.findAll();

            // Manually converting each JournalEntity to JournalEntityRequest
            List<JournalEntityRequest> requests = new ArrayList<>();
            for (JournalEntity entity : entries) {
                requests.add(new JournalEntityRequest(entity));
            }

            log.info("Fetched {} journal entries.", entries.size());
            return requests;
        } catch (DataAccessException e) {
            log.error("Database error while fetching all journal entries: {}", e.getMessage(), e);
            // Throw a custom exception if there's a database issue
            throw new CustomDatabaseException("Error fetching all journal entries from the database.", e);
        }
    }


    public Optional<JournalEntityRequest> findById(ObjectId id) {
        try {
            log.info("Finding journal entry by ID: {}", id);
            Optional<JournalEntity> journalEntry = journalEntryRepo.findById(id);

            if (journalEntry.isPresent()) {
                log.info("Journal entry found: {}", journalEntry.get());
                JournalEntityRequest request = new JournalEntityRequest(journalEntry.get());
                return Optional.of(request);
            } else {
                log.warn("Journal entry not found with ID: {}", id);
                throw new CustomDatabaseException("Id not in database" +id);
            }
        } catch (Exception e) {
            log.error("Error finding journal entry by ID: {}", e.getMessage(), e);
            throw new CustomDatabaseException("Error finding journal entry by ID: " + e.getMessage(), e);
        }
    }



    public void deleteById(ObjectId id) {
        try {
            Optional<JournalEntity> existingEntry = journalEntryRepo.findById(id);
            if (existingEntry.isPresent()) {
                // Proceed with deletion
                journalEntryRepo.deleteById(id);
                log.info("Journal entry with ID: {} deleted successfully.", id);
            } else {
                log.warn("Journal entry not found with  ID: {}", id);
                throw new CustomNotFoundException("Journal entry with ID: " + id + " not found in the database.");
            }
        } catch (Exception e) {
            log.error("Error deleting journal entry by ID: {}", e.getMessage(), e);
            throw new CustomDatabaseException("Error deleting journal entry by ID: " + e.getMessage(), e);
        }
    }

    public List<JournalEntityRequest> getEntriesByTitle(String title) {
        log.info("Fetching journal entries by title: {}", title);

        // Fetch entries from the database
        List<JournalEntity> entries = journalEntryRepo.findByTitle(title);

        // If no entries found, log it as an info message and return an empty list
        if (entries.isEmpty()) {
            log.warn("No journal entries found with title: {}", title);
            throw new NoSuchElementException("No journal entries found with title: " + title);
        }

        // Convert JournalEntryEntity to JournalEntityRequest
        List<JournalEntityRequest> requests = new ArrayList<>();
        for (JournalEntity entity : entries) {
            requests.add(new JournalEntityRequest(entity));
        }

        return requests;
    }


    public List<JournalEntityRequest> getEntriesByTitleAndContent(String title, String content) {
        try {
            log.info("Fetching journal entries by title: {} and content: {}", title, content);
            List<JournalEntity> entries = journalEntryRepo.findByTitleAndContent(title, content);
            List<JournalEntityRequest> requests = new ArrayList<>();
            for (JournalEntity entity : entries) {
                requests.add(new JournalEntityRequest(entity));
            }
            log.info("Fetched {} journal entries with title: {} and content: {}", entries.size(), title, content);
            return requests;
        } catch (Exception e) {
            log.error("Error fetching journal entries by title and content: {}", e.getMessage(), e);
            throw new CustomDatabaseException("Error fetching journal entries by title and content: " + e.getMessage(), e);
        }
    }

    public List<JournalEntityRequest> getEntriesByContent(String content) {
        try {
            log.info("Fetching journal entries by content: {}", content);
            List<JournalEntity> entries = journalEntryRepo.findByContent(content);
            List<JournalEntityRequest> requests = new ArrayList<>();
            for (JournalEntity entity : entries) {
                requests.add(new JournalEntityRequest(entity));
            }
            log.info("Fetched {} journal entries by content.", entries.size());
            return requests;
        } catch (Exception e) {
            log.error("Error fetching journal entries by content: {}", e.getMessage(), e);
            throw new CustomDatabaseException("Error fetching journal entries by content: " + e.getMessage(), e);
        }


    }

}

