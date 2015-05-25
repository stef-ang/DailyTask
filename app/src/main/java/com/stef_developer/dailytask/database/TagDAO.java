package com.stef_developer.dailytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.stef_developer.dailytask.table_object.Tag;

import java.sql.SQLException;
import java.util.ArrayList;



/**
 * Created by Stefanus Anggara on 12/05/2015.
 */
public class TagDAO extends DailyTaskDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_TAG + " =?";

    public TagDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(Tag tag){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TAG_ISI, tag.getIsi_tag());

        return database.insert(DataBaseHelper.TAG_TABLE, null, values);
    }

    public long update(Tag tag){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TAG_ISI, tag.getIsi_tag());

        long result = database.update(DataBaseHelper.TAG_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(tag.getId_tag()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(Tag tag){
        return database.delete(DataBaseHelper.TAG_TABLE,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(tag.getId_tag()) });
    }

    public ArrayList<Tag> getTags() {
        ArrayList<Tag> tags = new ArrayList<Tag>();

        Cursor cursor = database.query(DataBaseHelper.TAG_TABLE,
                new String[] { DataBaseHelper.ID_TAG,
                    DataBaseHelper.TAG_ISI},
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Tag tag = new Tag();
            tag.setId_tag(cursor.getInt(0));
            tag.setIsi_tag(cursor.getString(1));

            tags.add(tag);
        }
        return tags;
    }

    public ArrayList<Tag> getTagsByTask(int taskId) {
        ArrayList<Tag> tags = new ArrayList<Tag>();


        //TODO: bikin query untuk nyari tag berdasarkan task, yg di bawah ini salah
        Cursor cursor = database.query(DataBaseHelper.TAG_TABLE,
                new String[] { DataBaseHelper.ID_TAG,
                        DataBaseHelper.TAG_ISI},
                DataBaseHelper.ID_TASK + "=?",
                new String[]{String.valueOf(taskId)},
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Tag tag = new Tag();
            tag.setId_tag(cursor.getInt(0));
            tag.setIsi_tag(cursor.getString(1));

            tags.add(tag);
        }
        return tags;
    }
}
