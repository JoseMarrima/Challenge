package com.example.mindvalleychallenge.ui.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.mindvalleychallenge.R;
import com.example.mindvalleychallenge.data.remote.Pin;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        ListPreloader.PreloadModelProvider<String> {

    private List<Pin> mPins;
    private RequestManager requestManager;
    private ViewPreloadSizeProvider<String> preloadSizeProvider;

    public RecyclerAdapter(RequestManager requestManager,
                           ViewPreloadSizeProvider viewPreloadSizeProvider) {
        this.requestManager = requestManager;
        this.preloadSizeProvider = viewPreloadSizeProvider;

    }

    public void setPins(List<Pin> mPins) {
        this.mPins = mPins;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PinViewHolder(view, requestManager, preloadSizeProvider);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PinViewHolder) holder).onBind(mPins.get(position));
    }

    @Override
    public int getItemCount() {
        if(mPins != null){
            return mPins.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public List<String> getPreloadItems(int position) {
        String url = mPins.get(position).getUser().getProfileImage().getMedium();
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull String item) {
        return requestManager.load(item);
    }
}