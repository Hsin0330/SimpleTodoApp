package com.example.hsinhung.simpletodo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SymPhoNy on 08/11/2016.
 */

public class Database extends SQLiteOpenHelper {

    private static Database databaseInstance;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Note.db";


    public static synchronized Database getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new Database(context);
        }
        return databaseInstance;
    }

    private Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTable = "CREATE TABLE " + Tables.NoteTable.TABLE_NAME + " (" +
                Tables.NoteTable.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                Tables.NoteTable.NOTE_SUBJECT + TEXT_TYPE + COMMA_SEP +
                Tables.NoteTable.NOTE_CREATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP" + COMMA_SEP +
                Tables.NoteTable.NOTE_DUE_DATE + " DATETIME" + COMMA_SEP +
                Tables.NoteTable.NOTE_CONTENT + TEXT_TYPE + COMMA_SEP +
                Tables.NoteTable.NOTE_PRIORITY + " INTEGER" + COMMA_SEP +
                Tables.NoteTable.NOTE_STATUS + " INTEGER" +
                ")";

        db.execSQL(createNoteTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
