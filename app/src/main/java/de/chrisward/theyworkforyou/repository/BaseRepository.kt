package de.chrisward.theyworkforyou.repository

import de.chrisward.theyworkforyou.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository(apiKey: String) {
    val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val httpUrl = original.url()

                    val newHttpUrl = httpUrl.newBuilder().addQueryParameter("key", apiKey).build()
                    val requestBuilder = original.newBuilder().url(newHttpUrl)

                    val request = requestBuilder.build()

                    chain.proceed(request)
                }
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}
