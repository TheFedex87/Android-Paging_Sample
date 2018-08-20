package it.pagingexample.thefedex87.paginationexamplegithub.ui.adapters;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.pagingexample.thefedex87.paginationexamplegithub.NetworkState;
import it.pagingexample.thefedex87.paginationexamplegithub.R;
import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;

public class TopicsAdapter extends PagedListAdapter<Topic, TopicsViewHolder> {
    private NetworkState networkState;

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
                holder.bind(getItem(position), NetworkState.LOADED);
            } else {
                holder.bind(getItem(position), networkState);
            }

    }

    public void setIsLoadingNetwork(NetworkState isLoadingNetowrk){

        this.networkState = isLoadingNetowrk;
        //notifyItemChanged(getItemCount() - 1);
        notifyDataSetChanged();
    }
}
