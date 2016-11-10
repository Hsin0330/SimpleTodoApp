package com.example.hsinhung.simpletodo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hsinhung.simpletodo.utilities.TodoLog;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TodoLog.d("DialogFragment", ">>> onCreate @ " + getClass().getSimpleName() + " <<<");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TodoLog.d("DialogFragment", ">>> onCreateView @ " + getClass().getSimpleName() + " <<<");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TodoLog.d("DialogFragment", ">>> onViewCreated @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    public void onStart() {
        super.onStart();
        TodoLog.d("DialogFragment", ">>> onStart @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    public void onResume() {
        super.onResume();
        TodoLog.d("DialogFragment", ">>> onResume @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    public void onPause() {
        super.onPause();
        TodoLog.d("DialogFragment", ">>> onPause @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    public void onStop() {
        super.onStop();
        TodoLog.d("DialogFragment", ">>> onStop @ " + getClass().getSimpleName() + " <<<");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TodoLog.d("DialogFragment", ">>> onDestroy @ " + getClass().getSimpleName() + " <<<");
    }
}
