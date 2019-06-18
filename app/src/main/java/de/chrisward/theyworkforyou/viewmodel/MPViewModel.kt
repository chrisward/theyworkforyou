package de.chrisward.theyworkforyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import de.chrisward.theyworkforyou.database.MPStore
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase
import de.chrisward.theyworkforyou.model.MP
import de.chrisward.theyworkforyou.repository.MPRepository
import de.chrisward.theyworkforyou.wrapper.Resource
import javax.inject.Inject

class MPViewModel
@Inject constructor(
    application: Application,
    val mpRepository: MPRepository,
    val theyWorkForYouDatabase: TheyWorkForYouDatabase,
    val mpStore: MPStore
) : AndroidViewModel(application) {
    val mpList: LiveData<List<MP>>

    init {
        mpList = mpStore.selectAllMPs()
    }

    fun refreshMPs(lifecycleOwner: LifecycleOwner) {
        mpRepository
            .refreshMPs()
            .observe(
                lifecycleOwner,
                Observer<Resource<List<MP>>> { mps ->
                    when (mps!!.status) {
                        Resource.Status.SUCCESS -> {
                            object : Thread() {
                                override fun run() {
                                    if (mps.data == null) {
                                        return
                                    }

                                    theyWorkForYouDatabase.clearAllTables()

                                    for (mp in mps.data.orEmpty()) {
                                        mpStore.insert(mp)
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
