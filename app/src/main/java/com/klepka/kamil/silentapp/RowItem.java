package com.klepka.kamil.silentapp;

import android.text.Spannable;

/**
 * Created by Kamil on 2016-03-16.
 */
public class RowItem {
    private int imageId;
    private String title;
    private Spannable desc;


    public RowItem(int imageId, String title, Spannable desc) {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Spannable getDesc() {
        return desc;
    }

    public void setDesc(Spannable desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}