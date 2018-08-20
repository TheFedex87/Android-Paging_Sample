package it.pagingexample.thefedex87.paginationexamplegithub.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopicsApiInterface {
    @GET("search/repositories?&access_token=6a35a0c269a3f583fe1d5a473d58058794732340")
    Call<RetrofitResponse> pagedTopics(@Query("q") String argument, @Query("page") int page, @Query("per_page") int elementPerPage);

    @GET("search/repositories?page=1&per_page=100")
    Call<RetrofitResponse> topics(@Query("q") String argument);
}
