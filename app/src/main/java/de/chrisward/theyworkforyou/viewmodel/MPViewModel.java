package de.chrisward.theyworkforyou.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import de.chrisward.theyworkforyou.database.MPStore;
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase;
import de.chrisward.theyworkforyou.model.MP;
import de.chrisward.theyworkforyou.repository.MPRepository;
import de.chrisward.theyworkforyou.wrapper.Resource;

public class MPViewModel extends AndroidViewModel {
    public final LiveData<List<MP>> mpList;

    public MPViewModel(@NonNull Application application) {
        super(application);
        mpList = TheyWorkForYouDatabase.get(application).mpStore().selectAllMPs();
    }

    public void refreshMPs(LifecycleOwner lifecycleOwner) {
        MPRepository.getInstance(getApplication()).refreshMPs().observe(
                lifecycleOwner,
                mps -> {
                    if (mps == null) {
                        return;
                    }

                    switch (mps.status) {
                        case Resource.SUCCESS: {
                            final MPStore store = TheyWorkForYouDatabase.get(getApplication()).mpStore();

                            new Thread() {
                                @Override
                                public void run() {
                                    if (mps.data == null) {
                                        return;
                                    }

                                    TheyWorkForYouDatabase.get(getApplication()).clearAllTables();

                                    for (MP mp : mps.data) {
                                        store.insert(mp);
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
