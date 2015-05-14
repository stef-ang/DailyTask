package com.stef_developer.dailytask.table_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stefanus Anggara on 10/05/2015.
 */
public class Task_Tag implements Parcelable {

    private int id_task_tag;

//    private Task task;
//    private Tag tag;
    private int id_task;
    private int id_tag;

    public Task_Tag(int id_task_tag, int id_task, int id_tag) {
        super();
        this.id_task_tag = id_task_tag;
        this.id_task = id_task;
        this.id_tag = id_tag;
    }

    public Task_Tag(int id_task, int id_tag) {
        super();
        this.id_task = id_task;
        this.id_tag = id_tag;
    }

    public Task_Tag(Parcel in) {
        super();
        this.id_task_tag = in.readInt();
        this.id_task = in.readInt();
        this.id_tag = in.readInt();
    }

    //    public Task_Tag(int id_task_tag, Task task, Tag tag) {
//        super();
//        this.id_task_tag = id_task_tag;
//        this.task = task;
//        this.tag = tag;
//    }
//
//    public Task_Tag(Parcel in) {
//        super();
//        this.id_task_tag = in.readInt();
//
//        this.task = in.readParcelable(Task.class.getClassLoader());
//        this.tag = in.readParcelable(Tag.class.getClassLoader());
//    }


    public void setId_task_tag(int id_task_tag) {
        this.id_task_tag = id_task_tag;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }


//    public void setTask(Task task) {
//        this.task = task;
//    }
//
//    public void setTag(Tag tag) {
//        this.tag = tag;
//    }

    public int getId_task_tag() {
        return id_task_tag;
    }

    public int getId_task() {
        return id_task;
    }

    public int getId_tag() {
        return id_tag;
    }

    //    public Task getTask() {
//        return task;
//    }
//
//    public Tag getTag() {
//        return tag;
//    }

    @Override
    public String toString() {
//        return "TASK_TAG [id_task_tag: " + id_task_tag
//                + "| task: " + task
//                + "| tag: " + tag + "]";
        return "TASK_TAG [id_task_tag: " + id_task_tag
                + "| id_task: " + id_task
                + "| id_tag: " + id_tag + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId_task_tag());
        dest.writeInt(getId_task());
        dest.writeInt(getId_tag());
//        dest.writeParcelable(getTask(), flags);
//        dest.writeParcelable(getTag(), flags);
    }

    public static final Creator<Task_Tag> CREATOR = new Creator<Task_Tag>() {
        @Override
        public Task_Tag createFromParcel(Parcel source) {
            return new Task_Tag(source);
        }

        @Override
        public Task_Tag[] newArray(int size) {
            return new Task_Tag[size];
        }
    };

    @Override
    public int hashCode() {
        final int primne = 31;
        int result = 1;
        result = primne * result + id_task_tag;
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
        Task_Tag other = (Task_Tag) o;
        if (id_task_tag != other.id_task_tag)
            return false;
        return true;
    }
}
