package com.example.hsinhung.simpletodo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hsinhung.simpletodo.utilities.TodoLog;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TodoLog.d("Activity", ">>> onCreate @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    protected void onStart() {
        super.onStart();
        TodoLog.d("Activity", ">>> onStart @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TodoLog.d("Activity", ">>> onResume @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    protected void onPause() {
        super.onPause();
        TodoLog.d("Activity", ">>> onPause @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    protected void onStop() {
        super.onStop();
        TodoLog.d("Activity", ">>> onStop @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TodoLog.d("Activity", ">>> onDestroy @ " + getClass().getSimpleName() + " <<<");
    }
}
