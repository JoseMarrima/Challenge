package com.example.mindvalleychallenge.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.mindvalleychallenge.data.local.PinDao;
import com.example.mindvalleychallenge.data.remote.Pin;
import com.example.mindvalleychallenge.data.remote.PinServiceApi;
import com.example.mindvalleychallenge.util.AppExecutors;
import com.example.mindvalleychallenge.util.LiveDataTestUtil;
import com.example.mindvalleychallenge.util.TestUtil;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    // System under test
    private Repository repository;

//    @Mock
    private PinDao pinDao;

//    @Mock
    private PinServiceApi pinServiceApi;

//    @Mock
    private AppExecutors appExecutors;

//    @BeforeEach
//    public void initEach(){
//        MockitoAnnotations.initMocks(this);
//        repository = new Repository(pinDao, pinServiceApi, appExecutors);
//    }

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @BeforeEach
    public void initEach(){
        pinDao = mock(PinDao.class);
        pinServiceApi = mock(PinServiceApi.class);
        appExecutors = mock(AppExecutors.class);
        repository = new Repository(pinDao, pinServiceApi, appExecutors);
    }

    /*
    retrieve pins
     */
    @Test
    public void getPins() throws Exception{
        // Arrange
        List<Pin> pins = TestUtil.pinList();
        LiveDataTestUtil<List<Pin>> listLiveDataTestUtil = new LiveDataTestUtil<>();

        MutableLiveData<List<Pin>> returnedData = new MutableLiveData<>();
        returnedData.setValue(pins);
//        when(pinDao.getPinsFromDb()).thenReturn(returnedData);

        // Act
//        List<Pin> observedData = listLiveDataTestUtil.getValue(repository.searchPinsApi())
    }


}




















