package com.example.mindvalleychallenge.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.mindvalleychallenge.data.Repository;
import com.example.mindvalleychallenge.data.remote.Pin;
import com.example.mindvalleychallenge.util.Resource;

import java.util.List;

import javax.inject.Inject;

public class PinViewModel extends ViewModel {

    private static final String TAG = "PersonListViewModel";

    private MediatorLiveData<Resource<List<Pin>>> pins = new MediatorLiveData<>();

    private MutableLiveData<Pin> pin  = new MutableLiveData<>();

    private Repository repository;

    @Inject
    public PinViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<Pin>>> getPins() {
        return pins;
    }

    public void searchPinApi() {

        // this is source of livedata from the repository
        final LiveData<Resource<List<Pin>>> repositorySource = repository.searchPinsApi();

        pins.addSource(repositorySource, listResource -> {
            if (listResource != null) {
                pins.setValue(listResource);
                if (listResource.status == Resource.Status.SUCCESS) {

                    if (listResource.data != null) {
                        if (listResource.data.size() == 0) {
                            pins.setValue(
                                    new Resource<List<Pin>>(
                                            Resource.Status.ERROR,
                                            listResource.data,
                                            null
                                    ));
                        }
                    }
                    // must remove or it will keep listening to repository
                    pins.removeSource(repositorySource);
                } else if (listResource.status == Resource.Status.ERROR) {
                    pins.removeSource(repositorySource);
                }
            } else {
                pins.removeSource(repositorySource);
            }
        });
    }

    public LiveData<Pin> observePin(){
        return pin;
    }
}
