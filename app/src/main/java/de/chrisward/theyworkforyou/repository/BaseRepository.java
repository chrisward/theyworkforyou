package de.chrisward.theyworkforyou.repository;

import android.support.annotation.NonNull;

import java.io.IOException;

import de.chrisward.theyworkforyou.Constants;
import de.chrisward.theyworkforyou.api.MPApi;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class BaseRepository {
    private final Retrofit retrofit;

    BaseRepository(final String apiKey) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl httpUrl = original.url();

                        HttpUrl newHttpUrl = httpUrl.newBuilder().addQueryParameter("key", apiKey).build();
                        Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl);

                        Request request = requestBuilder.build();

                        return chain.proceed(request);
                    }
                })
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    Retrofit getRetrofit() {
        return retrofit;
    }
}
