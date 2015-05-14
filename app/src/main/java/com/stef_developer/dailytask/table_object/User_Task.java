package com.stef_developer.dailytask.table_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stefanus Anggara on 08/05/2015.
 */
public class User_Task implements Parcelable {

    private int id_user_task;

    // kenapa kok yang jadi field class? bukan FKnya saja?
//    private User user;
//    private Task task;
    private int id_user;
    private int id_task;

    public User_Task() {
        super();
    }

    public User_Task(int id_user_task, int id_user, int id_task) {
        super();
        this.id_user_task = id_user_task;
        this.id_user = id_user;
        this.id_task = id_task;
    }

    public User_Task(int id_user, int id_task) {
        super();
        this.id_user = id_user;
        this.id_task = id_task;
    }

    public User_Task(Parcel in) {
        super();
        this.id_user_task = in.readInt();
        this.id_user = in.readInt();
        this.id_task = in.readInt();
    }

    //    public User_Task(User user, Task task) {
//        super();
//        this.user = user;
//        this.task = task;
//    }
//
//    public User_Task(int id_user_task, User user, Task task) {
//        super();
//        this.id_user_task = id_user_task;
//        this.user = user;
//        this.task = task;
//    }
//
//    public User_Task(Parcel in) {
//        super();
//        this.id_user_task = in.readInt();
//
//        this.user = in.readParcelable(User.class.getClassLoader());
//        this.task = in.readParcelable(Task.class.getClassLoader());
//    }

    public void setId_user_task(int id_user_task) {
        this.id_user_task = id_user_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    //    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setTask(Task task) {
//        this.task = task;
//    }

    public int getId_user_task() {
        return id_user_task;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_task() {
        return id_task;
    }

    //    public User getUser() {
//        return user;
//    }
//
//    public Task getTask() {
//        return task;
//    }

    @Override
    public String toString() {
        return "USER_TASK [id_user_task: " + id_user_task
                + "| id_user: " + id_user
                + "| id_task: " + id_task + "]";
//        return "USER_TASK [id_user_task: " + id_user_task
//                + "| user: " + user
//                + "| task: " + task + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId_user_task());
        dest.writeInt(getId_user());
        dest.writeInt(getId_task());
//        dest.writeParcelable(getUser(), flags);
//        dest.writeParcelable(getTask(), flags);
    }

    public static final Creator<User_Task> CREATOR = new Creator<User_Task>() {
        @Override
        public User_Task createFromParcel(Parcel source) {
            return new User_Task(source);
        }

        @Override
        public User_Task[] newArray(int size) {
            return new User_Task[size];
        }
    };

    @Override
    public int hashCode() {
        final int primne = 31;
        int result = 1;
        result = primne * result + id_user_task;
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
        User_Task other = (User_Task) o;
        if (id_user_task != other.id_user_task)
            return false;
        return true;
    }
}
