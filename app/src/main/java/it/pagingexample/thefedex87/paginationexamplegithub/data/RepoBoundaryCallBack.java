package it.pagingexample.thefedex87.paginationexamplegithub.data;

import android.arch.paging.PagedList.BoundaryCallback;
import android.content.Context;
import android.support.annotation.NonNull;

public class RepoBoundaryCallBack extends BoundaryCallback<Topic> {
    private final Context context;

    public RepoBoundaryCallBack(Context context){
        this.context = context;
    }

    @Override
    public void onZeroItemsLoaded() {
        DbPopulator.populateDb(context);
    }
}
