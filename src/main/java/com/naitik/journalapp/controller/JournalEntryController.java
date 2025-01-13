package com.naitik.journalapp.controller;

import com.naitik.journalapp.entity.JournalEntityRequest;
import com.naitik.journalapp.entity.JournalEntryEntity;
import com.naitik.journalapp.service.JournalEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class JournalEntryController {

//    journalEntryController(JournalEntryService journalEntryService){
//        this.journalEntryService = journalEntryService;
//    }
    private final JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntityRequest> getALL(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntityRequest createEntry(@Valid @RequestBody JournalEntityRequest myEntry){
        return journalEntryService.saveEntry(myEntry);
    }



    @GetMapping("id/{myId}")
    public JournalEntityRequest getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);

    }
    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
         journalEntryService.deleteById(myId);
        return true;
    }

   @PutMapping("id/{id}")
    public JournalEntityRequest updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntityRequest newEntry){
        JournalEntityRequest old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
  @GetMapping("/search/{title}")
  public List<JournalEntityRequest> getJournalByTitle(@PathVariable String title)   {
      return journalEntryService.getEntriesByTitle(title);
  }


    /*@GetMapping("/search")
    public List<JournalEntry> getJournalByTitleAndContent(
            @RequestParam String title,
            @RequestParam String content) {
        return journalEntryService.getEntriesByTitleAndContent(title, content);
    }*/
 /* @GetMapping("/search")
  public List<JournalEntryEntity> searchEntries(@RequestParam(required = false) String title,
                                                @RequestParam(required = false) String content) {

      if (title != null && content != null) {
          return journalEntryService.getEntriesByTitleAndContent(title, content);
      } else if (title != null) {
          return journalEntryService.getEntriesByTitle(title);
      } else if (content != null) {
          return journalEntryService.getEntriesByContent(content);
      } else {
          return journalEntryService.getAll();
      }
  }*/

    //Naitik kaklotar
    //2222222222222222222222
    //3333333333333
}


