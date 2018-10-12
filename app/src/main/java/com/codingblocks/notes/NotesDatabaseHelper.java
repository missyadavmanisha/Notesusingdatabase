
package com.codingblocks.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NotesDatabaseHelper extends SQLiteOpenHelper {
    public NotesDatabaseHelper(Context context) {
        super(context, "Abhishek", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE task_table (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL," +
                "timeStamp TEXT NOT NULL);");

    }

    public void insert(Note note) {
        //Insert the note into the table
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", note.getTitle());
        contentValues.put("timeStamp", note.getTime());
        ;

        getWritableDatabase().insert("task_table",
                null
                , contentValues);
    }

    public void updateNote(Note note) {

        ContentValues contentValues = new ContentValues();

        contentValues.put("title", note.getTitle());

        contentValues.put("timeStamp", note.getTime());


        getWritableDatabase().update("task_table",
                contentValues,
                "id = ?",
                new String[]{note.getId().toString()});

    }

    public void deleteNote(Note note) {
        getWritableDatabase().delete("task_table",
                "id = ?",
                new String[]{note.getId().toString()});
    }

    public Note getNoteById(Integer noteId) {

        Note note = null;

        Cursor c = getReadableDatabase().query("task_table",
                null,
                "id = ?",
                new String[]{noteId.toString()},
                null,
                null,
                null);

        if (c.moveToNext()) {
            Integer id = c.getInt(0);
            String title = c.getString(1);
            String timeStamp = c.getString(c.getColumnIndex("timeStamp"));
            note = new Note(title,timeStamp,id);
        }

        c.close();
        return note;
    }

    public ArrayList<Note> getAllNotes() {

        ArrayList<Note> notes = new ArrayList<>();
        //Populate the notes ArrayList

        Cursor c = getReadableDatabase().query(
                "task_table",
                null,
                null,
                null,
                null,
                null,
                "timeStamp DESC"
        );


        while (c.moveToNext()) {

            Integer id = c.getInt(0);
            String title = c.getString(1);
            //  String description = c.getString(c.getColumnIndex("content"));
            String timeStamp = c.getString(c.getColumnIndex("timeStamp"));
            //  Integer isDone = c.getInt(c.getColumnIndex("isDone"));

            Note note = new Note(title, timeStamp, id);

            notes.add(note);
        }

        c.close();
        return notes;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}