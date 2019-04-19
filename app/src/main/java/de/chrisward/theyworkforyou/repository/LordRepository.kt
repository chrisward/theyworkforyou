package de.chrisward.theyworkforyou.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context

import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.api.LordApi
import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.wrapper.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LordRepository private constructor(apiKey: String) : BaseRepository(apiKey) {
    private val lordApi: LordApi

    init {
        lordApi = retrofit.create(LordApi::class.java)
    }

    fun refreshLords(): LiveData<Resource<List<Lord>>> {
        val data = MutableLiveData<Resource<List<Lord>>>()

        lordApi.mPs.enqueue(object : Callback<List<Lord>> {
            override fun onResponse(call: Call<List<Lord>>, response: Response<List<Lord>>) {
                if (response.body() == null) {
                    return
                }
                data.value = Resource(Resource.Status.SUCCESS, response.body())
            }

            override fun onFailure(call: Call<List<Lord>>, t: Throwable) {
                data.value = Resource(status = Resource.Status.ERROR, message = t.message)
            }
        })

        return data
    }

    companion object {
        private var instance: LordRepository? = null

        @Synchronized
        fun getInstance(context: Context): LordRepository {
            if (instance == null) {
                instance = LordRepository(
                        context.resources.getString(R.string.theyworkforyou_api_key)
                )
            }

            return instance as LordRepository
        }
    }
}
