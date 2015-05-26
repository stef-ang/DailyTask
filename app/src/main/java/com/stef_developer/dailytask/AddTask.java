package com.stef_developer.dailytask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.stef_developer.dailytask.database.TagDAO;
import com.stef_developer.dailytask.database.TaskDAO;
import com.stef_developer.dailytask.table_object.Task;
import com.stef_developer.dailytask.view.DatePickerFragment;
import com.stef_developer.dailytask.view.TimePickerFragment;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AddTask extends AppCompatActivity {

    private TagDAO tagDAO;
    private boolean fieldEmpty;
    private EditText taskTitle;
    private EditText date;
    private EditText time;
    private EditText detail;
    private MultiAutoCompleteTextView tag;
    private MultiAutoCompleteTextView pq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        taskTitle = (EditText) findViewById(R.id.tasktitle);
        date = (EditText) findViewById(R.id.datepicker);
        time = (EditText) findViewById(R.id.timepicker);
        detail = (EditText) findViewById(R.id.detail);
        tag = (MultiAutoCompleteTextView) findViewById(R.id.taglist);
        pq = (MultiAutoCompleteTextView) findViewById(R.id.prerequisites);
        try {
            tagDAO = new TagDAO(this);
            ArrayList<String> tagsList = tagDAO.getTagsNameArray();
            String[] tags = tagsList.toArray(new String[tagsList.size()]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_item,tags);
            MultiAutoCompleteTextView taglists = (MultiAutoCompleteTextView)findViewById(R.id.taglist);
            taglists.setAdapter(adapter);
            taglists.setThreshold(0);
            taglists.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            taglists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getBaseContext(), "You add tag " + adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                }
            });

            TaskDAO taskDAO = new TaskDAO(this);
            ArrayList<String> prerequisitelist = taskDAO.getTasksArray();
            String[] prerequisiteArray = prerequisitelist.toArray(new String[tagsList.size()]);
            ArrayAdapter<String> padapter = new ArrayAdapter<String>(this, R.layout.autocomplete_item, prerequisiteArray);
            MultiAutoCompleteTextView prerequisites = (MultiAutoCompleteTextView)findViewById(R.id.prerequisites);
            prerequisites.setAdapter(padapter);
            prerequisites.setThreshold(0);
            prerequisites.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            prerequisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getBaseContext(), "You add prerequisites " + adapterView.getItemAtPosition(i), Toast.LENGTH_LONG).show();
                }
            });

            EditText datePicker = (EditText) findViewById(R.id.datepicker);
            datePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerFragment newFragment = new DatePickerFragment();
                    newFragment.setEditText((EditText) view);
                    newFragment.show(getFragmentManager(), "datePicker");
                }
            });

            EditText timePicker = (EditText) findViewById(R.id.timepicker);
            timePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerFragment newFragment = new TimePickerFragment();
                    newFragment.setEditText((EditText)view);
                    newFragment.show(getFragmentManager(), "timepicker");
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void addTask(View view) {
        this.fieldEmpty = false;

        checkEmpty(taskTitle, "Please fill Task Title field !");
        checkEmpty(date, "Please fill date field !");
        checkEmpty(time, "Please fill time field !");

        if(!this.fieldEmpty) {
            Task task = new Task();
            try {
                task.setTask_title(taskTitle.getText().toString());
                String dateTime = date.getText().toString() + " " + time.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyy hh:mm");
                Date date = simpleDateFormat.parse(dateTime);
                task.setDatetime(date);
                task.setDetails(detail.getText().toString());
                task.setStatus(Task.UNFINISHED);
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Please fill columns correctly!",
                        Toast.LENGTH_LONG).show();
                return;
            }
            try {
                TaskDAO taskDAO = new TaskDAO(this);
                long result = taskDAO.insert(task);
                if(result != -1) {
                    Toast.makeText(getApplicationContext(),
                            "Add Task Success",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void checkEmpty(TextView textView, String errorMessage) {
        int length = textView.getText().toString().length();
        if(length == 0) {
            textView.setError(errorMessage);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
            this.fieldEmpty = true;
        }
    }

}
