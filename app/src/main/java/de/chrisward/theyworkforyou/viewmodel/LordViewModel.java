package de.chrisward.theyworkforyou.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import de.chrisward.theyworkforyou.database.LordStore;
import de.chrisward.theyworkforyou.repository.LordRepository;
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase;
import de.chrisward.theyworkforyou.model.Lord;
import de.chrisward.theyworkforyou.wrapper.Resource;

public class LordViewModel extends AndroidViewModel {
    public final LiveData<List<Lord>> lordList;

    public LordViewModel(@NonNull Application application) {
        super(application);
        this.lordList = TheyWorkForYouDatabase.get(application).lordStore().selectAllLords();
    }

    public void refreshLords(LifecycleOwner lifecycleOwner) {
        LordRepository.getInstance(getApplication()).refreshLords().observe(
                lifecycleOwner,
                lords -> {
                    if (lords == null) {
                        return;
                    }

                    switch (lords.status) {
                        case Resource.SUCCESS: {
                            final LordStore store = TheyWorkForYouDatabase.get(getApplication()).lordStore();

                            new Thread() {
                                @Override
                                public void run() {
                                    if (lords.data == null) {
                                        return;
                                    }

                                    TheyWorkForYouDatabase.get(getApplication()).clearAllTables();

                                    for (Lord lord : lords.data) {
                                        store.insert(lord);
                                    }
                                }
                            }.start();
                            break;
                        }
                        case Resource.ERROR: {
                            //ERROR HANDLING HERE
                        }
                    }
                }
        );
    }
}
