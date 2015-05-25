package com.stef_developer.dailytask.database;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.stef_developer.dailytask.table_object.Tag;
import com.stef_developer.dailytask.table_object.Task;
import com.stef_developer.dailytask.table_object.Task_Prerequisite;
import com.stef_developer.dailytask.table_object.Task_Tag;
import com.stef_developer.dailytask.table_object.User;

import java.text.SimpleDateFormat;

/**
 * Created by tsaqova on 5/25/15.
 */
public class DatabaseSeeder {

    private Activity activity;

    public DatabaseSeeder(Activity activity) {
        this.activity = activity;
    }

    public void seed() {
        try {
            this.activity.deleteDatabase(DataBaseHelper.DATABASE_NAME);
            TaskDAO taskDAO;
            Task_PrerequisiteDAO prereqDAO;
            TagDAO tagDAO;
            Task_TagDAO task_tagDAO;
            taskDAO = new TaskDAO(this.activity);
            prereqDAO = new Task_PrerequisiteDAO(this.activity);
            tagDAO = new TagDAO(this.activity);
            task_tagDAO = new Task_TagDAO(this.activity);

            SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy H:m");


            Task t1 = new Task(1, "Berkunjung ke rumah paman", sdf.parse("28-05-2015 13:00"), "Menyenangkan sekali", Task.UNFINISHED);
            Task t2 = new Task(2, "Berkunjung ke restoran paman", sdf.parse("29-06-2015 13:00"), "Mengenyangkan sekali", Task.UNFINISHED);
            Task t3 = new Task(3, "Beli Mobil", sdf.parse("27-05-2015 23:59"), "Supaya bisa ke rumah paman", Task.FINISHED);
            User u1 = new User(1, "Yusro Tsaqova", "a", "a");

            Task_Prerequisite tp = new Task_Prerequisite(1, 1, 3);

            Tag tg1 = new Tag(1, "Keluarga");
            Tag tg2 = new Tag(2, "Jalan-Jalan");
            Task_Tag ttg1 = new Task_Tag(1, 1, 1);
            Task_Tag ttg2 = new Task_Tag(2, 1, 2);

            taskDAO.insert(t1);
            taskDAO.insert(t2);
            taskDAO.insert(t3);
            prereqDAO.insert(tp);
            tagDAO.insert(tg1);
            tagDAO.insert(tg2);
            task_tagDAO.insert(ttg1);
            task_tagDAO.insert(ttg2);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
