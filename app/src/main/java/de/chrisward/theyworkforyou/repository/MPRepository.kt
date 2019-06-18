package de.chrisward.theyworkforyou.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import de.chrisward.theyworkforyou.R
import de.chrisward.theyworkforyou.api.MPApi
import de.chrisward.theyworkforyou.model.MP
import de.chrisward.theyworkforyou.wrapper.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MPRepository @Inject constructor(private val mpApi: MPApi) {
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
}
