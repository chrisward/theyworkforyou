package de.chrisward.theyworkforyou.api;

import java.util.List;

import de.chrisward.theyworkforyou.model.Lord;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LordApi {
    @GET("getLords")
    Call<List<Lord>> getMPs();
}
