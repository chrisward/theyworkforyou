package de.chrisward.theyworkforyou.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import de.chrisward.theyworkforyou.model.Lord;

@Dao
public interface LordStore {
    @Query("SELECT * FROM lords order by name")
    LiveData<List<Lord>> selectAllLords();

    @Insert
    void insert(Lord... products);
}
