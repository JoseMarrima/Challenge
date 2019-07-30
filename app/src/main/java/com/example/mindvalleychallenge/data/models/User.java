
package com.example.mindvalleychallenge.data.models;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("profile_image")
    @Expose
    @Embedded(prefix = "profileImage_")
    private ProfileImage profileImage;

    @SerializedName("userLinks")
    @Expose
    @Embedded(prefix = "userLinks_")
    private UserLinks userLinks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public UserLinks getUserLinks() {
        return userLinks;
    }

    public void setUserLinks(UserLinks userLinks) {
        this.userLinks = userLinks;
    }

    public User(String id, String username, String name, ProfileImage profileImage, UserLinks userLinks) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.profileImage = profileImage;
        this.userLinks = userLinks;
    }
}
