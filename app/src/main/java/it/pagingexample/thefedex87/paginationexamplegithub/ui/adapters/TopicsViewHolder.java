package it.pagingexample.thefedex87.paginationexamplegithub.ui.adapters;

import android.arch.persistence.room.util.StringUtil;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.pagingexample.thefedex87.paginationexamplegithub.R;
import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;

public class TopicsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.line_container)
    ConstraintLayout lineContainer;

    @BindView(R.id.repo_description)
    TextView repoDescription;

    @BindView(R.id.repo_name)
    TextView repoName;

    @BindView(R.id.repo_owner)
    TextView repoOwner;

    @BindView(R.id.repo_private)
    TextView repoPrivate;

    @BindView(R.id.is_loading_pb)
    ProgressBar isLoadingNetworkPb;

    public TopicsViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void bind(Topic topic, boolean isLoadingNetwork){
        if(!isLoadingNetwork) {
            lineContainer.setVisibility(View.VISIBLE);
            isLoadingNetworkPb.setVisibility(View.GONE);

            repoDescription.setText(topic.getDescription());
            repoName.setText(topic.getName());
            repoPrivate.setText(String.valueOf(topic.isPrivate()));
            if (topic.getOwnerName() != null && topic.getOwnerName() != "")
                repoOwner.setText(topic.getOwnerName());
            else if (topic.getOwner() != null)
                repoOwner.setText(topic.getOwner().getLogin());
        } else{
            lineContainer.setVisibility(View.GONE);
            isLoadingNetworkPb.setVisibility(View.VISIBLE);
        }
    }
}
