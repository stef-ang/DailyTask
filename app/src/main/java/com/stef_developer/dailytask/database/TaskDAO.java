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

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_TASK + " = ?";

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);

    public TaskDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(Task task){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TASK_TITLE, task.getTask_title());
        values.put(DataBaseHelper.TASK_DATETIME, formatter.format(task.getDatetime()));
        values.put(DataBaseHelper.TASK_DETAILS, task.getDetails());
        values.put(DataBaseHelper.TASK_STATUS, task.getStatus());

        database = dbHelper.getWritableDatabase();
        return database.insert(DataBaseHelper.TASK_TABLE, null, values);
    }

    public long update(Task task){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TASK_TITLE, task.getTask_title());
        values.put(DataBaseHelper.TASK_DATETIME, formatter.format(task.getDatetime()));
        values.put(DataBaseHelper.TASK_DETAILS, task.getDetails());
        values.put(DataBaseHelper.TASK_STATUS, task.getStatus());

        long result = database.update(DataBaseHelper.TASK_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] {task.getId_task()+""});
        System.out.println("Update Result: = " + result);
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
                    DataBaseHelper.TASK_DETAILS,
                        DataBaseHelper.TASK_STATUS},
                null,
                null,
                null,
                null,
                DataBaseHelper.TASK_DATETIME + " ASC");

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
            task.setStatus(cursor.getInt(4));

            tasks.add(task);
        }
        return tasks;
    }

    public ArrayList<String> getTasksArray() {
        ArrayList<String> tasks = new ArrayList<>();
        // http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
        Cursor cursor = database.query(DataBaseHelper.TASK_TABLE,
                new String[] {DataBaseHelper.TASK_TITLE},
                null,
                null,
                null,
                null,
                DataBaseHelper.TASK_DATETIME + " DESC");

        while (cursor.moveToNext()) {
            tasks.add(cursor.getString(0));
        }
        return tasks;
    }

    public ArrayList<Task> getPrerequisites(int taskId) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String query = "SELECT * FROM " + DataBaseHelper.TASK_TABLE + " WHERE " +
                DataBaseHelper.ID_TASK + " IN (SELECT " + DataBaseHelper.ID_TASK2 +
                " FROM " + DataBaseHelper.TASK_PREREQUISITE_TABLE + " WHERE " + DataBaseHelper.ID_TASK1 + "=" + taskId + ")" ;

        Cursor cursor = database.rawQuery(query, null);

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

    public Task find(int id) {
        Task task = new Task();
        Cursor cursor = database.query(DataBaseHelper.TASK_TABLE,
                new String[]{DataBaseHelper.ID_TASK,
                        DataBaseHelper.TASK_TITLE,
                        DataBaseHelper.TASK_DATETIME,
                        DataBaseHelper.TASK_DETAILS,
                        DataBaseHelper.TASK_STATUS},
                DataBaseHelper.ID_TASK + " = ?",
                new String[]{id + ""},
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            task.setId_task(cursor.getInt(0));
            task.setTask_title(cursor.getString(1));
            try {
                task.setDatetime(formatter.parse(cursor.getString(2)));
            }
            catch (ParseException e) {
                task.setDatetime(null);
            }
            task.setDetails(cursor.getString(3));
            task.setStatus(cursor.getInt(4));
        }
        return task;
    }

    public int findId(String title) {
        String query = "SELECT " + DataBaseHelper.ID_TASK + " FROM " + DataBaseHelper.TASK_TABLE + " WHERE " + DataBaseHelper.TASK_TITLE
                + "='" + title + "'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToNext()) {
            return cursor.getInt(0);
        }
        else return -1;
    }
    public long updateStatus(int id, int status) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.TASK_STATUS, status);

        long result = database.update(DataBaseHelper.TASK_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] {id+""});
        System.out.println("Update Result: = " + result);
        Log.d("Update Result:", "=" + result);
        return result;
    }
}
