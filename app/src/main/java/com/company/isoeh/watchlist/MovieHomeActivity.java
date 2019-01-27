package com.company.isoeh.watchlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;

public class MovieHomeActivity extends AppCompatActivity {

    private CardView addtolist;
    private CardView seelist;
    private Button  logout;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "a";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_home);
        addtolist = (CardView)findViewById(R.id.addtolist);
        seelist = (CardView)findViewById(R.id.seelist);
        logout = (Button)findViewById(R.id.logout);

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent i = new Intent(MovieHomeActivity.this, MainActivity.class);
            startActivity(i);
            finish();

            }
        });
    }
}
