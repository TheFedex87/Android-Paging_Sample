package it.pagingexample.thefedex87.paginationexamplegithub.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import it.pagingexample.thefedex87.paginationexamplegithub.Constants;
import it.pagingexample.thefedex87.paginationexamplegithub.data.AppDatabase;
import it.pagingexample.thefedex87.paginationexamplegithub.data.RepoBoundaryCallBack;
import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;

public class PagingWithDbViewModel extends AndroidViewModel {
    private LiveData<PagedList<Topic>> topicsList;

    public PagingWithDbViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        topicsList = new LivePagedListBuilder<>(db.topicDao().allTopics(), new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.ELEMENT_PER_PAGE)
                .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
                .build())
            .setBoundaryCallback(new RepoBoundaryCallBack(application.getApplicationContext()))
            .build();
    }

    public LiveData<PagedList<Topic>> getTopicsList() {
        return topicsList;
    }
}
