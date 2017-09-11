package com.rygital.producthunt.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ProductListData {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("votes_count")
    @Expose
    private String votes_count;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("redirect_url")
    @Expose
    private String redirect_url;
    @SerializedName("screenshot_url")
    @Expose
    private Screenshot screenshot_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getVotes_count() {
        return votes_count;
    }

    public void setVotes_count(String votes_count) {
        this.votes_count = votes_count;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Screenshot getScreenshot_url() {
        return screenshot_url;
    }

    public void setScreenshot_url(Screenshot screenshot_url) {
        this.screenshot_url = screenshot_url;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    @Generated("org.jsonschema2pojo")
    public class Thumbnail {
        @SerializedName("image_url")
        @Expose
        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }

    @Generated("org.jsonschema2pojo")
    public class Screenshot {
        @SerializedName("300px")
        @Expose
        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
