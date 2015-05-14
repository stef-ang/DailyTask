package com.stef_developer.dailytask.table_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stefanus Anggara on 08/05/2015.
 */
public class Tag implements Parcelable {

    private int id_tag;
    private String isi_tag;

    public Tag() {
        super();
    }

    public Tag(String isi_tag) {
        super();
        this.isi_tag = isi_tag;
    }

    public Tag(int id_tag, String isi_tag) {
        super();
        this.id_tag = id_tag;
        this.isi_tag = isi_tag;
    }

    public Tag(Parcel in) {
        super();
        this.id_tag = in.readInt();
        this.isi_tag = in.readString();
    }

    public void setId_tag(int id_tag) {
        this.id_tag = id_tag;
    }

    public void setIsi_tag(String isi_tag) {
        this.isi_tag = isi_tag;
    }

    public int getId_tag() {
        return id_tag;
    }

    public String getIsi_tag() {
        return isi_tag;
    }

    @Override
    public String toString() {
        return "TAG [id_tag: " + id_tag
                + "| isi_tag: " + isi_tag + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId_tag());
        dest.writeString(getIsi_tag());
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    @Override
    public int hashCode() {
        final int primne = 31;
        int result = 1;
        result = primne * result + id_tag;
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
        Tag other = (Tag) o;
        if (id_tag != other.id_tag)
            return false;
        return true;
    }
}
