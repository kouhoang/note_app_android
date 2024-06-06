package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends ArrayAdapter<Note> {
    private static final int[] COLORS = {R.color.yellow, R.color.light_pink, R.color.pink, R.color.purple, R.color.light_green, R.color.light_green_2};

    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_cell, parent, false);

        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);
        TextView time = convertView.findViewById(R.id.dateTextView);

        title.setText(note.getTitle());
        desc.setText(note.getDescription());
        time.setText(note.getTime());

        int color = COLORS[position % COLORS.length];
        convertView.setBackgroundColor(getContext().getResources().getColor(color));

        return convertView;
    }
}

