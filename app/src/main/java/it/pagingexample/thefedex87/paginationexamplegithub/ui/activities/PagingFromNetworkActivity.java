package it.pagingexample.thefedex87.paginationexamplegithub.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.pagingexample.thefedex87.paginationexamplegithub.R;
import it.pagingexample.thefedex87.paginationexamplegithub.ui.adapters.TopicsAdapter;
import it.pagingexample.thefedex87.paginationexamplegithub.ui.viewmodels.PagingWithDbViewModel;
import it.pagingexample.thefedex87.paginationexamplegithub.ui.viewmodels.PagingWithNetworkViewModel;

public class PagingFromNetworkActivity extends AppCompatActivity {
    @BindView(R.id.topic_list)
    RecyclerView topicsRv;

    private TopicsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_from_network);

        adapter = new TopicsAdapter();

        ButterKnife.bind(this);
        topicsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        topicsRv.setAdapter(adapter);

        setupViewModel();
    }

    private void setupViewModel() {
        PagingWithNetworkViewModel viewModel = ViewModelProviders.of(this).get(PagingWithNetworkViewModel.class);
        viewModel.getTopicsList().observe(this, adapter::submitList);
        viewModel.getIsLoadingNetwork().observe(this, networkState -> adapter.setIsLoadingNetwork(networkState));
    }
}
