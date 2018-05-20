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

public class DBOpenHelper_Room {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite)_Room.db";
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
            db.execSQL(ListViewItem.CreateChatRoom._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ListViewItem.CreateChatRoom._TABLENAME1);
            onCreate(db);
        }
    }

    public DBOpenHelper_Room(Context context){
        this.mCtx = context;
    }

    public DBOpenHelper_Room open() throws SQLException {
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

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(ListViewItem.CreateChatRoom._TABLENAME1, null, null);
    }

    // Insert DB
    public long insertColumn(int userimage, String chatname, String lastwords, String time){
        ContentValues values = new ContentValues();
        values.put(ListViewItem.CreateChatRoom.USERIMAGE, userimage);
        values.put(ListViewItem.CreateChatRoom.CHATNAME, chatname);
        values.put(ListViewItem.CreateChatRoom.LASTWORDS, lastwords);
        values.put(ListViewItem.CreateChatRoom.TIME, time);
        return mDB.insert(ListViewItem.CreateChatRoom._TABLENAME1, null, values);
    }

    // Select DB
    public Cursor selectColumns(){
        return mDB.query(ListViewItem.CreateChatRoom._TABLENAME1, null, null, null, null, null, null);
    }
    // sort by column
    public Cursor sortColumn(String sort) {
        Cursor c = mDB.rawQuery("SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }

}
