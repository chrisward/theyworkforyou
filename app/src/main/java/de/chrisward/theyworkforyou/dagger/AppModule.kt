package de.chrisward.theyworkforyou.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import de.chrisward.theyworkforyou.Constants
import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.api.LordApi
import de.chrisward.theyworkforyou.api.MPApi
import de.chrisward.theyworkforyou.database.LordStore
import de.chrisward.theyworkforyou.database.MPStore
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase
import de.chrisward.theyworkforyou.repository.LordRepository
import de.chrisward.theyworkforyou.repository.MPRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun providesRetrofit(apiKey: String) : Retrofit {
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

        return Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideMPApi(restAdapter: Retrofit) : MPApi {
        return restAdapter.create(MPApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLordApi(restAdapter: Retrofit) : LordApi {
        return restAdapter.create(LordApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMPRepository(mpApi: MPApi) : MPRepository {
        return MPRepository(mpApi)
    }

    @Provides
    @Singleton
    fun provideLordRepository(lordApi: LordApi) : LordRepository {
        return LordRepository(lordApi)
    }

    @Provides
    @Singleton
    fun provideTheyWorkForYouDatabase(application: Application) : TheyWorkForYouDatabase {
        return TheyWorkForYouDatabase.get(application)!!
    }

    @Provides
    @Singleton
    fun provideMPStore(application: Application) : MPStore {
        return TheyWorkForYouDatabase.get(application)!!.mpStore()
    }

    @Provides
    @Singleton
    fun provideLordStore(application: Application) : LordStore {
        return TheyWorkForYouDatabase.get(application)!!.lordStore()
    }

    @Provides
    @Singleton
    fun provideApiKey(application: Application) : String {
        return application.getString(R.string.theyworkforyou_api_key)
    }
}