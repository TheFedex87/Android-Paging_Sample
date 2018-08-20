package it.pagingexample.thefedex87.paginationexamplegithub.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import it.pagingexample.thefedex87.paginationexamplegithub.data.Topic;

public class RetrofitResponse {
    @SerializedName("total_count")
    private long totalCount;
    private List<Topic> items;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Topic> getItems() {
        return items;
    }

    public void setItems(List<Topic> items) {
        this.items = items;
    }
}
