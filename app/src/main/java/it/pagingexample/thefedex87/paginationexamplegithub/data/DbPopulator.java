package it.pagingexample.thefedex87.paginationexamplegithub.data;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.Executors;

import it.pagingexample.thefedex87.paginationexamplegithub.Constants;
import it.pagingexample.thefedex87.paginationexamplegithub.retrofit.RetrofitResponse;
import it.pagingexample.thefedex87.paginationexamplegithub.retrofit.TopicsApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class DbPopulator {


    public static void populateDb(Context context){
        TopicsApiInterface topicsApiInterface = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build()
                .create(TopicsApiInterface.class);

        final AppDatabase db = AppDatabase.getInstance(context);

        topicsApiInterface.topics(Constants.SEARCH_ARGUMENT).enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, final Response<RetrofitResponse> response) {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        Topic[] topics = new Topic[response.body().getItems().size()];
                        topics = response.body().getItems().toArray(topics);
                        for(Topic topic : topics){
                            topic.setOwnerName(topic.getOwner().getLogin());
                        }
                        db.topicDao().insert(topics);
                    }
                });
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {

            }
        });
    }
}
