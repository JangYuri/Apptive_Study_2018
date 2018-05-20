package com.example.yuri.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuri on 2018. 5. 20..
 */

public class DBOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DataBaseHelper mDBHelper;
    private Context mCtx;

    public class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ListViewItem.CreateFriendsList._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ListViewItem.CreateFriendsList._TABLENAME0);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context){
        this.mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DataBaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(long userimage, String name, String message){
        ContentValues values = new ContentValues();
        values.put(ListViewItem.CreateFriendsList.USERIMAGE, userimage);
        values.put(ListViewItem.CreateFriendsList.NAME, name);
        values.put(ListViewItem.CreateFriendsList.MESSAGE, message);
        return mDB.insert(ListViewItem.CreateFriendsList._TABLENAME0, null, values);
    }

    // Select DB
    public Cursor selectColumns(){
        return mDB.query(ListViewItem.CreateFriendsList._TABLENAME0, null, null, null, null, null, null);
    }
    // sort by column
    public Cursor sortColumn(String sort) {
        Cursor c = mDB.rawQuery("SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }

}
