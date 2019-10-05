package com.example.demo.chat.model;

import android.os.Parcel;
import android.os.Parcelable;

public class  User implements Parcelable {
    String id;
    String name;
    String email;
    String password;
    String imageURL;

    public User() {
    }

    public User(String id, String name, String email, String password, String imageURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.imageURL = imageURL;
    }

    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
