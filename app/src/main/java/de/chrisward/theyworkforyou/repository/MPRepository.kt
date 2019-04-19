package de.chrisward.theyworkforyou.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context

import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.api.MPApi
import de.chrisward.theyworkforyou.model.MP
import de.chrisward.theyworkforyou.wrapper.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MPRepository private constructor(apiKey: String) : BaseRepository(apiKey) {
    private val mpApi: MPApi

    init {
        mpApi = retrofit.create(MPApi::class.java)
    }

    fun refreshMPs(): LiveData<Resource<List<MP>>> {
        val data = MutableLiveData<Resource<List<MP>>>()

        mpApi.mPs.enqueue(object : Callback<List<MP>> {
            override fun onResponse(call: Call<List<MP>>, response: Response<List<MP>>) {
                if (response.body() == null) {
                    return
                }
                data.value = Resource(Resource.Status.SUCCESS, response.body())
            }

            override fun onFailure(call: Call<List<MP>>, t: Throwable) {
                data.value = Resource(status = Resource.Status.ERROR, message = t.message)
            }
        })

        return data
    }

    companion object {
        private var instance: MPRepository? = null

        @Synchronized
        fun getInstance(context: Context): MPRepository {
            if (instance == null) {
                instance = MPRepository(
                        context.resources.getString(R.string.theyworkforyou_api_key)
                )
            }

            return instance as MPRepository
        }
    }

}
