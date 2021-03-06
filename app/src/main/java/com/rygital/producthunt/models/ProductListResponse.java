package com.rygital.producthunt.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ProductListResponse {
    @SerializedName("posts")
    @Expose
    private List<ProductListData> posts = new ArrayList<ProductListData>();
    @SerializedName("status")
    @Expose
    private int status;

    public List<ProductListData> getPosts() {
        return posts;
    }

    public void setPosts(List<ProductListData> posts) {
        this.posts = posts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
