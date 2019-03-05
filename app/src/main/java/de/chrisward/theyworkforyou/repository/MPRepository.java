package de.chrisward.theyworkforyou.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

import de.chrisward.theyworkforyou.R;
import de.chrisward.theyworkforyou.api.MPApi;
import de.chrisward.theyworkforyou.model.MP;
import de.chrisward.theyworkforyou.wrapper.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MPRepository extends BaseRepository {
    private static MPRepository instance;
    private MPApi mpApi;

    private MPRepository(final String apiKey) {
        super(apiKey);
        mpApi = getRetrofit().create(MPApi.class);
    }

    public synchronized static MPRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MPRepository(
                    context.getResources().getString(R.string.theyworkforyou_api_key)
            );
        }

        return instance;
    }

    public LiveData<Resource<List<MP>>> refreshMPs() {
        final MutableLiveData<Resource<List<MP>>> data = new MutableLiveData<>();

        mpApi.getMPs().enqueue(new Callback<List<MP>>() {
            @Override
            public void onResponse(Call<List<MP>> call, Response<List<MP>> response) {
                if (response.body() == null) {
                    return;
                }
                data.setValue(new Resource<>(Resource.SUCCESS, response.body(), null));
            }

            @Override
            public void onFailure(Call<List<MP>> call, Throwable t) {
                data.setValue(new Resource<>(Resource.ERROR, null, t.getMessage()));
            }
        });

        return data;
    }

}
