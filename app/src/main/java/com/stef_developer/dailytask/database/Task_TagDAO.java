package com.stef_developer.dailytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.stef_developer.dailytask.table_object.Task_Tag;

import java.sql.SQLException;

/**
 * Created by Stefanus Anggara on 12/05/2015.
 */
public class Task_TagDAO extends DailyTaskDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_USER + " =?";

    public Task_TagDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(Task_Tag task_tag) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_TASK, task_tag.getId_task());
        values.put(DataBaseHelper.ID_TAG, task_tag.getId_tag());

//        values.put(DataBaseHelper.ID_TASK, task_tag.getTask().getId_task());
//        values.put(DataBaseHelper.ID_TAG, task_tag.getTag().getId_tag());

        return database.insert(DataBaseHelper.TASK_TAG_TABLE, null, values);
    }

    public long update(Task_Tag task_tag) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_TASK, task_tag.getId_task());
        values.put(DataBaseHelper.ID_TAG, task_tag.getId_tag());

//        values.put(DataBaseHelper.ID_TASK, task_tag.getTask().getId_task());
//        values.put(DataBaseHelper.ID_TAG, task_tag.getTag().getId_tag());

        long result = database.update(DataBaseHelper.TASK_TAG_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(task_tag.getId_task_tag()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(Task_Tag task_tag) {
        return database.delete(DataBaseHelper.TASK_TAG_TABLE,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(task_tag.getId_task_tag()) });
    }

    public int clearTags(int taskId) {
        return database.delete(DataBaseHelper.TASK_TAG_TABLE,
                DataBaseHelper.ID_TASK + "=" + taskId,
                null);
    }
}
