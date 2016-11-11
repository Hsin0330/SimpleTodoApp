package com.example.hsinhung.simpletodo.notelist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hsinhung.simpletodo.R;
import com.example.hsinhung.simpletodo.base.BaseFragment;
import com.example.hsinhung.simpletodo.database.Database;
import com.example.hsinhung.simpletodo.models.Note;
import com.example.hsinhung.simpletodo.repositories.NoteRepository;
import com.example.hsinhung.simpletodo.utilities.Constants;
import com.example.hsinhung.simpletodo.utilities.LocalBroadcastTool;

import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.example.hsinhung.simpletodo.utilities.Constants.ACTION_EDIT_NOTE;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class NoteListFragment extends BaseFragment {

    private final int REQUEST_CODE = 20;

    private RecyclerView listView;

    private NoteListAdapter itemAdapter;

    private NoteRepository noteRepository;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Note note = intent.getParcelableExtra(Constants.KEY_NOTE);

            switch (action) {
                case Constants.ACTION_ADD_NEW_NOTE:
                    noteRepository.insertNote(note);
                    setNoteData();
                    break;
                case Constants.ACTION_DELETE_NOTE:
                    noteRepository.deleteNote(note);
                    setNoteData();
                    break;
                case ACTION_EDIT_NOTE:
                    NoteEditDialog dialog = new NoteEditDialog();
                    dialog.setArguments(intent.getExtras());
                    dialog.show(getActivity().getSupportFragmentManager(), NoteEditDialog.class.getSimpleName());
                    break;
                case Constants.ACTION_UPDATE_NOTE:
                    noteRepository.updateNote(note);
                    setNoteData();
                    break;
            }

        }
    };

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

        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastTool.register(getContext(), receiver,
                Constants.ACTION_ADD_NEW_NOTE,
                Constants.ACTION_DELETE_NOTE,
                Constants.ACTION_EDIT_NOTE,
                Constants.ACTION_UPDATE_NOTE);
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastTool.unregister(getContext(), receiver);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int position = data.getExtras().getInt("position", -1);
            String content = data.getExtras().getString("content", "");

            itemAdapter.addItem(position, new Note("", content, 0, 0, new Date()));
            itemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_note_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.icon_add_note) {
            new NoteEditDialog().show(getActivity().getSupportFragmentManager(), NoteEditDialog.class.getSimpleName());
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews(View rootView) {
        listView = (RecyclerView) rootView.findViewById(R.id.fragment_listview_note);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setNoteData() {
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
}
