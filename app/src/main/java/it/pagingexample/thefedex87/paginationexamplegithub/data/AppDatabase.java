package it.pagingexample.thefedex87.paginationexamplegithub.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Topic.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "GITHUB_PAGING_TEST_DB";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return instance;
    }

    public abstract TopicDao topicDao();
}
