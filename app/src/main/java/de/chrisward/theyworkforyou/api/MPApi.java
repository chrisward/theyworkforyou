package de.chrisward.theyworkforyou.api;

import java.util.List;

import de.chrisward.theyworkforyou.model.MP;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MPApi {
    @GET("getMPs")
    Call<List<MP>> getMPs();
}
