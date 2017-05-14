package com.example.ad0502_note.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by yls on 2017/5/4.
 */

public class NoteDBUtil {
    static ArrayList<Note> noteList = new ArrayList<>();

    //调用内容提供者的插入方法。
    public static boolean addNote(Context context, String title, String type, String content, String createdate) {
        ContentValues values = new ContentValues();

        values.put(TableNote.COL_TITLE, title);
        values.put(TableNote.COL_TYPE, type);
        values.put(TableNote.COL_CONTENT, content);
        values.put(TableNote.COL_CREATE_DATE, createdate);

        context.getContentResolver().insert(NoteContentProvider.NOTE_URI, values);

        return true;
    }

    //调用内容提供者的更新方法。
    public static ArrayList<Note> getNoteList(Context context) {
        String[] projection = {TableNote.COL_ID, TableNote.COL_TITLE, TableNote.COL_TYPE, TableNote.COL_CONTENT, TableNote.COL_CREATE_DATE, TableNote.COL_MODIFY_DATE, TableNote.COL_IS_DEL};
        Cursor c = context.getContentResolver().query(NoteContentProvider.NOTE_URI, projection, null, null, null);
        noteList.clear();

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            Note note = new Note();

            if (c.getInt(6) == 1) {

                continue;

            } else {

                note.setId(c.getInt(0));
                note.setTitle(c.getString(1));
                note.setType(c.getString(2));
                note.setContent(c.getString(3));
                note.setCreateDate(c.getString(4));
                note.setModifyDate(c.getString(5));
                note.setIsDel(c.getInt(6));
                noteList.add(note);
            }
        }
        c.close();
        return noteList;
    }

    //调用内容提供者的更新方法。--------》改变字段isDel的值：0------》1。
    public static boolean Update(Context context, int id) {
        ContentValues values = new ContentValues();

        values.put(TableNote.COL_IS_DEL, 1);
        context.getContentResolver().update(NoteContentProvider.NOTE_URI, values, "id="+id,null );

        return true;
    }

    //调用内容提供者的更新方法。--------》改变字段modifydate的值：null------》modifydate。在ListView
    //的适配器中判断是否存在更改的时间，存在就让TextView显示字段modifydate的时间值。
    public static boolean Change_message(Context context, int id, String title, String type,
                                         String content,String modifydate) {
        ContentValues values = new ContentValues();

        values.put(TableNote.COL_TITLE, title);
        values.put(TableNote.COL_TYPE, type);
        values.put(TableNote.COL_CONTENT, content);
        values.put(TableNote.COL_MODIFY_DATE, modifydate);
        context.getContentResolver().update(NoteContentProvider.NOTE_URI, values, "id="+id,null );

        return true;
    }

    //删除操作的方法未实现，因为ListView中的滑动删除实现了删除动态数组中的数据，但数据库保存数据痕迹。

}
