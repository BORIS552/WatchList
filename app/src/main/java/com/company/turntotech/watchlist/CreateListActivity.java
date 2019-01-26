package com.company.turntotech.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.turntotech.watchlist.model.Movie;
import com.company.turntotech.watchlist.sqliteDb.MovieRepo;

public class CreateListActivity extends AppCompatActivity {

    private EditText moviename;
    private EditText genre;
    private EditText director;
    private Button add;
    private String user_email;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "a";
    public static final String Email = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        user_email = sharedPreferences.getString(Email,"");

        moviename = (EditText)findViewById(R.id.moviename);
        genre = (EditText)findViewById(R.id.genre);
        director = (EditText)findViewById(R.id.director);
        add = (Button)findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (moviename.getText().toString().trim().isEmpty()){
                    Toast.makeText(CreateListActivity.this,"Enter Movie name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (genre.getText().toString().trim().isEmpty()){
                    Toast.makeText(CreateListActivity.this, "Enter Genre!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (director.getText().toString().isEmpty()){
                    Toast.makeText(CreateListActivity.this,"Enter Director Name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                postData();
            }
        });

    }

    private void postData(){
        MovieRepo movieRepo = new MovieRepo(this);
        Movie movie = new Movie();
        movie.setName(moviename.getText().toString().trim());
        movie.setGenre(genre.getText().toString().trim());
        movie.setDirector(director.getText().toString().trim());
        movieRepo.insert(movie,user_email);
        Toast.makeText(this,"Movie Listed",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CreateListActivity.this, MovieHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
