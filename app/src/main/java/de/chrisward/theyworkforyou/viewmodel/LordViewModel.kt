package de.chrisward.theyworkforyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import de.chrisward.theyworkforyou.database.LordStore
import de.chrisward.theyworkforyou.repository.LordRepository
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase
import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.wrapper.Resource
import javax.inject.Inject

class LordViewModel
@Inject constructor(
    application: Application,
    val lordRepository: LordRepository,
    val theyWorkForYouDatabase: TheyWorkForYouDatabase,
    val lordStore: LordStore
) : AndroidViewModel(application) {
    val lordList: LiveData<List<Lord>> = lordStore.selectAllLords()

    fun refreshLords(lifecycleOwner: LifecycleOwner) {
        lordRepository
            .refreshLords()
            .observe(
                lifecycleOwner,
                Observer<Resource<List<Lord>>> { lords ->
                    when (lords!!.status) {
                        Resource.Status.SUCCESS -> {
                            object : Thread() {
                                override fun run() {
                                    if (lords.data == null) {
                                        return
                                    }

                                    theyWorkForYouDatabase.clearAllTables()

                                    for (lord in lords.data.orEmpty()) {
                                        lordStore.insert(lord)
                                    }
                                }
                            }.start()
                        }
                        Resource.Status.ERROR -> {
                            //ERROR HANDLING HERE
                        }
                        Resource.Status.LOADING -> {

                        }
                    }
                }
        )
    }
}
