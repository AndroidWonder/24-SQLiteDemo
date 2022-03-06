package com.course.example.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.TextView;
import android.content.*;

public class SQLiteDemo extends Activity {

    private TextView text;
    private SQLiteDatabase db;
    private ContentValues values;
    private Cursor cursor;

    private final String create = Constants.CREATE_TABLE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        text = (TextView) findViewById(R.id.Text);

        // create database and table
        db = openOrCreateDatabase(Constants.DATABASE_NAME,
                Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        db.execSQL(create);

        // insert records
        values = new ContentValues();
        values.put(Constants.KEY_NAME, "tiger");
        values.put(Constants.KEY_Q, 4);
        db.insert(Constants.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(Constants.KEY_NAME, "zebra");
        values.put(Constants.KEY_Q, 23);
        db.insert(Constants.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(Constants.KEY_NAME, "buffalo");
        values.put(Constants.KEY_Q, 13);
        db.insert(Constants.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(Constants.KEY_NAME, "lion");
        values.put(Constants.KEY_Q, 37);
        db.insert(Constants.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(Constants.KEY_NAME, "yak");
        values.put(Constants.KEY_Q, 18);
        db.insert(Constants.TABLE_NAME, null, values);

        // update buffalo to gorilla
        values = new ContentValues();
        values.put(Constants.KEY_NAME, "gorilla");
        db.update(Constants.TABLE_NAME, values, Constants.KEY_NAME + "=?",
                new String[]{"buffalo"});

        // delete record
        db.delete(Constants.TABLE_NAME, Constants.KEY_NAME + "=?",
                new String[]{"tiger"});

        //insert a record with SQL
        db.execSQL("INSERT INTO animals (name, Quantity) VALUES('dragons',99);");

        // query table and sort result set by name
        cursor = db.query(Constants.TABLE_NAME, new String[]{
                        Constants.KEY_NAME, Constants.KEY_Q}, null, null, null, null,
                "name" + " DESC");

        // write contents of Cursor to screen
        while (cursor.moveToNext()) {
            String str = cursor.getString(cursor
                    .getColumnIndex(Constants.KEY_NAME));
            int count = cursor.getInt(cursor.getColumnIndex(Constants.KEY_Q));
            text.append(str + " " + Integer.toString(count) + "\n");
        }
        ;
    }

    // close database
    @Override
    protected void onPause() {
        super.onPause();
        if (db != null)
            db.close();
    }
}