package com.example.petcarehotel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperAdd extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "AddData.db";
    public static final String TABLE_NAME = "Ad";

    public static final String Col_1 = "ID";
    public static final String Col_2 = "Name";
    public static final String Col_3 = "Owner";
    public static final String Col_4 = "Cage";

    public DatabaseHelperAdd(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    public DatabaseHelperAdd(@Nullable Context context) {
      super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db = this.getWritableDatabase();
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, owner TEXT, cage TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String owner, String cage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2, name);
        contentValues.put(Col_3, owner);
        contentValues.put(Col_4, cage);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
public boolean  UpdateData(String id, String name, String owner, String cage) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(Col_1, id);
    contentValues.put(Col_2, name);
    contentValues.put(Col_3, owner);
    contentValues.put(Col_4, cage);

    db.update(TABLE_NAME, contentValues, "ID = ? ", new String[]{id});
    return true;
}
public Integer deleteData(String id){
    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete(TABLE_NAME,"ID=?",new String[] {id});

}
}
