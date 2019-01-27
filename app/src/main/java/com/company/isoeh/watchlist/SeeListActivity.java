package com.company.isoeh.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.company.isoeh.watchlist.sqliteDb.MovieRepo;

import java.util.ArrayList;
import java.util.HashMap;

public class SeeListActivity extends AppCompatActivity {
    private MovieRepo movieRepo;
    private String user_email;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "a";
    public static final String Email = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_list);
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        user_email = sharedPreferences.getString(Email,"");
        movieRepo = new MovieRepo(this);
        final ArrayList<HashMap<String, String>> movieList = movieRepo.getMovies(user_email);
        ListView lv = (ListView)findViewById(R.id.movielist);
        ListAdapter adapter = new SimpleAdapter(SeeListActivity.this,movieList,R.layout.list_row,new String[]{"name","genre","director"}, new int[]{R.id.rowname,R.id.rowgenre
        ,R.id.rowdirector});

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<HashMap<String, String>> movie = new ArrayList<>();
                String movie_id = movie.get(position).get("id");
                String name = movie.get(position).get("name");
                String genre = movie.get(position).get("genre");
                String director = movie.get(position).get("director");

                Intent i = new Intent(SeeListActivity.this, EditMovieActivity.class);
                i.putExtra("id", movie_id);
                i.putExtra("name", name);
                i.putExtra("genre", genre);
                i.putExtra("director", director);
                startActivity(i);

            }
        });
    }
}
