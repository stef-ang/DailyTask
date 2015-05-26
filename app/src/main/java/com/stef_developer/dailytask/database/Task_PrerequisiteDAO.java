package com.stef_developer.dailytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.stef_developer.dailytask.table_object.Task_Prerequisite;

import java.sql.SQLException;



/**
 * Created by Stefanus Anggara on 12/05/2015.
 */
public class Task_PrerequisiteDAO extends DailyTaskDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_USER_TASK + " =?";

    public Task_PrerequisiteDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(Task_Prerequisite task_prerequisite) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_TASK1, task_prerequisite.getId_task1());
        values.put(DataBaseHelper.ID_TASK2, task_prerequisite.getId_task2());

//        values.put(DataBaseHelper.ID_TASK1, task_prerequisite.getTask1().getId_task());
//        values.put(DataBaseHelper.ID_TASK2, task_prerequisite.getTask2().getId_task());

        return database.insert(DataBaseHelper.TASK_PREREQUISITE_TABLE,
                null, values);
    }

    public long update(Task_Prerequisite task_prerequisite) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_TASK1, task_prerequisite.getId_task1());
        values.put(DataBaseHelper.ID_TASK2, task_prerequisite.getId_task2());

//        values.put(DataBaseHelper.ID_TASK1, task_prerequisite.getTask1().getId_task());
//        values.put(DataBaseHelper.ID_TASK2, task_prerequisite.getTask2().getId_task());

        long result = database.update(DataBaseHelper.TASK_PREREQUISITE_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(task_prerequisite.getId_task_prerequisite()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(Task_Prerequisite task_prerequisite) {
        return database.delete(DataBaseHelper.TASK_PREREQUISITE_TABLE,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(task_prerequisite.getId_task_prerequisite()) });
    }

    public int clearPrerequisites(int taskId) {
        return database.delete(DataBaseHelper.TASK_PREREQUISITE_TABLE,
                DataBaseHelper.ID_TASK1 + "=" + taskId,
                null);
    }

}
