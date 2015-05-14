package com.stef_developer.dailytask.table_object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stefanus Anggara on 04/05/2015.
 */
public class User implements Parcelable {

    private int id_user;
    private String fullname;
    private String email;
    private String password;

    public User() {
        super();
    }

    public User(int id_user, String fullname, String email, String password) {
        super();
        this.id_user = id_user;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public User(String fullname, String email, String password) {
        super();
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    private User(Parcel in){
        super();
        this.id_user = in.readInt();
        this.fullname = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "USER [id_user: " + id_user
                + "| fullname: " + fullname
                + "| email: " + email
                + "| password: " + password + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId_user());
        dest.writeString(getFullname());
        dest.writeString(getEmail());
        dest.writeString(getPassword());

        // cara lain, biar g pake private User(Parcel in)
        // Bundle bundle = new Bundle();
        // bundle.putIn("id_user", id_user);
        // bundle.putString("fullname", fullname);
        // bundle.putString("email", email);
        // bundle.putString("password", password);
        // dest.writeBundle(bundle);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);

            // cara lain, g pake private User(Parcel in) #yang masang2nya mbingungin
            // Bundle bundle = source.readeBundle();
            // return new User(bundle.getInt("id_user"),
            //                  bundle.getString("fullname"),
            //                  bundle.getString("email"),
            //                  bundle.getString("password"));
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int hashCode() {
        final int primne = 31;
        int result = 1;
        result = primne * result + id_user;
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
        User other = (User) o;
        if (id_user != other.id_user)
            return false;
        return true;
    }
}
