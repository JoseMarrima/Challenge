package com.example.mindvalleychallenge.data.remote;

import androidx.lifecycle.LiveData;

import java.util.List;

import retrofit2.http.GET;

public interface PinServiceApi {

    @GET("/raw/wgkJgazE")
    LiveData<ApiResponse<List<Pin>>> getPins();

}
