package com.company.turntotech.watchlist.sqliteDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.company.turntotech.watchlist.model.User;

public class UserRepo {

    private SQLiteUserHelper sqLiteUserHelper;
    private final static String TABLE_USER = "users";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_USERNAME = "name";
    private final static String COLUMN_EMAIL = "email";
    private final static String COLUMN_PASSWORD = "password";
    private final static String COLUMN_MOBILE = "mobile";
    public UserRepo(Context context){
        sqLiteUserHelper = new SQLiteUserHelper(context);
    }

    public void insert(User user){
        SQLiteDatabase db = sqLiteUserHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_MOBILE, user.getMobile());

        db.insert("users",null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_ID
        };

        SQLiteDatabase db = sqLiteUserHelper.getReadableDatabase();
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0 ){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_ID
        };

        SQLiteDatabase db = sqLiteUserHelper.getReadableDatabase();

        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD  + " = ?";

        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }

        return false;
    }

}
