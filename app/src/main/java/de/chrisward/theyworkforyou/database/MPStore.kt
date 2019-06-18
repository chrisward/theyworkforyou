package de.chrisward.theyworkforyou.database


import androidx.lifecycle.LiveData
import androidx.room.*
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
