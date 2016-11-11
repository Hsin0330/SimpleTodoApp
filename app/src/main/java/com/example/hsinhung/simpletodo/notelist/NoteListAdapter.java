package com.example.hsinhung.simpletodo.notelist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsinhung.simpletodo.R;
import com.example.hsinhung.simpletodo.base.AdaptedRecyclerView;
import com.example.hsinhung.simpletodo.models.Note;
import com.example.hsinhung.simpletodo.models.Priority;
import com.example.hsinhung.simpletodo.models.Status;
import com.example.hsinhung.simpletodo.utilities.Constants;
import com.example.hsinhung.simpletodo.utilities.LocalBroadcastTool;
import com.example.hsinhung.simpletodo.utilities.Utility;

import java.util.List;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class NoteListAdapter extends AdaptedRecyclerView.AdaptedAdapter<Note, NoteListAdapter.ViewHolder> {

    private final AdaptedRecyclerView.OnItemClickListener onItemClickListener = new AdaptedRecyclerView.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(Constants.ACTION_EDIT_NOTE);
            intent.putExtra(Constants.KEY_NOTE, getItem(position));
            LocalBroadcastTool.send(getContext(), intent);
        }
    };

    private final AdaptedRecyclerView.OnItemLongClickListener onItemLongClickListener = new AdaptedRecyclerView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(View view, int position) {
            Intent intent = new Intent(Constants.ACTION_DELETE_NOTE);
            intent.putExtra(Constants.KEY_NOTE, getItem(position));
            LocalBroadcastTool.send(getContext(), intent);
            return true;
        }
    };

    public NoteListAdapter(Fragment parentFragment, List<Note> objects) {
        super(parentFragment, objects);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(inflateView(parent, R.layout.item_note_list));
        viewHolder.setOnItemClickListener(onItemClickListener);
        viewHolder.setOnItemLongClickListener(onItemLongClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = getItem(position);

        holder.subject.setText(note.getSubject());
        holder.content.setText(note.getContent());
        holder.dueDate.setText(Utility.formatDateToString(note.getDueTime()));
        holder.priority.setText(Priority.values()[note.getPriority()].name());
        holder.status.setText(Status.values()[note.getStatus()].name());
    }

    public static class ViewHolder extends AdaptedRecyclerView.AdaptedViewHolder {

        TextView subject;
        TextView dueDate;
        TextView content;
        TextView priority;
        TextView status;

        public ViewHolder(View view) {
            super(view);
            subject = (TextView) view.findViewById(R.id.note_subject);
            dueDate = (TextView) view.findViewById(R.id.note_due_date);
            content = (TextView) view.findViewById(R.id.note_content);
            priority = (TextView) view.findViewById(R.id.note_priority);
            status = (TextView) view.findViewById(R.id.note_status);
        }
    }
}
