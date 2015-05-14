package com.stef_developer.dailytask.table_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stefanus Anggara on 10/05/2015.
 */
public class Task_Prerequisite implements Parcelable{

    private int id_task_prerequisite;

//    private Task task1;
//    private Task task2;
    private int id_task1;
    private int id_task2;

    public Task_Prerequisite(int id_task_prerequisite, int id_task1, int id_task2) {
        super();
        this.id_task_prerequisite = id_task_prerequisite;
        this.id_task1 = id_task1;
        this.id_task2 = id_task2;
    }

    public Task_Prerequisite(int id_task1, int id_task2) {
        super();
        this.id_task1 = id_task1;
        this.id_task2 = id_task2;
    }

    public Task_Prerequisite(Parcel in) {
        super();
        this.id_task_prerequisite = in.readInt();
        this.id_task1 = in.readInt();
        this.id_task2 = in.readInt();
    }
    //    public Task_Prerequisite(int id_task_prerequisite, Task task1, Task task2) {
//        super();
//        this.id_task_prerequisite = id_task_prerequisite;
//        this.task1 = task1;
//        this.task2 = task2;
//    }
//
//    public Task_Prerequisite(Parcel in) {
//        super();
//        this.id_task_prerequisite = in.readInt();
//
//        this.task1 = in.readParcelable(Task.class.getClassLoader());
//        this.task2 = in.readParcelable(Task.class.getClassLoader());
//    }

    public void setId_task_prerequisite(int id_task_prerequisite) {
        this.id_task_prerequisite = id_task_prerequisite;
    }

    public void setId_task1(int id_task1) {
        this.id_task1 = id_task1;
    }

    public void setId_task2(int id_task2) {
        this.id_task2 = id_task2;
    }
//    public void setTask1(Task task1) {
//        this.task1 = task1;
//    }
//
//    public void setTask2(Task task2) {
//        this.task2 = task2;
//    }

    public int getId_task_prerequisite() {
        return id_task_prerequisite;
    }

    public int getId_task1() {
        return id_task1;
    }

    public int getId_task2() {
        return id_task2;
    }

    //    public Task getTask1() {
//        return task1;
//    }
//
//    public Task getTask2() {
//        return task2;
//    }

    @Override
    public String toString() {
//        return "TASK_PREREQUISITE [id_task_prerequisite: " + id_task_prerequisite
//                + "| task1: " + task1
//                + "| task2: " + task2 + "]";
        return "TASK_PREREQUISITE [id_task_prerequisite: " + id_task_prerequisite
                + "| id_task1: " + id_task1
                + "| id_task2: " + id_task2 + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId_task_prerequisite());
        dest.writeInt(getId_task1());
        dest.writeInt(getId_task2());
//        dest.writeParcelable(getTask1(), flags);
//        dest.writeParcelable(getTask2(), flags);
    }

    public static final Creator<Task_Prerequisite> CREATOR = new Creator<Task_Prerequisite>() {
        @Override
        public Task_Prerequisite createFromParcel(Parcel source) {
            return new Task_Prerequisite(source);
        }

        @Override
        public Task_Prerequisite[] newArray(int size) {
            return new Task_Prerequisite[size];
        }
    };

    @Override
    public int hashCode() {
        final int primne = 31;
        int result = 1;
        result = primne * result + id_task_prerequisite;
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
        Task_Prerequisite other = (Task_Prerequisite) o;
        if (id_task_prerequisite != other.id_task_prerequisite)
            return false;
        return true;
    }
}
