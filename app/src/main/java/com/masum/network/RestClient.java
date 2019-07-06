package com.masum.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class for the network Client
 */
public class RestClient {
    private static final long TIMEOUT_SECOND = 120;
    private static final long WRITE_TIMEOUT_SECOND = 120;
    private static final long READ_TIMEOUT_SECOND = 120;

    private static String BASE_URL = null;
    private static RestClient restClient = null;
    private OkHttpClient client;

    private RestClient() {
    }

    /**
     * Get instance of the network client
     *
     * @return instance of the network client
     */
    @NonNull
    public static RestClient getInstance() {
        if (restClient == null) {
            synchronized (RestClient.class) {
                if (restClient == null) {
                    restClient = new RestClient();
                }
            }
        }
        return restClient;
    }

    /**
     * Get Base url from the shared Pref
     *
     * @return base_url for the network calls
     */
    @NonNull
    public static void setBaseURL(String baseusrl) {
        BASE_URL = baseusrl;

    }


    /**
     * API service object
     *
     * @return ApiService to call the API's
     */
    public ApiService callRetrofit() {
        client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECOND, TimeUnit.SECONDS)
                .cache(null)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }

    /**
     * Cancel all pending network calls in queue
     */
    public void cancelRequest() {
        client.dispatcher().cancelAll();
    }
}
