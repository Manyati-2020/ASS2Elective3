package com.example.petcarehotel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Mun.db";
    public static final String TABLE_NAME = "Munya";

    public static final String Col_1 = "ID";
    public static final String Col_2 = "username";
    public static final String Col_3 = "password";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Munya (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public long addUser(String user, String password) {
        SQLiteDatabase BB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = BB.insert("Munya", null, contentValues);
        BB.close();
        return res;
    }

    public boolean checkUser(String username, String password) {

        String[] columns = {Col_1};
        SQLiteDatabase BB = getReadableDatabase();
        String selection = Col_2 + "=?" + " and " + Col_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = BB.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        BB.close();

        if (count > 0)
            return true;
        else
            return
                    false;
    }

}
