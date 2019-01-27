package com.company.isoeh.watchlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.isoeh.watchlist.model.Movie;
import com.company.isoeh.watchlist.sqliteDb.MovieRepo;

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
              boolean stat =  movieRepo.updateMovie(movie, getIntent().getStringExtra("id"));
              if (stat){
                  Toast.makeText(EditMovieActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
              }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieRepo = new MovieRepo(EditMovieActivity.this);
                boolean stat = movieRepo.deleteMovie(getIntent().getStringExtra("id"));
                if (stat){
                    Toast.makeText(EditMovieActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
