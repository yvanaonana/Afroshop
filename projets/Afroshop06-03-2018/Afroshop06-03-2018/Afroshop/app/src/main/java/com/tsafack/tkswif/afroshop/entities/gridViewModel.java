package com.tsafack.tkswif.afroshop.entities;

/**
 * Created by Stephane on 7/22/2017.
 */

public class gridViewModel {
    private String name;
    private int url;

    public gridViewModel(String name, int url) {
        this.name = name;
        this.url = url;
    }

    public gridViewModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

}
