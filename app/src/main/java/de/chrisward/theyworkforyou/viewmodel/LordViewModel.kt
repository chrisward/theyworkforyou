package de.chrisward.theyworkforyou.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

import de.chrisward.theyworkforyou.database.LordStore
import de.chrisward.theyworkforyou.repository.LordRepository
import de.chrisward.theyworkforyou.database.TheyWorkForYouDatabase
import de.chrisward.theyworkforyou.model.Lord
import de.chrisward.theyworkforyou.wrapper.Resource

class LordViewModel(application: Application) : AndroidViewModel(application) {
    val lordList: LiveData<List<Lord>> = TheyWorkForYouDatabase.get(application)!!.lordStore().selectAllLords()

    fun refreshLords(lifecycleOwner: LifecycleOwner) {
        LordRepository.getInstance(getApplication()).refreshLords().observe(
                lifecycleOwner,
                Observer<Resource<List<Lord>>> { lords ->
                    when (lords!!.status) {
                        Resource.Status.SUCCESS -> {
                            val store = TheyWorkForYouDatabase.get(getApplication())!!.lordStore()

                            object : Thread() {
                                override fun run() {
                                    if (lords.data == null) {
                                        return
                                    }

                                    TheyWorkForYouDatabase.get(getApplication())!!.clearAllTables()

                                    for (lord in lords.data.orEmpty()) {
                                        store.insert(lord)
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
