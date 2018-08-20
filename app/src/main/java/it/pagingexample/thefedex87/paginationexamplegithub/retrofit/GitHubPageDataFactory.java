package it.pagingexample.thefedex87.paginationexamplegithub.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class GitHubPageDataFactory extends DataSource.Factory {

    private MutableLiveData<GitHubPageDataSource> mutableLiveData;

    private GitHubPageDataSource gitHubPageDataSource;

    public GitHubPageDataFactory(){
        mutableLiveData = new MutableLiveData<GitHubPageDataSource>();
    }

    @Override
    public DataSource create() {
        gitHubPageDataSource = new GitHubPageDataSource();
        mutableLiveData.postValue(gitHubPageDataSource);
        return gitHubPageDataSource;
    }

    public MutableLiveData<GitHubPageDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
