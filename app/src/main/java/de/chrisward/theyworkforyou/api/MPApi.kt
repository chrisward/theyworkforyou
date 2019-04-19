package de.chrisward.theyworkforyou.api

import de.chrisward.theyworkforyou.model.MP
import retrofit2.Call
import retrofit2.http.GET

interface MPApi {
    @get:GET("getMPs")
    val mPs: Call<List<MP>>
}
