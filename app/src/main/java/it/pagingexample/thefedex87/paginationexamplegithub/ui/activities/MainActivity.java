package it.pagingexample.thefedex87.paginationexamplegithub.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import it.pagingexample.thefedex87.paginationexamplegithub.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openActivityWithDbPaging(View view){
        Intent intent = new Intent(this, PagingFromDbActivity.class);
        startActivity(intent);
    }

    public void openActivityWithNetworkPaging(View view){
        Intent intent = new Intent(this, PagingFromNetworkActivity.class);
        startActivity(intent);
    }
}
