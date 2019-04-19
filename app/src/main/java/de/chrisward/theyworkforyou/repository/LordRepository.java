package de.chrisward.theyworkforyou.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import de.chrisward.theyworkforyou.R;
import de.chrisward.theyworkforyou.api.LordApi;
import de.chrisward.theyworkforyou.model.Lord;
import de.chrisward.theyworkforyou.wrapper.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LordRepository extends BaseRepository {
    private static LordRepository instance;
    private LordApi lordApi;

    private LordRepository(final String apiKey) {
        super(apiKey);
        lordApi = getRetrofit().create(LordApi.class);
    }

    public synchronized static LordRepository getInstance(Context context) {
        if (instance == null) {
            instance = new LordRepository(
                    context.getResources().getString(R.string.theyworkforyou_api_key)
            );
        }

        return instance;
    }

    public LiveData<Resource<List<Lord>>> refreshLords() {
        final MutableLiveData<Resource<List<Lord>>> data = new MutableLiveData<>();

        lordApi.getMPs().enqueue(new Callback<List<Lord>>() {
            @Override
            public void onResponse(Call<List<Lord>> call, Response<List<Lord>> response) {
                if (response.body() == null) {
                    return;
                }
                data.setValue(new Resource<>(Resource.SUCCESS, response.body(), null));
            }

            @Override
            public void onFailure(Call<List<Lord>> call, Throwable t) {
                data.setValue(new Resource<>(Resource.ERROR, null, t.getMessage()));
            }
        });

        return data;
    }
}
