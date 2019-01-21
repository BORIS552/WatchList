package com.company.turntotech.watchlist.sqliteDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteUserHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    public SQLiteUserHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE users" +
                                   "(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                   "name TEXT, email TEXT, password TEXT, mobile TEXT ) ";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS users";
        db.execSQL(sql);

        onCreate(db);
    }


}
