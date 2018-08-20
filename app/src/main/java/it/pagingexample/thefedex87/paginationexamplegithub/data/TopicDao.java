package it.pagingexample.thefedex87.paginationexamplegithub.data;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface TopicDao {
    @Query("SELECT * FROM topics")
    DataSource.Factory<Integer, Topic> allTopics();

    @Insert
    void insert(Topic... topics);
}
