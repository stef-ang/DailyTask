package com.stef_developer.dailytask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.stef_developer.dailytask.database.TagDAO;
import com.stef_developer.dailytask.view.DatePickerFragment;
import com.stef_developer.dailytask.view.TimePickerFragment;

import java.sql.SQLException;
import java.util.ArrayList;


public class AddTask extends Activity {

    private TagDAO tagDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        try {
            tagDAO = new TagDAO(this);
            ArrayList<String> tagsList = tagDAO.getTagsNameArray();
            String[] tags = tagsList.toArray(new String[tagsList.size()]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(); //TODO:kerjain ini lah pokoknya
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
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

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setEditText((EditText)v);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setEditText((EditText)v);
        newFragment.show(getFragmentManager(), "timepicker");
    }

}
