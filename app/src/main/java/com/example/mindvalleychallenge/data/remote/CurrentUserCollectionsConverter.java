package com.example.mindvalleychallenge.data.remote;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CurrentUserCollectionsConverter {

    @TypeConverter
    public static List<Object> stringToSomeObjectList(String data) {

        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Object>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Object> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }


}
