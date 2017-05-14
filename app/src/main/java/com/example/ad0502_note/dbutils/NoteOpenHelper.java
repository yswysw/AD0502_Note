package com.example.ad0502_note.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yls on 2017/5/4.
 * mysql> create table if not exists note(
 -> id integer not null primary key auto_increment,
 -> title varchar(40),
 -> type varchar(40),
 -> content varchar(1000),
 -> creaetime varchar(40),
 -> modifytime varchar(40) default null,
 -> isdel int default 0
 -> );
 */

public class NoteOpenHelper extends SQLiteOpenHelper{
    private  static NoteOpenHelper mInstance;
    private  final  String CREATE_TABLE_NOTE = "create table if not exists note(id integer not null primary key AUTOINCREMENT,title varchar(40),type varchar(40),content varchar(1000),createdate varchar(40),modifydate varchar(40) default null,isdel int default 0);";

    private NoteOpenHelper(Context context){
        super(context, "note.db", null,  1);
    }

    public static NoteOpenHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new NoteOpenHelper(context);
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            if(oldVersion == 1 && newVersion ==3){
                // alter tabble note add year date, health, weather varchar(10);
            }

            if(oldVersion == 2 && newVersion ==3){
                // alter tabble note add weather varchar(10);
            }

    }
}
