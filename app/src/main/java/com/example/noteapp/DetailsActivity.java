package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;


public class DetailsActivity extends AppCompatActivity {

    private EditText titleEditText, descEditText;
    private Note selectedNote;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initWidgets();
        checkForEditNote();
    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();

        int passNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT, -1);
        selectedNote = Note.getNodeForID(passNoteID);

        if (selectedNote != null) {
            titleEditText.setText(selectedNote.getTitle());
            descEditText.setText(selectedNote.getDescription());
        }
        else{
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteNoteButton);
    }

    public void saveNote(View view) {
        SQLite sqLite = SQLite.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedNote == null){
            int id = Note.noteArrayList.size();
            Note newNote = new Note(id, title, desc);
            Note.noteArrayList.add(newNote);
            sqLite.addNoteToDatabase(newNote);
        }
        else{
            selectedNote.setTitle(title);
            selectedNote.setDescription(desc);
            sqLite.updateNoteInDB(selectedNote);
        }

        sqLite.populateNoteListArray();
        finish();
    }

    public void deleteNote(View view){
        selectedNote.setDeleted(new Date());
        SQLite sqLite = SQLite.instanceOfDatabase(this);
        sqLite.updateNoteInDB(selectedNote);
        finish();
    }
}