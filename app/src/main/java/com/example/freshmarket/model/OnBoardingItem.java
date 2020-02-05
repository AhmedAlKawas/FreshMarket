package com.example.freshmarket.model;

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
}
