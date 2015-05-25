package com.stef_developer.dailytask.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stef_developer.dailytask.R;
import com.stef_developer.dailytask.database.TagDAO;
import com.stef_developer.dailytask.database.TaskDAO;
import com.stef_developer.dailytask.table_object.Tag;
import com.stef_developer.dailytask.table_object.Task;
import com.stef_developer.dailytask.view.TaskIcon;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by wik on 5/23/15.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    Context context;
    int layoutResourceId;
    Task[] taskArray;
    TaskDAO taskDAO;
    TagDAO tagDAO;
    public TaskAdapter(Context context, int layoutResourceId, Task[] taskArray) throws SQLException {
        super(context, layoutResourceId, taskArray);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.taskArray = taskArray;
        taskDAO = new TaskDAO(context);
        tagDAO = new TagDAO(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if (taskArray[position].getStatus() != 0)
        //    return null;

        SimpleDateFormat fullDateTimeFormat = new SimpleDateFormat("MMMM d yyyy -- HH:mm");
        LinearLayout.LayoutParams standardLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        View row = convertView;
        TaskHolder holder = null;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, null);
            holder = new TaskHolder();

            holder.upperbox = (LinearLayout)row.findViewById(R.id.taskbox_upperbox);
            holder.leftBar = (ImageView)row.findViewById(R.id.taskbox_bar_imageview);
            holder.titleView = (TextView)row.findViewById(R.id.taskbox_title_textview);
            holder.timeView = (TextView)row.findViewById(R.id.taskbox_time_textview);
            holder.hoursView = (TextView)row.findViewById(R.id.taskbox_hours_textview);
            holder.tagsList = (LinearLayout)row.findViewById(R.id.tags_layout);
            holder.prerequisiteList = (LinearLayout)row.findViewById(R.id.prerequisites_list);
            holder.detailsView = (TextView)row.findViewById(R.id.taskbox_details_textview);

            row.setTag(holder);
        }
        else {
            holder = (TaskHolder)convertView.getTag();
        }

        //get prerequisites list
        ArrayList<Task> prerequisites = taskDAO.getPrerequisites(taskArray[position].getId_task());

        //Set the task icon
        int finishedPrerequisites = 0;
        for (Task t : prerequisites) {
            if (t.getStatus() == 1)
                finishedPrerequisites++;
        }

        float progress = prerequisites.size() > 0 ? (float)finishedPrerequisites / (float)prerequisites.size() : 0;
        long diff = taskArray[position].getDatetime().getTime() - (new Date()).getTime();
        int dayDiff = (int)(diff / (1000 * 60 * 60 * 24));
        TaskIcon taskIcon = new TaskIcon(context, dayDiff, progress);
        if (holder.upperbox.getTag() == null) {
            holder.upperbox.addView(taskIcon, 0);
            holder.upperbox.setTag(true);
        }
        else {
            holder.upperbox.removeViewAt(0);
            holder.upperbox.addView(taskIcon, 0);
        }
        //--------------------------------------------------------------------------------------

        //Set the left bar's color
        holder.leftBar.setBackgroundColor(taskIcon.getColor());
        //--------------------------------------------------------------------------------------

        //Fill the title TextView
        holder.titleView.setText(taskArray[position].getTask_title());
        //---------------------------------------------------------------------------------------

        //Fill the time TextView
        holder.timeView.setText(fullDateTimeFormat.format(taskArray[position].getDatetime()));
        //---------------------------------------------------------------------------------------

        //Fill the "hours left" TextView
        long hoursDiff = taskArray[position].getDatetime().getTime() - (new Date()).getTime();
        hoursDiff = (hoursDiff / (1000 * 60 * 60));
        if (hoursDiff < 24) {
            holder.hoursView.setText(String.format("%d hours left", hoursDiff));
        }
        else {
            holder.hoursView.setText("");
        }
        //----------------------------------------------------------------------------------------

        //Fill the tags container
//        holder.tagsList.removeAllViews();
//        ArrayList<Tag> tags = tagDAO.getTagsByTask(taskArray[position].getId_task());
//        for (Tag tag : tags) {
//            TextView tv = new TextView(context);
//            tv.setText(tag.getIsi_tag());
//            tv.setTextSize(12);
//            tv.setBackgroundResource(R.drawable.tag_background);
//            tv.setLayoutParams(standardLayoutParams);
//            holder.tagsList.addView(tv);
//        }
        //----------------------------------------------------------------------------------------

        //Populate prerequisites list
        holder.prerequisiteList.removeAllViews();
        for (Task prereq : prerequisites) {
            TextView tv = new TextView(context);
            tv.setText("- " + prereq.getTask_title());
            tv.setLayoutParams(standardLayoutParams);
            holder.prerequisiteList.addView(tv);
        }
        //----------------------------------------------------------------------------------------

        holder.detailsView.setText(taskArray[position].getDetails());
        return row;
    }

    private class TaskHolder {
        private LinearLayout upperbox;
        private ImageView leftBar;
        private TextView titleView;
        private TextView timeView;
        private TextView hoursView;
        private TextView detailsView;
        private LinearLayout tagsList;
        private LinearLayout prerequisiteList;
    }
}
