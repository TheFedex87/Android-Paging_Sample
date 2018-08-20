package it.pagingexample.thefedex87.paginationexamplegithub.ui.adapters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.pagingexample.thefedex87.paginationexamplegithub.R;
import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;

public class TopicsAdapter extends PagedListAdapter<Topic, TopicsViewHolder> {
    private boolean isLoadingNetowrk;

    public TopicsAdapter() {
        super(Topic.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TopicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_line, parent, false);
        return new TopicsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsViewHolder holder, int position) {
        if (holder != null)
            if(position < getItemCount() - 1) {
                holder.bind(getItem(position), false);
            } else {
                holder.bind(getItem(position), isLoadingNetowrk);
            }

    }

    public void setIsLoadingNetwork(boolean isLoadingNetowrk){

        this.isLoadingNetowrk = isLoadingNetowrk;
        //notifyItemChanged(getItemCount() - 1);
        notifyDataSetChanged();
    }
}
