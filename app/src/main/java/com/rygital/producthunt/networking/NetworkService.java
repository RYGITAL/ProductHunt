package com.rygital.mvptest.networking;

import com.rygital.mvptest.models.CityListResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {
    @GET("v1/city")
    Observable<CityListResponse> getCityList();
}
