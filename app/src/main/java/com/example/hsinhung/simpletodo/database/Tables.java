package com.example.hsinhung.simpletodo.database;

/**
 * Created by SymPhoNy on 09/11/2016.
 */

public final class Tables {

    private Tables() {
    }

    public static abstract class NoteTable {
        public static final String TABLE_NAME = "note";
        public static final String NOTE_ID = "note_id";
        public static final String NOTE_SUBJECT = "note_subject";
        public static final String NOTE_CREATE_TIME = "note_create_time";
        public static final String NOTE_DUE_DATE = "note_due_date";
        public static final String NOTE_CONTENT = "note_content";
        public static final String NOTE_PRIORITY = "note_priority";
        public static final String NOTE_STATUS = "note_status";
    }
}
