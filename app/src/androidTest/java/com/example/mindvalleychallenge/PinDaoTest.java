package com.example.mindvalleychallenge;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.mindvalleychallenge.data.remote.Pin;
import com.example.mindvalleychallenge.util.LiveDataTestUtil;
import com.example.mindvalleychallenge.util.TestUtil;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PinDaoTest extends PinDatabaseTest{

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    List<Pin> pinList = new ArrayList<Pin>() {{ add(TestUtil.pin()); }};

    /*
    Insert, Read
     */
    @Test
    public void insertAndRead() throws Exception{

        // insert
        getPinDao().insertPin(TestUtil.pin());

        // read
        LiveDataTestUtil<List<Pin>> listLiveDataTestUtil = new LiveDataTestUtil<>();
        List<Pin> insertedPins = listLiveDataTestUtil.getValue(getPinDao().getPinsFromDb());

        assertNotNull(insertedPins);

        assertEquals(pinList.get(0).getId(), insertedPins.get(0).getId());
        assertEquals(pinList.get(0).getCreatedAt(), insertedPins.get(0).getCreatedAt());
        assertEquals(pinList.get(0).getWidth(), insertedPins.get(0).getWidth());
        assertEquals(pinList.get(0).getHeight(), insertedPins.get(0).getHeight());
        assertEquals(pinList.get(0).getColor(), insertedPins.get(0).getColor());
        assertEquals(pinList.get(0).getLikedByUser(), insertedPins.get(0).getLikedByUser());
        assertEquals(pinList.get(0).getCurrentUserCollections(), insertedPins.get(0).getCurrentUserCollections());
    }

}
