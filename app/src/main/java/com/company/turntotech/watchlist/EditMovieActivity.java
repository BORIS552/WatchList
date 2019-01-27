package com.company.turntotech.watchlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.company.turntotech.watchlist.model.Movie;
import com.company.turntotech.watchlist.sqliteDb.MovieRepo;

public class EditMovieActivity extends AppCompatActivity {

    private Button update;
    private Button delete;
    private EditText name;
    private EditText genre;
    private EditText director;
    private MovieRepo movieRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        name = (EditText)findViewById(R.id.editName);
        genre = (EditText)findViewById(R.id.editGenre);
        director = (EditText)findViewById(R.id.editDirector);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);

        name.setText(getIntent().getStringExtra("name"));
        genre.setText(getIntent().getStringExtra("genre"));
        director.setText(getIntent().getStringExtra("director"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_str = name.getText().toString();
                String genre_str = genre.getText().toString();
                String director_str = director.getText().toString();
                Movie movie = new Movie();
                movie.setName(name_str);
                movie.setGenre(genre_str);
                movie.setDirector(director_str);
                movieRepo = new MovieRepo(EditMovieActivity.this);
                movieRepo.updateMovie(movie, getIntent().getStringExtra("id"));
            }
        });
    }
}
