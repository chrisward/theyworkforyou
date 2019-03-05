package de.chrisward.theyworkforyou.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import de.chrisward.theyworkforyou.model.MP;

@Dao
public interface MPStore {
    @Query("SELECT * FROM mps ORDER BY name")
    LiveData<List<MP>> selectAllMPs();

    @Insert
    void insert(MP... products);

    @Update
    void update(MP... products);

    @Delete
    void delete(MP... products);
}
