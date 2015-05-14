package com.stef_developer.dailytask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.stef_developer.dailytask.table_object.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Stefanus Anggara on 04/05/2015.
 */
public class UserDAO extends DailyTaskDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_USER + " =?";

    public UserDAO(Context context) throws SQLException {
        super(context);
    }

    public long insert(User user) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.USER_FULLNAME, user.getFullname());
        values.put(DataBaseHelper.USER_EMAIL, user.getEmail());
        values.put(DataBaseHelper.USER_PASSWORD, user.getPassword());

        return database.insert(DataBaseHelper.USER_TABLE, null, values);
    }

    public long update(User user) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.USER_FULLNAME, user.getFullname());
        values.put(DataBaseHelper.USER_EMAIL, user.getEmail());
        values.put(DataBaseHelper.USER_PASSWORD, user.getPassword());

        long result = database.update(DataBaseHelper.USER_TABLE
                , values
                , WHERE_ID_EQUALS
                , new String[] {String.valueOf(user.getId_user()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(User user) {
        return database.delete(DataBaseHelper.USER_TABLE
                , WHERE_ID_EQUALS
                , new String[] {String.valueOf(user.getId_user()) });
                // apa beda String.valueOf(user.getId_user()) dengan user.getId_user() + "" ?
                // ohh, karena user.getId_user() itu return valuenya Integer
    }

    public boolean cekEmailPasswordByEmail(String email, String password) {
        Cursor cursor = database.query(DataBaseHelper.USER_TABLE    // String table
                , new String[] { DataBaseHelper.USER_EMAIL, DataBaseHelper.USER_PASSWORD }
                , DataBaseHelper.USER_EMAIL + " = ? AND " + DataBaseHelper.USER_PASSWORD + " = ?"
                , new String[] { email, password }
                , null                                  // String groupBy
                , null                                  // String having
                , null);                                // String orderBy

        if(cursor != null && cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // ane ganti ArrayList (sebelumnya List (lihat orginalnya di SQLiteMulti....))
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<User>();
        // http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
        Cursor cursor = database.query(DataBaseHelper.USER_TABLE    // String table
                            , new String[] {DataBaseHelper.ID_USER  // String[] columns
                                    , DataBaseHelper.USER_FULLNAME
                                    , DataBaseHelper.USER_EMAIL
                                    , DataBaseHelper.USER_PASSWORD}
                            , null                                  // String selection
                            , null                                  // String[] selectionArgs
                            , null                                  // String groupBy
                            , null                                  // String having
                            , null);                                // String orderBy

        while (cursor.moveToNext()) {
            User user = new User();
            user.setId_user(cursor.getInt(0));
            user.setFullname(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));

            users.add(user);
        }
        return users;
    }
}
