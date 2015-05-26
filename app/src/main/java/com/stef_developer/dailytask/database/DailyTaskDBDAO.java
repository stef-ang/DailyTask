package com.stef_developer.dailytask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Stefanus Anggara on 04/05/2015.
 */
// done
public class DailyTaskDBDAO {

    protected SQLiteDatabase database;
    protected DataBaseHelper dbHelper;
    private Context mContext;

    public DailyTaskDBDAO(Context context) throws SQLException {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException{
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }
}
