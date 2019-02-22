package com.example.demogit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuiltBy {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}