package de.chrisward.theyworkforyou.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import de.chrisward.theyworkforyou.model.Lord

@Dao
interface LordStore {
    @Query("SELECT * FROM lords order by name")
    fun selectAllLords(): LiveData<List<Lord>>

    @Insert
    fun insert(vararg products: Lord)
}
