package com.stef_developer.dailytask.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stefanus Anggara on 04/05/2015.
 */
// done
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dailytaskdb";
    public static final int DATABASE_VERSION = 1;

    public static final String USER_TABLE = "user";
    public static final String USER_TASK_TABLE = "user_task";
    public static final String TASK_TABLE = "task";
    public static final String TASK_PREREQUISITE_TABLE = "task_prerequisite";
    public static final String TASK_TAG_TABLE = "task_tag";
    public static final String TAG_TABLE = "tag";

    // user attributes
    public static final String ID_USER = "id_user";
    public static final String USER_FULLNAME = "fullname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";

    // task attributes
    public static final String ID_TASK = "id_task";
    public static final String TASK_TITLE = "task_title";
    public static final String TASK_DATETIME = "datetime";
    public static final String TASK_DETAILS = "details";

    // tag attributes
    public static final String ID_TAG = "id_tag";
    public static final String TAG_ISI = "isi_tag";

    // user_task attribute
    public static final String ID_USER_TASK = "id_user_task";

    // task_tag attribute
    public static final String ID_TASK_TAG = "id_task_tag";

    // task_prerequisite attributes
    public static final String ID_TASK_PRERE = "id_task_prerequisite";
    public static final String ID_TASK1 = "id_task1";
    public static final String ID_TASK2 = "id_task2";

    // create all tables
    public static final String CREATE_USER_TABLE = "CREATE TABLE "
            + USER_TABLE + "(" + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_FULLNAME + " VARCHAR(255),"
            + USER_EMAIL + " VARCHAR(255),"
            + USER_PASSWORD + " VARCHAR(255)" + ")";

    public static final String CREATE_TASK_TABLE = "CREATE TABLE "
            + TASK_TABLE + "(" + ID_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TASK_TITLE + " VARCHAR(255),"
            + TASK_DATETIME + " DATETIME,"
            + TASK_DETAILS + " TEXT" + ")";

    public static final String CREATE_USER_TASK_TABLE = "CREATE TABLE "
            + USER_TASK_TABLE + "(" + ID_USER_TASK + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_USER + " INTEGER,"
            + ID_TASK + " INTEGER,"
            + " FOREIGN KEY(" + ID_USER + ") REFERENCES " + USER_TABLE + "(" + ID_USER + "),"
            + " FOREIGN KEY(" + ID_TASK + ") REFERENCES " + TASK_TABLE + "(" + ID_TASK + "))";

    public static final String CREATE_TASK_PREREQUISITE_TABLE = "CREATE TABLE "
            + TASK_PREREQUISITE_TABLE + "(" + ID_TASK_PRERE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_TASK1 + " INTEGER,"
            + ID_TASK2 + " INTEGER,"
            + " FOREIGN KEY(" + ID_TASK1 + ") REFERENCES " + TASK_TABLE + "(" + ID_TASK + "),"
            + " FOREIGN KEY(" + ID_TASK2 + ") REFERENCES " + TASK_TABLE + "(" + ID_TASK + "))";

    public static final String CREATE_TAG_TABLE = "CREATE TABLE "
            + TAG_TABLE + "(" + ID_TAG + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TAG_ISI + " VARCHAR(255)" + ")";

    public static final String CREATE_TASK_TAG_TABLE = "CREATE TABLE "
            + TASK_TAG_TABLE + "(" + ID_TASK_TAG + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ID_TASK + " INTEGER,"
            + ID_TAG + " INTEGER,"
            + " FOREIGN KEY(" + ID_TASK + ") REFERENCES " + TASK_TABLE + "(" + ID_TASK + "),"
            + " FOREIGN KEY(" + ID_TAG + ") REFERENCES " + TAG_TABLE + "(" + ID_TAG + "))";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context){
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            // enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
        db.execSQL(CREATE_USER_TASK_TABLE);
        db.execSQL(CREATE_TASK_PREREQUISITE_TABLE);
        db.execSQL(CREATE_TAG_TABLE);
        db.execSQL(CREATE_TASK_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
