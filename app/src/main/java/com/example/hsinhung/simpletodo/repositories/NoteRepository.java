package com.example.hsinhung.simpletodo.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hsinhung.simpletodo.database.Database;
import com.example.hsinhung.simpletodo.database.Tables;
import com.example.hsinhung.simpletodo.models.Note;
import com.example.hsinhung.simpletodo.utilities.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SymPhoNy on 10/11/2016.
 */

public class NoteRepository {

    private Context context;
    private Database database;

    public NoteRepository(Context context, Database database) {
        this.context = context;
        this.database = database;
    }

    public List<Note> readNoteList() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = database.getWritableDatabase();

        String[] columns = {
                Tables.NoteTable.NOTE_ID,
                Tables.NoteTable.NOTE_SUBJECT,
                Tables.NoteTable.NOTE_CREATE_TIME,
                Tables.NoteTable.NOTE_DUE_DATE,
                Tables.NoteTable.NOTE_CONTENT,
                Tables.NoteTable.NOTE_PRIORITY,
                Tables.NoteTable.NOTE_STATUS
        };

//        String order = Tables.NoteTable.

        Cursor cursor = db.query(Tables.NoteTable.TABLE_NAME, null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            noteList.add(new Note(cursor));
        }
        cursor.close();

        return noteList;
    }

    public void insertNote(Note note) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Tables.NoteTable.NOTE_SUBJECT, note.getSubject());
        contentValues.put(Tables.NoteTable.NOTE_DUE_DATE, Utility.formatDateTimeToString(note.getDueTime()));
        contentValues.put(Tables.NoteTable.NOTE_CONTENT, note.getContent());
        contentValues.put(Tables.NoteTable.NOTE_PRIORITY, note.getPriority());
        contentValues.put(Tables.NoteTable.NOTE_STATUS, note.getStatus());

        db.insert(Tables.NoteTable.TABLE_NAME, null, contentValues);
    }

    public void updateNote(Note note) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Tables.NoteTable.NOTE_SUBJECT, note.getSubject());
        contentValues.put(Tables.NoteTable.NOTE_DUE_DATE, Utility.formatDateTimeToString(note.getDueTime()));
        contentValues.put(Tables.NoteTable.NOTE_CONTENT, note.getContent());
        contentValues.put(Tables.NoteTable.NOTE_PRIORITY, note.getPriority());
        contentValues.put(Tables.NoteTable.NOTE_STATUS, note.getStatus());

        String selection = Tables.NoteTable.NOTE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(note.getId()) };

        db.update(Tables.NoteTable.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = database.getWritableDatabase();

        String selection = Tables.NoteTable.NOTE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(note.getId()) };

        db.delete(Tables.NoteTable.TABLE_NAME, selection, selectionArgs);
    }
}
