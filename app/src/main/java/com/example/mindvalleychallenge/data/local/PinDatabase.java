package com.example.mindvalleychallenge.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mindvalleychallenge.data.remote.CategoryConverter;
import com.example.mindvalleychallenge.data.remote.CurrentUserCollectionsConverter;
import com.example.mindvalleychallenge.data.remote.Pin;

@Database(entities = {Pin.class}, version = 1)
@TypeConverters({CategoryConverter.class, CurrentUserCollectionsConverter.class})
public abstract class PinDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "pin_db";

    public abstract PinDao getPinDao();
}
