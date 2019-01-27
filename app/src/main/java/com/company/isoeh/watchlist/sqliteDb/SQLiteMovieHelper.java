package com.company.isoeh.watchlist.sqliteDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteMovieHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "movie.db";

    public SQLiteMovieHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_MOVIE_TABLE = "CREATE TABLE movies" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT, email TEXT, genre TEXT, director TEXT ) ";

        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS movies";
        db.execSQL(sql);

        onCreate(db);
    }


}
