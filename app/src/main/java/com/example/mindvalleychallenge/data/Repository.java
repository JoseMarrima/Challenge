package com.example.mindvalleychallenge.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.mindvalleychallenge.data.local.PinDao;
import com.example.mindvalleychallenge.data.remote.ApiResponse;
import com.example.mindvalleychallenge.data.remote.Pin;
import com.example.mindvalleychallenge.data.remote.PinServiceApi;
import com.example.mindvalleychallenge.util.AppExecutors;
import com.example.mindvalleychallenge.util.NetworkBoundResource;
import com.example.mindvalleychallenge.util.Resource;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class Repository {

    private static final String TAG = "Repository";

    private static Repository instance;
    private PinDao pinDao;
    private PinServiceApi pinServiceApi;
    private AppExecutors appExecutors;

    public Repository(PinDao pinDao, PinServiceApi pinServiceApi, AppExecutors appExecutors){
        this.pinDao = pinDao;
        this.pinServiceApi = pinServiceApi;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Pin>>> searchPinsApi() {

        return new NetworkBoundResource<List<Pin>, List<Pin>>(appExecutors){
            @Override
            protected void saveCallResults(@NonNull List<Pin> item) {
                Pin[] pins = new Pin[item.size()];
                if (item != null){
                    pinDao.insertPins(item.toArray(pins));
                }
            }
            @Override
            protected boolean shouldFetch(@NonNull List<Pin> data) {
                return true;
            }
            @NonNull
            @Override
            protected LiveData<List<Pin>> loadFromDb() {
                return pinDao.getPinsFromDb();
            }
            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Pin>>> createCall() {
                return pinServiceApi.getPins();
            }
        }.getAsLiveData();


    }


}
