package it.pagingexample.thefedex87.paginationexamplegithub.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import it.pagingexample.thefedex87.paginationexamplegithub.Constants;
import it.pagingexample.thefedex87.paginationexamplegithub.NetworkState;
import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubPageDataSource extends PageKeyedDataSource<Integer, Topic> {
    private MutableLiveData<NetworkState> isLoadingNetwork;

    public GitHubPageDataSource(){
        isLoadingNetwork = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Topic> callback) {
        isLoadingNetwork.postValue(NetworkState.LOADING);

        TopicsApiInterface topicsApiInterface = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(TopicsApiInterface.class);

        topicsApiInterface.pagedTopics(Constants.SEARCH_ARGUMENT, 1, Constants.ELEMENT_PER_PAGE).enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                callback.onResult(response.body().getItems(), null, 2);
                isLoadingNetwork.postValue(NetworkState.LOADED);
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                isLoadingNetwork.postValue(NetworkState.FAILED);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Topic> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Topic> callback) {
        isLoadingNetwork.postValue(NetworkState.LOADING);

        TopicsApiInterface topicsApiInterface = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(TopicsApiInterface.class);

        topicsApiInterface.pagedTopics(Constants.SEARCH_ARGUMENT, params.key, Constants.ELEMENT_PER_PAGE).enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                if (response.isSuccessful()) {
                    int nextKey = (params.key == response.body().getTotalCount()) ? null : params.key + 1;
                    callback.onResult(response.body().getItems(), nextKey);
                    isLoadingNetwork.postValue(NetworkState.LOADED);
                } else {
                    isLoadingNetwork.postValue(NetworkState.FAILED);
                }
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                isLoadingNetwork.postValue(NetworkState.FAILED);
            }
        });
    }

    public MutableLiveData<NetworkState> getIsLoadingNetwork() {
        return isLoadingNetwork;
    }
}
