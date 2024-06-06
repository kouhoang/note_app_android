package com.example.noteapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setNodeAdapter();
        setOnClickListener();
    }


    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLite sqLite = SQLite.instanceOfDatabase(this);
        Note.noteArrayList.clear();
        sqLite.populateNoteListArray();
    }

    private void initWidgets() {
        noteListView = findViewById(R.id.noteListView);
    }

    private void setNodeAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.nonDeleteNotes());
        noteListView.setAdapter(noteAdapter);
    }


    public void newNote(View view) {
        Intent newNoteIntent = new Intent(this, DetailsActivity.class);
        startActivity(newNoteIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFromDBToMemory();
        setNodeAdapter();
    }
}