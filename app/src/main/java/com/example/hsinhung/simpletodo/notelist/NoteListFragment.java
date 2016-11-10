package com.example.hsinhung.simpletodo.notelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hsinhung.simpletodo.R;
import com.example.hsinhung.simpletodo.base.BaseFragment;
import com.example.hsinhung.simpletodo.database.Database;
import com.example.hsinhung.simpletodo.models.Note;
import com.example.hsinhung.simpletodo.repositories.NoteRepository;

import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class NoteListFragment extends BaseFragment {

    private final int REQUEST_CODE = 20;

    private RecyclerView listView;
    private Button btnAdd;
    private EditText editNewContent;

    private NoteListAdapter itemAdapter;

    private NoteRepository noteRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        noteRepository = new NoteRepository(getContext(), Database.getInstance(getContext()));
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setNoteData();

        setupListViewListener();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int position = data.getExtras().getInt("position", -1);
            String content = data.getExtras().getString("content", "");

            itemAdapter.addItem(position, new Note("", content, 0, 0, new Date()));
            itemAdapter.notifyDataSetChanged();

            writeItems();
        }
    }

    private void initViews(View rootView) {
        listView = (RecyclerView) rootView.findViewById(R.id.fragment_listview_note);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setItemAnimator(new DefaultItemAnimator());

        btnAdd = (Button) rootView.findViewById(R.id.fragment_btn_add_note);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemText = editNewContent.getText().toString();
                itemAdapter.addItem(new Note("", itemText, 0, 0, new Date()));
                itemAdapter.notifyDataSetChanged();
                editNewContent.setText("");
//                writeItems();
            }
        });

        editNewContent = (EditText) rootView.findViewById(R.id.fragment_text_new_note);
    }

    private void setNoteData() {
        readItems();
        itemAdapter = new NoteListAdapter(this, noteRepository.readNoteList());
        listView.setAdapter(itemAdapter);
    }

    private void setupListViewListener() {
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
//                items.remove(pos);
//                itemAdapter.notifyDataSetChanged();
//                writeItems();
//                return true;
//            }
//        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getContext(), EditItemActivity.class);
//                intent.putExtra("position", i);
//                intent.putExtra("content", items.get(i));
//
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        });
    }

    private void readItems() {
//        items = noteRepository.readNoteList();
//        File filesDir = getContext().getFilesDir();
//        File todoFile = new File(filesDir, "todo.txt");
//
//        try {
//            items = new ArrayList<>(FileUtils.readLines(todoFile));
//        } catch (IOException e) {
//            items = new ArrayList<>();
//        }
    }

    private void writeItems() {
//        File fileDir = getContext().getFilesDir();
//        File todoFile = new File(fileDir, "todo.txt");
//
//        try {
//            FileUtils.writeLines(todoFile, items);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
