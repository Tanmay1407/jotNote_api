package com.patel.tanmay.jotNote.service;

import com.patel.tanmay.jotNote.entity.JournalEntry;
import com.patel.tanmay.jotNote.entity.User;
import com.patel.tanmay.jotNote.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getUserNotes().add(saved);
        userService.saveEntry(user);
    }

    public List<JournalEntry> fetchNotes(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> fetchNoteById(ObjectId noteId){
        return journalEntryRepository.findById(noteId);
    }

    public ObjectId deleteById(ObjectId noteId){
        journalEntryRepository.deleteById(noteId);
        return noteId;
    }
}
