package com.kun.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionRes;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    List<Person> personList = new ArrayList<Person>();

    @ViewById(R.id.t_data)
    TextView t_data;

    @ViewById(R.id.l_data)
    ListView l_data;

    @Click(R.id.btn_insert)
    public void insert() {
        PersonDBHelper dbHelper = new PersonDBHelper(MainActivity.this, "person_db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < 10; i++) {
            contentValues.put("name", "pyk" + i);
            contentValues.put("age", i + 10);
            db.insert("person", null, contentValues);
        }

        db.close();
        t_data.setText("插入成功");
    }

    @Click(R.id.btn_query)
    public void query() {
        PersonDBHelper dbHelper = new PersonDBHelper(MainActivity.this, "person_db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //Cursor cursor = db.query("person",new String[]{"id","name","age"},"id=?",new String[]{String.valueOf(1)},null,null,null);
        Cursor cursor = db.rawQuery("select id,name,age from person where id<10", null);
        while (cursor.moveToNext()) {
            Person p = new Person();
            p.setId(cursor.getInt(cursor.getColumnIndex("id")));
            p.setName(cursor.getString(cursor.getColumnIndex("name")));
            p.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            personList.add(p);
        }
        Log.i(">>>", "person size:" + personList.size());
        cursor.close();
        db.close();
    }

    @Click(R.id.btn_update)
    public void update() {
        PersonDBHelper dbHelper = new PersonDBHelper(MainActivity.this, "person_db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", 10);
        db.update("person", contentValues, "id=?", new String[]{String.valueOf(1)});
        t_data.setText("更新数据");
        db.close();
    }

    @Click(R.id.btn_delete)
    public void delete() {
        PersonDBHelper dbHelper = new PersonDBHelper(MainActivity.this, "person_db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("person", "id=?", new String[]{String.valueOf(1)});
        t_data.setText("删除数据");
    }


}
