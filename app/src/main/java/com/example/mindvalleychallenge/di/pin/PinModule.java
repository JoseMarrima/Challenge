package com.example.mindvalleychallenge.di.pin;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.mindvalleychallenge.ui.adapters.RecyclerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class PinModule {

    @PinScope
    @Provides
    static RecyclerAdapter provideRecyclerAdapter(RequestManager requestManager, ViewPreloadSizeProvider<String> viewPreloadSizeProvider){
        return new RecyclerAdapter(requestManager, viewPreloadSizeProvider);
    }
}
