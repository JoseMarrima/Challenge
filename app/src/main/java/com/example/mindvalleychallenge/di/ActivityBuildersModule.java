package com.example.mindvalleychallenge.di;

import com.example.mindvalleychallenge.di.pin.PinModule;
import com.example.mindvalleychallenge.di.pin.PinScope;
import com.example.mindvalleychallenge.di.pin.PinViewModelModule;
import com.example.mindvalleychallenge.ui.PinActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @PinScope
    @ContributesAndroidInjector(modules = {PinModule.class, PinViewModelModule.class})
    abstract PinActivity contributePinActivity();
}
