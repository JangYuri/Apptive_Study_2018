package com.example.yuri.myapplication;

import android.provider.BaseColumns;

/**
 * Created by yuri on 2018. 4. 28..
 */

public class ListViewItem {
    int image;
    String name, message;
    String chatName, lastWords, time;

    public ListViewItem(int image, String name, String message) {
        this.image = image;
        this.name = name;
        this.message = message;
    }

    public ListViewItem(int image, String chatName, String lastWords, String time) {
        this.image = image;
        this.chatName = chatName;
        this.lastWords = lastWords;
        this.time = time;
    }

    public int getImage(){return image;}
    public String getName(){return name;}
    public String getMessage( ){return message;}


    public String getChatName( ){return chatName;}
    public String getLastWords( ){return lastWords;}
    public String getTime( ){return time;}

    public static final class CreateFriendsList implements BaseColumns {
        public static final String USERIMAGE = "userimage";
        public static final String NAME = "name";
        public static final String MESSAGE = "message";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +USERIMAGE+" integer not null , "
                +NAME+" text not null , "
                +MESSAGE+" text not null );";
    }
    public static final class CreateChatRoom implements BaseColumns {
        public static final String USERIMAGE = "userimage";
        public static final String CHATNAME = "chatname";
        public static final String LASTWORDS = "lastwords";
        public static final String TIME = "time";
        public static final String _TABLENAME1 = "usertable";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +USERIMAGE+" integer not null , "
                + CHATNAME +" text not null , "
                +LASTWORDS+" text not null , "
                +TIME+" integer not null );";
    }
}
