package com.example.hsinhung.simpletodo;

import android.os.Bundle;

import com.example.hsinhung.simpletodo.base.BaseActivity;
import com.example.hsinhung.simpletodo.notelist.NoteListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteListFragment();
    }

    private void addNoteListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main, new NoteListFragment(), NoteListFragment.class.getSimpleName())
                .commit();
    }
}
