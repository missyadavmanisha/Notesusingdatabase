package com.codingblocks.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes = new ArrayList<>();

    ArrayList<Note> notes1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText noteTitle = findViewById(R.id.etNoteTitle);
        final NotesDatabaseHelper notesDatabaseHelper = new NotesDatabaseHelper(this);





        notes1=notesDatabaseHelper.getAllNotes();
notes.clear();
        if(notes1.size()!=0)
        {
            for(int i=0;i<notes1.size();i++){
                notes.add(notes1.get(i));
            }
        }


        ListView listView = findViewById(R.id.listView);
        final NotesAdapter notesAdapter = new NotesAdapter(notes);


        listView.setAdapter(notesAdapter);


        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title;
                String time;
                title = noteTitle.getText().toString();
                time = String.valueOf(System.currentTimeMillis());
                Note note;
                Log.e("Tag",""+notes.size());
                note = new Note(title, time);
                Log.e("Tag",title);
                notesDatabaseHelper.insert(new Note(title,time));
                notes.add(note);
                notesAdapter.notifyDataSetChanged();


            }
        });



        //   notes = notesDatabaseHelper.getAllNotes();



    }
}