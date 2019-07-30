package com.example.mindvalleychallenge.di.pin;

import androidx.lifecycle.ViewModel;

import com.example.mindvalleychallenge.di.ViewModelKey;
import com.example.mindvalleychallenge.ui.PinViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PinViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PinViewModel.class)
    public abstract ViewModel bindPinViewModel(PinViewModel pinViewModel);
}
