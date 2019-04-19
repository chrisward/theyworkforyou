package de.chrisward.theyworkforyou.api

import de.chrisward.theyworkforyou.model.Lord
import retrofit2.Call
import retrofit2.http.GET

interface LordApi {
    @get:GET("getLords")
    val mPs: Call<List<Lord>>
}
