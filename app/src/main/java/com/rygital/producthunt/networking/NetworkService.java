package com.rygital.producthunt.networking;

import com.rygital.producthunt.models.CategoriesListResponse;
import com.rygital.producthunt.models.ProductListResponse;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface NetworkService {
    @GET
    Observable<ProductListResponse> getTechPosts(@Url String url);

    @GET("/v1/categories")
    Observable<CategoriesListResponse> getCategories();

}
