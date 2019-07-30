package com.example.mindvalleychallenge.di;

import android.app.Application;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.mindvalleychallenge.R;
import com.example.mindvalleychallenge.data.Repository;
import com.example.mindvalleychallenge.data.local.PinDao;
import com.example.mindvalleychallenge.data.local.PinDatabase;
import com.example.mindvalleychallenge.data.remote.PinServiceApi;
import com.example.mindvalleychallenge.ui.adapters.RecyclerAdapter;
import com.example.mindvalleychallenge.util.AppExecutors;
import com.example.mindvalleychallenge.util.Constants;
import com.example.mindvalleychallenge.util.LiveDataCallAdapterFactory;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mindvalleychallenge.data.local.PinDatabase.DATABASE_NAME;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                // this is how we specify how to convert the call to livedata
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static PinServiceApi providePinServiceApi(Retrofit retrofit){
        return retrofit.create(PinServiceApi.class);
    }

    @Singleton
    @Provides
    static PinDatabase providePinDatabase(Application application){
        return Room.databaseBuilder(application, PinDatabase.class, DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    static PinDao providePinDao(PinDatabase pinDatabase){
        return pinDatabase.getPinDao();
    }

    @Singleton
    @Provides
    static Repository provideRepository(PinDao pinDao, PinServiceApi pinServiceApi, AppExecutors appExecutors){
        return new Repository(pinDao, pinServiceApi, appExecutors);
    }

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors(){
        return new AppExecutors();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static ViewPreloadSizeProvider<String> provideViewPreloadSizeProvider(){
        return new ViewPreloadSizeProvider<>();
    }

    @Singleton
    @Provides
    static RecyclerViewPreloader<String> provideRecyclerViewPreloader(Application application, RecyclerAdapter recyclerAdapter, ViewPreloadSizeProvider<String> viewPreloadSizeProvider){
        return new RecyclerViewPreloader<String>(
                Glide.with(application), recyclerAdapter, viewPreloadSizeProvider, 10);
    }

}
