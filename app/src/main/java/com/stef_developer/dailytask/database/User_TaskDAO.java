package com.stef_developer.dailytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.stef_developer.dailytask.table_object.User_Task;

import java.sql.SQLException;

/**
 * Created by Stefanus Anggara on 12/05/2015.
 */
public class User_TaskDAO extends DailyTaskDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_USER_TASK + " =?";

    public User_TaskDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(User_Task user_task){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_USER, user_task.getId_user());
        values.put(DataBaseHelper.ID_TASK, user_task.getId_task());

//        values.put(DataBaseHelper.ID_USER, user_task.getUser().getId_user());
//        values.put(DataBaseHelper.ID_TASK, user_task.getTask().getId_task());

        return database.insert(DataBaseHelper.USER_TASK_TABLE, null, values);
    }

    public long update(User_Task user_task){
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.ID_USER, user_task.getId_user());
        values.put(DataBaseHelper.ID_TASK, user_task.getId_task());

//        values.put(DataBaseHelper.ID_USER, user_task.getUser().getId_user());
//        values.put(DataBaseHelper.ID_TASK, user_task.getTask().getId_task());

        long result = database.update(DataBaseHelper.USER_TASK_TABLE,
                values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(user_task.getId_user_task()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(User_Task user_task) {
        return database.delete(DataBaseHelper.USER_TASK_TABLE,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(user_task.getId_user_task()) });
    }
}
