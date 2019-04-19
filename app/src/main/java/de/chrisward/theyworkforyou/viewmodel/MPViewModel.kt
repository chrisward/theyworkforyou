package de.chrisward.theyworkforyou.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

import de.chrisward.theyworkforyou.database.MPStore
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase
import de.chrisward.theyworkforyou.model.MP
import de.chrisward.theyworkforyou.repository.MPRepository
import de.chrisward.theyworkforyou.wrapper.Resource

class MPViewModel(application: Application) : AndroidViewModel(application) {
    val mpList: LiveData<List<MP>>

    init {
        mpList = TheyWorkForYouDatabase.get(application)!!.mpStore().selectAllMPs()
    }

    fun refreshMPs(lifecycleOwner: LifecycleOwner) {
        MPRepository.getInstance(getApplication()).refreshMPs().observe(
                lifecycleOwner,
                Observer<Resource<List<MP>>> { mps ->
                    when (mps!!.status) {
                        Resource.Status.SUCCESS -> {
                            val store = TheyWorkForYouDatabase.get(getApplication())!!.mpStore()

                            object : Thread() {
                                override fun run() {
                                    if (mps.data == null) {
                                        return
                                    }
7
                                    TheyWorkForYouDatabase.get(getApplication())!!.clearAllTables()

                                    for (mp in mps.data.orEmpty()) {
                                        store.insert(mp)
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
