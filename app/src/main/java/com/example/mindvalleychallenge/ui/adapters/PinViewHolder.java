package com.example.mindvalleychallenge.ui.adapters;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.example.mindvalleychallenge.R;
import com.example.mindvalleychallenge.data.remote.Pin;

public class PinViewHolder extends RecyclerView.ViewHolder {

    private RequestManager requestManager;

    ViewPreloadSizeProvider viewPreloadSizeProvider;

    private TextView name;
    private TextView userName;
    private TextView likes;
    private ImageView profileImage;
    private ImageView image;

    public PinViewHolder(@NonNull View itemView,  RequestManager requestManager,
                         ViewPreloadSizeProvider viewPreloadSizeProvider) {
        super(itemView);

        this.requestManager = requestManager;

        this.viewPreloadSizeProvider = viewPreloadSizeProvider;

        image = itemView.findViewById(R.id.image);
        profileImage = itemView.findViewById(R.id.profileImage);
        name = itemView.findViewById(R.id.name);
        userName = itemView.findViewById(R.id.userName);
        likes = itemView.findViewById(R.id.likes);
    }

    public void onBind(Pin pin){

        Uri imagePath = Uri.parse(pin.getUrls().getSmall());
        requestManager
                .load(imagePath)
                .into(image);

        Uri profileImagePath = Uri.parse(pin.getUser().getProfileImage().getLarge());
        requestManager
                .load(profileImagePath)
                .into(profileImage);

        name.setText(pin.getUser().getName());
        userName.setText(pin.getUser().getUsername());
        likes.setText(String.valueOf(pin.getLikes()));
    }

}
