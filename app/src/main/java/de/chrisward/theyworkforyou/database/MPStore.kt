package de.chrisward.theyworkforyou.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import de.chrisward.theyworkforyou.model.MP

@Dao
interface MPStore {
    @Query("SELECT * FROM mps ORDER BY name")
    fun selectAllMPs(): LiveData<List<MP>>

    @Insert
    fun insert(vararg products: MP)

    @Update
    fun update(vararg products: MP)

    @Delete
    fun delete(vararg products: MP)
}
