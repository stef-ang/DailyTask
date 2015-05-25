package com.stef_developer.dailytask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.stef_developer.dailytask.database.TagDAO;
import com.stef_developer.dailytask.database.TaskDAO;
import com.stef_developer.dailytask.database.Task_PrerequisiteDAO;
import com.stef_developer.dailytask.database.Task_TagDAO;
import com.stef_developer.dailytask.table_object.Tag;
import com.stef_developer.dailytask.table_object.Task;
import com.stef_developer.dailytask.table_object.Task_Prerequisite;
import com.stef_developer.dailytask.table_object.Task_Tag;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity2Activity extends Activity {
    TaskDAO taskDAO;
    Task_PrerequisiteDAO prereqDAO;
    TagDAO tagDAO;
    Task_TagDAO task_tagDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        try {
            taskDAO = new TaskDAO(this);
            prereqDAO = new Task_PrerequisiteDAO(this);
            tagDAO = new TagDAO(this);
            task_tagDAO = new Task_TagDAO(this);

            SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy H:m");


            Task t1 = new Task(1, "Berkunjung ke rumah paman", sdf.parse("28-05-2015 13:00"), "Menyenangkan sekali", 0);
            Task t2 = new Task(2, "Berkunjung ke restoran paman", sdf.parse("29-06-2015 13:00"), "Mengenyangkan sekali", 0);
            Task t3 = new Task(3, "Beli Mobil", sdf.parse("27-05-2015 23:59"), "Supaya bisa ke rumah paman", 1);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
