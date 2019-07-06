package com.masum.common;

import com.masum.network.ApiService;
import com.masum.network.RestClient;

public class CommonUtils {

    public static ApiService getApiService() {
        return RestClient.getInstance().callRetrofit();
    }
}
