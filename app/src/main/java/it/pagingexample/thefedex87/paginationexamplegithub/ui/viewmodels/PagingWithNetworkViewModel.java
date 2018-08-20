package it.pagingexample.thefedex87.paginationexamplegithub.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;

import it.pagingexample.thefedex87.paginationexamplegithub.Constants;
import it.pagingexample.thefedex87.paginationexamplegithub.NetworkState;
import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;
import it.pagingexample.thefedex87.paginationexamplegithub.retrofit.GitHubPageDataFactory;

public class PagingWithNetworkViewModel extends AndroidViewModel {
    private final Context context;
    private final LiveData<PagedList<Topic>> topicsList;
    private LiveData<NetworkState> networkState;

    public PagingWithNetworkViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

        GitHubPageDataFactory dataSourceFactory = new GitHubPageDataFactory();

        networkState = Transformations.switchMap(dataSourceFactory.getMutableLiveData(), dataSource -> dataSource.getIsLoadingNetwork());

        topicsList = new LivePagedListBuilder<>(dataSourceFactory, new PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(Constants.ELEMENT_PER_PAGE)
            .setPrefetchDistance(Constants.PREFETCH_DISTANCE).build()).build();

    }

    public LiveData<NetworkState> getIsLoadingNetwork(){
        return networkState;
    }
    public LiveData<PagedList<Topic>> getTopicsList() {
        return topicsList;
    }
}
