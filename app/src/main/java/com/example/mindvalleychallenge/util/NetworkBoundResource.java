package com.example.mindvalleychallenge.util;


import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.mindvalleychallenge.data.remote.ApiResponse;


public abstract class NetworkBoundResource<CacheObject, RequestObject> {
    private AppExecutors appExecutors;
    private MediatorLiveData<Resource<CacheObject>> results = new MediatorLiveData<>();
    private static final String TAG = "NetworkBoundResource";

    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        init();
    }

    private void init() {
        // update LiveData for loading status
        results.setValue((Resource<CacheObject>) Resource.loading(null));

        // observe LiveData Source from local db
        final LiveData<CacheObject> dbSource = loadFromDb();

        results.addSource(dbSource, cacheObject -> {
            results.removeSource(dbSource);

            if(shouldFetch(cacheObject)) {
                // get data from network
                fetchFromNetwork(dbSource);
            }
            else {
                results.addSource(dbSource, cacheObject1 -> setValue(Resource.success(cacheObject1)));
            }
        });
    }


    /**
     * 1) observe local db
     * 2) if <condition/> query the network
     * 3) stop observing the local db
     * 4) insert new data into local db
     * 5) begin observing local db again to see the refreshed data from network
     * @param dbSource
     */
    private void fetchFromNetwork(final LiveData<CacheObject> dbSource) {
        Log.d(TAG, "fetchFromNetwork: started");
        results.addSource(dbSource, cacheObject -> setValue(Resource.loading(cacheObject)));

        final LiveData<ApiResponse<RequestObject>> apiResponse = createCall();

        results.addSource(apiResponse, requestObjectApiResponse -> {
            results.removeSource(dbSource);
            results.removeSource(apiResponse);

            if(requestObjectApiResponse instanceof ApiResponse.ApiSuccessResponse) {
                Log.d(TAG, "onChanged: SucessResponse");
                appExecutors.diskIO().execute(() -> {
                    //save response to local database
                    saveCallResults((RequestObject) processResponse((ApiResponse.ApiSuccessResponse)requestObjectApiResponse));

                    appExecutors.mainThread().execute(() -> results.addSource(loadFromDb(),
                            cacheObject -> setValue(Resource.success(cacheObject))));
                });
            }
            else if(requestObjectApiResponse instanceof ApiResponse.ApiEmptyResponse) {
                Log.d(TAG, "onChanged: EmptyResponse");
                appExecutors.mainThread().execute(() -> results.addSource(loadFromDb(), cacheObject -> setValue(Resource.success(cacheObject))));
            }
            else if(requestObjectApiResponse instanceof ApiResponse.ApiErrorResponse) {
                Log.d(TAG, "onChanged: ErrorResponse");
                results.addSource(dbSource,
                        cacheObject -> setValue(Resource.error(
                        ((ApiResponse.ApiErrorResponse)requestObjectApiResponse).getErrorMessage()
                        , cacheObject)
                ));
            }
        });
    }

    public LiveData<Resource<CacheObject>> asLiveData() {
        return results;
    }

    private CacheObject processResponse(ApiResponse.ApiSuccessResponse response) {
        return (CacheObject) response.getBody();
    }

    private void setValue(Resource<CacheObject> newValue) {
        if(results.getValue() != newValue) {
            results.setValue(newValue);
        }
    }

    // called to save results of API response into the database
    @WorkerThread
    protected abstract void saveCallResults(@NonNull RequestObject item);

    @MainThread
    protected abstract boolean shouldFetch(@NonNull CacheObject data);

    @NonNull @MainThread
    protected abstract LiveData<CacheObject> loadFromDb();

    // called to create API call
    @NonNull @MainThread
    protected abstract LiveData<ApiResponse<RequestObject>> createCall();

    public final LiveData<Resource<CacheObject>>  getAsLiveData() {
        return results;
    }
}
