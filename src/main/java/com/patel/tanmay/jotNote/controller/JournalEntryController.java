package com.patel.tanmay.jotNote.controller;

import com.patel.tanmay.jotNote.entity.JournalEntry;
import com.patel.tanmay.jotNote.entity.User;
import com.patel.tanmay.jotNote.service.JournalEntryService;
import com.patel.tanmay.jotNote.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @PostMapping("{username}")
    public ResponseEntity<String> addNote(@RequestBody JournalEntry newEntry,@PathVariable String username){
        journalEntryService.saveEntry(newEntry, username);
        return new ResponseEntity<>("Note added successfully", HttpStatus.CREATED);
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getAllNotesOfUser(@PathVariable String username){
        User user = userService.findByUserName(username);
        List<JournalEntry> notes = user.getUserNotes();
        if(!notes.isEmpty()) {
            return new ResponseEntity<>(notes, HttpStatus.OK);
        }
        return new ResponseEntity<>("No notes found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{noteId}")
    public Optional<JournalEntry> getNotes(@PathVariable ObjectId noteId){
        return journalEntryService.fetchNoteById(noteId);
    }

    @DeleteMapping("id/{noteId}")
    public String deleteNoteById(@PathVariable ObjectId noteId){
        journalEntryService.deleteById(noteId);
        return "Note Deleted";
    }

}
