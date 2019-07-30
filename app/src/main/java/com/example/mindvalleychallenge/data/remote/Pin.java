
package com.example.mindvalleychallenge.data.remote;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import com.example.mindvalleychallenge.data.models.Category;
import com.example.mindvalleychallenge.data.models.PinLinks;
import com.example.mindvalleychallenge.data.models.Urls;
import com.example.mindvalleychallenge.data.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pin_table")
public class Pin {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("width")
    @Expose
    private Integer width;

    @SerializedName("height")
    @Expose
    private Integer height;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("likes")
    @Expose
    private Integer likes;

    @SerializedName("liked_by_user")
    @Expose
    private Boolean likedByUser;

    @SerializedName("user")
    @Expose
    @Embedded(prefix = "user_")
    private User user;

    @SerializedName("current_user_collections")
    @Expose
    private List<Object> currentUserCollections = null;

    @SerializedName("urls")
    @Expose
    @Embedded(prefix = "urls_")
    private Urls urls;

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    @SerializedName("links")
    @Expose
    @Embedded(prefix = "links_")
    private PinLinks links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Object> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public PinLinks getLinks() {
        return links;
    }

    public void setLinks(PinLinks links) {
        this.links = links;
    }

    public Pin(@NonNull String id, String createdAt, Integer width, Integer height, String color, Integer likes, Boolean likedByUser, User user, List<Object> currentUserCollections, Urls urls, List<Category> categories, PinLinks links) {
        this.id = id;
        this.createdAt = createdAt;
        this.width = width;
        this.height = height;
        this.color = color;
        this.likes = likes;
        this.likedByUser = likedByUser;
        this.user = user;
        this.currentUserCollections = currentUserCollections;
        this.urls = urls;
        this.categories = categories;
        this.links = links;
    }
}
