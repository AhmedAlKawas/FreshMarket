package com.example.freshmarket.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class OnBoardingItem {

    private String title;
    private String description;
    private int itemImage;

    public OnBoardingItem(String title, String description, int itemImage) {
        this.title = title;
        this.description = description;
        this.itemImage = itemImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    @BindingAdapter("android:loadImg")
    public static void loadImage(ImageView imageView, int imageResource) {

        imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(imageResource));

    }

}
