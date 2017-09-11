package com.rygital.producthunt.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class CategoriesListResponse {
    @SerializedName("categories")
    @Expose
    private List<CategoriesListData> categories;
    @SerializedName("status")
    @Expose
    private int status;

    public List<CategoriesListData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesListData> categories) {
        this.categories = categories;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
