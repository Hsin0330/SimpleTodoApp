package com.example.hsinhung.simpletodo.notelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hsinhung.simpletodo.R;
import com.example.hsinhung.simpletodo.base.BaseDialogFragment;
import com.example.hsinhung.simpletodo.models.Note;
import com.example.hsinhung.simpletodo.models.Priority;
import com.example.hsinhung.simpletodo.models.Status;
import com.example.hsinhung.simpletodo.utilities.Constants;
import com.example.hsinhung.simpletodo.utilities.LocalBroadcastTool;
import com.example.hsinhung.simpletodo.utilities.TodoLog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class NoteEditDialog extends BaseDialogFragment {

    private EditText editSubject;
    private DatePicker datePicker;
    private EditText editContent;
    private Spinner spinnerPriority;
    private Spinner spinnerStatus;
    private Button btnConfirm;

    private Calendar calendar = Calendar.getInstance();
    private Note note;
    private boolean isEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_edit_note, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isEdit = getArguments() != null;
        note = isEdit ? (Note) getArguments().getParcelable(Constants.KEY_NOTE) : new Note();

        initViews(view);
        setDatePicker();
        setEditSubject();
        setPrioritySpinner();
        setStatusSpinner();
        setBtnConfirm();
    }

    private void initViews(View rootView) {
        editSubject = (EditText) rootView.findViewById(R.id.note_edit_subject);
        datePicker = (DatePicker) rootView.findViewById(R.id.note_edit_date_picker);
        editContent = (EditText) rootView.findViewById(R.id.note_edit_content);
        spinnerPriority = (Spinner) rootView.findViewById(R.id.note_edit_priority);
        spinnerStatus = (Spinner) rootView.findViewById(R.id.note_edit_status);
        btnConfirm = (Button) rootView.findViewById(R.id.confirm);
    }

    private void setEditSubject() {
        editSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnConfirm.setEnabled(s.length() > 0);
            }
        });
        if(isEdit) {
            editSubject.setText(note.getSubject());
        }
    }

    private void setDatePicker() {
        if(isEdit) {
            calendar.setTime(note.getDueTime());
        }
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        TodoLog.d("onDateChanged", "Year : " + year + "  Month : " + monthOfYear + "  date : " + dayOfMonth);
                        calendar.set(year, monthOfYear, dayOfMonth);
                    }
                });
    }

    private void setPrioritySpinner() {
        List<String> valueList = new ArrayList<>();
        for(Priority priority : Priority.values()) {
            valueList.add(priority.name());
        }

        spinnerPriority.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, valueList));
        if(isEdit) {
            spinnerPriority.setSelection(note.getPriority());
        }

//        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void setStatusSpinner() {
        List<String> valueList = new ArrayList<>();
        for(Status status : Status.values()) {
            valueList.add(status.name());
        }

        spinnerStatus.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, valueList));
        if(isEdit) {
            spinnerStatus.setSelection(note.getStatus());
        }
//        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void setBtnConfirm() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = editSubject.getText().toString();
                String content = editContent.getText().toString();
                int priority = Priority.valueOf((String) spinnerPriority.getSelectedItem()).ordinal();
                int status = Status.valueOf((String) spinnerStatus.getSelectedItem()).ordinal();

                note.setSubject(subject);
                note.setContent(content);
                note.setPriority(priority);
                note.setStatus(status);
                note.setDueTime(calendar.getTime());

                Intent intent = new Intent(isEdit ? Constants.ACTION_UPDATE_NOTE : Constants.ACTION_ADD_NEW_NOTE);
                intent.putExtra(Constants.KEY_NOTE, note);
                LocalBroadcastTool.send(getContext(), intent);
                dismiss();
            }
        });
    }


}
