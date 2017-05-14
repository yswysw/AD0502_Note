package com.example.ad0502_note.dbutils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by yls on 2017/5/4.
 */

public class NoteContentProvider extends ContentProvider {
    private NoteOpenHelper mHelper;
    private static String authorities = "com.example.ad0502_note";
    public static final Uri NOTE_URI = Uri.parse("content://com.example.ad0502_note/note");
    private static final UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sMatcher.addURI(authorities, TableNote.TABLE_NAME, 1);
    }

    @Override
    public boolean onCreate() {
        mHelper = NoteOpenHelper.getInstance(getContext());
        return false;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (sMatcher.match(uri)) {
            case 1:
                SQLiteDatabase db = mHelper.getWritableDatabase();
                db.insert(TableNote.TABLE_NAME, null, values);
//                db.close();
                return Uri.withAppendedPath(NOTE_URI, values.getAsString(TableNote.COL_ID));
            default:

                return null;
        }
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        switch (sMatcher.match(uri)) {
            case 1:
                db.delete(TableNote.TABLE_NAME, selection, null);//arg1是要删除的条件是MainActivity传过来。
                break;

            default:
                break;
        }
        //一行都没删除返回0.
        return 0;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        switch (sMatcher.match(uri)) {
            case 1:
                db.update(TableNote.TABLE_NAME, values, selection, null);
                break;

            default:
                break;
        }
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (sMatcher.match(uri)) {
            case 1:
                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor c = db.query(TableNote.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
//                db.close();
                return c;
            case 2:
                break;


        }


        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }




}
