package com.masum.test.repository;

import android.arch.lifecycle.MutableLiveData;

import com.masum.Listener.DataLoadFailedListener;
import com.masum.common.CommonUtils;
import com.masum.datamodel.Login;
import com.masum.datamodel.LoginResponse;
import com.masum.datamodel.ResponseData;
import com.masum.network.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {


    private MutableLiveData<LoginResponse> responseData;
    private static DataLoadFailedListener mListener;

    public MutableLiveData<LoginResponse> getLoginResponse(Login login) {
        if (responseData == null) {
            responseData = new MutableLiveData<>();
        }

        RestClient.setBaseURL("http://182.160.97.214:81/api/v1/");
        CommonUtils.getApiService().login(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        responseData.setValue(response.body());
                    } else {
                        mListener.onFailed("" + response.code(), response.message());
                    }
                } else {
                    if (mListener != null) {
                        mListener.onFailed("" + response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (mListener != null) {
                    mListener.onFailed(t.getMessage(), t.getLocalizedMessage());
                }
            }
        });

        return responseData;
    }

    public void setListener(DataLoadFailedListener mListener) {
        this.mListener = mListener;
    }

}
