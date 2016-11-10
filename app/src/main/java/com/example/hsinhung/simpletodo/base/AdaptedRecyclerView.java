package com.example.hsinhung.simpletodo.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class AdaptedRecyclerView extends RecyclerView {

    public static final String TAG = AdaptedRecyclerView.class.getSimpleName();

    private ScrollPosition scrollPosition = new ScrollPosition();

    public AdaptedRecyclerView(Context context) {
        super(context);
    }

    public AdaptedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        if (getLayoutManager() instanceof LinearLayoutManager) {
            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(scrollPosition.position, scrollPosition.offset);
        }
    }

    @Override
    public void swapAdapter(RecyclerView.Adapter adapter, boolean removeAndRecycleExistingViews) {
        super.swapAdapter(adapter, removeAndRecycleExistingViews);
        if (getLayoutManager() instanceof LinearLayoutManager) {
            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(scrollPosition.position, scrollPosition.offset);
        }
    }

    @Override
    public void scrollToPosition(int position) {
        super.scrollToPosition(position);
        scrollPosition.update(position);
    }

    @Override
    public void smoothScrollToPosition(int position) {
        super.smoothScrollToPosition(position);
        scrollPosition.update(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        for(int i = 0; i < getChildCount(); i ++ ) {
            AdaptedViewHolder viewHolder = (AdaptedViewHolder) getChildViewHolder(getChildAt(i));
            viewHolder.setOnItemClickListener(onItemClickListener);
        }
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemClickListener) {
        for(int i = 0; i < getChildCount(); i ++ ) {
            AdaptedViewHolder viewHolder = (AdaptedViewHolder) getChildViewHolder(getChildAt(i));
            viewHolder.setOnItemLongClickListener(onItemClickListener);
        }
    }

    public static abstract class AdaptedAdapter<T, VH extends ViewHolder> extends Adapter<VH> {

        private Context context;
        private Fragment parentFragment;

        private List<T> objects;

        public AdaptedAdapter(Context context, List<T> objects) {
            this(context, null, objects);
        }

        public AdaptedAdapter(Fragment parentFragment, List<T> objects) {
            this(parentFragment.getContext(), parentFragment, objects);
        }

        public AdaptedAdapter(Context context, Fragment parentFragment, List<T> objects) {
            this.context = context;
            this.parentFragment = parentFragment;
            this.objects = objects;
        }

        public View inflateView(ViewGroup parent, int resource) {
            return inflateView(parent.getContext(), parent, resource);
        }

        public View inflateView(Context context, ViewGroup parent, int resource) {
            return LayoutInflater.from(context).inflate(resource, parent, false);
        }

        public Context getContext() {
            return context;
        }

        public Fragment getParentFragment() {
            return parentFragment;
        }

        public void addItem(T object) {
            objects.add(object);
//            notifyItemInserted(objects.size() - 1);
        }

        public void addItem(int position, T object) {
            objects.add(position, object);
        }

        public T getItem(int position) {
            return objects.get(position);
        }

        public List<T> getItems() {
            return objects;
        }

        @Override
        public int getItemCount() {
            return objects.size();
        }
    }

    public static abstract class AdaptedViewHolder extends ViewHolder {
        public AdaptedViewHolder(View view) {
            super(view);
        }

        public void setOnItemClickListener(final OnItemClickListener listener) {
            itemView.setOnClickListener(listener == null ? null : new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getAdapterPosition());
                }
            });
        }

        public void setOnItemLongClickListener(final OnItemLongClickListener listener) {
            itemView.setOnLongClickListener(listener == null ? null : new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return listener.onItemLongClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    public static class ScrollPosition {

        public int position;
        public int offset;

        public ScrollPosition() {
            this.position = 0;
            this.offset = 0;
        }

        public ScrollPosition(int position, int offset) {
            this.position = position;
            this.offset = offset;
        }

        public void update(ScrollPosition stable) {
            this.position = stable.position;
            this.offset = stable.offset;
        }

        public void update(int position) {
            this.position = position;
        }

        public void update(int position, int offset) {
            this.position = position;
            this.offset = offset;
        }

        @Override
        public String toString() {
            return "{ position : " + position + " , offset : " + offset + " }";
        }
    }
}
