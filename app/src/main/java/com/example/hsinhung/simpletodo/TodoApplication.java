package com.example.hsinhung.simpletodo;

import android.app.Application;

import com.example.hsinhung.simpletodo.database.Database;

/**
 * Created by SymPhoNy on 10/11/2016.
 */

public class TodoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //init Database
        Database.getInstance(this);
    }
}
