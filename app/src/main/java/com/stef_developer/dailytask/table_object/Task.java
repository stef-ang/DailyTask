package com.stef_developer.dailytask.table_object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Stefanus Anggara on 07/05/2015.
 */
public class Task implements Parcelable {

    private int id_task;
    private String task_title;
    private Date datetime;
    private String details;
    private int status;
    public final static int UNFINISHED = 0;
    public final static int FINISHED = 1;
    public final static int FAILED = 2;

    public Task() {
        super();
    }

    public Task(int id_task, String task_title, Date datetime, String details) {
        super();
        this.id_task = id_task;
        this.task_title = task_title;
        this.datetime = datetime;
        this.details = details;
        this.status = 0;
    }

    public Task(int id_task, String task_title, Date datetime, String details, int status) {
        super();
        this.id_task = id_task;
        this.task_title = task_title;
        this.datetime = datetime;
        this.details = details;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Task(String task_title, Date datetime, String details) {
        super();
        this.task_title = task_title;
        this.datetime = datetime;
        this.details = details;
        this.status = 0;

    }

    private Task(Parcel in){
        super();
        this.id_task = in.readInt();
        this.task_title = in.readString();
        this.datetime = new Date(in.readLong());
        this.details = in.readString();
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId_task() {
        return id_task;
    }

    public String getTask_title() {
        return task_title;
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "TASK[id_task: " + id_task
                + "| task_title: " + task_title
                + "| datetime: " + datetime
                + "| details: " + details + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId_task());
        dest.writeString(getTask_title());
        dest.writeLong(getDatetime().getTime());
        dest.writeString(getDetails());
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_task;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Task other = (Task) o;
        if (id_task != other.id_task)
            return false;
        return true;
    }
}
