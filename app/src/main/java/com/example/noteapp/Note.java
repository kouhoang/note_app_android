package com.example.noteapp;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class Note {
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT = "noteEdit";
    private int id;
    private String title;
    private String description;
    private String time;
    private Date deleted;

    public Note(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = getCurrentTime();
        this.deleted = deleted;
    }

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = getCurrentTime();
        deleted = null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }


    public static Note getNodeForID(int passedNoteID){
        for (Note note: noteArrayList){
            if(note.getId() == passedNoteID){
                return note;
            }
        }
        return null;
    }

    public static ArrayList<Note> nonDeleteNotes(){
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for(Note note : noteArrayList){
            if (note.getDeleted() == null){
                nonDeleted.add(note);
            }
        }

        return nonDeleted;
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }
}
