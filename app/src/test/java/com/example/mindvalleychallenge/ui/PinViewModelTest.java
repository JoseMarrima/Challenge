package com.example.mindvalleychallenge.ui;

import com.example.mindvalleychallenge.data.Repository;
import com.example.mindvalleychallenge.data.remote.Pin;
import com.example.mindvalleychallenge.util.LiveDataTestUtil;
import com.example.mindvalleychallenge.util.TestUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PinViewModelTest {

    // System under test
    private PinViewModel pinViewModel;

    @Mock
    private Repository repository;

    @BeforeEach
    public void innit(){
        MockitoAnnotations.initMocks(this);
        pinViewModel = new PinViewModel(repository);
    }

    @Test
    public void testNull() {
        assertThat(pinViewModel.getPins(), notNullValue());
        verify(repository, never()).searchPinsApi();
    }



}
