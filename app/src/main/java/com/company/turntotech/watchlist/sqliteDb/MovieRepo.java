package com.company.turntotech.watchlist.sqliteDb;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.company.turntotech.watchlist.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieRepo {
    private SQLiteMovieHelper sqLiteMovieHelper;

    public static final String TABLE_NAME = "movies";
    public static final String Email = "email";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_DIRECTOR = "director";
    private static final String COLUMN_EMAIL = "email";
    public MovieRepo(Context context){
        sqLiteMovieHelper = new SQLiteMovieHelper(context);
    }

    public void insert(Movie movie,String user_email){
        SQLiteDatabase db = sqLiteMovieHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,movie.getName());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_DIRECTOR, movie.getDirector());
        values.put(COLUMN_EMAIL, user_email);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getMovies(String email){
        SQLiteDatabase db = sqLiteMovieHelper.getWritableDatabase();
        ArrayList<HashMap<String, String>> movieList = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE email = ?";
        Cursor cursor =db.rawQuery(query, new String[]{email});
        System.out.print("-------------------------   " + cursor.getCount()+"   ----------------");
        while (cursor.moveToNext()){
            HashMap<String, String> movie = new HashMap<>();
            movie.put("id",cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            movie.put("name",cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            movie.put("genre",cursor.getString(cursor.getColumnIndex(COLUMN_GENRE)));
            movie.put("director", cursor.getString(cursor.getColumnIndex(COLUMN_DIRECTOR)));
            movieList.add(movie);
        }
        return movieList;
    }

    public void updateMovie(Movie movie, String id){
        SQLiteDatabase db = sqLiteMovieHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,movie.getName());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_DIRECTOR, movie.getDirector());

    }
}
