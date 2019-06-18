package de.chrisward.theyworkforyou.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.chrisward.theyworkforyou.api.LordApi
import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.wrapper.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LordRepository @Inject constructor(private val lordApi: LordApi) {
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
}
