package de.chrisward.theyworkforyou.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import de.chrisward.theyworkforyou.model.Lord

@Dao
interface LordStore {
    @Query("SELECT * FROM lords order by name")
    fun selectAllLords(): LiveData<List<Lord>>

    @Insert
    fun insert(vararg products: Lord)
}
