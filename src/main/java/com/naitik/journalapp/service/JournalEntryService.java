package com.naitik.journalapp.service;

import com.naitik.journalapp.entity.JournalEntityRequest;
import com.naitik.journalapp.entity.JournalEntryEntity;
import com.naitik.journalapp.repository.JournalEntryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class JournalEntryService {
    //dont need to write these if we use @sl4j
  // private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    private final JournalEntryRepo journalEntryRepo;

    public JournalEntityRequest saveEntry(JournalEntityRequest journalEntryRequest) {
        try {
            journalEntryRequest.setDate(LocalDateTime.now());
            log.info("Saving journal entry: {}", journalEntryRequest);
            JournalEntryEntity journalEntryEntity = new JournalEntryEntity(journalEntryRequest);
            journalEntryEntity = journalEntryRepo.save(journalEntryEntity);
            JournalEntityRequest journalEntryRequestSaved = new JournalEntityRequest(journalEntryEntity);
            log.info("Journal entry saved successfully.");
            return journalEntryRequestSaved;
        } catch (Exception e) {
            log.error("Error saving journal entry: {}", e.getMessage(), e);
            throw new RuntimeException("Error saving journal entry: " + e.getMessage(), e);
        }
    }


    public List<JournalEntityRequest> getAll() {
        try {
            log.info("Fetching all journal entries.");
            List<JournalEntryEntity> entries = journalEntryRepo.findAll();
            List<JournalEntityRequest> requests = new ArrayList<>();
            for (JournalEntryEntity entity : entries) {
                requests.add(new JournalEntityRequest(entity));
            }
            log.info("Fetched {} journal entries.", entries.size());
            return requests;
        } catch (Exception e) {
            log.error("Error fetching all journal entries: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching all journal entries: " + e.getMessage(), e);
        }
    }

    public Optional<JournalEntityRequest> findById(ObjectId id) {
        try {
            log.info("Finding journal entry by ID: {}", id);
            Optional<JournalEntryEntity> journalEntry = journalEntryRepo.findById(id);

            if (journalEntry.isPresent()) {
                log.info("Journal entry found: {}", journalEntry.get());
                JournalEntityRequest request = new JournalEntityRequest(journalEntry.get());
                return Optional.of(request);
            } else {
                log.warn("Journal entry not found with ID: {}", id);
                return Optional.empty();
            }
        } catch (Exception e) {
            log.error("Error finding journal entry by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error finding journal entry by ID: " + e.getMessage(), e);
        }
    }



    public JournalEntityRequest deleteById(ObjectId id) {
        try {
            log.info("Deleting journal entry with ID: {}", id);
            journalEntryRepo.deleteById(id);
            log.info("Journal entry deleted successfully.");
        } catch (Exception e) {
            log.error("Error deleting journal entry by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error deleting journal entry by ID: " + e.getMessage(), e);
        }
        return null;
    }

    public List<JournalEntityRequest> getEntriesByTitle(String title) {
        log.info("Fetching journal entries by title: {}", title);

        // Fetch entries from the database
        List<JournalEntryEntity> entries = journalEntryRepo.findByTitle(title);

        // If no entries found, log it as an info message and return an empty list
        if (entries.isEmpty()) {
            log.warn("No journal entries found with title: {}", title);
            throw new NoSuchElementException("No journal entries found with title: " + title);
        }

        // Convert JournalEntryEntity to JournalEntityRequest
        List<JournalEntityRequest> requests = new ArrayList<>();
        for (JournalEntryEntity entity : entries) {
            requests.add(new JournalEntityRequest(entity));
        }

        /*log.info("Fetched {} journal entries with title: {}", entries.size(), title);*/
        return requests;
    }


    public List<JournalEntityRequest> getEntriesByTitleAndContent(String title, String content) {
        try {
            log.info("Fetching journal entries by title: {} and content: {}", title, content);
            List<JournalEntryEntity> entries = journalEntryRepo.findByTitleAndContent(title, content);
            List<JournalEntityRequest> requests = new ArrayList<>();
            for (JournalEntryEntity entity : entries) {
                requests.add(new JournalEntityRequest(entity));
            }
            log.info("Fetched {} journal entries with title: {} and content: {}", entries.size(), title, content);
            return requests;
        } catch (Exception e) {
            log.error("Error fetching journal entries by title and content: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching journal entries by title and content: " + e.getMessage(), e);
        }
    }

    public List<JournalEntryEntity> getEntriesByContent(String content) {
        /*try {
            logger.info("Fetching journal entries by content: {}", content);
            List<JournalEntry> entries = journalEntryRepo.findByContent(content);
            logger.info("Fetched {} journal entries by content.", entries.size());
            return entries;
        } catch (Exception e) {
            logger.error("Error fetching journal entries by content: {}", e.getMessage(), e);
            throw new RuntimeException("Error fetching journal entries by content: " + e.getMessage(), e);
        }*/
        return journalEntryRepo.findByContent(content);
    }

}

