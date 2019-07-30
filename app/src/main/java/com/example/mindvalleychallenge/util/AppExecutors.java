package com.example.mindvalleychallenge.util;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Singleton
public class AppExecutors {

    private final Executor diskIO = Executors.newSingleThreadExecutor();

    private final Executor mainThread = new MainThreadExecutor();

    public Executor diskIO(){
        return diskIO;
    }

    public Executor mainThread(){
        return mainThread;
    }


    private static class MainThreadExecutor implements Executor{
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}