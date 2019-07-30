package com.example.mindvalleychallenge.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.mindvalleychallenge.R;
import com.example.mindvalleychallenge.data.remote.Pin;
import com.example.mindvalleychallenge.di.ViewModelProviderFactory;
import com.example.mindvalleychallenge.ui.adapters.RecyclerAdapter;
import com.example.mindvalleychallenge.util.Resource;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinActivity extends DaggerAppCompatActivity {

    private static final String TAG = "PinActivity";

    private PinViewModel mPinViewModel;
    private RecyclerView mRecyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Inject
    public RecyclerAdapter mAdapter;

    @Inject
    public ViewPreloadSizeProvider<String> viewPreloader;

    @Inject
    public ViewModelProviderFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        mRecyclerView = findViewById(R.id.recyclerView);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        mPinViewModel = ViewModelProviders.of(this, factory).get(PinViewModel.class);

        mPinViewModel.searchPinApi();
        initRecyclerView();
        mAdapter.notifyDataSetChanged();
        searchPersonsApi();

        subscribeObservers();
    }


    private void subscribeObservers() {

        mPinViewModel.getPins().observe(this, new Observer<Resource<List<Pin>>>() {

            @Override
            public void onChanged(Resource<List<Pin>> listResource) {
                if (listResource != null) {
                    Log.d(TAG, "onChanged: status " + listResource.status);

                    if (listResource != null) {

                        switch (listResource.status) {
                            case LOADING: {
                                Log.d(TAG, "onChanged: loading***********");
                                // display at the beginning of the page
//                                mAdapter.displayOnlyLoading();
                                break;
                            }
                            case ERROR: {
                                Log.e(TAG, "onChanged: cannot refresh cache");
                                Log.e(TAG, "onChanged: ERROR message " + listResource.message);
                                Log.e(TAG, "onChanged: status: ERROR, #pins: " + listResource.data.size());
//                                mAdapter.hideLoading();
                                mAdapter.setPins(listResource.data);
                                Toast.makeText(PinActivity.this, listResource.message,
                                        Toast.LENGTH_SHORT).show();
                                mShimmerViewContainer.stopShimmerAnimation();
                                mShimmerViewContainer.setVisibility(View.GONE);

                                break;
                            }
                            case SUCCESS: {
                                Log.d(TAG, "onChanged: cache HAS BEEN REFRESHED");
                                Log.d(TAG, "onChanged: status: SUCCESS, #pins: " + listResource.data.size());
//                                mAdapter.hideLoading();
                                mAdapter.setPins(listResource.data);
                                mShimmerViewContainer.stopShimmerAnimation();
                                mShimmerViewContainer.setVisibility(View.GONE);

                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    private void searchPersonsApi() {
        mRecyclerView.smoothScrollToPosition(0);
        mPinViewModel.getPins();
    }

    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewPreloader<String> preloader = new RecyclerViewPreloader<String>(
                Glide.with(this),
                mAdapter, viewPreloader,
                30);

        mRecyclerView.addOnScrollListener(preloader);

        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}
