package com.example.hsinhung.simpletodo.notelist;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsinhung.simpletodo.R;
import com.example.hsinhung.simpletodo.base.AdaptedRecyclerView;
import com.example.hsinhung.simpletodo.models.Note;

import java.util.List;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class NoteListAdapter extends AdaptedRecyclerView.AdaptedAdapter<Note, NoteListAdapter.ViewHolder> {

    public NoteListAdapter(Fragment parentFragment, List<Note> objects) {
        super(parentFragment, objects);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflateView(parent, R.layout.item_note_list));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = getItem(position);

        holder.content.setText(note.getContent());
    }

    public static class ViewHolder extends AdaptedRecyclerView.AdaptedViewHolder {

        TextView content;

        public ViewHolder(View view) {
            super(view);
            content = (TextView) view.findViewById(R.id.note_content);
        }
    }
}
