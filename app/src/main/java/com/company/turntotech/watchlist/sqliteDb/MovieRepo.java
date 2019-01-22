package com.company.turntotech.watchlist.sqliteDb;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import com.company.turntotech.watchlist.model.Movie;

public class MovieRepo {
    private SQLiteMovieHelper sqLiteMovieHelper;

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
    }
}
