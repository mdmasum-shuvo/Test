package com.masum.test.repository;

import android.arch.lifecycle.MutableLiveData;

import com.masum.Listener.DataLoadFailedListener;
import com.masum.common.CommonUtils;
import com.masum.datamodel.Resource;
import com.masum.datamodel.ResponseData;
import com.masum.network.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataResponseRepository {

    private MutableLiveData<List<Resource>> responseData;

    private static DataLoadFailedListener mListener;


    public MutableLiveData<List<Resource>> getResponseData() {
        if (responseData == null) {
            responseData = new MutableLiveData<>();
        }

        RestClient.setBaseURL("http://192.168.1.226/");
        CommonUtils.getApiService().getResponseData().enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    responseData.setValue(response.body().getResults().getResources());
                } else {
                    if (mListener != null) {
                        mListener.onFailed("" + response.code(), response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
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
