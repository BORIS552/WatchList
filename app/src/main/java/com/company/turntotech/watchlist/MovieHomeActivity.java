package com.company.turntotech.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MovieHomeActivity extends AppCompatActivity {

    private CardView addtolist;
    private CardView seelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_home);
        addtolist = (CardView)findViewById(R.id.addtolist);
        seelist = (CardView)findViewById(R.id.seelist);

        addtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieHomeActivity.this, CreateListActivity.class);
                startActivity(i);
            }
        });


        seelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieHomeActivity.this, SeeListActivity.class);
                startActivity(i);
            }
        });
    }
}
