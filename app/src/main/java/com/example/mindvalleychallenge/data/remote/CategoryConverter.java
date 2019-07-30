package com.example.mindvalleychallenge.data.remote;


import androidx.room.TypeConverter;

import com.example.mindvalleychallenge.data.models.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CategoryConverter {


    @TypeConverter
    public static List<Category> stringToSomeObjectList(String data) {

        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Category>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Category> someObjects) {
        Gson gson = new Gson();
        return gson.toJson(someObjects);
    }
}
