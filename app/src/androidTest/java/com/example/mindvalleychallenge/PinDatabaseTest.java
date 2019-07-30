package com.example.mindvalleychallenge;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.mindvalleychallenge.data.local.PinDao;
import com.example.mindvalleychallenge.data.local.PinDatabase;

import org.junit.After;
import org.junit.Before;

public abstract class PinDatabaseTest {

    // System being tested
    private PinDatabase pinDatabase;

    public PinDao getPinDao(){
        return pinDatabase.getPinDao();
    }

    @Before
    public void init(){
        pinDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                PinDatabase.class
        ).build();
    }

    @After
    public void finish(){
        pinDatabase.close();
    }


}
