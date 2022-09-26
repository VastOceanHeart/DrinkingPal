package com.example.drinkingpal.retrofit;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("title")
    public String title;

    @SerializedName("link")
    public String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
