package com.stef_developer.dailytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.stef_developer.dailytask.table_object.Task;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;



/**
 * Created by Stefanus Anggara on 12/05/2015.
 */
public class TaskDAO extends DailyTaskDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_TASK + " =?";

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    public TaskDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(Task task){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TASK_TITLE, task.getTask_title());
        values.put(DataBaseHelper.TASK_DATETIME, formatter.format(task.getDatetime()));
        values.put(DataBaseHelper.TASK_DETAILS, task.getDetails());

        return database.insert(DataBaseHelper.TASK_TABLE, null, values);
    }

    public long update(Task task){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TASK_TITLE, task.getTask_title());
        values.put(DataBaseHelper.TASK_DATETIME, formatter.format(task.getDatetime()));
        values.put(DataBaseHelper.TASK_DETAILS, task.getDetails());

        long result = database.update(DataBaseHelper.TASK_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] {String.valueOf(task.getId_task()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(Task task){
        return database.delete(DataBaseHelper.TASK_TABLE,
                WHERE_ID_EQUALS,
                new String[] {String.valueOf(task.getId_task()) });
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
        Cursor cursor = database.query(DataBaseHelper.TASK_TABLE,
                new String[] {DataBaseHelper.ID_TASK,
                    DataBaseHelper.TASK_TITLE,
                    DataBaseHelper.TASK_DATETIME,
                    DataBaseHelper.TASK_DETAILS},
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId_task(cursor.getInt(0));
            task.setTask_title(cursor.getString(1));
            try {
                task.setDatetime(formatter.parse(cursor.getString(2)));
            }
            catch (ParseException e) {
                task.setDatetime(null);
            }
            task.setDetails(cursor.getString(3));

            tasks.add(task);
        }
        return tasks;
    }
}
