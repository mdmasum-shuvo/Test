package com.masum.network;

import com.masum.datamodel.Login;
import com.masum.datamodel.LoginResponse;
import com.masum.datamodel.Resource;
import com.masum.datamodel.ResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("RecruitmentTest.json")
    Call<ResponseData> getResponseData();

    @POST("user")
    Call<LoginResponse> login(@Body Login login);

}
