package com.kun.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Crazy-kun on 2017/7/30.
 */
public class PersonDBHelper extends SQLiteOpenHelper {

    public PersonDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS person");
        Log.i(">>>","create Database------------->");
        String sql = "create table person(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(20),age int)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(">>>", "update Database------------->");
    }
}
