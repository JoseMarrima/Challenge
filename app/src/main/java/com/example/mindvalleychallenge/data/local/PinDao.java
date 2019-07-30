package com.example.mindvalleychallenge.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mindvalleychallenge.data.remote.Pin;

import java.util.List;


import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface PinDao {

    @Insert(onConflict = IGNORE)
    long[] insertPins(Pin... pins);

    @Query("SELECT * FROM pin_table")
    LiveData<List<Pin>> getPinsFromDb();

    @Insert
    void insertPin(Pin pin);
}
