package com.example.hsinhung.simpletodo.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.hsinhung.simpletodo.database.Tables;
import com.example.hsinhung.simpletodo.utilities.Utility;

import java.util.Date;

/**
 * Created by SymPhoNy on 10/11/2016.
 */

public class Note implements Parcelable{

    private int id;
    private String subject;
    private Date createTime;
    private Date dueTime;
    private String content;
    private int priority;
    private int status;

    public Note() {
        subject = "";
        createTime = new Date();
        dueTime = new Date();
        content = "";
        priority = 0;
        status = 0;
    }

    public Note(String subject, String content, int priority, int status, Date dueTime) {
        this.subject = subject;
        this.content = content;
        this.priority = priority;
        this.status = status;
        this.dueTime = dueTime;
    }

    public Note(Cursor cursor) {
        id = cursor.getInt(cursor.getColumnIndex(Tables.NoteTable.NOTE_ID));
        subject = cursor.getString(cursor.getColumnIndex(Tables.NoteTable.NOTE_SUBJECT));
        createTime = Utility.parseStringToDateTime(cursor.getString(cursor.getColumnIndex(Tables.NoteTable.NOTE_CREATE_TIME)));
        dueTime = Utility.parseStringToDateTime(cursor.getString(cursor.getColumnIndex(Tables.NoteTable.NOTE_DUE_DATE)));
        content = cursor.getString(cursor.getColumnIndex(Tables.NoteTable.NOTE_CONTENT));
        priority = cursor.getInt(cursor.getColumnIndex(Tables.NoteTable.NOTE_PRIORITY));
        status = cursor.getInt(cursor.getColumnIndex(Tables.NoteTable.NOTE_STATUS));
    }

    protected Note(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        content = in.readString();
        priority = in.readInt();
        status = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeString(content);
        dest.writeInt(priority);
        dest.writeInt(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
